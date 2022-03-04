/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.util.List;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

/**
 *
 * @author matic
 */
public class FabricanteServicio {
    
    FabricanteDAO dao = new FabricanteDAO();
    
    public void nuevoFabricante() throws Exception {
        
     Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese el nombre del fabricante");
            String nombre = leer.next();
            
             if(nombre.trim().isEmpty()) {
                throw new Exception("No puede ingresar un nombre vacío");
            }
            
            Fabricante fabricante = new Fabricante();
            fabricante.setCodigo(dao.codigoFabricante()+1);
            fabricante.setNombre(nombre);
            
            dao.agregarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }

    }
    public void listaFabricantes() throws Exception {
        try {
        List<Fabricante> fabricante = dao.listaFabricantes();
        
         if (fabricante.isEmpty()) {
                throw new Exception("No se encontraron fabricantes");
            } else {
             System.out.println("\nLista de fabricantes: ");
                for (Fabricante f : fabricante) {
                    
              System.out.println("Fabricante código " + f.getCodigo()
                      + " - Nombre del fabricante " + f.getNombre());
                }}
                
        } catch (Exception e) {

            throw e;
        }
    }
}
