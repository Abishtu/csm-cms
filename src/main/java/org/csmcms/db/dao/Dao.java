package org.csmcms.db.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.csmcms.db.dao.filter.DaoFilter;
import org.csmcms.db.dao.query.InitQueryBuilder;
import org.csmcms.db.dao.query.ListQuery;
import org.csmcms.db.dao.query.NewQuery;
import org.csmcms.db.model.CmsEntity;
import org.csmcms.db.model.Content;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class Dao<T extends CmsEntity, F extends DaoFilter> implements NewQuery<T>, ListQuery<T> {
    protected EntityManager em;
    protected F filter;
    protected StringBuilder listQuery;
    protected CriteriaBuilder cb;

    protected CriteriaQuery<T> query;
    protected Root<T> root;

    protected Dao(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
        this.cb = this.em.getCriteriaBuilder();
    }

    @Override
    public Optional<T> save(T entity) {
        try {
            var tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            em.flush();
            tx.commit();
            return Optional.of(entity);
        } catch (PersistenceException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> update(T entity) {
        try {
            var tx = em.getTransaction();
            tx.begin();
            em.merge(entity);
            em.flush();
            tx.commit();
            return Optional.of(entity);
        } catch (PersistenceException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> get(long id) {
        return Optional.empty();
    }

    @Override
    public ListQuery<T> list() {
        return this;
    }

    @Override
    public ListQuery<T> limit(int limit) {
        this.filter.setLimit(limit);
        return this;
    }

    @Override
    public ListQuery<T> offset(int offset) {
        this.filter.setOffset(offset);
        return this;
    }

    @Override
    public ListQuery<T> ids(ArrayList<Long> ids) {
        this.filter.setIds(ids);
        return this;
    }

    @Override
    public ListQuery<T> dateRange(Date start, Date end) {
        this.filter.setStartDate(start);
        this.filter.setEndDate(end);
        return this;
    }

    @Override
    public Optional<List<T>> getList() {
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(long id) {
        return Optional.empty();
    }


    public void close() {
        this.em.close();
    }
}
