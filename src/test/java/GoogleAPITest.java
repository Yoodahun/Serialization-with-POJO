import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo.googleAPI.AddPlace;
import pojo.googleAPI.subClass.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GoogleAPITest {

    @Test
    public void googleAPITest(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .body(serializationAddPlace())
        .when().post("/maps/api/place/add/json")
        .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println(response);
    }

    @Test
    public void googleAPITestUsingSpecBuilder() {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                                .addQueryParam("key", "qaclick123")
                                .setContentType(ContentType.JSON)
                                .build();
        ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
                                        .expectContentType(ContentType.JSON)
                                        .build();

        req.log().all().spec(req).body(serializationAddPlace());
        String response = req.when().post("/maps/api/place/add/json")
                            .then().log().all()
                            .assertThat().spec(res).extract().response().asString();

        System.out.println(response);

    }

    private AddPlace serializationAddPlace() {
        AddPlace addPlace = new AddPlace();

        addPlace.setAccuracy(50);
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("French-IN");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("http://google.com");
        addPlace.setName("Frontline hous");

        List<String> list = new ArrayList<>();
        list.add("shoe par");
        list.add("shop");
        addPlace.setTypes(list);
        addPlace.setLocation(new Location());

        return addPlace;


    }

}
