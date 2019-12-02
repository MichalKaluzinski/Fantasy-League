package com.michalkaluzinski.fantasyleague.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String login;

  private String password;

  private String email;

  @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean enabled;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private Set<UserAuthority> userAuthorities;
}
