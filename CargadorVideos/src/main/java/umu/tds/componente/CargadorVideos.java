package umu.tds.componente;

import java.io.File;
import java.util.Vector;

public class CargadorVideos implements IBuscarVideos {

	private Vector<VideosListener> videosListeners = new Vector<>();
	private Videos videos;

	public CargadorVideos() {

	}

	@Override
	public void cargarVideos(File xml) {

		Videos videosAnteriores = this.videos;
		videos = MapperVideosXMLtoJava.cargarVideos(xml.getAbsolutePath());

		if (videosAnteriores != videos) {
			VideosEvent evento = new VideosEvent(this, videosAnteriores, videos);
			notificarCambio(evento);
		}
	}

	public Videos getVideos() {
		return videos;
	}

	public synchronized void addVideosListener(VideosListener listener) {
		videosListeners.addElement(listener);
	}

	public synchronized void removeVideosListener(VideosListener listener) {
		videosListeners.removeElement(listener);
	}

	@SuppressWarnings("unchecked")
	private void notificarCambio(VideosEvent event) {

		Vector<VideosListener> lista;
		synchronized (this) {
			lista = (Vector<VideosListener>) videosListeners.clone();
		}
		for (VideosListener vl : lista)
			vl.nuevosVideos(event);
	}
}