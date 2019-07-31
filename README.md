---
page_type: sample
description: This sample shows how to use Spring Data JPA module with different Azure Database services.
languages:
- java
products:
- azure
urlFragment: spring-jpa-azure
---

# Spring Data JPA on Azure

This sample shows how to use Spring Data JPA module with different Azure Database services.

## TOC
- [Prerequisite](#prerequisite)
- [Build and Package](#build-and-package)
- [Run](#run)

## Prerequisites

- Azure Account
- JDK 1.8 or above
- Maven 3.0 or above
- Curl
- Database Client
    - MySQL CLI
    - pgAdmin for PostgreSQL

## Build and Package

You can build this sample with different Azure Database services.
`SQL Server`, `MySQL` and `PostgreSQL` are supported by Azure.
Follow below sections to use one of them.

- [Azure Database for MySQL](#azure-database-for-mysql)
- [Azure Database for PostgreSQL](#azure-database-for-postgresql)
- [Azure SQL Database](#azure-sql-database)

### Azure Database for MySQL

1. Create an Azure Database for MySQL server by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/mysql/quickstart-create-mysql-server-database-using-azure-portal#create-an-azure-database-for-mysql-server).

1. Configure a firewall rule to allow your machine accessing the created MySQL server by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/mysql/quickstart-create-mysql-server-database-using-azure-portal#configure-a-server-level-firewall-rule).

1. Use `mysql` to connect to your MySQL server and create a database named `mysqldb` by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/mysql/quickstart-create-mysql-server-database-using-azure-portal#connect-to-mysql-by-using-the-mysql-command-line-tool).

1. Find `application.properties` at `src/main/resources` directory and fill in below properties.

    ```
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

    spring.datasource.url=<replace with your database server URL>
    spring.datasource.username=<replace with your database server username>
    spring.datasource.password=<replace with your database server password>
    ```
    
    >**NOTE**
    > `spring.datasource.url` should be in the form of `jdbc:mysql://<MySQL Host>:3306/mysqldb?useSSL=true&requireSSL=false`

1. Package the sample application by running below command.

    ```shell
    mvn package -P mysql
    ```

### Azure Database for PostgreSQL

1. Create an Azure Database for PostgreSQL server by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/postgresql/quickstart-create-server-database-portal#create-an-azure-database-for-postgresql-server).

1. Configure a firewall rule to allow your machine accessing the created PostgreSQL server by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/postgresql/quickstart-create-server-database-portal#configure-a-server-level-firewall-rule).

1. Use `pgAdmin` to connect to your PostgreSQL server and create a database named `mypgsqldb` by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/postgresql/quickstart-create-server-database-portal#connect-to-the-postgresql-server-using-pgadmin).

1. Find `application.properties` at `src/main/resources` directory and fill in below properties.

    ```
    spring.datasource.url=<replace with your database server URL>
    spring.datasource.username=<replace with your database server username>
    spring.datasource.password=<replace with your database server password>
    ```

    >**NOTE**
    > `spring.datasource.url` should be in the form of `jdbc:postgresql://<PostgreSQL Host>:5432/mypgsqldb?ssl=true&sslmode=prefer`

1. Package the sample application by running below command.

    ```shell
    mvn package -P postgresql
    ```

### Azure SQL Database

1. Create an Azure SQL Database by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/sql-database/sql-database-get-started-portal).

1. Create a firewall rule to allow your machine accessing the created Azure SQL Database by following tutorial at 
[here](https://docs.microsoft.com/en-us/azure/sql-database/sql-database-get-started-portal-firewall).

1. Find `application.properties` at `src/main/resources` directory and fill in below properties.

    ```
    spring.datasource.url=<replace with your database server URL>
    spring.datasource.username=<replace with your database server username>
    spring.datasource.password=<replace with your database server password>
    ```

    >**NOTE**
    > `spring.datasource.url` should be in the form of `jdbc:sqlserver://{SQL Host}:1433;database=sqldb;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;`

1. Package the sample application by running below command.

    ```shell
    mvn clean package -P sql
    ```


## Run

Following below steps to run and test the sample application.

1. Run application.

    ```shell
    java -jar target/spring-data-jpa-on-azure-0.1.0-SNAPSHOT.jar
    ```

1. Create new users by running below command.

    ```shell
    curl -s -d '{"name":"Tom","species":"cat"}' -H "Content-Type: application/json" -X POST http://localhost:8080/pets
    curl -s -d '{"name":"Jerry","species":"mouse"}' -H "Content-Type: application/json" -X POST http://localhost:8080/pets
    ```
    
    Sample output is as below.
    ```text
    Added Pet(id=1, name=Tom, species=cat).
    ...
    Added Pet(id=2, name=Jerry, species=mouse).
    ```

1. Get all existing pets by running below command.

    ```shell
    curl -s http://localhost:8080/pets
    ```
    
    Sample output is as below.
    ```txt
    [{"id":1,"name":"Tom","species":"cat"},{"id":2,"name":"Jerry","species":"mouse"}]
    ```
