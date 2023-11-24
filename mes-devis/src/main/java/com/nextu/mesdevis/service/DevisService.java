package com.nextu.mesdevis.service;

import com.nextu.mesdevis.dto.CommercialDto;
import com.nextu.mesdevis.dto.DetailsDevisDto;
import com.nextu.mesdevis.dto.DevisDto;
import com.nextu.mesdevis.entity.*;
import com.nextu.mesdevis.repository.ClientRepository;
import com.nextu.mesdevis.repository.CommercialRepository;
import com.nextu.mesdevis.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Services continent les méthodes qui effectuent des opérations complexes, manipulent les données,
 *  et interagissent avec la couche de persistance pour accéder ou mettre à jour des données.
 *  Ici nous sommes dans le service de Devis
 */
@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommercialRepository commercialRepository;

    @Autowired
    private CommercialService commercialService;

    // Service methods

    public List<DevisDto> getAllDevis() {
        try {
            List<Devis> listDevis = devisRepository.findAll();
            return listDevis.stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des devis");
        }
    }

    public DevisDto getDevisById(Long devisId) {
        try {
            Devis devis = devisRepository.findById(devisId)
                    .orElseThrow(() -> new RuntimeException("devis not found with id: " + devisId));
            return convertToDto(devis);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du devis");
        }
    }

    public List<DevisDto> getDevisByIds(List<Long> ids) {
        if(ids.isEmpty()) return null;

        try {
            List<Devis> devisList = devisRepository.findByIds(ids);
            if(devisList.isEmpty()) {
                throw new RuntimeException("devis not found with id: " + ids);
            }
            List<DevisDto> resultDevisList = new ArrayList<>();
            for ( Devis devis : devisList) {
               DevisDto newDevisDto = convertToDto(devis);
                resultDevisList.add(newDevisDto);
            }
            return resultDevisList;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du devis");
        }
    }

    public DevisDto createDevis(DevisDto devisDto) {
        try {
            Devis devis = convertToEntity(devisDto);
            devis.setCreationDate(LocalDate.now());
            //Commercial commercialResult = setCommercialDevis(saveDevis);
            //if(commercialResult != null) {
            //    saveDevis.setCommercial(commercialResult);
            //}
            Devis saveDevis = devisRepository.save(devis);

            return  convertToDto(saveDevis);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du devis");
        }
    }

    public DevisDto updateDevis(Long devisId, DevisDto updatedDevisDto) {
        try {
            Devis devis = devisRepository.findById(devisId)
                    .orElseThrow(() -> new RuntimeException("Devis not found with id: " + devisId));

            if (devis.getIdDevis() != -1) {
                // Update the fields you want to allow updating

                devis.setName(updatedDevisDto.getName());
                devis.setCancellationDate(updatedDevisDto.getCancellationDate());
                devis.setCreationDate(updatedDevisDto.getCreationDate());
                devis.setPaymentDate(updatedDevisDto.getPaymentDate());
                devis.setValidationDate(updatedDevisDto.getValidationDate());

                devisRepository.save(devis);

                return convertToDto(devis);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du devis");
        }
    }

    public void deleteDevis(Long devisId) {
        try {
            devisRepository.deleteById(devisId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du devis");
        }
    }


    public Commercial setCommercialDevis(Devis devisToSet) {
        try {
            List<Devis> listDevisFromRepo = devisRepository.findAll();
            List<CommercialDto> listCommercial = commercialService.getAllCommercials();
            long idCommercialMinus = 0;
            int count = 999999999;
            for (CommercialDto commercial : listCommercial) {

                // Filter Devis based on the specified criteria for the current commercial
                long devisCount = listDevisFromRepo.stream()
                        .filter(devis -> devis.getCommercial() != null && devis.getCommercial().getIdCommercial() != null && devis.getCommercial().getIdCommercial() == commercial.getIdCommercial())
                        .filter(devis -> devis.getValidationDate() != null)
                        .filter(devis -> devis.getCancellationDate() == null)
                        .filter(devis -> devis.getPaymentDate() == null)
                        .count();

                // Update the idCommercialMinus and count if a lower count is found
                if (devisCount < count) {
                    idCommercialMinus = commercial.getIdCommercial();
                    count = (int) devisCount;
                }


            }

            if(idCommercialMinus != 0) {
                CommercialDto commercialDto = commercialService.getCommercialById(idCommercialMinus);
                return commercialService.convertToEntityCommercial(commercialDto);
            } else {
                CommercialDto commercialDto = listCommercial.get(0);
                return commercialService.convertToEntityCommercial(commercialDto);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'attribution du commercial au devis");
        }
    }




    private DevisDto convertToDto(Devis devis) {
        long idClient = (devis.getClient() != null) ? devis.getClient().getIdClient() : null;

        long idCommercial = (devis.getCommercial() != null) ? devis.getCommercial().getIdCommercial() : null;


        List<DetailsDevisDto> detailsDevisList= new ArrayList<>();

        if (devis.getDetailsDevisList() != null) {
            detailsDevisList = devis.getDetailsDevisList()
                    .stream()
                    .map(this::convertToDtoDetailsDevis)
                    .collect(Collectors.toList());
        }

        return new DevisDto(
                devis.getIdDevis(),
                devis.getName(),
                devis.getCreationDate(),
                devis.getValidationDate(),
                devis.getPaymentDate(),
                devis.getCancellationDate(),
                idClient,
                idCommercial,
                detailsDevisList

        );
    }

    private DetailsDevisDto convertToDtoDetailsDevis(DetailsDevis detailsDevis) {
        return new DetailsDevisDto(
                detailsDevis.getIdDetailsDevis(),
                detailsDevis.getPrice(),
                detailsDevis.getInventory(),
                detailsDevis.getDevis().getIdDevis(),
                detailsDevis.getProduct().getIdProduct()
        );
    }


    private Devis convertToEntity(DevisDto devisDto) {
        Devis devisConvert = new Devis(devisDto.getName(),
                devisDto.getCreationDate(),
                devisDto.getValidationDate(),
                devisDto.getPaymentDate(),
                devisDto.getCancellationDate()
        );


        if (devisDto.getIdClient() != 0) {
            Client client = clientRepository.findById(devisDto.getIdClient())
                    .orElseThrow(() -> new RuntimeException("Client not found with id: " + devisDto.getIdClient()));
            devisConvert.setClient(client);
        }

        if (devisDto.getIdCommercial() != 0) {
            Commercial commercial = commercialRepository.findById(devisDto.getIdCommercial())
                    .orElseThrow(() -> new RuntimeException("Commercial not found with id: " + devisDto.getIdCommercial()));
            devisConvert.setCommercial(commercial);
        }

        return devisConvert;
    }
}