# Webapp

This application is implemented using the Spring Boot v2.0.2.RELEASE.
Maven must be installed in order to build and run the application.

## Build

Run `mvn clean install -DskipTests` in order to build the application.

## Run

Run `mvn spring-boot:run` in order to run the application.

## Notes

The application is divided in 3 layers:
- Persistence layer
- Service layer
- Web layer

Each layer (except for the web)has its own classes for the entities, although they are almost identical.
This may add some !delay in the application, especially while it scales,
but it is necessary for complete abstraction between the layers.

## Use

The application needs the 8080 port to be available.
It loads some predefined data on startup.
The default changesets have ids 1 and 2.

A difference report can be generated
by a GET REST request to the following path:
`/api/change-set/{firstId}/{secondId}`
where firstId is the id of the first dataset and secondId is the id of the second dataset.
If any of the ids does not correspond to a dataset, an exception is raised.
