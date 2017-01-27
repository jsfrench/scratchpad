package ie.jfrench.scratchpad.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author french2
 */
public class WireMockTests {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Before
    public void setupStub() {
        // Stub GET
        stubFor(get(urlPathEqualTo("/events"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBody("You've reached the /event endpoint")));

        // Stub POST
        stubFor(post(urlPathEqualTo("/events"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBody("You've reached the /event endpoint")
                )
        );

        // Stub DELETE with regex match for id path parameter
        stubFor(delete(urlMatching("/events/id/([A-Za-z0-9]+)"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBody("You've reached the event endpoint")
                )
        );
    }

    // Test GET request with query paramater is matched
    @Test
    public void testStatusCodePositiveGet() {
        given()
                .when()
                .get("http://localhost:8080/events?affinity=" + "127.0.0.1")
                .then()
                .assertThat().statusCode(200);
    }

    // Test POST request with query paramater is matched
    @Test
    public void testStatusCodePositivePost() {
        given()
                .when()
                .post("http://localhost:8080/events?affinity=" + "127.0.0.1")
                .then()
                .assertThat().statusCode(200);

        // Check WireMock journal for request
        verify(postRequestedFor(urlPathEqualTo("/events")));
    }

    // Test response body
    @Test
    public void testContents() {
        given()
                .when()
                .get("http://localhost:8080/events?affinity=" + "127.0.0.1")
                .then()
                .assertThat().body(equalTo("You've reached the /event endpoint"));
    }

    // Test DELETE request with path parameter is matched
    @Test
    public void testStatusCodeWithPathParameter() {
        //Verify response with RESTAssured
        given()
                .when()
                .delete("http://localhost:8080/events/id/" + "123")
                .then()
                .assertThat().statusCode(200);
        
        // Check WireMock journal for request
        verify(deleteRequestedFor(urlMatching("/events/id/([A-Za-z0-9]+)")));        
    }
}
