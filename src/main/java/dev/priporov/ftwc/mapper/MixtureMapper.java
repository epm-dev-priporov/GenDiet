package dev.priporov.ftwc.mapper;

import dev.priporov.ftwc.dto.MixtureDto;
import dev.priporov.ftwc.entity.MixtureEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MixtureMapper {

    MixtureDto toDto(MixtureEntity entity);

}
