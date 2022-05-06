package appvideo.vista;

import javax.swing.JPanel;

import appvideo.controlador.ControladorAppVideo;
import appvideo.lanzador.MainWindow;
import appvideo.modelo.ListaReproduccion;
import appvideo.modelo.Video;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class PanelEditarListas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelVideosLista;
	private PanelMiniaturas panelMiniaturasLista;
	private JComboBox<String> comboBoxListaReproduccion;
	private PanelExplorar panelExplorar;

	/**
	 * Create the panel.
	 */
	public PanelEditarListas() {

		panelMiniaturasLista = new PanelMiniaturas(new LinkedList<>());
		panelVideosLista = panelMiniaturasLista;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 0, 20, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 20, 0, 0, 20, 0, 0, 10, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnAñadirLista = new JButton("Añadir Lista");
		btnAñadirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object nombreListaReproduccion = JOptionPane.showInputDialog(MainWindow.getUnicaInstancia(),
						"Introduce el nombre de la nueva Lista de Reproducción:", "Añadir Lista de Reproducción",
						JOptionPane.PLAIN_MESSAGE, null, null, "");

				if (nombreListaReproduccion != null) {
					ControladorAppVideo.getUnicaInstancia().crearListaReproduccion((String) nombreListaReproduccion);
					PanelPrincipal.getInstanciaActual().cambiarPanelPrincipalActual(new PanelEditarListas());
				}
			}
		});

		GridBagConstraints gbc_btnAñadirLista = new GridBagConstraints();
		gbc_btnAñadirLista.insets = new Insets(0, 0, 5, 5);
		gbc_btnAñadirLista.gridx = 1;
		gbc_btnAñadirLista.gridy = 0;
		add(btnAñadirLista, gbc_btnAñadirLista);

		JButton btnEliminarLista = new JButton("Eliminar Lista");
		btnEliminarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[] options = ControladorAppVideo.getUnicaInstancia().getAllListasReproduccion().stream()
						.map(lr -> lr.getNombre()).collect(Collectors.toList()).toArray();

				Object nombreListaReproduccion = JOptionPane.showInputDialog(MainWindow.getUnicaInstancia(),
						"Selecciona la Lista de Reproducción a eliminar:", "Eliminar Lista de Reproducción",
						JOptionPane.PLAIN_MESSAGE, null, options, "");

				if (nombreListaReproduccion != null) {
					ListaReproduccion lr = ControladorAppVideo.getUnicaInstancia()
							.getListaReproduccion((String) nombreListaReproduccion);
					ControladorAppVideo.getUnicaInstancia().eliminarListaReproduccion(lr);
					PanelPrincipal.getInstanciaActual().cambiarPanelPrincipalActual(new PanelEditarListas());
				}
			}
		});

		GridBagConstraints gbc_btnEliminarLista = new GridBagConstraints();
		gbc_btnEliminarLista.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminarLista.gridx = 2;
		gbc_btnEliminarLista.gridy = 0;
		add(btnEliminarLista, gbc_btnEliminarLista);

		JLabel lblSeleccionaListaReproduccin = new JLabel("Selecciona Lista Reproducción");
		GridBagConstraints gbc_lblSeleccionaListaReproduccin = new GridBagConstraints();
		gbc_lblSeleccionaListaReproduccin.anchor = GridBagConstraints.WEST;
		gbc_lblSeleccionaListaReproduccin.gridwidth = 2;
		gbc_lblSeleccionaListaReproduccin.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionaListaReproduccin.gridx = 1;
		gbc_lblSeleccionaListaReproduccin.gridy = 2;
		add(lblSeleccionaListaReproduccin, gbc_lblSeleccionaListaReproduccin);

		comboBoxListaReproduccion = new JComboBox<String>();

		comboBoxListaReproduccion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				recargarPanelVideosLista();
			}
		});

		for (ListaReproduccion listaReproduccion : ControladorAppVideo.getUnicaInstancia().getAllListasReproduccion())
			comboBoxListaReproduccion.addItem(listaReproduccion.getNombre());

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		add(comboBoxListaReproduccion, gbc_comboBox);

		JLabel lblVideos = new JLabel("Videos Lista");
		GridBagConstraints gbc_lblVideos = new GridBagConstraints();
		gbc_lblVideos.anchor = GridBagConstraints.WEST;
		gbc_lblVideos.insets = new Insets(0, 0, 5, 5);
		gbc_lblVideos.gridx = 1;
		gbc_lblVideos.gridy = 5;
		add(lblVideos, gbc_lblVideos);

		GridBagConstraints gbc_scrollPaneVideosLista = new GridBagConstraints();
		gbc_scrollPaneVideosLista.gridwidth = 2;
		gbc_scrollPaneVideosLista.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneVideosLista.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneVideosLista.gridx = 1;
		gbc_scrollPaneVideosLista.gridy = 6;
		add(panelVideosLista, gbc_scrollPaneVideosLista);

		panelExplorar = new PanelExplorar();
		GridBagConstraints gbc_panelExplorar = new GridBagConstraints();
		gbc_panelExplorar.gridheight = 10;
		gbc_panelExplorar.fill = GridBagConstraints.BOTH;
		gbc_panelExplorar.gridx = 4;
		gbc_panelExplorar.gridy = 0;
		add(panelExplorar, gbc_panelExplorar);

		JButton btnAñadirVideo = new JButton("Añadir");
		btnAñadirVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirVideoListaReproduccion();
			}
		});
		GridBagConstraints gbc_btnAñadirVideo = new GridBagConstraints();
		gbc_btnAñadirVideo.anchor = GridBagConstraints.EAST;
		gbc_btnAñadirVideo.insets = new Insets(0, 0, 5, 5);
		gbc_btnAñadirVideo.gridx = 1;
		gbc_btnAñadirVideo.gridy = 8;
		add(btnAñadirVideo, gbc_btnAñadirVideo);

		JButton btnQuitarVideo = new JButton("Quitar");
		btnQuitarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarVideoListaReproduccion();
			}
		});
		GridBagConstraints gbc_btnQuitarVideo = new GridBagConstraints();
		gbc_btnQuitarVideo.anchor = GridBagConstraints.WEST;
		gbc_btnQuitarVideo.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuitarVideo.gridx = 2;
		gbc_btnQuitarVideo.gridy = 8;
		add(btnQuitarVideo, gbc_btnQuitarVideo);
	}

	private void recargarPanelVideosLista() {

		ListaReproduccion lr = ControladorAppVideo.getUnicaInstancia()
				.getListaReproduccion((String) comboBoxListaReproduccion.getSelectedItem());

		panelMiniaturasLista = new PanelMiniaturas(
				ControladorAppVideo.getUnicaInstancia().getVideosListaReproduccion(lr));

		panelVideosLista.removeAll();
		panelVideosLista.add(panelMiniaturasLista);
		revalidate();
		repaint();
	}

	private void eliminarVideoListaReproduccion() {

		Video video = panelMiniaturasLista.getVideoSeleccionado();

		if (video != null && (String) comboBoxListaReproduccion.getSelectedItem() != null) {

			ListaReproduccion lr = ControladorAppVideo.getUnicaInstancia()
					.getListaReproduccion((String) comboBoxListaReproduccion.getSelectedItem());

			ControladorAppVideo.getUnicaInstancia().removeVideoToList(lr, video);
			recargarPanelVideosLista();
		}
	}

	private void añadirVideoListaReproduccion() {

		Video video = panelExplorar.getVideoSeleccionado();

		if (video != null && (String) comboBoxListaReproduccion.getSelectedItem() != null) {

			ListaReproduccion lr = ControladorAppVideo.getUnicaInstancia()
					.getListaReproduccion((String) comboBoxListaReproduccion.getSelectedItem());

			ControladorAppVideo.getUnicaInstancia().addVideoToList(lr, video);

			recargarPanelVideosLista();
		}
	}
}