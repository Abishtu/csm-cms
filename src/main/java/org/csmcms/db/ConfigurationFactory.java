package org.csmcms.db;

import org.csmcms.db.config.Configuration;

import java.util.Map;

public class ConfigurationFactory {
    public static Configuration configurationPostgres() {
        Map<String, String> env = System.getenv();

        return Configuration
                .builder()
                .setPostgresDbService()
                .setName(env.get("POSTGRES_DB"))
                .setPersistenceUnit("org.samcms.db")
                .setUsername(env.get("POSTGRES_USER"))
                .setPassword(env.get("POSTGRES_PASSWORD"))
                .setHost(env.get("POSTGRES_HOSTNAME"))
                .setUrl().build();
    }

    public static Configuration configurationTestContainer() {
        Map<String, String> env = System.getenv();

        return Configuration
                .builder()
                .setTestContainerDbService()
                .setName(env.get("POSTGRES_DB"))
                .setPersistenceUnit("org.samcms.db")
                .setUsername(env.get("POSTGRES_USER"))
                .setPassword(env.get("POSTGRES_PASSWORD"))
                .setHost(env.get("POSTGRES_HOSTNAME"))
                .setUrl().build();
    }
}
