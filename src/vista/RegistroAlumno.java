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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modelo.Alumno;
import modelo.Alumnos;
import modelo.Grupo;
import modelo.Grupos;
import modelo.Persona;
import modelo.Utileria;

public class RegistroAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FormularioPersona formPersona;
	private JTextField cajaMatricula;
	private Grupos grupos;
	private JComboBox comboGrupo;
	private Alumno alumno;
	private Grupo grupo;
	private Grupo grupoAnterior;
	private JRadioButton radioBaja;
	private JRadioButton radioAlta;
	private Alumnos alumnos;

	public RegistroAlumno(Grupos grupos, Alumnos alumnos, boolean mod) {
		this.alumnos = alumnos;
		setTitle("Registro de Alumnos");
		this.grupos = grupos;
		setModal(true);
		formPersona = new FormularioPersona();
		if (!mod)
			setBounds(100, 100, 532, 589);
		else
			setBounds(100, 100, 532, 750);
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
					setMatricula();
				}
			});
			panel.add(comboGrupo);
			{
				JLabel lblMatricula = new JLabel("Matricula:");
				panel.add(lblMatricula);
			}
			{
				cajaMatricula = new JTextField();
				cajaMatricula.setEditable(false);
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
								alumno = getAlumno();
								if (!grupos.existeAlumno(alumno)) {
									añadirAlumno();
									Utileria.mensaje("Alumno Registrado");
									RegistroAlumno.this.dispose();
								} else {
									Utileria.mensaje("El alumno ya esta en otro grupo");
								}

							} else {
								modAlumno();
							}
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

		setMatricula();
		setLocationRelativeTo(null);
	}

	public boolean validarAlumno() {
		if (!formPersona.validarPanel()) {
			return false;
		}
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
		grupoAnterior = a.getGrupo();
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
		Alumno d = getAlumno();
		alumnos.agregarAlumno(d);
		grupo.agregarAlumno(d);
	}

	private void modAlumno() {
		if (formPersona.validarPanel()) {
			grupoAnterior.eliminarAlumno(alumno);
			grupo.agregarAlumno(alumno);
			formPersona.modPersona(alumno);
			alumno.setGrupo(grupo);
			if (radioAlta.isSelected()) {
				alumno.setStatus("Alta");
			} else {
				alumno.setStatus("Baja");
			}
			Utileria.mensaje("Alumno modificado");
			RegistroAlumno.this.dispose();

		} else {
			Utileria.mensaje("Llene correctamente los campos");
		}
	}

	private void setMatricula() {
		grupo = (Grupo) comboGrupo.getSelectedItem();
		int mat = alumnos.getMatricula();
		cajaMatricula.setText(String.valueOf(mat));
	}
}
