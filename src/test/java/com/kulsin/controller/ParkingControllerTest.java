package com.kulsin.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.kulsin.models.response.ParkingStatus;
import com.kulsin.repository.ParkedVehicleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.kulsin.models.VehicleType.GASOLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ParkedVehicleRepository repository;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        wireMockServer.stop();
    }

    @Test
    public void testParkEndpoint() {
        webTestClient.post()
                .uri("/api/park")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber": "ABC123",
                        "vehicleType": "gasoline",
                        "color": "red",
                        "brand": "Toyota"
                        }""")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.message").isEqualTo("Parked successfully");
    }

    @Test
    public void testUnparkEndpoint() {
        webTestClient.post()
                .uri("/api/park")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber": "ABC123",
                        "vehicleType": "gasoline",
                        "color": "red",
                        "brand": "Toyota"
                        }""")
                .exchange();

        webTestClient.post()
                .uri("/api/unpark")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber":"ABC123"
                        }""")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.message").isEqualTo("Unparked successfully");
    }

    @Test
    public void testUnparkEndpoint_failure() {
        webTestClient.post()
                .uri("/api/unpark")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber":"ABC123"
                        }""")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.message").isEqualTo("EntityNotFoundException - Vehicle ABC123 not found!");
    }

    @Test
    public void testGenerateBill() {
        webTestClient.post()
                .uri("/api/park")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber": "ABC123",
                        "vehicleType": "gasoline",
                        "color": "red",
                        "brand": "Toyota"
                        }""")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.message").isEqualTo("Parked successfully");

        webTestClient.post()
                .uri("/api/generate-bill?plate-number=ABC123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.billAmount").isEqualTo(5);
    }

    @Test
    public void testParkingStatus() {
        webTestClient.post()
                .uri("/api/park")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber": "ABC123",
                        "vehicleType": "gasoline",
                        "color": "red",
                        "brand": "Toyota"
                        }""")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.message").isEqualTo("Parked successfully");

        webTestClient.get()
                .uri("/api/parking-status")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ParkingStatus.class)
                .hasSize(1)
                .consumeWith(entityExchangeResult -> {
                    List<ParkingStatus> parkingStatusList = entityExchangeResult.getResponseBody();
                    Assertions.assertFalse(parkingStatusList.isEmpty());

                    ParkingStatus parkingStatus = parkingStatusList.get(0);
                    assertEquals("GASOLINE_slot_1", parkingStatus.getSlotNumber());
                    assertEquals("Toyota", parkingStatus.getBrand());
                    assertEquals("red", parkingStatus.getColor());
                    assertEquals("ABC123", parkingStatus.getPlateNumber());
                    assertEquals(GASOLINE, parkingStatus.getVehicleType());
                });

    }

    @Test
    public void testSearchVehicle() {
        webTestClient.post()
                .uri("/api/park")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber": "ABC123",
                        "vehicleType": "gasoline",
                        "color": "red",
                        "brand": "Toyota"
                        }""")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.message").isEqualTo("Parked successfully");

        webTestClient.get()
                .uri("/api/search-vehicle?plate-number=ABC123")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ParkingStatus.class)
                .hasSize(1)
                .consumeWith(entityExchangeResult -> {
                    List<ParkingStatus> parkingStatusList = entityExchangeResult.getResponseBody();
                    Assertions.assertFalse(parkingStatusList.isEmpty());

                    ParkingStatus parkingStatus = parkingStatusList.get(0);
                    assertEquals("GASOLINE_slot_1", parkingStatus.getSlotNumber());
                    assertEquals("Toyota", parkingStatus.getBrand());
                    assertEquals("red", parkingStatus.getColor());
                    assertEquals("ABC123", parkingStatus.getPlateNumber());
                    assertEquals(GASOLINE, parkingStatus.getVehicleType());
                });
    }

    @Test
    public void testSearchByParkingSlot() {
        webTestClient.post()
                .uri("/api/park")
                .contentType(APPLICATION_JSON)
                .bodyValue("""
                        {
                        "plateNumber": "ABC123",
                        "vehicleType": "gasoline",
                        "color": "red",
                        "brand": "Toyota"
                        }""")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.message").isEqualTo("Parked successfully");

        webTestClient.get()
                .uri("/api/parking-slot/GASOLINE_slot_1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ParkingStatus.class)
                .hasSize(1)
                .consumeWith(entityExchangeResult -> {
                    List<ParkingStatus> parkingStatusList = entityExchangeResult.getResponseBody();
                    Assertions.assertFalse(parkingStatusList.isEmpty());

                    ParkingStatus parkingStatus = parkingStatusList.get(0);
                    assertEquals("GASOLINE_slot_1", parkingStatus.getSlotNumber());
                    assertEquals("Toyota", parkingStatus.getBrand());
                    assertEquals("red", parkingStatus.getColor());
                    assertEquals("ABC123", parkingStatus.getPlateNumber());
                    assertEquals(GASOLINE, parkingStatus.getVehicleType());
                });
    }

}
