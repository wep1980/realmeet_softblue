package br.com.wepdev.realmeet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
Classe de configuração criada para fornecer uma instancia de RestTemplate e resolver o erro de :
Parameter 0 of constructor in br.com.wepdev.realmeet.api.ApiClient required a bean of type ‘org.springframework.web.client.RestTemplate’ that could not be found.

Action:

Consider defining a bean of type ‘org.springframework.web.client.RestTemplate’ in your configuration.
 */
@Configuration
public class ClientConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
