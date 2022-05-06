package appvideo.vista;

import javax.swing.JPanel;

import appvideo.controlador.ControladorAppVideo;
import appvideo.lanzador.MainWindow;
import appvideo.modelo.FiltrosName;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class PanelPremium extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelRadioButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public PanelPremium() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 5, 0, 10, 0, 50, 0, 5, 0, 5, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblGenerarpdf = new JLabel("Obtener Listas Reproducción en PDF.");
		lblGenerarpdf.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblGenerarpdf = new GridBagConstraints();
		gbc_lblGenerarpdf.anchor = GridBagConstraints.WEST;
		gbc_lblGenerarpdf.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenerarpdf.gridx = 1;
		gbc_lblGenerarpdf.gridy = 0;
		add(lblGenerarpdf, gbc_lblGenerarpdf);

		JLabel lblText1 = new JLabel("Obten un PDF con la información más relevante sobre tus listas de reproducción.");
		GridBagConstraints gbc_lblText1 = new GridBagConstraints();
		gbc_lblText1.anchor = GridBagConstraints.WEST;
		gbc_lblText1.insets = new Insets(0, 0, 5, 5);
		gbc_lblText1.gridx = 1;
		gbc_lblText1.gridy = 2;
		add(lblText1, gbc_lblText1);

		JButton btnObtenerPDF = new JButton(" Otener PDF");
		btnObtenerPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser selectorCarpeta = new JFileChooser();
				selectorCarpeta.setCurrentDirectory(new File("."));
				selectorCarpeta.setDialogTitle("Selecciona la carpeta para almacenar el PDF.");
				selectorCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				selectorCarpeta.setAcceptAllFileFilterUsed(false);

				if (selectorCarpeta.showOpenDialog(MainWindow.getUnicaInstancia()) == JFileChooser.APPROVE_OPTION) {
					File directory = selectorCarpeta.getSelectedFile();
					ControladorAppVideo.getUnicaInstancia().generatePDF(directory.getAbsoluteFile().toString());
					JOptionPane.showMessageDialog(MainWindow.getUnicaInstancia(), "PDF generado correctamente.");
				}
			}
		});
		btnObtenerPDF.setIcon(new ImageIcon(PanelPremium.class.getResource("/appvideo/recursos/descargar.png")));
		GridBagConstraints gbc_btnObtenerPDF = new GridBagConstraints();
		gbc_btnObtenerPDF.anchor = GridBagConstraints.WEST;
		gbc_btnObtenerPDF.insets = new Insets(0, 0, 5, 5);
		gbc_btnObtenerPDF.gridx = 1;
		gbc_btnObtenerPDF.gridy = 4;
		add(btnObtenerPDF, gbc_btnObtenerPDF);

		JLabel lblFiltros = new JLabel("Activar filtros de búsqueda.");
		lblFiltros.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblFiltros = new GridBagConstraints();
		gbc_lblFiltros.anchor = GridBagConstraints.WEST;
		gbc_lblFiltros.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltros.gridx = 1;
		gbc_lblFiltros.gridy = 6;
		add(lblFiltros, gbc_lblFiltros);

		JLabel lblText2 = new JLabel("Activa alguno de los siguintes filtros para realizar busquedas más concretras.");
		GridBagConstraints gbc_lblText2 = new GridBagConstraints();
		gbc_lblText2.anchor = GridBagConstraints.WEST;
		gbc_lblText2.insets = new Insets(0, 0, 5, 5);
		gbc_lblText2.gridx = 1;
		gbc_lblText2.gridy = 8;
		add(lblText2, gbc_lblText2);

		panelRadioButton = new JPanel();
		GridBagConstraints gbc_panelRadioButton = new GridBagConstraints();
		gbc_panelRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_panelRadioButton.fill = GridBagConstraints.BOTH;
		gbc_panelRadioButton.gridx = 1;
		gbc_panelRadioButton.gridy = 10;
		add(panelRadioButton, gbc_panelRadioButton);
		panelRadioButton.setLayout(new BoxLayout(panelRadioButton, BoxLayout.Y_AXIS));

		createRadioButtonsFiltros();
	}

	private void createRadioButtonsFiltros() {

		for (FiltrosName filtroName : FiltrosName.values()) {

			JRadioButton rdbtn = new JRadioButton(filtroName.toString().replace("_", " "));
			rdbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ControladorAppVideo.getUnicaInstancia().setFiltroBusquedaUser(filtroName);
				}
			});

			if (ControladorAppVideo.getUnicaInstancia().getUsuarioActual().getFiltroVideos().getName() == filtroName)
				rdbtn.setSelected(true);

			buttonGroup.add(rdbtn);
			panelRadioButton.add(rdbtn);
		}
	}
}