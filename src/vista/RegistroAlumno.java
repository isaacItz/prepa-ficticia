package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modelo.Alumno;
import modelo.Grupo;
import modelo.Grupos;
import modelo.Persona;
import modelo.Utileria;
import javax.swing.JRadioButton;

public class RegistroAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FormularioPersona formPersona;
	private JTextField cajaMatricula;
	private Grupos grupos;
	private JComboBox comboGrupo;
	private Alumno alumno;
	private Grupo grupo;
	private JRadioButton radioBaja;
	private JRadioButton radioAlta;

	public RegistroAlumno(Grupos grupos, boolean mod) {
		setTitle("Registro de Alumnos");
		this.grupos = grupos;
		setModal(true);
		formPersona = new FormularioPersona();
		setBounds(100, 100, 532, 589);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(formPersona, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Datos del Estudiante:", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new GridLayout(0, 1, 0, 0));

			JLabel lblGrupo = new JLabel("Grupo:");
			panel.add(lblGrupo);

			comboGrupo = new JComboBox<>(grupos.getGrupos().toArray());
			comboGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					grupo = (Grupo) comboGrupo.getSelectedItem();
					int mat = grupo.getAlumnos().size();
					cajaMatricula.setText(String.valueOf(mat));
				}
			});
			panel.add(comboGrupo);
			{
				JLabel lblMatricula = new JLabel("Matricula:");
				panel.add(lblMatricula);
			}
			{
				cajaMatricula = new JTextField();
				panel.add(cajaMatricula);
				cajaMatricula.setColumns(10);
			}
			if (mod) {
				setTitle("Modificacion de Alumnos");
				{

					JLabel lblStatus = new JLabel("Estatus:");
					panel.add(lblStatus);
				}
				{
					ButtonGroup grupo = new ButtonGroup();
					JPanel panel_1 = new JPanel();
					panel.add(panel_1);
					{
						radioAlta = new JRadioButton("Alta");
						panel_1.add(radioAlta);
					}
					{
						radioBaja = new JRadioButton("Baja");
						panel_1.add(radioBaja);
					}
					{
						grupo.add(radioBaja);
						grupo.add(radioAlta);
					}
				}
			}

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (validarAlumno()) {
							if (alumno == null) {
								if (grupos.existeAlumno(getAlumno())) {
									Utileria.mensaje("El alumno ya existe");
								} else {
									añadirAlumno();
									Utileria.mensaje("Alumnos Registrado");
									RegistroAlumno.this.dispose();
								}
							} else {
								modAlumno();
								Utileria.mensaje("Alumnos Modificado");
								RegistroAlumno.this.dispose();
							}
						} else {
							Utileria.mensaje("Datos incorrectos");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		setLocationRelativeTo(null);
	}

	public boolean validarAlumno() {
		if (!Utileria.validarEntero(cajaMatricula))
			return false;
		return true;
	}

	public Alumno getAlumno() {
		Alumno a = new Alumno();
		Persona p = formPersona.getPersona();
		a.setCurp(p.getCurp());
		a.setNombre(p.getNombre());
		a.setPaterno(p.getPaterno());
		a.setMaterno(p.getMaterno());
		a.setFechaNac(p.getFechaNac());
		a.setSexo(p.getSexo());
		a.setMatricula(Integer.parseInt(cajaMatricula.getText()));
		a.setGrupo((Grupo) comboGrupo.getSelectedItem());
		a.setStatus("Alta");
		return a;
	}

	public void setAlumno(Alumno a) {
		this.alumno = a;
		cajaMatricula.setText(String.valueOf(a.getMatricula()));
		comboGrupo.setSelectedItem(a.getGrupo());
		formPersona.setPersona(a);
		if (a.getStatus().equals("Alta")) {
			radioAlta.setSelected(true);
		} else {
			radioBaja.setSelected(true);
		}
	}

	private void añadirAlumno() {
		if (formPersona.validarPanel()) {
			Alumno d = getAlumno();
			if (!grupo.existeAlumno(d)) {
				grupo.agregarAlumno(d);
				RegistroAlumno.this.dispose();
				Utileria.mensaje("Alumno registrado con exito");
			} else {
				Utileria.mensaje("Alumno ya registrado");
			}

		} else {
			Utileria.mensaje("Llene correctamente los campos");
		}
	}

	private void modAlumno() {
		if (formPersona.validarPanel()) {
			formPersona.modPersona(alumno);
			alumno.setMatricula(Integer.parseInt(cajaMatricula.getText()));
			alumno.setGrupo(grupo);
			Utileria.mensaje("Alumno modificado");
			RegistroAlumno.this.dispose();

		} else {
			Utileria.mensaje("Llene correctamente los campos");
		}
	}
}
