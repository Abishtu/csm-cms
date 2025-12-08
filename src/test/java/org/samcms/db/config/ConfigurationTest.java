package org.samcms.db.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void getDbUrl() {
        Configuration config = new Configuration(
                "newUnit",
                "newPassword",
                "newUser",
                "newHost",
                "newDbName"
        );

        assertEquals("jdbc:postgresql://newHost/newDbName", config.getDbUrl());
    }

    @Test
    void persistenceUnitName() {
        Configuration config = new Configuration(
                "newUnit",
                "newPassword",
                "newUser",
                "newHost",
                "newDbName"
        );

        assertEquals("newUnit", config.persistenceUnitName());
    }

    @Test
    void password() {
        Configuration config = new Configuration(
                "newUnit",
                "newPassword",
                "newUser",
                "newHost",
                "newDbName"
        );

        assertEquals("newPassword", config.password());
    }

    @Test
    void username() {
        Configuration config = new Configuration(
                "newUnit",
                "newPassword",
                "newUser",
                "newHost",
                "newDbName"
        );

        assertEquals("newUser", config.username());
    }

    @Test
    void hostName() {
        Configuration config = new Configuration(
                "newUnit",
                "newPassword",
                "newUser",
                "newHost",
                "newDbName"
        );

        assertEquals("newHost", config.hostName());
    }

    @Test
    void dbName() {
        Configuration config = new Configuration(
                "newUnit",
                "newPassword",
                "newUser",
                "newHost",
                "newDbName"
        );

        assertEquals("newDbName", config.dbName());
    }
}