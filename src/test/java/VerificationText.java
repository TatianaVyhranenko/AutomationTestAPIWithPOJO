import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerificationText {

    private final static String URL = "https://reqres.in/";
    private final String EXPECTED_SUPPORT_TEXT = "To keep ReqRes free, contributions towards server costs are appreciated!";

    @Test
    public void checkText() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecification(200));

        Support responseSupportData = given()
                .when()
                .get("/api/unknown/2")
                .then().log().all()
                .extract().body().jsonPath()
                .getObject("support", Support.class);

        String actualSupportText = responseSupportData.getText().trim();
        Assert.assertNotNull(actualSupportText);
        Assert.assertEquals(actualSupportText, EXPECTED_SUPPORT_TEXT);

    }

}
