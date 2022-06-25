package lab16;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TrelloCRUDTest {
    private final String API_KEY = "classified";
    private final String API_TOKEN = "classified";
    private final String ORGANIZATION_ID = "e90b0487f91148400a38394ee2efb92f";
    private String boardName;
    private String boardID;

    @BeforeTest
    void init() {
        boardName = UUID.randomUUID().toString();
    }

    // Create board
    @Test(priority = 1)
    void createTest() {
        Response response = given()
                .when()
                .contentType("application/json")
                .body(String.format("{\"idOrganization\":\"%s\"}", ORGANIZATION_ID))
                .post(String.format("https://api.trello.com/1/boards/?name=%s&key=%s&token=%s", boardName, API_KEY, API_TOKEN));

        response
                .then()
                .assertThat()
                .statusCode(200);

        boardID = response.getBody().jsonPath().get("id");
    }
    // Update board
    @Test(priority = 3)
    void updateBoard() {
        if (boardID == null) {
            Assert.fail("Board id is null, make sure you ran first test");
        }

        given()
                .when()
                .queryParam("name", "Changed Board Name")
                .pathParam("id", boardID)
                .put(String.format("https://api.trello.com/1/boards/{id}?key=%s&token=%s", API_KEY, API_TOKEN))
                .then()
                .assertThat()
                .statusCode(200);

    }

    // Delete board
    @Test(priority = 4)
    void deleteBoard() {
        if (boardID == null) {
            Assert.fail("Board id is null, make sure you ran first test");
        }

        given()
                .when()
                .pathParam("id", boardID)
                .delete(String.format("https://api.trello.com/1/boards/{id}?key=%s&token=%s", API_KEY, API_TOKEN))
                .then()
                .assertThat()
                .statusCode(200);
    }

}
