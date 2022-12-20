package servicios;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import modelo.Proveedor;
import modelo.Usuario;
import modelo.Vendedor;

//import oracle.net.aso.n;

public class JavaMail {

    public static void enviarContraseña(Usuario usu) {  
        String remitente = "otaner983@gmail.com";
        String clave = "renato2020HUAMAN+";
        String destinatario = usu.getEMAUSU();
        String asunto = "FARMAVIC";
        String cuerpo = "Buenos días usuario: " + usu.getNOMUSU()+" " + usu.getNIVUSU() + "\n" +
                "\n su nombre de usuario es:" + usu.getEMAUSU() + "\n" + 
                "\n y su contraseña es: @1234gV@";
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try { 
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            BodyPart texto = new MimeBodyPart();
            texto.setText(cuerpo);

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            message.setContent(multiParte);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
            ex.printStackTrace();
            System.out.println("Error verifica: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            Usuario usu = new Usuario();
            usu.setEMAUSU("victor.ramos@vallegrande.edu.pe");
            JavaMail.enviarContraseña(usu);
            System.out.println("CORREO ENVIADO");
            JOptionPane.showMessageDialog(null, "CORREO ENVIADO", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.out.println("Error en mandarCorreo/mail " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "NO SE ENVIO EL CORREO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
