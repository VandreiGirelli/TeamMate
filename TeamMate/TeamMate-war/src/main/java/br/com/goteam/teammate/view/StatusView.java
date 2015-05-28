package br.com.goteam.teammate.view;

import br.com.goteam.teammate.modelo.Status;

public class StatusView {

    private Long codigo;
    private String descricao;

    public StatusView() {
    }
    
    public StatusView(Status status) {
        codigo = status.getCodigo();
        descricao = status.getDescricao();
    }    
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status construir() {
        return new Status(codigo, descricao);
    }

}
