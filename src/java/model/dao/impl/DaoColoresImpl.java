package model.dao.impl;

import dto.Colores;
import java.util.ArrayList;
import java.util.List;
import model.dao.DaoColores;
import service.sql.Sql;

public class DaoColoresImpl implements DaoColores {

    private Sql sql;

    public DaoColoresImpl() {
        sql = new Sql();
    }

    @Override
    public List<Colores> combo() {
        String s =
                "SELECT idcolor,color "
                + "FROM colores ORDER BY idcolor";

        List<Object[]> list1 = sql.consulta(s);
        List<Colores> list2 = new ArrayList<Colores>();

        for (Object[] fila : list1) {
            Colores c = new Colores();

            c.setIdcolor((Integer) fila[0]);
            c.setColor((String) fila[1]);

            list2.add(c);
        }

        return list2;
    }

    @Override
    public List<Colores> getColores(String ids) {
        String s =
                "SELECT idcolor,color "
                + "FROM colores WHERE idcolor IN(" + ids + ")";

        List<Object[]> list1 = sql.consulta(s);
        List<Colores> list2 = new ArrayList<Colores>();

        for (Object[] fila : list1) {
            Colores c = new Colores();

            c.setIdcolor((Integer) fila[0]);
            c.setColor((String) fila[1]);

            list2.add(c);
        }

        return list2;
    }
}
