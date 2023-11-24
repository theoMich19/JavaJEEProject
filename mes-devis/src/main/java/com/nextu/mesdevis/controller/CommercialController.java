package com.nextu.mesdevis.controller;

import com.nextu.mesdevis.dto.CommercialDto;
import com.nextu.mesdevis.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur qui responsable de la gestion des interactions entre le modèle et la vue.
 * Ici il s'agit du controller de commercials
 * Il propopose plusieurs endpoint, get, post, push , delete
 */
@RestController
@RequestMapping("/commercials")
public class CommercialController {

    @Autowired
    private CommercialService commercialService;

    // Controller methods

    @GetMapping
    public ResponseEntity<List<CommercialDto>> getAllCommercials() {
        List<CommercialDto> commercials = commercialService.getAllCommercials();
        return new ResponseEntity<>(commercials, HttpStatus.OK);
    }

    @GetMapping("/{commercialId}")
    public ResponseEntity<CommercialDto> getCommercialById(@PathVariable Long commercialId) {
        CommercialDto commercial = commercialService.getCommercialById(commercialId);
        return new ResponseEntity<>(commercial, HttpStatus.OK);
    }

    @GetMapping("/byRole/{role}")
    public ResponseEntity<CommercialDto> getCommercialByRole(@PathVariable String role) {
        CommercialDto commercial = commercialService.getCommercialByRole(role);

        if (commercial != null) {
            return new ResponseEntity<>(commercial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CommercialDto> createCommercial(@RequestBody CommercialDto commercialDto) {
        CommercialDto createdCommercial = commercialService.createCommercial(commercialDto);
        return new ResponseEntity<>(createdCommercial, HttpStatus.CREATED);
    }

    @PutMapping("/{commercialId}")
    public ResponseEntity<CommercialDto> updateCommercial(@PathVariable Long commercialId, @RequestBody CommercialDto updatedCommercialDto) {
        CommercialDto commercialDto = commercialService.updateCommercial(commercialId, updatedCommercialDto);
        return new ResponseEntity<>(commercialDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commercialId}")
    public ResponseEntity<Void> deleteCommercial(@PathVariable Long commercialId) {
        commercialService.deleteCommercial(commercialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}