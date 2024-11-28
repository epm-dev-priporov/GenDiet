package dev.priporov.ftwc.service;

import dev.priporov.ftwc.dto.DiseaseDto;
import dev.priporov.ftwc.mapper.DiseaseMapper;
import dev.priporov.ftwc.repository.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseServiceImpl implements DiseaseService {

    private final DiseaseMapper diseaseMapper;
    private final DiseaseRepository diseaseRepository;

    @Override
    public Set<DiseaseDto> getDisease(){
        return diseaseRepository.findAll()
                .stream()
                .map(diseaseMapper::toDto)
                .collect(Collectors.toSet());
    }

}
