package appvideo.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListaReproduccionTest {

	private ListaReproduccion listaReproduccion;
	private Video video;

	@Before
	public void testListaReproduccion() {

		listaReproduccion = new ListaReproduccion("Series");
		assertNotNull(listaReproduccion);

		listaReproduccion.setCodigo(1);

		video = new Video("Invasion", "https://www.youtube.com/watch?v=DlaHxL3mHAU");
		video.addEtiqueta(new Etiqueta("Series"));
		video.addEtiqueta(new Etiqueta("AppleTV"));
	}

	@Test
	public void testGetCodigo() {
		assertEquals(listaReproduccion.getCodigo(), 1);
	}

	@Test
	public void testSetCodigo() {
		listaReproduccion.setCodigo(2);
		assertEquals(listaReproduccion.getCodigo(), 2);
	}

	@Test
	public void testGetNombre() {
		assertEquals(listaReproduccion.getNombre(), "Series");
	}

	@Test
	public void testGetVideos() {
		assertTrue(listaReproduccion.getVideos().isEmpty());
	}

	@Test
	public void testAddVideo() {
		assertTrue(listaReproduccion.addVideo(video));
		assertFalse(listaReproduccion.addVideo(video));
		listaReproduccion.removeVideo(video);
	}

	@Test
	public void testRemoveVideo() {
		listaReproduccion.addVideo(video);
		assertTrue(listaReproduccion.removeVideo(video));
		assertFalse(listaReproduccion.removeVideo(video));
	}
}