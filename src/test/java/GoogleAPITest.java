import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoogleAPITest {

    @Test
    public void googleAPITest(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .body("")
        .when().post("/maps/api/place/add/json")
        .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println(response);
    }

}
