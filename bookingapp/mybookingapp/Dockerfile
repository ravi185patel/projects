FROM eclipse-temurin:17-jdk
WORKDIR /mybookingapp
COPY target/mybookingapp.jar mybookingapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","mybookingapp.jar"]