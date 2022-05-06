package appvideo.lanzador;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;

import appvideo.controlador.ControladorAppVideo;
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

					// Registramos al usuario administrador en el sistema
					ControladorAppVideo.getUnicaInstancia().registrarusuario("admin", "", new Date(),
							"admin@appvideopng.es", "admin", "admin");

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
		setMinimumSize(new Dimension(1100, 800));

		// Abrimos AppVideo mostrando el login
		showPanelLogin();
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
}