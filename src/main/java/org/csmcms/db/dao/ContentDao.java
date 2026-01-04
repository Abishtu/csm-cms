package org.csmcms.db.dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.csmcms.db.dao.filter.ContentDaoFilter;
import org.csmcms.db.dao.query.InitQueryBuilder;
import org.csmcms.db.dao.query.ListQuery;
import org.csmcms.db.dao.query.NewQuery;
import org.csmcms.db.model.CmsEntity;
import org.csmcms.db.model.Content;

import java.util.*;


public class ContentDao extends Dao<Content, ContentDaoFilter>  {

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

    @Override
    public ListQuery<Content> list() {
        this.filter = new ContentDaoFilter();
        this.query = this.cb.createQuery(Content.class);
        this.root = query.from(Content.class);
        return this;
    }

    @Override
    public Optional<List<Content>> getList() {
        if (this.filter == null) {
            return Optional.empty();
        }

        ArrayList<Predicate> predicates = new ArrayList<>();

        var startDate = this.filter.getStartDate();
        if (startDate != null) {
            predicates.add(
                cb.greaterThanOrEqualTo(
                    root.get("createdAt"),
                    startDate
                )
            );
        }

        var endDate = this.filter.getEndDate();
        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), endDate));
        }

        var ids =  this.filter.getIds();
        if (ids != null && !ids.isEmpty()) {
            List<Long> idsList = ids.stream().toList();
            Expression<Long> idsExpression = root.get("id");
            Predicate idsPredicate = idsExpression.in(idsList);
            predicates.add(idsPredicate);
        }

        query.select(root).where(predicates);
        try {
            List<Content> resultsList = em.createQuery(query)
                    .setFirstResult(this.filter.getOffset())
                    .setMaxResults(this.filter.getLimit())
                    .getResultList();
            return Optional.of(resultsList);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
