package org.excusas.dao;

import org.excusas.modelo.Excusa;

import java.sql.SQLException;
import java.util.List;

public interface ExcusaDao {
    List<Excusa> consultaBasica() throws SQLException;

    List<Excusa> consultaLikeBetween() throws SQLException;

    List<Excusa> consultaIsNull() throws SQLException;
}
/*
    List<Excusa> consultaAvanzada() throws SQLException;
}
*/



