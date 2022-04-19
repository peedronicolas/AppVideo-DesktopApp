package appvideo.vista;

import javax.swing.JPanel;

import appvideo.controlador.ControladorAppVideo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

public class PanelRecientes extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelRecientes() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 380, 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblVideosRecientes = new JLabel("Videos Recientes");
		GridBagConstraints gbc_lblVideosRecientes = new GridBagConstraints();
		gbc_lblVideosRecientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVideosRecientes.insets = new Insets(0, 0, 5, 5);
		gbc_lblVideosRecientes.gridx = 1;
		gbc_lblVideosRecientes.gridy = 0;
		add(lblVideosRecientes, gbc_lblVideosRecientes);

		JPanel panelReproductor = PanelReproductor.getUnicaInstancia();
		GridBagConstraints gbc_panelReproductor = new GridBagConstraints();
		gbc_panelReproductor.gridheight = 2;
		gbc_panelReproductor.insets = new Insets(0, 0, 5, 5);
		gbc_panelReproductor.fill = GridBagConstraints.BOTH;
		gbc_panelReproductor.gridx = 3;
		gbc_panelReproductor.gridy = 0;
		add(panelReproductor, gbc_panelReproductor);

		JPanel panelVideos = new PanelMiniaturas(
				ControladorAppVideo.getUnicaInstancia().getUsuarioActual().getVideosRecientes());
		GridBagConstraints gbc_panelVideos = new GridBagConstraints();
		gbc_panelVideos.insets = new Insets(0, 0, 5, 5);
		gbc_panelVideos.fill = GridBagConstraints.BOTH;
		gbc_panelVideos.gridx = 1;
		gbc_panelVideos.gridy = 1;
		add(panelVideos, gbc_panelVideos);
	}
}