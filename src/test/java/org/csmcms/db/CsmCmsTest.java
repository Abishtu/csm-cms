package org.csmcms.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.csmcms.db.config.Configuration;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;


class CsmCmsTest {
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testMakeNewSamCmsDbConnection() {
        Configuration configuration = ConfigurationFactory.configurationPostgres();
        configuration.setPersistenceUnit("org.samcms.db.test");

        var csmCms = new CsmCms(configuration);
        entityManagerFactory = csmCms.getEntityManagerFactory();

        assertNotNull(entityManagerFactory);

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        assertNotNull(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }
}