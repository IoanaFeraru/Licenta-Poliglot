package org.licentaCRMPoliglot;

import org.licentaCRMPoliglot.Entities.Client.Client;
import org.licentaCRMPoliglot.Entities.Client.StatusMembru;
import org.licentaCRMPoliglot.Entities.ClientReferences.Adresa;
import org.licentaCRMPoliglot.Entities.ClientReferences.AddressDetail;
import org.licentaCRMPoliglot.Entities.ClientReferences.TaguriClient;
import org.licentaCRMPoliglot.Entities.ClientReferences.Enums.Judet;
import org.licentaCRMPoliglot.Entities.ClientReferences.WishList;
import org.licentaCRMPoliglot.Entities.ClientReferences.WishListItem;
import org.licentaCRMPoliglot.Repositories.ClientRepository;
import org.licentaCRMPoliglot.Repositories.AdresaRepo;
import org.licentaCRMPoliglot.Repositories.TaguriClientRepo;
import org.licentaCRMPoliglot.Repositories.WishListRepo;
import org.licentaCRMPoliglot.Entities.Produs.*;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ClientTest {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            ClientRepository clientRepository = new ClientRepository();
            AdresaRepo adresaRepo = container.select(AdresaRepo.class).get();
            TaguriClientRepo taguriClientRepo = container.select(TaguriClientRepo.class).get();
            WishListRepo wishListRepo = container.select(WishListRepo.class).get();
            ProdusRepository produsRepository = new ProdusRepository();

            List<String> availableProducts = getAllProductCodes(produsRepository);

            addMultipleClients(clientRepository, adresaRepo, taguriClientRepo, wishListRepo, 10, availableProducts);
        }
        System.exit(0);
    }

    public static void addMultipleClients(ClientRepository clientRepository, AdresaRepo adresaRepo, TaguriClientRepo taguriClientRepo, WishListRepo wishListRepo, int numberOfClients, List<String> availableProducts) {
        Random random = new Random();

        for (int i = 1; i <= numberOfClients; i++) {
            Client client = new Client();
            client.setCodclient("C" + i);
            client.setNume("Nume " + i);
            client.setPrenume("Prenume " + i);
            client.setEmail("client" + i + "@example.com");
            client.setDatanastere(LocalDate.now().minusYears(random.nextInt(30) + 18));
            client.setNumartelefon("07" + random.nextInt(100000000));
            client.setPuncteloialitate(random.nextInt(1000));
            client.setStatusmembru(StatusMembru.values()[random.nextInt(StatusMembru.values().length)]);
            client.setLastactive(new Date());

            try {
                clientRepository.addClient(client);
                System.out.println("Inserted client: " + client.getCodclient());

                int numberOfAdrese = 1 + random.nextInt(3);
                List<AddressDetail> addressDetails = new ArrayList<>();
                for (int j = 1; j <= numberOfAdrese; j++) {
                    AddressDetail addressDetail = new AddressDetail();
                    addressDetail.setJudet(Judet.values()[random.nextInt(Judet.values().length)]);
                    addressDetail.setCodPostal(1000 + random.nextInt(9000));
                    addressDetail.setStrada("Strada " + j);
                    addressDetail.setBloc("Bloc " + (char) (random.nextInt(26) + 'A'));
                    addressDetails.add(addressDetail);
                }

                Adresa adresa = new Adresa();
                adresa.setCodClient(client.getCodclient());
                adresa.setAddressDetails(addressDetails);

                try {
                    adresaRepo.save(adresa);
                    System.out.println("Inserted adresa for client: " + adresa.getCodClient());
                } catch (IllegalArgumentException e) {
                    System.out.println("Failed to insert adresa: " + e.getMessage());
                }

                TaguriClient taguriClient = new TaguriClient();
                taguriClient.setCodClient(client.getCodclient());
                List<String> tags = new ArrayList<>();
                int numberOfTaguri = 1 + random.nextInt(5);
                for (int k = 1; k <= numberOfTaguri; k++) {
                    tags.add("Tag" + k);
                }
                taguriClient.setTaguriClient(tags);

                try {
                    taguriClientRepo.save(taguriClient);
                    System.out.println("Inserted taguriClient for client: " + taguriClient.getCodClient() + " with tags: " + taguriClient.getTaguriClient());
                } catch (IllegalArgumentException e) {
                    System.out.println("Failed to insert taguriClient: " + e.getMessage());
                }

                List<WishListItem> wishListItems = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    WishListItem wishListItem = new WishListItem();
                    wishListItem.setCodProdus(availableProducts.get(random.nextInt(availableProducts.size())));
                    wishListItem.setDataAdaugare(new Date());
                    wishListItems.add(wishListItem);
                }

                WishList wishList = new WishList();
                wishList.setCodClient(client.getCodclient());
                wishList.setWishListItems(wishListItems);

                try {
                    wishListRepo.save(wishList);
                    System.out.println("Inserted wishlist for client: " + wishList.getCodClient());
                } catch (IllegalArgumentException e) {
                    System.out.println("Failed to insert wishlist: " + e.getMessage());
                }

            } catch (Exception e) {
                System.out.println("Failed to insert client: " + e.getMessage());
            }
        }
    }

    private static List<String> getAllProductCodes(ProdusRepository produsRepository) {
        List<Produs> produse = produsRepository.findAll();
        return produse.stream().map(Produs::getCodprodus).collect(Collectors.toList());
    }
}
