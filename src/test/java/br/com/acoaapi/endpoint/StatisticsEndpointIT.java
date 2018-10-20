package br.com.acoaapi.endpoint;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StatisticsEndpointIT {

    @Test
    public void findAll() {
        given().when().get("api/statistics").then().statusCode(200);
    }
}
