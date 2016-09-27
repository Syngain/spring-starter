package com.hualongdata.springstarter.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * Created by yangbajing on 16-9-12.
 */
@Configuration
@EnableSwagger2
//@ComponentScan({"com.hualongdata.springstarter.web.controllers", "com.hualongdata.springstarter.config"})
@AutoConfigureAfter(CustomConfiguration.class)
public class SwaggerConfiguration {

    @Autowired
    private SwaggerSetting swaggerSetting;

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerSetting.getGroup())
                .apiInfo(new ApiInfo(swaggerSetting.getTitle(), swaggerSetting.getDescription(),
                        swaggerSetting.getVersion(), swaggerSetting.getTermsOfServiceUrl(),
                        new Contact(swaggerSetting.getContact(), "", ""),
                        swaggerSetting.getLicense(), swaggerSetting.getLicenseUrl()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hualongdata.springstarter.web.controllers"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.ant("/inapi/*"))
//                .paths(PathSelectors.ant("/sign/*"))
                .build()
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .pathMapping("/")
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET,
//                        newArrayList(new ResponseMessageBuilder()
//                                .code(500)
//                                .message("500 message")
//                                .responseModel(new ModelRef("Error"))
//                                .build()));
//                .securitySchemes(newArrayList(apiKey()))
//                .securityContexts(newArrayList(securityContext()))
//                .globalOperationParameters(
//                        newArrayList(new ParameterBuilder()
//                                .name("someGlobalParameter")
//                                .description("Description of someGlobalParameter")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("query")
//                                .required(true)
//                                .build()))
//                .tags(new Tag("Pet Service", "All apis relating to pets"));
//                .additionalModels(typeResolver.resolve(AdditionalModel.class))
                .enableUrlTemplating(true);
    }

//    private ApiKey apiKey() {
//        return new ApiKey("mykey", "api_key", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/anyPath.*"))
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return newArrayList(
//                new SecurityReference("mykey", authorizationScopes));
//    }
//
//    @Bean
//    SecurityConfiguration security() {
//        return new SecurityConfiguration(
//                "test-app-client-id",
//                "test-app-client-secret",
//                "test-app-realm",
//                "test-app",
//                "apiKey",
//                ApiKeyVehicle.HEADER,
//                "api_key",
//                "," /*scope separator*/);
//    }

}
