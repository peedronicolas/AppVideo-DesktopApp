package appvideo.lanzador;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import appvideo.controlador.ControladorAppVideo;
import appvideo.modelo.Etiqueta;
import appvideo.modelo.ListaReproduccion;
import appvideo.modelo.Video;
import appvideo.vista.PanelLogin;
import appvideo.vista.PanelPrincipal;
import appvideo.vista.PanelRegistro;
import appvideo.vista.PanelReproductor;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainWindow unicaInstancia = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// TODO Quitar para produccion; codigo para realizar pruebas
					// ------------------------------------------------------------------------

					ControladorAppVideo c = ControladorAppVideo.getUnicaInstancia();

					c.registrarusuario("Pedro", "Nicolas Gomariz",
							new SimpleDateFormat("yyyy-MM-dd").parse("1999-09-29"), "pedro.nicolasg@um.es", "pedro",
							"123");

					c.registrarusuario("Maria", "Lopez", new SimpleDateFormat("yyyy-MM-dd").parse("1998-07-09"),
							"maria@um.es", "maria", "123");

					Video v1 = new Video("S81, traslado a la fosa del Syncrolift",
							"https://www.youtube.com/watch?v=Fnux6V5679g");
					v1.addEtiqueta(new Etiqueta("navantia"));
					v1.addEtiqueta(new Etiqueta("mar"));
					v1.addEtiqueta(new Etiqueta("submarino"));
					v1.addEtiqueta(new Etiqueta("armada"));

					Video v2 = new Video("Pruebas de mar corbeta AL-JUBAIL",
							"https://www.youtube.com/watch?v=bhdbbWTErpE");
					v2.addEtiqueta(new Etiqueta("navantia"));
					v2.addEtiqueta(new Etiqueta("mar"));
					v2.addEtiqueta(new Etiqueta("submarino"));
					v2.addEtiqueta(new Etiqueta("fragata"));

					c.registrarVideo(v1);
					c.registrarVideo(v2);

					c.login("pedro", "123");

					ListaReproduccion lr1 = c.crearListaReproduccion("Ing. Naval");
					ListaReproduccion lr2 = c.crearListaReproduccion("Sector naval");
					c.addVideoToList(lr1, v2);
					c.addVideoToList(lr1, v1);
					c.addVideoToList(lr1, v2);
					c.addVideoToList(lr2, v1);
					c.covertUserPremium();

					// ------------------------------------------------------------------------

					MainWindow frame = MainWindow.getUnicaInstancia();
					frame.setVisible(true);

					PanelReproductor.getUnicaInstancia().reproducirVideo(
							ControladorAppVideo.getUnicaInstancia().getVideo("Pruebas de mar corbeta AL-JUBAIL"));

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
}