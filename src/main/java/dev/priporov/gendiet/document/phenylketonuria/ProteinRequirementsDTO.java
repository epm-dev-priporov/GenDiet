package dev.priporov.gendiet.document.phenylketonuria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProteinRequirementsDTO {
    private List<ProteinRequirement> proteinRequirement;
    private List<AcceptableValueFa> acceptableValueFa;
    private Constants constants;
    private String formulaProteinNatural;
    private String formulaProteinMedicinalMixture;
}
