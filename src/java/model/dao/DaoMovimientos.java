package model.dao;

import dto.Colores;
import dto.Movimientos;
import dto.Prendas;
import dto.Tallas;
import java.util.List;

public interface DaoMovimientos {

    // Inserta compras o ventas y actualiza stock de prenda
    public String transaccion(
            List<Movimientos> list, Integer idprenda, String tipo);

    // retorna stocks de una prenda por colores y tallas
    public List<Integer[]> stocks(
            List<Colores> colores, List<Tallas> tallas, Prendas prenda);
}

