FROM java:8

# Install maven
RUN apt-get update && apt-get install -y maven

WORKDIR /workdir

# Prepare by downloading dependencies
ADD . /workdir
RUN cd /workdir/colon-testerka && mvn package -DskipTests

EXPOSE 7777
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "/workdir/colon-testerka/target/colon-testerka-jar-with-dependencies.jar"]