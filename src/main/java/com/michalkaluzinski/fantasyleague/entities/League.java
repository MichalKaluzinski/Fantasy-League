package com.michalkaluzinski.fantasyleague.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "league")
@Getter
@Setter
public class League {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean active;

  @OneToMany(mappedBy = "league", fetch = FetchType.EAGER)
  private Set<Team> teams;
}
