package com.michalkaluzinski.fantasyleague.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAuthority implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId private UserAuthorityPK userAuthorityPK;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "authorityId", insertable = false, updatable = false)
  private Authority authority;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userId", insertable = false, updatable = false)
  private User user;
}
