package appvideo.lanzador;

import java.text.ParseException;

import appvideo.controlador.ControladorAppVideo;
import appvideo.modelo.Etiqueta;
import appvideo.modelo.ListaReproduccion;
import appvideo.modelo.Video;

public class Lanzador {

	public static void main(String[] args) throws ParseException {

		Video v1 = new Video("S81, traslado a la fosa del Syncrolift", "https://www.youtube.com/watch?v=Fnux6V5679g");
		v1.addEtiqueta(new Etiqueta("navantia"));
		v1.addEtiqueta(new Etiqueta("mar"));

		Video v2 = new Video("Pruebas de mar corbeta AL-JUBAIL", "https://www.youtube.com/watch?v=bhdbbWTErpE");
		v2.addEtiqueta(new Etiqueta("navantia"));

		ListaReproduccion lr = new ListaReproduccion("Navantia");
		lr.addVideo(v2);
		lr.addVideo(v1);

		ControladorAppVideo c = new ControladorAppVideo();
//		c.registrarusuario("Pedro", "Nicolas Gomariz", new SimpleDateFormat("yyyy-MM-dd").parse("1999-09-29"),
//				"pedro.nicolasg@um.es", "peedronicolas", "123");

		System.out.println(c.login("peedronicolas", "123"));
		System.out.println(c.getUsuarioActual());
	}
}