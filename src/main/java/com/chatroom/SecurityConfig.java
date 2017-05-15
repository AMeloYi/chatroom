package com.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The Class SecurityConfig.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /** The environment. */
  @Autowired
  private Environment environment;

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.security.config.annotation.web.configuration.
   * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
   * annotation.web.builders.HttpSecurity)
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    if (environment.acceptsProfiles("devel")) {
      // with devel profile, the H2 console is used but does not support CSRF
      // protection or frame protection, then disable them
      http.csrf().disable();
      http.headers().frameOptions().disable();
      // disable authentication in development
      http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
      http.authorizeRequests().antMatchers("/management/**").permitAll();
    } else {
      // require ADMIN role to access management urls
      http.authorizeRequests().antMatchers("/management/**").hasRole("ADMIN");
    }

    // admin pages are accessible only to ADMIN role
    http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

    // provide login page
    http.formLogin().loginPage("/login").failureUrl("/login-error");

    // provide logout page
    http.logout().logoutSuccessUrl("/");

    // allow anonymous user to register
    http.authorizeRequests().antMatchers("/register").hasRole("ANONYMOUS");

    // permit all by default (must be at end)
    http.authorizeRequests().anyRequest().permitAll();
  }
}
