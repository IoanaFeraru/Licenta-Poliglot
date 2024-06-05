package org.licentaCRMPoliglot.Repositories;


import org.licentaCRMPoliglot.Entities.Produs.*;
import org.licentaCRMPoliglot.MetaModels.AbstractRepository;

public class ProdusRepository extends AbstractRepository<Produs> {
    @Override
    protected Class<Produs> getEntityClass() {
        return Produs.class;
    }

    public Produs findById(String id) {
        return getEm().find(Produs.class, id);
    }

    public void addProdus(Produs produs) {
        try {
            beginTransaction();
            create(produs);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateProdus(Produs produs) {
        try {
            beginTransaction();
            update(produs);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteProdus(Produs produs) {
        try {
            beginTransaction();
            delete(produs);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}