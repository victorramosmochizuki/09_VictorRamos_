
package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Consulta;


public class ConsultaD extends Conexion implements ICRUD<Consulta>{

    @Override
    public void registrar(Consulta modelo) throws Exception {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Consulta modelo) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Consulta modelo) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consulta> listar() throws Exception {
        
        
        List<Consulta> listado = null;
        this.conectar();
        
        try {
            
            System.out.println("Haciendo consulta");
            
            String sql = "SELECT * FROM vBOLETABD";
            
            
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            listado = new ArrayList();
            
            while (rs.next()) {
                
                Consulta consulta = new Consulta();
                
                consulta.setIDBOLE(rs.getInt("IDBOLE"));
                consulta.setFECHEMBOLE(rs.getDate("FECHEMBOLE"));
                consulta.setIDCLI(rs.getInt("IDCLI"));
                consulta.setIDVEN(rs.getInt("IDVEN"));
                consulta.setIMPBOLE(rs.getDouble("IMPBOLE"));
                
                   
                listado.add(consulta);
                System.out.println("Enviando Consulta");
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            System.out.println("Error en CONSULTAS D LISTA: " + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
         
    }
    
    
    
    
    
    
    public List<Consulta> listarBD(int codigoVta) throws Exception {
        
        
        List<Consulta> detalle = new ArrayList();
        Consulta cons;
//        this.conectar();
        
        try {
            
            CallableStatement ps = this.conectar().prepareCall("{call spDetalleVta(?)}");
            ps.setInt(1, codigoVta);
            ResultSet rs = ps.executeQuery();
            
            System.out.println("Haciendo consulta");
            
//            String sql = "SELECT * FROM BOLETA_DETALLE";
            
            
//            Statement st = this.conectar().createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            listado = new ArrayList();
            
            while (rs.next()) {
                
                cons = new Consulta();
                
                cons.setIDBODE(rs.getInt("IDBODE"));
                cons.setCANTBODE(rs.getInt("CANTBODE"));
                cons.setIDBOLE(rs.getInt("IDBOLE"));
                cons.setIDMED(rs.getInt("IDMED"));
                cons.setSUBTOT(rs.getDouble("SUBTOT"));
     
                detalle.add(cons);
                
                System.out.println("Enviando Consulta");
                }
        } catch (Exception e) {
            System.out.println("Error en ConsultaD/listarBD " + e.getMessage());
        }
        return detalle;
    }
    
    
    
    
    
}
