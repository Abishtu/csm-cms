package org.csmcms.db.dao.query;

import org.csmcms.db.model.CmsEntity;
import org.csmcms.db.model.Content;

import java.util.List;
import java.util.Optional;

public interface NewQuery<T extends CmsEntity> {
    Optional<T> save(T entity);
    Optional<T> update(T entity);
    Optional<T> delete(long id);

    ListQuery<T> list();
    Optional<T> get(long id);

}
