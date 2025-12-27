package org.csmcms.db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.csmcms.db.config.Configuration;

import java.util.HashMap;
import java.util.Map;

public class CsmCms {
    private Configuration configuration;
    private EntityManagerFactory entityManagerFactory;


    public CsmCms(Configuration configuration) {
        this.configuration = configuration;
        var configOverrides = getConfigurationOverridesMap();

        entityManagerFactory = Persistence.createEntityManagerFactory(
                this.configuration.getPersistenceUnit(),
                configOverrides
        );
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private Map<String, Object> getConfigurationOverridesMap() {
        Map<String, Object> configOverrides = new HashMap<>();

        configOverrides.put("jakarta.persistence.jdbc.url", this.configuration.getUrl());
        configOverrides.put("jakarta.persistence.jdbc.user", this.configuration.getUsername());
        configOverrides.put("jakarta.persistence.jdbc.password", this.configuration.getPassword());

        return configOverrides;
    }
}
