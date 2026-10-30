package org.excusas.dao;

import org.excusas.conexion.ConexionBD;
import org.excusas.modelo.Excusa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExcusaDaoImpl implements ExcusaDao {
    @Override
    public List<Excusa> consultaBasica() throws SQLException {
        String sql = "SELECT * FROM excusa_entrega ORDER BY nivel_Drama ";
        List<Excusa> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Excusa e = new Excusa();
                e.setAlumno(rs.getString("alumno"));
                e.setExcusa(rs.getString("excusa"));

                e.setNivelDrama(rs.getInt("nivel_drama"));
                lista.add(e);
            }
        }
        return lista;
    }

    @Override
    public List<Excusa> consultaLikeBetween() throws SQLException {
        String sql = "SELECT * FROM excusa_entrega "
                + "WHERE (excusa LIKE '%perro%' OR excusa LIKE '%gato%') "
                + "AND nivelDrama BETWEEN 2 AND 6 "
                + "ORDER BY nivelDrama DESC";
        List<Excusa> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Excusa e = new Excusa();
                e.setExcusa(rs.getString("excusa"));
                e.setNivelDrama(rs.getInt("nivelDrama"));
                lista.add(e);
            }
        }
        return lista;

    }

    @Override
    public List<Excusa> consultaIsNull() throws SQLException {
        String sql = "SELECT * FROM excusa_entrega "
                + "WHERE (excusa LIKE '%perro%' OR excusa LIKE '%gato%') "
                + "AND  BETWEEN 2 AND 6 "
                + "ORDER BY nivelDrama DESC";
        List<Excusa> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Excusa e = new Excusa();
                e.setExcusa(rs.getString("excusa"));
                e.setNivelDrama(rs.getInt("nivelDrama"));
                lista.add(e);
            }
        }
        return lista;
    }
}

    /*@Override
    public List<Excusa> consultaAvanzada() throws SQLException {



        return List.of();
    }
}
*/