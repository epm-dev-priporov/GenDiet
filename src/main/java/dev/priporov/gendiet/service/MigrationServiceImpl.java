package dev.priporov.gendiet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.priporov.gendiet.document.DiseaseDocument;
import dev.priporov.gendiet.document.phenylketonuria.ProteinRequirementsDocument;
import dev.priporov.gendiet.repository.MongoDbClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MigrationServiceImpl implements MigrationService {

    private final ObjectMapper objectMapper;
    private final MongoDbClient dbClient;

    @PostConstruct
    public void migrate() throws IOException {
        List<Object> documents = new ArrayList<>();
        readDocument(
                "/db/phenylketonuria/table_751145a4-cd6c-49b6-83f0-49085cf66ad9.json",
                ProteinRequirementsDocument.class,
                documents
        );
        readDocument(
                "/db/phenylketonuria/phenylketonuria.json",
                DiseaseDocument.class,
                documents
        );

        dbClient.saveAll(documents);
    }

    private <T> void readDocument(String name, Class<T> target, List<Object> documents) throws IOException {
        var lookupClass = MethodHandles.lookup().lookupClass();
        InputStream stream = lookupClass.getResourceAsStream(name);
        T t = objectMapper.readValue(stream, target);
        documents.add(t);
    }

}
