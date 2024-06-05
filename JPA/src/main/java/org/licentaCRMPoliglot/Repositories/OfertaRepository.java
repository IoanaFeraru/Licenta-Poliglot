package org.licentaCRMPoliglot.Repositories;

import org.licentaCRMPoliglot.Entities.Oferta.*;
import org.licentaCRMPoliglot.MetaModels.AbstractRepository;

import java.util.List;

public class OfertaRepository extends AbstractRepository<Oferta> {
    @Override
    protected Class<Oferta> getEntityClass() {
        return Oferta.class;
    }

    public Oferta findById(String id) {
        return getEm().find(Oferta.class, id);
    }

    public void addOferta(Oferta oferta) {
        try {
            beginTransaction();
            create(oferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateOferta(Oferta oferta) {
        try {
            beginTransaction();
            update(oferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteOferta(Oferta oferta) {
        try {
            beginTransaction();
            delete(oferta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public List<Oferta> findByTipreducere(Tipreducere tipreducere) {
        return getEm().createNamedQuery("Oferta.findByTipreducere", Oferta.class)
                .setParameter("tipreducere", tipreducere)
                .getResultList();
    }
}