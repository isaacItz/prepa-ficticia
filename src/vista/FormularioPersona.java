package vista;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class FormularioPersona extends JPanel {
	private JTextField cajaNombre;
	private JTextField cajaPaterno;
	private JTextField cajaMaterno;
	private JTextField cajaCurp;
	private JRadioButton radioMasculino;
	private JRadioButton radioFemenino;
	private JDateChooser dateChooser;

	/**
	 * Create the panel.
	 */
	public FormularioPersona() {
		ButtonGroup grupo = new ButtonGroup();
		setLayout(new GridLayout(1, 0, 1, 10));

		JPanel panelPersonales = new JPanel();
		panelPersonales.setBorder(
				new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelPersonales);
		panelPersonales.setLayout(new GridLayout(0, 1, 0, 10));

		JLabel lblNombre = new JLabel("Nombre:");
		panelPersonales.add(lblNombre);

		cajaNombre = new JTextField();
		panelPersonales.add(cajaNombre);
		cajaNombre.setColumns(10);

		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
		panelPersonales.add(lblApellidoPaterno);

		cajaPaterno = new JTextField();
		panelPersonales.add(cajaPaterno);
		cajaPaterno.setColumns(10);

		JLabel lblAppellidoMaterno = new JLabel("Appellido Materno:");
		panelPersonales.add(lblAppellidoMaterno);

		cajaMaterno = new JTextField();
		panelPersonales.add(cajaMaterno);
		cajaMaterno.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo:");
		panelPersonales.add(lblSexo);

		JPanel panel = new JPanel();
		panelPersonales.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		radioMasculino = new JRadioButton("Masculino");
		panel.add(radioMasculino);

		radioFemenino = new JRadioButton("Femenino");
		panel.add(radioFemenino);

		JLabel lblNewLabel = new JLabel("Fecha de Nacimiento:");
		panelPersonales.add(lblNewLabel);

		dateChooser = new JDateChooser();
		panelPersonales.add(dateChooser);

		JLabel lblCurp = new JLabel("CURP:");
		panelPersonales.add(lblCurp);

		cajaCurp = new JTextField();
		panelPersonales.add(cajaCurp);
		cajaCurp.setColumns(10);
		grupo.add(radioFemenino);
		grupo.add(radioMasculino);

	}

	public JTextField getCajaNombre() {
		return cajaNombre;
	}

	public JTextField getCajaPaterno() {
		return cajaPaterno;
	}

	public JTextField getCajaMaterno() {
		return cajaMaterno;
	}

	public JTextField getCajaCurp() {
		return cajaCurp;
	}

	public JRadioButton getRadioMasculino() {
		return radioMasculino;
	}

	public JRadioButton getRadioFemenino() {
		return radioFemenino;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

}
