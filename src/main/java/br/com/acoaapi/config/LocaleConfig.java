package br.com.acoaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig extends WebMvcConfigurerAdapter {

    @Bean
    public LocaleResolver localeResolver() {
        //Set the default locale to US
        //The LocaleResolver interface has implementations that determine the current locale
        //based on the sessions, cookies, the Accept-Language header, or a fixed value.
        //Here, we have used the session based resolver SessionLocaleResolver and set a
        //default locale with value US
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        //An interceptor bean that will switch to a new locale based on the value of the
        //lang parameter appended to a request
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    //In order to take effect, this bean needs to be added to the application's interceptor
    //registry.
    //To achieve this, our @Configuration class has to extend the WebMvcConfigurerAdapter class
    //and override the addInterceptors method
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
