package br.com.edrsantos.controlecombustivel.model;

import java.io.Serializable;

/**
 * Created by slimv on 6/16/2018.
 */

public class Abastecimento implements Serializable {

    private long id;
    private int codigo;
    private String cidade;
    private int quantidade;

    public long getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCidade() {
        return cidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
