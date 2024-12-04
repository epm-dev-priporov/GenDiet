package dev.priporov.gendiet.mapper;

import dev.priporov.gendiet.document.DiseaseDocument;
import dev.priporov.gendiet.document.Mixture;
import dev.priporov.gendiet.dto.DiseaseDto;
import dev.priporov.gendiet.dto.MixtureDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DiseaseMapper {

    public DiseaseDto toDto(DiseaseDocument document){
        DiseaseDto dto = new DiseaseDto();
        dto.setId(document.getId());
        dto.setName(document.getName());
        Set<MixtureDto> mixtures = new HashSet<>();

        for (Mixture mixture : document.getMixtures()) {
            MixtureDto mixtureDto = new MixtureDto();
            mixtureDto.setId(mixture.getId());
            mixtureDto.setName(mixture.getName());
            mixtures.add(mixtureDto);
        }
        dto.setMixtures(mixtures);

        return dto;
    }

}
