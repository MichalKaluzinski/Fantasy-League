package com.michalkaluzinski.fantasyleague.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class UserCustomLeaguePK implements Serializable {
  
  private static final long serialVersionUID = 1L;

  private Integer userId;
  
  private Integer customLeagueId;
}
