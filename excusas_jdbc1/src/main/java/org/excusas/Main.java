package org.excusas;

import org.excusas.dao.ExcusaDao;
import org.excusas.dao.ExcusaDaoImpl;
import org.excusas.modelo.Excusa;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExcusaDao dao = new ExcusaDaoImpl();
        try {
            System.out.println("1.Consulta Basica.");
           for (Excusa e : dao.consultaBasica()){
               System.out.println(e.toString());

           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}