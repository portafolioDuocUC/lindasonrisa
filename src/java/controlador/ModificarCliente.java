/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import modelo.ClienteADO;
import modelo.ClienteVO;

/**
 *
 * @author Diana
 */
public class ModificarCliente extends HttpServlet {

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

        String mensaje = "";

        try {
            ClienteADO clienteADO = new ClienteADO(ds.getConnection());
            String runCliente = request.getParameter("run");
            String dv = request.getParameter("dv");
            String nombre = request.getParameter("nombre");
            String paterno = request.getParameter("paterno");
            String materno = request.getParameter("materno");
            Date fechaNacimiento = Date.valueOf(request.getParameter("fecha"));
            String direccion = request.getParameter("direccion");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            String sexo = request.getParameter("sexo");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            int run = Integer.parseInt(runCliente);

            ClienteVO clienteVO = new ClienteVO();
            clienteVO.setDv(dv);
            clienteVO.setNombre(nombre);
            clienteVO.setApellidoPaterno(paterno);
            clienteVO.setApellidoMaterno(materno);
            clienteVO.setFechaNacimiento(fechaNacimiento);
            clienteVO.setDireccion(direccion);
            clienteVO.setTelefono(telefono);
            clienteVO.setSexo(sexo);
            clienteVO.setEmail(email);
            clienteVO.setTipoUsuario("Cliente");
            clienteVO.setUsuario(runCliente);
            clienteVO.setPassword(password);
            
            clienteADO.actualizar(clienteVO, run);
            mensaje = "* Cliente " + clienteVO.getRun() + "  modificado correctamente.";
            HttpSession session = request.getSession(true);
            session.setAttribute("clienteVO", clienteVO);

        } catch (SQLException ex) {
            mensaje = "* Error en la modificación del cliente.";
            Logger.getLogger(ModificarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("msg", mensaje);
        response.sendRedirect("listaCliente.jsp");
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
