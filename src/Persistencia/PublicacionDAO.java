package Persistencia;
import Logica.Estudiante;
import Logica.Publicacion;

import java.util.*;
import java.util.List;

public  class PublicacionDAO extends DAO{

    private EstudianteDAO daoEstudiante;

    public PublicacionDAO() {
        this.daoEstudiante = new EstudianteDAO();
    }

    /**
         * g) Ingresar una publicacion a la base de datos
         *
         * @param publicacion nombre de la publicacion a ingresar a la base de datos
         * @throws Exception
         */
        public void guardarPublicacion(String publicacion) throws Exception {
            try {
                //Armado de la sentencia nativa query con el String pasado como parámetro
                String sql = "INSERT INTO PUBLICACION (IDESTUDIANTE,NOMBREPUB,DESCRIPCION,TIPO, DISPONIBILIDAD) VALUES ('" + publicacion + "')";
                //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
                insertarModificarEliminar(sql);

            } catch (Exception e) {
                throw e;
            } finally {
                desconectarBase();
            }
        }


//        /**
//         * Devolver publicaciones segun el usuario
//         *
//         * @param usuario Usuario del estudiante a buscar
//         * @return Estudiante correspondiente al recibido como parámetro
//         * @throws Exception
//         */
//
//        public Estudiante devolverEstudiante(String usuario) throws Exception {
//            try {
//
//               //Armado de la sentencia query con el id del estudiante recibido como argumento
//                String sql = "SELECT * FROM ESTUDIANTE WHERE USUARIO = '" + usuario + "'";
//
//                //Ejecuto el método de consulta que devuelve y guarda una tabla en el Objeto ResultSet
//                consultarBase(sql);
//
//            /* Aunque sea un solo String, es necesario seguir ocupando el while con el método next()
//            para obtener el valor y que no arroje errores. */
//                Estudiante estudiante = new Estudiante();
//                while (resultado.next()) {
//                    estudiante.setIdEstudiante(resultado.getString(1).trim());
//                    estudiante.setNombre(resultado.getString(2).trim());
//                    estudiante.setApellido(resultado.getString(3).trim());
//                    estudiante.setCorreo(resultado.getString(4).trim());
//                    estudiante.setCelular(resultado.getString(5).trim());
//                    estudiante.setUsuario(resultado.getString(6).trim());
//                    estudiante.setClave(resultado.getString(7).trim());
//
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
//        public Estudiante devolverEstudiante(String usuario) throws Exception {
//            try {
//
//                Estudiante estudiante = new Estudiante();
//
//                //Armado de la sentencia query con el id del estudiante recibido como argumento
//                String sql = "SELECT nombre FROM fabricante WHERE identificador = " + usurio;
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

        /**
         * Listar las publicaciones
         *
         * @return ArrayList del tipo Publicaciones con sus atributos guardados
         * @throws Exception
         */

        public List<Publicacion> recuperarPublicaciones() throws Exception {
            try {

                String sql = "SELECT NOMBREPUB,DESCRIPCION,TIPO, DISPONIBILIDAD, IDESTUDIANTE FROM PUBLICACION";
                consultarBase(sql);

                Publicacion publicacion;
                List<Publicacion> publicaciones = new ArrayList();
                while (resultado.next()) {
                    publicacion = new Publicacion();
                    publicacion.setTitulo(resultado.getString(1).trim());
                    publicacion.setDescripcion(resultado.getString(2).trim());
                    publicacion.setTipo(resultado.getString(3).trim());
                    publicacion.setDisponibilidad(resultado.getInt(4));
                    int idPropietario = resultado.getInt(5);
                    Estudiante propietario= this.daoEstudiante.devolverEstudiante(idPropietario);
                    publicacion.setPropietario(propietario);
                    publicaciones.add(publicacion);
                }
                return publicaciones;

            } catch (Exception e) {
                throw e;
            } finally {
                desconectarBase();
            }
        }

    }

