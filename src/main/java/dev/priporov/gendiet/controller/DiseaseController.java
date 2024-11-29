package dev.priporov.gendiet.controller;

import lombok.RequiredArgsConstructor;
import net.ravendb.client.documents.DocumentStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disease")
@RequiredArgsConstructor
public class DiseaseController {

    private final DocumentStore store;

//    @PostMapping
//    public void method(@RequestBody RequestDocument document) {
//        try (IDocumentSession session = store.openSession()) {
//            session.store(new DiseaseDocument(UUID.randomUUID(), document.getName(), document.getMixtures()));
//            session.saveChanges();
//        }
//    }
//
//    @GetMapping
//    public List<DiseaseDocument> method2(){
//        try (IDocumentSession session = store.openSession()) {
//            return session.query(DiseaseDocument.class).toList();
//        }
//    }

}
