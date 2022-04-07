package appvideo.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JSeparator;

import appvideo.lanzador.MainWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelPrincipal() {
		setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[] { 20, 0, 0, 10, 10, 0, 20, 0 };
		gbl_panelNorte.rowHeights = new int[] { 20, 0, 20, 0, 0, 0, 20, 0 };
		gbl_panelNorte.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelNorte.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		panelNorte.setLayout(gbl_panelNorte);

		JLabel lblIcon = new JLabel(" AppVideo - PNG");
		lblIcon.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblIcon.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/yt-logo-p.png")));
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 1;
		gbc_lblIcon.gridy = 1;
		panelNorte.add(lblIcon, gbc_lblIcon);

		// TODO cambiar name por el nombre del user
		JLabel lblWelcomeUser = new JLabel("Hola, " + "name" + "!");
		GridBagConstraints gbc_lblWelcomeUser = new GridBagConstraints();
		gbc_lblWelcomeUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcomeUser.gridx = 3;
		gbc_lblWelcomeUser.gridy = 1;
		panelNorte.add(lblWelcomeUser, gbc_lblWelcomeUser);

		JButton btnLogout = new JButton(" Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO descomentar proxima linea para quitar al user
				// controlador.logout();
				MainWindow.getUnicaInstancia().showPanelLogin();
			}
		});
		btnLogout.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/logout.png")));
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogout.gridx = 5;
		gbc_btnLogout.gridy = 1;
		panelNorte.add(btnLogout, gbc_btnLogout);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 7;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		panelNorte.add(separator_1, gbc_separator_1);

		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.anchor = GridBagConstraints.WEST;
		gbc_panelBotones.gridwidth = 2;
		gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotones.fill = GridBagConstraints.VERTICAL;
		gbc_panelBotones.gridx = 1;
		gbc_panelBotones.gridy = 4;
		panelNorte.add(panelBotones, gbc_panelBotones);

		JButton btnExplorar = new JButton(" Explorar");
		btnExplorar.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/lupa.png")));
		panelBotones.add(btnExplorar);

		JButton btnMisListas = new JButton(" Mis Listas");
		btnMisListas.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/lista.png")));
		panelBotones.add(btnMisListas);

		JButton btnNuevaLista = new JButton(" Nueva Lista");
		btnNuevaLista.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/addLista.png")));
		panelBotones.add(btnNuevaLista);

		JButton btnRecientes = new JButton(" Recientes");
		btnRecientes.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/recientes.png")));
		panelBotones.add(btnRecientes);

		JButton btnPremium = new JButton(" Hazte Premium");
		btnPremium.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/premium.png")));
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.anchor = GridBagConstraints.EAST;
		gbc_btnPremium.gridwidth = 3;
		gbc_btnPremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnPremium.gridx = 3;
		gbc_btnPremium.gridy = 4;
		panelNorte.add(btnPremium, gbc_btnPremium);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 7;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		panelNorte.add(separator, gbc_separator);

	}
}