package model.dao.impl;

import dto.Tallas;
import java.util.ArrayList;
import java.util.List;
import model.dao.DaoTallas;
import service.sql.Sql;

public class DaoTallasImpl implements DaoTallas {

    private Sql sql;

    public DaoTallasImpl() {
        sql = new Sql();
    }

    @Override
    public List<Tallas> getTallas() {
        String s =
                "SELECT idtalla,talla FROM tallas ORDER BY idtalla";

        List<Object[]> list1 = sql.consulta(s);
        List<Tallas> list2 = new ArrayList<Tallas>();

        for (Object[] fila : list1) {
            Tallas t = new Tallas();

            t.setIdtalla((Integer) fila[0]);
            t.setTalla((String) fila[1]);

            list2.add(t);
        }

        return list2;
    }
}

