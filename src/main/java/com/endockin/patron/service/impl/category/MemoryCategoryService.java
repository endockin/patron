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
        ubuntu.setDescription("Ubuntu is an open source software platform that runs everywhere from the smartphone, the tablet and the PC to the server and the cloud.");
        ubuntu.setId("ubuntu");
        ubuntu.setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_ubuntu-h.png");
        ubuntu.setName("Ubuntu");
        blueprintService.save(ubuntu);

        Blueprint debian = new Blueprint();
        debian.setDescription("Debian is a free operating system (OS) for your computer. An operating system is the set of basic programs and utilities that make your computer run.");
        debian.setId("debian");
        debian.setLogo("http://upload.wikimedia.org/wikipedia/commons/0/04/Debian_logo.png");
        debian.setName("Debian");
        blueprintService.save(debian);

        operatingSystems.setBlueprints(new ArrayList<>(Arrays.asList(ubuntu, debian)));
        categories.put(operatingSystems.getId(), operatingSystems);

        //DATABASES
        Category databases = new Category();
        databases.setId("db");
        databases.setName("Databases");

        Blueprint mysql = new Blueprint();
        mysql.setDescription("MySQL is the world's second most widely used relational database management system and most widely used open-source RDBMS. ");
        mysql.setId("mysql");
        mysql.setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mysql.png");
        mysql.setName("MySQL");
        blueprintService.save(mysql);

        Blueprint mongo = new Blueprint();
        mongo.setDescription("MongoDB (from \"humongous\") is an open-source document database, and the leading NoSQL database.");
        mongo.setId("mongo");
        mongo.setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mongodb.png");
        mongo.setName("MongoDB");
        blueprintService.save(mongo);

        databases.setBlueprints(new ArrayList<>(Arrays.asList(mysql, mongo)));
        categories.put(databases.getId(), databases);

        // TOOLS
        Category tools = new Category();
        tools.setId("tools");
        tools.setName("Tools");

        Blueprint jenkins = new Blueprint();
        jenkins.setDescription("Jenkins is an award-winning application that monitors executions of repeated jobs, such as building a software project or jobs run by cron.");
        jenkins.setId("jenkins");
        jenkins.setLogo("http://qvacua.com/media/jenkins-menu-icon.png");
        jenkins.setName("Jenkins");
        jenkins.setPorts(new ArrayList<>(Arrays.asList(8080)));
        blueprintService.save(jenkins);

        Blueprint git = new Blueprint();
        git.setDescription("Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency.");
        git.setId("git");
        git.setLogo("https://git-scm.com/images/logo@2x.png");
        git.setName("Git");
        blueprintService.save(git);

        Blueprint httpd = new Blueprint();
        httpd.setDescription("The Apache HTTP Server Project is an effort to develop and maintain an open-source HTTP server for modern operating systems including UNIX and Windows NT. The goal of this project is to provide a secure, efficient and extensible server that provides HTTP services in sync with the current HTTP standards.");
        httpd.setId("httpd");
        httpd.setLogo("http://www.host-resource.com/glossary/apache-http-server.jpg");
        httpd.setName("Apache HTTP Server");
        httpd.setPorts(new ArrayList<>(Arrays.asList(80)));
        blueprintService.save(httpd);

        tools.setBlueprints(new ArrayList<>(Arrays.asList(jenkins, git, httpd)));
        categories.put(tools.getId(), tools);
    }
}
