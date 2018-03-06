import io.github.robwin.swagger.test.SwaggerAssertions;
import io.swagger.models.auth.AuthorizationValue;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class AssertjSwaggerTest {

    private final String swaggerhubToken = System.getenv("SWAGGERHUB_APIKEY");

    @Test
    public void specsShouldMatch() {
        String designFirstSwagger = AssertjSwaggerTest.class.getResource("/PetStoreAPI_1.0.0.json").getPath();
        List<AuthorizationValue> auths = Collections.singletonList(new AuthorizationValue("Authorization", swaggerhubToken, "header"));
        SwaggerAssertions.assertThat(designFirstSwagger)
                .isEqualTo("https://api.swaggerhub.com/apis/jsfrench/PetStoreAPI/1.0.0", auths);
    }
}
