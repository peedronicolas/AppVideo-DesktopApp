package appvideo.vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import appvideo.controlador.ControladorAppVideo;
import appvideo.lanzador.MainWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelLogin extends JPanel {

	private ControladorAppVideo controlador = ControladorAppVideo.getUnicaInstancia();

	private static final long serialVersionUID = 1L;
	private JTextField textUsuario;
	private JPasswordField txtPassword;
	private JLabel lblErrores;
	private String imgPath = "./src/main/java/appvideo/recursos/";

	/**
	 * Create the panel.
	 */
	public PanelLogin() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 5, 0, 40, 0, 0, 20, 0, 15, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(imgPath + "yt-logo.png"));
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.gridwidth = 2;
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 1;
		gbc_lblIcon.gridy = 1;
		add(lblIcon, gbc_lblIcon);

		JLabel lblTitulo = new JLabel("AppVideo - PNG");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 35));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 3;
		add(lblTitulo, gbc_lblTitulo);

		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 5;
		add(lblUsuario, gbc_lblUsuario);

		textUsuario = new JTextField();
		textUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					login();
			}
		});
		textUsuario.setColumns(10);
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 5;
		add(textUsuario, gbc_textUsuario);

		JLabel lblPassword = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 6;
		add(lblPassword, gbc_lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					login();
			}
		});
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 6;
		add(txtPassword, gbc_txtPassword);

		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.getUnicaInstancia().showPanelRegistro();
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 8;
		add(btnRegistrar, gbc_btnRegistrar);

		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		GridBagConstraints gbc_btnIniciarSesion = new GridBagConstraints();
		gbc_btnIniciarSesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciarSesion.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciarSesion.gridx = 2;
		gbc_btnIniciarSesion.gridy = 8;
		add(btnIniciarSesion, gbc_btnIniciarSesion);

		lblErrores = new JLabel("");
		lblErrores.setForeground(Color.RED);
		lblErrores.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrores.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		GridBagConstraints gbc_lblErrores = new GridBagConstraints();
		gbc_lblErrores.anchor = GridBagConstraints.WEST;
		gbc_lblErrores.gridwidth = 2;
		gbc_lblErrores.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrores.gridx = 1;
		gbc_lblErrores.gridy = 10;
		add(lblErrores, gbc_lblErrores);
	}

	private void showError(String msg) {
		lblErrores.setText(msg);
	}

	private void login() {

		String user = textUsuario.getText();
		String pass = String.valueOf(txtPassword.getPassword());

		if ((user == null || user.equals("")) && (pass == null || pass.equals(""))) {
			showError("*Campo usuario y contraseña en blanco.");
			return;
		}

		if (user == null || user.equals("")) {
			showError("*Campo usuario en blanco.");
			return;
		}

		if (pass == null || pass.equals("")) {
			showError("*Campo contraseña en blanco.");
			return;
		}

		boolean isLogin = controlador.login(user, pass);

		if (!isLogin) {
			showError("*Usuario o contraseña no válidos.");
			return;
		}

		// TODO Habra que cambiarlo para que nos lleve al panel principal cuando este
		// desarrollado.
		MainWindow.getUnicaInstancia().showPanelLogin();
	}
}