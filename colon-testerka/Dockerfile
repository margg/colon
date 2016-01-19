FROM java:8
EXPOSE 7777

COPY colon-testerka-jar-with-dependencies.jar /opt/colon-testerka.jar
COPY application.properties /opt/application.properties

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "/opt/colon-testerka.jar", "/opt/application.properties"]
