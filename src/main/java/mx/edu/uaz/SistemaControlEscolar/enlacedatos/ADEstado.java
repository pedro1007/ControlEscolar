package mx.edu.uaz.SistemaControlEscolar.enlacedatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SistemaControlEscolar.modelos.Estado;


public class ADEstado {
	
	public ADEstado(){
		 
	}
	
	public List<Estado> obtenerTodos(){
		SqlSession sesion = Config.abreSesion();
		List<Estado> estados = null;
		try {
			estados = sesion.selectList("obtenerTodosEstados");
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los estados de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return estados;
	}
	
	
}
