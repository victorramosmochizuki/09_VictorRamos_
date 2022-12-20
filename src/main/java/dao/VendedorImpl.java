package dao;

import java.util.List;
import modelo.Vendedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendedorImpl extends Conexion implements ICRUD<Vendedor> {

    @Override
    public void registrar(Vendedor modelo) throws Exception {
        String sql = "INSERT INTO VENDEDOR (NOMVEN, APEVEN, DNIVEN, EMAVEN, TELFVEN, DIRVEN, IDUBI, ESTVEN) "
                + "VALUES (UPPER(?),UPPER(?),?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMVEN());
            ps.setString(2, modelo.getAPEVEN());
            ps.setString(3, modelo.getDNIVEN());
            ps.setString(4, modelo.getEMAVEN());
            ps.setString(5, modelo.getTELFVEN());
            ps.setString(6, modelo.getDIRVEN());
            ps.setString(7, modelo.getIDUBI());
            ps.setString(8, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar VendedorImpl:" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Vendedor modelo) throws Exception {
        String sql = "UPDATE VENDEDOR SET NOMVEN=?, APEVEN=?, DNIVEN=?, EMAVEN=?, TELFVEN=?, DIRVEN=?, IDUBI=? WHERE IDVEN=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMVEN());
            ps.setString(2, modelo.getAPEVEN());
            ps.setString(3, modelo.getDNIVEN());
            ps.setString(4, modelo.getEMAVEN());
            ps.setString(5, modelo.getTELFVEN());
            ps.setString(6, modelo.getDIRVEN());
            ps.setString(7, modelo.getIDUBI());
            ps.setString(8, modelo.getIDUBI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en modificar VendedorImpl" + e.getMessage());
        }
    }

    @Override
    public void eliminar(Vendedor modelo) throws Exception {
        String sql = "DELETE FROM VENDEDOR WHERE IDVEN=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDVEN());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR VendedorImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    
    public void desactivar(Vendedor modelo) throws Exception {
        String sql = "UPDATE VENDEDOR SET ESTVEN=? WHERE IDVEN=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDVEN());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en DESACTIVAR VendedorImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }


    public List<Vendedor> listar(int Listado) throws Exception {
        List<Vendedor> listado = null;
        Vendedor vend;
        String sql = "";
        switch (Listado) {
            case 1:
                sql = "SELECT * FROM VENDEDOR WHERE ESTVEN = 'A' ORDER BY IDVEN DESC";
                break;
            case 2:
                sql = "SELECT * FROM VENDEDOR WHERE ESTVEN = 'I' ORDER BY IDVEN DESC";
                break;
            case 3:
                sql = "SELECT * FROM VENDEDOR ORDER BY IDVEN DESC";
                break;
        }
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                vend = new Vendedor();
                vend.setNOMVEN(rs.getString("NOMVEN"));
                vend.setAPEVEN(rs.getString("APEVEN"));
                vend.setDNIVEN(rs.getString("DNIVEN"));
                vend.setEMAVEN(rs.getString("EMAVEN"));
                vend.setTELFVEN(rs.getString("TELFVEN"));
                vend.setDIRVEN(rs.getString("DIRVEN"));
                vend.setIDUBI(rs.getString("IDUBI"));
                listado.add(vend);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listaTodos VendedorImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<String> autocompleteUbigeo(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT TOP 5 CONCAT(DISUBI, ', ', PROUBI, ', ',DEPUBI) AS UBIGEODESC FROM UBIGEO WHERE DISUBI LIKE ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("UBIGEODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteUbigeoDao" + e.getMessage());
        }
        return lista;
    }

    public String obtenerCodigoUbigeo(String cadenaUbi) throws SQLException, Exception {
        String sql = "SELECT IDUBI FROM UBIGEO WHERE CONCAT(DISUBI, ', ', PROUBI, ', ',DEPUBI) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaUbi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDUBI");
            }
            return rs.getString("IDUBI");
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoUbigeo " + e.getMessage());
            throw e;
        }
    }
    
    public boolean existe(Vendedor modelo, List<Vendedor> listaModelo) {
        for (Vendedor cli : listaModelo) {
            if (modelo.getDNIVEN().equals(cli.getDNIVEN())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vendedor> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
