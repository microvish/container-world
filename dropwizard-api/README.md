#Dropwizard API

Simple API built using [Dropwizard](https://www.dropwizard.io/). This is not a tutorial on Dropwizard, as the the site itself provides decent documentation. This project provides the basic functionality, which can be used to build more complex applications.

Key highlights: -
- Uses Google Juice for dependency injection.
- Java 8 lambda to abstract common functionality.
- Environment variable substitution during configuration file load.

Exposes the following operations: -
- /api/healthcheck
- /api/hello
- /api/my-secret 

Requirements: -
- [maven](https://maven.apache.org/)
- Java 8

To run: -
- mvn clean install
- export MY_SECRET=${yourSecret}
- java -jar target/dropwizard-api-1.0.0.jar server src/test/resources/configuration-local.yml


##TODO
- Add comments to code.
- Extend documentation to step through what each component is doing.
- Add unit tests.