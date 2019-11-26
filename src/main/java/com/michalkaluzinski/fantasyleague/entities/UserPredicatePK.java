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
public class UserPredicatePK implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private Integer userId;
  
  private Integer gameId;
}
