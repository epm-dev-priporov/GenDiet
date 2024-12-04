package dev.priporov.gendiet.handler;

import dev.priporov.gendiet.document.DiseaseDocument;
import dev.priporov.gendiet.document.Mixture;
import dev.priporov.gendiet.document.phenylketonuria.AcceptableValueFa;
import dev.priporov.gendiet.document.phenylketonuria.Formula;
import dev.priporov.gendiet.document.phenylketonuria.ProteinRequirement;
import dev.priporov.gendiet.document.phenylketonuria.ProteinRequirementsDocument;
import dev.priporov.gendiet.dto.CalculationRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableStatement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PhenylketonuriaHandlerBase extends BaseMixtureHandler {

    @Getter
    private String diseaseId = "751145a4-cd6c-49b6-83f0-49085cf66ad9";

    public List<String> calculate(CalculationRequest request, DiseaseDocument diseaseDocument) {
        ProteinRequirementsDocument proteinRequirements = mongoDbClient.findProteinRequirementsByTableId(
                diseaseDocument.getTableId()
        );

        long months = getAgeInMonths(request);

        // TODO make in mongo query
        Mixture mixture = diseaseDocument.getMixtures()
                .stream()
                .filter(it -> it.getId().equals(request.getMixtureId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("mixture not found.[id=%s]".formatted(request.getMixtureId())));

        Map<String, Double> variables = fillVariables(
                request,
                proteinRequirements,
                mixture
        );
        log.info("variables = {}", variables);

        List<Formula> formulas = proteinRequirements.getFormulas();
        List<String> results = new ArrayList<>();
        for (Formula formula : formulas) {
            if( formula.getAgeMax() != null && formula.getAgeMax() < months ){
                continue;
            }
            Double value = compute(formula, variables);
            String message = "%s %.2f %s".formatted(formula.getDescription(), value, formula.getMeasure());

            log.info(message);

            results.add(message);
        }

        return results;
    }

    private Double compute(Formula formula, Map<String, Double> variables) {
        ExecutableStatement statement = (ExecutableStatement) MVEL.compileExpression(
                formula.getFormula()
        );
        return MVEL.executeExpression(
                statement,
                variables,
                Double.class
        );
    }

    private Map<String, Double> fillVariables(
            CalculationRequest request,
            ProteinRequirementsDocument proteinRequirements,
            Mixture mixture
    ) {
        long months = getAgeInMonths(request);
        Double weight = request.getWeight();
        Map<String, Double> constants = proteinRequirements.getConstants();

        Double amountOfRequiredProtein = getAmountOfRequiredProtein(proteinRequirements, months, weight);

        Map<String, Double> variables = new HashMap<>();

        variables.put("acceptableValueFa", getAcceptableValueFa(proteinRequirements, months).getMinValue());
        variables.put("proteinPerDayRequirement", amountOfRequiredProtein);
        variables.put("weight", weight);
        variables.put("protein", mixture.getProtein());

        variables.putAll(constants);

        return variables;
    }

    private static long getAgeInMonths(CalculationRequest request) {
        LocalDate birthDate = LocalDate.parse(request.getBirthDay());
        LocalDate now = LocalDate.now();

        return ChronoUnit.MONTHS.between(birthDate, now);
    }

    private AcceptableValueFa getAcceptableValueFa(ProteinRequirementsDocument mixture, long months) {
        return mixture.getAcceptableValueFa()
                .stream()
                .filter(it -> appropriateAge(it, months))
                .findFirst().orElseThrow(() -> new RuntimeException("Not found"));
    }

    private Double getAmountOfRequiredProtein(
            ProteinRequirementsDocument mixture,
            long months,
            Double weight
    ) {
        ProteinRequirement proteinRequirement = mixture.getProteinRequirement()
                .stream()
                .filter(it -> appropriateAge(it, months))
                .findFirst().orElseThrow(() -> new RuntimeException("Not found"));

        Double minValue = proteinRequirement.getMinValue();
        String type = proteinRequirement.getType();

        if (type.equals("per_kg")) {
            minValue = weight * minValue;
        }

        return minValue;
    }

    public boolean appropriateAge(AcceptableValueFa acceptableValueFa, long months) {
        long ageMax = acceptableValueFa.getAgeMax();
        long ageMin = acceptableValueFa.getAgeMin();
        return ageMin <= months && ageMax > months;
    }

    public boolean appropriateAge(ProteinRequirement proteinRequirement, long months) {
        long ageMax = proteinRequirement.getAgeMax();
        long ageMin = proteinRequirement.getAgeMin();
        return ageMin <= months && ageMax > months;
    }
}
