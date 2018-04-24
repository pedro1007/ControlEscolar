package mx.edu.uaz.SistemaControlEscolar.enlacedatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SistemaControlEscolar.modelos.Estado;
import mx.edu.uaz.SistemaControlEscolar.modelos.Programa;


public class ADPrograma {
	
	public ADPrograma(){
		 
	}
	
	public List<Programa> obtenerTodos(){
		SqlSession sesion = Config.abreSesion();
		List<Programa> programas = null;
		try {
			programas = sesion.selectList("obtenerTodosProgramas");
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los estados de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return programas;
	}
	
	
}
