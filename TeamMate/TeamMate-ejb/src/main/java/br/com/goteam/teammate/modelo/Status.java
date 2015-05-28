package br.com.goteam.teammate.modelo;

import br.com.goteam.teammate.services.CharacterType;
import br.com.goteam.teammate.valueobjects.CaseType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@SequenceGenerator(allocationSize = 1, initialValue = 1,
        name = "SEQ_STATUS", sequenceName = "SEQ_STATUS")
public class Status implements Serializable {

    @Id
    @GeneratedValue
    private Long codigo;
    @NotNull
    @Length(min = 6, max = 50)
    @CharacterType(value = CaseType.LETTER_SPACE)
    @Column(nullable = false, length = 50)
    private String descricao;
    @OneToMany(mappedBy = "status")
    private List<Estado> listaEstado;

    public Status() {
    }

    public Status(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public boolean equals(Object objeto) {
        if (objeto == null) {
            return false;
        }
        if (!(objeto instanceof Status)) {
            return false;
        }
        Status outro = (Status) objeto;
        if (this.codigo == null) {
            return false;
        }
        return this.codigo.equals(outro.codigo);
    }
    
}
