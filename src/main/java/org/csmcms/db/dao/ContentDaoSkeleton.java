package org.csmcms.db.dao;

import org.csmcms.db.model.Content;

import java.util.List;

public interface ContentDaoSkeleton {
    List<Content> getContents();
    Content getContent(long id);


}
