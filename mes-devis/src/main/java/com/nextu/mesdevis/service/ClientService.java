package com.nextu.mesdevis.service;

import com.nextu.mesdevis.dto.ClientDto;
import com.nextu.mesdevis.entity.Client;
import com.nextu.mesdevis.entity.Devis;
import com.nextu.mesdevis.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


/**
 *  Services continent les méthodes qui effectuent des opérations complexes, manipulent les données,
 *  et interagissent avec la couche de persistance pour accéder ou mettre à jour des données.
 *  Ici nous sommes dans le service de Client
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Service methods

    /**
     * Function qui premet de récuprer l'ensemble des client
     * @return une list de clientDto
     */
    public List<ClientDto> getAllClients() {
        try {
            List<Client> clients = clientRepository.findAll();
            return clients.stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des clients");
        }
    }

    /**
     * Function qui premet de récuprer un client selon l'id passé en paramètres
     * @param clientId identification du client
     * @return un clientDto
     */
    public ClientDto getClientById(Long clientId) {
        try {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
            return convertToDto(client);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du client");
        }
    }

    /**
     * Function qui permet de créer un client selon les données passé en paramètres
     * @param clientDto données du client pour la création
     * @return le clientDto créer
     */
    public ClientDto createClient(ClientDto clientDto) {
        try {
            Client client = convertToEntity(clientDto);
            client.setRegistrationDate(LocalDate.now());
            Client savedClient = clientRepository.save(client);
            return convertToDto(savedClient);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du client");
        }
    }

    /**
     * Function pour mettre à jour le client en base de données
     * @param clientId identificaiton du client
     * @param updatedClientDto données du client que l'on souhaite mettre à jours
     * @return le clientdto mis à jours
     */
    public ClientDto updateClient(Long clientId, ClientDto updatedClientDto) {
        try {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Client not found with id: " + updatedClientDto));

            if (client.getIdClient() != -1) {
                // Update the fields you want to allow updating

                client.setFirstName(updatedClientDto.getFirstName());
                client.setLastName(updatedClientDto.getLastName());
                client.setEmail(updatedClientDto.getEmail());
                client.setPhone(updatedClientDto.getPhone());
                client.setAddress(updatedClientDto.getAddress());
                client.setRegistrationDate(updatedClientDto.getRegistrationDate());

                clientRepository.save(client);
                return convertToDto(client);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du client");
        }
    }

    /**
     * Function qui permet de supprimer un client en base de données selon son id
     * @param clientId identification du client
     */
    public void deleteClient(Long clientId) {
        try {
            clientRepository.deleteById(clientId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du client");
        }
    }

    /**
     * Function de convertion pour pouvoir manipuler un clientDto
     * @param client donnée du client
     * @return ClientDto que l'on souhaite manipuler
     */
    private ClientDto convertToDto(Client client) {
        List<Long> listIdDevis = null;
        if (client.getDevisList() != null) {
            listIdDevis = client.getDevisList()
                    .stream()
                    .map(Devis::getIdDevis)
                    .collect(Collectors.toList());
        }

        return new ClientDto(
            client.getIdClient(),
            client.getFirstName(),
            client.getLastName(),
            client.getEmail(),
            client.getPhone(),
            client.getAddress(),
            client.getRegistrationDate(),
            listIdDevis
        );
    }

    /**
     * Function de convertion pour pouvoir manipuler un client
     * @param clientDto  donnée du client
     * @return Client que l'on souhaite manipuler
     */
    private Client convertToEntity(ClientDto clientDto) {
        return new Client(
            clientDto.getFirstName(),
            clientDto.getLastName(),
            clientDto.getEmail(),
            clientDto.getPhone(),
            clientDto.getAddress(),
            clientDto.getRegistrationDate()
        );
    }
}
