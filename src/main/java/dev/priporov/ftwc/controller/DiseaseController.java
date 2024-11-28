package dev.priporov.ftwc.controller;

import dev.priporov.ftwc.dto.DiseaseDto;
import dev.priporov.ftwc.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/disease")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;

    @GetMapping
    @CrossOrigin
    public Set<DiseaseDto> getDisease(){
        return diseaseService.getDisease();
    }

}
