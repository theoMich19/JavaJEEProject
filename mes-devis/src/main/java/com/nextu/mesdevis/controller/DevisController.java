package com.nextu.mesdevis.controller;

import com.nextu.mesdevis.dto.ClientDto;
import com.nextu.mesdevis.dto.DevisDto;
import com.nextu.mesdevis.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur qui responsable de la gestion des interactions entre le modèle et la vue.
 * Ici il s'agit du controller de devis
 * Il propopose plusieurs endpoint, get, post, push , delete
 */
@RestController
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    // Controller methods

    @GetMapping
    public ResponseEntity<List<DevisDto>> getAllDevis() {
        List<DevisDto> devis =  devisService.getAllDevis();
        return new ResponseEntity<>(devis, HttpStatus.OK);

    }

    @GetMapping("/{devisId}")
    public ResponseEntity<DevisDto> getDevisById(@PathVariable Long devisId) {
        DevisDto devis = devisService.getDevisById(devisId);
        return new ResponseEntity<>(devis, HttpStatus.OK);
    }

    @GetMapping("/byIds")
    public ResponseEntity<List<DevisDto>> getDevisByIds(@RequestParam List<Long> ids) {
        List<DevisDto> devisList = devisService.getDevisByIds(ids);
        return new ResponseEntity<>(devisList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DevisDto> createDevis(@RequestBody DevisDto devis) {
        DevisDto createdDevis = devisService.createDevis(devis);
        return new ResponseEntity<>(createdDevis, HttpStatus.CREATED);
    }

    @PutMapping("/{devisId}")
    public ResponseEntity<DevisDto> updateDevis(@PathVariable Long devisId, @RequestBody DevisDto updatedDevis) {
        DevisDto updateDevis = devisService.updateDevis(devisId, updatedDevis);
        return new ResponseEntity<>(updateDevis,HttpStatus.OK);
    }


    @DeleteMapping("/{devisId}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long devisId) {
        devisService.deleteDevis(devisId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}