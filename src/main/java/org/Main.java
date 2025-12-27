package org;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.csmcms.db.ConfigurationFactory;
import org.csmcms.db.CsmCms;
import org.csmcms.db.config.Configuration;
import org.csmcms.db.dao.ContentDao;
import org.csmcms.db.model.Content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Configuration config = ConfigurationFactory.configurationFromEnvVars();
        CsmCms cms = new CsmCms(config);

        try {
            EntityManagerFactory emf = cms.getEntityManagerFactory();

            Optional<List<Content>> contentList = ContentDao
                                                        .builder()
                                                        .init(emf)
                                                        .getContentList();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
