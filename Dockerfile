FROM maven:3-jdk-11
WORKDIR /drone-feeder
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run