FROM java:8
EXPOSE 7777
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "/etc/colon/colon-testerka-jar-with-dependencies.jar", "/etc/colon/application.properties"]