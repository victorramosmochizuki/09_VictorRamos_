
package servicios;

import static dao.Conexion.conectar;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import modelo.Proveedor;
import modelo.Usuario;


public class JavaMailv3 {
    
    public static void enviarEmail(Proveedor pro) throws UnknownHostException, SQLException, Exception {
        String sql = "SELECT \n"
                + "P.NOMPROV AS NOMPROV, \n"
                + "P.RUCPROV AS RUCPROV \n"
                + "FROM \n"
                + "PROVEEDOR P \n";
        Statement st = conectar().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            
            pro.setNOMPROV(rs.getString("NOMPROV"));
            pro.setRUCPROV(rs.getString("RUCPROV"));
        }
        rs.close();
        st.close();
        
        
        String remitente = "Certidigitaleducativo@gmail.com";
        String clave = "glcjjdmfpvtbbmom";
        String destinatario = "victor.ramos@vallegrande.edu.pe";
        
                        //IP, fecha y hora
        String ip = InetAddress.getLocalHost().getHostAddress();
        String fecha = new SimpleDateFormat("dd/MMMM/yyyy").format(Calendar.getInstance().getTime());
        String hora = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        
        
                //Asunto, cuerpo y la notificacion de inicio de sesion
        String asunto = "NUEVO REGISTRO DE PROVEEDOR DETECTADO DE UN USUARIO EN FARMAVIC";
        String cuerpo = "Buen día ADMINISTRADOR, tenemos NOVEDADES para usted."
                + "\n Se ha detectado un nuevo registro, por parte de la persona con nombre :" + pro.getNOMPROV() + "\n"
                + "Recientemente se registro a  FARMAVIC, con el ruc : "  + pro.getRUCPROV() + "\n"
                + "A continuación se muestran algunos detalles que pueden ayudar a garantizar la seguridad del Sistema: " + "\n"
                + "\n País o región: Perú" + "\n"
                + "\n El numero de IP, es :  " + ip + "\n"
                + "\n Ingresó en la Fecha :  " + fecha + "\n"
                + "\n Su ingreso se registró a la Hora :  " + hora + "\n"
                + "\n Gracias, le estaremos comunicando cualquier movimiento de usuario o fallas en el servidor en FARMAVIC.";
        

        String contraseña = "CONTRASEÑA DE ENVIO";
        
        

        
        
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
            System.out.println("Error en enviarEmail " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            Proveedor proveedor = new Proveedor();
            proveedor.setEMAPROV("victor.ramos@vallegrande.edu.pe");
            JavaMailv3.enviarEmail(proveedor);
            System.out.println("CORREO ENVIADO");
        } catch (Exception ex) {
            System.out.println("ERROR AL ENVIAR CORREO");
            System.out.println("Error en enviarEmail_S " + ex.getMessage());
        }
    }
       
}



