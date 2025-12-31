package org.csmcms.db.dao.filter;

import java.util.ArrayList;
import java.util.Date;

public class DaoFilter {
    private int limit;
    private int offset;

    private Date startDate;
    private Date endDate;

    private ArrayList<Integer> ids;

    public DaoFilter() {
        this.limit = 100;
        this.offset = 0;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Integer> ids) {
        this.ids = ids;
    }
}
