package dao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.List;
import modelo.Cliente;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void registrar(Cliente modelo) throws Exception {
        String sql = "INSERT INTO CLIENTE (NOMCLI, APECLI, DNICLI, SEXCLI, TELFCLI, ESTCLI) VALUES (UPPER(?),UPPER(?),?,?,?,?)";
        try (Connection conec = (Connection) this.getCn()) {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMCLI());
            ps.setString(2, modelo.getAPECLI());
            ps.setString(3, modelo.getDNICLI());
            ps.setString(4, modelo.getSEXCLI());
            ps.setString(5, modelo.getTELFCLI());
            ps.setString(6, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar ClienteImpl:" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Cliente modelo) throws Exception {
        String sql = "UPDATE CLIENTE SET NOMCLI=UPPER(?), APECLI=UPPER(?), DNICLI=?, SEXCLI=?, TELFCLI=? WHERE IDCLI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMCLI());
            ps.setString(2, modelo.getAPECLI());
            ps.setString(3, modelo.getDNICLI());
            ps.setString(4, modelo.getSEXCLI());
            ps.setString(5, modelo.getTELFCLI());
            ps.setInt(6, modelo.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en modificar ClienteImpl" + e.getMessage());
        }
    }

    public void eliminar(Cliente modelo) throws Exception {
        String sql = "DELETE FROM CLIENTE WHERE IDCLI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR ClienteImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void desactivar(Cliente modelo) throws Exception {
        String sql = "UPDATE CLIENTE SET ESTCLI=? WHERE IDCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en DESACTIVAR ClienteImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<Cliente> listar(int Listado) throws Exception {
        List<Cliente> listado = null;
        Cliente cliente;
        String sql = "";
        switch (Listado) {
            case 1:
                sql = "SELECT * FROM CLIENTE WHERE ESTCLI = 'A' ORDER BY IDCLI DESC";
                break;
            case 2:
                sql = "SELECT * FROM CLIENTE WHERE ESTCLI = 'I' ORDER BY IDCLI DESC";
                break;
            case 3:
                sql = "SELECT * FROM CLIENTE ORDER BY IDCLI DESC";
                break;
        }
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIDCLI(rs.getInt("IDCLI"));
                cliente.setNOMCLI(rs.getString("NOMCLI"));
                cliente.setAPECLI(rs.getString("APECLI"));
                cliente.setDNICLI(rs.getString("DNICLI"));
                cliente.setSEXCLI(rs.getString("SEXCLI"));
                cliente.setTELFCLI(rs.getString("TELFCLI"));
                listado.add(cliente);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar ClienteImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public boolean existe(Cliente modelo, List<Cliente> listaModelo) {
        for (Cliente cli : listaModelo) {
            if (modelo.getDNICLI().equals(cli.getDNICLI())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Cliente> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
