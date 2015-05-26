package br.com.goteam.teammate.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(initialValue = 1, allocationSize = 1,
        name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_USUARIO", strategy = GenerationType.SEQUENCE)
    private Long codigo;
    private String nome;
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
