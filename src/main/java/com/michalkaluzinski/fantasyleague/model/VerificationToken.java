package com.michalkaluzinski.fantasyleague.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
public class VerificationToken {

  @Id 
  @Column(name = "user_id")
  private Integer userId;

  private String token;

  @Column(name = "expiration_date")
  private Timestamp expirationDate;
}
