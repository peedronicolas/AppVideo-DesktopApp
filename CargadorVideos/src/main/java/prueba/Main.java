package prueba;

import java.io.File;
import java.util.EventObject;

import umu.tds.componente.CargadorVideos;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;

public class Main implements VideosListener {

	public static CargadorVideos cargadorVideos;
	public static Videos videos;

	public Main() {

		cargadorVideos = new CargadorVideos();
		cargadorVideos.addVideosListener(this);
	}

	@Override
	public void nuevosVideos(EventObject e) {
		if (e instanceof VideosEvent) {

			// Tratamiento de lo que deseamos realizar con los nuevos videos
			System.out.println(((VideosEvent) e).getVideosNuevos());
		}
	}

	public static void main(String[] args) throws InterruptedException {

		new Main();

		File f1 = new File("/home/pedro/Escritorio/videos.xml");
		File f2 = new File("/home/pedro/Escritorio/videos2.xml");

		cargadorVideos.cargarVideos(f1);
		cargadorVideos.cargarVideos(f2);
	}
}
