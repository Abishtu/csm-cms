package org.csmcms.db.dao.query;

import jakarta.persistence.EntityManagerFactory;

public interface InitQueryBuilder {
    NewQuery init(EntityManagerFactory emf);
}
