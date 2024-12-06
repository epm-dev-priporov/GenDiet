package dev.priporov.gendiet.controller;

import dev.priporov.gendiet.dto.DiseaseDto;
import dev.priporov.gendiet.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/disease")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;

    @GetMapping
    @CrossOrigin
    public List<DiseaseDto> getDisease(){
        return diseaseService.getDisease();
    }

}
