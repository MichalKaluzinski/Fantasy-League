package com.michalkaluzinski.fantasyleague.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name =  "game")
@Getter
@Setter
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @ManyToOne
  @JoinColumn(name = "leagueId", insertable = false, updatable = false)
  private League league;
  
  @ManyToOne
  @JoinColumn(name = "homeTeamId", insertable = false, updatable = false)
  private Team homeTeam;
  
  @ManyToOne
  @JoinColumn(name = "awayTeamId", insertable = false, updatable = false)
  private Team awayTeam;
  
  private Integer round;
  
  private Integer homeTeamScore;
  
  private Integer awayTeamScore;
  
  @Column(name = "start_at")
  private Timestamp startAt;
}
