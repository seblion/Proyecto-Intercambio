package Persistencia;
import java.util.ArrayList;
import java.util.Collection;

public class EstudianteDAO extends DAO{

        /**
         * g) Ingresar un fabricante a la base de datos
         *
         * @param estudiante nombre del fabricante a ingresar a la base de datos
         * @throws Exception
         */
        public void guardarEstudiante(String estudiante) throws Exception {
            try {
                //Armado de la sentencia nativa query con el String pasado como parámetro
                String sql = "INSERT INTO Estudiante (Nombre,Contrasena,Celular,Correo) VALUES ('" + estudiante + "')";

                //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
                insertarModificarEliminar(sql);

            } catch (Exception e) {
                System.out.println(e.toString());;
            } finally {
                desconectarBase();
            }
        }

//        /**
//         * Devolver el fabricante segun el código
//         *
//         * @param codigoFabricante Código del fabricante a buscar
//         * @return Nombre del Fabricante correspondiente al código recibido como parámetro
//         * @throws Exception
//         */
//        public String devolverFabricante(int codigoFabricante) throws Exception {
//            try {
//
//                //Armado de la sentencia query con el código del fabricante recibido como argumento
//                String sql = "SELECT nombre FROM fabricante WHERE codigo = " + codigoFabricante;
//
//                //Ejecuto el método de consulta que devuelve y guarda una tabla en el Objeto ResultSet
//                consultarBase(sql);
//
//            /* Aunque sea un solo String, es necesario seguir ocupando el while con el método next()
//            para obtener el valor y que no arroje errores. */
//                String fabricante = null;
//                while (resultado.next()) {
//                    fabricante = resultado.getString(1);
//                }
//                return fabricante;
//
//            } catch (Exception e) {
//                throw e;
//            } finally {
//                desconectarBase();
//            }
//        }
//
//        /**
//         * Listar a los fabricantes con su código
//         *
//         * @return ArrayList del tipo Fabricante con sus atributos guardados
//         * @throws Exception
//         */
//        public Collection<Fabricante> listarFabricantes() throws Exception {
//            try {
//
//                String sql = "SELECT * FROM fabricante";
//                consultarBase(sql);
//
//                Fabricante fabricante;
//                Collection<Fabricante> fabricantes = new ArrayList();
//                while (resultado.next()) {
//                    fabricante = new Fabricante();
//                    fabricante.setCodigo(resultado.getInt(1));
//                    fabricante.setNombre(resultado.getString(2));
//                    fabricantes.add(fabricante);
//                }
//                return fabricantes;
//
//            } catch (Exception e) {
//                throw e;
//            } finally {
//                desconectarBase();
//            }
//        }

    }

