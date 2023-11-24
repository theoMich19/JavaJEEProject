package com.nextu.mesdevis.service;

import com.nextu.mesdevis.dto.CommercialDto;
import com.nextu.mesdevis.entity.Category;
import com.nextu.mesdevis.entity.Commercial;
import com.nextu.mesdevis.entity.Devis;
import com.nextu.mesdevis.repository.CategoryRepository;
import com.nextu.mesdevis.repository.CommercialRepository;
import com.nextu.mesdevis.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



/**
 *  Services continent les méthodes qui effectuent des opérations complexes, manipulent les données,
 *  et interagissent avec la couche de persistance pour accéder ou mettre à jour des données.
 *  Ici nous sommes dans le service de Commercial
 */
@Service
public class CommercialService {

    @Autowired
    private CommercialRepository commercialRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DevisRepository devisRepository;

    // Service methods

    public List<CommercialDto> getAllCommercials() {
        try {
            List<Commercial> commercials = commercialRepository.findAll();
            return commercials.stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des commerciaux");
        }
    }

    public CommercialDto getCommercialById(Long commercialId) {
        try {
            Commercial commercial = commercialRepository.findById(commercialId)
                    .orElseThrow(() -> new RuntimeException("Commercial not found with id: " + commercialId));
            return convertToDto(commercial);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du commercial");
        }
    }

    public CommercialDto getCommercialByRole(String role) {
        try {
            Commercial commercial = commercialRepository.findByRole(role);
            if(commercial.getIdCommercial() != -1) {
                throw new RuntimeException("Commercial not found with role: " + role);
            }
            return convertToDto(commercial);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du commercial");
        }

    }

    public CommercialDto createCommercial(CommercialDto commercialDto) {
        try {
            Commercial commercial = convertToEntityCommercial(commercialDto);
            Commercial savedCommercial = commercialRepository.save(commercial);
            return convertToDto(savedCommercial);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du commercial");
        }
    }

    public CommercialDto updateCommercial(Long commercialId, CommercialDto updatedCommercialDto) {
        try {
            Commercial commercial = commercialRepository.findById(commercialId)
                        .orElseThrow(() -> new RuntimeException("Commercial not found with id: " + commercialId));

            if (commercial.getIdCommercial() != -1) {
                // Update the fields you want to allow updating

                commercial.setFirstName(updatedCommercialDto.getFirstName());
                commercial.setLastName(updatedCommercialDto.getLastName());
                commercial.setRole(updatedCommercialDto.getRole());
                commercial.setEmail(updatedCommercialDto.getEmail());

                if (updatedCommercialDto.getIdCategorys() != null && !updatedCommercialDto.getIdCategorys().isEmpty()) {
                    List<Category> newCategories = categoryRepository.findAllById(updatedCommercialDto.getIdCategorys());
                    commercial.getCategory().addAll(newCategories);
                }

                commercialRepository.save(commercial);
                return convertToDto(commercial);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du commerical");
        }
    }

    public void deleteCommercial(Long commercialId) {
        try {
            commercialRepository.deleteById(commercialId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du commercial");
        }
    }

    private CommercialDto convertToDto(Commercial commercial) {
        List<Long> idsCommercial = Optional.ofNullable(commercial.getCategory())
                .orElse(Collections.emptyList())
                .stream()
                .map(Category::getIdCategory)
                .collect(Collectors.toList());

        List<Long> idsDevis = Optional.ofNullable(commercial.getDevis())
                .orElse(Collections.emptyList())
                .stream()
                .map(Devis::getIdDevis)
                .collect(Collectors.toList());

        return new CommercialDto(
                commercial.getIdCommercial(),
                commercial.getFirstName(),
                commercial.getLastName(),
                commercial.getRole(),
                commercial.getEmail(),
                idsCommercial,
                idsDevis

        );
    }

    public Commercial convertToEntityCommercial(CommercialDto commercialDto) {
        Commercial commercial = new Commercial(
                commercialDto.getFirstName(),
                commercialDto.getLastName(),
                commercialDto.getRole(),
                commercialDto.getEmail()
        );

        if (commercialDto.getIdCategorys() != null && !commercialDto.getIdCategorys().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(commercialDto.getIdCategorys());
            commercial.setCategory(categories);
        }

        if (commercialDto.getIdDevis() != null && !commercialDto.getIdDevis().isEmpty()) {
            List<Devis> devis = devisRepository.findAllById(commercialDto.getIdDevis());
            commercial.setDevis(devis);
        }

        return commercial;
    }


}