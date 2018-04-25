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
public class ProveedorADO {
    
    private Connection cnx;
    public ProveedorADO(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public boolean ingresar(ProveedorVO proveedor) {
        String sql = "insert into lindasonrisa.proveedor (rut, dv, razonSocial, direccion, telefono, email,"
                   + " rubro, tipoUsuario, usuario, contrasena) values (?,?,?,?,?,?,?,?,?,?)";
                
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, proveedor.getRut());
            pstm.setString(2, proveedor.getDv());
            pstm.setString(3, proveedor.getRazonSocial());
            pstm.setString(4, proveedor.getDireccion());
            pstm.setInt(5, proveedor.getTelefono());
            pstm.setString(6, proveedor.getEmail());
            pstm.setString(7, proveedor.getRubro());
            pstm.setString(8, proveedor.getTipoUsuario());
            pstm.setString(9, proveedor.getUsuario());
            pstm.setString(10, proveedor.getPassword());
                       
            pstm.executeUpdate();                 
                                   
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorADO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public int eliminar(int rut)
    {
        String sql = "delete from lindasonrisa.proveedor where rut = ?";
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, rut);
            int c = pstm.executeUpdate();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorADO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int actualizar(ProveedorVO proveedor, int rut)
    {
        String sql ="update lindasonrisa.proveedor set razonSocial=?, direccion=?, telefono=?, email=?, rubro=?,"
                + " tipoUsuario=?, usuario=?, contrasena=?  where rut=?";
        
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setString(1, proveedor.getRazonSocial());
            pstm.setString(2, proveedor.getDireccion());
            pstm.setInt(3, proveedor.getTelefono());
            pstm.setString(4, proveedor.getEmail());
            pstm.setString(5, proveedor.getRubro());
            pstm.setString(6, proveedor.getTipoUsuario());
            pstm.setString(7, proveedor.getUsuario());
            pstm.setString(8, proveedor.getPassword());
            pstm.setInt(9, rut);
            int c = pstm.executeUpdate();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorADO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public ProveedorVO buscar(int rut)
    {
        String sql ="select * from lindasonrisa.proveedor where rut=?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, rut);
            rs=pstm.executeQuery();
            rs.next();
            ProveedorVO proveedor = new ProveedorVO(
                    rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),
                    rs.getString(9),rs.getString(10));
            rs.close();
            pstm.close();
            
            
            
            return proveedor;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public ProveedorVO buscaCredenciales(String usuario, String password)
    {
        String sql ="select * from lindasonrisa.proveedor where usuario = ? AND contrasena = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = this.cnx.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, password);
            rs=pstm.executeQuery();
            rs.next();
            ProveedorVO proveedor = new ProveedorVO(
                    rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),
                    rs.getString(9),rs.getString(10));
            rs.close();
            pstm.close();
            
            return proveedor;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<ProveedorVO> buscarTodos()
    {
        ArrayList<ProveedorVO> temp = new ArrayList<ProveedorVO>();
        String sql ="select * from lindasonrisa.proveedor";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = this.cnx.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next())
            {                
                temp.add(new ProveedorVO(
                    rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),
                    rs.getString(9),rs.getString(10)));
            }
            rs.close();
            stm.close();
            return temp;           
            
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
