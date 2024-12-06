package dev.priporov.gendiet.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDocument {

    @MongoId
    private String id;
    private String name;
    @JsonProperty("table_id")
    private String tableId;
    private List<Mixture> mixtures;

}