# AcoaAPI

Web API that handle requests from ACOA App and ACOA Module and manage the logical and persistence of the data

### Requirements

- Java 7/8
- Maven
- An IDE for development if you want edit the id in a easier way like IntellIj, Eclipse, Netbeans, etc

### Setup and statup

- $ git clone https://github.com/bedrickprokop/acoaapi.git

- $ cd acoaapi

- $ mvn spring-boot:run

### Accessing the application

Open your browser and type: localhost:8080/

### Accessing the embedded database

Open your browser and type: localhost:8080/console

- Saved Settings: Generic H2 (Embedded)
- Settings Name: Generic H2 (Embedded)
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:file:~/h2/acoa_db;DB_CLOSE_ON_EXIT=FALSE
- User Name: root
- Password: root
