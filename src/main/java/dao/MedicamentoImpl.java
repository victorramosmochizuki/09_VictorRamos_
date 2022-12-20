package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.internet.ParseException;
import modelo.Medicamento;
import servicios.UtilToSql;



public class MedicamentoImpl extends Conexion implements ICRUD<Medicamento> {
    
    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException, java.text.ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    @Override
    public void registrar(Medicamento modelo) throws Exception {
        String sql = "INSERT INTO MEDICAMENTO (PRESMED, GENMED, COMMED, PRECMED, FVMED, STOCMED, "
                + "LOTMED, IDPROV, ESTMED) "
                + "VALUES (LOWER(?),LOWER(?),LOWER(?),?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getPRESMED());
            ps.setString(2, modelo.getGENMED());
            ps.setString(3, modelo.getCOMMED());
            ps.setDouble(4, modelo.getPRECMED());
            ps.setString(5, formatter.format(modelo.getFVMED()));
            ps.setInt(6, modelo.getSTOCMED());
            ps.setString(7, modelo.getLOTMED());
            ps.setString(8, modelo.getIDPROV());
            ps.setString(9, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar MedicamentoImpl:" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Medicamento modelo) throws Exception {
        String sql = "UPDATE MEDICAMENTO SET PRESMED=LOWER(?), GENMED=LOWER(?), COMMED=LOWER(?), PRECMED=?, FVMED=?, STOCMED=?, LOTMED=?, IDPROV=? WHERE IDMED=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getPRESMED());
            ps.setString(2, modelo.getGENMED());
            ps.setString(3, modelo.getCOMMED());
            ps.setDouble(4, modelo.getPRECMED());
            ps.setString(5, formatter.format(modelo.getFVMED()));
            ps.setInt(6, modelo.getSTOCMED());
            ps.setString(7, modelo.getLOTMED());
            ps.setString(8, modelo.getIDPROV());
            ps.setInt(9, modelo.getIDMED());
            System.out.println("algo pasa");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en modificar MedicamentoImpl" + e.getMessage());
        }
    }

    @Override
    public void eliminar(Medicamento modelo) throws Exception {
        String sql = "DELETE FROM MEDICAMENTO WHERE IDMED=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDMED());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR MedicamentoImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void eliminar2(Medicamento modelo) throws Exception {
        String sql = "DELETE FROM MEDICAMENTO WHERE IDMED=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDMED());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR MedicamentoImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void desactivar(Medicamento modelo) throws Exception {
        String sql = "UPDATE MEDICAMENTO SET ESTMED=? WHERE IDMED=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDMED());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en DESACTIVAR MedicamentoImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<Medicamento> listar(int Listado) throws Exception {
        List<Medicamento> listado = null;
        Medicamento medi;
        String sql = "";
        switch (Listado) {
            case 1:
                sql = "SELECT * FROM P_MEDICAMENTO_LA";
                break;
            case 2:
                sql = "SELECT * FROM P_MEDICAMENTO_LI";
                break;
            case 3:
                sql = "SELECT * FROM P_MEDICAMENTO_L";
                break;
        }
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                medi = new Medicamento();
                medi.setIDMED(rs.getInt("IDMED"));
                medi.setPRESMED(rs.getString("PRESMED"));
                medi.setGENMED(rs.getString("GENMED"));
                medi.setCOMMED(rs.getString("COMMED"));
                medi.setPRECMED(rs.getDouble("PRECMED"));
                medi.setFVMED(rs.getDate("FVMED"));
                medi.setSTOCMED(rs.getInt("STOCMED"));
                medi.setLOTMED(rs.getString("LOTMED"));
                medi.setIDPROV(rs.getString("IDPROV"));
                listado.add(medi);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar MedicamentoImpl: " + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<String> autocompleteProveedor(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT NOMPROV || ', ' || RUCPROV || ', ' ||TIPPROV AS PROVEEDORDESC FROM PROVEEDOR WHERE NOMPROV LIKE ? AND rownum <= 20";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("PROVEEDORDESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteProveedor" + e.getMessage());
        }
        return lista;
    }

    public String obtenerCodigoProveedor(String cadenaPro) throws SQLException, Exception {
        String sql = "SELECT IDPROV FROM PROVEEDOR WHERE NOMPROV || ', ' || RUCPROV || ', ' ||TIPPROV = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaPro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDPROV");
            }
            return rs.getString("IDPROV");
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoProveedor " + e.getMessage());
            throw e;
        }
    }
    
    public Double precio=0.0;
    public Integer stockMed = 0;
    public String presentacion="", generico="", comercial = "", proveedor="";
    
    public Integer obtenerCodigoMedicamento(String cadenaMed) throws SQLException, Exception {
        Integer codigoMedicamento = 0;
        try {
            CallableStatement ps = this.conectar().prepareCall("{call spDatosAutoCompletMed(?)}");
            ps.setString(1, cadenaMed);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                codigoMedicamento = rs.getInt("IDMED");
                presentacion = rs.getString("PREMED");
                generico = rs.getString("NOMGENMED");
                comercial = rs.getString("NOMCOMMED");
                precio = rs.getDouble("PRECMED");
                stockMed = rs.getInt("STOCMED");
                proveedor = rs.getString("NOMPROV");    
            }            
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoMedicamento " + e.getMessage());            
        }
        return codigoMedicamento;
    }

    @Override
    public List<Medicamento> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


       
    
}
