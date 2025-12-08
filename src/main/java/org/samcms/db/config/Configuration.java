package org.samcms.db.config;

import java.util.Optional;

public class Configuration {

    private String pu;
    private String driver;
    private String url;

    private String dbService;

    private String username;
    private String password;
    private String host;
    private String db;

    private Optional<String> dbDriver;

    public String pu() {
        return pu;
    }

    public String host() {
        return host;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String driver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String userName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<String> dbDriver() {
        return dbDriver;
    }

    public void setDbDriver(Optional<String> dbDriver) {
        this.dbDriver = dbDriver;
    }

    public HostSetter builder() {
        return new Builder();
    }

    public interface HostSetter {
        public UsernameSetter host(String host);
    }

    public interface UsernameSetter {
        public PasswordSetter username(String driver);
    }

    public interface PasswordSetter {
        public DbSetter password(String password);
    }

    public interface DbSetter {
        public DbServiceSetter db(String db);
    }

    public interface DbServiceSetter {
        public UrlSetter dbService(String dbService);
    }

    public interface UrlSetter {
        public OptionalFieldsSetter url();
    }

    public interface OptionalFieldsSetter {
        public OptionalFieldsSetter dbDriver(Optional<String> dbDriver);
        public Configuration build();
    }

    private static class Builder implements HostSetter, UsernameSetter, PasswordSetter, DbSetter, DbServiceSetter, UrlSetter, OptionalFieldsSetter {
        private String pu;
        private String driver;
        private String url;
        private String username;
        private String password;
        private String db;
        private String dbService;
        private String host;


        private Optional<String> dbDriver;

        @Override
        public DbServiceSetter db(String db) {
            this.db = db;
            return this;
        }

        @Override
        public UsernameSetter host(String host) {
            this.host = host;
            return this;
        }

        @Override
        public OptionalFieldsSetter dbDriver(Optional<String> dbDriver) {
            this.dbDriver = dbDriver;
            return this;
        }

        @Override
        public Configuration build() {
            Configuration config = new Configuration();
            config.setPu(this.pu);
            config.setDriver(this.driver);
            config.setUrl(this.url);
            config.setDriver(this.driver);
            config.setPassword(this.driver);
            config.setDbDriver(this.dbDriver);
            config.setUsername(this.username);

            return config;
        }

        @Override
        public DbSetter password(String password) {
            return null;
        }

        @Override
        public PasswordSetter username(String driver) {
            return null;
        }

        @Override
        public UrlSetter dbService(String dbService) {
            return null;
        }

        @Override
        public OptionalFieldsSetter url() {
            return null;
        }
    }
}
