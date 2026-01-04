package org.csmcms.db.dao.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ListQuery<T> {

    ListQuery<T> limit(int limit);
    ListQuery<T> offset(int offset);
    ListQuery<T> ids(ArrayList<Long> ids);

    ListQuery<T> dateRange(Date start, Date end);

    Optional<List<T>> getList();
}
