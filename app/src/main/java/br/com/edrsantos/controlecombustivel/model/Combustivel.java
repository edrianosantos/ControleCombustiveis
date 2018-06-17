package br.com.edrsantos.controlecombustivel.model;

import java.io.Serializable;

/**
 * Created by slimv on 6/16/2018.
 */

public class Combustivel implements Serializable {

    private long id;
    private int codigo;
    private String descricao;

    public long getId(){
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
