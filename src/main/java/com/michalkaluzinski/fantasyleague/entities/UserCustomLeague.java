package com.michalkaluzinski.fantasyleague.entities;

import java.io.Serializable;
import javax.persistence.Column;
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
public class UserCustomLeague implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId private UserCustomLeaguePK userCustomLeaguePK;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userId", insertable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customLeagueId", insertable = false, updatable = false)
  private CustomLeague customLeague;
  
  @Column
  private Integer membershipType;
}
