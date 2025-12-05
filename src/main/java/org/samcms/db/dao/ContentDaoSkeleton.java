package org.samcms.db.dao;

import jakarta.persistence.EntityManagerFactory;
import org.samcms.db.model.Content;

import java.util.List;
import java.util.Optional;

public interface ContentDaoSkeleton {
    List<Content> getContents();
    Content getContent(long id);


}
