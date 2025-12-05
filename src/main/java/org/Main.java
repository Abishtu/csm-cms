package org;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.samcms.db.dao.ContentDao;
import org.samcms.db.model.Content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();

        String postgresHost = "";
        String postgresDb = "";
        StringBuilder postgresUrl = new StringBuilder("jdbc:postgresql://");

        for (String envName : env.keySet()) {
            if (envName.contains("POSTGRES_USER")) {
                configOverrides.put("jakarta.persistence.jdbc.user", env.get(envName));
            }

            if (envName.contains("POSTGRES_PASSWORD")) {
                configOverrides.put("jakarta.persistence.jdbc.password", env.get(envName));
            }

            if (envName.contains("POSTGRES_HOSTNAME")) {
                postgresHost = env.get(envName);
            }

            if (envName.contains("POSTGRES_DB")) {
                postgresDb = env.get(envName);
            }
        }

        if (!postgresHost.isEmpty() && !postgresDb.isEmpty()) {
            String url = postgresUrl
                    .append(postgresHost)
                    .append("/")
                    .append(postgresDb)
                    .toString();

            configOverrides.put("jakarta.persistence.jdbc.url", url);
        }

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.samcms.db", configOverrides);



            Optional<List<Content>> contentList = ContentDao
                                                        .builder()
                                                        .init(emf)
                                                        .getContentList();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
