package controlador;

import dao.ClienteImpl;
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
import modelo.Cliente;
import servicios.ReniecS;
import servicios.ReniecSv;
import servicios.ReporteS;

@Data

@Named(value = "clienteC")
@SessionScoped
public class ClienteC implements Serializable {

    Cliente cli;
    ClienteImpl dao;
    List<Cliente> listadoCli;
    int Listado = 1;

    public ClienteC() {
        cli = new Cliente();
        dao = new ClienteImpl();
        listadoCli = new ArrayList<>();
    }

    public void buscarDNI() {
        try {
            ReniecSv.buscarDni(cli);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "DNI no encontrado"));
            System.out.println("error en Busqueda DNI" + e.getMessage());
        }
    }


    public void registrar() throws Exception {
        try {
            if (!dao.existe(cli, listadoCli)) {
                dao.registrar(cli);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Registro exitoso"));
                limpiar();
                listar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "DNI existente"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Registro fallido"));
            System.out.println("Error en REGISTRAR ClienteC " + e.getMessage());
        }
        cli = new Cliente();
        listar();
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cli);
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
            dao.eliminar(cli);
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
            dao.desactivar(cli);
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

    public void limpiar() {
        cli = new Cliente();
    }

    public void listar() {
        try {
            listadoCli = dao.listar(Listado);
        } catch (Exception e) {
            System.out.println("Error en LISTAR ClienteC: " + e.getMessage());
        }
    }

    public void verReportePDFCLI() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ReporteS reporteCli = new ReporteS();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("reporte/R_CLIENTE_L.jasper");
        String numeroinformesocial = null;
        System.out.println("El Cliente es: " + numeroinformesocial);
        reporteCli.getReportePdf(root, numeroinformesocial);
        FacesContext.getCurrentInstance().responseComplete();
    }

    @PostConstruct
    public void construir() {
        listar();
    }
}
