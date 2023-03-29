import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class PostTest {

    @Test
    public void ShouldCheckAnswerOfPostRequest() {
// Given - When - Then
// Предусловия
        JSONObject requestBody = new JSONObject()
                .put("id", "1")
                .put("name", "Василий")
                .put("surname", "Петров")
                .put("loyaltyprogramnumber", "235226230012")
                .put("registrationdate", "01.03.2014")
                .put("bonusbalance", 1870);
        given()
                .baseUri("https://postman-echo.com")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(requestBody.toString())
// отправляемые данные (заголовки и query можно выставлять аналогично)
// Выполняемые действия
                .when()
                .post("/post")
// Проверки
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("data.name", equalTo("Василий"))
                .body("data.bonusbalance", equalTo(1870))
        ;
    }
}
