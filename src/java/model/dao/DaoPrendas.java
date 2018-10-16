package model.dao;

import dto.Prendas;
import java.util.List;


public interface DaoPrendas {

    public List<Object[]> combo();

    public Prendas get(Integer idprenda);
}

