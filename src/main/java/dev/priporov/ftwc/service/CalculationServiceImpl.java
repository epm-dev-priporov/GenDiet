package dev.priporov.ftwc.service;

import dev.priporov.ftwc.common.AliasConstants;
import dev.priporov.ftwc.dto.CalculationRequest;
import dev.priporov.ftwc.dto.CalculationResponse;
import dev.priporov.ftwc.entity.FormulaEntity;
import dev.priporov.ftwc.repository.FormulaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableStatement;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final FormulaRepository formulaRepository;


    public CalculationResponse calculate(CalculationRequest request) {
        log.info("Calculating formula: {}", request);
        FormulaEntity formula = formulaRepository.findFormula(request);
        if(formula == null) {
            throw new RuntimeException("Something wrong");
        }
        Map<String, Double> variables = toVariables(formula, request);

        ExecutableStatement statement = (ExecutableStatement) MVEL.compileExpression(formula.getFormula());
        Double result = MVEL.executeExpression(statement, variables, Double.class);

        return new CalculationResponse(result);
    }

    private Map<String, Double> toVariables(
            FormulaEntity formula,
            CalculationRequest request
    ) {
        Map<String, Double> variables = new HashMap<>();
        formula.getVariables().forEach(variable -> {
            String name = variable.getName();

            if (AliasConstants.AGE.equals(variable.getAlias())) {
                variables.put(name, request.getAge().doubleValue());
            } else if (AliasConstants.WEIGHT.equals(variable.getAlias())) {
                variables.put(name, request.getWeight().doubleValue());
            } else {
                variables.put(name, variable.getValue().doubleValue());
            }
        });

        return variables;
    }

}
