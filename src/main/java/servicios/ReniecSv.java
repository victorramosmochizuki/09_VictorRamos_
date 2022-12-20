package servicios;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.ws.Response;
import modelo.Cliente;
import modelo.Vendedor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ReniecSv {

    public static void buscarDni(Cliente cli) throws Exception {
        String dni = cli.getDNICLI();
        String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imp1bGlvLnF1aXNwZUB2YWxsZWdyYW5kZS5lZHUucGUifQ.6M-P2QMMvKFZEeMvTUXvkOooM02N_pWqt0OdlaYW3PM";
        String enlace = "https://dniruc.apisperu.com/api/v1/dni/" + dni + token;
        try {
            URL url = new URL(enlace);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            if (root.isJsonObject()) {
                JsonObject rootobj = root.getAsJsonObject();
                String apellido_paterno = rootobj.get("apellidoPaterno").getAsString();
                String apellido_materno = rootobj.get("apellidoMaterno").getAsString();
                String nombres = rootobj.get("nombres").getAsString();

                System.out.println("Resultado\n");
                System.out.println(apellido_paterno + "\n" + apellido_materno + "\n" + nombres + "\n");

                cli.setNOMCLI(nombres);
                cli.setAPECLI(apellido_paterno + " " + apellido_materno);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "DNI no encontrado"));
        }
    }
    
    public static void buscarDni2(Vendedor ven) throws Exception {
        String dni = ven.getDNIVEN();
        String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imp1bGlvLnF1aXNwZUB2YWxsZWdyYW5kZS5lZHUucGUifQ.6M-P2QMMvKFZEeMvTUXvkOooM02N_pWqt0OdlaYW3PM";
        String enlace = "https://dniruc.apisperu.com/api/v1/dni/" + dni + token;
        try {
            URL url = new URL(enlace);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            if (root.isJsonObject()) {
                JsonObject rootobj = root.getAsJsonObject();
                String apellido_paterno = rootobj.get("apellidoPaterno").getAsString();
                String apellido_materno = rootobj.get("apellidoMaterno").getAsString();
                String nombres = rootobj.get("nombres").getAsString();

                System.out.println("Resultado\n");
                System.out.println(apellido_paterno + "\n" + apellido_materno + "\n" + nombres + "\n");

                ven.setNOMVEN(nombres);
                ven.setAPEVEN(apellido_paterno + " " + apellido_materno);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "DNI no encontrado"));
        }
    }

    public static void main(String[] args) throws Exception {
        Cliente cli = new Cliente();
        cli.setDNICLI("73934789");
        buscarDni(cli);
    }
    
    
    
//    public static void buscarRuc(Persona per) throws Exception {
//        String ruc = per.getRuc();
//        String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imp1bGlvLnF1aXNwZUB2YWxsZWdyYW5kZS5lZHUucGUifQ.6M-P2QMMvKFZEeMvTUXvkOooM02N_pWqt0OdlaYW3PM";
//        String enlace = "https://dniruc.apisperu.com/api/v1/ruc/" + ruc + token;
//        try {
//            URL url = new URL(enlace);
//            URLConnection request = url.openConnection();
//            request.connect();
//
//            JsonParser jp = new JsonParser();
//            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//            if (root.isJsonObject()) {
//                JsonObject rootobj = root.getAsJsonObject();
//                String razon = rootobj.get("razonSocial").getAsString();
//                String direccion = rootobj.get("direccion").getAsString();
//                String ubigeo = rootobj.get("ubigeo").getAsString();
//
//                System.out.println("Resultado\n");
//                System.out.println(razon + "\n" + direccion + "\n" + ubigeo + "\n");
//
//                per.setRazon(razon);
//                per.setDirección(direccion);
//                per.setUbigeo(ubigeo);
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "RUC no encontrado"));
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        Persona per = new Persona();
//        per.setDni("41298813");
//        per.setRuc("20491265737");
//        buscarDni(per);
//        buscarRuc(per);
//    }

    public static void buscarDni(Vendedor ven) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
