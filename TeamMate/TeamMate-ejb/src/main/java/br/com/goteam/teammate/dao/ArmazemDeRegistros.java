package br.com.goteam.teammate.dao;

import br.com.goteam.teammate.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class ArmazemDeRegistros<T> {

    private final Class<T> classe;

    public ArmazemDeRegistros(Class<T> classe) {
        this.classe = classe;
    }

    public List<T> listaTodosOsRegistros() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> lista = entityManager.createQuery(query).getResultList();

        entityManager.close();
        return lista;
    }
    
    public T pegaORegistro() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        T objeto = entityManager.createQuery(query).getSingleResult();
        entityManager.close();
        return objeto;
    }
}
