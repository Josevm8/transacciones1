<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>parainformaticos.com</title>
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/table.css">
        <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
    </head>
    <body>
        <h3 style="text-align:center;margin-top:20px;color: #7690bb">
            Compra/Venta de Prendas de Vestir
        </h3>

        <form action="Movimientos" method="post" onsubmit="return valida()">
            <input type="hidden" name="accion" value="transaccion"/>

            <table class="tindex">
                <tr>
                    <td style="vertical-align: top;text-align: center">
                        
                        <select name="idprenda" id="idprenda" 
                                style="margin-bottom: 5px">
                            <c:forEach var="p" items="${lprendas}">
                                <option value="${p[0]}">${p[1]}</option>
                            </c:forEach>
                        </select><br/>
                        
                        <span class="tipo">
                            <input type="radio" value="C" name="tipo"/>Compra
                            <input type="radio" value="V" name="tipo" style="margin-left: 22px"/>Venta
                        </span><br/>
                        
                        <input type="button" value="Ver Prendas" 
                               onclick="grilla()" style="margin-top: 20px"/>
                    </td>
                    <td style="vertical-align: top;padding-left: 4px">
                        <div class="check">
                            <table>
                                <c:forEach var="c" items="${lcolores}">
                                    <tr>
                                        <td>
                                            <input type="checkbox" 
                                                   name="idcolor" 
                                                   value="${c.idcolor}">
                                            ${c.color}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>

            <div id="grilla" style="margin: auto;width: 590px"></div>
        </form>
    </body>
</html>

