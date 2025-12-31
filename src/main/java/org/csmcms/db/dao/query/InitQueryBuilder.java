package org.csmcms.db.dao.query;

import jakarta.persistence.EntityManagerFactory;

public interface InitQueryBuilder<T> {
    NewQuery<T> init(EntityManagerFactory emf);
}
