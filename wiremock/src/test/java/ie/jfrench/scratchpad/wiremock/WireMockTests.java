package ie.jfrench.scratchpad.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.restassured.RestAssured.given;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
        stubFor(get(urlPathEqualTo("/services/EventingConnector"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBody("You've reached a valid WireMock endpoint")));
    }

    @Test
    public void testStatusCodePositive() throws UnknownHostException {
        given().
                when().
                get("http://localhost:8080/services/EventingConnector?affinity=" + InetAddress.getLocalHost().getHostAddress()).
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void testContents() throws UnknownHostException {
        given().
                when().
                get("http://localhost:8080/services/EventingConnector?affinity=" + InetAddress.getLocalHost().getHostAddress()).
                then().
                assertThat().body(equalTo("You've reached a valid WireMock endpoint"));
    }
}
