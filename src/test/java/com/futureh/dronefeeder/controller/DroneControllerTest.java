package com.futureh.dronefeeder.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futureh.dronefeeder.model.Delivery;
import com.futureh.dronefeeder.model.Drone;
import com.futureh.dronefeeder.repository.DroneRepository;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DroneControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private DroneRepository repository;

  @Captor
  private ArgumentCaptor<Drone> droneCaptor;

  @BeforeEach
  public void setup() {
    repository.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("1 -  Deve adicionar um drone na base de dados.")
  void deveAdicionarDroneNaBaseDeDados() throws Exception {
    final var drone = new Drone("tantosGrausNorte", "tantosGrausLeste");
    mockMvc
        .perform(post("/drones").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    verify(repository, atLeast(1)).save(droneCaptor.capture());

    assertThat(droneCaptor.getValue()).isNotNull();
    assertThat(droneCaptor.getValue().getId()).isNotNull();
    assertThat(droneCaptor.getValue().getLatitude()).isEqualTo(drone.getLatitude());
    assertThat(droneCaptor.getValue().getLongitude()).isEqualTo(drone.getLongitude());
    assertThat(droneCaptor.getValue().getDeliveries()).isEmpty();
  }

  @Test
  @Order(2)
  @DisplayName("2 - Deve retornar todas os drones existentes da base de dados.")
  void deveRetornarTodasDronesExistentesNaBase() throws Exception {
    final var drone1 = new Drone("tantosGrausNorte", "tantosGrausLeste");
    final var drone2 = new Drone("tantosGrausSul", "tantosGrausOeste");
    repository.save(drone1);
    repository.save(drone2);

    mockMvc.perform(get("/drones").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].latitude").value(drone1.getLatitude()))
        .andExpect(jsonPath("$[0].longitude").value(drone1.getLongitude()))
        .andExpect(jsonPath("$[1].latitude").value(drone2.getLatitude()))
        .andExpect(jsonPath("$[1].longitude").value(drone2.getLongitude()));
  }

  @Test
  @Order(3)
  @DisplayName("3 - Deve retornar lista vazia quando não existir drones na base de dados.")
  void deveRetornarListaVaziaQuandoNaoExistirDronesNaBase() throws Exception {
    mockMvc.perform(get("/drones").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(containsString("[]")));
  }

  @Test
  @Order(4)
  @DisplayName("4 - Deve remover drone, por um id existente informado.")
  void deveRemoverDroneQuandoExistirNaBase() throws Exception {
    final var drone = repository.save(new Drone("tantosGrausNorte", "tantosGrausLeste"));;

    mockMvc.perform(delete("/drones/" + drone.getId())).andExpect(status().isOk());
  }

  @Test
  @Order(5)
  @DisplayName("5 - Utilizar @ControllerAdvice em controller para emitir 'não encontrado' quando"
      + "tenta remover um drone não existente na base de dados.")

  void deveEmitirNaoEncontradoQuandoDroneNaoExistirNaBase() throws Exception {
    mockMvc.perform(delete("/drones/" + new Random().nextInt())).andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Drone not found"));
  }

  @Test
  @Order(6)
  @DisplayName("6 - Deve adicionar entrega quando existir drone na base de dados.")
  void deveAdicionarDeliveryQuandoExistirDroneNaBaseDeDados() throws Exception {
    final var drone = repository.save(new Drone("tantosGrausNorte", "tantosGrausLeste"));
    final var delivery = new Delivery();

    mockMvc
        .perform(patch("/drones/" + drone.getId() + "/deliveries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(delivery)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$.latitude").value(drone.getLatitude()))
        .andExpect(jsonPath("$.longitude").value(drone.getLongitude()));

    verify(repository, atLeast(1)).save(droneCaptor.capture());

    assertThat(droneCaptor.getValue()).isNotNull();
    assertThat(droneCaptor.getValue().getId()).isNotNull();
    assertThat(droneCaptor.getValue().getLatitude()).isEqualTo(drone.getLatitude());
    assertThat(droneCaptor.getValue().getLongitude()).isEqualTo(drone.getLongitude());
    assertThat(droneCaptor.getValue().getDeliveries()).hasSize(1);
  }

  @Test
  @Order(7)
  @DisplayName("7 - Utilizar @ControllerAdvice em controller para emitir 'não encontrado' quando"
      + "tenta adicionar uma entrega em um drone não existente na base de dados.")

  void deveEmitirNaoEncontradoQuandoNaoExistirDroneNaBaseDeDados() throws Exception {
    final var delivery = new Delivery();

    mockMvc
        .perform(patch("/drones/" + new Random().nextInt() + "/deliveries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(delivery)))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  @Order(8)
  @DisplayName("8 - Deve retornar lista de entregas quando existir na base.")
  void deveRetornarListaDeDeliveriesQuandoExistirNaBase() throws Exception {
    var drone = new Drone("tantosGrausNorte", "tantosGrausLeste");
    drone = repository.save(drone);
    final var delivery1 = new Delivery(null, LocalDateTime.now().toString(), "PENDENTE", drone);
    final var delivery2 = new Delivery(null, LocalDateTime.now().toString(), "EM PREPARO", drone);

    drone.addDelivery(delivery1);
    drone.addDelivery(delivery2);

    drone = repository.save(drone);

    mockMvc
        .perform(
            get("/drones/" + drone.getId() + "/deliveries").contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].status").value(delivery1.getStatus()))
        .andExpect(jsonPath("$[1].status").value(delivery2.getStatus()));
  }

  @Test
  @Order(9)
  @DisplayName("9 - Utilizar o conceito de Circuit Breaker ao tratamento de exceções.")
  void deveRetornarServicoIndisponivelCasoExtrapoleTaxaDeErros() throws Exception {
    final var delivery = new Delivery();

    mockMvc.perform(patch("/drones/" + new Random().nextInt() + "/deliveries")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(delivery)));

    mockMvc
        .perform(patch("/drones/" + new Random().nextInt() + "/deliveries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(delivery)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isServiceUnavailable());
  }
}
