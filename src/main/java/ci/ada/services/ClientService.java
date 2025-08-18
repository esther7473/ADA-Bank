package ci.ada.services;

import ci.ada.Repository.ClientRepository;
import ci.ada.entity.Client;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final EmailService emailService;

    // Créer un client
    public Client createClient(Client client) {
        try {
            client = clientRepository.save(client);
            log.info("Client created: {}", client.getName());
            emailService.sendWelcomeEmail(
                    client.getEmail(),
                    client.getName(),
                    client.getBank().getNom()
            );
            return client;
        } catch (Exception e) {
            log.error("Error creating client: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating client", e);
        }
    }

    // Mettre à jour un client par ID
    public void updateClientById(Long id, Client client) {
        try {
            Client existingClient = clientRepository.findById(id).orElse(null);
            if (existingClient != null) {
                existingClient.setName(client.getName());
                existingClient.setPrenom(client.getPrenom());
                existingClient.setEmail(client.getEmail());
                existingClient.setClientNumber(client.getClientNumber());
                existingClient.setBank(client.getBank());
                existingClient.setCreatedAt(client.getCreatedAt());
                clientRepository.save(existingClient);
                log.info("Client updated: {}", existingClient.getName());
            } else {
                log.warn("Client with ID {} not found", id);
            }
        } catch (Exception e) {
            log.error("Error updating client: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating client", e);
        }
    }

    // Trouver un client par ID
    public Client findClientById(Long id) {
        try {
            return clientRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error("Error finding client: {}", e.getMessage(), e);
            throw new RuntimeException("Error finding client", e);
        }
    }

    // Trouver tous les clients
    public List<Client> findAllClients() {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            log.error("Error finding clients: {}", e.getMessage(), e);
            throw new RuntimeException("Error finding clients", e);
        }
    }

    // Supprimer un client par ID
    public void deleteClientById(Long id) {
        try {
            clientRepository.deleteById(id);
            log.info("Client deleted with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting client: {}", e.getMessage(), e);
            throw new RuntimeException("Error deleting client", e);
        }
    }
}
