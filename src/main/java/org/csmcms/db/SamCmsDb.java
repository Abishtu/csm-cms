package org.csmcms.db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.csmcms.db.config.Configuration;

import java.util.HashMap;
import java.util.Map;

public class SamCmsDb {
    private Configuration configuration;
    EntityManagerFactory entityManagerFactory;

    public Configuration getConfiguration() {
        return configuration;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public ConfigurationSetter builder() {
        return new Builder();
    }

    public interface ConfigurationSetter {
        public FinalBuild setConfiguration(Configuration configuration);
    }

    public interface FinalBuild {
        public SamCmsDb init();
    }

    private static class Builder implements ConfigurationSetter, FinalBuild {
        private Configuration configuration;

        public FinalBuild setConfiguration(Configuration configuration) {
            this.configuration = configuration;
            return this;
        }

        public SamCmsDb init() {
            SamCmsDb samCmsDb = new SamCmsDb();
            samCmsDb.setConfiguration(configuration);

            Map<String, Object> configOverrides = new HashMap<String, Object>();

            configOverrides.put("jakarta.persistence.jdbc.user", configuration.username());
            configOverrides.put("jakarta.persistence.jdbc.password", configuration.password());
            configOverrides.put("jakarta.persistence.jdbc.url", configuration.getDbUrl());


            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
                    this.configuration.persistenceUnitName(),
                    configOverrides
            );

            samCmsDb.setEntityManagerFactory(entityManagerFactory);

            return samCmsDb;
        }
    }
}
