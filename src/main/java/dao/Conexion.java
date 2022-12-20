package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    public static Connection cnx = null;
    
    public static Connection conectar() throws  Exception{
        
        try {
            
            String user = "VICTOR1";
            String pwd = "prueba1";
            String driver = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521/XE";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión");
            System.out.println("error de conexion " + e.getMessage());
        }
        return cnx;
    }
    
    
    public static Connection getCnx() {
        return cnx;
    }

    public static void setCnx(Connection aCnx) {
        cnx = aCnx;
    }

    public void cerrar() throws Exception {
        if (cnx != null) {
            cnx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        try {
            if (cnx != null) {
                System.out.println("Correcto, conectado");
                JOptionPane.showMessageDialog(null, "CONECTADO", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("SIN CONEXIÓN REVISA");
                JOptionPane.showMessageDialog(null, "CONEXIÓN FALLIDA", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error en " + e.getMessage());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }


    
    
    
    
//    public void cerrar() throws Exception{
//        if(cnx !=null){
//            cnx.close();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        conectar();
//        if(cnx!=null){
//            System.out.println("esta abierta, jojolete");
//        }else{
//            System.out.println("fijate el driver, conexión cerrada, etc....");
//        }
//    }
//    
//    
//    public Connection getCn() {
//        return cnx;
//    }
//
//    public void setCn(Connection cnx) {
//        this.cnx = cnx;
//    }
    
}
