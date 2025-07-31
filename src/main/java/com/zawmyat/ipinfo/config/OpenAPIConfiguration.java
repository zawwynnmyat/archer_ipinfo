package com.zawmyat.ipinfo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {

//        Server server = new Server();
//        server.setUrl("http://localhost:8080");
//        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Zaw Myat");
        myContact.setEmail("zawwinmyat.dev@gmail.com");
        myContact.url("https://linkedin.com/in/zaw-win-myat-1054b525b/");


        Info information = new Info()
                .title("Archer IP Info API")
                .version("1.0")
                .description("Detail information of the IP addresses you entered and your IP address")
                .summary("IP address information!")
                .contact(myContact);

        //return new OpenAPI().info(information).servers(List.of(server));
        return new OpenAPI().info(information);
    }

}
