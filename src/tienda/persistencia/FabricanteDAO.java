/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tienda.entidades.Fabricante;

/**
 *
 * @author matic
 */
public final class FabricanteDAO extends DAO {

    public void agregarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante");
            }
            String sql = "INSERT INTO Fabricante (codigo, nombre)"
                    + "VALUES ( " + fabricante.getCodigo() + " , '" + fabricante.getNombre() + "' );";

            System.out.println(sql);
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public List<Fabricante> listaFabricantes() throws Exception {

        try {

            String sql = "SELECT * FROM Fabricante ";

            consultarBase(sql);
            List<Fabricante> fabricantes = new ArrayList();
            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));

                fabricantes.add(fabricante);

            }
            desconectarBase();
            return fabricantes;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }

    public int seleccionarCodigoFabricante(String nombre) throws Exception {

        if (nombre.isEmpty()) {
            System.out.println("No ha ingresado un nombre de fabricante. Por favor hágalo ahora:");
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            nombre = leer.next();
        }

        int codigo = 0;
        String sql = "SELECT codigo FROM Fabricante where nombre like '" + nombre + "'";

        consultarBase(sql);

        if (resultado.next()) {

            codigo = resultado.getInt(1);
        }

        return codigo;
    }

    public int codigoFabricante() throws Exception {

        try {
            int codigo = 0;
            String sql = "SELECT max(codigo) FROM Fabricante;";

            consultarBase(sql);

            if (resultado.next()) {
                codigo = resultado.getInt(1);

            }

            desconectarBase();
            return codigo;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public String seleccionarFabricantePorNombre(int codigo) throws Exception {

        if (codigo < 0) {
            System.out.println("No ha ingresado un codigo válido. Por favor hágalo ahora:");
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            codigo = leer.nextInt();
        }

        String nombre = "";
        String sql = "SELECT nombre FROM Fabricante where codigo = " + codigo + "";
        consultarBase(sql);

        if (resultado.next()) {

            nombre = resultado.getString(1);
        }

        return nombre;
    }

    public List<Fabricante> controlFabricanteEnLista(String nombre) throws Exception {

        try {

            String sql = "SELECT codigo FROM Fabricante where nombre like '" + nombre + "'";

            consultarBase(sql);
            List<Fabricante> fabricantes = new ArrayList();
            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));

                fabricantes.add(fabricante);
            }
            desconectarBase();
            return fabricantes;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
}
