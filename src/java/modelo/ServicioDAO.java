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
public class ServicioDAO {
    private Connection cnx;

    public ServicioDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public boolean agregar(ServicioVO servicio) {
        String sql = "INSERT INTO lindasonrisa.servicio(nombre) VALUES(?)";
        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, servicio.getNombre());
            pstm.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<ServicioVO> buscarTodos() {
        List<ServicioVO> listaServicio = new ArrayList<>();
        String sql = "SELECT * FROM lindasonrisa.servicio order by id";
        ResultSet rs = null;
        try (Statement stm = this.cnx.createStatement()) {
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                listaServicio.add(new ServicioVO(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
            return listaServicio;

        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
    public void actualizar(ServicioVO servicio) {
        String sql = "UPDATE lindasonrisa.servicio "
                + "SET nombre = ? WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, servicio.getNombre());
            pstm.setInt(2, servicio.getId());
            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar(int idServicio) {
        String sql = "DELETE FROM lindasonrisa.servicio WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setInt(1, idServicio);
            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
