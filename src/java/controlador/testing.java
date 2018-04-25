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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.*;

/**
 *
 * @author Diana
 */
@WebServlet(name = "testing", urlPatterns = {"/testing"})
public class testing extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testing</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testing</h1>");
            
            try {
                FamiliaProductoDAO fmDAO = new FamiliaProductoDAO(ds.getConnection());
                TipoProductoDAO tpDAO = new TipoProductoDAO(ds.getConnection());
                ProductoDAO pDAO = new ProductoDAO(ds.getConnection());
                
                ClienteADO cDAO = new ClienteADO(ds.getConnection());
                out.println("Ingreso de Clientes");
                out.println(cDAO.ingresar(new ClienteVO(233, "K", "Diana", "Qiu", "FA", Date.valueOf("27/01/1980"), "laland", 2222, "Femenino", "diana@", "Cliente", "233", "123")));
                
                //out.println(pDAO.agregar(new ProductoVO("dentadura postiza removible" , 100, 107, 50, 10)));
                
               // out.println(tpDAO.buscar(104));
                
           // out.println("El resultado es... ");
           // out.println(fmDAO.agregar(new FamiliaProductoVO("Brackets")));
           
           //out.println(pDAO.actualizar(new ProductoVO(100,"Lalala", 100, 107, 20, 5)));
           
            //out.println(pDAO.eliminar(100));
            out.println(pDAO.buscarTodos());

            out.println("<br><br>La lista de PRODUCTOS es: ");
                for (ProductoVO p : pDAO.buscarTodos()) {
                    out.println("<br>"+p.getDescripcion()+"-"+
                                       p.getId()+"-"+
                                       p.getIdFamilia()+"-"+
                                       p.getIdTipo()+"-"+
                                       p.getStock()+"-"+
                                       p.getStockCritico()+"<br>");
                }
            

            //fmDAO.actualizar(new FamiliaProductoVO(102,"Anestesia"));
            
            //fmDAO.eliminar(103);
                
            out.println("<br><br>La lista de la familia es: ");
                for (FamiliaProductoVO fm : fmDAO.buscarTodos()) {
                    out.println("<br> ID: "+fm.getId()+" Nombre: "+fm.getNombre());
                }
                
//            ServicioDAO sDAO = new ServicioDAO(ds.getConnection());
//            out.println(sDAO.agregar(new ServicioVO("Endodoncia")));
                
            } catch (SQLException ex) {
                Logger.getLogger(testing.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("</body>");
            out.println("</html>");
        }
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
