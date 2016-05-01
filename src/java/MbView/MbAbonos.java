/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.Material;
import Clases.Puerta;
import Clases.Vitrina;
import Daos.DaoAbonoDetalle;
import Daos.DaoAbonoSecundario;
import Daos.DaoAbonos;
import Daos.DaoCliente;
import Daos.DaoEmpleado;
import Daos.DaoMaterial;
import Daos.DaoProductos;
import Daos.DaoPuertas;
import Daos.DaoVidrio;
import Daos.DaoVitrinas;
import HibernateUtil.HibernateUtil;
import Pojos.Abonodetalle;
import Pojos.Abonos;
import Pojos.Abonosecundario;
import Pojos.Materiales;
import Pojos.Puertas;
import Pojos.Vitrinas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class MbAbonos {

    /**
     * Creates a new instance of MbAbonos
     */
       private Session session;
    private Transaction transaccion;
   
    private Abonodetalle abonoDeta;
    private ArrayList<Abonodetalle> listaAbono;
    private ArrayList<Abonodetalle> listaFactura;
    private ArrayList<Materiales> lista;
       private List<Abonos> listaAbonosFiltrado;
    private int tipoVidrio;
    private int tipoVentana;
    private String ancho;
    private String alto;
    private int ganancia;
    private int manObra;
    private Abonos abonos;
    private List<Abonos> listaFact;
    private List<Abonos> listaVentasByFecha;
    private int idCliente;
    private int idEmpleado;
      private int idAbono;
    private long precioVidrio;
    private int idVidrio;
     private Date fechaFin;
    private Date fechaInicio;
    private long totalVentasFecha;
    private long montoAbono;
    private List<Abonosecundario> listaAbonoSecundario;
    private int idAbonoSecundario;
    private int productoTipo;
    private String fondo;
    private List<Puertas> listaPuertas;
    private int tipoPuerta;
    private List<Vitrinas> listaVitrinas;
    private int tipoVitrina;
      private int tipoEntrepanos;
        private String nombreProducto;
       private long precioTotal;
    
    public MbAbonos() {
        this.listaVentasByFecha= new ArrayList<>();
        this.listaFact=new ArrayList<>();
        this.lista = new ArrayList<>();
        this.listaPuertas = new ArrayList<>();
        this.listaAbono = new ArrayList<>();
        this.listaVitrinas= new ArrayList<>();
        this.listaFactura = new ArrayList<>();
        this.abonos =  new Abonos();
        this.abonoDeta = new Abonodetalle();
        this.listaAbonoSecundario= new ArrayList<>();
        this.idVidrio = 0;
        this.ganancia = 0;
        this.alto = "";
        this.ancho = "";
        this.manObra = 0;
        this.tipoVentana = 0;
        this.tipoVidrio = 0;
        this.tipoVitrina=0;
        this.fondo="";
   this.productoTipo=0;
   this.tipoEntrepanos=0;
    }
    
    
    
    
       public void agregarProducto() {
     switch(this.productoTipo){
             case 1:
                calcularVentana();
                break;
            case 2:
                calcularPuerta();
                break;
            case 3:
                calcularVitrina();
                break;
            case 4:
                calcularPuerta();
                break;
            case 5:
                calcularPuerta();
                break;
            case 6:
                calcularPuerta();
                break;
            case 7:
                calcularPuerta();
                break;

            case 8:
                calcularVitrina();
                break;
            case 9:   

                calcularVitrina();
                break;
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Escoja un tipo de producto"));
                break;
      }
    }

     public void calcularVitrina() {
        this.session = null;
        this.transaccion = null;
        Vitrina vitrina = new Vitrina();
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoProductos daoProductos = new DaoProductos();
            DaoVitrinas daoVitrinas = new DaoVitrinas();
            this.listaVitrinas.addAll(daoVitrinas.getAll(this.session));
               int codigoVitrina = 0;
               
                if (this.productoTipo == 3) {
                  if (this.idVidrio != 0) {
                int vidrioEntrepano = 0;
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
                vidrioEntrepano = daoVidrio.getById(this.session, 6).getPreciocost();
                long precFondos = this.precioVidrio * (vitrina.getAlto() * vitrina.getFondo());
                precFondos = precFondos * 2;
                long precFondoAncho = this.precioVidrio * (vitrina.getAncho() * vitrina.getFondo());
                precFondoAncho = precFondoAncho * 2;
                this.precioVidrio = this.precioVidrio * (vitrina.getAlto() * vitrina.getAncho());
                this.precioVidrio = this.precioVidrio * 2;
                this.precioVidrio = this.precioVidrio + precFondos + precFondoAncho;

                if (this.tipoEntrepanos == 1) {
                    vidrioEntrepano = vidrioEntrepano * (vitrina.getAncho() * vitrina.getFondo() * 3);
                } else {
                    if (this.tipoEntrepanos == 2) {
                        vidrioEntrepano = vidrioEntrepano * (vitrina.getAncho() * vitrina.getFondo() * 4);
                    }
                }

                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                this.precioVidrio = this.precioVidrio + vidrioEntrepano;
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);

         
            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            if (this.tipoEntrepanos == 1) {
                this.nombreProducto = "Vitrina de 3 entrepaños " + this.alto + " * " + this.ancho + " Fondo: " + this.fondo;
                this.precioTotal = vitrina.getSumaTotal() + this.precioVidrio;
            } else {
                this.nombreProducto = "Vitrina de 4 entrepaños " + this.alto + " * " + this.ancho + " Fondo: " + this.fondo;
                this.precioTotal = vitrina.getSumaTotal() + this.precioVidrio;
            }
                     
                
                codigoVitrina = 7;
                 vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                    this.listaVitrinas.get(0).getPreciocot(), this.listaVitrinas.get(1).getPreciocot(),
                    this.listaVitrinas.get(2).getPreciocot(), this.listaVitrinas.get(4).getPreciocot(), this.listaVitrinas.get(5).getPreciocot(),
                    this.listaVitrinas.get(6).getPreciocot(), this.listaVitrinas.get(3).getPreciocot(),
                    this.listaVitrinas.get(7).getPreciocot(),
                    this.listaVitrinas.get(8).getPreciocot(), 1,0,0,0,0);

            } else {
                if (this.productoTipo == 8) {
                    codigoVitrina = 11;
                          vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                            0, this.listaVitrinas.get(1).getPreciocot(),
                            0, this.listaVitrinas.get(4).getPreciocot(), 0,
                            this.listaVitrinas.get(6).getPreciocot(), 0,
                            this.listaVitrinas.get(8).getPreciocot(),
                            0, 2, this.listaVitrinas.get(9).getPreciocot(), this.listaVitrinas.get(10).getPreciocot(),
                            this.listaVitrinas.get(11).getPreciocot(), this.listaVitrinas.get(12).getPreciocot());

                } else {
                    if (this.productoTipo == 9) {
                        codigoVitrina = 12;
                        vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                                0, this.listaVitrinas.get(1).getPreciocot(),
                                0, this.listaVitrinas.get(4).getPreciocot(), 0,
                                this.listaVitrinas.get(6).getPreciocot(), 0,
                                this.listaVitrinas.get(8).getPreciocot(),
                                0, 3, this.listaVitrinas.get(9).getPreciocot(), this.listaVitrinas.get(10).getPreciocot(),
                                this.listaVitrinas.get(11).getPreciocot(), this.listaVitrinas.get(12).getPreciocot());
                  
               }  
                }
              }        
                  
            this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, codigoVitrina),
                    daoProductos.getById(this.session, codigoVitrina).getNombre() + " " + this.alto + "*" + this.ancho + " fondo: " + this.fondo,
                    1, vitrina.getSumaTotal() + this.precioVidrio, 1 * vitrina.getSumaTotal()
                    + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, this.fondo));

            this.transaccion.commit();
            this.idVidrio = 0;
            this.ganancia = 0;
            this.alto = "";
            this.ancho = "";
            this.manObra = 0;
            this.tipoVentana = 0;
            this.tipoVidrio = 0;
            this.tipoVitrina = 0;
            this.fondo = "";
            this.productoTipo = 0;
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
    public void calcularPuerta() {
        this.session = null;
        this.transaccion = null;
        Puerta puertas = new Puerta();

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoProductos daoProductos = new DaoProductos();
            DaoPuertas daoPuertas = new DaoPuertas();
            this.listaPuertas.addAll(daoPuertas.getAll(this.session));
            int codigoPuerta = 0;

            if (this.productoTipo == 2) {
                if (this.idVidrio != 0) {
                    DaoVidrio daoVidrio = new DaoVidrio();

                    this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
//                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));

                    this.precioVidrio = this.precioVidrio * (puertas.getAlto() * puertas.getAncho());
                    // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                    this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                    int espacios = String.valueOf(this.precioVidrio).length();
                
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "seleccione:", "por favor selecione un vidrio: "));
                    return;
                }
                codigoPuerta = 5;
                puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                        this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                        this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(3).getPreciocot(), this.listaPuertas.get(5).getPreciocot(),
                        this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                        this.listaPuertas.get(8).getPreciocot(),
                        1, 0, 0,0,0,this.listaPuertas.get(13).getPreciocot());

            } else {
                if (this.productoTipo == 4) {
                    codigoPuerta = 6;
                    puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                            this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                            this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(5).getPreciocot(),
                            this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                            this.listaPuertas.get(8).getPreciocot(),
                             2, this.listaPuertas.get(9).getPreciocot(), 0,0,0,0);

                } else {
                    if (this.productoTipo == 5) {
                        codigoPuerta = 8;
                        puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                                this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(3).getPreciocot(), this.listaPuertas.get(5).getPreciocot(),
                                this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                                this.listaPuertas.get(8).getPreciocot(),
                             3, 0, this.listaPuertas.get(9).getPreciocot(),0,0,0);
                    } else {
                        if (this.productoTipo == 6) {
                            if (this.idVidrio != 0) {
                                DaoVidrio daoVidrio = new DaoVidrio();

                                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
//                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));

                                this.precioVidrio = this.precioVidrio * (puertas.getAlto() * puertas.getAncho());
                                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                                int espacios = String.valueOf(this.precioVidrio).length();
                            
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "seleccione:", "por favor selecione un vidrio: "));
                                return;

                            }

                            
                            codigoPuerta = 9;
                            puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                    this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                                    this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(5).getPreciocot(),
                                    this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                                    this.listaPuertas.get(8).getPreciocot(),
                                4, this.listaPuertas.get(9).getPreciocot(), 0,0,0,this.listaPuertas.get(13).getPreciocot());
  } else {
                        if (this.productoTipo == 7) {
                            if (this.idVidrio != 0) {
                                DaoVidrio daoVidrio = new DaoVidrio();

                                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
//                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));

                                this.precioVidrio = this.precioVidrio * (puertas.getAlto() * puertas.getAncho());
                                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                                int espacios = String.valueOf(this.precioVidrio).length();
                            
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "seleccione:", "por favor selecione un vidrio: "));
                                return;

                            }

                            
                            codigoPuerta = 10;
                             puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                    this.listaPuertas.get(1).getPreciocot(), 0,
                                    this.listaPuertas.get(2).getPreciocot(), 0, 0,
                                    this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                                    this.listaPuertas.get(8).getPreciocot(),
                                    this.tipoVentana, 0, 0,this.listaPuertas.get(11).getPreciocot(),this.listaPuertas.get(12).getPreciocot(),this.listaPuertas.get(13).getPreciocot());

                        }
                    }
                }
            }
            }

            this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, codigoPuerta),
                    daoProductos.getById(this.session, codigoPuerta).getNombre() + " " + this.alto + "*" + this.ancho,
                    1, puertas.getSumaTotal() + this.precioVidrio, 1 * puertas.getSumaTotal()
                    + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, ""));

            this.transaccion.commit();

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
    public void calcularVentana() {
        this.session = null;
        this.transaccion = null;
        try {

            if (this.alto.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor digite el alto."));
                return;
            }

            if (this.ancho.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor digite el ancho."));
                return;
            }

            if (this.alto.length() > 3 || this.ancho.length() > 3) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Las medidas no pueden superar 3 cifras."));
                return;
            }

            if (String.valueOf(this.manObra).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el precio de la mano de obra."));
                return;
            }

            if (String.valueOf(this.ganancia).equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el porcentaje de ganancia."));
                return;
            }

            if (this.tipoVentana == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Escoja un tipo de producto."));
                return;
            }

            try {
                int o = Integer.valueOf(this.alto);
                int p = Integer.valueOf(this.ancho);
                int m = this.manObra;
                int t = this.ganancia;
            } catch (NumberFormatException nfe) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digito texto en un campo numerico."));
            }

            DaoMaterial daoMaterial = new DaoMaterial();
            DaoProductos daoProductos = new DaoProductos();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.lista.addAll(daoMaterial.getAll(this.session));

            Material material = new Material(this.ancho, this.alto, this.manObra,
                    this.ganancia, this.lista.get(0).getPreciocost(),
                    this.lista.get(2).getPreciocost(), this.lista.get(1).getPreciocost(),
                    this.lista.get(3).getPreciocost(), this.lista.get(4).getPreciocost(),
                    this.lista.get(5).getPreciocost(), this.lista.get(6).getPreciocost(),
                    this.lista.get(9).getPreciocost(), this.lista.get(8).getPreciocost(),
                    this.lista.get(7).getPreciocost(), this.lista.get(10).getPreciocost(),
                    this.tipoVentana, this.lista.get(11).getPreciocost());

            if (this.idVidrio != 0) {
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
                this.precioVidrio = this.precioVidrio * (material.getAlto() * material.getAncho());

                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));
            }
            if (this.idVidrio == 0) {
                this.precioVidrio = 0;
            }

            if (this.tipoVentana < 4 && this.tipoVentana > 0) {
                this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + this.alto + "*" + this.ancho,
                        1, material.getSumaTotal() + this.precioVidrio, 1 * material.getSumaTotal()
                        + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, ""));

            }
            if (this.tipoVentana == 4) {
                this.listaAbono.add(new Abonodetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + this.alto + "*" + this.ancho,
                        1, this.precioVidrio, 1 * this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, ""));
            }
            this.transaccion.commit();
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

    public void generarFactura() {
        this.session = null;
        this.transaccion = null;
        try {
            DaoCliente daoCliente = new DaoCliente();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            DaoAbonos daoAbonos = new DaoAbonos();
            DaoAbonoDetalle daoAbonoDetalle = new DaoAbonoDetalle();
        
            
            if(this.idCliente==0 || this.idEmpleado==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor selecione un empleado y un cliente."));
                return;
            }
              if(this.montoAbono==0){
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el valor del Abono."));
                return;  
            }
            if(String.valueOf(this.montoAbono).equals("")){
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digite el valor del Abono."));
                return;  
            }
               try {
              
                long m=this.montoAbono;
            
            } catch (NumberFormatException nfe) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Digito texto en un campo numerico."));
            }
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
          
            this.abonos.setCliente(daoCliente.getById(this.session, this.idCliente));
            this.abonos.setEmpleado(daoEmpleado.getById(this.session, this.idEmpleado));
           this.abonos.setFecharegistro(new Date());
            daoAbonos.registar(this.session, this.abonos);

            this.abonos = daoAbonos.getByUltimoRegistro(this.session);
            long suma = 0;
            for (Abonodetalle listaVentana1 : this.listaAbono) {
                listaVentana1.setAbonos(this.abonos);
                suma += listaVentana1.getTotal();
              daoAbonoDetalle.registar(this.session, listaVentana1);
            }
            this.listaFactura = this.listaAbono;
            this.abonos.setPrecioTotal(suma);
            this.abonos.setMontoabono(this.montoAbono);
            this.abonos.setSaldofinal(this.abonos.getPrecioTotal()-this.abonos.getMontoabono());
            daoAbonos.actualizar(this.session, this.abonos);
            this.abonos.getFecharegistro();
            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesson.setAttribute("idfactura", this.abonos.getIdabonos());
            this.transaccion.commit();

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

    public void retirarListaVentaDetalle(String nombre) {
        try {
            int i = 0;
            for (Abonodetalle listaVentana1 : this.listaAbono) {
                if (listaVentana1.getNombrepro().equals(nombre)) {
                    this.listaAbono.remove(i);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Productos retirado de la lista de ventas"));

                    break;
                }
                i++;
            }

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
        }
    }

    public void calcular() {

        for (Abonodetalle listaVentana1 : listaAbono) {
            listaVentana1.setTotal(listaVentana1.getCantidad() * listaVentana1.getPrecioventa());
        }

    }

    public List<Abonodetalle> getByIdFactura() {
        this.session = null;
        this.transaccion = null;

        try {
         
            DaoAbonos daoAbonos= new DaoAbonos();
            DaoAbonoDetalle daoAbonoDetalle = new DaoAbonoDetalle();
            

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            this.abonos = daoAbonos.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));
            List<Abonodetalle> listaVenta = daoAbonoDetalle.getAllByIdFactura(this.session, this.abonos.getIdabonos());
            this.transaccion.commit();
            return listaVenta;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }
    }

    public Abonos getFacturaActual() {
        this.session = null;
        this.transaccion = null;

        try {
             DaoAbonos daoAbonos= new DaoAbonos();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            this.abonos = daoAbonos.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));

            this.transaccion.commit();
            return this.abonos;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }
    }

    
  
    
       public List<Abonos> getAll() {
        this.session = null;
        this.transaccion = null;
        try {
      
          
             DaoAbonos daoAbonos= new DaoAbonos();
                    
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaFact = daoAbonos.getAll(this.session);
            this.transaccion.commit();
            return this.listaFact;
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor intente mas tarde " + ex.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    
       
         public List<Abonos> getListVentasByFecha() {
        if (listaVentasByFecha == null) {
            listaVentasByFecha = new ArrayList<>();
        }
        return listaVentasByFecha;
    }
         
             public void consultarVentas() {
        this.session = null;
        this.transaccion= null;
                try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            
            DaoAbonos daoAbonos = new DaoAbonos();
            this.listaVentasByFecha = daoAbonos.getAllFecha(this.session, this.fechaInicio, this.fechaFin);
            
        
            totalVentasFecha=0;
            for (Abonos abono : listaVentasByFecha) {
                totalVentasFecha = totalVentasFecha+(abono.getMontoabono());
            }
            this.transaccion.commit();
        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor intente mas tarde " + ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
          
    public Abonodetalle getAbonoDeta() {
        return abonoDeta;
    }

    public void setAbonoDeta(Abonodetalle abonoDeta) {
        this.abonoDeta = abonoDeta;
    }

    public ArrayList<Abonodetalle> getListaAbono() {
        return listaAbono;
    }

    public void setListaAbono(ArrayList<Abonodetalle> listaAbono) {
        this.listaAbono = listaAbono;
    }

    public ArrayList<Abonodetalle> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(ArrayList<Abonodetalle> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public ArrayList<Materiales> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Materiales> lista) {
        this.lista = lista;
    }

    public int getTipoVidrio() {
        return tipoVidrio;
    }

    public void setTipoVidrio(int tipoVidrio) {
        this.tipoVidrio = tipoVidrio;
    }

    public int getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(int tipoVentana) {
        this.tipoVentana = tipoVentana;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
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

    public Abonos getAbonos() {
        return abonos;
    }

    public void setAbonos(Abonos abonos) {
        this.abonos = abonos;
    }

    public List<Abonos> getListaFact() {
        return listaFact;
    }

    public void setListaFact(List<Abonos> listaFact) {
        this.listaFact = listaFact;
    }

    public List<Abonos> getListaVentasByFecha() {
        return listaVentasByFecha;
    }

    public void setListaVentasByFecha(List<Abonos> listaVentasByFecha) {
        this.listaVentasByFecha = listaVentasByFecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public long getPrecioVidrio() {
        return precioVidrio;
    }

    public void setPrecioVidrio(long precioVidrio) {
        this.precioVidrio = precioVidrio;
    }

    public int getIdVidrio() {
        return idVidrio;
    }

    public void setIdVidrio(int idVidrio) {
        this.idVidrio = idVidrio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public long getTotalVentasFecha() {
        return totalVentasFecha;
    }

    public void setTotalVentasFecha(long totalVentasFecha) {
        this.totalVentasFecha = totalVentasFecha;
    }

    public List<Abonos> getListaAbonosFiltrado() {
        return listaAbonosFiltrado;
    }

    public void setListaAbonosFiltrado(List<Abonos> listaAbonosFiltrado) {
        this.listaAbonosFiltrado = listaAbonosFiltrado;
    }

    public long getMontoAbono() {
        return montoAbono;
    }

    public void setMontoAbono(long montoAbono) {
        this.montoAbono = montoAbono;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public List<Abonosecundario> getListaAbonoSecundario() {
        return listaAbonoSecundario;
    }

    public void setListaAbonoSecundario(List<Abonosecundario> listaAbonoSecundario) {
        this.listaAbonoSecundario = listaAbonoSecundario;
    }

    public int getIdAbonoSecundario() {
        return idAbonoSecundario;
    }

    public void setIdAbonoSecundario(int idAbonoSecundario) {
        this.idAbonoSecundario = idAbonoSecundario;
    }

    public int getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipo(int productoTipo) {
        this.productoTipo = productoTipo;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public List<Puertas> getListaPuertas() {
        return listaPuertas;
    }

    public void setListaPuertas(List<Puertas> listaPuertas) {
        this.listaPuertas = listaPuertas;
    }

    public int getTipoPuerta() {
        return tipoPuerta;
    }

    public void setTipoPuerta(int tipoPuerta) {
        this.tipoPuerta = tipoPuerta;
    }

    public List<Vitrinas> getListaVitrinas() {
        return listaVitrinas;
    }

    public void setListaVitrinas(List<Vitrinas> listaVitrinas) {
        this.listaVitrinas = listaVitrinas;
    }

    public int getTipoVitrina() {
        return tipoVitrina;
    }

    public void setTipoVitrina(int tipoVitrina) {
        this.tipoVitrina = tipoVitrina;
    }

    public int getTipoEntrepanos() {
        return tipoEntrepanos;
    }

    public void setTipoEntrepanos(int tipoEntrepanos) {
        this.tipoEntrepanos = tipoEntrepanos;
    }
    
}
