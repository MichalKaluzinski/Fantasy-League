package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import com.michalkaluzinski.fantasyleague.InvalidJwtAuthenticationException;

public interface JwtTokenProviderService {

  public String createToken(String username, Set<String> roles);

  public Authentication getAuthentication(String token);

  public String getUsername(String token);

  public String resolveToken(HttpServletRequest req);

  public boolean validateToken(String token) throws InvalidJwtAuthenticationException;
  
  public List<String> getWhiteList();
}
