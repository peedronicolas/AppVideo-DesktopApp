package appvideo.vista;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.awt.Font;
import javax.swing.JComboBox;

import appvideo.controlador.ControladorAppVideo;
import appvideo.modelo.ListaReproduccion;

public class PanelMisListas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelVideo;

	/**
	 * Create the panel.
	 */
	public PanelMisListas() {

		panelVideo = new PanelMiniaturas(new LinkedList<>());

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

		comboBoxLR.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {

				ListaReproduccion lr = ControladorAppVideo.getUnicaInstancia()
						.getListaReproduccion((String) comboBoxLR.getSelectedItem());
				panelVideo.removeAll();
				panelVideo.add(
						new PanelMiniaturas(ControladorAppVideo.getUnicaInstancia().getVideosListaReproduccion(lr)));
				revalidate();
				repaint();
			}
		});

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

		JPanel panelReproductor = PanelReproductor.getUnicaInstancia();
		GridBagConstraints gbc_panelReproductor = new GridBagConstraints();
		gbc_panelReproductor.gridheight = 5;
		gbc_panelReproductor.insets = new Insets(0, 0, 5, 5);
		gbc_panelReproductor.fill = GridBagConstraints.BOTH;
		gbc_panelReproductor.gridx = 3;
		gbc_panelReproductor.gridy = 0;
		add(panelReproductor, gbc_panelReproductor);

		GridBagConstraints gbc_panelVideo = new GridBagConstraints();
		gbc_panelVideo.insets = new Insets(0, 0, 5, 5);
		gbc_panelVideo.fill = GridBagConstraints.BOTH;
		gbc_panelVideo.gridx = 1;
		gbc_panelVideo.gridy = 4;
		add(panelVideo, gbc_panelVideo);
	}
}