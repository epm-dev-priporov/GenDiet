package dev.priporov.gendiet.document.phenylketonuria;


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
    private int ageMin;
    private int ageMax;
    private double min_value;
    private double max_value;
}