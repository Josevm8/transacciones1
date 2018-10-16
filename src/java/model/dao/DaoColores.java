package model.dao;

import dto.Colores;
import java.util.List;

public interface DaoColores {

    public List<Colores> combo();
    
    public List<Colores> getColores(String ids);
}

