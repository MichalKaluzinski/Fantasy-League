package com.michalkaluzinski.fantasyleague.configuration;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.michalkaluzinski.fantasyleague.filters.JwtTokenFilter;
import com.michalkaluzinski.fantasyleague.services.JwtTokenProviderService;

public class JwtConfigurer
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private JwtTokenProviderService jwtTokenProviderService;

  public JwtConfigurer(JwtTokenProviderService jwtTokenProviderService) {
    this.jwtTokenProviderService = jwtTokenProviderService;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProviderService);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
