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
|     └── tests/dronefeeder
|     |     └── DronefeederApplicationTests.java

```

## API

#### /delivery
* `GET` : Retorna todos deliveries
* `GET:ID`: Retorna delivery por id
* `POST`: Cria um delivery
* `PUT`: Atualiza um delivery
* `DELETE`: Deleta um delivery

#### /drone
* `GET` : Retorna todos drones
* `GET:ID`: Retorna drone por id
* `POST`: Cria um drone
* `DELETE:ID`: Deleta um drone

#### /drone/:id/localization
* `PATCH` : Atualiza a localizacao de um drone

### /video
* `GET` : Retorna todos drones utiliznado a classe "VideoGetAllResult", retorna o id da entrega, a data da entrega e o id de cada video.

#### /drone/:id/deliveries
* `PATCH` : Adiciona uma delivery ao drone
* `GET` : Retorna todos deliveries de um drone

### /video/:delivery_id
* `POST`: Cria um video e associa a uma entrega

### /video/:video_id
* `GET`: Retorna um video pelo id




