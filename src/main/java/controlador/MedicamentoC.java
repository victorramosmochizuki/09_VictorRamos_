package controlador;

import dao.MedicamentoImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.sql.SQLException;
import modelo.Medicamento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import lombok.Data;
import servicios.Reporte;
import servicios.ReporteS;

@Data
@Named(value = "medicamentoC")
@ConversationScoped
public class MedicamentoC implements Serializable {

    private Medicamento med;
    private MedicamentoImpl dao;
    private List<Medicamento> listadomed;
    private int Listado = 1;

    public MedicamentoC() {
        med = new Medicamento();
        dao = new MedicamentoImpl();
        listadomed = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            med.setIDPROV(dao.obtenerCodigoProveedor(med.getIDPROV()));
            dao.registrar(med);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Registro exitoso"));
            listar();
            limpiar();
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Registro fallido"));
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(med);
            System.out.println("parece que si");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Modificación fallida"));
            System.out.println("Error en MODIFICAR MedicamentoC" + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar2(med);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Eliminado con éxito"));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Eliminación fallida"));
            System.out.println("Error en ELIMINAR MedicamentoC" + e.getMessage());
        }
    }

    public void Desactivar() throws Exception {
        try {
            dao.desactivar(med);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Desactivado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Desactivación fallida"));
            System.out.println("Error en DESACTIVAR MedicamentoC " + e.getMessage());
        }
    }

    public String CamelCase(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {
                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    ch[i] = (char) (ch[i] - 'a' + 'A');
                }
            } else if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        return st;
    }

    public List<String> completeTextProveedor(String query) throws SQLException, Exception {
        MedicamentoImpl daoPro = new MedicamentoImpl();
        return daoPro.autocompleteProveedor(query);
    }

    public void limpiar() {
        med = new Medicamento();

    }

    public void listar() {
        try {
            listadomed = dao.listar(Listado);
        } catch (Exception e) {
            System.out.println("Error en LISTAR MedicamentoC: " + e.getMessage());
        }
    }

    public void verReportePDFMEDI() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ReporteS reporteMedi = new ReporteS();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("reporte/R_MEDICAMENTO_L.jasper");
        String numeroinformesocial = null;
        System.out.println("El medicamento es: " + numeroinformesocial);
        reporteMedi.getReportePdf(root, numeroinformesocial);
        FacesContext.getCurrentInstance().responseComplete();
    }

//    public void ReportePDFMedi() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
//        ReporteS reporteMedicamento = new ReporteS();
//        FacesContext facescontext = FacesContext.getCurrentInstance();
//        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
//        String root = servletcontext.getRealPath("reporte/R_MEDICAMENTO_MV.jasper");
//        String numeroinformesocial = null;
//        System.out.println("El medicamento es: " + numeroinformesocial);
//        reporteMedicamento.getReportePdf(root, numeroinformesocial);
//        FacesContext.getCurrentInstance().responseComplete();
//    }
    
    public void reporteMedicamentoMV() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "R_MEDICAMENTO_MV.jasper", "Medicamentos más vendidos.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    @PostConstruct
    public void construir() {
        listar();
    }
}
