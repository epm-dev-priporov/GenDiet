package dev.priporov.gendiet.document.phenylketonuria;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcceptableValueFa {
    private UUID id;
    private String name;
    @JsonProperty("age_min")
    private Long ageMin;
    @JsonProperty("age_max")
    private Long ageMax;
    @JsonProperty("min_value")
    private Double minValue;
    @JsonProperty("max_value")
    private Double maxValue;
}