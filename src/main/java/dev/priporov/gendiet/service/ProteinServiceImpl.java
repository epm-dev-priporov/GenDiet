package dev.priporov.gendiet.service;

import dev.priporov.gendiet.document.DiseaseDocument;
import dev.priporov.gendiet.repository.MongoDbClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProteinServiceImpl implements ProteinService {

    private final MongoDbClient mongoDbClient;

    @Override
    public DiseaseDocument getTableIdByMixtureId(String mixtureId){
        return mongoDbClient.findDiseaseByMixtureId(mixtureId);
    }

}
