package org.csmcms.db.config;

import org.csmcms.db.config.options.DbService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {
    @Test
    void testBuilder() {
        Configuration config = Configuration
                .builder()
                .setTestContainerDbService()
                .setName("db")
                .setPersistenceUnit("Some Pu")
                .setUsername("username")
                .setPassword("password")
                .setHost("host")
                .setUrl().build();

        assertEquals(DbService.TestContainers, config.getDbService());
        assertEquals("com.postgres.Driver", config.getDbDriver());
        assertTrue(config.getDbName().isPresent());
        if (config.getDbName().isPresent()) {
            assertEquals("db", config.getDbName().get());
        }
        assertEquals("Some Pu", config.getPersistenceUnit());
        assertEquals("username", config.getUsername());
        assertEquals("password", config.getPassword());
        assertEquals("host", config.getHost());

        assertEquals("jdbc:postgres://host/db", config.getUrl());

    }
}