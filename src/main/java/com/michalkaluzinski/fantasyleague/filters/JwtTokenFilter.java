package com.michalkaluzinski.fantasyleague.filters;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import com.michalkaluzinski.fantasyleague.InvalidJwtAuthenticationException;
import com.michalkaluzinski.fantasyleague.services.JwtTokenProviderService;

public class JwtTokenFilter extends GenericFilterBean {

  private JwtTokenProviderService jwtTokenProviderService;

  public JwtTokenFilter(JwtTokenProviderService jwtTokenProviderService) {
    this.jwtTokenProviderService = jwtTokenProviderService;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
      throws IOException, ServletException {

    if (req instanceof HttpServletRequest) {
      String url = ((HttpServletRequest) req).getRequestURI();
      if (jwtTokenProviderService.getWhiteList().contains(url)) {
        filterChain.doFilter(req, res);
        return;
      }
    }
    String token = jwtTokenProviderService.resolveToken((HttpServletRequest) req);
    try {
      if (token != null && jwtTokenProviderService.validateToken(token)) {
        Authentication auth =
            token != null ? jwtTokenProviderService.getAuthentication(token) : null;
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (InvalidJwtAuthenticationException e) {
      throw new ServletException("Token is not valid.");
    }
    filterChain.doFilter(req, res);
  }
}
