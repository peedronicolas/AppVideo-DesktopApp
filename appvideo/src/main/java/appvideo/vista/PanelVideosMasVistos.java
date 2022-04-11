package appvideo.vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

		JScrollPane scrollPaneVideos = new JScrollPane();
		GridBagConstraints gbc_scrollPaneVideos = new GridBagConstraints();
		gbc_scrollPaneVideos.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneVideos.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneVideos.gridx = 1;
		gbc_scrollPaneVideos.gridy = 1;
		add(scrollPaneVideos, gbc_scrollPaneVideos);

		JScrollPane scrollPaneReproductor = new JScrollPane();
		GridBagConstraints gbc_scrollPaneReproductor = new GridBagConstraints();
		gbc_scrollPaneReproductor.gridheight = 2;
		gbc_scrollPaneReproductor.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneReproductor.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneReproductor.gridx = 3;
		gbc_scrollPaneReproductor.gridy = 0;
		add(scrollPaneReproductor, gbc_scrollPaneReproductor);
	}
}