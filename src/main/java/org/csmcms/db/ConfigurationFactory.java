package org.csmcms.db;

import org.csmcms.db.config.Configuration;
import org.csmcms.db.config.Configuration.ConfigurationBuilder;
import org.csmcms.db.config.options.DbService;

import java.util.Map;

public class ConfigurationFactory {
    public static Configuration configurationFromEnvVars() {
        Map<String, String> env = System.getenv();

        return Configuration
                .builder()
                .setPostgresDbService()
                .setName(env.get("POSTGRES_DB"))
                .setPersistenceUnit("org.samcms.db")
                .setUsername("POSTGRES_USER")
                .setPassword("POSTGRES_PASSWORD")
                .setHost("POSTGRES_HOSTNAME")
                .setUrl().build();
    }
}
