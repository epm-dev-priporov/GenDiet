package dev.priporov.gendiet.document.phenylketonuria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProteinRequirement {
    private String type;
    private String name;
    private int ageMin;
    private int ageMax;
    private double value;
}