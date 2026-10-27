package com.entrega.P5_P6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver(){
        //Creamos una variable que guarda el idioma en la sesion del usuario.
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        //Establece el español como idioma predeterminado.
        resolver.setDefaultLocale(Locale.forLanguageTag("es"));
        return resolver;
    }
    @Bean
    //Creamos un Interceptor para poder cambiar de idioma.
    public LocaleChangeInterceptor localeChangeInterceptor(){
        //Crea una instancia del intercerptor.
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        //Indica el parametro de la URL para cambiar el idioma.
        interceptor.setParamName("lang");
        return interceptor;


        }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Registra el interceptor encargado de cambiar el idioma.
        //Cuando la peticion contiene el parametro "lang".
        registry.addInterceptor(localeChangeInterceptor());
    }
}
