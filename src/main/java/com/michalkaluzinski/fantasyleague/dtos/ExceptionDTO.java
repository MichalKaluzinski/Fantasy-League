package com.michalkaluzinski.fantasyleague.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {

  private Integer statusCode;
  private String message;
}
