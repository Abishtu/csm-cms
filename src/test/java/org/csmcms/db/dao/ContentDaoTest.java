package org.csmcms.db.dao;

import jakarta.persistence.EntityManagerFactory;
import org.csmcms.db.ConfigurationFactory;
import org.csmcms.db.CsmCms;
import org.csmcms.db.model.Content;
import org.csmcms.db.model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ContentDaoTest {
    private EntityManagerFactory emf;
    @BeforeEach
    void setUp() {
        var configuration = ConfigurationFactory.configurationPostgres();
        configuration.setPersistenceUnit("org.samcms.db.test");

        var csmCms = new CsmCms(configuration);
        this.emf = csmCms.getEntityManagerFactory();
    }

    @AfterEach
    void tearDown() {
        this.emf.close();
    }

    @Test
    void testCreateNewContent() {
        Content newContent = Content
                .builder()
                .setName("nc1")
                .setDescription("A new piece of content")
                .setContentType("text/plain")
                .setContentPath("#")
                .build();

        var createdContent = ContentDao
                .builder()
                .init(emf)
                .save(newContent);

        assertTrue(createdContent.isPresent());
        assertTrue(createdContent.get().getId() > 0);
    }

    @Test
    void testGetContent() {
        Content newContent = Content
                .builder()
                .setName("nc1")
                .setDescription("A new piece of content")
                .setContentType("text/plain")
                .setContentPath("#")
                .build();

        var createdContent = ContentDao
                .builder()
                .init(emf)
                .save(newContent);

        assertTrue(createdContent.isPresent());
        assertTrue(createdContent.get().getId() > 0);

        long id = createdContent.get().getId();

        var content = ContentDao
                .builder()
                .init(emf)
                .getContent(id);

        assertTrue(content.isPresent());
        assertEquals(id, content.get().getId());
    }

    @Test
    void testGetContentList() {
        var contentList = new ArrayList<Content>();
        contentList.add(
                Content
                        .builder()
                        .setName("nc1")
                        .setDescription("A new piece of content")
                        .setContentType("text/plain")
                        .setContentPath("#")
                        .build()
        );

        contentList.add(
                Content
                        .builder()
                        .setName("nc2")
                        .setDescription("A new piece of content")
                        .setContentType("text/plain")
                        .setContentPath("#")
                        .build()
        );

        for (Content content : contentList) {
            ContentDao
                    .builder()
                    .init(emf)
                    .save(content);
        }

        var createdContents = ContentDao
                .builder()
                .init(emf)
                .getContentList();

        assertTrue(createdContents.isPresent());
        assertEquals(contentList.size(), createdContents.get().size());
    }

    @Test
    void testUpdateContent() {
        Content newContent = Content
                .builder()
                .setName("nc1")
                .setDescription("A new piece of content")
                .setContentType("text/plain")
                .setContentPath("#")
                .build();

        var createdContent = ContentDao
                .builder()
                .init(emf)
                .save(newContent);

        assertTrue(createdContent.isPresent());
        assertTrue(createdContent.get().getId() > 0);

        var contentToUpdate = createdContent.get();

        contentToUpdate.setName("new name");

        var updatedContent = ContentDao.builder().init(emf).update(contentToUpdate);

        assertTrue(updatedContent.isPresent());
        assertEquals(contentToUpdate.getName(), updatedContent.get().getName());
    }

    @Test
    void testCreateContentWithExistingTag() {
        Tag newTag = Tag
                .builder()
                .setName("newTag")
                .build();

        Tag newTag2 = Tag
                .builder()
                .setName("newTag")
                .build();

        var tag = TagDao.builder().init(emf).save(newTag);

        assertTrue(tag.isPresent());
        assertTrue(tag.get().getId() > 0);


        var tag2 = TagDao.builder().init(emf).save(newTag2);

        assertTrue(tag2.isPresent());
        assertTrue(tag2.get().getId() > 0);


        Tag existingTag = tag.get();
        Content newContent = Content
                .builder()
                .setName("nc1")
                .setDescription("A new piece of content")
                .setContentType("text/plain")
                .setContentPath("#")
                .initTags()
                .addTag(existingTag)
                .build();

        var createdContent = ContentDao
                .builder()
                .init(emf)
                .save(newContent);

        assertTrue(createdContent.isPresent());
        assertTrue(createdContent.get().getId() > 0);

        assertEquals(1, createdContent.get().getTags().size());

        for (Tag contentTag  : createdContent.get().getTags()) {
            assertEquals(contentTag.getId(), existingTag.getId());
        }


    }
}