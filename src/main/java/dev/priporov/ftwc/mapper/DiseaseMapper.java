package dev.priporov.ftwc.mapper;

import dev.priporov.ftwc.dto.DiseaseDto;
import dev.priporov.ftwc.entity.DiseaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = MixtureMapper.class)
public interface DiseaseMapper {

    MixtureMapper MIXTURE_MAPPER = Mappers.getMapper(MixtureMapper.class);

    DiseaseDto toDto(DiseaseEntity entity);

}
