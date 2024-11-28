package dev.priporov.ftwc.service;

import dev.priporov.ftwc.dto.CalculationRequest;
import dev.priporov.ftwc.dto.CalculationResponse;

public interface CalculationService {

    CalculationResponse calculate(CalculationRequest request);

}
