package com.michalkaluzinski.fantasyleague.model;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.michalkaluzinski.fantasyleague.entities.User;
import com.michalkaluzinski.fantasyleague.entities.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private User user;

  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getUserAuthorities()
        .stream()
        .map(UserAuthority::getAuthority)
        .map(e -> new SimpleGrantedAuthority(e.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public UserDetailsImpl(User user) {
    super();
    this.user = user;
  }
}
