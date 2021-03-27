# Quarkus-cloud project


## Creating the project
```
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create \
  -DprojectGroupId=org.quarkus.cloud   \
  -DprojectArtifactId=quarkus-cloud    \
  -Dextensions="container-image-jib"
```

### Adding other cloud extensions
```
# Health extensions
./mvnw quarkus:add-extension -Dextensions="smallrye-health"

# Opentracing extensions
./mvnw quarkus:add-extension -Dextensions="smallrye-opentracing"

# Sentry extensions
./mvnw quarkus:add-extension -Dextensions="logging-sentry"

# Metrics extensions
./mvnw quarkus:add-extension -Dextensions="metrics"

# Logging-gelf extensions
./mvnw quarkus:add-extension -Dextensions="logging-gelf"
```

### Building Image
```
./mvnw clean package -Dquarkus.container-image.build=true
```

### Building and Pushing Image
```
./mvnw clean package -Dquarkus.container-image.push=true
```

### Run jaeger containerised
```
docker run -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 jaegertracing/all-in-one:latest
```

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```