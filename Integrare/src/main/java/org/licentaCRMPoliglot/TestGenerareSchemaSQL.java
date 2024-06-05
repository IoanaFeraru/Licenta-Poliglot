package org.licentaCRMPoliglot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

//ToDO Oferta (SQL)
//TODO Campanie (SQL)
//TODO Comunicare (NoSQL)
//TODO Achizitie (Nosql) -> generates Istoric puncte and feedback
public class TestGenerareSchemaSQL {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
    }
}
