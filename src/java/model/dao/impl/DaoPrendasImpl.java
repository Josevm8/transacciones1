package model.dao.impl;

import dto.Prendas;
import java.util.List;
import model.dao.DaoPrendas;
import service.sql.Sql;

public class DaoPrendasImpl implements DaoPrendas {

    private Sql sql;

    public DaoPrendasImpl() {
        sql = new Sql();
    }

    @Override
    public List<Object[]> combo() {
        String s =
                "SELECT idprenda,prenda FROM prendas";

        List<Object[]> list = sql.consulta(s);
        return list;
    }

    @Override
    public Prendas get(Integer idprenda) {
        String s =
                "SELECT "
                + "idprenda,"
                + "prenda,"
                + "stock "
                + "FROM prendas "
                + "WHERE idprenda=" + idprenda;

        Object[] fila = sql.getFila(s);
        Prendas p = null;
        if (fila != null) {
            p = new Prendas();

            p.setIdprenda((Integer) fila[0]);
            p.setPrenda((String) fila[1]);
            p.setStock((Integer) fila[2]);
        }

        return p;
    }
}
