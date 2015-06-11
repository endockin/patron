Install MySQL and run the scripts to populate your database: 
- DB_SCHEMA.sql 
- DB_DATA.sql.
Build the application: mvn clean install
Deploy the application in Tomcat.

Login service:
Use utils/testLogin.html to test the login service

Show all categories service:
http://172.16.116.209:8080/patron/service/category/all?callback=test

Where:
- IP of the machine where Tomcat is installed
- 8080 Tomcat port
