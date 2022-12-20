package controlador;

import dao.BoletaImpl;
import dao.MedicamentoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Boleta;
import modelo.BoletaDetalle;
import modelo.Medicamento;
import modelo.TempVta;

@Named(value = "boletaC")
@SessionScoped
public class BoletaC implements Serializable {

    double precio = 0.0, monto = 0.0;
    Integer stockMed = 0, cantPed = 1;
    MedicamentoImpl daoMed;
    String cadenaMed, cadenaCli = "";
    TempVta tempVta;
    List<TempVta> medicamentos;
//    String proveedor = "", presentacion = "", generico = "", comercial = "", cadenaMed = "", cadenaCli = "";
    
    private Medicamento medicamento;
    private List<BoletaDetalle> listaBoletaDetalle;
    private List<BoletaDetalle> listaBoletaDetalleFinal;
    private BoletaImpl dao;
    private BoletaDetalle boletaDetalle;
    private Boleta boleta;
    
    

    public BoletaC() {
        medicamento = new Medicamento();
        listaBoletaDetalle = new ArrayList<>();
        listaBoletaDetalleFinal = new ArrayList<>();
        dao = new BoletaImpl();
        boletaDetalle = new BoletaDetalle();
        boleta = new Boleta();
    }

    public List<TempVta> agregarTmp() throws Exception {
        try {
            boolean repetido = false;
            for (int i=0;i<medicamentos.size();i++){
                if(medicamentos.get(i).getIDMED()== daoMed.obtenerCodigoMedicamento(cadenaMed)){
                    repetido = true;
                    cadenaMed ="";
                    break;
                }            }            
            if (repetido==true) {                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Duplicado", "Ya se tiene el producto en la compra"));
            } else {                
                tempVta = new TempVta();
                tempVta.setIDMED(daoMed.obtenerCodigoMedicamento(cadenaMed));
                tempVta.setPresentacion(daoMed.presentacion);
                tempVta.setGenerico(daoMed.generico);
                tempVta.setComercial(daoMed.comercial);
                tempVta.setPrecio(daoMed.precio);
                tempVta.setSTOCKMED(daoMed.stockMed);
                tempVta.setProveedor(daoMed.proveedor);
                tempVta.setCANTBODE(cantPed);                    // esto ya lo tengo en la vista
                tempVta.setSubTotal((double) (tempVta.getPrecio() * tempVta.getCANTBODE()));
                this.medicamentos.add(tempVta);
                monto += tempVta.getSubTotal();
                limpiarCampos();
            }
        } catch (Exception e) {
            System.out.println("Error en agregarTmp MedicinaC " + e.getMessage());
        }
        return medicamentos;
    }
    
    public void limpiarCampos() throws Exception {
        cadenaMed = "";
        cantPed = 1;
    }
    
    public void anularTmp() throws Exception {
        limpiarCampos();
        medicamentos.clear();
    }
    
//    public void nuevoRegVta() throws Exception {
//        tempVta = new TempVta();
//        cadenaCli = "";
//        monto = 0.0;
//        limpiarCampos();
//        medicamentos.clear();
//        Calendar c1 = Calendar.getInstance(); 
//        regventa.setNdoc(daoVtas.generarTicket(String.valueOf(c1.get(Calendar.YEAR)), 3, "TIC", true));
//        System.out.println("regventa.setNdoc" + regventa.getNdoc());
//    }
    
    
    public void agregarFila() {
        try {
            BoletaDetalle boletadet = dao.agregarFila(boletaDetalle.getMedicamento().getIDMED());
            boletadet.setIDMED(this.boletaDetalle.getMedicamento().getIDMED());
            boletadet.setCANTBODE(this.boletaDetalle.getCANTBODE());
            boletadet.setSUBTOT((boletadet.getMedicamento().getPRECMED() + 0.50) * this.boletaDetalle.getCANTBODE());

            this.listaBoletaDetalle.add(boletadet);
            boletaDetalle = new BoletaDetalle();
            for (BoletaDetalle boletadetalle : listaBoletaDetalle) {
                System.out.println(boletadetalle);
            }
            sumar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sumar() {
        double precioTotal = 0;
        for (BoletaDetalle boletaDetalle : listaBoletaDetalle) {
            precioTotal += boletaDetalle.getSUBTOT();
        }
        System.out.println(precioTotal);
        boleta.setIMPBOLE(precioTotal);
    }

    public void eliminarFila(BoletaDetalle v) {
        try {
            listaBoletaDetalle.remove(v);
            sumar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarVenta() {
        try {
            dao.registrar(boleta);
            int idven = dao.obtenerUltimoId();
            dao.registroMultiple(listaBoletaDetalle, idven);
            listaBoletaDetalle.clear();
            listar();
            boleta = new Boleta();
        } catch (Exception e) {
            System.out.println("Error en registrarVentaC " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            listaBoletaDetalleFinal = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en Listar BoletaC " + e.getMessage());
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void start() {
        listar();
    }

    public List<BoletaDetalle> getListaBoletaDetalle() {
        return listaBoletaDetalle;
    }

    public void setListaBoletaDetalle(List<BoletaDetalle> listaBoletaDetalle) {
        this.listaBoletaDetalle = listaBoletaDetalle;
    }

    public List<BoletaDetalle> getListaBoletaDetalleFinal() {
        return listaBoletaDetalleFinal;
    }

    public void setListaBoletaDetalleFinal(List<BoletaDetalle> listaBoletaDetalleFinal) {
        this.listaBoletaDetalleFinal = listaBoletaDetalleFinal;
    }

    public BoletaImpl getDao() {
        return dao;
    }

    public void setDao(BoletaImpl dao) {
        this.dao = dao;
    }

    public BoletaDetalle getBoletaDetalle() {
        return boletaDetalle;
    }

    public void setBoletaDetalle(BoletaDetalle boletaDetalle) {
        this.boletaDetalle = boletaDetalle;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }

}