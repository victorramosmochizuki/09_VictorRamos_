//
package controlador;

import dao.CompraImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Compra;
import modelo.Medicamento;
import modelo.Proveedor;
import servicios.Reporte;
//
//

@Named(value = "compraC")
@SessionScoped
public class CompraC implements Serializable {

    private CompraImpl dao;
    private Compra compra;
    private Medicamento medicamento;
    private Proveedor proveedor;
    private List<Compra> lstCompra;

    public CompraC() {
        lstCompra = new ArrayList<>();
        dao = new CompraImpl();
        medicamento = new Medicamento(); //jalas los modelo en transaccional
        proveedor = new Proveedor();
        compra = new Compra();
        
    }

    public void listar() {
        try {
            lstCompra = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en LISTAR COMPRA CompraC: " + e.getMessage());
        }

    }

    public void eliminar(Compra com) {
        try {
            dao.eliminar(com);
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en eliminar CompraC:" + e.getMessage());
        }
    }

//    public void reporteCompra() throws Exception {
//        Reporte report = new Reporte();
//        try {
//            Map<String, Object> parameters = new HashMap();
//            report.exportarPDFGlobal(parameters, "ListadoC.jasper", "ListadoCompra.pdf");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
//            throw e;
//        }
//    }
    
    
    
    public void reporteCompra() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "ticketcomv9.2.jasper", "TicketCompra.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }
    
    
    
//    public void reporteTicketCompra() throws Exception {
//        Reporte report = new Reporte();
//        try {
//            Map<String, Object> parameters = new HashMap();
//            report.exportarPDFGlobal(parameters, "ticketcomv1.jasper", "TicketCompra.pdf");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
//            throw e;
//        }
//    }
    
    
    
    public void registrar() { //Aqui no va nd
        try {
            dao.registrar(compra);
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en registrar CompraC:" + e.getMessage());
        }
    }

    public void obtenerDatos(Compra com) {
        this.compra = com;
    }

    public void modificar() {
        try {
            dao.modificar(compra);
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en modificar CompraC:" + e.getMessage());
        }
    }

    public void limpiar() {
        compra = new Compra();

    }

//    
//    public List<String> completeTextUbigeo(String query) throws SQLException, Exception {
//        CompraD dao = new CompraD();
//        return dao.autocompleteUbigeo(query);
//    }
    public CompraImpl getDao() {
        return dao;
    }

    public void setDao(CompraImpl dao) {
        this.dao = dao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public List<Compra> getLstCompra() {
        return lstCompra;
    }

    public void setLstCompra(List<Compra> lstCompra) {
        this.lstCompra = lstCompra;
    }

}
