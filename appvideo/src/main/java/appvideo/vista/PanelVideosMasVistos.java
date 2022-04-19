package appvideo.vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import appvideo.controlador.ControladorAppVideo;

public class PanelVideosMasVistos extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelVideosMasVistos() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 380, 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblVideosMasVistos = new JLabel("Videos Más Vistos");
		GridBagConstraints gbc_lblVideosMasVistos = new GridBagConstraints();
		gbc_lblVideosMasVistos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVideosMasVistos.insets = new Insets(0, 0, 5, 5);
		gbc_lblVideosMasVistos.gridx = 1;
		gbc_lblVideosMasVistos.gridy = 0;
		add(lblVideosMasVistos, gbc_lblVideosMasVistos);

		JPanel panelReproductor = PanelReproductor.getUnicaInstancia();
		GridBagConstraints gbc_panelReproductor = new GridBagConstraints();
		gbc_panelReproductor.gridheight = 2;
		gbc_panelReproductor.insets = new Insets(0, 0, 5, 5);
		gbc_panelReproductor.fill = GridBagConstraints.BOTH;
		gbc_panelReproductor.gridx = 3;
		gbc_panelReproductor.gridy = 0;
		add(panelReproductor, gbc_panelReproductor);

		JPanel panelVideos = new PanelMiniaturas(ControladorAppVideo.getUnicaInstancia().getVideosMasVistos());

		GridBagConstraints gbc_panelVideos = new GridBagConstraints();
		gbc_panelVideos.insets = new Insets(0, 0, 5, 5);
		gbc_panelVideos.fill = GridBagConstraints.BOTH;
		gbc_panelVideos.gridx = 1;
		gbc_panelVideos.gridy = 1;
		add(panelVideos, gbc_panelVideos);
	}
}