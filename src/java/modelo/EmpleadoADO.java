/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chasc
 */
public class EmpleadoADO {
    
    private Connection cnx;
    public EmpleadoADO(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public EmpleadoVO ingresar(EmpleadoVO empleado) {
        String sql = "insert into lindasonrisa.empleado (run, dv, nombre, paterno, materno, "
                + "fechaNacimiento, fechaContrato, direccion, telefono, sexo, email,"
                + "tipoUsuario,usuario,contrasena) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, empleado.getRun());
            pstm.setString(2, empleado.getDv());
            pstm.setString(3, empleado.getNombre());
            pstm.setString(4, empleado.getaPaterno() );
            pstm.setString(5, empleado.getaMaterno());
            pstm.setDate(6, empleado.getFechaNacimiento());
            pstm.setDate(7, empleado.getFechaContrato());
            pstm.setString(8, empleado.getDireccion());
            pstm.setInt(9, empleado.getTelefono());
            pstm.setString(10, empleado.getSexo());
            pstm.setString(11, empleado.getEmail());
            pstm.setString(12, empleado.getTipoUsuario());
            pstm.setString(13, empleado.getUsuario());
            pstm.setString(14, empleado.getPassword());
            
            pstm.executeUpdate();                 
                                   
            return empleado;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int eliminar(int run)
    {
        String sql = "delete from lindasonrisa.empleado where run = ?";
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, run);
            int c = pstm.executeUpdate();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoADO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int actualizar(EmpleadoVO empleado, int run)
    {
        String sql ="update lindasonrisa.empleado set dv=?,nombre=?, paterno=?, materno=?, "
                + "fechaNacimiento=?, fechaContrato=?, direccion=?, telefono=?, "
                + "sexo=?, email=?, tipoUsuario=?, usuario=?, contrasena=?  where run=?";
        
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setString(1, empleado.getDv());
            pstm.setString(2, empleado.getNombre());
            pstm.setString(3, empleado.getaPaterno());
            pstm.setString(4, empleado.getaMaterno());
            pstm.setDate(5, empleado.getFechaNacimiento());
            pstm.setDate(6, empleado.getFechaContrato());
            pstm.setString(7, empleado.getDireccion());
            pstm.setInt(8, empleado.getTelefono());
            pstm.setString(9, empleado.getSexo());
            pstm.setString(10, empleado.getEmail());
            pstm.setString(11, empleado.getTipoUsuario());
            pstm.setString(12, empleado.getUsuario());
            pstm.setString(13, empleado.getPassword());
            pstm.setInt(14, run);
            int c = pstm.executeUpdate();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoADO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public EmpleadoVO buscar(int run)
    {
        String sql ="select * from lindasonrisa.empleado where run=?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, run);
            rs=pstm.executeQuery();
            rs.next();
            EmpleadoVO empleado = new EmpleadoVO(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),
                    rs.getString(8),rs.getInt(9),rs.getString(10),rs.getString(11),rs.getString(12),
                    rs.getString(13),rs.getString(14));
            rs.close();
            pstm.close();
            
            
            
            return empleado;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public EmpleadoVO buscarCredenciales(String usuario, String password)
    {
        String sql ="select * from lindasonrisa.empleado where usuario=? AND contrasena=?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = this.cnx.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, password);
            rs=pstm.executeQuery();
            rs.next();
            EmpleadoVO empleado = new EmpleadoVO(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),
                    rs.getString(8),rs.getInt(9),rs.getString(10),rs.getString(11),rs.getString(12),
                    rs.getString(13),rs.getString(14));
            rs.close();
            pstm.close();
            
                       
            return empleado;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ArrayList<EmpleadoVO> buscarTodos()
    {
        ArrayList<EmpleadoVO> temp = new ArrayList<EmpleadoVO>();
        String sql ="select * from lindasonrisa.empleado";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = this.cnx.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next())
            {                
                temp.add(new EmpleadoVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getString(8),rs.getInt(9),rs.getString(10),
                        rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
            stm.close();
            return temp;           
            
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
