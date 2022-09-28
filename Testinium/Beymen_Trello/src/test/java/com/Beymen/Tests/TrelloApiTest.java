package com.Beymen.Tests;

import com.Beymen.Utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class TrelloApiTest {

    static Properties properties;

    @BeforeAll
    public static void setup() {
        properties = ConfigurationReader.initializeProperties();

        RestAssured.baseURI = properties.getProperty("baseURI");
        RestAssured.basePath = properties.getProperty("basePath");
    }

    @Test
    public void apiTest() {

        // Create a board on Trello.
        String boardId = given()
                .contentType("application/json")
                .when()
                .queryParam("key", properties.getProperty("key"))
                .queryParam("token", properties.getProperty("token"))
                .queryParam("name", "Trello My Board")
                .post("/boards")
                .then()
                .statusCode(200)
                .extract().path("id");

        String listId = given()
                .contentType("application/json")
                .when()
                .queryParam("key", properties.getProperty("key"))
                .queryParam("token", properties.getProperty("token"))
                .queryParam("name", "New List")
                .post("/boards/" + boardId + "/lists")
                .then()
                .statusCode(200)
                .extract().path("id");

        //Create two cards on the board you created.
        String[] cardsArr = new String[2];
        for (int i = 0; i < 2; i++) {
            cardsArr[i] = given()
                    .contentType("application/json")
                    .when()
                    .queryParam("key", properties.getProperty("key"))
                    .queryParam("token", properties.getProperty("token"))
                    .queryParam("name", "Card Title" + i)
                    .queryParam("idList", listId)
                    .post("/cards")
                    .then()
                    .statusCode(200)
                    .extract().path("id");
        }

        //Update one of these two cards randomly..
        Random random = new Random();
        int temp = random.nextInt(2);
        given()
                .contentType("application/json")
                .when()
                .queryParam("key", properties.getProperty("key"))
                .queryParam("token", properties.getProperty("token"))
                .queryParam("name", "New Card Title")
                .queryParam("desc", "Card description edited.")
                .put("/cards/" + cardsArr[temp])
                .then()
                .statusCode(200)
                .extract().path("id");

        //Delete the cards you have created..
        for (int i = 0; i < 2; i++) {
            given()
                    .contentType("application/json")
                    .when()
                    .queryParam("key", properties.getProperty("key"))
                    .queryParam("token", properties.getProperty("token"))
                    .delete("/cards/" + cardsArr[i])
                    .then()
                    .statusCode(200);
        }

        //Delete the board you created.
        given()
                .contentType("application/json")
                .when()
                .queryParam("key", properties.getProperty("key"))
                .queryParam("token", properties.getProperty("token"))
                .pathParam("id", boardId)
                .delete("/boards/{id}")
                .then()
                .statusCode(200);

    }

}