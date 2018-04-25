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
public class ClienteADO {
    
    private Connection cnx;
    public ClienteADO(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public ClienteVO ingresar(ClienteVO cliente) {
        String sql = "insert into lindasonrisa.cliente (run, dv, nombre, paterno, materno,"
                + " fechaNacimiento, direccion, telefono, sexo, email,"
                + " tipoUsuario, usuario, contrasena) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, cliente.getRun());
            pstm.setString(2, cliente.getDv());
            pstm.setString(3, cliente.getNombre());
            pstm.setString(4, cliente.getApellidoPaterno() );
            pstm.setString(5, cliente.getApellidoMaterno());
            pstm.setDate(6, cliente.getFechaNacimiento());
            pstm.setString(7, cliente.getDireccion());
            pstm.setInt(8, cliente.getTelefono());
            pstm.setString(9, cliente.getSexo());
            pstm.setString(10, cliente.getEmail());
            pstm.setString(11, cliente.getTipoUsuario());
            pstm.setString(12, cliente.getUsuario());
            pstm.setString(13, cliente.getPassword());
                        
            pstm.executeUpdate();                 
                                   
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int eliminar(int run)
    {
        String sql = "delete from lindasonrisa.cliente where run = ?";
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, run);
            int c = pstm.executeUpdate();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int actualizar(ClienteVO cliente, int run)
    {
        String sql ="update lindasonrisa.cliente set dv=?, nombre=?, paterno=?,"
                + " materno=?, fechaNacimiento=?, direccion=?, telefono=?, sexo=?, email=?,"
                + " tipoUsuario=?, usuario=?, contrasena=?  where run=?";
        
        try {
            PreparedStatement pstm = this.cnx.prepareStatement(sql);
            pstm.setString(1, cliente.getDv());
            pstm.setString(2, cliente.getNombre());
            pstm.setString(3, cliente.getApellidoPaterno());
            pstm.setString(4, cliente.getApellidoMaterno());
            pstm.setDate(5, cliente.getFechaNacimiento());
            pstm.setString(6, cliente.getDireccion());
            pstm.setInt(7, cliente.getTelefono());
            pstm.setString(8, cliente.getSexo());
            pstm.setString(9, cliente.getEmail());
            pstm.setString(10, cliente.getTipoUsuario());
            pstm.setString(11, cliente.getUsuario());
            pstm.setString(12, cliente.getPassword());
            pstm.setInt(13, run);
            int c = pstm.executeUpdate();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        //return null;
    }
    
    public ClienteVO buscar(int run)
    {
        String sql ="select * from lindasonrisa.cliente where run=?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = this.cnx.prepareStatement(sql);
            pstm.setInt(1, run);
            rs=pstm.executeQuery();
            rs.next();
            ClienteVO cliente = new ClienteVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getDate(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),
                    rs.getString(11),rs.getString(12),rs.getString(13));
            rs.close();
            pstm.close();
            
            
            
            return cliente;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public ClienteVO buscarCredenciales(String usuario, String password)
    {
        String sql ="select * from lindasonrisa.cliente where usuario=? AND contrasena = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = this.cnx.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, password);
            rs=pstm.executeQuery();
            rs.next();
            ClienteVO cliente = new ClienteVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getDate(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),
                    rs.getString(11),rs.getString(12),rs.getString(13));
            rs.close();
            pstm.close();

            return cliente;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ArrayList<ClienteVO> buscarTodos()
    {
        ArrayList<ClienteVO> temp = new ArrayList<ClienteVO>();
        String sql ="select * from lindasonrisa.cliente";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = this.cnx.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next())
            {                
                temp.add(new ClienteVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getDate(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),
                        rs.getString(11),rs.getString(12),rs.getString(13)));
            }
            rs.close();
            stm.close();
            return temp;           
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
