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
import modelo.ProveedorADO;
import modelo.ProveedorVO;

/**
 *
 * @author Diana
 */
public class IngresarProveedor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Resource (mappedName="jdbc/lindasonrisaPool")
private DataSource ds;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           HttpSession session = request.getSession();
           int rut = Integer.parseInt(request.getParameter("rut"));
           String dv = request.getParameter("dv");
           String razonSocial= request.getParameter("razon");
           String direccion= request.getParameter("direccion");
           int telefono= Integer.parseInt(request.getParameter("telefono"));
           String email= request.getParameter("email");
           String rubro= request.getParameter("rubro");
           String tipoUsuario = "Proveedor";
           String usuario = request.getParameter("usuario");
           String password = request.getParameter("password");
           
           ProveedorVO proveedor = new ProveedorVO(rut,dv,razonSocial,direccion,
                                telefono,email,rubro,tipoUsuario,usuario,password);
           
           String mensaje = "";
           
           try {
               
               session.setAttribute("proveedor", proveedor);
               ProveedorADO proveedorDAO = new ProveedorADO(ds.getConnection());
               proveedorDAO.ingresar(proveedor);
            
            mensaje = "* Proveedor " + proveedor.getRazonSocial()+ "  ingresado correctamente";
        } catch (SQLException ex) {
            mensaje = "* Error en el ingreso del Proveedor.";
            Logger.getLogger(IngresarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        session.setAttribute("proveeedor", proveedor);
        request.setAttribute("msg", mensaje);
        request.getRequestDispatcher("proveedores.jsp").forward(request, response);   
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
