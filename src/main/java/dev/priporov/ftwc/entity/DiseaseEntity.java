package dev.priporov.ftwc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseEntity {

    private Long id;
    private String name;
    private Set<MixtureEntity> mixtures = new HashSet<>();

}
