FROM openjdk:17
EXPOSE 8080
ADD target/rentacar.jar rentacar.jar
ENTRYPOINT ["java","-jar","/rentacar.jar"]
