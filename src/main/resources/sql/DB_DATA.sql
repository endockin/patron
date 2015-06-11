INSERT INTO users (userid, username, password, firstname, lastname, email, type) VALUES (1, 'admin', 'admin', 'Endava', 'ITS', 'its@endava.com', 0);
INSERT INTO users (userid, username, password, firstname, lastname, email, type) VALUES (2, 'john', 'doe', 'John', 'Doe', 'john.doe@visa.eu', 1);


INSERT INTO categories (categoryid, categoryname) VALUES (1, 'Databases');
INSERT INTO categories (categoryid, categoryname) VALUES (2, 'Application Servers');
INSERT INTO categories (categoryid, categoryname) VALUES (3, 'Operating systems');
INSERT INTO categories (categoryid, categoryname) VALUES (4, 'Trainings');
INSERT INTO categories (categoryid, categoryname) VALUES (5, 'Employee role');

INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (1, 1, 'MySQL', 'Dummy description', 'https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mysql.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (2, 1, 'MongoDB', 'Dummy description', 'https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_mongodb.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (3, 1, 'Postgres', 'Dummy description', 'http://media.tumblr.com/290f58218f6c7edc49ee60bbaf924156/tumblr_inline_moaefiNoSY1qz4rgp.jpg');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (4, 3, 'Ubuntu', 'Dummy description', 'https://d207aa93qlcgug.cloudfront.net/1.88.1/img/explore_repos/official_ubuntu-h.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (5, 3, 'Fedora', 'Dummy description', 'https://www.mysql.com/common/logos/logo-mysql-110x57.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (6, 3, 'Debian', 'Dummy description', 'https://www.mysql.com/common/logos/logo-mysql-110x57.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (7, 3, 'CentOS', 'Dummy description', 'https://www.mysql.com/common/logos/logo-mysql-110x57.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (8, 2, 'Django', 'Dummy description', 'http://www.howopensource.com/wp-content/uploads/2011/06/django-logo.jpg');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (9, 2, 'Tomcat', 'Dummy description', 'http://tomcat.apache.org/images/tomcat.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (10, 2, 'Bamboo server', 'Dummy description', 'https://www.atlassian.com/wac/software/bamboo/productLogo/imageBinary/bamboo_logo_landing.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (11, 2, 'Jetty', 'Dummy description', 'http://www.eclipse.org/jetty/documentation/current/images/jetty-header-logo.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (12, 2, 'Chef-Server', 'Dummy description', 'https://www.mysql.com/common/logos/logo-mysql-110x57.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (13, 2, 'Php MyAdmin', 'Dummy description', 'http://www.h-online.com/imgs/43/1/0/2/0/2/1/2/phpMyAdmin_120-e91e013f6db59496.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (14, 2, 'Websphere Liberty', 'Dummy description', 'http://convensysglobal.com/wp-content/uploads/2014/09/ibm-websphere.jpeg');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (15, 4, 'Jenkins', 'Dummy description', 'http://qvacua.com/media/jenkins-menu-icon.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (16, 4, 'Sonar', 'Dummy description', 'http://www.leadtec.fr/images/logos/sonar-logo.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (17, 4, 'GIT', 'Dummy description', 'https://git-scm.com/images/logo@2x.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (18, 5, 'Developer', 'Dummy description', 'http://icons.iconarchive.com/icons/aha-soft/free-large-boss/96/Superman-icon.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (19, 5, 'Tester', 'Dummy description', 'http://icons.iconarchive.com/icons/aha-soft/free-large-boss/96/Captain-icon.png');
INSERT INTO images (imageid, categoryid, imagename, imagedescription, imageLogo) VALUES (20, 5, 'Manager', 'Dummy description', 'http://icons.iconarchive.com/icons/aha-soft/free-large-boss/96/Manager-icon.png');
