package org.licentaCRMPoliglot.Utilities;

import org.licentaCRMPoliglot.Entities.Achizitie.Achizitie;
import org.licentaCRMPoliglot.Entities.Achizitie.LinieAchizitie;
import org.licentaCRMPoliglot.Entities.Client.Client;
import org.licentaCRMPoliglot.Entities.ClientReferences.IstoricPuncte;
import org.licentaCRMPoliglot.Entities.ClientReferences.PuncteEntry;
import org.licentaCRMPoliglot.Entities.Oferta.Oferta;
import org.licentaCRMPoliglot.Entities.Oferta.StatusOferta;
import org.licentaCRMPoliglot.Entities.Oferta.Tipreducere;
import org.licentaCRMPoliglot.Entities.Produs.Produs;
import org.licentaCRMPoliglot.Repositories.OfertaRepository;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;
import org.licentaCRMPoliglot.Repositories.ClientRepository;
import org.licentaCRMPoliglot.Repositories.IstoricPuncteRepo;

import java.util.ArrayList;
import java.util.Date;

public class AchizitieUtil {

    public static void calculateValoareAchizitie(Achizitie achizitie, ProdusRepository produsRepository, OfertaRepository ofertaRepository) {
        double totalValue = 0.0;

        Oferta ofertaOpt = null;
        if (achizitie.getCodOferta() != null && !achizitie.getCodOferta().isEmpty()) {
            ofertaOpt = ofertaRepository.findById(achizitie.getCodOferta());
        }

        for (LinieAchizitie linie : achizitie.getLinieAchizitie()) {
            Produs produsOpt = produsRepository.findById(linie.getCodProdus());
            if (produsOpt != null) {
                Produs produs = produsOpt;
                double lineValue = produs.getPret() * linie.getCantitate();

                if (ofertaOpt != null) {
                    Oferta oferta = ofertaOpt;
                    if (oferta.getTipreducere() == Tipreducere.PRODUS && oferta.getStatus() == StatusOferta.ACTIVE) {
                        Produs ofertaProdus = oferta.getCodprodus();
                        if (ofertaProdus != null && ofertaProdus.getCodprodus().equals(linie.getCodProdus())) {
                            lineValue = 0;
                        }
                    }
                }
                linie.setPrice(lineValue);
                totalValue += lineValue;
            }
        }

        if (ofertaOpt != null) {
            Oferta oferta = ofertaOpt;
            if (oferta.getTipreducere() == Tipreducere.VOUCHER) {
                totalValue -= oferta.getValoarereducere();
            } else if (oferta.getTipreducere() == Tipreducere.PROCENT) {
                totalValue -= (totalValue * oferta.getValoarereducere() / 100);
            }
        }

        achizitie.setValoareAchizitie(String.valueOf(totalValue));
    }

    public static void calculateValoarePuncte(Achizitie achizitie, OfertaRepository ofertaRepository) {
        double valoareAchizitie = Double.parseDouble(achizitie.getValoareAchizitie());
        int valoarePuncte = (int) Math.floor(valoareAchizitie);

        if (achizitie.getCodOferta() != null && !achizitie.getCodOferta().isEmpty()) {
            Oferta ofertaOpt = ofertaRepository.findById(achizitie.getCodOferta());
            if (ofertaOpt != null) {
                Oferta oferta = ofertaOpt;
                if (oferta.getTipreducere() == Tipreducere.PRODUS) {
                    valoarePuncte -= oferta.getCostpuncte();
                }
            }
        }
        achizitie.setValoarePuncte(String.valueOf(valoarePuncte));
    }

    public static void updatePuncteClient(Achizitie achizitie, ClientRepository clientRepository, IstoricPuncteRepo istoricPuncteRepo) {
        String codClient = achizitie.getCodClient();
        Client optionalClient = clientRepository.findById(codClient);

        if (optionalClient != null) {
            Client client = optionalClient;
            int newPuncteLoialitate = client.getPuncteloialitate() + Integer.parseInt(achizitie.getValoarePuncte());
            client.setPuncteloialitate(newPuncteLoialitate);

            PuncteEntry entry = new PuncteEntry();
            entry.setCodAchizitie(achizitie.getCodAchizitie());
            entry.setValoarePuncte(Integer.parseInt(achizitie.getValoarePuncte()));
            entry.setDataProcesare(new Date());

            IstoricPuncte istoricPuncte = new IstoricPuncte();
            istoricPuncte.setCodClient(codClient);
            istoricPuncte.setPuncteEntries(new ArrayList<>());
            istoricPuncte.getPuncteEntries().add(entry);

            istoricPuncteRepo.save(istoricPuncte);
            clientRepository.updateClient(client);
            try {
                clientRepository.updateClient(client);
                istoricPuncteRepo.save(istoricPuncte);
                System.out.println("Updated client points and saved history for client: " + client.getCodclient());
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to update client points or save history: " + e.getMessage());
            }
        } else {
            System.out.println("Client not found with ID: " + codClient);
        }
    }
}
