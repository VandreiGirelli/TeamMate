package br.com.goteam.teammate.application;

import br.com.goteam.teammate.dao.ArmazemDeRegistros;
import br.com.goteam.teammate.modelo.Solicitacao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DashboardMB {

    private List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();

    public List<Solicitacao> getListaSolicitacao() {
        return listaSolicitacao;
    }

    public void setListaSolicitacao(List<Solicitacao> listaSolicitacao) {
        this.listaSolicitacao = listaSolicitacao;
    }
    
    public String selecionaSolicitacao(){
        
        
        
        return "solicitacao?faces-redirect=true";
    }

    @PostConstruct
    public void construct() {
        listaSolicitacao = new ArmazemDeRegistros<Solicitacao>(Solicitacao.class).listaTodosOsRegistros();
    }

}
