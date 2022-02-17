package appvideo.lanzador;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import appvideo.modelo.ListaReproduccion;
import appvideo.modelo.Usuario;
import appvideo.modelo.Video;

public class Lanzador {

	public static void main(String[] args) throws ParseException {

		Video v1 = new Video("S81, traslado a la fosa del Syncrolift", "https://www.youtube.com/watch?v=Fnux6V5679g",
				0);
		v1.addEtiqueta("subamrino");
		v1.addEtiqueta("mar");

		Video v2 = new Video("Pruebas de mar corbeta AL-JUBAIL", "https://www.youtube.com/watch?v=bhdbbWTErpE", 0);
		v2.addEtiqueta("submarine");
		v2.addEtiqueta("defensa");

		Usuario u1 = new Usuario("Pedro", "Nicolas Gomariz", new SimpleDateFormat("yyyy-MM-dd").parse("1999-09-29"),
				"pedro.nicolasg@um.es", "peedronicolas", "123", false);

		ListaReproduccion lr = u1.createAddListaReproduccion("Navantia");
		u1.addVideoToList(lr, v1);
		u1.addVideoToList(lr, v2);
		u1.addVideoReciente(v2);

		System.out.println(u1.toJson());
	}
}