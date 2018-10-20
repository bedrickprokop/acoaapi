package br.com.acoaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket detalheApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select().apis(RequestHandlerSelectors.basePackage("br.com.acoaapi"))
                .paths(PathSelectors.any()).build().apiInfo(getInfo().build());
        return docket;
    }

    private ApiInfoBuilder getInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("ACOA-Api");
        apiInfoBuilder.description("Api de consumo e envio de dados para o sistema Acoa");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.contact(getContact());
        return apiInfoBuilder;
    }

    private Contact getContact() {
        return new Contact("Bedrick Prokop", null, "bedrick@acoa.com.br");
    }
}