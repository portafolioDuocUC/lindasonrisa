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
public class ProductoDAO {

    private Connection cnx;

    public ProductoDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public boolean agregar(ProductoVO producto) {
        String sql = "INSERT INTO lindasonrisa.producto(descripcion, idfamilia, idtipo, stock, stockcritico)"
                + " VALUES(?,?,?,?,?)";
        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, producto.getDescripcion());
            pstm.setInt(2, producto.getIdFamilia());
            pstm.setInt(3, producto.getIdTipo());
            pstm.setInt(4, producto.getStock());
            pstm.setInt(5, producto.getStockCritico());
            
            pstm.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ProductoVO> buscarTodos() {
        List<ProductoVO> listaProducto = new ArrayList<ProductoVO>();
        String sql = "SELECT * FROM lindasonrisa.producto";
        ResultSet rs = null;
        try (Statement stm = this.cnx.createStatement()) {
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                listaProducto.add(new ProductoVO(
                        rs.getInt(1),
                        rs.getString(2), 
                        rs.getInt(3), 
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
            rs.close();
            return listaProducto;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean actualizar(ProductoVO producto) {
        String sql = "UPDATE lindasonrisa.producto "
                + "SET descripcion = ?, idfamilia = ?,"
                + " idtipo = ?, stock = ?, stockcritico = ? WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setString(1, producto.getDescripcion());
            pstm.setInt(2, producto.getIdFamilia());
            pstm.setInt(3, producto.getIdTipo());
            pstm.setInt(4, producto.getStock());
            pstm.setInt(5, producto.getStockCritico());
            pstm.setInt(6, producto.getId());
            pstm.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM lindasonrisa.producto WHERE id = ?";

        try (PreparedStatement pstm = this.cnx.prepareStatement(sql)) {
            pstm.setInt(1, idProducto);
            pstm.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
