package service.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author parainformaticos.com
 */
public class Sql {

    ConectaDb ds = null;

    public Sql() {
        ds = new ConectaDb();
    }

    // ejecuta INS, DEL y UPD
    public String ejecuta(String sql) {
        String msg = null;
        Connection cn = ds.getConnection();

        try {
            Statement st = cn.createStatement();
            int ctos = st.executeUpdate(sql);

            if (ctos == 0) {
                msg = "0 filas afectadas!";
            }

            cn.close();

        } catch (SQLException e) {
            msg = e.getMessage();
        }

        return msg; // returna null si todo bien
    }

    // ejecuta SELECT
    public List<Object[]> consulta(String sql) {
        List<Object[]> list = null;

        Connection cn = ds.getConnection();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            int ctascols = rm.getColumnCount();

            list = new ArrayList<Object[]>();

            // toma las filas de la consulta
            while (rs.next()) {
                Object[] fila = new Object[ctascols];
                for (int i = 0; i < ctascols; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                list.add(fila);
            }

            cn.close();
            if (list.isEmpty()) {
                list = null;
            }
        } catch (SQLException e) {
        }

        return list; // returna null si falla
    }

    public Object[] getFila(String sql) {
        List<Object[]> list = consulta(sql);
        Object[] fila = null;

        if (list != null) {
            fila = (Object[]) list.get(0);
        }

        return fila;
    }

    public Object getCampo(String sql) {
        Object[] fila = getFila(sql);
        Object campo = null;

        if (fila != null) {
            campo = fila[0];
        }

        return campo;
    }
}
