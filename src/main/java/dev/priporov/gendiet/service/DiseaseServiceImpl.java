package dev.priporov.gendiet.service;

import dev.priporov.gendiet.dto.DiseaseDto;
import dev.priporov.gendiet.mapper.DiseaseMapper;
import dev.priporov.gendiet.repository.MongoDbClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseServiceImpl implements DiseaseService {

    private final MongoDbClient dbClient;
    private final DiseaseMapper diseaseMapper;

    @Override
    public List<DiseaseDto> getDisease() {
        return dbClient.findDisease()
                .stream()
                .map(diseaseMapper::toDto)
                .collect(Collectors.toList());
    }

}
