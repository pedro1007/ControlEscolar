package mx.edu.uaz.SistemaControlEscolar.enlacedatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SistemaControlEscolar.logica.Hash;
import mx.edu.uaz.SistemaControlEscolar.modelos.Estado;
import mx.edu.uaz.SistemaControlEscolar.modelos.Materia;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;

public class ADMateria {
	
	public boolean altaMateria(Materia materia) {
		boolean ok = false;
		
		SqlSession sesion = Config.abreSesion();		
		try {
			sesion.insert("altaMateria", materia);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("No se pudo crear la materia"+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}		
		return ok;		
	}
	
	public boolean modificarMateria(Materia materia) {
		boolean ok = false;
		SqlSession sesion = Config.abreSesion();		
		try {
			
			sesion.update("modificarMateria", materia);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("No se pudo modificar la materia "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}		
		return ok;		
	}
	
	public List<Materia> obtenerTodasMaterias(){
		SqlSession sesion = Config.abreSesion();
		List<Materia> materias = null;
		try {
			materias = sesion.selectList("obtenerTodasMaterias");
				} catch (Exception e) {
			Notification.show("No se puedieron recuperar las materias de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();			
		}
		return materias;
	}
	
	public boolean eliminarMaterias(List<Materia> materias) {
		boolean ok = false;
		SqlSession sesion = Config.abreSesion();
		try {
			sesion.delete("eliminarMaterias", materias);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("No se puedieron eliminar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();			
		}
		return ok;
		
	}
	
	public Usuario buscaUsuario(Usuario usuario) {
		Usuario user = null;
		SqlSession sesion = Config.abreSesion();
		try {
			user = sesion.selectOne("buscaUsuario", usuario);
		}
		catch (Exception e) {
			Notification.show("No se encontró el usuario"+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();			
		}
		return user;
	}
}
