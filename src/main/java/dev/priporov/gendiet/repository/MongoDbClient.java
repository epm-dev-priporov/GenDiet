package dev.priporov.gendiet.repository;

import dev.priporov.gendiet.document.DiseaseDocument;
import dev.priporov.gendiet.document.Mixture;
import dev.priporov.gendiet.document.phenylketonuria.ProteinRequirementsDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MongoDbClient {

    private final MongoTemplate mongoTemplate;

    public void saveAll(List<Object> documents) {
        mongoTemplate.getDb();
        for (Object document : documents) {
            mongoTemplate.save(document);
        }
    }

    public List<DiseaseDocument> findDisease() {
        return mongoTemplate.findAll(DiseaseDocument.class);
    }

    public Mixture findMixtureById(String mixtureId) {
        String query = "{\"mixtures\": {$elemMatch: {id: \"%s\"}}}".formatted(mixtureId);
        return mongoTemplate.findOne(new BasicQuery(query), Mixture.class);
    }

    public ProteinRequirementsDocument findProteinRequirementsByTableId(String id) {
        return mongoTemplate.findById(id, ProteinRequirementsDocument.class);
    }

    public DiseaseDocument findDiseaseByMixtureId(String mixtureId){
        String query = "{\"mixtures._id\": \"%s\"}".formatted(mixtureId);
        DiseaseDocument diseaseDocument = mongoTemplate.findOne(
                new BasicQuery(query),
                DiseaseDocument.class
        );
        return diseaseDocument;
    }
}
