package br.com.goteam.teammate.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@SequenceGenerator(initialValue = 1, allocationSize = 1,
        name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_USUARIO", strategy = GenerationType.SEQUENCE)
    private Long codigo;
    @NotNull
    @Length(min = 6, max = 60)
    @Column(nullable = false, length = 60)
    private String nome;
    @NotNull
    @Length(min = 6, max = 60)
    @Column(nullable = false, length = 60)
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Estado> listaEstado;

    public Usuario() {
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    
}
