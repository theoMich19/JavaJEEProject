package com.nextu.mesdevis.controller;

import com.nextu.mesdevis.dto.DetailsDevisDto;
import com.nextu.mesdevis.service.DetailsDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur qui responsable de la gestion des interactions entre le modèle et la vue.
 * Ici il s'agit du controller de detailsDevis
 * Il propopose plusieurs endpoint, get, post, push , delete
 */
@RestController
@RequestMapping("/detailsDevis")
public class DetailsDevisController {

    @Autowired
    private DetailsDevisService detailsDevisService;

    // Controller methods

    @GetMapping
    public ResponseEntity<List<DetailsDevisDto>> getAllDetailsDevis() {
        List<DetailsDevisDto> detailsDevis = detailsDevisService.getAllDetailsDevis();
        return new ResponseEntity<>(detailsDevis, HttpStatus.OK);
    }

    @GetMapping("/{detailsDevisId}")
    public ResponseEntity<DetailsDevisDto> getDetailsDevisById(@PathVariable Long detailsDevisId) {
        DetailsDevisDto detailsDevis = detailsDevisService.getDetailsDevisById(detailsDevisId);
        return new ResponseEntity<>(detailsDevis, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<DetailsDevisDto> createDetailsDevis(@RequestBody DetailsDevisDto detailsDevisDto) {
        DetailsDevisDto createdDetailsDevis = detailsDevisService.createDetailsDevis(detailsDevisDto);
        return new ResponseEntity<>(createdDetailsDevis, HttpStatus.CREATED);
    }

    @PutMapping("/{detailsDevisId}")
    public ResponseEntity<DetailsDevisDto> updateDetailsDevis(@PathVariable Long detailsDevisId, @RequestBody DetailsDevisDto updatedDetailsDevisDto) {
        DetailsDevisDto detailsdevisUpdate = detailsDevisService.updateDetailsDevis(detailsDevisId, updatedDetailsDevisDto);
        return new ResponseEntity<>(detailsdevisUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{detailsDevisId}")
    public ResponseEntity<Void> deleteDetailsDevis(@PathVariable Long detailsDevisId) {
        detailsDevisService.deleteDetailsDevis(detailsDevisId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
