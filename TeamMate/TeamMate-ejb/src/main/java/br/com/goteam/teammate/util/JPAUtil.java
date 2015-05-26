package br.com.goteam.teammate.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    // @PersistenceContext(unitName="homero")
    private static EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("teammate");
    private static EntityManager manager;

    //Retorna o entity manager aberto ou cria um novo.
    public static EntityManager getEntityManager() {
        if (manager == null || !manager.isOpen()) {
            manager = entityManager.createEntityManager();
            return manager;
        } else {
            return manager;
        }
    }
}