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

import appvideo.controlador.ControladorAppVideo;
import appvideo.lanzador.MainWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private ControladorAppVideo controlador = ControladorAppVideo.getUnicaInstancia();
	private JPanel panelPrincipalActual = new PanelExplorar();

	/**
	 * Create the panel.
	 */
	public PanelPrincipal() {
		
		setLayout(new BorderLayout(0, 0));

		JPanel panelBarraNavegacion = new JPanel();
		add(panelBarraNavegacion, BorderLayout.NORTH);
		GridBagLayout gbl_panelBarraNavegacion = new GridBagLayout();
		gbl_panelBarraNavegacion.columnWidths = new int[] { 20, 0, 0, 10, 10, 0, 20, 0 };
		gbl_panelBarraNavegacion.rowHeights = new int[] { 20, 0, 20, 0, 0, 0, 20, 0 };
		gbl_panelBarraNavegacion.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelBarraNavegacion.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		panelBarraNavegacion.setLayout(gbl_panelBarraNavegacion);

		JLabel lblIcon = new JLabel(" AppVideo - PNG");
		lblIcon.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblIcon.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/yt-logo-p.png")));
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 1;
		gbc_lblIcon.gridy = 1;
		panelBarraNavegacion.add(lblIcon, gbc_lblIcon);

		JLabel lblWelcomeUser = new JLabel("Hola, " + controlador.getUsuarioActual().getUsername() + "!");
		GridBagConstraints gbc_lblWelcomeUser = new GridBagConstraints();
		gbc_lblWelcomeUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcomeUser.gridx = 3;
		gbc_lblWelcomeUser.gridy = 1;
		panelBarraNavegacion.add(lblWelcomeUser, gbc_lblWelcomeUser);

		JButton btnLogout = new JButton(" Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.logout();
				MainWindow.getUnicaInstancia().showPanelLogin();
			}
		});
		btnLogout.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/logout.png")));
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogout.gridx = 5;
		gbc_btnLogout.gridy = 1;
		panelBarraNavegacion.add(btnLogout, gbc_btnLogout);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 7;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		panelBarraNavegacion.add(separator_1, gbc_separator_1);

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
		panelBarraNavegacion.add(panelBotones, gbc_panelBotones);

		JButton btnExplorar = new JButton(" Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanelPrincipalActual(new PanelExplorar());
			}
		});
		btnExplorar.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/lupa.png")));
		panelBotones.add(btnExplorar);

		JButton btnMisListas = new JButton(" Mis Listas");
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanelPrincipalActual(new PanelMisListas());
			}
		});
		btnMisListas.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/lista.png")));
		panelBotones.add(btnMisListas);

		JButton btnNuevaLista = new JButton(" Nueva Lista");
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanelPrincipalActual(new PanelNuevaLista());
			}
		});
		btnNuevaLista.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/addLista.png")));
		panelBotones.add(btnNuevaLista);

		JButton btnRecientes = new JButton(" Recientes");
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanelPrincipalActual(new PanelRecientes());
			}
		});
		btnRecientes.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/recientes.png")));
		panelBotones.add(btnRecientes);

		JButton btnPremium = new JButton(" Hazte Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanelPrincipalActual(new PanelPremium());
			}
		});
		btnPremium.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/appvideo/recursos/premium.png")));
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.anchor = GridBagConstraints.EAST;
		gbc_btnPremium.gridwidth = 3;
		gbc_btnPremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnPremium.gridx = 3;
		gbc_btnPremium.gridy = 4;
		panelBarraNavegacion.add(btnPremium, gbc_btnPremium);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 7;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		panelBarraNavegacion.add(separator, gbc_separator);

		add(panelPrincipalActual, BorderLayout.CENTER);
	}

	private void cambiarPanelPrincipalActual(JPanel panel) {
		remove(panelPrincipalActual);
		panelPrincipalActual = panel;
		add(panelPrincipalActual, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}