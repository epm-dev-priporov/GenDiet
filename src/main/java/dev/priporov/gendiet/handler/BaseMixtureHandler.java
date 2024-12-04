package dev.priporov.gendiet.handler;

import dev.priporov.gendiet.document.DiseaseDocument;
import dev.priporov.gendiet.dto.CalculationRequest;
import dev.priporov.gendiet.repository.MongoDbClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

abstract public class BaseMixtureHandler {

    @Autowired
    protected MongoDbClient mongoDbClient;

    abstract public String getDiseaseId();

    abstract public List<String> calculate(CalculationRequest request, DiseaseDocument tableId);

}
