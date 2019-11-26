package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.CustomLeagueDTO;
import com.michalkaluzinski.fantasyleague.entities.CustomLeague;

@Component
public class CustomLeagueToCustomLeagueDTOConverter
    implements Converter<CustomLeague, CustomLeagueDTO> {

  @Override
  public CustomLeagueDTO convert(CustomLeague source) {
    CustomLeagueDTO customLeagueDTO = new CustomLeagueDTO();
    customLeagueDTO.setId(source.getId());
    customLeagueDTO.setName(source.getName());
    customLeagueDTO.setLeagueId(source.getId());
    return customLeagueDTO;
  }
}
