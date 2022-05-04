FROM java:8
EXPOSE 8080
ADD target/BookAPI.jar BookAPI.jar
ENTRYPOINT ["java","-jar","BookAPI.jar"]