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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diana
 */
public class FamiliaProductoDAO {
    
    private Connection cnx;

    public FamiliaProductoDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public boolean agregar(FamiliaProductoVO familia) {
        String sql = "INSERT INTO lindasonrisa.familiaproducto(nombre) VALUES(?)";
        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, familia.getNombre());
            pstm.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<FamiliaProductoVO> buscarTodos() {
        List<FamiliaProductoVO> listaFamilia = new ArrayList<FamiliaProductoVO>();
        String sql = "SELECT * FROM lindasonrisa.familiaproducto order by id";
        ResultSet rs = null;
        try (Statement stm = this.cnx.createStatement()) {
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                listaFamilia.add(new FamiliaProductoVO(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
            return listaFamilia;

        } catch (SQLException ex) {
            Logger.getLogger(FamiliaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String buscar(int id) {
        String sql = "SELECT nombre FROM lindasonrisa.familiaproducto WHERE id = ?";
        ResultSet rs = null;

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            String nombre = rs.getString(1);
            rs.close();
            return nombre;

        } catch (SQLException ex) {
            Logger.getLogger(FamiliaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
    public void actualizar(FamiliaProductoVO familia) {
        String sql = "UPDATE lindasonrisa.familiaproducto "
                + "SET nombre = ? WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, familia.getNombre());
            pstm.setInt(2, familia.getId());
            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FamiliaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar(int idFamilia) {
        String sql = "DELETE FROM lindasonrisa.familiaproducto WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setInt(1, idFamilia);
            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FamiliaProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
