package dev.priporov.gendiet.controller;

import dev.priporov.gendiet.dto.CalculationRequest;
import dev.priporov.gendiet.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculation")
public class CalculationController {

    private final CalculationService calculationService;

    @PostMapping
    @CrossOrigin
    public List<String> calculate(@RequestBody CalculationRequest request){
        return calculationService.calculate(request);
    }

}
