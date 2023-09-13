
package com.emergentes.parcial1.controlador;

import com.emergentes.parcial1.Producto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String op = request.getParameter("op");
                Producto objprod = new Producto();
                int id, pos;
                
                HttpSession ses = request.getSession();
                ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");
                
                switch (op) {
            case "nuevo":
                request.setAttribute("miobjprod", objprod);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "modificar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);

                objprod = lista.get(pos);
                request.setAttribute("miobjprod", objprod);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                //Eliminar el registro de la coleccion segun el id
                id = Integer.parseInt(request.getParameter("id"));
                //Averiguar la posición del elemento en la lista
                pos = buscarPorIndice(request, id);
                if (pos <= 0) {
                    lista.remove(pos);
                }
                request.setAttribute("listaprod", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int id = Integer.parseInt(request.getParameter("id"));
                HttpSession ses = request.getSession();
                ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");
                Producto objprod = new Producto();
                objprod.setId(id);
                objprod.setDescripcion(request.getParameter("descripcion"));
                objprod.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                objprod.setPrecio(Integer.parseInt(request.getParameter("precio")));
                objprod.setCategoria(request.getParameter("categoria"));
        
        if (id == 0) {
            int idNuevo = obtenerId(request);
            objprod.setId(idNuevo);
            lista.add(objprod);
        }
        else{
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objprod);
        }
        request.setAttribute("listaprod", lista);
        response.sendRedirect("index.jsp");
    }
    
     public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");
        
        int pos = -1;
        
        if (lista != null) {
            for (Producto ite : lista) {
                ++pos;
                if (ite.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }
    
    public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listaprod");        
        int idn = 0;
        for (Producto ite : lista) {
            idn = ite.getId();
        }
        return idn + 1;
    }
}
