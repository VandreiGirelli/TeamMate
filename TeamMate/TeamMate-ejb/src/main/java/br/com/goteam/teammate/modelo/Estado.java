package br.com.goteam.teammate.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(allocationSize = 1, initialValue = 1,
        name = "SEQ_ESTADO", sequenceName = "SEQ_ESTADO")
public class Estado implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_ESTADO", strategy = GenerationType.SEQUENCE)
    private Long codigo;
    @ManyToOne
    private Status status;
    @ManyToOne
    private Usuario usuario;
    private Date dataInicial;
    private Date dataFinal;
    private Integer grauDeConclusao;
    private String descricao;
    @ManyToOne
    private Solicitacao solicitacao;
}
