package org.csmcms.db.dao;

import jakarta.persistence.*;
import org.csmcms.db.dao.filter.ContentDaoFilter;
import org.csmcms.db.dao.query.InitQueryBuilder;
import org.csmcms.db.dao.query.ListQuery;
import org.csmcms.db.dao.query.NewQuery;
import org.csmcms.db.model.CmsEntity;
import org.csmcms.db.model.Content;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class ContentDao extends Dao<Content, ContentDaoFilter>  {

    public ContentDao(EntityManagerFactory emf) {
        super(emf);
        this.listQuery = new StringBuilder("SELECT c FROM Content c\n");
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

    @Override
    public ListQuery<Content> list() {
        this.filter = new ContentDaoFilter();
        return this;
    }

    @Override
    public Optional<List<Content>> getList() {
        if (this.filter == null) {
            return Optional.empty();
        }

        // Date Range
        var startDate = this.filter.getStartDate();
        var endDate = this.filter.getEndDate();
        if (startDate != null &&  endDate != null) {
            this.listQuery.append("WHERE c.createdAt BETWEEN :startDate AND :endDate\n");
        }

        var idList = this.filter.getIds();
        if (idList != null && !idList.isEmpty()) {
            this.listQuery.append("AND c.id = :ids;");
        }

        try {
            TypedQuery<Content> finalListQuery = this.em.createQuery(this.listQuery.toString(), Content.class);
            finalListQuery.setParameter("ids", idList);
            finalListQuery.setParameter("startDate", startDate);
            finalListQuery.setParameter("endDate", endDate);
        }
    }

}
