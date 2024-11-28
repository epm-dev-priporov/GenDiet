package dev.priporov.ftwc.controller;

import dev.priporov.ftwc.dto.CalculationRequest;
import dev.priporov.ftwc.dto.CalculationResponse;
import dev.priporov.ftwc.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculation")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @PostMapping
    @CrossOrigin
    public CalculationResponse calculate(@RequestBody CalculationRequest request) {
        return calculationService.calculate(request);
    }

}
