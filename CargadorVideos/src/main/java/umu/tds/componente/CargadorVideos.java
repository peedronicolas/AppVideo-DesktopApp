package umu.tds.componente;

import java.util.Vector;

public class CargadorVideos {

	private Vector<VideosListener> videosListeners = new Vector<>();
	private Videos videos;

	public CargadorVideos() {

	}

	public void setVideos(String fichero) {

		Videos videosAnteriores = this.videos;
		videos = MapperVideosXMLtoJava.cargarVideos(fichero);

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