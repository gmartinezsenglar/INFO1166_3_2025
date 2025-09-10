package com.bne.postulaciones_service.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonBeansConfig {
    //
    //@Bean public ObjectMapper objectMapper() { return new ObjectMapper(); } PROBOCA QUE SE ROMAPA EL PARAMETRO "fecha"
    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
//// CommonBeansConfig.java conserva ben yy registrar módulos
//@Bean
//public ObjectMapper objectMapper() {
//    var mapper = new ObjectMapper();
//    mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
//    mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    return mapper;
//}