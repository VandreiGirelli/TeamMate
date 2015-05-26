package br.com.goteam.teammate.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(allocationSize = 1, initialValue = 1,
        name = "SEQ_STATUS", sequenceName = "SEQ_STATUS")
public class Status implements Serializable {

    @Id
    @GeneratedValue
    private Long codigo;
    private String descricao;
    @OneToMany(mappedBy = "status")
    private List<Estado> listaEstado;
}
