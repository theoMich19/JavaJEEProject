package com.nextu.mesdevis.service;

import com.nextu.mesdevis.dto.DetailsDevisDto;
import com.nextu.mesdevis.dto.DevisDto;
import com.nextu.mesdevis.entity.Client;
import com.nextu.mesdevis.entity.DetailsDevis;
import com.nextu.mesdevis.entity.Devis;
import com.nextu.mesdevis.entity.Product;
import com.nextu.mesdevis.repository.DetailsDevisRepository;
import com.nextu.mesdevis.repository.DevisRepository;
import com.nextu.mesdevis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Services continent les méthodes qui effectuent des opérations complexes, manipulent les données,
 *  et interagissent avec la couche de persistance pour accéder ou mettre à jour des données.
 *  Ici nous sommes dans le service de DetailsService
 */
@Service
public class DetailsDevisService {

    @Autowired
    private DetailsDevisRepository detailsDevisRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private PriceService priceService;

    // Service methods

    public List<DetailsDevisDto> getAllDetailsDevis() {
        try {
            List<DetailsDevis> detailsDevis = detailsDevisRepository.findAll();
            return detailsDevis.stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des Details DEvis");
        }
    }

    public DetailsDevisDto getDetailsDevisById(Long detailsDevisId) {
        try {
            DetailsDevis detailsDevis = detailsDevisRepository.findById(detailsDevisId)
                    .orElseThrow(() -> new RuntimeException("DetailsDevis not found with id: " + detailsDevisId));
            return convertToDto(detailsDevis);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du Details du devis");
        }
    }

    public DetailsDevisDto createDetailsDevis(DetailsDevisDto detailsDevisDto) {
        try {
            DetailsDevis detailsDevis = convertToEntity(detailsDevisDto);
            float price = priceService.findProductPrice(detailsDevis.getProduct().getIdProduct());
            System.out.print(price);
            detailsDevis.setPrice(price);

            DetailsDevis saveDetailsDevis = detailsDevisRepository.save(detailsDevis);
            return convertToDto(saveDetailsDevis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la sauvegarde du DEtails Devis");
        }
    }

    public DetailsDevisDto updateDetailsDevis(Long detailsDevisId, DetailsDevisDto updatedDetailsDevis) {
        DetailsDevis detailsDevis = detailsDevisRepository.findById(detailsDevisId)
                .orElseThrow(() -> new RuntimeException("DetailsDevis not found with id: " + detailsDevisId));


        if (detailsDevis.getIdDetailsDevis() != -1) {
            detailsDevis.setPrice(updatedDetailsDevis.getPrice());
            detailsDevis.setInventory(updatedDetailsDevis.getInventory());

            detailsDevisRepository.save(detailsDevis);
            return convertToDto(detailsDevis);
        }
        return null;
    }

    public void deleteDetailsDevis(Long detailsDevisId) {
        try {
            detailsDevisRepository.deleteById(detailsDevisId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du Details du devis");
        }
    }



    private DetailsDevisDto convertToDto(DetailsDevis detailsDevis) {
        return new DetailsDevisDto(
                detailsDevis.getIdDetailsDevis(),
                detailsDevis.getPrice(),
                detailsDevis.getInventory(),
                detailsDevis.getDevis().getIdDevis(),
                detailsDevis.getProduct().getIdProduct()
        );
    }

    private DetailsDevis convertToEntity(DetailsDevisDto detailsDevisDto) {
        DetailsDevis detailsDevisConvert = new DetailsDevis(
                detailsDevisDto.getPrice(),
                detailsDevisDto.getInventory()
        );

        if (detailsDevisDto.getIdProduct() != 0) {
            Product product = productRepository.findById(detailsDevisDto.getIdProduct())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + detailsDevisDto.getIdProduct()));
            detailsDevisConvert.setProduct(product);
        }

        if (detailsDevisDto.getIdDevis() != 0) {
            Devis devis = devisRepository.findById(detailsDevisDto.getIdDevis())
                    .orElseThrow(() -> new RuntimeException("Devis not found with id: " + detailsDevisDto.getIdDevis()));
            detailsDevisConvert.setDevis(devis);
        }

        return detailsDevisConvert;

    }
}