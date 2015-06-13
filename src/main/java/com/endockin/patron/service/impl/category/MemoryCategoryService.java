package com.endockin.patron.service.impl.category;

import com.endockin.patron.model.Blueprint;
import com.endockin.patron.model.Category;
import com.endockin.patron.service.blueprint.BlueprintService;
import com.endockin.patron.service.blueprint.BlueprintServiceException;
import com.endockin.patron.service.category.CategoryService;
import com.endockin.patron.service.category.CategoryServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryCategoryService implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(MemoryCategoryService.class);

    @Autowired
    private BlueprintService blueprintService;

    private final Map<String, Category> categories = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            initializeMap();
        } catch (BlueprintServiceException ex) {
            LOG.error("Unable to initialize map.", ex);
        }
    }

    @Override
    public List<Category> findAll() throws CategoryServiceException {
        return new ArrayList<>(categories.values());
    }

    @Override
    public Category find(String id) throws CategoryServiceException {
        if (!categories.containsKey(id)) {
            throw new CategoryServiceException("Category with id [" + id + "] does not exist");
        }

        return categories.get(id);
    }

    private void initializeMap() throws BlueprintServiceException {
        LOG.info("Initializing categories");

        //OS
        Category operatingSystems = new Category();
        operatingSystems.setId("os");
        operatingSystems.setName("Operating Systems");

        Blueprint ubuntu = new Blueprint();
        ubuntu.setDescription("Ubuntu Operating System Blueprint");
        ubuntu.setId("ubuntu");
        ubuntu.setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_ubuntu-h.png");
        ubuntu.setName("Ubuntu");
        blueprintService.save(ubuntu);

        Blueprint debian = new Blueprint();
        debian.setDescription("Debian Operating System Blueprint");
        debian.setId("debian");
        debian.setLogo("http://upload.wikimedia.org/wikipedia/commons/0/04/Debian_logo.png");
        debian.setName("Debian");
        blueprintService.save(debian);

        operatingSystems.setBlueprints(new ArrayList<>(Arrays.asList(ubuntu, debian)));
        categories.put(operatingSystems.getId(), operatingSystems);

        /*
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (3, 1, 'Postgres', 'Dummy description', 'http://media.tumblr.com/290f58218f6c7edc49ee60bbaf924156/tumblr_inline_moaefiNoSY1qz4rgp.jpg');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (5, 3, 'Fedora', 'Dummy description', 'https://www.mysql.com/common/logos/logo-mysql-110x57.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (7, 3, 'CentOS', 'Dummy description', 'https://www.mysql.com/common/logos/logo-mysql-110x57.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (8, 2, 'Django', 'Dummy description', 'http://www.howopensource.com/wp-content/uploads/2011/06/django-logo.jpg');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (9, 2, 'Tomcat', 'Dummy description', 'http://tomcat.apache.org/images/tomcat.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (10, 2, 'Bamboo server', 'Dummy description', 'https://www.atlassian.com/wac/software/bamboo/productLogo/imageBinary/bamboo_logo_landing.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (11, 2, 'Jetty', 'Dummy description', 'http://www.eclipse.org/jetty/documentation/current/images/jetty-header-logo.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (12, 2, 'Chef-Server', 'Dummy description', 'https://www.mysql.com/common/logos/logo-mysql-110x57.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (13, 2, 'Php MyAdmin', 'Dummy description', 'http://www.h-online.com/imgs/43/1/0/2/0/2/1/2/phpMyAdmin_120-e91e013f6db59496.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (14, 2, 'Websphere Liberty', 'Dummy description', 'http://convensysglobal.com/wp-content/uploads/2014/09/ibm-websphere.jpeg');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (16, 4, 'Sonar', 'Dummy description', 'http://www.leadtec.fr/images/logos/sonar-logo.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (18, 5, 'Developer', 'Dummy description', 'http://icons.iconarchive.com/icons/aha-soft/free-large-boss/96/Superman-icon.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (19, 5, 'Tester', 'Dummy description', 'http://icons.iconarchive.com/icons/aha-soft/free-large-boss/96/Captain-icon.png');
         INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (20, 5, 'Manager', 'Dummy description', 'http://icons.iconarchive.com/icons/aha-soft/free-large-boss/96/Manager-icon.png');
         */
//DATABASES
        Category databases = new Category();
        databases.setId("db");
        databases.setName("Databases");

        Blueprint mysql = new Blueprint();
        mysql.setDescription("MySQL Database Blueprint");
        mysql.setId("mysql");
        mysql.setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mysql.png");
        mysql.setName("MySQL");
        blueprintService.save(mysql);

        Blueprint mongo = new Blueprint();
        mongo.setDescription("mongodb Database Blueprint");
        mongo.setId("mongo");
        mongo.setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mongodb.png");
        mongo.setName("MongoDB");
        blueprintService.save(mongo);

        databases.setBlueprints(new ArrayList<>(Arrays.asList(mysql, mongo)));
        categories.put(databases.getId(), databases);

        // TOOLS
        Category tools = new Category();
        tools.setId("tools");
        databases.setName("Tools");

        Blueprint jenkins = new Blueprint();
        jenkins.setDescription("Jenkins CI");
        jenkins.setId("jenkins");
        jenkins.setLogo("http://qvacua.com/media/jenkins-menu-icon.png");
        jenkins.setName("Jenkins");
        blueprintService.save(jenkins);

        Blueprint git = new Blueprint();
        git.setDescription("Git DVSC");
        git.setId("git");
        git.setLogo("https://git-scm.com/images/logo@2x.png");
        git.setName("Git");
        blueprintService.save(git);

        tools.setBlueprints(new ArrayList<>(Arrays.asList(mysql, mongo)));
        categories.put(tools.getId(), tools);
    }
}
