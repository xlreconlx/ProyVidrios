package Pojos;
// Generated 15-abr-2016 20:51:33 by Hibernate Tools 4.3.1



/**
 * Materiales generated by hbm2java
 */
public class Materiales  implements java.io.Serializable {


     private Integer idmateriales;
     private String nombre;
     private Integer preciocost;

    public Materiales() {
    }

    public Materiales(String nombre, Integer preciocost) {
       this.nombre = nombre;
       this.preciocost = preciocost;
    }
   
    public Integer getIdmateriales() {
        return this.idmateriales;
    }
    
    public void setIdmateriales(Integer idmateriales) {
        this.idmateriales = idmateriales;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getPreciocost() {
        return this.preciocost;
    }
    
    public void setPreciocost(Integer preciocost) {
        this.preciocost = preciocost;
    }




}


