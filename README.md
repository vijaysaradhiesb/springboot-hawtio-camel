#Spring Boot Camel Sample Application

This a sample Spring Boot application that includes an Apache Camel (http://camel.apache.org/) route and Hawtio (http://hawt.io/).

##Basic How-to Requires Java 8.

###Maven

Make sure you have Maven installed
In your console, under the project directory, run: mvn clean package
Then run: java -jar target/spring-boot-camel-sample-{version}.jar
###Using IntelliJ:

Right click SpringBoot Application
Select Run as -> Java application
###Hawtio

Once the application has started up successfully, in any web browser, go to localhost:8080/hawtio/index.html and select the Camel tab. You should see your Camel Route running.