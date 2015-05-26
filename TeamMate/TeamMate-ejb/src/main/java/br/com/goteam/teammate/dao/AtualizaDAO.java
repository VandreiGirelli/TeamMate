package br.com.goteam.teammate.dao;

import br.com.goteam.teammate.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Rafael-Pc
 */
public class AtualizaDAO<T> {

    private final Class<T> classe;
    private EntityManager em;

    public AtualizaDAO(Class<T> classe) {
        this.classe = classe;
    }

    public void atualiza(T t) throws ConstraintViolationException {

        try {
            // consegue a entity manager
            em = new JPAUtil().getEntityManager();

            // abre transacao
            em.getTransaction().begin();

            // persiste o objeto e log do mesmo
            em.merge(t);

            // commita a transacao
            em.getTransaction().commit();

        } catch (PersistenceException pe) {
            if (pe.getCause().getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException cve = (ConstraintViolationException) pe.getCause().getCause();
                throw new ConstraintViolationException(getMessage(cve), null, getConstraint(cve));
            }
        }finally{
            // fecha a entity manager
            em.close();
        }

    }

    private String getMessage(ConstraintViolationException cve) {
        String mensagemFormatada = String.valueOf(cve.getCause())
                .replaceFirst("\\(", "")
                .replaceFirst("\\)", "")
                .replaceAll("\\(", "\\'")
                .replaceAll("\\)", "\\'")
                .replaceAll("\\=", " com valor ")
                .replaceAll("Chave", "Campo");
        mensagemFormatada = mensagemFormatada.substring(mensagemFormatada.indexOf("Detalhe:") + 9, mensagemFormatada.length());
        return mensagemFormatada;
    }

    private String getConstraint(ConstraintViolationException cve) {
        String constraint = String.valueOf(cve.getCause());
        constraint = constraint.substring(constraint.indexOf("(") + 1, constraint.indexOf(")"));
        return constraint;
    }
}
