package appvideo.vista;

import javax.swing.JPanel;

import appvideo.controlador.ControladorAppVideo;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInformacionPremium extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelInformacionPremium() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 30, 0, 0, 0, 60, 0, 10, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblTitulo = new JLabel("¡Conviértete en PREMIUM!...\n");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		add(lblTitulo, gbc_lblTitulo);

		JLabel lblSubtitulo = new JLabel("… y disfruta de la siguiente funcionalidad tras mejorar tu cuenta:\n");
		lblSubtitulo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_lblSubtitulo = new GridBagConstraints();
		gbc_lblSubtitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtitulo.gridx = 1;
		gbc_lblSubtitulo.gridy = 2;
		add(lblSubtitulo, gbc_lblSubtitulo);

		JLabel lblInfo1 = new JLabel("- Podrás generar PDF’s con la información de tus Listas de reproducción!");
		lblInfo1.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblInfo1 = new GridBagConstraints();
		gbc_lblInfo1.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfo1.gridx = 1;
		gbc_lblInfo1.gridy = 4;
		add(lblInfo1, gbc_lblInfo1);

		JLabel lblInfo2 = new JLabel("- Posibilidad de aplicar filtros en el explorador de videos.");
		lblInfo2.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblInfo2 = new GridBagConstraints();
		gbc_lblInfo2.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfo2.gridx = 1;
		gbc_lblInfo2.gridy = 5;
		add(lblInfo2, gbc_lblInfo2);

		JLabel lblInfo3 = new JLabel("- Tendrás acceso al Top Ten de AppVideos!");
		lblInfo3.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblInfo3 = new GridBagConstraints();
		gbc_lblInfo3.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfo3.gridx = 1;
		gbc_lblInfo3.gridy = 6;
		add(lblInfo3, gbc_lblInfo3);

		JLabel lblPrecio = new JLabel("¡Paga ahora!");
		lblPrecio.setFont(new Font("FreeSerif", Font.BOLD, 25));
		lblPrecio.setForeground(Color.RED);
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 1;
		gbc_lblPrecio.gridy = 8;
		add(lblPrecio, gbc_lblPrecio);

		JButton btnPagar = new JButton(" Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorAppVideo.getUnicaInstancia().covertUserPremium();
				PanelPrincipal.getInstanciaActual().cambiarPanelPrincipalActual(new PanelPremium());
			}
		});
		btnPagar.setIcon(new ImageIcon(PanelInformacionPremium.class.getResource("/appvideo/recursos/tarjeta.png")));
		GridBagConstraints gbc_btnPagar = new GridBagConstraints();
		gbc_btnPagar.insets = new Insets(0, 0, 5, 5);
		gbc_btnPagar.gridx = 1;
		gbc_btnPagar.gridy = 10;
		add(btnPagar, gbc_btnPagar);
	}
}