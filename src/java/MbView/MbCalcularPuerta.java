/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Daos.DaoPuertas;
import Daos.DaoVidrio;
import HibernateUtil.HibernateUtil;
import java.util.ArrayList;
import Clases.Puerta;
import Pojos.Puertas;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author William Sanchez
 */
@ManagedBean
@ViewScoped
public class MbCalcularPuerta {

    private String alto;
    private String ancho;
    private int ganancia;
    private int manObra;
    private Session session;
    private Transaction transaccion;
    private ArrayList<Puertas> lista;
    private int total;
    private int idVidrio;
    private int precioVidrio;
    private int tipoVentana;
    private int precioCuerpo;
    private long precioTotal;
    private String nombreProducto;
    private int tipoPuerta;

    private double recorteAluminio3MarcoAlto;
    private double recorteAluminio3MarcoAncho;
    private double recorteAncho;
    private double recorteAlto;
    private double recorte4;

    private double recorteVidrioAncho;
    private double recorteVidrioAlto;

    private int cantidadMarcoAlto;
    private int cantidadMarcoAncho;
    private int cantidadNaveAlto;
    private int cantidadNaveAncho;
    private int cantidadPisaVidriosAncho;
    private int cantidadPisaVidriosAlto;
    private int cantidadVidrio;
    private int cantidadPartidor;
    private int cantidadEntamborado;
    private int cantidadEntamboradoF06;

    private String mensajeAluminio3Ancho;
    private String mensajeAluminio3Alto;
    private String mensajeNaveAlto;
    private String mensajeNaveAncho;
    private String mensajePisaVidriosAncho;
    private String mensajePisavidriosAlto;
    private String mensajeVidrio;
    private String mensajePartidor;
    private String mensageEntamborado;
    private String mensageEntamboradoF06;

    public MbCalcularPuerta() {
        this.lista = new ArrayList<>();
        this.precioVidrio = 0;
        this.precioTotal = 0;
        this.alto = "";
        this.ancho = "";
        this.ganancia = 0;
        this.manObra = 0;
        this.idVidrio = 0;
        this.tipoVentana = 0;
        this.nombreProducto = "";
        this.tipoPuerta = 0;
    }

    public void calcularCosto() {
        this.session = null;
        this.transaccion = null;
        try {
            Puerta puertas = new Puerta();
            DaoPuertas daoPuertas = new DaoPuertas();

            if (this.tipoVentana == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error:", "Seleccione un tipo de ventana"));
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
            this.lista.addAll(daoPuertas.getAll(this.session));

            if (this.tipoVentana == 1) {
                puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                        this.lista.get(1).getPreciocot(), this.lista.get(0).getPreciocot(),
                        this.lista.get(2).getPreciocot(), this.lista.get(3).getPreciocot(), this.lista.get(5).getPreciocot(),
                        this.lista.get(6).getPreciocot(), this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(),
                        this.lista.get(8).getPreciocot(),
                        this.tipoVentana, 0, 0,0,0,this.lista.get(13).getPreciocot());
            } else {
                if (this.tipoVentana == 2) {
                    puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                             0,this.lista.get(0).getPreciocot(),
                            this.lista.get(1).getPreciocot(), 0, this.lista.get(3).getPreciocot(),
                            this.lista.get(4).getPreciocot(), this.lista.get(2).getPreciocot(), this.lista.get(5).getPreciocot(),
                            this.lista.get(6).getPreciocot(),
                            this.tipoVentana, this.lista.get(7).getPreciocot(), 0,0,0,0);

                } else {
                    if (this.tipoVentana == 3) {
                        puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                this.lista.get(1).getPreciocot(), this.lista.get(0).getPreciocot(),
                                this.lista.get(2).getPreciocot(), this.lista.get(3).getPreciocot(), this.lista.get(5).getPreciocot(),
                                this.lista.get(6).getPreciocot(), this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(),
                                this.lista.get(8).getPreciocot(),
                                this.tipoVentana, 0, this.lista.get(9).getPreciocot(),0,0,0);
                    } else {
                        if (this.tipoVentana == 4) {
                            puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                    0,this.lista.get(0).getPreciocot(),
                            this.lista.get(1).getPreciocot(), 0, this.lista.get(3).getPreciocot(),
                            this.lista.get(4).getPreciocot(), this.lista.get(2).getPreciocot(), this.lista.get(5).getPreciocot(),
                            this.lista.get(6).getPreciocot(),
                                    this.tipoVentana, this.lista.get(7).getPreciocot(), 0,0,0,this.lista.get(13).getPreciocot());

                        
                         } else {
                        if (this.tipoVentana == 5) {
                            puertas = new Puerta(this.ancho, this.alto, this.manObra, this.ganancia,
                                     0, this.lista.get(0).getPreciocot(),
                                    this.lista.get(2).getPreciocot(), 0, 0,
                                    this.lista.get(6).getPreciocot(), this.lista.get(4).getPreciocot(), this.lista.get(7).getPreciocot(),
                                    0, this.tipoVentana, 0, 0,this.lista.get(11).getPreciocot(),this.lista.get(12).getPreciocot()
                                    ,this.lista.get(13).getPreciocot());

                        }
                    }
                }
                }
            }

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

            if (this.tipoVentana == 5) {
                this.nombreProducto = "Vidrio " + this.alto + " * " + this.ancho;
                this.precioTotal = this.precioVidrio;
            } else {
                this.nombreProducto = "Puerta " + this.alto + " * " + this.ancho;
                this.precioTotal = puertas.getSumaTotal() + this.precioVidrio;

            }

//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Total es: " + material.getSumaTotal()));
            if (this.tipoVentana == 1) {
//                this.recorte3=material.getAncho()/2;
                this.recorteAncho = Double.valueOf(this.getAncho());
                this.recorteAlto = Double.valueOf(this.getAlto());

                this.cantidadMarcoAncho = 1;
                this.cantidadMarcoAlto = 2;
                this.cantidadNaveAlto = 2;
                this.cantidadNaveAncho = 2;
                this.cantidadNaveAlto = 2;
                this.cantidadPartidor = 1;
                this.cantidadPisaVidriosAlto = 4;
                this.cantidadPisaVidriosAncho = 4;
                this.cantidadVidrio = 2;
                this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media Alto de:" + (this.recorteAlto);
                this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media Ancho de: " + (this.recorteAncho);
                this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT87 Alto de " + (this.recorteAlto - 3.0);
                this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT87 Ancho de:  " + (this.recorteAncho - 2.0);
                this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "PisaVidrios Ancho de:  " + (this.recorteAncho - 4.5);
                this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " PisaVidrios Alto de:  " + (this.recorteAlto - 5.0);
                this.mensajePartidor = "" + this.cantidadPartidor + " Partidor de:  " + (this.recorteAncho - 5.0);
//                  this.mensajeVidrio=""+this.cantidadVidrio+"  Vidrios ALto * Ancho:"+(this.recorteVidrioAlto-10)+(this.recorteVidrioAncho-5.5);
                this.recorteVidrioAncho = Integer.valueOf(this.getAncho()) - 5.5;
                this.recorteVidrioAlto = puertas.getAlto() - 10;
                this.recorteVidrioAlto = Integer.valueOf(this.getAlto()) - 5.5;

            } else {
                if (this.tipoVentana == 2) {
//                    this.recorte3=material.getAncho()/2;

                    this.recorteAncho = Double.valueOf(this.getAncho());
                    this.recorteAlto = Double.valueOf(this.getAlto());
                    this.cantidadMarcoAlto=2;
                    this.cantidadMarcoAncho = 1;
                    this.cantidadNaveAlto = 2;
                    this.cantidadNaveAncho = 2;
                  
                    this.cantidadPartidor = 1;
                    this.cantidadPisaVidriosAlto = 4;
                    this.cantidadPisaVidriosAncho = 4;
                    this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media de:" + (this.recorteAlto);
                    this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media de: " + (this.recorteAncho);
                    this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT103 de " + (this.recorteAlto - 3.0);
                    this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT103 de:  " + (this.recorteAncho - 5);
                    this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "perfil U71 Ancho de:  " + (this.recorteAncho - 4.5);
                    this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " perfil U71 Alto de:  " + (this.recorteAlto - 5.0);
                    this.mensajePartidor = "" + this.cantidadPartidor + " AluminiT103 Partidor de:  " + (this.recorteAncho - 5);

                } else {
                    if (this.tipoVentana == 3) {
//                this.recorte3=material.getAncho()/2;
                        this.recorteAncho = Double.valueOf(this.getAncho());
                        this.recorteAlto = Double.valueOf(this.getAlto());

                        this.cantidadMarcoAncho = 1;
                        this.cantidadMarcoAlto = 2;
                        this.cantidadNaveAlto = 2;
                        this.cantidadNaveAncho = 2;
                        this.cantidadNaveAlto = 2;
                        this.cantidadPartidor = 1;
                        this.cantidadPisaVidriosAlto = 4;
                        this.cantidadPisaVidriosAncho = 4;
                        this.cantidadEntamboradoF06 = Integer.valueOf(this.getAncho()) / 8;
                        this.cantidadVidrio = 2;
                        this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media Alto de:" + (this.recorteAlto);
                        this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media Ancho de: " + (this.recorteAncho);
                        this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT87 Alto de " + (this.recorteAlto - 3.0);
                        this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT87 Ancho de:  " + (this.recorteAncho - 2.0);
                        this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "PisaVidrios Ancho de:  " + (this.recorteAncho - 4.5);
                        this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " PisaVidrios Alto de:  " + (this.recorteAlto - 5.0);
                        this.mensajePartidor = "" + this.cantidadPartidor + " Partidor AluminiT103 de:  " + (this.recorteAncho - 5.0);
                        this.mensageEntamboradoF06 = "" + this.cantidadEntamboradoF06 + "Emtamborado F06" + (this.recorteAlto - 30) / 2;
//                  this.mensajeVidrio=""+this.cantidadVidrio+"  Vidrios ALto * Ancho:"+(this.recorteVidrioAlto-10)+(this.recorteVidrioAncho-5.5);

                    } else {
                        if (this.tipoVentana == 4) {
//                    this.recorte3=material.getAncho()/2;

                            this.recorteAncho = Double.valueOf(this.getAncho());
                            this.recorteAlto = Double.valueOf(this.getAlto());
                            this.cantidadMarcoAncho = 1;
                            this.cantidadNaveAlto = 2;
                            this.cantidadNaveAncho = 2;
                            this.cantidadNaveAlto = 2;
                            this.cantidadPartidor = 1;
                            this.cantidadPisaVidriosAlto = 4;
                            this.cantidadPisaVidriosAncho = 4;
                            this.mensajeAluminio3Alto = "" + this.cantidadMarcoAlto + " Aluminio 3media de:" + (this.recorteAlto);
                            this.mensajeAluminio3Ancho = "" + this.cantidadMarcoAncho + "Alumininio 3media de: " + (this.recorteAncho);
                            this.mensajeNaveAlto = "" + this.cantidadNaveAlto + "  AluminiT103 de " + (this.recorteAlto - 3.0);
                            this.mensajeNaveAncho = "" + this.cantidadNaveAncho + " AluminioT103 de:  " + (this.recorteAncho - 2.0);
                            this.mensajePisaVidriosAncho = "" + this.cantidadPisaVidriosAncho + "PisaVidrios Ancho de:  " + (this.recorteAncho - 4.5);
                            this.mensajePisavidriosAlto = "" + this.cantidadPisaVidriosAlto + " PisaVidrios Alto de:  " + (this.recorteAlto - 5.0);
                            this.mensajePartidor = "" + this.cantidadPartidor + " Partidor AlumunoT103 de:  " + (this.recorteAncho - 20);
                            this.recorteVidrioAncho = Integer.valueOf(this.getAncho()) - 20;
                            this.recorteVidrioAlto = puertas.getAlto() - 10;
                            this.recorteVidrioAlto = Integer.valueOf(this.getAlto()) - 20;

                        }
                    }

                }
            }
//            this.recorteVidrioAncho=Integer.valueOf(this.getAncho())-5.5;            
////            this.recorteVidrioAlto=material.getAlto()-2.5;
//            this.recorteVidrioAlto= Integer.valueOf(this.getAlto())-10;
            this.precioVidrio = 0;
            this.alto = "";
            this.ancho = "";
            this.ganancia = 0;
            this.manObra = 0;
            this.idVidrio = 0;
            this.tipoVentana = 0;

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

    public ArrayList<Puertas> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Puertas> lista) {
        this.lista = lista;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdVidrio() {
        return idVidrio;
    }

    public void setIdVidrio(int idVidrio) {
        this.idVidrio = idVidrio;
    }

    public int getPrecioVidrio() {
        return precioVidrio;
    }

    public void setPrecioVidrio(int precioVidrio) {
        this.precioVidrio = precioVidrio;
    }

    public int getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(int tipoVentana) {
        this.tipoVentana = tipoVentana;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getTipoPuerta() {
        return tipoPuerta;
    }

    public void setTipoPuerta(int tipoPuerta) {
        this.tipoPuerta = tipoPuerta;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
    }

    public double getRecorteAluminio3MarcoAlto() {
        return recorteAluminio3MarcoAlto;
    }

    public void setRecorteAluminio3MarcoAlto(double recorteAluminio3MarcoAlto) {
        this.recorteAluminio3MarcoAlto = recorteAluminio3MarcoAlto;
    }

    public double getRecorteAluminio3MarcoAncho() {
        return recorteAluminio3MarcoAncho;
    }

    public void setRecorteAluminio3MarcoAncho(double recorteAluminio3MarcoAncho) {
        this.recorteAluminio3MarcoAncho = recorteAluminio3MarcoAncho;
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

    public int getCantidadMarcoAlto() {
        return cantidadMarcoAlto;
    }

    public void setCantidadMarcoAlto(int cantidadMarcoAlto) {
        this.cantidadMarcoAlto = cantidadMarcoAlto;
    }

    public int getCantidadMarcoAncho() {
        return cantidadMarcoAncho;
    }

    public void setCantidadMarcoAncho(int cantidadMarcoAncho) {
        this.cantidadMarcoAncho = cantidadMarcoAncho;
    }

    public int getCantidadNaveAlto() {
        return cantidadNaveAlto;
    }

    public void setCantidadNaveAlto(int cantidadNaveAlto) {
        this.cantidadNaveAlto = cantidadNaveAlto;
    }

    public int getCantidadNaveAncho() {
        return cantidadNaveAncho;
    }

    public void setCantidadNaveAncho(int cantidadNaveAncho) {
        this.cantidadNaveAncho = cantidadNaveAncho;
    }

    public int getCantidadPisaVidriosAncho() {
        return cantidadPisaVidriosAncho;
    }

    public void setCantidadPisaVidriosAncho(int cantidadPisaVidriosAncho) {
        this.cantidadPisaVidriosAncho = cantidadPisaVidriosAncho;
    }

    public int getCantidadPisaVidriosAlto() {
        return cantidadPisaVidriosAlto;
    }

    public void setCantidadPisaVidriosAlto(int cantidadPisaVidriosAlto) {
        this.cantidadPisaVidriosAlto = cantidadPisaVidriosAlto;
    }

    public int getCantidadVidrio() {
        return cantidadVidrio;
    }

    public void setCantidadVidrio(int cantidadVidrio) {
        this.cantidadVidrio = cantidadVidrio;
    }

    public int getCantidadPartidor() {
        return cantidadPartidor;
    }

    public void setCantidadPartidor(int cantidadPartidor) {
        this.cantidadPartidor = cantidadPartidor;
    }

    public String getMensajeAluminio3Ancho() {
        return mensajeAluminio3Ancho;
    }

    public void setMensajeAluminio3Ancho(String mensajeAluminio3Ancho) {
        this.mensajeAluminio3Ancho = mensajeAluminio3Ancho;
    }

    public String getMensajeAluminio3Alto() {
        return mensajeAluminio3Alto;
    }

    public void setMensajeAluminio3Alto(String mensajeAluminio3Alto) {
        this.mensajeAluminio3Alto = mensajeAluminio3Alto;
    }

    public String getMensajeNaveAlto() {
        return mensajeNaveAlto;
    }

    public void setMensajeNaveAlto(String mensajeNaveAlto) {
        this.mensajeNaveAlto = mensajeNaveAlto;
    }

    public String getMensajeNaveAncho() {
        return mensajeNaveAncho;
    }

    public void setMensajeNaveAncho(String mensajeNaveAncho) {
        this.mensajeNaveAncho = mensajeNaveAncho;
    }

    public String getMensajePisaVidriosAncho() {
        return mensajePisaVidriosAncho;
    }

    public void setMensajePisaVidriosAncho(String mensajePisaVidriosAncho) {
        this.mensajePisaVidriosAncho = mensajePisaVidriosAncho;
    }

    public String getMensajePisavidriosAlto() {
        return mensajePisavidriosAlto;
    }

    public void setMensajePisavidriosAlto(String mensajePisavidriosAlto) {
        this.mensajePisavidriosAlto = mensajePisavidriosAlto;
    }

    public String getMensajePartidor() {
        return mensajePartidor;
    }

    public void setMensajePartidor(String mensajePartidor) {
        this.mensajePartidor = mensajePartidor;
    }

    public int getCantidadEntamborado() {
        return cantidadEntamborado;
    }

    public void setCantidadEntamborado(int cantidadEntamborado) {
        this.cantidadEntamborado = cantidadEntamborado;
    }

    public int getCantidadEntamboradoF06() {
        return cantidadEntamboradoF06;
    }

    public void setCantidadEntamboradoF06(int cantidadEntamboradoF06) {
        this.cantidadEntamboradoF06 = cantidadEntamboradoF06;
    }

    public String getMensajeVidrio() {
        return mensajeVidrio;
    }

    public void setMensajeVidrio(String mensajeVidrio) {
        this.mensajeVidrio = mensajeVidrio;
    }

    public String getMensageEntamborado() {
        return mensageEntamborado;
    }

    public void setMensageEntamborado(String mensageEntamborado) {
        this.mensageEntamborado = mensageEntamborado;
    }

    public String getMensageEntamboradoF06() {
        return mensageEntamboradoF06;
    }

    public void setMensageEntamboradoF06(String mensageEntamboradoF06) {
        this.mensageEntamboradoF06 = mensageEntamboradoF06;
    }

}
