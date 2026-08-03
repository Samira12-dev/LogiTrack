package com.example.LOGITRACK.mapper;

import com.example.LOGITRACK.dto.request.CommandRequestDTO;
import com.example.LOGITRACK.dto.response.CommandResponseDTO;
import com.example.LOGITRACK.entity.Client;
import com.example.LOGITRACK.entity.Command;
import com.example.LOGITRACK.entity.LigneCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandMapper {

    @Mapping(source = "clientId", target = "client.id")
    Command toEntity(CommandRequestDTO dto);

    @Mapping(source = "client.id", target = "clientId")
    CommandResponseDTO toResponseDTO(Command command);

    List<CommandResponseDTO> toListDto(List<Command> commands);
}

