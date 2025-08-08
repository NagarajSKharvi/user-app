//package com.apexon.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Value("${documentation.swagger.organization:iVoyant}")  //
//    private String organization;
//
//    @Value("${documentation.swagger.email:info@ivoyant.com}")  //
//    private String email;
//
//    @Value("${documentation.swagger.url:https://www.ivoyant.com/}")  //
//    private String url;
//
//    @Value("${documentation.swagger.version:v1}")  //
//    private String version;
//
//    @Value("${documentation.swagger.terms-and-conditions:https://www.ivoyant.com/terms-and-conditions}")  //
//    private String termsAndConditions;
//
//    @Value("${documentation.swagger.title:API Documentation}")  // Default title if not provided
//    private String title;
//
//    @Value("${documentation.swagger.description:API documentation for the service}")  // Default description if not provided
//    private String description;
//
//    @Value("${documentation.swagger.displayName:API displayName for the service}")  // Default displayName if not provided
//    private String displayName;
//
//    @Bean
//    public OpenAPI myOpenAPI() {
//        Contact contact = new Contact()
//                .name(organization)
//                .email(email)
//                .url(url);
//
//        Info info = new Info()
//                .title(title)
//                .version(version)
//                .contact(contact)
//                .description(description)
//                .termsOfService(termsAndConditions);
//
//        return new OpenAPI().info(info);
//    }
//
//    @Bean
//    public GroupedOpenApi httpApi() {
//        return GroupedOpenApi.builder()
//                .group(organization)
//                .pathsToMatch("/**")
//                .displayName(displayName)
//                .build();
//    }
//}
