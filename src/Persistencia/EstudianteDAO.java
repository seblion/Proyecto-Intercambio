package Persistencia;
import Logica.Estudiante;

import java.util.ArrayList;
import java.util.Collection;

public  class EstudianteDAO extends DAO{

        /**
         * g) Ingresar un fabricante a la base de datos
         *
         * @param estudiante nombre del fabricante a ingresar a la base de datos
         * @throws Exception
         */
        public void guardarEstudiante(String estudiante) throws Exception {
            try {
                //Armado de la sentencia nativa query con el String pasado como parámetro
                String sql = "INSERT INTO Estudiante (NOMBRE,APELLIDO,CORREO,CELULAR,USUARIO,CLAVE) VALUES ('" + estudiante + "')";
                //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
                insertarModificarEliminar(sql);

            } catch (Exception e) {
                throw e;
            } finally {
                desconectarBase();
            }
        }

//
//        /**
//         * Devolver estudiante segun el código
//         *
//         * @param identificador Identificador del estudiante a buscar
//         * @return Nombre del estdiante correspondiente al recibido como parámetro
//         * @throws Exception
//         */
//
//        public Estudiante devolverEstudiante(String identificador) throws Exception {
//            try {
//
//                //Armado de la sentencia query con el id del estudiante recibido como argumento
//                String sql = "SELECT nombre FROM fabricante WHERE identificador = " + identificador;
//
//                //Ejecuto el método de consulta que devuelve y guarda una tabla en el Objeto ResultSet
//                consultarBase(sql);
//
//            /* Aunque sea un solo String, es necesario seguir ocupando el while con el método next()
//            para obtener el valor y que no arroje errores. */
//                Estudiante estudiante = null;
//                while (resultado.next()) {
//                    fabricante = resultado.getString(1);
//                }
//                return estudiante;
//
//            } catch (Exception e) {
//                throw e;
//            } finally {
//                desconectarBase();
//            }
//        }
//
//        /**
//         * Listar los correos de los estudiantes
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

