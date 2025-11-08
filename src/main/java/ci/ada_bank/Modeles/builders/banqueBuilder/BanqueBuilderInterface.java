package ci.ada_bank.Modeles.builders.banqueBuilder;

import ci.ada_bank.Modeles.Banque;

public interface BanqueBuilderInterface {
    BanqueBuilderInterface setId(Long id);
    BanqueBuilderInterface setNom(String nom);
    BanqueBuilderInterface setPays (String pays);
    BanqueBuilderInterface setVille (String ville);
    BanqueBuilderInterface setDateCreation (String dateCreation);
    BanqueBuilderInterface setNombreClients (String nombreClients);
    BanqueBuilderInterface setNombreBanque(String nombreBanque);
    Banque build();
}
