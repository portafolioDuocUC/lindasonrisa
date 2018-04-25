/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.*;

/**
 *
 * @author Diana
 */
public class AgregarProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Resource(mappedName = "jdbc/lindasonrisaPool")
    private DataSource ds;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String opcion = request.getParameter("opcion");

        String mensaje = "";

        try {
            if ("familia".equals(opcion)) {
                FamiliaProductoVO familia = new FamiliaProductoVO(nombre);
                String idFamilia = request.getParameter("idFamilia");

                FamiliaProductoDAO fmDAO = new FamiliaProductoDAO(ds.getConnection());
                if (idFamilia != null) { // Modifica la familia
                    int id = Integer.parseInt(idFamilia);
                    fmDAO.actualizar(new FamiliaProductoVO(id, nombre));
                    mensaje = "* Familia " + familia.getNombre() + " modificado correctamente.";
                } else { // Agrega la familia
                    fmDAO.agregar(familia);
                    mensaje = "* Familia " + familia.getNombre() + " ingresada correctamente.";
                }

            } else if ("tipo".equals(opcion)) {
                TipoProductoVO tipo = new TipoProductoVO(nombre);
                String idTipo = request.getParameter("idTipo");

                TipoProductoDAO tpDAO = new TipoProductoDAO(ds.getConnection());
                if (idTipo != null) { // Modifica el servicio
                    int id = Integer.parseInt(idTipo);
                    tpDAO.actualizar(new TipoProductoVO(id, nombre));
                    mensaje = "* Tipo " + tipo.getNombre() + " modificado correctamente.";
                } else { // Agrega el servicio
                    tpDAO.agregar(tipo);
                    mensaje = "* Tipo " + tipo.getNombre() + " ingresado correctamente.";
                }
            } else {
                String idProducto = request.getParameter("idProducto");
                int selectFamilia = Integer.parseInt(request.getParameter("selectFamilia"));
                int selectTipo = Integer.parseInt(request.getParameter("selectTipo"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                int stockCritico = Integer.parseInt(request.getParameter("stockCritico"));
                String descripcion = request.getParameter("descripcionProducto");

                ProductoVO producto = new ProductoVO(descripcion, selectFamilia, selectTipo, stock, stockCritico);

                ProductoDAO pDAO = new ProductoDAO(ds.getConnection());
                if (idProducto != null) { // Modifica el producto
                    int id = Integer.parseInt(idProducto);
                    producto.setId(id);
                    pDAO.actualizar(producto);
                    mensaje = "* Producto código "+id+" modificado correctamente.";
                } else { // Agrega el producto
                    pDAO.agregar(producto);
                    mensaje = "* Producto ingresado correctamente.";
                }
            }
        } catch (SQLException ex) {
            mensaje = "* Error en el proceso.";
            Logger.getLogger(AgregarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("msg", mensaje);
        request.getRequestDispatcher("ListarProductos.do").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
