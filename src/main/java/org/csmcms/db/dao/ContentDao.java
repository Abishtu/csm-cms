package org.csmcms.db.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceException;
import org.csmcms.db.dao.query.InitQueryBuilder;
import org.csmcms.db.dao.query.ListQuery;
import org.csmcms.db.dao.query.NewQuery;
import org.csmcms.db.model.CmsEntity;
import org.csmcms.db.model.Content;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class ContentDao extends Dao<Content>  {

    public ContentDao(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Optional<Content> get(long id) {
        try {
            Content content = em.find(Content.class, id);
            return Optional.of(content);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
