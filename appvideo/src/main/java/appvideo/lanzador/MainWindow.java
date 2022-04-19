package appvideo.lanzador;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import appvideo.controlador.ControladorAppVideo;
import appvideo.modelo.*;
import appvideo.vista.PanelLogin;
import appvideo.vista.PanelPrincipal;
import appvideo.vista.PanelRegistro;
import tds.video.VideoWeb;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainWindow unicaInstancia = null;
	private static VideoWeb videoWeb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// TODO Quitar para produccion; codigo para realizar pruebas
					// ------------------------------------------------------------------------
					ControladorAppVideo.getUnicaInstancia().login("pedro", "123");
					// ------------------------------------------------------------------------

					videoWeb = new VideoWeb();
					MainWindow frame = MainWindow.getUnicaInstancia();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// CONSTRUCTOR:
	private MainWindow() {
		setTitle("AppVideo - PNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setMinimumSize(new Dimension(1100, 700));

		// TODO Cambiar a panel login para produccion
		showPanelPrincipal();
	}

	// METODOS:
	public static MainWindow getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new MainWindow();
		return unicaInstancia;
	}

	public static VideoWeb getVideoWeb() {
		return videoWeb;
	}

	public void showPanelLogin() {
		setContentPane(new PanelLogin());
		revalidate();
		repaint();
	}

	public void showPanelRegistro() {
		setContentPane(new PanelRegistro());
		revalidate();
		repaint();
	}

	public void showPanelPrincipal() {
		setContentPane(new PanelPrincipal());
		revalidate();
		repaint();
	}

	// TODO Quitar para produccion; codigo para realizar pruebas
	public static void cargarVideos() throws ParseException {

		ControladorAppVideo c = ControladorAppVideo.getUnicaInstancia();

		c.registrarusuario("Pedro", "Nicolas Gomariz", new SimpleDateFormat("yyyy-MM-dd").parse("1999-09-29"),
				"pedro.nicolasg@um.es", "pedro", "123");

		c.registrarusuario("Maria", "Lopez", new SimpleDateFormat("yyyy-MM-dd").parse("1998-07-09"), "maria@um.es",
				"maria", "123");

		c.login("pedro", "123");

		Video v1 = new Video("S81, traslado a la fosa del Syncrolift", "https://www.youtube.com/watch?v=Fnux6V5679g");
		v1.addEtiqueta(new Etiqueta("navantia"));
		v1.addEtiqueta(new Etiqueta("mar"));
		v1.addEtiqueta(new Etiqueta("submarino"));
		v1.addEtiqueta(new Etiqueta("armada"));

		Video v2 = new Video("Pruebas de mar corbeta AL-JUBAIL", "https://www.youtube.com/watch?v=bhdbbWTErpE");
		v2.addEtiqueta(new Etiqueta("navantia"));
		v2.addEtiqueta(new Etiqueta("mar"));
		v2.addEtiqueta(new Etiqueta("submarino"));
		v2.addEtiqueta(new Etiqueta("fragata"));

		Video v3 = new Video("POCOYÓ en ESPAÑOL - Especial 2020", "https://www.youtube.com/watch?v=g8KuwGC0QYQ");
		v3.addEtiqueta(new Etiqueta("pocoyo"));
		v3.addEtiqueta(new Etiqueta("dibujos"));
		v3.addEtiqueta(new Etiqueta("niños"));

		Video v4 = new Video("Separación Rápida | Ben 10", "https://www.youtube.com/watch?v=Vu89nxRhJu0");
		v4.addEtiqueta(new Etiqueta("dibujos"));
		v4.addEtiqueta(new Etiqueta("niños"));
		v4.addEtiqueta(new Etiqueta("ben 10"));

		Video v5 = new Video("Shouse - Love Tonight ", "https://www.youtube.com/watch?v=V0woPCb5xk8");
		v5.addEtiqueta(new Etiqueta("musica"));

		Video v6 = new Video("A equipe de Relâmpago McQueen", "https://www.youtube.com/watch?v=T3MYVPAMiac");
		v6.addEtiqueta(new Etiqueta("dibujos"));
		v6.addEtiqueta(new Etiqueta("niños"));
		v6.addEtiqueta(new Etiqueta("cars"));
		v6.addEtiqueta(new Etiqueta("entretenimiento"));

		Video v7 = new Video("Paco de Lucía y Camarón de la Isla por bulerías",
				"https://www.youtube.com/watch?v=YSAq2oJB53E");
		v7.addEtiqueta(new Etiqueta("musica"));
		v7.addEtiqueta(new Etiqueta("flamenco"));
		v7.addEtiqueta(new Etiqueta("guitarra española"));

		Video v8 = new Video("FORTNITE - D3stri", "https://www.youtube.com/watch?v=uFGpLJMYulI");
		v8.addEtiqueta(new Etiqueta("fortnite"));
		v8.addEtiqueta(new Etiqueta("entretenimiento"));
		v8.addEtiqueta(new Etiqueta("videojuegos"));

		Video v9 = new Video("TROLLEANDO AL MÁS CAMPERO de Modern Warfare 3",
				"https://www.youtube.com/watch?v=xqOQwJAMz2o");
		v9.addEtiqueta(new Etiqueta("COD"));
		v9.addEtiqueta(new Etiqueta("entretenimiento"));
		v9.addEtiqueta(new Etiqueta("videojuegos"));
		v9.addEtiqueta(new Etiqueta("Call Of Duty"));
		v9.addEtiqueta(new Etiqueta("WillyRex"));

		c.registrarVideo(v1);
		c.registrarVideo(v2);
		c.registrarVideo(v3);
		c.registrarVideo(v4);
		c.registrarVideo(v5);
		c.registrarVideo(v6);
		c.registrarVideo(v7);
		c.registrarVideo(v8);
		c.registrarVideo(v9);
	}
}