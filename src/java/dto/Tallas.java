package dto;

import java.io.Serializable;

public class Tallas implements Serializable {

    private Integer idtalla;
    private String talla;

    public Tallas() {
    }

    public Integer getIdtalla() {
        return idtalla;
    }

    public void setIdtalla(Integer idtalla) {
        this.idtalla = idtalla;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}

