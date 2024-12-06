package dev.priporov.gendiet.document.phenylketonuria;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProteinRequirementsDocument {
    private String id;
    private List<ProteinRequirement> proteinRequirement;
    private List<AcceptableValueFa> acceptableValueFa;
    private Map<String, Double> constants;
    private List<Formula> formulas;

}
