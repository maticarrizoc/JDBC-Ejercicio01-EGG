/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.FabricanteDAO;
import tienda.persistencia.ProductoDAO;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author matic
 */
public class ProductoServicio {

    ProductoDAO daoP = new ProductoDAO();
    FabricanteDAO daoF = new FabricanteDAO();
    FabricanteServicio fabricanteServicio = new FabricanteServicio();

    public void nuevoProducto() throws Exception, InputMismatchException {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese el nombre del producto");
            String nombre = leer.next();

            if (nombre.trim().isEmpty()) {
                throw new Exception("No puede ingresar un nombre vacío");
            }

            System.out.println("Ingrese el precio del producto");
            Double precio = leer.nextDouble();

            if (precio <= 0.00) {
                throw new Exception("El precio no puede ser 0 o un valor no numérico");

            }
            System.out.println("Ingrese el nombre del fabricante del producto"
                    + "\n(si desea ver la lista de fabricantes presione 1)");
            String nombreFabricante = leer.next();

            if (daoF.controlFabricanteEnLista(nombreFabricante).isEmpty()) {

                System.out.println("Nombre inexistente. Por favor agregue un nuevo fabricante. Lista de fabricantes:");
                fabricanteServicio.listaFabricantes();

                System.out.println("Ingrese el nombre del fabricante del producto");
                nombreFabricante = leer.next();

            }

            if (nombreFabricante.equalsIgnoreCase("1")) {
                fabricanteServicio.listaFabricantes();
                System.out.println("\nAhora ingrese el nombre del fabricante del producto"
                        + "\n Si desea agregar un nuevo fabricante presione 2");
                nombreFabricante = leer.next();

                if (nombreFabricante.equalsIgnoreCase("2")) {
                    fabricanteServicio.nuevoFabricante();

                    System.out.println("Ingrese el nombre del fabricante del producto"
                            + "\n(si desea ver la lista de fabricantes presione 1)");
                    nombreFabricante = leer.next();

                    if (nombreFabricante.equalsIgnoreCase("1")) {
                        fabricanteServicio.listaFabricantes();
                        System.out.println("\nAhora ingrese el nombre del fabricante del producto");
                        nombreFabricante = leer.next();
                    }
                }
            }

            int idFabricante = daoF.seleccionarCodigoFabricante(nombreFabricante);

            Producto producto = new Producto();
            producto.setCodigo(daoP.codigoProducto() + 1);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(idFabricante);

            daoP.agregarProducto(producto);

        } catch (InputMismatchException e) {
            throw new Exception("El precio debe ser un valor numérico");
        } catch (Exception e) {
            throw e;
        }

    }

    public void modificarProducto() throws Exception, InputMismatchException {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Indique el id del producto que desea modificar");

            int productoAModificar = leer.nextInt();

            if (productoAModificar <= 0 || productoAModificar > daoP.codigoProducto()) {

                System.out.println("El código no ocrresponde a un fabricante");
                modificarProducto();
            } else {

                System.out.println("\nQué dato del producto desea modificar?"
                        + "\nPara cambiar el nombre presione la tecla 1"
                        + "\nPara cambiar el precio presione la tecla 2");

                int opcionACambiar = leer.nextInt();

                if (opcionACambiar != 1 && opcionACambiar != 2) {
                    System.out.println("Opción no válida");
                    modificarProducto();

                }
                if (opcionACambiar == 1) {
                    double precio = 0.0;
                    System.out.println("Ingrese el nuevo nombre del producto");
                    String nuevoNombre = leer.next();

                    if (nuevoNombre.trim().isEmpty()) {
                        throw new Exception("Ingresó un nombre vacío");
                    }
                    daoP.modificarProducto(productoAModificar, nuevoNombre, precio, 1);
                }

                if (opcionACambiar == 2) {
                    String nuevoNombre = "";
                    System.out.println("Ingrese el nuevo precio del producto");
                    Double nuevoPrecio = leer.nextDouble();

                    if (nuevoPrecio <= 0.00) {
                        throw new Exception("El precio debe ser mayor a 0");
                    }

                    daoP.modificarProducto(productoAModificar, nuevoNombre, nuevoPrecio, 2);
                }

            }
        } catch (InputMismatchException e) {
            throw new Exception("El precio debe ser un valor numérico");
        } catch (Exception e) {
            throw e;
        }

    }

    public void listarProductosPorNombre() throws Exception {

        try {

            List<Producto> productos = daoP.seleccionarProductosPorNombreyPrecio("soloNombre");
            if (productos.isEmpty()) {
                throw new Exception("No se encontraron productos");
            } else {
                System.out.println("\nLista de productos: ");
                for (Producto p : productos) {
                    System.out.println("Código: " + p.getCodigo()
                            + " - Nombre: " + p.getNombre());
                }
            }
        } catch (Exception e) {

            throw e;
        }
    }

    public void listarProductosPorNombreYPrecio() throws Exception {

        try {
            List<Producto> productos = daoP.seleccionarProductosPorNombreyPrecio("nombreYPrecio");

            if (productos.isEmpty()) {
                throw new Exception("No se encontraron productos");
            } else {
                System.out.println("\nLista de productos: ");
                for (Producto p : productos) {
                    System.out.println("Código: " + p.getCodigo()
                            + " - Nombre: " + p.getNombre()
                            + " - Precio: " + p.getPrecio());
                }
            }

        } catch (Exception e) {

            throw e;
        }
    }

    public void listarProductoMasBarato() throws Exception {

        try {

            List<Producto> productos = daoP.seleccionarProductosPorNombreyPrecio("masBarato");

            System.out.println("\nSe lista el producto más económico");
            for (Producto p : productos) {
                System.out.println("Producto código " + p.getCodigo()
                            + " - Nombre del producto: " + p.getNombre()
                            + " - Precio: " + p.getPrecio());
            }
        } catch (Exception e) {

            throw e;
        }
    }

    public void listarTablas() throws Exception {

        try {
            List<Producto> productos = daoP.seleccionarTablas();

            if (productos.isEmpty()) {
                throw new Exception("No se encontraron tablets");
            } else {
                System.out.println("\nSe listan las tablets");
                for (Producto p : productos) {

                    String nombreFabricante = daoF.seleccionarFabricantePorNombre(p.getCodigoFabricante());

                    System.out.println("Producto código " + p.getCodigoFabricante()
                            + " - Nombre del producto: " + p.getNombre()
                            + " - Precio: " + p.getPrecio()
                            + " - Fabricante: " + nombreFabricante);
                }
            }
        } catch (Exception e) {

            throw e;
        }
    }

    public void listarRangoDePrecio() throws Exception, InputMismatchException {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {
            System.out.println("Ingrese los valores del rango"
                    + "\nPrimer valor:");
            int n1 = leer.nextInt();
            System.out.println("Segundo valor:");
            int n2 = leer.nextInt();

            List<Producto> productos = daoP.rangoDePrecios(n1, n2);
            if (productos.isEmpty()) {
                throw new Exception("No se encontraron productos en ese rango de precios");
            } else {

                System.out.println("\nSe listan los productos cuyo precio se encuentra entre los valores predefinidos");
                for (Producto p : productos) {

                    String nombreFabricante = daoF.seleccionarFabricantePorNombre(p.getCodigoFabricante());

                    System.out.println("Producto código " + p.getCodigoFabricante()
                            + " - Nombre del producto: " + p.getNombre()
                            + " - Precio: " + p.getPrecio()
                            + " - Fabricante: " + nombreFabricante);
                }
            }
        } catch (InputMismatchException e) {
            throw new Exception("Los valores del rango deben ser numéricos");
        } catch (Exception e) {

            throw e;
        }
    }
}

//    public void crearProducto(int codigo, String nombre, double precio, int codigoFabricante) throws Exception {
//
//        try {
//            //Validamos
//            if (codigo < 0 || codigo.) {
//                throw new Exception("Debe indicar el codigo del producto");
//            }
//
//            if (raza == null || raza.trim().isEmpty()) {
//                throw new Exception("Debe indicar la raza");
//            }
//
//            if (usuario == null) {
//                throw new Exception("Debe indicar el Usuario");
//            }
//
//            //Creamos el mascota
//            Mascota mascota = new Mascota();
//            mascota.setApodo(apodo);
//            mascota.setRaza(raza);
//            mascota.setUsuario(usuario);
//
//            dao.guardarMascota(mascota);
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
//
//        try {
//
//            //Validamos
//            if (codigo < 0) {
//                throw new Exception("Debe indicar el id");
//            }
//            Producto producto = dao.buscarProductoPorCodigo(codigo);
//            //Verificamos
//            if (producto == null) {
//                throw new Exception("No se econtró producto para el codigo¿? indicado");
//            }
//
//            return producto;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    public Collection<Producto> listarProductos() throws Exception {
//
//        try {
//
//            Collection<Producto> productos = dao.listarProductos();
//
//            return productos;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    public void imprimirProductos() throws Exception {
//
//        try {
//
//            //Listamos los productos
//            Collection<Producto> productos = listarProductos();
//
//            //Imprimimos los productos
//            if (productos.isEmpty()) {
//                throw new Exception("No existen productos para imprimir");
//            } else {
//                for (Producto aux : productos) {
//                    System.out.println(aux.toString());
//                }
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//    }
