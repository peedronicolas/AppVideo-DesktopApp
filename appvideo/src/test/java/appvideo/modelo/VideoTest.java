package appvideo.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VideoTest {

	private Video video1;
	private Video video2;

	@Before
	public void testVideoStringString() {
		video1 = new Video("Invasion1", "https://www.youtube.com/watch?v=DlaHxL3mHAU");
		assertNotNull(video1);
		video1.setCodigo(1);
	}

	@Before
	public void testVideoStringStringInt() {
		video2 = new Video("Invasion2", "https://www.youtube.com/watch?v=DlaHxL3mJTN", 3);
	}

	@Test
	public void testSetCodigo() {
		video2.setCodigo(2);
		assertEquals(video2.getCodigo(), 2);
	}

	@Test
	public void testGetCodigo() {
		assertEquals(video1.getCodigo(), 1);
	}

	@Test
	public void testGetTitulo() {
		assertEquals(video1.getTitulo(), "Invasion1");
		assertEquals(video2.getTitulo(), "Invasion2");
	}

	@Test
	public void testGetURL() {
		assertEquals(video1.getURL(), "https://www.youtube.com/watch?v=DlaHxL3mHAU");
		assertEquals(video2.getURL(), "https://www.youtube.com/watch?v=DlaHxL3mJTN");
	}

	@Test
	public void testGetNumReproducciones() {
		assertEquals(video2.getNumReproducciones(), 3);
	}

	@Test
	public void testIncrementarNumReproducciones() {
		video1.incrementarNumReproducciones();
		assertEquals(video1.getNumReproducciones(), 1);
	}

	@Test
	public void testGetEtiquetas() {
		assertNotNull(video1.getEtiquetas());
		assertTrue(video1.getEtiquetas().isEmpty());
	}

	@Test
	public void testAddEtiqueta() {
		Etiqueta etiqueta = new Etiqueta("Prueba");
		assertTrue(video2.addEtiqueta(etiqueta));
		assertFalse(video2.addEtiqueta(etiqueta));
	}
}