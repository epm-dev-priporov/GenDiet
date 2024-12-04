package dev.priporov.gendiet.service;

import dev.priporov.gendiet.dto.CalculationRequest;

import java.util.List;

public interface CalculationService {

    List<String> calculate(CalculationRequest request);

}
