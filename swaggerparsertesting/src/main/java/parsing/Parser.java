package parsing;

import io.swagger.parser.SwaggerParser;
import io.swagger.models.Swagger;
import io.swagger.util.Json;

public class Parser {

    public static void main ( String[] args) {
        Swagger swagger = new SwaggerParser().read("https://internal.swaggerhub.com/v1/apis/swagger-hub/registry-api/1.0.46");
        String swaggerString = Json.pretty(swagger);


        Swagger swagger2 = new SwaggerParser().read("http://mongo-dev.swaggerhub.com/apis/jftest1/nfp-data_transfer_api/1.0.3");
        String swaggerString2 = Json.pretty(swagger2);

        System.out.println("SWAGGER: " + swaggerString);
        System.out.println("SWAGGER: " + swaggerString2);

    }
}
