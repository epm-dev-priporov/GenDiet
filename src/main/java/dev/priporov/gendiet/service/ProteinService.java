package dev.priporov.gendiet.service;

import dev.priporov.gendiet.document.DiseaseDocument;

public interface ProteinService {
    DiseaseDocument getTableIdByMixtureId(String mixtureId);
}
