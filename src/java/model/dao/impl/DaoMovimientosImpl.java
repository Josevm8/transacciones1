package model.dao.impl;

import dto.Colores;
import dto.Movimientos;
import dto.Prendas;
import dto.Tallas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.DaoMovimientos;
import service.sql.ConectaDb;

public class DaoMovimientosImpl implements DaoMovimientos {

    @Override
    public String transaccion(
            List<Movimientos> list, // color, talla, cantidad
            Integer idprenda,
            String tipo) { // C==compra, V==venta

        String result = null;
        ConectaDb db = new ConectaDb();
        Connection cn = db.getConnection();

        if (cn == null) {
            result = "Sin acceso a base de datos";
        } else {
            try {
                cn.setAutoCommit(false);
                // insertando compras
                String s =
                        "INSERT INTO movimientos("
                        + "idprenda,idcolor,idtalla,cantidad) "
                        + "VALUES(?,?,?,?)";
                PreparedStatement ps = cn.prepareStatement(s);
                int stock = 0;
                for (Movimientos m : list) {
                    ps.setInt(1, idprenda);
                    ps.setInt(2, m.getIdcolor());
                    ps.setInt(3, m.getIdtalla());
                    int cantidad =
                            (tipo.equals("C")
                            ? m.getCantidad() // si compra es positivo
                            : -m.getCantidad()); // si vende es negativo
                    ps.setInt(4, cantidad);

                    ps.execute();
                    stock += m.getCantidad();
                }
                ps.close();

                // actualizando stock de prenda
                String signo = (tipo.equals("C") ? "+" : "-");
                s = "UPDATE prendas SET stock=stock" + signo + "? "
                        + "WHERE idprenda=?";
                ps = cn.prepareStatement(s);
                ps.setInt(1, stock);
                ps.setInt(2, idprenda);
                ps.execute();
                ps.close();
                // ---
                cn.commit();

            } catch (SQLException e) {
                result = e.getMessage();
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                }
            } finally {
                try {
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }

        return result;
    }

    @Override
    public List<Integer[]> stocks(
            List<Colores> colores,
            List<Tallas> tallas,
            Prendas prenda) {

        List<Integer[]> list = new ArrayList<Integer[]>();

        ConectaDb db = new ConectaDb();
        Connection cn = db.getConnection();

        if (cn != null) {
            try {
                String s = "SELECT IFNULL(SUM(cantidad), 0) "
                        + "FROM movimientos "
                        + "WHERE (idprenda=?) "
                        + "AND (idcolor=?) "
                        + "AND (idtalla=?)";
                
                PreparedStatement ps = cn.prepareStatement(s);
                for (Colores c : colores) {
                    Integer[] fila = new Integer[tallas.size()];

                    int col = 0;
                    for (Tallas t : tallas) {
                        ps.setInt(1, prenda.getIdprenda());
                        ps.setInt(2, c.getIdcolor());
                        ps.setInt(3, t.getIdtalla());

                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            fila[col] = rs.getInt(1);
                            ++col;
                        }
                    }
                    list.add(fila);
                }
                ps.close();

            } catch (SQLException e) {
                list = null;
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    list = null;
                }
            }
        }

        return list;
    }
}
