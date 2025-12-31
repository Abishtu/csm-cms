package org.csmcms.db.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.csmcms.db.dao.filter.DaoFilter;
import org.csmcms.db.dao.query.InitQueryBuilder;
import org.csmcms.db.dao.query.ListQuery;
import org.csmcms.db.dao.query.NewQuery;
import org.csmcms.db.model.CmsEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class Dao implements NewQuery<CmsEntity>, ListQuery<CmsEntity> {
    private EntityManager em;
    private DaoFilter filter;

    protected Dao(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    @Override
    public Optional<CmsEntity> save(CmsEntity entity) {
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
    public Optional<CmsEntity> update(CmsEntity entity) {
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
    public Optional<CmsEntity> get(long id) {
        try {
            var tx = em.getTransaction();
            tx.begin();
            CmsEntity entity = em.find(CmsEntity.class, id);
            em.flush();
            tx.commit();
            return Optional.of(entity);
        }  catch (PersistenceException e) {
            return Optional.empty();
        }
    }

    @Override
    public ListQuery<CmsEntity> list() {
        this.filter = new DaoFilter();
        return this;
    }

    @Override
    public ListQuery<CmsEntity> limit(int limit) {
        this.filter.setLimit(limit);
        return this;
    }

    @Override
    public ListQuery<CmsEntity> offset(int offset) {
        this.filter.setOffset(offset);
        return this;
    }

    @Override
    public ListQuery<CmsEntity> ids(ArrayList<Integer> ids) {
        this.filter.setIds(ids);
        return this;
    }

    @Override
    public ListQuery<CmsEntity> dateRange(Date start, Date end) {
        this.filter.setStartDate(start);
        this.filter.setEndDate(end);
        return this;
    }

    @Override
    public Optional<List<CmsEntity>> getList() {
        return Optional.empty();
    }

    @Override
    public Optional<CmsEntity> get() {
        return Optional.empty();
    }

    @Override
    public Optional<CmsEntity> delete(long id) {
        return Optional.empty();
    }


    public void close() {
        this.em.close();
    }
}
