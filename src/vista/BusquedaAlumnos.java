package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Alumno;
import modelo.Alumnos;
import modelo.Grupo;
import modelo.Grupos;
import modelo.Utileria;

public class BusquedaAlumnos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TablaBusqueda tabla;
	private Grupos grupos;
	private JComboBox<Grupo> comboGrupo;
	private DefaultComboBoxModel<Grupo> model;
	private Grupo grupo;
	private Alumnos alumnos;

	public BusquedaAlumnos(Grupos grupos, Alumnos alumnos) {
		this.grupos = grupos;
		this.alumnos = alumnos;
		model = new DefaultComboBoxModel<>();
		tabla = new TablaBusqueda(true);
		comboGrupo = tabla.getComboGrupo();
		comboGrupo.setModel(model);
		llenarModelo();
		comboGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setGrupo();
			}
		});

		setModal(true);
		setBounds(100, 100, 554, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(tabla, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar Alumno");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						modAlumno();
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

	private void setGrupo() {
		grupo = (Grupo) comboGrupo.getSelectedItem();
		tabla.setTablaAlumnos(grupo);
	}

	private void llenarModelo() {
		model.addAll(grupos.getGrupos());
	}

	private void modAlumno() {
		if (tabla.hayFilaSeleccionada()) {
			Alumno a = tabla.getAlumnoSeleccionado();
			RegistroAlumno modA = new RegistroAlumno(grupos, alumnos, true);
			modA.setAlumno(a);
			modA.setVisible(true);
			refrescarTabla();
		} else {
			Utileria.mensaje("No hay algun alumno seleccionado");
		}
	}

	private void refrescarTabla() {
		setGrupo();
	}
}
