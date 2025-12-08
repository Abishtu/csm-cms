package org.csmcms.db.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testBuilder() {
        Tag tag = Tag.builder().setName("New Tag").build();
        Tag tag2 = Tag.builder().setName("New Tag2").build();

        Content content = Content
                .builder()
                .setName("TestContent")
                .setDescription("Test Content")
                .setContentType("application/json")
                .setContentPath("#")
                .initTags()
                .addTag(tag)
                .addTag(tag2)
                .build();

        assertEquals("TestContent", content.getName());
        assertEquals("Test Content", content.getDescription());
        assertEquals("application/json", content.getContentType());
        assertEquals("#", content.getContentPath());

        assertEquals(2, content.getTags().size());
    }
}