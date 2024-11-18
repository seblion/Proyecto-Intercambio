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


        /**
         * Devolver estudiante segun el usuario
         *
         * @param usuario Usuario del estudiante a buscar
         * @return Estudiante correspondiente al recibido como parámetro
         * @throws Exception
         */

        public Estudiante devolverEstudiante(String usuario) throws Exception {
            try {

               //Armado de la sentencia query con el id del estudiante recibido como argumento
                String sql = "SELECT * FROM ESTUDIANTE WHERE USUARIO = '" + usuario + "'";

                //Ejecuto el método de consulta que devuelve y guarda una tabla en el Objeto ResultSet
                consultarBase(sql);

            /* Aunque sea un solo String, es necesario seguir ocupando el while con el método next()
            para obtener el valor y que no arroje errores. */
                Estudiante estudiante = new Estudiante();
                while (resultado.next()) {
                    estudiante.setIdEstudiante(resultado.getString(1).trim());
                    estudiante.setNombre(resultado.getString(2).trim());
                    estudiante.setApellido(resultado.getString(3).trim());
                    estudiante.setCorreo(resultado.getString(4).trim());
                    estudiante.setCelular(resultado.getString(5).trim());
                    estudiante.setUsuario(resultado.getString(6).trim());
                    estudiante.setClave(resultado.getString(7).trim());

                }
                return estudiante;

            } catch (Exception e) {
                throw e;
            } finally {
                desconectarBase();
            }
        }

    public Estudiante devolverEstudiante(int idEstudiante) throws Exception {
        try {

            //Armado de la sentencia query con el id del estudiante recibido como argumento
            String sql = "SELECT * FROM ESTUDIANTE WHERE IDESTUDIANTE = '" + idEstudiante + "'";

            //Ejecuto el método de consulta que devuelve y guarda una tabla en el Objeto ResultSet
            consultarBase(sql);

            /* Aunque sea un solo String, es necesario seguir ocupando el while con el método next()
            para obtener el valor y que no arroje errores. */
            Estudiante estudiante = new Estudiante();
            while (resultado.next()) {
                estudiante.setIdEstudiante(resultado.getString(1).trim());
                estudiante.setNombre(resultado.getString(2).trim());
                estudiante.setApellido(resultado.getString(3).trim());
                estudiante.setCorreo(resultado.getString(4).trim());
                estudiante.setCelular(resultado.getString(5).trim());
                estudiante.setUsuario(resultado.getString(6).trim());
                estudiante.setClave(resultado.getString(7).trim());

            }
            return estudiante;

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    }

