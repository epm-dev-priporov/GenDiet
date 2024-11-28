package dev.priporov.ftwc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalculationRequest {

    private BigDecimal weight;
    private Long mixtureId;
    private Integer age;

}
