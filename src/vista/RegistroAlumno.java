package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class RegistroAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FormularioPersona formPersona;
	private JTextField cajaMatricula;
	private Grupos grupos;
	private JComboBox comboGrupo;
	private Alumno alumno;

	public RegistroAlumno(Grupos grupos) {
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
			panel.setLayout(new GridLayout(0, 1, 0, 10));

			JLabel lblGrupo = new JLabel("Grupo:");
			panel.add(lblGrupo);

			comboGrupo = new JComboBox<>(grupos.getGrupos().toArray());
			comboGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int mat = ((Grupo) comboGrupo.getSelectedItem()).getAlumnos().size();
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
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
		return a;
	}

	public void setAlumno(Alumno a) {
		this.alumno = a;
		cajaMatricula.setText(String.valueOf(a.getMatricula()));
		comboGrupo.setSelectedItem(a.getGrupo());
		formPersona.setPersona(a);
	}

}
