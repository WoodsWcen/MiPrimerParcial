
<%@page import="com.emergentes.parcial1.Producto"%>
<%@page import="java.util.ArrayList"%>
<%
    if (session.getAttribute("listaprodut") == null) {
            ArrayList<Producto> lisaux = new ArrayList<Producto>();
            session.setAttribute("listaproduct", lisaux);
        }
    ArrayList<Producto> lista = (ArrayList<Producto>) session.getAttribute("listaproduct");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> 1º parcial</title>
    </head>
    <div style="border: black 2px solid;border-radius: 20px;margin-right: 1500px;margin-left: 40px;">
        <table border="1">
            <tr>
                <label><h2 style="color:blue;">SEGUNDO PARCIAL TEM-742</h2></label>
                <label>Nombre: Freddy Armando Ajnota Tito</label><br>
                <a>Carnet: 6903361 LP.</a>               
            </tr>
        </table>

    </div>
    <body style="margin-left: 40px;">
        <h1>Gestión de productos</h1>
        <a href="MainServlet?op=nuevo">Nuevo producto</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripción</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoría</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if (lista != null) {
                        for (Producto item : lista) {
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getDescripcion() %></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getPrecio() %></td>
                <td><%= item.getCategoria() %></td>
                <td>
                    <a href="MainServlet?op=editar&id=<%=item.getId() %>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%=item.getId() %>" onclick="return (confirm('Esta seguro de eliminar??'))">Eliminar</a>
                </td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>

