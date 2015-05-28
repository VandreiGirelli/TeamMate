package br.com.goteam.teammate.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "SEQ_SOLICITACAO",
        sequenceName = "SEQ_SOLICITACAO")
public class Solicitacao implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_SOLICITACAO", strategy = GenerationType.SEQUENCE)
    private Long codigo;
    private String assunto;
    private Date data;
    @OneToMany(mappedBy = "solicitacao", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Estado> listaEstados;

    public Solicitacao() {
    }

    public Solicitacao(Long codigo, String assunto, Date data, List<Estado> listaEstados) {
        this.codigo = codigo;
        this.assunto = assunto;
        this.data = data;
        this.listaEstados = listaEstados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getAssunto() {
        return assunto;
    }

    public Date getData() {
        return data;
    }
    
    @Override
    public boolean equals(Object objeto) {
        if (objeto == null) {
            return false;
        }
        if (!(objeto instanceof Solicitacao)) {
            return false;
        }
        Solicitacao outro = (Solicitacao) objeto;
        if (this.codigo == null) {
            return false;
        }
        return this.codigo.equals(outro.codigo);
    }

}
