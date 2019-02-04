/**
* Simple CRUD Application using Spring Boot + H2 Database
* Author : palash.debnath5@gmail.com
* Position: Software Developer - Full Stack
* Purpose : Learn Usage of H2 Database
*
*/

Spring Boot + H2 Database Configuration. Why, what and how H2 database is useful?

What is H2 database?
	H2 is a very popular in memory database which is written in Java. H2 is a relational database management system which can be used with java application very easily. It supports SQL. Beside these H2 provides a web console to maintain database

Why it needs?
	Regular database like MySQL, PostgreSQL, MS SQL etc. need a good configuration for startup with them like you need 
1,	Download the database and install it in your pc
2.	Setup database schema, tables, data 
3.	Then need to config with the application. Need to do coding as well
Though it required for a developer to learn regular databases and we also do it. But sometime we need to do a quick POC. Now if we want to do it using traditional database then need to do lots of work. Another scenario is Testing purpose its really helpful like when multiple developer do test in same project then data changes may occur some issue. In that case H2 database is a great solution as its start when applications start and again get destroyed when application stop

How to use with Spring?
	Spring Boot provides very simple configuration to switch between a real database and a in memory database like H2. It needs zero configuration, zero maintenance.

To use H2 database in  Spring Boot project just have to add a dependency in you boot pom.xml file
	<dependency>
		<groupId>com.h2database</groupId>
		<artifactId>h2</artifactId>
		<scope>runtime</scope>
</dependency>

H2 provide a web interface called H2 console to see the data. 
Now enable H2 console in the application.properties file located inside /src/main/resources/application.properties
spring.h2.console.enabled = true

If you don’t provide a database connection details still Spring Boot will provide a auto configuration for a H2 database if it gets h2 jar dependency in project’s classpath. When you will start you project then Spring Boot will create a database with tables using your DTO/Entity/Model class. You can insert some data as well in specific tables by adding a file name data.sql inside /src/main/resources/data.sql  data is will be regular sql like bellow
insert into table_name(colnameA,colnameB,colnameC) values(‘A’,’B’,’C’)
when you will reload you project then spring will import this data. You can see data from h2-console like http://localhost:8080/h2-console

Spring use bellow configuration to use h2 database automatically

	spring.datasource.url = jdbc:h2:mem:testdb
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.username = sa				(sa = server administraton)
	spring.datasource.password = 

spring.jpa.database-platform = org.hibernate.dialect.H2Dialect



Find a Sample project of CRUD where I used H2database. 
https://github.com/Palash-it/spring-boot-h2-database-crud
