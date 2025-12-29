package org.csmcms.db.dao;

import jakarta.persistence.EntityManagerFactory;
import org.csmcms.db.ConfigurationFactory;
import org.csmcms.db.CsmCms;
import org.csmcms.db.model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TagDaoTest {
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
    void testCreateTag() {

        Tag newTag = Tag
                .builder()
                .setName("newTag")
                .build();

        var createdTag = TagDao
                .builder()
                .init(emf)
                .save(newTag);

        assertTrue(createdTag.isPresent());
        assertTrue(createdTag.get().getId() > 0);
    }

    @Test
    void testGetTag() {
        Tag newTag = Tag
                .builder()
                .setName("newTag")
                .build();

        var createdTag = TagDao
                .builder()
                .init(emf)
                .save(newTag);

        assertTrue(createdTag.isPresent());
        assertTrue(createdTag.get().getId() > 0);

        long id = createdTag.get().getId();

        var tag = TagDao
                .builder()
                .init(emf)
                .getTag(id);

        assertTrue(tag.isPresent());
        assertEquals(id, tag.get().getId());
    }

    @Test
    void testGetTagList() {
        var tagList = new ArrayList<Tag>();
        tagList.add(
                Tag
                        .builder()
                        .setName("newTag")
                        .build()
        );

        tagList.add(
                Tag
                        .builder()
                        .setName("newTag2")
                        .build()
        );

        for (Tag tag : tagList) {
            TagDao
                    .builder()
                    .init(emf)
                    .save(tag);
        }

        var createdTagList = TagDao
                .builder()
                .init(emf)
                .getTagList();

        assertTrue(createdTagList.isPresent());
        assertEquals(tagList.size(), createdTagList.get().size());
    }

    @Test
    void testUpdateTag() {

        Tag newTag = Tag
                .builder()
                .setName("newTag")
                .build();

        var createdTag = TagDao
                .builder()
                .init(emf)
                .save(newTag);

        assertTrue(createdTag.isPresent());
        assertTrue(createdTag.get().getId() > 0);

        var tagToUpdate = createdTag.get();

        tagToUpdate.setName("new Tag name");

        var updatedTag = TagDao.builder().init(emf).update(tagToUpdate);

        assertTrue(updatedTag.isPresent());
        assertEquals(updatedTag.get().getName(), tagToUpdate.getName());

    }
}