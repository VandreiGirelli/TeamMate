package br.com.goteam.teammate.application;

import br.com.goteam.teammate.dao.AdicionaDAO;
import br.com.goteam.teammate.modelo.Solicitacao;
import br.com.goteam.teammate.view.NovaSolicitacaoView;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class NovaSolicitacaoMB {

    private NovaSolicitacaoView novaSolicitacao = new NovaSolicitacaoView();

    public NovaSolicitacaoView getNovaSolicitacao() {
        return novaSolicitacao;
    }

    public void setNovaSolicitacao(NovaSolicitacaoView novaSolicitacao) {
        this.novaSolicitacao = novaSolicitacao;
    }

    public String adicionar() {
        new AdicionaDAO<Solicitacao>().adiciona(novaSolicitacao.contruir());
        return "dashboard?faces-redirect=true";
    }

}
