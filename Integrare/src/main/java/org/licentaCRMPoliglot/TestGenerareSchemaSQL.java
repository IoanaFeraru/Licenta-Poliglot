package org.licentaCRMPoliglot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class TestGenerareSchemaSQL {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
    }
}
