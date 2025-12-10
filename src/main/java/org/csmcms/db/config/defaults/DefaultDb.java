package org.csmcms.db.config.defaults;

import org.csmcms.db.config.options.DbService;

public class DefaultDb {
    public static final String Name = "postgres";
    public static final String Driver = "org.postgresql.Driver";
    public static final String Dialect = "org.hibernate.dialect.PostgresSQLDialect";
    public static final DbService Service = DbService.Postgres;
}
