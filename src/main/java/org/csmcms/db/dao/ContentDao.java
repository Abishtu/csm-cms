package org.csmcms.db.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.csmcms.db.model.Content;

import java.util.List;
import java.util.Optional;


public class ContentDao {

    public static InitQueryBuilder builder() {
        return new ContentDaoQueryBuilder();
    }

    public interface InitQueryBuilder {
        NewQuery init(EntityManagerFactory emf);
    }

    public interface NewQuery {
        // Basic CRUD
        Optional<Content> save(Content content);
        Optional<Content> update(Content content);
        Optional<Content> delete(long id);

        Optional<List<Content>> getContentList();
        Optional<Content> getContent(long id);
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
                em.persist(content);
                return Optional.of(content);
            } catch (PersistenceException e) {
                return Optional.empty();
            }
        }

        @Override
        public Optional<Content> update(Content content) {
            return Optional.empty();
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
            Content content = this.em.createQuery("FROM Content WHERE id = :id", Content.class)
                    .setParameter("id", id).getSingleResult();
            return Optional.of(content);
        }
    }
}
