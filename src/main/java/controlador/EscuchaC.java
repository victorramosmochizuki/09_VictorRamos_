package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import modelo.Datos;
import modelo.Proveedor;
import servicios.Azure;

@Named(value = "escuchaC")
@SessionScoped
public class EscuchaC implements Serializable {

    private Datos model;
    private Azure servicio;
    private Proveedor proveedor;
    

    public EscuchaC() {

        model = new Datos();
        
        proveedor = new Proveedor();
        servicio = new Azure();

    }

    public void Resultado() throws Exception {

        try {
            Azure.recognizeFromMic(proveedor);
        } catch (InterruptedException | ExecutionException e) {

            System.out.println("ERROR" + e.getMessage());

        }

    }

    public void prueba() {

        try {
            Azure.recognizeFromMic(proveedor);
        } catch (InterruptedException | ExecutionException e) {

            System.out.println("ERROR" + e.getMessage());

        }

    }

    public Datos getModel() {
        return model;
    }

    public void setModel(Datos model) {
        this.model = model;
    }

    public Azure getServicio() {
        return servicio;
    }

    public void setServicio(Azure servicio) {
        this.servicio = servicio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
