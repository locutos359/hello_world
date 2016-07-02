FROM java
ADD /hello-world-1.0-SNAPSHOT.jar //
ADD /conf/hello-world.yml /conf/
ADD /src/main/resources /src/main/resources
ENTRYPOINT ["java", "-jar", "/hello-world-1.0-SNAPSHOT.jar", "server", "/conf/hello-world.yml"]
