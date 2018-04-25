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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diana
 */
public class TipoProductoDAO {
    private Connection cnx;

    public TipoProductoDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public boolean agregar(TipoProductoVO tipo) {
        String sql = "INSERT INTO lindasonrisa.tipoproducto(nombre) VALUES(?)";
        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, tipo.getNombre());
            pstm.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<TipoProductoVO> buscarTodos() {
        List<TipoProductoVO> listaTipoProd = new ArrayList<>();
        String sql = "SELECT * FROM lindasonrisa.tipoproducto order by id";
        ResultSet rs = null;
        try (Statement stm = this.cnx.createStatement()) {
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                listaTipoProd.add(new TipoProductoVO(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
            return listaTipoProd;

        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String buscar(int id) {
        String sql = "SELECT nombre FROM lindasonrisa.tipoproducto WHERE id = ?";
        ResultSet rs = null;

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            String nombre = rs.getString(1);
            rs.close();
            return nombre;

        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
    public void actualizar(TipoProductoVO tipo) {
        String sql = "UPDATE lindasonrisa.tipoproducto "
                + "SET nombre = ? WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, tipo.getNombre());
            pstm.setInt(2, tipo.getId());
            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar(int idTipo) {
        String sql = "DELETE FROM lindasonrisa.tipoproducto WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setInt(1, idTipo);
            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TipoProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
