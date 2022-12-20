package servicios;

import static dao.Conexion.conectar;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class EmailS {



    public static void sendIngresoUsuario(String usu) throws UnknownHostException, Exception {
        Usuario usuario = null;
        String sql = "SELECT\n"
                + "U.NOMUSU AS NOMUSU,\n"
                + "FROM \n"
                + "USUARIO U\n";
        Statement st = conectar().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setNOMUSU(rs.getString("NOMUSU"));
            usuario.setEMAUSU(rs.getString("EMAUSU"));
        }
        rs.close();
        st.close();

        // El correo gmail de envio
        String remitente = "Certidigitaleducativo@gmail.com";
        String clave = "glcjjdmfpvtbbmom";

        //Destinatario segun el usuario en el login
//        String destinatario = usuario.getEmail();
        String destinatario = "victor.ramos@vallegrande.edu.pe";

        //IP, fecha y hora
        String ip = InetAddress.getLocalHost().getHostAddress();
        String fecha = new SimpleDateFormat("dd/MMMM/yyyy").format(Calendar.getInstance().getTime());
        String hora = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

        //Asunto, cuerpo y la notificacion de inicio de sesion
        String asunto = "NUEVO INICIO DE SESIÓN DETECTADO DE UN USUARIO EN SISREGVG";
        String cuerpo = "Buen día ADMINISTRADOR, tenemos NOVEDADES para usted."
                + "\n Se ha detectado un nuevo iniciado sesión, por parte de la persona con nombre :" + usuario.getNOMUSU()+ " " + usuario.getNOMUSU() + "\n"
                + "Recientemente accedió a  SISREGVG, con el usuario : " + usuario.getNOMUSU() + "\n"
                + "A continuación se muestran algunos detalles que pueden ayudar a garantizar la seguridad del Sistema: " + "\n"
                + "\n País o región: Perú" + "\n"
                + "\n El numero de IP, es :  " + ip + "\n"
                + "\n Ingresó en la Fecha :  " + fecha + "\n"
                + "\n Su ingreso se registró a la Hora :  " + hora + "\n"
                + "\n Gracias, le estaremos comunicando cualquier movimiento de usuario o fallas en el serivor en SISREGVG.";
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
            System.out.println("Error en enviarNotificacion_S " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    

    


}
