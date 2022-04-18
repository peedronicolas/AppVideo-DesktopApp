package appvideo.vista;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;

import appvideo.controlador.ControladorAppVideo;
import appvideo.lanzador.MainWindow;
import appvideo.modelo.Etiqueta;
import appvideo.modelo.Video;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class PanelReproductor extends JPanel {

	private static final long serialVersionUID = 1L;

	private static PanelReproductor unicaInstancia = null;

	private JLabel lblTitulo;
	private JLabel lblReproducciones;
	private JLabel lblAddEtiq;
	private GridBagConstraints gbc_lblAddEtiq;
	private JPanel panelEtiquetas;
	private JPanel panelAddEtiqueta;
	private GridBagConstraints gbc_panelAddEtiqueta;
	private JTextField textEtiqueta;
	private Video videoActual = null;

	/**
	 * Create the panel.
	 */
	private PanelReproductor() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 5, 0, 5, 0, 60, 0, 0, 20, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPanel();
			}
		});
		btnCancelar.setIcon(new ImageIcon(PanelReproductor.class.getResource("/appvideo/recursos/cancel.png")));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 0;
		add(btnCancelar, gbc_btnCancelar);

		lblTitulo = new JLabel("Reproductor");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 2;
		add(lblTitulo, gbc_lblTitulo);

		JPanel panelVideo = new JPanel();
		panelVideo.add(MainWindow.getVideoWeb());
		GridBagConstraints gbc_panelVideo = new GridBagConstraints();
		gbc_panelVideo.gridwidth = 2;
		gbc_panelVideo.insets = new Insets(0, 0, 5, 0);
		gbc_panelVideo.fill = GridBagConstraints.BOTH;
		gbc_panelVideo.gridx = 0;
		gbc_panelVideo.gridy = 4;
		add(panelVideo, gbc_panelVideo);

		lblReproducciones = new JLabel("");
		GridBagConstraints gbc_lblReproducciones = new GridBagConstraints();
		gbc_lblReproducciones.gridwidth = 2;
		gbc_lblReproducciones.insets = new Insets(0, 0, 5, 0);
		gbc_lblReproducciones.gridx = 0;
		gbc_lblReproducciones.gridy = 6;
		add(lblReproducciones, gbc_lblReproducciones);

		lblAddEtiq = new JLabel("");
		gbc_lblAddEtiq = new GridBagConstraints();
		gbc_lblAddEtiq.gridwidth = 3;
		gbc_lblAddEtiq.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddEtiq.gridx = 0;
		gbc_lblAddEtiq.gridy = 8;
		add(lblAddEtiq, gbc_lblAddEtiq);

		panelAddEtiqueta = new JPanel();
		gbc_panelAddEtiqueta = new GridBagConstraints();
		gbc_panelAddEtiqueta.gridwidth = 3;
		gbc_panelAddEtiqueta.insets = new Insets(0, 0, 5, 0);
		gbc_panelAddEtiqueta.fill = GridBagConstraints.BOTH;
		gbc_panelAddEtiqueta.gridx = 0;
		gbc_panelAddEtiqueta.gridy = 9;
		panelAddEtiqueta.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textEtiqueta = new JTextField();
		textEtiqueta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					addEtiqueta();
			}
		});
		panelAddEtiqueta.add(textEtiqueta);
		textEtiqueta.setColumns(20);

		JButton btnAñadir = new JButton("");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEtiqueta();
			}
		});
		btnAñadir.setIcon(new ImageIcon(PanelReproductor.class.getResource("/appvideo/recursos/add.png")));
		panelAddEtiqueta.add(btnAñadir);

		panelEtiquetas = new JPanel();
		GridBagConstraints gbc_panelEtiquetas = new GridBagConstraints();
		gbc_panelEtiquetas.gridwidth = 3;
		gbc_panelEtiquetas.fill = GridBagConstraints.BOTH;
		gbc_panelEtiquetas.gridx = 0;
		gbc_panelEtiquetas.gridy = 11;
		add(panelEtiquetas, gbc_panelEtiquetas);
		panelEtiquetas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	// METODOS:
	public static PanelReproductor getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new PanelReproductor();
		return unicaInstancia;
	}

	public void reproducirVideo(Video video) {

		videoActual = video;

		lblTitulo.setText(video.getTitulo());
		MainWindow.getVideoWeb().playVideo(video.getURL());
		lblReproducciones.setText(video.getNumReproducciones() + " reproducciones");
		lblAddEtiq.setText("Añade una nueva etiqueta");

		panelEtiquetas.removeAll();
		for (Etiqueta etiqueta : video.getEtiquetas()) {
			JButton btnEtiqueta = new JButton(etiqueta.getNombre());
			btnEtiqueta.setEnabled(false);
			panelEtiquetas.add(btnEtiqueta);
		}

		add(panelAddEtiqueta, gbc_panelAddEtiqueta);

		revalidate();
		repaint();
	}

	public void clearPanel() {

		lblTitulo.setText("Reproductor");
		MainWindow.getVideoWeb().cancel();
		lblReproducciones.setText("");
		lblAddEtiq.setText("");
		panelEtiquetas.removeAll();
		remove(panelAddEtiqueta);

		revalidate();
		repaint();
	}

	private void addEtiqueta() {

		String etqName = textEtiqueta.getText().toUpperCase();
		textEtiqueta.setText("");

		if (!etqName.equals("")) {

			boolean isRegistrada = ControladorAppVideo.getUnicaInstancia().addEtiquetaToVideo(videoActual, etqName);

			if (isRegistrada == false) {
				JOptionPane.showMessageDialog(MainWindow.getUnicaInstancia(),
						"La etiqueta '" + etqName + "' ya existe para este video.");
				return;
			}

			JButton btnEtiqueta = new JButton(etqName);
			btnEtiqueta.setEnabled(false);
			panelEtiquetas.add(btnEtiqueta);

			revalidate();
			repaint();
		}
	}
}