/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Diana
 */
public class ProductoVO {
    private int id;
    private String descripcion;
    private int idFamilia;
    private int idTipo;
    private int stock;
    private int stockCritico;

    public ProductoVO() {
    }

    public ProductoVO(String descripcion, int idFamilia, int idTipo, int stock, int stockCritico) {
        this.descripcion = descripcion;
        this.idFamilia = idFamilia;
        this.idTipo = idTipo;
        this.stock = stock;
        this.stockCritico = stockCritico;
    }
    
    
    public ProductoVO(int id, String descripcion, int idFamilia, int idTipo, int stock, int stockCritico) {
        this.id = id;
        this.descripcion = descripcion;
        this.idFamilia = idFamilia;
        this.idTipo = idTipo;
        this.stock = stock;
        this.stockCritico = stockCritico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockCritico() {
        return stockCritico;
    }

    public void setStockCritico(int stockCritico) {
        this.stockCritico = stockCritico;
    }
    
    
}
