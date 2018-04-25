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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import modelo.ServicioDAO;
import modelo.ServicioVO;

/**
 *
 * @author Diana
 */
public class AgregarServicio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Resource (mappedName = "jdbc/lindasonrisaPool")
    private DataSource ds;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //HttpSession session = request.getSession();

        String nombre = request.getParameter("nombre");
        String idServicio = request.getParameter("idServicio");
        ServicioVO servicio = new ServicioVO(nombre);
        String mensaje = "";
        
        try {
            ServicioDAO sDAO = new ServicioDAO(ds.getConnection());
            if(idServicio != null) { // Si el idServicio no es null, está modificando el servicio
            int id = Integer.parseInt(idServicio);
                sDAO.actualizar(new ServicioVO(id, nombre));
                mensaje = "* Servicio " + servicio.getNombre()+ " modificado correctamente";

            } else { // Si es null, agrega el servicio
            sDAO.agregar(servicio);
            mensaje = "* Servicio " + servicio.getNombre()+ " ingresado correctamente";
            }
            

        } catch (SQLException ex) {
            mensaje = "* Error en el ingreso del servicio.";
            Logger.getLogger(AgregarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("msg", mensaje);
        request.getRequestDispatcher("ListarServicios.do").forward(request, response);
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
