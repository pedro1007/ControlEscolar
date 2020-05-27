package mx.edu.uaz.SistemaControlEscolar.enlacedatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SistemaControlEscolar.logica.Hash;
import mx.edu.uaz.SistemaControlEscolar.modelos.Estado;
import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;
import uaz.edu.mx.SistemaControlEscolar.utils.CadenaAleatoria;
import uaz.edu.mx.SistemaControlEscolar.utils.EnviarCorreo;

public class ADUsuario {
	
	public boolean altaUsuario(Usuario usuario) {
		boolean ok = false;
		usuario.setPassword(Hash.sha1(usuario.getPassword()));
		CadenaAleatoria cadenaAleatoria = new CadenaAleatoria();
		usuario.setCadena(cadenaAleatoria.getCadenaAleatoria(50));
		usuario.setEstatus('1');
		
		SqlSession sesion = Config.abreSesion();		
		try {
			sesion.insert("altaUsuario", usuario);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("No se pudo crear el usuario "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}	
		/*try {
			EnviarCorreo enviarCorreo = new EnviarCorreo();
			enviarCorreo.sendMail(
					usuario.getUsuario(),
					usuario.getMail(),
					usuario.getCadena(), 
					usuario.getNombre(), 
					usuario.getApellidoPaterno());
		} catch (Exception e) {
			Notification.show("No se puedo enviar correo de confirmación "+e.getMessage(),Notification.Type.ERROR_MESSAGE);
		}	*/		
		return ok;		
	}
	
	public boolean modificarUsuario(Usuario usuario) {
		boolean ok = false;
		SqlSession sesion = Config.abreSesion();		
		try {
			
			sesion.update("modificarUsuario", usuario);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("No se pudo modificar el usuario "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}		
		return ok;		
	}
	
	public List<Usuario> obtenerTodosUsuarios(){
		SqlSession sesion = Config.abreSesion();
		List<Usuario> usuarios = null;
		try {
			usuarios = sesion.selectList("obtenerTodosUsuarios");
				} catch (Exception e) {
			Notification.show("No se puedieron recuperar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();			
		}
		return usuarios;
	}
	
	public boolean eliminarUsuarios(List<Usuario> usuarios) {
		boolean ok = false;
		SqlSession sesion = Config.abreSesion();
		try {
			sesion.delete("eliminarUsuarios", usuarios);
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
	
	public boolean confirmaCuenta(Usuario usuario) {
		boolean ok = false;
		Usuario user = buscaUsuario(usuario);
		if (user != null) {
			if (user.getCadena().equals(usuario.getCadena())){
				SqlSession sesion = Config.abreSesion();
				try {
					sesion.update("confirmaCuenta", user);
					sesion.commit();
					ok = true;
				}
				catch (Exception e) {
					Notification.show("No se pudo activar la cuenta"+e.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
				finally {
					sesion.close();			
				}
			}
		}
		
		return ok;
		
	}
	
}
