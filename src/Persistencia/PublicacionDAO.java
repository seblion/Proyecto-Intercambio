package Persistencia;
import Logica.Estudiante;
import Logica.Publicacion;

import java.sql.ResultSet;
import java.sql.Statement;
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

        /**
         * Listar las publicaciones
         *
         * @return ArrayList del tipo Publicaciones con sus atributos guardados
         * @throws Exception
         */

        public List<Publicacion> recuperarPublicaciones() throws Exception {
            try {

                String sql = "SELECT NOMBREPUB,DESCRIPCION,TIPO, DISPONIBILIDAD, IDESTUDIANTE, IDPUBLICACION FROM PUBLICACION";
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
                    publicacion.setId(resultado.getInt(6));
                    publicaciones.add(publicacion);
                }
                return publicaciones;

            } catch (Exception e) {
                throw e;
            } finally {
                desconectarBase();
            }
        }

    public void vincularIntercambio(int idIntercambio, int idPub) throws Exception {
        try {
            //Armado de la sentencia nativa query con el String pasado como parámetro
            String sql = "UPDATE PUBLICACION SET IDINTERCAMBIO = '" + idIntercambio + "', DISPONIBILIDAD = 0 where IDPUBLICACION = " + idPub;
            //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public boolean hayDisponibilidad(int id) throws RuntimeException {
        try {
            String sql = "SELECT 1 FROM PUBLICACION WHERE IDPUBLICACION = "+id+" AND DISPONIBILIDAD = 1;";
            consultarBase(sql);
            int i=0;
            if (resultado.next()) {
                if(i==1)
                // Si hay al menos una fila en el resultado, significa que el ID existe
                return false;
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            return true;
    }

    public List<Publicacion> recuperarOfertas(String idIntercambio) throws Exception {
        try {

            String sql = "SELECT NOMBREPUB,DESCRIPCION,TIPO, DISPONIBILIDAD, IDESTUDIANTE, IDPUBLICACION FROM PUBLICACION P " +
                    "JOIN INTERCAMBIO I ON P.IDINTERCAMBIO=I.IDINTERCAMBIO WHERE I.IDINTERCAMBIO = " + idIntercambio;
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
                publicacion.setId(resultado.getInt(6));
                publicaciones.add(publicacion);
            }
            return publicaciones;

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarPublicacion(int idPublicacion) throws Exception {
        try {
            //Armado de la sentencia nativa query con el String pasado como parámetro
            String sql = "DELETE FROM PUBLICACION WHERE IDPUBLICACION = " + idPublicacion;
            //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public boolean tieneRelaciones(int idPublicacion) throws Exception {
        try {
            // Construcción de la consulta SQL
            String sql = "SELECT 1 FROM PUBLICACION WHERE IDINTERCAMBIO IS NOT NULL AND IDPUBLICACION = " + idPublicacion;

            // Ejecutar la consulta y analizar el resultado
            conectarBase(); // Método para conectar a la base de datos
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Verifica si hay algún resultado
            boolean tieneRelacion = rs.next();

            rs.close();
            stmt.close();
            return tieneRelacion; // Devuelve true si hay relación, false en caso contrario
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase(); // Cierra la conexión
        }

    }

    public void desvincularIntercambio(String idIntercambio) throws Exception {
        try {
            //Armado de la sentencia nativa query con el String pasado como parámetro
            String sql = "UPDATE PUBLICACION SET IDINTERCAMBIO = NULL, DISPONIBILIDAD = 1 where IDINTERCAMBIO = " + idIntercambio;
            //Ejecuto el método para actualizar la base de datos que no devuelve ningún resultado
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
}

