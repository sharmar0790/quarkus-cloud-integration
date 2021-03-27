# Quarkus-cloud project


## Creating the project
```
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create \
  -DprojectGroupId=org.quarkus.cloud   \
  -DprojectArtifactId=quarkus-cloud    \
  -Dextensions="container-image-jib,smallrye-health"
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

### Running the application in kubernetes
```
$ kubectl apply -f target/kubernetes/kubernetes.yml
```

### Running the health check
Importing the smallrye-health extension directly exposes three REST endpoints:
* _/q/health/live_ - The application is up and running.
* _/q/health/ready_ - The application is ready to serve requests.
* _/q/health_ - Accumulating all health check procedures in the application.
* _http://localhost:8080/q/health-ui/_ - health-ui allows you to see your Health Checks in a Web GUI.

### Docker image settings
```
quarkus.container-image.name=quarkus-cloud
#optional, defaults to the application version
quarkus.container-image.tag=1.0
quarkus.container-image.registry=registry.hub.docker.com
quarkus.container-image.insecure=true
quarkus.container-image.username=********
quarkus.container-image.password=********
```

### Quarkus Kubernetes configuration
```
quarkus.kubernetes.namespace=quarkus-cloud
quarkus.kubernetes.service-type=NodePort
quarkus.kubernetes.replicas=2

#Labels
#quarkus.kubernetes.part-of=quarkus-cloud
#quarkus.kubernetes.name=quarkus-cloud
#quarkus.kubernetes.version=1.0-rc.1

# annotations
#quarkus.kubernetes.annotations.name=quarkus-cloud
#quarkus.kubernetes.annotations."app.quarkus/id"=42

#Container Resources Management
#quarkus.kubernetes.resources.requests.memory=64Mi
#quarkus.kubernetes.resources.requests.cpu=250m
#quarkus.kubernetes.resources.limits.memory=512Mi
#quarkus.kubernetes.resources.limits.cpu=1000m

#Env Key value
#quarkus.kubernetes.env.mapping.foo.from-configmap=my-configmap
#quarkus.kubernetes.env.mapping.foo.with-key=keyName
```

> For more on quarkus-kubernetes, visit [here](https://quarkus.io/guides/deploying-to-kubernetes)

### Opentracing configuration
```
quarkus.jaeger.service-name=quarkus-cloud-service 
quarkus.jaeger.sampler-type=const
quarkus.jaeger.sampler-param=1
quarkus.log.console.format=%d{HH:mm:ss} %-5p traceId=%X{traceId}, parentId=%X{parentId}, spanId=%X{spanId}, sampled=%X{sampled} [%c{2.}] (%t) %s%e%n
quarkus.jaeger.endpoint=http://localhost:14268/api/traces
```  

### Gelf Configuration
```
quarkus.log.handler.gelf.enabled=true
quarkus.log.handler.gelf.host=localhost
quarkus.log.handler.gelf.port=12201
```

### syslog
```
quarkus.log.syslog.enable=true
quarkus.log.syslog.endpoint=localhost:5140
quarkus.log.syslog.protocol=udp
quarkus.log.syslog.app-name=quarkus
quarkus.log.syslog.hostname=quarkus-test
```

#### UI Path
* GrayLog UI - _**http://localhost:9000**_
* ELK / EFK / Syslog UI - _**http://localhost:5601/**_