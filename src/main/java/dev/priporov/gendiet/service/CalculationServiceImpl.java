package dev.priporov.gendiet.service;

import dev.priporov.gendiet.document.DiseaseDocument;
import dev.priporov.gendiet.dto.CalculationRequest;
import dev.priporov.gendiet.handler.BaseMixtureHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CalculationServiceImpl implements CalculationService {

    private final ProteinService proteinService;
    private final Map<String, BaseMixtureHandler> handlers;

    public CalculationServiceImpl(
            ProteinService proteinService,
            List<BaseMixtureHandler> handlerList
    ) {
        this.proteinService = proteinService;
        this.handlers = handlerList
                .stream()
                .collect(Collectors.toMap(BaseMixtureHandler::getDiseaseId, Function.identity()));
    }

    @Override
    public List<String> calculate(CalculationRequest request) {
        String mixtureId = request.getMixtureId();
        DiseaseDocument diseaseDocument = proteinService.getTableIdByMixtureId(mixtureId);
        BaseMixtureHandler handler = handlers.get(diseaseDocument.getTableId());

        return handler.calculate(request, diseaseDocument);
    }

}
