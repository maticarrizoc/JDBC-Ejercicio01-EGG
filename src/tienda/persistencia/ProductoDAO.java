/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tienda.entidades.Producto;
import java.util.InputMismatchException;

/**
 *
 * @author matic
 */
public final class ProductoDAO extends DAO {

    public void agregarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto");
            }
            String sql = "INSERT INTO Producto (codigo, nombre, precio, codigo_fabricante)"
                    + "VALUES ( " + producto.getCodigo() + " , '" + producto.getNombre() + "' , " + producto.getPrecio() + " ," + producto.getCodigoFabricante() + " );";

            System.out.println(sql);
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarProducto(int id, String name, double price, int option) throws Exception {
        try {

            if (option == 1) {

                String sql = "UPDATE Producto SET "
                        + " nombre = '" + name
                        + "' WHERE codigo = " + id + "";

                insertarModificarEliminar(sql);
            }

            if (option == 2) {

                String sql = "UPDATE Producto SET precio = " + price + " WHERE codigo = " + id + "";

                insertarModificarEliminar(sql);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public List<Producto> seleccionarProductosPorNombreyPrecio(String option) throws Exception {

        try {

            String sql = "";
            List productos = null;
            if (option.equalsIgnoreCase("soloNombre")) {

                sql = "SELECT codigo, nombre FROM Producto";

                consultarBase(sql);
                productos = new ArrayList();
                Producto producto = null;

                while (resultado.next()) {
                    producto = new Producto();
                    producto.setCodigo(resultado.getInt(1));
                    producto.setNombre(resultado.getString(2));

                    productos.add(producto);
                }
            }
            if (option.equalsIgnoreCase("nombreYPrecio")) {
                sql = "SELECT codigo, nombre, precio FROM Producto";

                consultarBase(sql);
                productos = new ArrayList();
                Producto producto = null;

                while (resultado.next()) {
                    producto = new Producto();
                    producto.setCodigo(resultado.getInt(1));
                    producto.setNombre(resultado.getString(2));
                    producto.setPrecio(resultado.getDouble(3));
//                    producto.setCodigoFabricante(resultado.getInt(4));

                    productos.add(producto);

                }
            }
            if (option.equalsIgnoreCase("masBarato")) {
                sql = "SELECT codigo, nombre, precio FROM Producto order by precio limit 1";

                consultarBase(sql);
                productos = new ArrayList();
                Producto producto = null;

                while (resultado.next()) {
                    producto = new Producto();
                    producto.setCodigo(resultado.getInt(1));
                    producto.setNombre(resultado.getString(2));
                    producto.setPrecio(resultado.getDouble(3));
//                    producto.setCodigoFabricante(resultado.getInt(4));

                    productos.add(producto);
                }
            }
            desconectarBase();
            return productos;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }

    public List<Producto> seleccionarTablas() throws Exception {

        try {

            String sql = "SELECT * FROM Producto "
                    + " WHERE nombre like '%Portátil%'";

            consultarBase(sql);
            List<Producto> productos = new ArrayList();
            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                productos.add(producto);

            }
            desconectarBase();
            return productos;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }

    public List<Producto> rangoDePrecios(int n1, int n2) throws Exception {

        try {

            String sql = "SELECT * FROM Producto "
                    + " WHERE precio between " + n1 + " and " + n2 + "";

            consultarBase(sql);
            List<Producto> productos = new ArrayList();
            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                productos.add(producto);
            }
            desconectarBase();
            return productos;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }

    public int codigoProducto() throws Exception {

        try {
            int id = 0;

            String sql = "SELECT max(codigo) FROM Producto;";
            consultarBase(sql);

            if (resultado.next()) {
                id = resultado.getInt(1);
            }

            desconectarBase();
            return id;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

//    public void modificarProducto(Producto producto) throws Exception {
//        try {
//            if (producto == null) {
//                throw new Exception("Debe indicar el producto que desea modificar");
//            }
//            String sql = "UPDATE Producto SET "
//                    + " codigo = " + producto.getCodigo() + " , nombre = '" + producto.getNombre() + "' , precio = " + producto.getPrecio() + " , codigo_fabricante = " + producto.getCodigoFabricante()
//                    + " WHERE codigo = " + producto.getCodigo() + ";";
//            insertarModificarEliminar(sql);
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            desconectarBase();
//        }
//    }
//
//    public void eliminarProducto(int codigo) throws Exception {
//        try {
//            String sql = "DELETE FROM Producto WHERE codigo = " + codigo + "";
//            insertarModificarEliminar(sql);
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            desconectarBase();
//        }
//    }
//
//    public Producto buscarProductoPorNombre(String nombre) throws Exception {
//        try {
//            String sql = "SELECT * FROM Producto WHERE nombre LIKE '%" + nombre + "%'";
//            consultarBase(sql);
//            Producto producto = null;
//            while (resultado.next()) {
//                producto = new Producto();
//                producto.setCodigo(resultado.getInt(1));
//                producto.setNombre(resultado.getString(2));
//                producto.setPrecio(resultado.getDouble(3));
//                producto.setCodigoFabricante(resultado.getInt(4));
//
////                Integer idUsuario = resultado.getInt(4);
////                Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
////                mascota.setUsuario(usuario);
////                Lo anterior se hace si la clave foranea se pasa como objeto
//            }
//            desconectarBase();
//            return producto;
//        } catch (Exception e) {
//            desconectarBase();
//            throw e;
//        }
//    }
//
//    public Collection<Producto> listarProductosPorNombre() throws Exception {
//        try {
//            String sql = "SELECT nombre FROM Producto ";
//            consultarBase(sql);
//            Producto producto = null;
//            Collection<Producto> productos = new ArrayList();
//            while (resultado.next()) {
//                producto = new Producto();
//                 producto.setNombre(resultado.getString(1));
//            }
//            desconectarBase();
//            return productos;
//        } catch (Exception e) {
//            e.printStackTrace();
//            desconectarBase();
//            throw e;
//        }
//    }
//
//    public Collection<Producto> listarProductosPorNombreYPrecio() throws Exception {
//        try {
//            String sql = "SELECT nombre, precio FROM Producto ";
//            consultarBase(sql);
//            Producto producto = null;
//            Collection<Producto> productos = new ArrayList();
//            while (resultado.next()) {
//                producto = new Producto();
//                producto.setNombre(resultado.getString(1));
//                producto.setPrecio(resultado.getDouble(2));
//            }
//            desconectarBase();
//            return productos;
//        } catch (Exception e) {
//            e.printStackTrace();
//            desconectarBase();
//            throw e;
//        }
//    }
//
//    public Collection<Producto> listarProductos() throws Exception {
//        try {
//            String sql = "SELECT * FROM Producto ";
//            consultarBase(sql);
//            Producto producto = null;
//            Collection<Producto> productos = new ArrayList();
//            while (resultado.next()) {
//                producto = new Producto();
//                producto.setCodigo(resultado.getInt(1));
//                producto.setNombre(resultado.getString(2));
//                producto.setPrecio(resultado.getDouble(3));
//                producto.setCodigoFabricante(resultado.getInt(4));
//
////                Integer idUsuario = resultado.getInt(4);
////                Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
////                mascota.setUsuario(usuario);
////                mascotas.add(mascota);
////                Lo anterior se hace si la clave foranea se pasa como objeto
//            }
//            desconectarBase();
//            return productos;
//        } catch (Exception e) {
//            e.printStackTrace();
//            desconectarBase();
//            throw e;
//        }
//    }
//    
//        public Collection<Producto> listarProductosPorNombreYPrecioMasBarato() throws Exception {
//        try {
//            String sql = "SELECT nombre, min(precio) FROM Producto LIMIT 1  ";
//            consultarBase(sql);
//            Producto producto = null;
//            Collection<Producto> productos = new ArrayList();
//            while (resultado.next()) {
//                producto = new Producto();
//                producto.setNombre(resultado.getString(1));
//                producto.setPrecio(resultado.getDouble(2));
//            }
//            desconectarBase();
//            return productos;
//        } catch (Exception e) {
//            e.printStackTrace();
//            desconectarBase();
//            throw e;
//        }
//    }
}
