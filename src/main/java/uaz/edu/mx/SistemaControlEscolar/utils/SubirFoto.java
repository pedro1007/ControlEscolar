package uaz.edu.mx.SistemaControlEscolar.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Image;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import mx.edu.uaz.SistemaControlEscolar.modelos.Usuario;
import mx.edu.uaz.SistemaControlEscolar.utils.BuscaComponentes;

public class SubirFoto implements Receiver, SucceededListener, FinishedListener{
	public File file;
	private Image img;
	private String ruta,userImg;
	private Usuario user;
	
	public SubirFoto(Image foto, Usuario user) {
		this.user = user;
		userImg=user.getUsuario()+"-"+user.getIdUsuario();
		ruta = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/VAADIN/fotos/";
		img=foto;
		file=null;


	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null;
//		Notification.show(filename); foto12313.jpg
		if (!filename.equals("")){			
			String ext,imgS;
			int dot = filename.lastIndexOf('.');
			ext = filename.substring(dot + 1).toLowerCase();
			if (ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg") || ext.equals("bmp")){
				imgS=searchFoto();
				if (!imgS.equals("")){
					file = new File(ruta + imgS);
					file.delete();				
				}
				try {
					file = new File(ruta + userImg+'.'+ext);
					fos = new FileOutputStream(file);				
				}			
	        		catch (final Exception e) {
		        		Notification.show("No se pudo abrir la imagen ",e.getMessage(),Notification.Type.ERROR_MESSAGE);        		
		        	}
			}
			else{
				Notification.show("Archivo incomplatible ",filename+" no es un formato válido ",Notification.Type.ERROR_MESSAGE);
			}
		}
		else{
			Notification.show("Selecciona un archivo",Notification.Type.ERROR_MESSAGE);
		}
		 return fos; 
		
	}
	@Override
	public void uploadSucceeded(SucceededEvent event) {
		if (file!=null){
			img.setSource(new FileResource(file));	
		}
	}
	public FileResource getFoto(){
		String imgS=searchFoto();
		if (imgS.equals(""))
			imgS="user.png";
		return new FileResource(new File(ruta+imgS));
	}
	public String searchFoto(){
		String files[];
		File dir = new File(ruta);
		String archivo;
		String imgS="";
		files=dir.list();
		for (int i = 0; i < files.length; i++) {
			int dot = files[i].lastIndexOf('.');
			archivo=files[i].substring(0,dot);
			if (archivo.equals(userImg)){
				imgS=files[i];
				break;
			}
		}
		return imgS;
	}

	@Override
	public void uploadFinished(FinishedEvent event) {
		String js = "var img=document.getElementById('imgFotoAlta');"
				+ "img.src=img.src+'?id="+new CadenaAleatoria().getCadenaAleatoria(20)+"';";
		JavaScript.getCurrent().execute(js);
		
		Usuario usuario = (Usuario) VaadinSession.getCurrent().getAttribute("usuario");
		if (user.getIdUsuario() == usuario.getIdUsuario()) {
			Image imgTmp = (Image) BuscaComponentes.findComponentById(UI.getCurrent(), "userImg");
			imgTmp.setSource(getFoto());
			
			js = "var img=document.getElementById('userImg');"
					+ "img.src=img.src+'?id="+new CadenaAleatoria().getCadenaAleatoria(20)+"';";
			JavaScript.getCurrent().execute(js);
			
		}
		
		
		
	}


}
