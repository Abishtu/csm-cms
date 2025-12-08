package org.samcms.db.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void builder() {
        Tag tag = Tag
                .builder()
                .setName("New Tag")
                .build();

        assertEquals("New Tag", tag.getName());
    }
}