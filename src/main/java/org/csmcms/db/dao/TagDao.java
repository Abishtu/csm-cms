package org.csmcms.db.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.csmcms.db.model.Tag;

import java.util.List;
import java.util.Optional;

public class TagDao {

    public static InitQueryBuilder builder() {
        return new TagDaoQueryBuilder();
    }

    public interface InitQueryBuilder {
        NewQuery init(EntityManagerFactory emf);
    }
    
    public interface NewQuery {
        // Basic CRUD
        Optional<Tag> save(Tag Tag);
        Optional<Tag> update(Tag Tag);
        Optional<Tag> delete(long id);

        Optional<List<Tag>> getTagList();
        Optional<Tag> getTag(long id);
    }

    static class TagDaoQueryBuilder implements InitQueryBuilder, NewQuery {
        private EntityManager em;

        @Override
        public NewQuery init(EntityManagerFactory emf) {
            this.em = emf.createEntityManager();
            return this;
        }

        @Override
        public Optional<Tag> save(Tag tag) {
            try {
                var tx = em.getTransaction();
                tx.begin();
                em.persist(tag);
                em.flush();
                tx.commit();
                em.close();
                return Optional.of(tag);
            } catch (PersistenceException e) {
                return Optional.empty();
            }
        }

        @Override
        public Optional<Tag> update(Tag tag) {
            try {
                var tx = em.getTransaction();
                tx.begin();
                em.merge(tag);
                em.flush();
                tx.commit();
                em.close();
                return Optional.of(tag);
            } catch (PersistenceException e) {
                return Optional.empty();
            }
        }

        @Override
        public Optional<Tag> delete(long id) {
            return Optional.empty();
        }

        @Override
        public Optional<List<Tag>> getTagList() {
            List<Tag> tags = (List<Tag>) this.em.createQuery("FROM Tag").getResultList();
            return Optional.of(tags);
        }

        @Override
        public Optional<Tag> getTag(long id) {
            Tag tag = em.find(Tag.class, id);
            return Optional.of(tag);
        }
    }
}
