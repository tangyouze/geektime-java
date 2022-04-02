package com.tangyouze;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tyz
 */
@Configuration
public class AppConfig {

    @Bean
    public Foo foo(){
        return new Foo();
    }

}
