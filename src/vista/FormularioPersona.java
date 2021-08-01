package vista;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import modelo.Alumno;
import modelo.Docente;
import modelo.Persona;
import modelo.Utileria;

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

	public boolean validarPanel() {
		if (cajaNombre.getText().isEmpty())
			return false;
		if (cajaPaterno.getText().isEmpty())
			return false;
		if (cajaMaterno.getText().isEmpty())
			return false;
		if (cajaCurp.getText().isEmpty())
			return false;
		if (dateChooser.getDate() == null)
			return false;
		if (!radioFemenino.isSelected() && !radioMasculino.isSelected())
			return false;
		return true;
	}

	public Persona getPersona() {
		Persona p = new Persona();
		p.setNombre(cajaNombre.getText());
		p.setPaterno(cajaPaterno.getText());
		p.setMaterno(cajaMaterno.getText());
		if (radioMasculino.isSelected())
			p.setSexo('M');
		else
			p.setSexo('F');
		LocalDate fecha = Utileria.toLocalDate(dateChooser.getDate());
		p.setFechaNac(fecha);
		p.setCurp(cajaCurp.getText());
		return p;
	}

	public Alumno getAlumno() {
		Alumno p = new Alumno();
		p.setNombre(cajaNombre.getText());
		p.setPaterno(cajaPaterno.getText());
		p.setMaterno(cajaMaterno.getText());
		if (radioMasculino.isSelected())
			p.setSexo('M');
		else
			p.setSexo('F');
		LocalDate fecha = Utileria.toLocalDate(dateChooser.getDate());
		p.setFechaNac(fecha);
		p.setCurp(cajaCurp.getText());
		return p;
	}
	public Docente getDocente() {
		Docente p = new Docente();
		p.setNombre(cajaNombre.getText());
		p.setPaterno(cajaPaterno.getText());
		p.setMaterno(cajaMaterno.getText());
		if (radioMasculino.isSelected())
			p.setSexo('M');
		else
			p.setSexo('F');
		LocalDate fecha = Utileria.toLocalDate(dateChooser.getDate());
		p.setFechaNac(fecha);
		p.setCurp(cajaCurp.getText());
		return p;
	}

	public void modDocente(Docente p) {
		p.setNombre(cajaNombre.getText());
		p.setPaterno(cajaPaterno.getText());
		p.setMaterno(cajaMaterno.getText());
		if (radioMasculino.isSelected())
			p.setSexo('M');
		else
			p.setSexo('F');
		LocalDate fecha = Utileria.toLocalDate(dateChooser.getDate());
		p.setFechaNac(fecha);
		p.setCurp(cajaCurp.getText());
	}
	
	public void modPersona(Persona p) {
		p.setNombre(cajaNombre.getText());
		p.setPaterno(cajaPaterno.getText());
		p.setMaterno(cajaMaterno.getText());
		if (radioMasculino.isSelected())
			p.setSexo('M');
		else
			p.setSexo('F');
		LocalDate fecha = Utileria.toLocalDate(dateChooser.getDate());
		p.setFechaNac(fecha);
		p.setCurp(cajaCurp.getText());
	}

	public void setPersona(Persona d) {
		cajaNombre.setText(d.getNombre());
		cajaPaterno.setText(d.getPaterno());
		cajaMaterno.setText(d.getMaterno());
		cajaCurp.setText(d.getCurp());
		if (d.getSexo() == 'M') {
			radioMasculino.setSelected(true);
		} else {
			radioFemenino.setSelected(true);
		}
		dateChooser.setDate(Utileria.toDate(d.getFechaNac()));
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
