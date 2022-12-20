package controlador;

import dao.UsuarioD;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.Data;
import modelo.Medicamento;
import modelo.Usuario;
import servicios.JavaMail;
import static servicios.JavaMail.enviarContraseña;
import servicios.JavaMailv2;
import static servicios.JavaMailv2.enviarEmail;
import servicios.Password;

@Data
@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable {

    Usuario usuario;
    UsuarioD dao;
    private int captcha = 0;
    private int intentos = 0;
    private boolean bloquear;

    public UsuarioC() {
        usuario = new Usuario();
        dao = new UsuarioD();

    }

    public void login() throws Exception {
        try {
            dao.login(usuario);
        } catch (Exception e) {
            System.out.println("Error en USUARIOC  login_C " + e.getMessage());
        }
    }

    public void loginNivel() throws Exception {
        try {
            dao.loginNivel(usuario);
        } catch (Exception e) {
            System.out.println("Error en USUARIOC loginNivel_C" + e.getMessage());
        }
    }

    public void acceso() throws Exception {
        try {
            usuario = dao.login(usuario);
            this.login();
            if (dao.logueo == false) {
                intentos++;
                if (intentos == 1) {
                    setIntentos(1);
                    setCaptcha(0);
                    //enviarContraseña(usuario);
                    JavaMailv2.enviarEmail(usuario);
                    System.out.println("intentos iguales " + intentos);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "1 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "LE QUEDAN 2 INTENTOS", ""));
                }
                if (intentos == 2) {
                    setIntentos(2);
                    setCaptcha(1);
//                    bloquear = true;
                    System.out.println("intentos igual " + intentos);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "2 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "LE QUEDA 1 INTENTO", ""));
                }
                if (intentos == 3) {
                    System.out.println("intentos igual " + intentos);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "3 INTENTO FALLIDO", "Usuario/Contraseña incorrectas"));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "BLOQUEO DE SEGURIDAD", ""));
                    setIntentos(3);
//                    bloquear = true;
                    if (bloquear) {
                        delayTiempo();
                    }
                    if (intentos == 3) {
                        setIntentos(0);
                        setCaptcha(0);
                        bloquear = true;
                    }
                }
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario", usuario);
                this.loginNivel();
                if (dao.nivel == 1) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/FARMAVIC_ODAO/faces/vistas/Administrador.xhtml");
                }
                if (dao.nivel == 2) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/FARMAVIC_ODAO/faces/vistas/personal/Personal.xhtml");
                }
                if (dao.nivel == 3) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡BIENVENIDO!", "Ingreso Exitoso"));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/FARMAVIC_ODAO/faces/vistas/usuario/Usucliente.xhtml");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en Acceso_C " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void enviarCorreo() {
        try {
            enviarContraseña(usuario);
            //System.out.println(usuario.getAPEVEN());
        } catch (Exception e) {
            System.out.println("error en enviar correo " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void delayTiempo() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Error en delaySegundo_C " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Obtener el objeto de la sesión activa
    public static Usuario obtenerObjetoSesion() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().get("objetoUsuario");
    }

    // Si la sesión no está iniciada no permitirá entrar a otra vista de la aplicación
    public void seguridadSesion() throws IOException {
        if (obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("http://localhost:8080/FARMAVIC_ODAO/");
        }
    }

    // Cerrar y limpiar la sesión y direccionar al xhtml inicial del proyecto
    public void cerrarSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/FARMAVIC_ODAO/faces/Login.xhtml");
    }

    // Si la sesión está activa se redirecciona a la vista principal
    public void seguridadLogin() throws IOException {
        Usuario us = obtenerObjetoSesion();
        if (us != null) {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("/FARMAVIC_ODAO/faces/vistas/Login.xhtml?faces-redirect=true");
        }
    }

    public void passAleatorio() throws Exception {
        try {
            Password.passAleatorio2(usuario);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "error", "No genero password"));
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(usuario);

            if (dao.validar.equals(true)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "La contraseña ha sido modificada con éxito"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Ingrese correctamente los datos"));
            }
            limpiar();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "error en cambiar contraseña {0}", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR ", "error en cambiar contraseña"));
        }
    }

    public void limpiar() {
        usuario = new Usuario();
    }

}
