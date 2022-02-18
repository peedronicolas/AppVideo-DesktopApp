package appvideo.lanzador;

import java.text.ParseException;

import appvideo.modelo.CatalogoVideos;
import appvideo.modelo.Etiqueta;
import appvideo.modelo.Video;
import appvideo.persistencia.AdaptadorVideoTDS;

public class Lanzador {

	public static void main(String[] args) throws ParseException {

		Video v1 = new Video("S81, traslado a la fosa del Syncrolift", "https://www.youtube.com/watch?v=Fnux6V5679g");
		v1.addEtiqueta(new Etiqueta("navantia"));
		v1.addEtiqueta(new Etiqueta("mar"));

		Video v2 = new Video("Pruebas de mar corbeta AL-JUBAIL", "https://www.youtube.com/watch?v=bhdbbWTErpE");
		v2.addEtiqueta(new Etiqueta("navantia"));

		/*
		 * Usuario u1 = new Usuario("Pedro", "Nicolas Gomariz", new
		 * SimpleDateFormat("yyyy-MM-dd").parse("1999-09-29"), "pedro.nicolasg@um.es",
		 * "peedronicolas", "123", false);
		 */

		AdaptadorVideoTDS av = AdaptadorVideoTDS.getUnicaInstancia();
		av.registrarVideo(v1);
		av.registrarVideo(v2);

		CatalogoVideos cv = new CatalogoVideos();
		System.out.println(cv.getVideos());
	}
}