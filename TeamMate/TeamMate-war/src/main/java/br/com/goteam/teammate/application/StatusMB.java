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

    private StatusView statusMercaderia = new StatusView();
    private List<Status> statusList;
    private List<Status> statusFiltered;
    private Status statusSelected;

    public StatusView getStatus() {
        return statusMercaderia;
    }

    public void setStatus(StatusView statusMercaderia) {
        this.statusMercaderia = statusMercaderia;
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
        Status novoStatus = this.statusMercaderia.construir();
        new AdicionaDAO<Status>().adiciona(novoStatus);
    }

    public void atualizar() {
        try {
            Status status = this.statusMercaderia.construir();
            statusExiste(true);
            clean();
        } catch (DadoInvalidoException die) {
            die.print();
        }
    }

    public void delete() {
        try {
            new RemoveDAO<Status>(Status.class).remove(statusSelected, statusSelected.getCodigo());
            statusList.remove(statusSelected);
            InfoMessage.print("Status '" + statusSelected.getDescricao()+ "' removido com sucesso!");
            clean();
        } catch (Exception ex) {
            FatalMessage.print(ex.getMessage(), ex.getCause());
        }
    }

    public void clean() {
        this.statusMercaderia = new StatusView();
        this.statusSelected = new Status();
    }

    public void confirmsUpdate() {
        if (statusList != null && statusList.contains(statusSelected)) {
            atualizar();
        } else {
            RequestContext.getCurrentInstance().execute("PF('statusAnadir').show()");
        }
    }

    public void selectStatus() {
        if (statusSelected != null) {
            this.statusMercaderia = new StatusView(statusSelected);
        }
    }

    public void statusExiste(boolean statusExiste) throws DadoInvalidoException {
        if (statusExiste) {
            for (Status status : statusList) {
                if (status.getDescricao().equalsIgnoreCase(this.statusMercaderia.getDescricao())
                        && status.getCodigo() != this.statusMercaderia.getCodigo()) {
                    throw new IDadoInvalidoException("¡Status ya existe!");
                }
            }
        } else {
            for (Status unidadMedidaLista : statusList) {
                if (unidadMedidaLista.getDescricao().equalsIgnoreCase(this.statusMercaderia.getDescricao())) {
                    throw new IDadoInvalidoException("¡Status ya existe!");
                }
            }
        }
    }

    @PostConstruct
    public void construct() {
        statusList = new ArmazemDeRegistros<Status>(Status.class).listaTodosOsRegistros();
    }

}
