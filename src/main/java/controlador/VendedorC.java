package controlador;

import dao.VendedorImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import lombok.Data;
import modelo.Vendedor;
import static servicios.JavaMail.enviarContraseña;
import servicios.ReniecS;
import servicios.ReniecSv;
import servicios.ReporteS;

@Data

@Named(value = "vendedorC")
@SessionScoped
public class VendedorC implements Serializable {

    Vendedor ven;
    VendedorImpl dao;
    List<Vendedor> listadoVen;
    int Listado = 1;

    public VendedorC() {
        ven = new Vendedor();
        dao = new VendedorImpl();
        listadoVen = new ArrayList<>();
    }

    public void buscarDNI() {
        try {
            ReniecSv.buscarDni(ven);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "DNI no encontrado"));
            System.out.println("error en Busqueda DNI" + e.getMessage());
        }
    }
    
//    public void enviarCorreo() {
//        try {
//            enviarContraseña(ven);
//            System.out.println(ven.getAPEVEN());
//        } catch (Exception e) {
//            System.out.println("error en buscar enviar correo " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void registrar() throws Exception {
        try {
            if (!dao.existe(ven, listadoVen)) {
                ven.setIDUBI(dao.obtenerCodigoUbigeo(ven.getIDUBI()));
                dao.registrar(ven);
               // enviarContraseña(ven);
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Registro exitoso"));
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Correo enviado"));
                limpiar();
                listar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "DNI existente"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Registro fallido"));
            System.out.println("Error en REGISTRAR VendedorC" + e.getMessage());
        }
        ven = new Vendedor();
        listar();
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(ven);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Modificación fallida"));
            System.out.println("Error en MODIFICAR ClienteC" + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(ven);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Eliminación fallida"));
            System.out.println("Error en ELIMINAR ClienteC" + e.getMessage());
        }
    }

    public void Desactivar() throws Exception {
        try {
            dao.desactivar(ven);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Desactivado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Desactivación fallida"));
            System.out.println("Error en DESACTIVAR ClienteC " + e.getMessage());
        }
    }

    public List<String> completeTextUbigeo(String query) throws SQLException, Exception {
        VendedorImpl daoUbi = new VendedorImpl();
        return daoUbi.autocompleteUbigeo(query);
    }

    public void limpiar() {
        ven = new Vendedor();
    }

    public void listar() {
        try {
            listadoVen = dao.listar(Listado);
        } catch (Exception e) {
            System.out.println("Error en LISTAR VendedorC: " + e.getMessage());
        }
    }
    
    public void verReportePDFMEDI() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ReporteS reporteMedi = new ReporteS();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("reporte/R_PERSONAL_L.jasper");
        String numeroinformesocial = null;
        System.out.println("El vendedor es: " + numeroinformesocial);
        reporteMedi.getReportePdf(root, numeroinformesocial);
        FacesContext.getCurrentInstance().responseComplete();
    }

    @PostConstruct
    public void construir() {
        listar();
    }

}
