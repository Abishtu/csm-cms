package org.csmcms.db.config;

import org.csmcms.db.config.options.DbService;

import java.util.Optional;

public class Configuration {
    private String persistenceUnit;

    private DbService dbService;

    private Optional<String> dbPath;
    private Optional<String> dbName;
    private String dbDriver;

    private String username;
    private String password;

    private String host;
    private Optional<Integer> port;
    private String url;

    public void setPort(Optional<Integer> port) {
        this.port = port;
    }

    public Optional<Integer> getPort() {
        return port;
    }

    public DbService getDbService() {
        return dbService;
    }

    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public String getPersistenceUnit() {
        return persistenceUnit;
    }

    public void setPersistenceUnit(String pu) {
        this.persistenceUnit = pu;
    }

    public Optional<String> getDbPath() {
        return this.dbPath;
    }

    private void setDbPath(String path) {
        this.dbPath = switch(this.dbService) {
            case SQLite -> Optional.of(path);
            default -> Optional.empty();
        };
    }

    public Optional<String> getDbName() {
        return this.dbName;
    }
    
    private void setDbName(String name) {
        this.dbName = switch(this.dbService) {
            case SQLite -> Optional.empty();
            default -> Optional.of(name);
        };
    }

    public String getDbDriver() {
        return this.dbDriver;
    }

    private void setDbDriver(String driver) {
        this.dbDriver = driver;
    }

    public String getUsername() {
        return this.username;
    }
    
    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return this.host;
    }
    
    private void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return this.url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public static DbServiceSetter builder() {
        return new Builder();
    }

    

    public interface DbServiceSetter {
        SqLitePathSetter setSqLiteDbService();
        PostgresDbNameSetter setPostgresDbService();
        MySqlDbNameSetter setMySqlDbService();
        TestContainerDbNameSetter setTestContainerDbService();
    }

    public interface SqLitePathSetter {
        PersisteneUnitSetter setPath(String path);
    }

    public interface PostgresDbNameSetter {
        PersisteneUnitSetter setName(String name);
    }

    public interface MySqlDbNameSetter {
        PersisteneUnitSetter setName(String name);
    }

    public interface TestContainerDbNameSetter {
        PersisteneUnitSetter setName(String name);
    }

    public interface PersisteneUnitSetter {
        UsernameSetter setPersistenceUnit(String pu);
    }

    public interface UsernameSetter {
        PasswordSetter setUsername(String username);
    }

    public interface PasswordSetter {
        HostSetter setPassword(String password);
    }

    public interface HostSetter {
        UrlSetter setHost(String host);
    }

    public interface UrlSetter {
        ConfigurationBuilder setUrl();
        ConfigurationBuilder setUrl(Integer port);
    }

    public interface ConfigurationBuilder {
        Configuration build();
    }

    private static class Builder implements
     DbServiceSetter, PersisteneUnitSetter,
     SqLitePathSetter, PostgresDbNameSetter,
     MySqlDbNameSetter, TestContainerDbNameSetter, 
     UsernameSetter, PasswordSetter, HostSetter, 
     ConfigurationBuilder, UrlSetter {

        private String persistenceUnit;

        private DbService dbService;

        private String dbPath;
        private String dbName;
        private String dbDriver;

        private String username;
        private String password;

        private String host;
        private String url;

        @Override
        public Configuration build() {
            var config = new Configuration();
            config.setDbService(this.dbService);
            config.setDbDriver(this.dbDriver);
            config.setHost(this.host);
            config.setDbName(this.dbName);
            config.setDbPath(this.dbPath);
            config.setPersistenceUnit(this.persistenceUnit);
            config.setUsername(this.username);
            config.setPassword(this.password);
            config.setUrl(this.url);

            return config;
        }

        @Override
        public SqLitePathSetter setSqLiteDbService() {
            this.dbService = DbService.SQLite;
            this.dbDriver = "";
            return this;
        }

        @Override
        public PostgresDbNameSetter setPostgresDbService() {
            this.dbService = DbService.Postgres;
            this.dbDriver = "org.postgres.Driver";
            return this;
        }

        @Override
        public MySqlDbNameSetter setMySqlDbService() {
            this.dbService = DbService.MySQL;
            this.dbDriver = "com.mysql.jdbc.Driver";
            return this;
        }

        @Override
        public TestContainerDbNameSetter setTestContainerDbService() {
            this.dbService = DbService.TestContainers;
            this.dbDriver = "org.testcontainers.jdbc.ContainerDatabaseDriver";
            return this;
        }

        @Override
        public UrlSetter setHost(String host) {
            this.host = host;
            return this;
        }

        @Override
        public ConfigurationBuilder setUrl() {
            var dbUrl = new StringBuilder("jdbc:");
            var name = switch (this.dbService) {
                case MySQL -> "mysql";
                case Postgres -> "postgresql";
                case SQLite -> "sqlite";
                case TestContainers -> "tc:postgresql";
            };
            dbUrl.append(name)
                 .append("://")
                 .append(host);

            var dbOrPath = switch(this.dbService) {
                case MySQL, Postgres, TestContainers -> "/"+this.dbName;
                case SQLite -> ":" + this.dbPath;
            };
            dbUrl.append(dbOrPath);

            this.url = dbUrl.toString();

            return this;
        }

        @Override
        public ConfigurationBuilder setUrl(Integer port) {
            var dbUrl = new StringBuilder("jdbc:");
            var name = switch (this.dbService) {
                case MySQL -> "mysql";
                case Postgres -> "postgresql";
                case SQLite -> "sqlite";
                case TestContainers -> "tc:postgresql";
            };
            dbUrl.append(name)
                    .append("://")
                    .append(host)
                    .append(":")
                    .append(port);

            var dbOrPath = switch(this.dbService) {
                case MySQL, Postgres, TestContainers -> "/"+this.dbName;
                case SQLite -> ":" + this.dbPath;
            };
            dbUrl.append(dbOrPath);

            this.url = dbUrl.toString();

            return this;
        }

        @Override
        public HostSetter setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public UsernameSetter setPersistenceUnit(String pu) {
            this.persistenceUnit = pu;
            return this;
        }

        @Override
        public PersisteneUnitSetter setName(String name) {
            this.dbName = name;
            return this;
        }

        @Override
        public PersisteneUnitSetter setPath(String path) {
            this.dbPath = path;
            return this;
        }

        @Override
        public PasswordSetter setUsername(String username) {
            this.username = username;
            return this;
        }
    }
   
}
