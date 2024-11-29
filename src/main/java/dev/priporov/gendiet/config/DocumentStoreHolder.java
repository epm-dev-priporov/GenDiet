package dev.priporov.gendiet.config;

import net.ravendb.client.documents.DocumentStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentStoreHolder {

    @Bean
    public DocumentStore method() {
        DocumentStore store = new DocumentStore("http://localhost:8888", "gendiet");
        store.initialize();
        return store;
    }

}