package com.endockin.patron.service;

import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.endockin.patron.config.TestConfiguration;
import com.endockin.patron.model.Blueprint;
import com.endockin.patron.model.Category;
import com.endockin.patron.service.category.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@WebAppConfiguration
@Transactional
public class PopulateDBTest {

  @Autowired
  private CategoryService categoryService;

  @Test
  @Rollback(false)
  @Ignore
  public void createData() throws Exception {
    Category operatingSystems = new Category();
    operatingSystems.setKey("os");
    operatingSystems.setName("Operating Systems");

    Blueprint ubuntu = new Blueprint();
    ubuntu
        .setDescription("Ubuntu is an open source software platform that runs everywhere from the smartphone, the tablet and the PC to the server and the cloud.");
    ubuntu.setImageName("ubuntu");
    ubuntu
        .setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_ubuntu-h.png");
    ubuntu.setName("Ubuntu");

    Blueprint debian = new Blueprint();
    debian
        .setDescription("Debian is a free operating system (OS) for your computer. An operating system is the set of basic programs and utilities that make your computer run.");
    debian.setImageName("debian");
    debian.setLogo("http://upload.wikimedia.org/wikipedia/commons/0/04/Debian_logo.png");
    debian.setName("Debian");

    operatingSystems.setBlueprints(new ArrayList<>(Arrays.asList(ubuntu, debian)));
    categoryService.save(operatingSystems);

    // DATABASES
    Category databases = new Category();
    databases.setKey("db");
    databases.setName("Databases");

    Blueprint mysql = new Blueprint();
    mysql
        .setDescription("MySQL is the world's second most widely used relational database management system and most widely used open-source RDBMS. ");
    mysql.setImageName("mysql");
    mysql
        .setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mysql.png");
    mysql.setName("MySQL");

    Blueprint mongo = new Blueprint();
    mongo
        .setDescription("MongoDB (from \"humongous\") is an open-source document database, and the leading NoSQL database.");
    mongo.setImageName("mongo");
    mongo
        .setLogo("https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mongodb.png");
    mongo.setName("MongoDB");

    databases.setBlueprints(new ArrayList<>(Arrays.asList(mysql, mongo)));
    categoryService.save(databases);

    // TOOLS
    Category tools = new Category();
    tools.setKey("tools");
    tools.setName("Tools");

    Blueprint jenkins = new Blueprint();
    jenkins
        .setDescription("Jenkins is an award-winning application that monitors executions of repeated jobs, such as building a software project or jobs run by cron.");
    jenkins.setImageName("jenkins");
    jenkins.setLogo("http://qvacua.com/media/jenkins-menu-icon.png");
    jenkins.setName("Jenkins");
    jenkins.setPorts("8080");

    Blueprint git = new Blueprint();
    git.setDescription("Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency.");
    git.setImageName("git");
    git.setLogo("https://git-scm.com/images/logo@2x.png");
    git.setName("Git");

    Blueprint httpd = new Blueprint();
    httpd
        .setDescription("The Apache HTTP Server Project is an effort to develop and maintain an open-source HTTP server for modern operating systems including UNIX and Windows NT. The goal of this project is to provide a secure, efficient and extensible server that provides HTTP services in sync with the current HTTP standards.");
    httpd.setImageName("httpd");
    httpd.setLogo("http://www.host-resource.com/glossary/apache-http-server.jpg");
    httpd.setName("Apache HTTP Server");
    httpd.setPorts("80");

    tools.setBlueprints(new ArrayList<>(Arrays.asList(jenkins, git, httpd)));
    categoryService.save(tools);
  }
}
