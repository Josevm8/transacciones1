package dto;

import java.io.Serializable;

public class Colores implements Serializable {

    private Integer idcolor;
    private String color;

    public Colores() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(Integer idcolor) {
        this.idcolor = idcolor;
    }
}

