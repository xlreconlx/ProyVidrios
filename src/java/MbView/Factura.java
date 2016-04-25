/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Clases.Material;
import Clases.Puerta;
import Clases.Vitrina;
import Daos.DaoCliente;
import Daos.DaoDetalle;
import Daos.DaoEmpleado;
import Daos.DaoFactura;
import Daos.DaoMaterial;
import Daos.DaoProductos;
import Daos.DaoPuertas;
import Daos.DaoVidrio;
import Daos.DaoVitrinas;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import HibernateUtil.HibernateUtil;
import Pojos.Cliente;
import Pojos.Facturas;
import Pojos.Materiales;
import Pojos.Puertas;
import Pojos.Ventanadetalle;
import Pojos.Vitrinas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ander
 */
@ManagedBean
@ViewScoped
public class Factura {

    private Session session;
    private Transaction transaccion;
    private Ventanadetalle ventana;
    private ArrayList<Ventanadetalle> listaVentana;
    private ArrayList<Ventanadetalle> listaFactura;
    private ArrayList<Materiales> lista;
    private int tipoVidrio;
    private int tipoVentana;
    private String ancho;
    private String alto;
    private String fondo;
    private int ganancia;
    private int manObra;
    private Facturas factura;
    private List<Facturas> listaFact;
    private List<Facturas> listaFactFiltrado;
    private List<Facturas> listaVentasByFecha;
    private int idCliente;
    private int idEmpleado;
    private long precioVidrio;
    private int idVidrio;
    private Date fechaFin;
    private Date fechaInicio;
    private long totalVentasFecha;
    private int tipoAluminio;
    private int productoTipo;
    private String numeroDocumento;
    private List<Puertas> listaPuertas;
    private int tipoPuerta;
    private List<Vitrinas> listaVitrinas;
    private int tipoVitrina;
    private int idFactura;
    private Cliente cliente;
    private Long montoDinero;
    private Long saldoDevuelta;
    private int tipoEntrepanos;

    /**
     * Creates a new instance of VentanaDetalle
     */
    public Factura() {
        this.lista = new ArrayList<>();
        this.listaVentana = new ArrayList<>();
        this.listaFactura = new ArrayList<>();
        this.factura = new Facturas();
        this.ventana = new Ventanadetalle();

        this.idVidrio = 0;
        this.ganancia = 0;
        this.alto = "";
        this.ancho = "";
        this.manObra = 0;
        this.tipoVentana = 0;
        this.tipoVidrio = 0;
        this.tipoVitrina = 0;
        this.fondo = "";
        this.tipoEntrepanos=0;

    }

    public void agregarProducto() {
        switch (this.productoTipo) {
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
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Escoja un tipo de producto"));
                break;
        }
    }

    public void calcularVitrina() {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoProductos daoProductos = new DaoProductos();
            DaoVitrinas daoVitrinas = new DaoVitrinas();
            this.listaVitrinas.addAll(daoVitrinas.getAll(this.session));
            Vitrina vitrina = new Vitrina(this.ancho, this.alto, this.fondo, this.manObra, this.ganancia,
                    this.listaVitrinas.get(0).getPreciocot(), this.listaVitrinas.get(1).getPreciocot(),
                    this.listaVitrinas.get(2).getPreciocot(), this.listaVitrinas.get(4).getPreciocot(), this.listaVitrinas.get(5).getPreciocot(),
                    this.listaVitrinas.get(6).getPreciocot(), this.listaVitrinas.get(3).getPreciocot(),
                    this.listaVitrinas.get(7).getPreciocot(),
                    this.listaVitrinas.get(8).getPreciocot(), 1);

            if (this.idVidrio != 0) {
                  int vidrioEntrepano = 0;
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
                vidrioEntrepano = daoVidrio.getById(this.session, 6).getPreciocost();
                 long precFondos=this.precioVidrio*(vitrina.getAlto()*vitrina.getFondo());
                precFondos= precFondos*2;
                long precFondoAncho = this.precioVidrio*(vitrina.getAncho()*vitrina.getFondo());
                precFondoAncho=precFondoAncho*2;
                this.precioVidrio= this.precioVidrio*(vitrina.getAlto()*vitrina.getAncho());
                this.precioVidrio=this.precioVidrio*2;
                this.precioVidrio=this.precioVidrio+precFondos+precFondoAncho;
                
                  if (this.tipoEntrepanos == 1) {
                    vidrioEntrepano = vidrioEntrepano * (vitrina.getAncho() * vitrina.getFondo() * 3);
                } else {
                    if (this.tipoEntrepanos == 2) {
                        vidrioEntrepano = vidrioEntrepano * (vitrina.getAncho() * vitrina.getFondo() * 4);
                    }
                }
                
                 this.precioVidrio = this.precioVidrio + vidrioEntrepano;
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);

                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));
         

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, 7),
                    daoProductos.getById(this.session, 7).getNombre() + " " + this.alto + "*" + this.ancho + " fondo: " + this.fondo,
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

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoProductos daoProductos = new DaoProductos();
            DaoPuertas daoPuertas = new DaoPuertas();
            this.listaPuertas.addAll(daoPuertas.getAll(this.session));
            int codigoPuerta = 0;
            if (this.productoTipo == 2) {
                this.tipoPuerta = 1;
                codigoPuerta = 5;
            } else {
                this.tipoPuerta = 2;
                codigoPuerta = 6;
            }

            Puerta puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                    this.listaPuertas.get(1).getPreciocot(), this.listaPuertas.get(0).getPreciocot(),
                    this.listaPuertas.get(2).getPreciocot(), this.listaPuertas.get(3).getPreciocot(), this.listaPuertas.get(5).getPreciocot(),
                    this.listaPuertas.get(6).getPreciocot(), this.listaPuertas.get(4).getPreciocot(), this.listaPuertas.get(7).getPreciocot(),
                    this.listaPuertas.get(8).getPreciocot(), 0, 0,
                    this.tipoPuerta);

            if (this.idVidrio != 0) {
                DaoVidrio daoVidrio = new DaoVidrio();

                this.precioVidrio = daoVidrio.getById(this.session, this.idVidrio).getPreciocost();
//                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio1 del vidrio es: " + this.precioVidrio));

                this.precioVidrio = this.precioVidrio * (puertas.getAlto() * puertas.getAncho());
                // this.precioVidrio=this.precioVidrio+(this.precioVidrio*50/100);
                this.precioVidrio = this.precioVidrio + (this.precioVidrio / 2);
                int espacios = String.valueOf(this.precioVidrio).length();
                this.precioVidrio = Integer.valueOf(String.valueOf(this.precioVidrio).substring(0, espacios - 4));
                //        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El precio del vidrio es: " + String.valueOf(this.precioVidrio).substring(0, espacios-4)));

            } else {
                if (this.idVidrio == 0) {
                    this.precioVidrio = 0;
                }
            }

            this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, codigoPuerta),
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
                this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, this.tipoVentana),
                        daoProductos.getById(this.session, this.tipoVentana).getNombre() + " " + this.alto + "*" + this.ancho,
                        1, material.getSumaTotal() + this.precioVidrio, 1 * material.getSumaTotal()
                        + this.precioVidrio, this.manObra, this.ganancia, this.alto, this.ancho, ""));

            }
            if (this.tipoVentana == 4) {
                this.listaVentana.add(new Ventanadetalle(null, daoProductos.getById(this.session, this.tipoVentana),
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
            DaoFactura daoFactura = new DaoFactura();
            DaoDetalle daoDetalle = new DaoDetalle();

            if (this.idCliente == 0 || this.idEmpleado == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Por favor selecione un empleado y un cliente."));
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.factura.setCliente(daoCliente.getById(this.session, this.idCliente));
            this.factura.setEmpleado(daoEmpleado.getById(this.session, this.idEmpleado));
            this.factura.setFechaventa(new Date());
            daoFactura.registar(this.session, this.factura);

            this.factura = daoFactura.getByUltimoRegistro(this.session);
            long suma = 0;
            long subtota = 0;
            long ivat = 0;
            for (Ventanadetalle listaVentana1 : this.listaVentana) {
                listaVentana1.setFacturas(this.factura);
                suma += listaVentana1.getTotal();
                ivat = this.factura.getPreciototal() * 16 / 100;
                subtota = this.factura.getPreciototal() - ivat;

                daoDetalle.registar(this.session, listaVentana1);

            }
            this.listaFactura = this.listaVentana;
            this.factura.setSubtotal(subtota);
            this.factura.setIva(ivat);
            this.factura.setPreciototal(suma);

            daoFactura.actualizar(this.session, this.factura);
            this.factura.getFechaventa();
            HttpSession sesson
                    = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            sesson.setAttribute("idfactura", this.factura.getIdfacturas());
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
            for (Ventanadetalle listaVentana1 : this.listaVentana) {
                if (listaVentana1.getNombrepro().equals(nombre)) {
                    this.listaVentana.remove(i);
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

        long total = 0;
        try {
            for (Ventanadetalle listaVentana1 : listaVentana) {
                listaVentana1.setTotal(listaVentana1.getCantidad() * listaVentana1.getPrecioventa());
                total = total + listaVentana1.getTotal();

            }
            this.factura.setPreciototal(total);
            this.factura.setIva(this.factura.getPreciototal() * 16 / 100);
            this.factura.setSubtotal(this.factura.getPreciototal() - this.factura.getIva());

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));

        }

    }

    public List<Ventanadetalle> getByIdFactura() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoFactura daoFactura = new DaoFactura();
            DaoDetalle daoDetalle = new DaoDetalle();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            this.factura = daoFactura.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));
            List<Ventanadetalle> listaVenta = daoDetalle.getAllByIdFactura(this.session, this.factura.getIdfacturas());
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

    public Facturas getFacturaActual() {
        this.session = null;
        this.transaccion = null;

        try {
            DaoFactura daoFactura = new DaoFactura();
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            HttpSession sesson = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            this.factura = daoFactura.getById(this.session, Integer.valueOf(sesson.getAttribute("idfactura").toString()));

            this.transaccion.commit();
            return this.factura;
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

    public List<Facturas> getAll() {
        this.session = null;
        this.transaccion = null;
        try {

            DaoFactura daoFactura = new DaoFactura();

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            this.listaFact = daoFactura.getAll(this.session);
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

    public List<Facturas> getListVentasByFecha() {
        if (listaVentasByFecha == null) {
            listaVentasByFecha = new ArrayList<>();
        }
        return listaVentasByFecha;
    }

    public void consultarVentas() {
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();

            DaoFactura daoFactura = new DaoFactura();
            this.listaVentasByFecha = daoFactura.getAllFecha(this.session, this.fechaInicio, this.fechaFin);

            totalVentasFecha = 0;
            for (Facturas facturas : listaVentasByFecha) {
                totalVentasFecha = totalVentasFecha + (facturas.getPreciototal());
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

    public void selectFactura(int id) {
        this.idFactura = id;
    }

    public void searchByDocumento() {
        this.listaFact = new ArrayList<>();
        this.session = null;
        this.transaccion = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            DaoCliente daoCliente = new DaoCliente();
            DaoFactura daoFactura = new DaoFactura();
            this.cliente = daoCliente.getByNumeroDocumento(this.session, this.numeroDocumento);
            this.listaFact.addAll(daoFactura.getAllByCliente(this.session, this.numeroDocumento));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contacte con su administrador" + e.getMessage()));
            this.listaFact = null;
            this.cliente = null;
            if (this.transaccion != null) {
                this.transaccion.rollback();
                this.session.close();
            }
        }
    }

    public void calcularDevueltas(int monto, long precioVenta) {

        try {
            this.setMontoDinero(montoDinero);
          
            saldoDevuelta = saldoDevuelta+(this.montoDinero-this.factura.getPreciototal());

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));

        }
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

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
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

    public Ventanadetalle getVentana() {
        return ventana;
    }

    public void setVentana(Ventanadetalle ventana) {
        this.ventana = ventana;
    }

    public ArrayList<Ventanadetalle> getListaVentana() {
        return listaVentana;
    }

    public void setListaVentana(ArrayList<Ventanadetalle> listaVentana) {
        this.listaVentana = listaVentana;
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

    public ArrayList<Materiales> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Materiales> lista) {
        this.lista = lista;
    }

    public ArrayList<Ventanadetalle> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(ArrayList<Ventanadetalle> listaFactura) {
        this.listaFactura = listaFactura;
    }

    public List<Facturas> getListaFact() {
        return listaFact;
    }

    public void setListaFact(List<Facturas> listaFact) {
        this.listaFact = listaFact;
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

    public List<Facturas> getListaVentasByFecha() {
        return listaVentasByFecha;
    }

    public void setListaVentasByFecha(List<Facturas> listaVentasByFecha) {
        this.listaVentasByFecha = listaVentasByFecha;
    }

    public List<Facturas> getListaFactFiltrado() {
        return listaFactFiltrado;
    }

    public void setListaFactFiltrado(List<Facturas> listaFactFiltrado) {
        this.listaFactFiltrado = listaFactFiltrado;
    }

    public int getTipoAluminio() {
        return tipoAluminio;
    }

    public void setTipoAluminio(int tipoAluminio) {
        this.tipoAluminio = tipoAluminio;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public int getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipo(int productoTipo) {
        this.productoTipo = productoTipo;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getMontoDinero() {
        return montoDinero;
    }

    public void setMontoDinero(Long montoDinero) {
        this.montoDinero = montoDinero;
    }

    public Long getSaldoDevuelta() {
        return saldoDevuelta;
    }

    public void setSaldoDevuelta(Long saldoDevuelta) {
        this.saldoDevuelta = saldoDevuelta;
    }

    public int getTipoEntrepanos() {
        return tipoEntrepanos;
    }

    public void setTipoEntrepanos(int tipoEntrepanos) {
        this.tipoEntrepanos = tipoEntrepanos;
    }

    
    
    
}
