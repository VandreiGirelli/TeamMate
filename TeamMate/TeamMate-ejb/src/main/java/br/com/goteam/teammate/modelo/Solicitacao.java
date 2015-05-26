package br.com.goteam.teammate.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    @OneToMany(mappedBy = "solicitacao")
    private List<Estado> listaEstados;
    
}
