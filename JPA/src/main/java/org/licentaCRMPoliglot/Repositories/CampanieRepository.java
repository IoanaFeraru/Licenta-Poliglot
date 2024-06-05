package org.licentaCRMPoliglot.Repositories;

import org.licentaCRMPoliglot.Entities.Campanie.*;
import org.licentaCRMPoliglot.MetaModels.AbstractRepository;

public class CampanieRepository extends AbstractRepository<Campanie> {
    @Override
    protected Class<Campanie> getEntityClass() {
        return Campanie.class;
    }

    public Campanie findById(Integer id) {
        return getEm().find(Campanie.class, id);
    }

    public void addCampanie(Campanie campanie) {
        try {
            beginTransaction();
            create(campanie);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateCampanie(Campanie campanie) {
        try {
            beginTransaction();
            update(campanie);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteCampanie(Campanie campanie) {
        try {
            beginTransaction();
            delete(campanie);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}