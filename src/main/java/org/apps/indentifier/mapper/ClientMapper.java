package org.apps.indentifier.mapper;

import org.apps.indentifier.dto.ClientDto;
import org.apps.indentifier.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    ClientDto userEntityToDto(Client client);
    Client userDtoToUserEntity(ClientDto userDto);
}
