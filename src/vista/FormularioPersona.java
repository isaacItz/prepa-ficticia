package vista;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class FormularioPersona extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public FormularioPersona() {
		setLayout(new GridLayout(0, 1, 0, 10));
		
		JLabel lblNombre = new JLabel("Nombre:");
		add(lblNombre);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		add(lblApellidoPaterno);
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAppellidoMaterno = new JLabel("Appellido Materno:");
		add(lblAppellidoMaterno);
		
		textField_2 = new JTextField();
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		add(lblSexo);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Masculino");
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		panel.add(rdbtnFemenino);

	}

}
