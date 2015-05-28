package br.com.goteam.teammate.view;

import br.com.goteam.teammate.dao.AdicionaDAO;
import br.com.goteam.teammate.modelo.Estado;
import br.com.goteam.teammate.modelo.Solicitacao;
import br.com.goteam.teammate.modelo.Status;
import java.util.Arrays;
import java.util.Date;

public class NovaSolicitacaoView {
    
    private String assunto;
    private String descricao;
    private Date data = new Date();
    private Status status;
    private Integer grauConclusao = 0;
    
    public String getAssunto() {
        return assunto;
    }
    
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Date getData() {
        return data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Integer getGrauConclusao() {
        return grauConclusao;
    }
    
    public void setGrauConclusao(Integer grauConclusao) {
        this.grauConclusao = grauConclusao;
    }
    
    public Solicitacao contruir() {
        Estado estado = new Estado(null, status, null, data, data, grauConclusao, descricao, null);
        return new Solicitacao(null, assunto, data, Arrays.asList(estado));
        
    }
    
}
