package dev.priporov.gendiet.document.phenylketonuria;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Constants {

    @JsonProperty("ACCEPTABLE_PROTEIN_PER_GRAM")
    private Double acceptableProteinPerGram;

}