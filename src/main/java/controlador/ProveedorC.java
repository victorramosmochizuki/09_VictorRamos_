package controlador;

import dao.ProveedorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.Proveedor;
import modelo.Usuario;
import servicios.Azure;
import servicios.EmailS;
import servicios.MD5;
import servicios.JavaMailv3;
import servicios.ReniecS;
import servicios.Reporte;

@Data

@Named(value = "proveedorC")
@SessionScoped

public class ProveedorC implements Serializable {

    //solo para esta clase
    Proveedor pro;
    ProveedorImpl dao;
    List<Proveedor> listadopro;
    JavaMailv3 javamailv3;
    Usuario usua;
    Azure servicio;
    

    //metodo publico
    public ProveedorC() {

        //instanciando
        pro = new Proveedor();
        dao = new ProveedorImpl();
        listadopro = new ArrayList();
        servicio = new Azure();

    }
    
    
    public void Resultado() throws Exception {

        try {
            Azure.recognizeFromMic(pro);
        } catch (InterruptedException | ExecutionException e) {

            System.out.println("ERROR" + e.getMessage());

        }

    }

    public void prueba() {

        try {
            Azure.recognizeFromMic(pro);
        } catch (InterruptedException | ExecutionException e) {

            System.out.println("ERROR" + e.getMessage());

        }

    }
    
    

    //retorme un valor void = vacio
    public void listar() {
        try {
            listadopro = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en ProveedorC" + e.getMessage());
        }
    }

    //metodo constructor
    //metodo listar
    @PostConstruct
    public void construir() {
        listar();
    }

    //void = vacio
    public void registrar() {
        try {
            if (!dao.existe(pro, listadopro)) {

                pro.setNOMPROV(CamelCase(pro.getNOMPROV()));
                pro.setEMAPROV(CamelCase(pro.getEMAPROV()));
                pro.setTIPPROV(CamelCase(pro.getTIPPROV()));
                pro.setNCOMPROV(MD5.encriptar(pro.getNCOMPROV()));
                pro.setABREVPROV(CamelCase(pro.getABREVPROV()));
                pro.setDIRPROV(MD5.encriptar(pro.getDIRPROV()));
                

                
                    
                dao.registrar(pro);
                javamailv3.enviarEmail(pro);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
                listar();
                limpiar();
            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "RUC existente"));

            }

        } catch (Exception e) {
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Registro fallido"));
            
            System.out.println("Error en REGISTRAR en ProveedorC" + e.getMessage());
        }
    }

    public void obtenerDatos(Proveedor prov) {
        this.pro = prov;

    }

    public void modificar() {
        try {
            dao.modificar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en MODIFICAR ProveedorC" + e.getMessage());
        }

    }

    //get es traer
    //set es enviar
    public void eliminar() {
        try {
            dao.eliminar(pro);
            //Manda mensaje
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Eliminado con éxito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR ProveedorC" + e.getMessage());
        }
    }

    public void limpiar() {
        pro = new Proveedor();
    }

    public void buscarRUC() {
        try {
            ReniecS.buscarRuc(pro);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "RUC no encontrado"));
            System.out.println("error en Busqueda RUC" + e.getMessage());
        }
    }

    public void reporteProveedor() throws Exception {
        Reporte report = new Reporte();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "proveedor.jasper", "ListadoProveedor.pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
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
    
    

//    public Proveedor getPro() {
//        return pro;
//    }
}
