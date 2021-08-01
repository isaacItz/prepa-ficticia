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
import modelo.Grupo;
import modelo.Grupos;
import modelo.Utileria;

public class RegistroCalificaciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TablaBusqueda tabla;
	private Grupos grupos;
	private JComboBox<Grupo> comboGrupo;
	private DefaultComboBoxModel<Grupo> model;
	private Grupo grupo;
	private JButton okButton;
	private JPanel buttonPane;
	private JButton cancelButton;

	public RegistroCalificaciones(Grupos grupos) {
		this.grupos = grupos;
		model = new DefaultComboBoxModel<>();
		tabla = new TablaBusqueda();
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
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						guardarCalificaciones();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
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
	}

	private void llenarModelo() {
		model.addAll(grupos.getGrupos());
	}

	private void guardarCalificaciones() {
		if (tabla.validarCalificaciones()) {
			tabla.registrarCalificaciones();
			Utileria.mensaje("Calificaciones registradas");
			dispose();

		} else {
			Utileria.mensaje("Calificaciones deben ser enteros mayores a 0");
		}
	}
	
	public void soloConsultar() {
		buttonPane.remove(okButton);
		cancelButton.setText("Salir");
	}
}