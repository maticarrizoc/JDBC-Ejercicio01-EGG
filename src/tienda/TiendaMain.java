/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.util.Scanner;
import tienda.servicios.FabricanteServicio;
import tienda.servicios.ProductoServicio;

/**
 *
 * @author matic
 */
public class TiendaMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        ProductoServicio productoS = new ProductoServicio();
        FabricanteServicio fabricanteS = new FabricanteServicio();

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        String menu = "";

        System.out.println("Bienvenido");
        do {
            try {

                System.out.println("\nPresione Enter para ver el menu");
                leer.next();
                System.out.println("Por favor, presione la tecla"
                        + "\n1. Para listar los productos por su nombre"
                        + "\n2. Para listar los productos por nombre y precio"
                        + "\n3. Para lista el producto de menor precio presione"
                        + "\n4. Para listar los productos cuyo precio se encuentre entre dos valores"
                        + "\n5. Para listar los productos Portátiles"
                        + "\n6. Para agregar un producto a la base de datos"
                        + "\n7. Para modificar los datos de un producto de la base de datos"
                        + "\n8. Para agregar un fabricante a la base de datos"
                        + "\n9. Para imprimir el listado de fabricante a la base de datos"
                        + "\nS. Para salir");

                menu = leer.next();

                switch (menu) {

                    case "1":
                        productoS.listarProductosPorNombre();
                        break;
                    case "2":
                        productoS.listarProductosPorNombreYPrecio();
                        break;
                    case "3":
                        productoS.listarProductoMasBarato();
                        break;
                    case "4":
                        productoS.listarRangoDePrecio();
                        break;
                    case "5":
                        productoS.listarTablas();
                        break;
                    case "6":
                        productoS.nuevoProducto();
                        break;
                    case "7":
                        productoS.modificarProducto();
                        break;
                    case "8":
                        fabricanteS.nuevoFabricante();
                        break;
                    case "9":
                        fabricanteS.listaFabricantes();
                        break;
                    case "s":
                        System.out.println("Hasta pronto");
                        break;
                    case "S":
                        System.out.println("Hasta pronto");
                        break;
                    default:
                        System.out.println("\nLa opción elegida no es válida");
                        break;

                }
            } catch (Exception e) {

                throw e;
            }

        } while (!menu.equalsIgnoreCase("S"));

    }

}
