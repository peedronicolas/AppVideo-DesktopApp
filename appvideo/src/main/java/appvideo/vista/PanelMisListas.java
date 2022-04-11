package appvideo.vista;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import appvideo.controlador.ControladorAppVideo;
import appvideo.modelo.ListaReproduccion;

public class PanelMisListas extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMisListas() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 380, 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 20, 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblLR = new JLabel("Selecciona Lista Reproducción:");
		lblLR.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblLR = new GridBagConstraints();
		gbc_lblLR.anchor = GridBagConstraints.WEST;
		gbc_lblLR.insets = new Insets(0, 0, 5, 5);
		gbc_lblLR.gridx = 1;
		gbc_lblLR.gridy = 0;
		add(lblLR, gbc_lblLR);

		JComboBox<String> comboBoxLR = new JComboBox<String>();
		for (ListaReproduccion listaReproduccion : ControladorAppVideo.getUnicaInstancia().getAllListasReproduccion())
			comboBoxLR.addItem(listaReproduccion.getNombre());

		GridBagConstraints gbc_comboBoxLR = new GridBagConstraints();
		gbc_comboBoxLR.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxLR.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxLR.gridx = 1;
		gbc_comboBoxLR.gridy = 1;
		add(comboBoxLR, gbc_comboBoxLR);

		JLabel lblVideosLista = new JLabel("Videos Lista");
		GridBagConstraints gbc_lblVideosLista = new GridBagConstraints();
		gbc_lblVideosLista.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVideosLista.insets = new Insets(0, 0, 5, 5);
		gbc_lblVideosLista.gridx = 1;
		gbc_lblVideosLista.gridy = 3;
		add(lblVideosLista, gbc_lblVideosLista);

		JScrollPane scrollPaneVideos = new JScrollPane();
		GridBagConstraints gbc_scrollPaneVideos = new GridBagConstraints();
		gbc_scrollPaneVideos.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneVideos.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneVideos.gridx = 1;
		gbc_scrollPaneVideos.gridy = 4;
		add(scrollPaneVideos, gbc_scrollPaneVideos);

		JScrollPane scrollPaneReproductor = new JScrollPane();
		GridBagConstraints gbc_scrollPaneReproductor = new GridBagConstraints();
		gbc_scrollPaneReproductor.gridheight = 5;
		gbc_scrollPaneReproductor.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneReproductor.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneReproductor.gridx = 3;
		gbc_scrollPaneReproductor.gridy = 0;
		add(scrollPaneReproductor, gbc_scrollPaneReproductor);
	}
}