package dev.priporov.ftwc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDto {

    private Long id;
    private String name;
    private Set<MixtureDto> mixtures;

}
