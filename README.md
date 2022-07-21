# Java Dronefeeder Spring API

Uma API feita em Java, utilizando Spring, Junit, Docker, MySQL entre outros.
Projeto em grupo de conclusão da aceleração em Java da empresa Wipro.

## Instalação e colocando para rodar
```bash
- Clone o repositório
- rode mvn install
- rode na pasta raiz "docker-compose up"
- API Endpoint: localhost:8080
```

## Estrutura
```
├── src
|     ├── main/dronefeeder
|     |     ├── controller
|     |     |     ├── DeliveryController.java 
|     |     |     ├── DroneController.java
|     |     |     └── VideoController.java
|     |     ├── dto
|     |     |     ├── DeliveryDto.java
|     |     |     ├── DroneDto.java
|     |     ├── model
|     |     |     ├── Delivery.java
|     |     |     ├── Drone.java
|     |     |     └── Video.java
|     |     ├── exception
|     |     |     ├── DataError
|     |     |     ├── DroneControllerAdvisor
|     |     |     ├── DroneNotFoundException
|     |     |     └── UnexpectedErrorException
|     |     ├── repository
|     |     |     ├── DeliveryRepository.java
|     |     |     ├── DroneRepository.java
|     |     |     └── VideoRepository.java
|     |     ├── result
|     |     |     └── VideoGetAllResult.java
|     |     ├── service
|     |     |     ├── DeliveryService.java
|     |     |     ├── DeliveryServiceImpl.java
|     |     |     ├── DroneService.java
|     |     |     ├── DroneServiceImpl.java
|     |     |     ├── VideoService.java
|     |     |     └── VideoServiceImpl.java
|     |     ├── DroneFeederApplication.java
|     ├── tests/dronefeeder
|     |     ├── controller
|     |     |     └── DroneControllerTest
            └── DronefeederApplicationTests.java

```

## API

#### /deliveries
* `GET` : Retorna todos deliveries
* `GET:ID`: Retorna delivery por id
* `PUT`: Atualiza o status de um delivery
* `DELETE`: Deleta um delivery

#### /drones
* `GET` : Retorna todos drones
* `GET:ID`: Retorna drone por id
* `POST`: Cria um drone
* `DELETE:ID`: Deleta um drone

#### /drone/:id/localization
* `PATCH` : Atualiza a localizacao de um drone

#### /drone/:id/deliveries
* `POST` : Adiciona uma nova delivery a um drone
* `GET` : Lista as deliveries de um drone

### /videos
* `GET` : Retorna todos drones utiliznado a classe "VideoGetAllResult", retorna o id da entrega, a data da entrega e o id de cada video.

### /video/:delivery_id
* `POST`: Cria um video e associa a uma entrega

### /video/:video_id
* `GET`: Retorna um video pelo id






## ENTIDADES E RELACIONAMENTOS


![Captura de tela de 2022-07-21 10-13-19](https://user-images.githubusercontent.com/87549369/180222198-a27cbd83-3204-4269-95fe-0a9e38f81f97.png)




