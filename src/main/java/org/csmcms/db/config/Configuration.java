package org.csmcms.db.config;

import org.csmcms.db.config.options.DbService;

import java.util.Optional;

public class Configuration {

    private DbService dbService;

    private String persistenceUnit;
    private String username;
    private String password;
    private String host;
    private String url;

    public String getPersistenceUnit() {
        return persistenceUnit;
    }

    public void setPersistenceUnit(String persistenceUnit) {
        this.persistenceUnit = persistenceUnit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl() {

        String host = "";
        String dbName = switch (dbService) {
            case Postgres ->  "postgres";
            case MySQL -> "mysql";
            case SQLite -> "sqlite";
        };


    }

    public DbService getDbService() {
        return dbService;
    }

    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public HostSetter builder() {
        return new Builder();
    }

    public interface HostSetter {
        public UsernameSetter host(String host);
    }

    public interface UsernameSetter {
        public PasswordSetter username(String username);
    }

    public interface PasswordSetter {
        public DbSetter password(String password);
    }

    public interface DbSetter {
        public UrlSetter dbService(DbService dbService);
    }

    public interface UrlSetter {
        public FinalBuilder url();
    }

    public interface FinalBuilder {
        public Configuration build();
    }

    private static class Builder implements HostSetter, UsernameSetter, PasswordSetter, DbSetter, UrlSetter, FinalBuilder {

        private DbService dbService;
        private String persistenceUnit;
        private String username;
        private String password;
        private String host;

        @Override
        public UrlSetter dbService(DbService dbService) {
            this.dbService = dbService;
            return this;
        }

        @Override
        public Configuration build() {
            return null;
        }

        @Override
        public UsernameSetter host(String host) {
            this.host = host;
            return null;
        }

        @Override
        public DbSetter password(String password) {
            this.password = password;
            return null;
        }

        @Override
        public FinalBuilder url() {
            return null;
        }

        @Override
        public PasswordSetter username(String username) {
            this.username = username;
            return null;
        }
    }
}
