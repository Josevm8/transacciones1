package web.servlet;

import dto.Colores;
import dto.Movimientos;
import dto.Prendas;
import dto.Tallas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DaoColores;
import model.dao.DaoMovimientos;
import model.dao.DaoPrendas;
import model.dao.DaoTallas;
import model.dao.impl.DaoColoresImpl;
import model.dao.impl.DaoMovimientosImpl;
import model.dao.impl.DaoPrendasImpl;
import model.dao.impl.DaoTallasImpl;
import service.html.Html;

@WebServlet(name = "ServletMovimientos", urlPatterns = {"/Movimientos"})
public class ServletMovimientos extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if (accion == null) {
            request.getSession().setAttribute("msg", "Solicitud no recibida");
            response.sendRedirect("mensaje.jsp");

        } else if (accion.equals("inicio")) {
            DaoPrendas daoPrendas = new DaoPrendasImpl();
            DaoColores daoColores = new DaoColoresImpl();

            request.getSession().setAttribute("lprendas", daoPrendas.combo()); //idcolor,color (lista)
            request.getSession().setAttribute("lcolores", daoColores.combo());

            response.sendRedirect("inicio.jsp");

        } else if (accion.equals("grilla")) {
            String ids = request.getParameter("idcolor");
            String tipo = request.getParameter("tipo");
            String id = request.getParameter("idprenda");
            Integer idprenda = Integer.valueOf(id);

            DaoColores daoColores = new DaoColoresImpl(); 
            DaoPrendas daoPrendas = new DaoPrendasImpl(); 
            DaoTallas daoTallas = new DaoTallasImpl();

            List<Colores> colores = daoColores.getColores(ids); //dev lista d los colores selec idcolor,color
            Prendas prendas = daoPrendas.get(idprenda);  //dev idprenda,prenda,stock
            List<Tallas> tallas = daoTallas.getTallas(); //dev lista de 6 registros idtalla,talla

            Html html = new Html();
            StringBuilder result;
            if (tipo.equals("C")) {
                result = html.gCompra(colores, tallas, prendas); //devuelve la tabla para C 
            } 
            else if (tipo.equals("V")) {
                DaoMovimientos daoMovimientos = new DaoMovimientosImpl();
                List<Integer[]> stock =
                        daoMovimientos.stocks(colores, tallas, prendas);
                result = html.gVenta(colores, tallas, prendas, stock);
            } 
            else {
                result = new StringBuilder();
            }

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(result);
            out.close();

        } 
        else if (accion.equals("transaccion")) {
            String[] cantidad = request.getParameterValues("cantidad");
            String tipo = request.getParameter("tipo");
            String idprenda = request.getParameter("idprenda");

            List<Movimientos> list = new ArrayList<Movimientos>();
            for (String c : cantidad) {
                if (c.length() > 0) {
                    String[] data = c.split("_");

                    Movimientos m = new Movimientos();
                    m.setIdcolor(Integer.valueOf(data[0]));
                    m.setIdtalla(Integer.valueOf(data[1]));
                    m.setCantidad(Integer.valueOf(data[2]));

                    list.add(m);
                }
            }

            String result;
            if (list.size() > 0) {
                DaoMovimientos daoMovimientos = new DaoMovimientosImpl();
                result = daoMovimientos.transaccion(list,
                        Integer.valueOf(idprenda), tipo);
            } else {
                result = "Sin cantidades seleccionadas";
            }

            if (result == null) {
                response.sendRedirect("index.jsp");
            } else {
                request.getSession().setAttribute("msg", result);
                response.sendRedirect("mensaje.jsp");
            }

        } 
        else {
            request.getSession().setAttribute("msg", "Solicitud no conocida");
            response.sendRedirect("mensaje.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="doGet y doPost.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

