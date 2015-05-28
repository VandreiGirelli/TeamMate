package br.com.goteam.teammate.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;
    @NotNull
    @Min(message = "{}", value = 0)
    @Max(message = "{}", value = 100)
    @Column(nullable = false)
    private Integer grauDeConclusao;
    @NotNull(message = "{}")
    @Length(min = 4, max = 1000, message = "{}")
    @Column(nullable = false, length = 1000)
    private String descricao;
    @ManyToOne
    private Solicitacao solicitacao;

    public Estado() {
    }

    public Estado(Long codigo, Status status, Usuario usuario, Date dataInicial,
            Date dataFinal, Integer grauDeConclusao, String descricao, Solicitacao solicitacao) {
        this.codigo = codigo;
        this.status = status;
        this.usuario = usuario;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.grauDeConclusao = grauDeConclusao;
        this.descricao = descricao;
        this.solicitacao = solicitacao;
    }

}
