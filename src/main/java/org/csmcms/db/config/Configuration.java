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
    private String url;

    public String getPersistenceUnit() {
        return persistenceUnit;
    }

    private void setPersistenceUnit(String pu) {
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

    public Builder builder() {
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
        ConfigurationBuilder setHost(String host);
    } 

    public interface ConfigurationBuilder {
        Configuration build();
    }

    private class Builder implements 
     DbServiceSetter, PersisteneUnitSetter,
     SqLitePathSetter, PostgresDbNameSetter,
     MySqlDbNameSetter, TestContainerDbNameSetter, 
     UsernameSetter, PasswordSetter, HostSetter, 
     ConfigurationBuilder {

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
        SqLitePathSetter setSqLiteDbService() {
            this.dbService = DbService.SQLite;
            this.dbDriver = "";
            return this;
        }

        @Override
        MySqlDbNameSetter setMySqlDbService() {
            this.dbService = DbService.MySQL;
            this.dbDriver = "";
            return this;
        }

        @Override
        PostgresDbNameSetter setPostgresDbService() {
            this.dbService = DbService.Postgres;
            this.dbDriver = "org.postgres.Driver";
            return this;
        }

    }
   
}
