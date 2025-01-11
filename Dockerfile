# use an official Maven image to build the spring boot app
FROM maven:3.8.4-openjdk-17 AS build

#Set the working directory
WORKDIR /app

#copy the pom.sml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

#copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests


#use an official openJDK image to run the application
FROM openjdk:17-jdk-slim

#set the working directory
WORKDIR /app

#copy the built JAR file from the built stage
COPY --from=build /app/target/spt-backend-0.0.1-SNAPSHOT.jar .


#expose port 8081
EXPOSE 8081

#Specify the command to run the application
ENTRYPOINT ["java","-jar","/app/spt-backend-0.0.1-SNAPSHOT.jar"]
