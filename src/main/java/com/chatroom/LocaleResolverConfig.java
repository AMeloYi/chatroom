package com.chatroom;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Provides what is needed to handle locale resolution and change.
 */
@Configuration
public class LocaleResolverConfig extends WebMvcConfigurerAdapter {

  /*
   * (non-Javadoc)
   *
   * @see
   * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
   * addInterceptors(org.springframework.web.servlet.config.annotation.
   * InterceptorRegistry)
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  /**
   * Locale change interceptor.
   *
   * @return the locale change interceptor
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    // It allows changing the locale using URLs with query parameter ?locale=fr
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    return localeChangeInterceptor;
  }

  /**
   * Locale resolver.
   *
   * @return the locale resolver
   */
  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setDefaultLocale(Locale.ENGLISH);
    return localeResolver;
  }

}
