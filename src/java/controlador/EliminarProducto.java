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
public class EliminarProducto extends HttpServlet {

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

        String idTipo = request.getParameter("idTipo");
        String idFamilia = request.getParameter("idFamilia");
        String idProducto = request.getParameter("idProducto");
        int id;
        String mensaje = "";

        try {
            if (idFamilia != null) {
                id = Integer.parseInt(idFamilia);
                
                FamiliaProductoDAO fmDAO = new FamiliaProductoDAO(ds.getConnection());
                fmDAO.eliminar(id);
                mensaje = "* Tipo de familia código "+id+" eliminada correctamente.";

            } else if (idTipo != null) {
                id = Integer.parseInt(idTipo);

                TipoProductoDAO tpDAO = new TipoProductoDAO(ds.getConnection());
                tpDAO.eliminar(id);
                mensaje = "* Tipo de producto código " +id+ " eliminado correctamente.";
                
                
            } else {
                id = Integer.parseInt(idProducto);

                ProductoDAO pDAO = new ProductoDAO(ds.getConnection());
                pDAO.eliminar(id);
                mensaje = "* Producto código "+ id +" eliminado correctamente.";
            }

        } catch (SQLException ex) {
            mensaje = "* Error. No se pudo eliminar el registro.";
            Logger.getLogger(EliminarProducto.class.getName()).log(Level.SEVERE, null, ex);
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
