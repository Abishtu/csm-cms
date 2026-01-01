package org.csmcms.db.dao.query;

import jakarta.persistence.EntityManagerFactory;
import org.csmcms.db.model.CmsEntity;

public interface InitQueryBuilder<T extends CmsEntity> {
    NewQuery<T> init(EntityManagerFactory emf);
}
