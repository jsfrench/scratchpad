package parsing;

import io.swagger.oas.models.OpenAPI;
import v2.io.swagger.parser.SwaggerParser;
import v2.io.swagger.models.Swagger;
import io.swagger.parser.v3.OpenAPIV3Parser;
import io.swagger.util.Json;

public class Parser {

    public static void main ( String[] args) {
//        Swagger swagger = new SwaggerParser().read("https://internal.swaggerhub.com/v1/apis/swagger-hub/registry-api/1.0.46");
        Swagger swagger = new SwaggerParser().read("registry-1.0.46.json");
        OpenAPI openApi = new OpenAPIV3Parser().read("simple-oauth-3.0.0.json");
        String swaggerString = Json.pretty(swagger);
        String openAPiString = Json.pretty(openApi);



        System.out.println("*** SWAGGER ***");
        System.out.println(swaggerString);
        System.out.println();
        System.out.println("*** OpenAPI *** ");
        System.out.println(openAPiString);

    }
}
