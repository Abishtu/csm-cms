package org.csmcms.db.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.csmcms.db.dao.query.InitQueryBuilder;
import org.csmcms.db.dao.query.ListQuery;
import org.csmcms.db.dao.query.NewQuery;
import org.csmcms.db.model.Content;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class ContentDao implements InitQueryBuilder, NewQuery<Content>, ListQuery<Content> {


    @Override
    public NewQuery init(EntityManagerFactory emf) {
        return null;
    }

    @Override
    public ListQuery<Content> limit(int limit) {
        return null;
    }

    @Override
    public ListQuery<Content> offset(int offset) {
        return null;
    }

    @Override
    public ListQuery<Content> ids(List<Integer> ids) {
        return null;
    }

    @Override
    public ListQuery<Content> dateRange(Date start, Date end) {
        return null;
    }

    @Override
    public Optional<List<Content>> get() {
        return Optional.empty();
    }

    @Override
    public Optional<Content> save(Content entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Content> update(Content entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Content> delete(long id) {
        return Optional.empty();
    }

    @Override
    public ListQuery<Content> list() {
        return null;
    }

    @Override
    public Optional<Content> get(long id) {
        return Optional.empty();
    }

    static class ContentDaoQueryBuilder implements InitQueryBuilder, NewQuery {
        private EntityManager em;
        @Override
        public NewQuery init(EntityManagerFactory emf) {
            this.em = emf.createEntityManager();
            return this;
        }

        @Override
        public Optional<Content> save(Content content) {
            try {
                var tx = em.getTransaction();
                tx.begin();
                em.persist(content);
                em.flush();
                tx.commit();
                em.close();
                return Optional.of(content);
            } catch (PersistenceException e) {
                return Optional.empty();
            }
        }

        @Override
        public Optional<Content> update(Content content) {
            try {
                var tx = em.getTransaction();
                tx.begin();
                em.merge(content);
                em.flush();
                tx.commit();
                em.close();
                return Optional.of(content);
            } catch (PersistenceException e) {
                return Optional.empty();
            }
        }

        @Override
        public Optional<Content> delete(long id) {
            return Optional.empty();
        }

        @Override
        public Optional<List<Content>> getContentList() {
            List<Content> contents = (List<Content>) this.em.createQuery("FROM Content").getResultList();
            return Optional.of(contents);
        }

        @Override
        public Optional<Content> getContent(long id) {
            Content content = this.em.find(Content.class, id);
            return Optional.of(content);
        }
    }
}
