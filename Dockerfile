FROM arm32v7/openjdk:11
ADD target/danielek.jar danielek.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "danielek.jar"]