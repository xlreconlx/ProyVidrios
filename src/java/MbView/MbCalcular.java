/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.Material;
import Daos.DaoMaterial;
import Daos.DaoVidrio;
import HibernateUtil.HibernateUtil;
import Pojos.Materiales;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ander
 */
@ManagedBean
@ViewScoped
public class MbCalcular {

    private String alto;
    private String ancho;
    private int ganancia;
    private int manObra;
    private Session session;
    private Transaction transaccion;
    private ArrayList<Materiales> lista;
    private int total;
    private int idVidrio;
    private long  precioVidrio;
    private int tipoVentana;
    private int precioCuerpo;
    private long precioTotal;
    private String nombreProducto;
    
    private int tipoAluminio;
    private double recorteTraslape;
    private double recorteEnganche;
    private double recorteAncho;
    private double recorteAlto;
    private double recorte4;
    private double recorteVidrioAncho;
    private double recorteVidrioAlto;
    private int cantidadJamba;
    private int cantidadCabezal;
    private int cantidadSillar;
    private int cantidadTraslape;
    private int cantidadEnganche;
    private int cantidadHorizontalesSuperior;
      private int cantidadHorizontalesInferior;
      private int cantidadVidrio;
      private int cantidadAdactador;
    private double recorteJamba;
    private double recorteCabezal;
    private double recorteSillar;
    private String mensajeHSuperior;
     private String mensajeHInferior;
     private String mensajeCabezal;
     private String mensajeSillar;
     private String mensajeJamba;
     private String mensajeEnganche;
     private String mensajeTraslape;
     private String mensajeAdactador;
        private String mensajeVidrio;
         private int canti;
    private String mensajeCanti;
    private long precioTotalCantidad;
    

    /**
     * Creates a new instance of MbCalcular
     */
     public MbCalcular() {
        this.lista = new ArrayList<>();
        this.precioVidrio = 0;
        this.precioTotal = 0;
        this.alto = "";
        this.ancho = "";
        this.ganancia = 0;
        this.manObra = 0;
        this.idVidrio = 0;
          this.canti=0;
        this.nombreProducto="";
        this.mensajeHSuperior="";
        this.mensajeHInferior="";
        
        
    }
     
  public void calcularCosto() {
        this.mensajeHSuperior="";
        this.session = null;
        this.transaccion = null;
        try {
            Material material= new Material();
            DaoMaterial daoMaterial = new DaoMaterial();

            if (this.tipoVentana == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Seleccione un tipo de ventana"));
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            this.lista.addAll(daoMaterial.getAll(this.session));
            
            if(this.tipoAluminio==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Seleccione un tipo de aluminio"));
                return;
            }
            
            if(this.tipoAluminio==1){
                    material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                    this.lista.get(0).getPreciocost(), this.lista.get(2).getPreciocost(),
                    this.lista.get(1).getPreciocost(), this.lista.get(3).getPreciocost(), 
                    this.lista.get(4).getPreciocost(),
                    this.lista.get(5).getPreciocost(), this.lista.get(6).getPreciocost(),
                    this.lista.get(9).getPreciocost(), this.lista.get(8).getPreciocost(),
                    this.lista.get(7).getPreciocost(), this.lista.get(10).getPreciocost(), 
                    this.tipoVentana, this.lista.get(11).getPreciocost());
              }else{
               
                if(this.tipoAluminio==2){
                         material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                    this.lista.get(12).getPreciocost(), this.lista.get(14).getPreciocost(),
                    this.lista.get(13).getPreciocost(), this.lista.get(15).getPreciocost(), 
                    this.lista.get(16).getPreciocost(),
                    this.lista.get(17).getPreciocost(), this.lista.get(18).getPreciocost(),
                    this.lista.get(21).getPreciocost(), this.lista.get(20).getPreciocost(),
                    this.lista.get(19).getPreciocost(), this.lista.get(22).getPreciocost(), 
                    this.tipoVentana, this.lista.get(23).getPreciocost());
                }else{
                        material = new Material(this.ancho, this.alto, this.manObra, this.ganancia,
                    this.lista.get(24).getPreciocost(), this.lista.get(26).getPreciocost(),
                    this.lista.get(25).getPreciocost(), this.lista.get(27).getPreciocost(), 
                    this.lista.get(28).getPreciocost(),
                    this.lista.get(29).getPreciocost(), this.lista.get(30).getPreciocost(),
                    this.lista.get(33).getPreciocost(), this.lista.get(32).getPreciocost(),
                    this.lista.get(31).getPreciocost(), this.lista.get(34).getPreciocost(), 
                    this.tipoVentana, this.lista.get(35).getPreciocost()); 
                }
                
                
                
              }
            

              if (this.idVidrio != 0) {
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio del vidrio db es : " + this.precioVidrio));
//          
//                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));

                this.precioVidrio = this.precioVidrio * (material.getAlto() * material.getAncho());
                
                
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio multi es : " + this.precioVidrio));
//          
                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                int espacios1 = String.valueOf(this.precioVidrio).length();
                if(String.valueOf(this.precioVidrio).length()>10){
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios1 - 5));
              }else{
                 this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios1 - 4));
                }
                
               this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
            
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio vidrio mas ganancia : " + this.precioVidrio));
//          
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio recortado es: " + this.precioVidrio));
//          
        //        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio del vidrio es: " + String.valueOf(this.precioVidrio).substring(0, espacios-4)));

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            if (this.tipoVentana == 4) {
                this.nombreProducto = "Vidrio " + this.alto + " * " + this.ancho;
                this.precioTotal = this.precioVidrio;
            } else {
                this.nombreProducto = "ventana " + this.alto + " * " + this.ancho;
                this.precioTotal = material.getSumaTotal() + this.precioVidrio;
                    this.precioTotalCantidad= this.precioTotal*this.canti;
                this.mensajeCanti=String.valueOf(this.canti);
            }
            
            
            
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio del vidrio es: " + String.valueOf(this.precioVidrio).substring(0, espacios-4)));

//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El alto  es: " + material.getAlto()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "el ancho es: " + material.getAncho()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio empaque es: " + material.getEmpaque()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio guias es: " + material.getGuias()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio cabezal es: " + material.getPrecioCabezal()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio enganche es: " + material.getPrecioEnganche()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio traslape es: " + material.getPrecioTraslape()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio hinferior es: " + material.getPrecioHinferior()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio hsuperior es: " + material.getPrecioHsuperior()));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio sillar es: " + material.getPrecioSillar()));
//
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Precio jamba es: " + material.getPrecioJamba()));
//            
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El total sin aumento: " + material.getSubTotal()));
//         
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Total de los Materiales es: " + material.getSumaTotal()));
          
            if(this.tipoVentana==1){
//                this.recorte3=material.getAncho()/2;
                this.recorteAncho=Double.valueOf(this.getAncho())/2;
                this.recorteAlto=Double.valueOf(this.getAlto())/2;
                
                 this.cantidadCabezal=1* this.canti;
                 this.cantidadSillar=1* this.canti;
                 this.cantidadJamba=2* this.canti;
                 this.cantidadEnganche=2* this.canti;
                 this.cantidadTraslape=2* this.canti;
                 this.cantidadHorizontalesSuperior=2* this.canti;
                 this.cantidadHorizontalesInferior=2* this.canti;
                      this.cantidadVidrio = 2 * this.canti;
                 this.mensajeCabezal=""+this.cantidadCabezal+" Cabeazal de:  "+ (this.recorteAncho * 2);
                  this.mensajeSillar=""+this.cantidadSillar+" Sillar de: "+ (this.recorteAncho * 2);
                  this.mensajeJamba=""+this.cantidadJamba+"  Jamba de "+(this.recorteAlto+this.recorteAlto-1.3);
                  this.mensajeEnganche=""+this.cantidadEnganche+" Enganchez de:  "+(this.recorteAlto+this.recorteAlto-2.5);
                  this.mensajeTraslape=""+this.cantidadTraslape+"traslapes de:  "+(this.recorteAlto+recorteAlto-2.5);
                  this.mensajeHSuperior=""+this.cantidadHorizontalesSuperior+" Horizontales Superior de:  "+this.recorteAncho;
                 this.mensajeHInferior=""+this.cantidadHorizontalesInferior+" Horizontales Inferior de:  "+this.recorteAncho;
                  this.mensajeVidrio = "" + this.cantidadVidrio + " Vidrio Alto de: "+ (this.recorteAlto * 2-5)+ " VidrioAncho de: "+ (this.recorteAncho * 2 -5.5);


            }else{
                if(this.tipoVentana==2){
//                    this.recorte3=material.getAncho()/2;
                    
           this.recorteAncho=Double.valueOf(this.getAncho())/2;
                this.recorteAlto=Double.valueOf(this.getAlto())/2;
                  this.cantidadCabezal=1* this.canti;
                 this.cantidadSillar=1* this.canti;
                 this.cantidadJamba=2* this.canti;
                 this.cantidadEnganche=2* this.canti;
                 this.cantidadTraslape=4* this.canti;
                 this.cantidadHorizontalesSuperior=2* this.canti;
                 this.cantidadHorizontalesInferior=2* this.canti;
                this.mensajeCabezal=""+this.cantidadCabezal+" Cabeazal de:  "+ (this.recorteAncho * 2);
                  this.mensajeSillar=""+this.cantidadSillar+" Sillar de: "+ (this.recorteAncho * 2);;
                  this.mensajeJamba=""+this.cantidadJamba+"  Jamba de "+(this.recorteAlto+this.recorteAlto-1.3);
                  this.mensajeEnganche=""+this.cantidadEnganche+" Enganchez de:  "+(this.recorteAlto+this.recorteAlto-2.5);
                  this.mensajeTraslape=""+this.cantidadTraslape+"traslapes de:  "+(this.recorteAlto+recorteAlto-2.5);
                
                     this.cantidadHorizontalesSuperior=3;
                        this.cantidadHorizontalesInferior=3;
                    this.mensajeHSuperior=""+(this.cantidadHorizontalesSuperior-1)+" Horizontales Superior de:  "+(this.recorteAncho/2)+"  y 1 horizontal Superior de: "+this.recorteAncho;
                   this.mensajeHInferior=""+(this.cantidadHorizontalesInferior-1)+" Horizontales Inferior de:  "+(this.recorteAncho/2)+"  y 1 horizontal Inferior de: "+this.recorteAncho;
                                    this.mensajeVidrio = "" + this.cantidadVidrio + " Vidrio Alto de: "+ (this.recorteAlto * 2-5)+ " VidrioAncho de: "+ (this.recorteAncho * 2 -5.5);


                   
                }else{
//                    this.recorte3=material.getAncho()/4;
                     this.recorteAncho=Double.valueOf(this.getAncho())/4;
                this.recorteAlto=Double.valueOf(this.getAlto())/4;
                
                 this.cantidadCabezal=1* this.canti;
                 this.cantidadSillar=1* this.canti;
                 this.cantidadJamba=2* this.canti;
                 this.cantidadEnganche=4* this.canti;
                 this.cantidadTraslape=4* this.canti;
                 this.cantidadHorizontalesSuperior=4* this.canti;
                 this.cantidadHorizontalesInferior=4* this.canti;
                 this.cantidadAdactador=1* this.canti;
                this.mensajeCabezal=""+this.cantidadCabezal+" Cabeazal de:  "+ (this.recorteAncho * 2);
                  this.mensajeSillar=""+this.cantidadSillar+" Sillar de: "+ (this.recorteAncho * 2);
                  this.mensajeJamba=""+this.cantidadJamba+"  Jamba de "+(this.recorteAlto+this.recorteAlto+this.recorteAlto+this.recorteAlto-1.3);
                  this.mensajeEnganche=""+this.cantidadEnganche+" Enganchez de:  "+(this.recorteAlto+this.recorteAlto+this.recorteAlto+this.recorteAlto-2.5);
                  this.mensajeTraslape=""+this.cantidadTraslape+" traslapes de:  "+(this.recorteAlto+recorteAlto+this.recorteAlto+this.recorteAlto-2.5);
                  this.mensajeHSuperior=""+this.cantidadHorizontalesSuperior+" Horizontales Superior de:  "+this.recorteAncho;
                 this.mensajeHInferior=""+this.cantidadHorizontalesInferior+" Horizontales Inferior de:  "+this.recorteAncho;
                 this.mensajeHSuperior=""+this.cantidadHorizontalesSuperior+" Horizontales de:  "+this.recorteAncho;
                 this.mensajeHInferior=""+this.cantidadHorizontalesInferior+" Horizontales de:  "+this.recorteAncho;
                 this.mensajeAdactador=""+this.cantidadAdactador+" Adaptador de: "+(this.recorteAlto+this.recorteAlto+this.recorteAlto+this.recorteAlto-2.5);
               this.mensajeVidrio = "" + this.cantidadVidrio + " Vidrio Alto de: "+ (this.recorteAlto * 2-5)+ " VidrioAncho de: "+ (this.recorteAncho * 2 -5.5);

                 
                }
            }
//            this.recorteCabezal=Integer.valueOf(this.getAncho())-0;
//            this.recorteSillar= Integer.valueOf(this.getAncho())-0;
//          
////            this.recorteTraslape=Integer.valueOf(this.getAlto())-2.5;
//            
////             this.recorteEnganche=Integer.valueOf(this.getAlto())-2.5;
//            this.recorteVidrioAncho=Integer.valueOf(this.getAncho())-5.5;            
////            this.recorteVidrioAlto=material.getAlto()-2.5;
//            this.recorteVidrioAlto= Integer.valueOf(this.getAlto())-10;
//            
//            this.cantidadHorizontalesSuperior=Integer.valueOf(this.getAncho())-0;
//            this.cantidadHorizontalesInferior = Integer.valueOf(this.getAncho())-0;
            this.precioVidrio = 0;
            this.alto = "";
            this.ancho = "";
            this.ganancia = 0;
            this.manObra = 0;
            this.idVidrio = 0;
//            this.tipoVentana = 0;

        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));

        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }

    }

    public ArrayList<Materiales> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Materiales> lista) {
        this.lista = lista;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getGanancia() {
        return ganancia;
    }

    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }

    public int getManObra() {
        return manObra;
    }

    public void setManObra(int manObra) {
        this.manObra = manObra;
    }

    public int getIdVidrio() {
        return idVidrio;
    }

    public void setIdVidrio(int idVidrio) {
        this.idVidrio = idVidrio;
    }

    public int getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(int tipoVentana) {
        this.tipoVentana = tipoVentana;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getPrecioVidrio() {
        return precioVidrio;
    }

    public void setPrecioVidrio(long precioVidrio) {
        this.precioVidrio = precioVidrio;
    }

    public int getPrecioCuerpo() {
        return precioCuerpo;
    }

    public void setPrecioCuerpo(int precioCuerpo) {
        this.precioCuerpo = precioCuerpo;
    }

    public long getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(long precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTipoAluminio() {
        return tipoAluminio;
    }

    public void setTipoAluminio(int tipoAluminio) {
        this.tipoAluminio = tipoAluminio;
    }

    public double getRecorteTraslape() {
        return recorteTraslape;
    }

    public void setRecorteTraslape(double recorteTraslape) {
        this.recorteTraslape = recorteTraslape;
    }

    public double getRecorteEnganche() {
        return recorteEnganche;
    }

    public void setRecorteEnganche(double recorteEnganche) {
        this.recorteEnganche = recorteEnganche;
    }

    public double getRecorteAncho() {
        return recorteAncho;
    }

    public void setRecorteAncho(double recorteAncho) {
        this.recorteAncho = recorteAncho;
    }

    public double getRecorteAlto() {
        return recorteAlto;
    }

    public void setRecorteAlto(double recorteAlto) {
        this.recorteAlto = recorteAlto;
    }

  

    public double getRecorte4() {
        return recorte4;
    }

    public void setRecorte4(double recorte4) {
        this.recorte4 = recorte4;
    }

    public double getRecorteVidrioAncho() {
        return recorteVidrioAncho;
    }

    public void setRecorteVidrioAncho(double recorteVidrioAncho) {
        this.recorteVidrioAncho = recorteVidrioAncho;
    }

    public double getRecorteVidrioAlto() {
        return recorteVidrioAlto;
    }

    public void setRecorteVidrioAlto(double recorteVidrioAlto) {
        this.recorteVidrioAlto = recorteVidrioAlto;
    }

    public int getCantidadHorizontalesSuperior() {
        return cantidadHorizontalesSuperior;
    }

    public void setCantidadHorizontalesSuperior(int cantidadHorizontalesSuperior) {
        this.cantidadHorizontalesSuperior = cantidadHorizontalesSuperior;
    }

    public int getCantidadHorizontalesInferior() {
        return cantidadHorizontalesInferior;
    }

    public void setCantidadHorizontalesInferior(int cantidadHorizontalesInferior) {
        this.cantidadHorizontalesInferior = cantidadHorizontalesInferior;
    }

    public double getRecorteCabezal() {
        return recorteCabezal;
    }

    public void setRecorteCabezal(double recorteCabezal) {
        this.recorteCabezal = recorteCabezal;
    }

    public double getRecorteSillar() {
        return recorteSillar;
    }

    public void setRecorteSillar(double recorteSillar) {
        this.recorteSillar = recorteSillar;
    }

 

    public double getRecorteJamba() {
        return recorteJamba;
    }

    public void setRecorteJamba(double recorteJamba) {
        this.recorteJamba = recorteJamba;
    }

    public String getMensajeHSuperior() {
        return mensajeHSuperior;
    }

    public void setMensajeHSuperior(String mensajeHSuperior) {
        this.mensajeHSuperior = mensajeHSuperior;
    }

    public String getMensajeHInferior() {
        return mensajeHInferior;
    }

    public void setMensajeHInferior(String mensajeHInferior) {
        this.mensajeHInferior = mensajeHInferior;
    }

    public int getCantidadJamba() {
        return cantidadJamba;
    }

    public void setCantidadJamba(int cantidadJamba) {
        this.cantidadJamba = cantidadJamba;
    }

    public int getCantidadCabezal() {
        return cantidadCabezal;
    }

    public void setCantidadCabezal(int cantidadCabezal) {
        this.cantidadCabezal = cantidadCabezal;
    }

    public int getCantidadSillar() {
        return cantidadSillar;
    }

    public void setCantidadSillar(int cantidadSillar) {
        this.cantidadSillar = cantidadSillar;
    }

    public int getCantidadTraslape() {
        return cantidadTraslape;
    }

    public void setCantidadTraslape(int cantidadTraslape) {
        this.cantidadTraslape = cantidadTraslape;
    }

    public int getCantidadEnganche() {
        return cantidadEnganche;
    }

    public void setCantidadEnganche(int cantidadEnganche) {
        this.cantidadEnganche = cantidadEnganche;
    }

    public int getCantidadVidrio() {
        return cantidadVidrio;
    }

    public void setCantidadVidrio(int cantidadVidrio) {
        this.cantidadVidrio = cantidadVidrio;
    }

    public String getMensajeCabezal() {
        return mensajeCabezal;
    }

    public void setMensajeCabezal(String mensajeCabezal) {
        this.mensajeCabezal = mensajeCabezal;
    }

    public String getMensajeSillar() {
        return mensajeSillar;
    }

    public void setMensajeSillar(String mensajeSillar) {
        this.mensajeSillar = mensajeSillar;
    }

    public String getMensajeJamba() {
        return mensajeJamba;
    }

    public void setMensajeJamba(String mensajeJamba) {
        this.mensajeJamba = mensajeJamba;
    }

    public String getMensajeEnganche() {
        return mensajeEnganche;
    }

    public void setMensajeEnganche(String mensajeEnganche) {
        this.mensajeEnganche = mensajeEnganche;
    }

    public String getMensajeTraslape() {
        return mensajeTraslape;
    }

    public void setMensajeTraslape(String mensajeTraslape) {
        this.mensajeTraslape = mensajeTraslape;
    }

    public int getCantidadAdactador() {
        return cantidadAdactador;
    }

    public void setCantidadAdactador(int cantidadAdactador) {
        this.cantidadAdactador = cantidadAdactador;
    }

    public String getMensajeAdactador() {
        return mensajeAdactador;
    }

    public void setMensajeAdactador(String mensajeAdactador) {
        this.mensajeAdactador = mensajeAdactador;
    }

    public int getCanti() {
        return canti;
    }

    public void setCanti(int canti) {
        this.canti = canti;
    }

    public String getMensajeCanti() {
        return mensajeCanti;
    }

    public void setMensajeCanti(String mensajeCanti) {
        this.mensajeCanti = mensajeCanti;
    }

    public long getPrecioTotalCantidad() {
        return precioTotalCantidad;
    }

    public void setPrecioTotalCantidad(long precioTotalCantidad) {
        this.precioTotalCantidad = precioTotalCantidad;
    }

    public String getMensajeVidrio() {
        return mensajeVidrio;
    }

    public void setMensajeVidrio(String mensajeVidrio) {
        this.mensajeVidrio = mensajeVidrio;
    }

   
    
}
