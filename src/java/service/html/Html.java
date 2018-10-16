package service.html;

import dto.Colores;
import dto.Prendas;
import dto.Tallas;
import java.util.List;

public class Html {

    public StringBuilder gCompra(
            List<Colores> colores, // colores seleccionados a comprar
            List<Tallas> tallas, // lista de tallas
            Prendas prenda) { // prenda elegida a comprar

        StringBuilder result = new StringBuilder();
        int ctastallas = tallas.size(); //tamaño de array 6
        int ctoscol = colores.size(); //

        // dibujando tabla HTML
        result.append("<table class=\"clasico\">");
        result.append("<caption>");
        result.append("Stock general de ");
        result.append(prenda.getPrenda());
        result.append(" es: ");
        result.append(prenda.getStock());
        result.append("</caption>");

        // titulos
        result.append("<thead>");
        result.append("<tr>");
        result.append("<td>Color</td>"); 
        for (int i = 0; i < ctastallas; i++) {  //tamaño de la cabecera dinamico
            //Tallas t = (Tallas) tallas.get(i); t= todo el registro 0, de la clase Talla
            result.append("<th>");
            result.append(tallas.get(i).getTalla());//insertando nombre d talla X 6
            result.append("</th>");
        }
        result.append("</tr>");
        result.append("</thead>");

        // pie de tabla
        result.append("<tfoot>");
        result.append("<tr>");
        result.append("<td colspan=\"");
        result.append(1 + ctastallas);
        result.append("\">");
        result.append("¡Para Inform&aacute;ticos facilita tu aprendizaje!");
        result.append("</td>");
        result.append("</tr>");
        result.append("</tfoot>");

        // data
        result.append("<tbody>");
        for (int fil = 0; fil < ctoscol; fil++) {
            Colores c = (Colores) colores.get(fil); //c = todo el registro 0 d la clase Colores 
            result.append("<tr>");
            result.append("<td>");
            result.append(c.getColor()); //se agrega el 1er color
            result.append("</td>");
            //agregando las cajas text
            // cajas de texto para compra por talla-color
            for (int i = 0; i < ctastallas; i++) {
                Tallas t = (Tallas) tallas.get(i);
                result.append("<th>");
                result.append("<input type=\"text\" value=\"0\" ");
                result.append("size=\"3\" maxlength=\"2\" name=\"caja\" ");
                result.append("onchange=\"copia('");
                result.append(c.getIdcolor()).append("_");
                result.append(t.getIdtalla());
                result.append("',this.value)\">");

                result.append("<input type=\"hidden\" name=\"cantidad\" ");
                result.append("id=\"");
                result.append(c.getIdcolor()).append("_");
                result.append(t.getIdtalla());
                result.append("\">");
                result.append("</th>");
            }
            result.append("</tr>");
        }
        result.append("</tbody>");
        result.append("</table>");
        result.append("<p style=\"text-align:center\">");
        result.append("<input type=\"submit\" "
                + "value=\"Ejecutar Transacci&oacute;n\">");
        result.append("</p>");

        return result;
    }

    public StringBuilder gVenta(
            List<Colores> colores, // colores seleccionados a comprar
            List<Tallas> tallas, // lista de tallas
            Prendas prenda, // prenda elegida a comprar
            List<Integer[]> stock) { // stocks de prenda por color/talla

        StringBuilder result = new StringBuilder();
        int ctastallas = tallas.size();
        int ctoscol = colores.size();

        // dibujando tabla HTML
        result.append("<table class=\"clasico\">");
        result.append("<caption>");
        result.append("Stock general de ");
        result.append(prenda.getPrenda());
        result.append(" es: ");
        result.append(prenda.getStock());
        result.append("</caption>");

        // titulos
        result.append("<thead>");
        result.append("<tr>");
        result.append("<td>Color</td>");
        for (int i = 0; i < ctastallas; i++) {
            Tallas t = (Tallas) tallas.get(i);
            result.append("<th>");
            result.append(t.getTalla());
            result.append("</th>");
        }
        result.append("</tr>");
        result.append("</thead>");

        // pie de tabla
        result.append("<tfoot>");
        result.append("<tr>");
        result.append("<td colspan=\"");
        result.append(1 + ctastallas);
        result.append("\">");
        result.append("¡Para Inform&aacute;ticos facilita tu aprendizaje!");
        result.append("</td>");
        result.append("</tr>");
        result.append("</tfoot>");

        // data
        result.append("<tbody>");
        for (int fil = 0; fil < ctoscol; fil++) {
            Colores c = (Colores) colores.get(fil);
            result.append("<tr>");
            result.append("<td>");
            result.append(c.getColor());
            result.append("</td>");

            // combos para vender por talla-color
            Integer[] fila = (Integer[]) stock.get(fil);
            for (int i = 0; i < ctastallas; i++) {
                int cantidad = fila[i].intValue();

                Tallas t = (Tallas) tallas.get(i);
                result.append("<th>");
                if (cantidad == 0) { // no hay stock
                    result.append("0");
                } else { // hay stock
                    result.append("<select name=\"caja\" ");
                    result.append("onchange=\"copia('");
                    result.append(c.getIdcolor()).append("_");
                    result.append(t.getIdtalla());
                    result.append("',this.options[this.selectedIndex].value)\">");
                    for (int j = 0; j <= cantidad; j++) {
                        result.append("<option value=\"");
                        result.append(j).append("\">");
                        result.append(j).append("</option>");
                    }
                    result.append("</select>");

                    result.append("<input type=\"hidden\" name=\"cantidad\" ");
                    result.append("id=\"");
                    result.append(c.getIdcolor()).append("_");
                    result.append(t.getIdtalla());
                    result.append("\">");
                }
                result.append("</th>");
            }
            result.append("</tr>");
        }
        result.append("</tbody>");
        result.append("</table>");
        result.append("<p style=\"text-align:center\">");
        result.append("<input type=\"submit\" "
                + "value=\"Ejecutar Transacci&oacute;n\">");
        result.append("</p>");

        return result;
    }
}
