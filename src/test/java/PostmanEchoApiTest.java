import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class PostmanEchoApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("GET request - проверка всех полей ответа с параметрами")
    public void testGetRequest() {
        Response response = given()
                .when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("bar1", response.jsonPath().getString("args.foo1"));
        assertEquals("bar2", response.jsonPath().getString("args.foo2"));
        assertEquals("https://postman-echo.com/get?foo1=bar1&foo2=bar2",
                response.jsonPath().getString("url"));
        assertNotNull(response.jsonPath().getString("headers"));
        assertNotNull(response.jsonPath().getString("headers.host"));
    }

    @Test
    @DisplayName("POST request с raw text - проверка передачи текста")
    public void testPostRawText() {
        String rawText = "This is a raw text request";

        Response response = given()
                .contentType("text/plain")
                .body(rawText)
                .when()
                .post("/post")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals(rawText, response.jsonPath().getString("data"));
        assertNotNull(response.jsonPath().getString("headers"));
        assertEquals("https://postman-echo.com/post", response.jsonPath().getString("url"));
    }

    @Test
    @DisplayName("POST request с form-data - проверка передачи параметров формы")
    public void testPostFormData() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("username", "testuser");
        formParams.put("password", "testpass123");
        formParams.put("rememberMe", "true");

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams(formParams)
                .when()
                .post("/post")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("testuser", response.jsonPath().getString("form.username"));
        assertEquals("testpass123", response.jsonPath().getString("form.password"));
        assertEquals("true", response.jsonPath().getString("form.rememberMe"));
        assertNotNull(response.jsonPath().getString("headers"));
    }

    @Test
    @DisplayName("POST request с JSON - проверка передачи JSON данных")
    public void testPostJson() {
        String jsonBody = "{\"name\":\"John\",\"age\":30,\"active\":true}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/post")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("John", response.jsonPath().getString("json.name"));
        assertEquals(30, response.jsonPath().getInt("json.age"));
        assertTrue(response.jsonPath().getBoolean("json.active"));
        assertEquals("https://postman-echo.com/post", response.jsonPath().getString("url"));
    }

    @Test
    @DisplayName("PUT request - проверка обновления данных")
    public void testPutRequest() {
        String jsonBody = "{\"id\":123,\"updated\":true,\"status\":\"completed\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("/put")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals(123, response.jsonPath().getInt("json.id"));
        assertTrue(response.jsonPath().getBoolean("json.updated"));
        assertEquals("completed", response.jsonPath().getString("json.status"));
        assertEquals("https://postman-echo.com/put", response.jsonPath().getString("url"));
    }

    @Test
    @DisplayName("PATCH request - проверка частичного обновления")
    public void testPatchRequest() {
        String jsonBody = "{\"name\":\"updatedValue\",\"active\":false}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .patch("/patch")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("updatedValue", response.jsonPath().getString("json.name"));
        assertFalse(response.jsonPath().getBoolean("json.active"));
        assertEquals("https://postman-echo.com/patch", response.jsonPath().getString("url"));
        assertNotNull(response.jsonPath().getString("headers"));
    }

    @Test
    @DisplayName("DELETE request - проверка удаления")
    public void testDeleteRequest() {
        Response response = given()
                .when()
                .delete("/delete")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("https://postman-echo.com/delete", response.jsonPath().getString("url"));
        assertNotNull(response.jsonPath().getString("headers"));
        assertNotNull(response.jsonPath().getString("data"));
    }
}