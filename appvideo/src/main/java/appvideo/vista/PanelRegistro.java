package appvideo.vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;

import appvideo.controlador.ControladorAppVideo;
import appvideo.lanzador.MainWindow;

import java.awt.Color;
import javax.swing.ImageIcon;

public class PanelRegistro extends JPanel {

	private ControladorAppVideo controlador = ControladorAppVideo.getUnicaInstancia();

	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEmail;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtC_Password;
	private JDateChooser dateChooser;
	private JLabel lblErrores;

	public PanelRegistro() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 5, 0, 40, 0, 0, 0, 0, 15, 0, 0, 0, 10, 0, 10, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(PanelRegistro.class.getResource("/appvideo/recursos/yt-logo.png")));
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.gridwidth = 2;
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 1;
		gbc_lblIcon.gridy = 1;
		add(lblIcon, gbc_lblIcon);

		JLabel lblTitulo = new JLabel("AppVideo - PNG");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 35));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblTitulo.gridwidth = 2;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 3;
		add(lblTitulo, gbc_lblTitulo);

		JLabel lblNombre = new JLabel("*Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 5;
		add(lblNombre, gbc_lblNombre);

		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 5;
		add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.getUnicaInstancia().showPanelLogin();
			}
		});

		JLabel lblApellidos = new JLabel("Apellidos:");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 6;
		add(lblApellidos, gbc_lblApellidos);

		textApellidos = new JTextField();
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textApellidos.gridx = 2;
		gbc_textApellidos.gridy = 6;
		add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(10);

		JLabel lblF_Nacimiento = new JLabel("*F. Nacimiento:");
		GridBagConstraints gbc_lblF_Nacimiento = new GridBagConstraints();
		gbc_lblF_Nacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblF_Nacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblF_Nacimiento.gridx = 1;
		gbc_lblF_Nacimiento.gridy = 7;
		add(lblF_Nacimiento, gbc_lblF_Nacimiento);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 7;
		add(dateChooser, gbc_dateChooser);

		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 8;
		add(lblEmail, gbc_lblEmail);

		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.gridx = 2;
		gbc_textEmail.gridy = 8;
		add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);

		JLabel lblUsername = new JLabel("*Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 10;
		add(lblUsername, gbc_lblUsername);

		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 10;
		add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("*Contraseña:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 11;
		add(lblPassword, gbc_lblPassword);

		txtPassword = new JPasswordField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 11;
		add(txtPassword, gbc_txtPassword);

		JLabel lblC_Password = new JLabel("*Repetir Contraseña:");
		GridBagConstraints gbc_lblC_Password = new GridBagConstraints();
		gbc_lblC_Password.anchor = GridBagConstraints.EAST;
		gbc_lblC_Password.insets = new Insets(0, 0, 5, 5);
		gbc_lblC_Password.gridx = 1;
		gbc_lblC_Password.gridy = 12;
		add(lblC_Password, gbc_lblC_Password);

		txtC_Password = new JPasswordField();
		GridBagConstraints gbc_txtC_Password = new GridBagConstraints();
		gbc_txtC_Password.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtC_Password.insets = new Insets(0, 0, 5, 5);
		gbc_txtC_Password.gridx = 2;
		gbc_txtC_Password.gridy = 12;
		add(txtC_Password, gbc_txtC_Password);

		lblErrores = new JLabel("*Campos obligatorios.");
		lblErrores.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblErrores = new GridBagConstraints();
		gbc_lblErrores.anchor = GridBagConstraints.WEST;
		gbc_lblErrores.gridwidth = 2;
		gbc_lblErrores.insets = new Insets(0, 0, 5, 5);
		gbc_lblErrores.gridx = 1;
		gbc_lblErrores.gridy = 14;
		add(lblErrores, gbc_lblErrores);
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAtras.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtras.gridx = 1;
		gbc_btnAtras.gridy = 16;
		add(btnAtras, gbc_btnAtras);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 2;
		gbc_btnRegistrar.gridy = 16;
		add(btnRegistrar, gbc_btnRegistrar);

	}

	private void showError(String msg) {
		lblErrores.setText(msg);
		lblErrores.setForeground(Color.RED);
	}

	private void registrar() {

		String nombre = textNombre.getText();
		String apellidos = textApellidos.getText();
		Date fNacimiento = dateChooser.getDate();
		String email = textEmail.getText();
		String username = txtUsername.getText();
		String password = String.valueOf(txtPassword.getPassword());
		String C_password = String.valueOf(txtC_Password.getPassword());

		if (nombre == null || nombre.equals("")) {
			showError("* El campo 'nombre' es obligatorio.");
			return;
		}

		if (apellidos == null || apellidos.equals(""))
			apellidos = "";

		if (fNacimiento == null) {
			showError("* El campo 'F.Nacimiento' es obligatorio.");
			return;
		}

		if (fNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())) {
			showError("* F.Nacimiento debe ser anterior a F.Actual.");
			return;
		}

		if (email == null || email.equals(""))
			email = "";

		if (username == null || username.equals("")) {
			showError("* El campo 'Username' es obligatorio.");
			return;
		}

		if (password == null || password.equals("")) {
			showError("* El campo 'Password' es obligatorio.");
			return;
		}

		if (C_password == null || C_password.equals("")) {
			showError("* El campo 'Rep. Contraseña' es obligatorio.");
			return;
		}

		if (!password.equals(C_password)) {
			showError("* Las contraseñas deben coincidir.");
			return;
		}

		boolean isRegistrado = controlador.registrarusuario(nombre, apellidos, fNacimiento, email, username, password);

		if (!isRegistrado) {
			showError("* Username no disponible.");
			return;
		}

		// Se ha registrado con exito, mostramos msg y redirigimos a login.
		JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
		MainWindow.getUnicaInstancia().showPanelLogin();
	}
}