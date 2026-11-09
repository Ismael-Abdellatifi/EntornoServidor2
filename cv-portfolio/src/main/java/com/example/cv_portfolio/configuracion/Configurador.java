package com.example.cv_portfolio.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// Configuración de la internacionalización (i18n): español e inglés
@Configuration
public class Configurador implements WebMvcConfigurer {
    // Guarda el idioma elegido en la sesión, así se mantiene al cambiar de página.
    // Si no se ha elegido ninguno, por defecto es español
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(java.util.Locale.forLanguageTag("es"));
        return resolver;
    }
    // Cambia el idioma cuando la URL lleva el parámetro lang (?lang=en o ?lang=es
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }
    // Registro el interceptor para que se ejecute en todas las peticiones
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }
}
