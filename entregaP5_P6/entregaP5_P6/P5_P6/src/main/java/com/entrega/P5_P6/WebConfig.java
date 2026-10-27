package com.entrega.P5_P6;

import jakarta.websocket.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

public class WebConfig implements WebMvcConfigurer {
    @Bean
    //Define el metodo que nos srive para gestionar el idioma de la aplicacion.
    public LocaleResolver localeResolver(){
        //Crea el metodo y guarda el idioma seleccionado por el usuario en esa sesion
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        //Idioma inicial, esta predeterminado el español,mientras no se elija otro.
        resolver.setDefaultLocale(Locale.forLanguageTag("es"));
    return resolver;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
    return interceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
}
