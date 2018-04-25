package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import modelo.*;

/**
 *
 * @author Diana
 */
public class Autenticar extends HttpServlet {

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

        HttpSession session = request.getSession();

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String redirect = "";
        String mensaje = "";
        

        if ("admin".equals(usuario) && "duoc".equals(password)) {
            session.setAttribute("usuarioConectado", "Administrador");
            redirect = "index.jsp";
        } else {
            try {
                ClienteADO cADO = new ClienteADO(ds.getConnection());
                ProveedorADO pADO = new ProveedorADO(ds.getConnection());
                EmpleadoADO eADO = new EmpleadoADO(ds.getConnection());

                ProveedorVO proveedor = pADO.buscaCredenciales(usuario, password);
                ClienteVO cliente = cADO.buscarCredenciales(usuario, password);
                EmpleadoVO empleado = eADO.buscarCredenciales(usuario, password);

                if (proveedor != null) {
                    session.setAttribute("usuarioConectado", "Proveedor");
                    session.setAttribute("nombreUsuario", proveedor.getRazonSocial());
                    redirect = "indexProveedor.jsp";

                } else if (cliente != null) {
                    session.setAttribute("usuarioConectado", "Cliente");
                    session.setAttribute("nombreUsuario", cliente.getNombre()+" "+cliente.getApellidoPaterno());
                    redirect = "indexCliente.jsp";
                } else if (empleado != null) {
                    session.setAttribute("usuarioConectado", "Empleado");
                    session.setAttribute("nombreUsuario", empleado.getNombre()+" "+empleado.getaPaterno());
                    redirect = "indexEmpleado.jsp";
                } else {
                    redirect = "login.jsp";
                    mensaje = "*El usuario no existe, o las credenciales no son válidas.";
                }

            } catch (SQLException ex) {
                Logger.getLogger(Autenticar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("msg", mensaje);
        request.getRequestDispatcher(redirect).forward(request, response);
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
