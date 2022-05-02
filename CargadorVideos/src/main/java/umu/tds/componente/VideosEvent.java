package umu.tds.componente;

import java.util.EventObject;

public class VideosEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	protected Videos videosAnteriores, videosNuevos;

	public VideosEvent(Object source, Videos videosAnteriores, Videos videosNuevos) {

		super(source);
		this.videosAnteriores = videosAnteriores;
		this.videosNuevos = videosNuevos;
	}

	public Videos getVideosAnteriores() {
		return videosAnteriores;
	}

	public Videos getVideosNuevos() {
		return videosNuevos;
	}
}