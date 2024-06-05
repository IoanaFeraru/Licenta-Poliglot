package org.licentaCRMPoliglot;

import org.licentaCRMPoliglot.Entities.Campanie.Campanie;
import org.licentaCRMPoliglot.Entities.Comunicare.Comunicare;
import org.licentaCRMPoliglot.Entities.Comunicare.Enums.Metoda;
import org.licentaCRMPoliglot.Entities.Comunicare.Enums.Status;
import org.licentaCRMPoliglot.Entities.Client.Client;
import org.licentaCRMPoliglot.Repositories.CampanieRepository;
import org.licentaCRMPoliglot.Repositories.ClientRepository;
import org.licentaCRMPoliglot.Repositories.ComunicareRepo;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComunicareTest {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            CampanieRepository campanieRepository = new CampanieRepository();
            ClientRepository clientRepository = new ClientRepository();
            ComunicareRepo comunicareRepo = container.select(ComunicareRepo.class).get();

            insertComunicari(campanieRepository, clientRepository, comunicareRepo, 3);
        }
        System.exit(0);
    }

    public static void insertComunicari(CampanieRepository campanieRepository, ClientRepository clientRepository, ComunicareRepo comunicareRepo, int numberOfComunicariPerCampanie) {
        List<Campanie> allCampanii = campanieRepository.findAll();
        List<Client> allClients = clientRepository.findAll();

        if (allClients.isEmpty()) {
            throw new IllegalStateException("No clients available in the database.");
        }

        Random random = new Random();
        int currentCodComunicare = 1;

        for (Campanie campanie : allCampanii) {
            for (int i = 0; i < numberOfComunicariPerCampanie; i++) {
                Comunicare comunicare = new Comunicare();
                comunicare.setCodComunicare(currentCodComunicare++);
                comunicare.setCodCampanie(campanie.getId());
                comunicare.setScop("Scop " + i);
                comunicare.setStatus(Status.values()[random.nextInt(Status.values().length)]);
                comunicare.setMetoda(Metoda.values()[random.nextInt(Metoda.values().length)]);

                List<String> coduriClienti = new ArrayList<>();
                int numberOfClients = 1 + random.nextInt(5);
                while (coduriClienti.size() < numberOfClients) {
                    String selectedClient = allClients.get(random.nextInt(allClients.size())).getCodclient();
                    if (!coduriClienti.contains(selectedClient)) {
                        coduriClienti.add(selectedClient);
                    }
                }
                comunicare.setCoduriCLienti(coduriClienti);

                try {
                    comunicareRepo.save(comunicare);
                    System.out.println("Inserted Comunicare for campanie: " + campanie.getNume() + " with clients: " + coduriClienti);
                } catch (IllegalArgumentException e) {
                    System.out.println("Failed to insert comunicare: " + e.getMessage());
                }
            }
        }
    }
}
