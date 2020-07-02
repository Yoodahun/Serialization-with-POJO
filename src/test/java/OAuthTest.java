import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pojo.course.GetCourse;
import pojo.course.WebAutomation;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    String accessToken = null;
    String codeURL = null;

    @Test
    public void getCode() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver",
//                "/Users/yoodahun/Documents/Github/Java/Selenium WebDriver with Java/chromedriver");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        driver.findElement(By.id("identifierId")).sendKeys("");
//        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("");
//        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
        codeURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F1QE3mTrLjv47b8rGRwiK0FpIzgiGYlzI9IwAxl1M4pJCD-y--a03LsQ6SxTYuia9fk7yW0XA9cyajre3qnEYyY8&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&hd=likelion.org&prompt=none#";

        codeURL = codeURL.split("code=")[1].split("&scope=")[0];
        System.out.println(codeURL);
        exchangeCode(codeURL);
    }

    //send code to get Access token uri server.

    public void exchangeCode(String codeURL) {
       String response = given().log().all()
                .urlEncodingEnabled(false)
                .queryParam("code",codeURL)
                .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
        .when().post("https://www.googleapis.com/oauth2/v4/token")
                .asString();

        JsonPath jp = new JsonPath(response);
        accessToken = jp.getString("access_token");
        actualRequest(accessToken);

    }

    //there is no OAuth mechanism.
    public void actualRequest(String accessToken) {

       GetCourse response = given().log().all()
                .queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
        .when().get("https://rahulshettyacademy.com/getCourse.php/")
                .as(GetCourse.class);

        System.out.println(response.getLinkedIn());
        System.out.println(response.getInstructor());

        for (WebAutomation wa: response.getCourses().getWebAutomation()) {
            System.out.println( wa.getCourseTitle());
            System.out.println(wa.getPrice());
        }


//        System.out.println(response);
    }

}
