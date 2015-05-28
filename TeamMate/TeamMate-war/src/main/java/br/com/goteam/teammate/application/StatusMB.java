package br.com.goteam.teammate.application;

import br.com.goteam.teammate.application.util.FatalMessage;
import br.com.goteam.teammate.application.util.InfoMessage;
import br.com.goteam.teammate.dao.AdicionaDAO;
import br.com.goteam.teammate.dao.ArmazemDeRegistros;
import br.com.goteam.teammate.dao.RemoveDAO;
import br.com.goteam.teammate.exception.DadoInvalidoException;
import br.com.goteam.teammate.exception.impl.IDadoInvalidoException;
import br.com.goteam.teammate.modelo.Status;
import br.com.goteam.teammate.view.StatusView;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class StatusMB implements Serializable {

    private StatusView status = new StatusView();
    private List<Status> statusList;
    private List<Status> statusFiltered;
    private Status statusSelected;

    public StatusView getStatus() {
        return status;
    }

    public void setStatus(StatusView status) {
        this.status = status;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public List<Status> getStatusFiltered() {
        return statusFiltered;
    }

    public void setStatusFiltered(List<Status> statusFiltered) {
        this.statusFiltered = statusFiltered;
    }

    public Status getStatusSelected() {
        return statusSelected;
    }

    public void setStatusSelected(Status statusSelected) {
        this.statusSelected = statusSelected;
    }

    public void adicionar() {
        try {
            Status novoStatus = this.status.construir();
            statusExiste(false);
            new AdicionaDAO<Status>().adiciona(novoStatus);
            limpar();
        } catch (DadoInvalidoException die) {
            die.print();
        }
    }

    public void atualizar() {
        try {
            Status status = this.status.construir();
            statusExiste(true);
            limpar();
        } catch (DadoInvalidoException die) {
            die.print();
        }
    }

    public void delete() {
        try {
            new RemoveDAO<Status>(Status.class).remove(statusSelected, statusSelected.getCodigo());
            statusList.remove(statusSelected);
            InfoMessage.print("Status '" + statusSelected.getDescricao() + "' removido com sucesso!");
            limpar();
        } catch (Exception ex) {
            FatalMessage.print(ex.getMessage(), ex.getCause());
        }
    }

    public void limpar() {
        this.status = new StatusView();
        this.statusSelected = new Status();
    }

    public void confirmsUpdate() {
        if (statusList != null && statusList.contains(statusSelected)) {
            atualizar();
        } else {
            RequestContext.getCurrentInstance().execute("PF('statusAdicionar').show()");
        }
    }

    public void selectStatus() {
        if (statusSelected != null) {
            this.status = new StatusView(statusSelected);
        }
    }

    public void statusExiste(boolean statusExiste) throws DadoInvalidoException {
        if (statusExiste) {
            for (Status status : statusList) {
                if (status.getDescricao().equalsIgnoreCase(this.status.getDescricao())
                        && status.getCodigo() != this.status.getCodigo()) {
                    throw new IDadoInvalidoException("Status já existe!");
                }
            }
        } else {
            for (Status unidadMedidaLista : statusList) {
                if (unidadMedidaLista.getDescricao().equalsIgnoreCase(this.status.getDescricao())) {
                    throw new IDadoInvalidoException("Status já existe!");
                }
            }
        }
    }

    @PostConstruct
    public void construct() {
        statusList = new ArmazemDeRegistros<Status>(Status.class).listaTodosOsRegistros();
    }

}
