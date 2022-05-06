package appvideo.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

import appvideo.controlador.ControladorAppVideo;
import appvideo.modelo.Etiqueta;
import appvideo.modelo.Video;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;

public class PanelExplorar extends JPanel {

	private static final long serialVersionUID = 1L;

	private LinkedList<JCheckBox> listCheckBoxEtiquetas = new LinkedList<>();
	private JTextField textbusqueda;
	private JPanel panelEtiquetas;
	private JPanel panelVideos;
	private PanelMiniaturas panelMiniaturasVideos;

	/**
	 * Create the panel.
	 */
	public PanelExplorar() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 300, 0, 0, 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblBuscador = new JLabel("Buscador");
		lblBuscador.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblBuscador = new GridBagConstraints();
		gbc_lblBuscador.gridwidth = 2;
		gbc_lblBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscador.gridx = 2;
		gbc_lblBuscador.gridy = 0;
		add(lblBuscador, gbc_lblBuscador);

		JButton btnRBusqueda = new JButton(" Reiniciar Busqueda");
		btnRBusqueda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciarBusqueda();
			}
		});
		btnRBusqueda.setIcon(new ImageIcon(PanelExplorar.class.getResource("/appvideo/recursos/reset.png")));
		GridBagConstraints gbc_btnRBusqueda = new GridBagConstraints();
		gbc_btnRBusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_btnRBusqueda.gridx = 6;
		gbc_btnRBusqueda.gridy = 0;
		add(btnRBusqueda, gbc_btnRBusqueda);

		textbusqueda = new JTextField();
		textbusqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					realizarBusqueda();
			}
		});
		GridBagConstraints gbc_textbusqueda = new GridBagConstraints();
		gbc_textbusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_textbusqueda.fill = GridBagConstraints.BOTH;
		gbc_textbusqueda.gridx = 2;
		gbc_textbusqueda.gridy = 1;
		add(textbusqueda, gbc_textbusqueda);
		textbusqueda.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				realizarBusqueda();
			}
		});
		btnBuscar.setIcon(new ImageIcon(PanelExplorar.class.getResource("/appvideo/recursos/lupa.png")));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 1;
		add(btnBuscar, gbc_btnBuscar);

		JLabel lblVideos = new JLabel("Videos");
		GridBagConstraints gbc_lblVideos = new GridBagConstraints();
		gbc_lblVideos.anchor = GridBagConstraints.WEST;
		gbc_lblVideos.insets = new Insets(0, 0, 5, 5);
		gbc_lblVideos.gridx = 1;
		gbc_lblVideos.gridy = 2;
		add(lblVideos, gbc_lblVideos);

		JLabel lblEtiquetas = new JLabel("Etiquetas");
		GridBagConstraints gbc_lblEtiquetas = new GridBagConstraints();
		gbc_lblEtiquetas.anchor = GridBagConstraints.WEST;
		gbc_lblEtiquetas.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtiquetas.gridx = 6;
		gbc_lblEtiquetas.gridy = 2;
		add(lblEtiquetas, gbc_lblEtiquetas);

		panelMiniaturasVideos = new PanelMiniaturas(ControladorAppVideo.getUnicaInstancia().getAllVideos());
		panelVideos = panelMiniaturasVideos;
		GridBagConstraints gbc_panelVideos = new GridBagConstraints();
		gbc_panelVideos.gridwidth = 4;
		gbc_panelVideos.insets = new Insets(0, 0, 5, 5);
		gbc_panelVideos.fill = GridBagConstraints.BOTH;
		gbc_panelVideos.gridx = 1;
		gbc_panelVideos.gridy = 3;
		add(panelVideos, gbc_panelVideos);

		JScrollPane scrollPaneEtiquetas = new JScrollPane();
		scrollPaneEtiquetas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPaneEtiquetas = new GridBagConstraints();
		gbc_scrollPaneEtiquetas.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneEtiquetas.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneEtiquetas.gridx = 6;
		gbc_scrollPaneEtiquetas.gridy = 3;
		add(scrollPaneEtiquetas, gbc_scrollPaneEtiquetas);

		panelEtiquetas = new JPanel();
		scrollPaneEtiquetas.setViewportView(panelEtiquetas);
		panelEtiquetas.setLayout(new BoxLayout(panelEtiquetas, BoxLayout.Y_AXIS));

		// Añadimos todos los CheckBox correspondientes a las eqtiquetas registradas en
		// el sistema
		createListCheckBoxEtiquetas();
		for (JCheckBox checkBox : listCheckBoxEtiquetas)
			panelEtiquetas.add(checkBox);
	}

	private void createListCheckBoxEtiquetas() {
		for (Etiqueta etiqueta : ControladorAppVideo.getUnicaInstancia().getAllEtiquetas())
			listCheckBoxEtiquetas.add(new JCheckBox(etiqueta.getNombre()));
	}

	private void reiniciarBusqueda() {
		textbusqueda.setText("");
		for (JCheckBox jcb : listCheckBoxEtiquetas)
			jcb.setSelected(false);

		panelVideos.removeAll();
		panelMiniaturasVideos = new PanelMiniaturas(ControladorAppVideo.getUnicaInstancia().getAllVideos());
		panelVideos.add(panelMiniaturasVideos);
		revalidate();
		repaint();
	}

	private void realizarBusqueda() {

		LinkedList<Etiqueta> etiquetasSeleccionadas = new LinkedList<>();

		for (JCheckBox jcb : listCheckBoxEtiquetas)
			if (jcb.isSelected())
				etiquetasSeleccionadas.add(ControladorAppVideo.getUnicaInstancia().getEtiqueta(jcb.getText()));

		panelVideos.removeAll();
		panelMiniaturasVideos = new PanelMiniaturas(ControladorAppVideo.getUnicaInstancia()
				.getVideosBusqueda(textbusqueda.getText(), etiquetasSeleccionadas));
		panelVideos.add(panelMiniaturasVideos);
		revalidate();
		repaint();
	}

	public Video getVideoSeleccionado() {
		return panelMiniaturasVideos.getVideoSeleccionado();
	}
}