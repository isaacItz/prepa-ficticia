package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Docente;
import modelo.Docentes;
import modelo.Grupos;

public class RegistroGrupo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox textField;
	private JComboBox textField_1;
	private Grupos grupos;
	private JComboBox textField_2;
	private DefaultComboBoxModel<String> modelDocentes;
	private Docentes docentes;

	public RegistroGrupo(Grupos grupos) {
		modelDocentes = new DefaultComboBoxModel<String>();
		this.grupos = grupos;

		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 30, 10));
		{
			JLabel lblGrado = new JLabel("Grado:");
			contentPanel.add(lblGrado);
		}
		{
			textField = new JComboBox();
			textField.setModel(new DefaultComboBoxModel(
					new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
			contentPanel.add(textField);
		}
		{
			JLabel lblLetra = new JLabel("Letra:");
			contentPanel.add(lblLetra);
		}
		{
			textField_1 = new JComboBox();
			textField_1.setModel(new DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F" }));
			contentPanel.add(textField_1);
		}
		{
			JLabel lblDocente = new JLabel("Docente:");
			contentPanel.add(lblDocente);
		}
		{
			textField_2 = new JComboBox();
			contentPanel.add(textField_2);
		}
		{
			JLabel lblCantidadDeParciales = new JLabel("Cantidad de Parciales:");
			contentPanel.add(lblCantidadDeParciales);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(
					new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));
			contentPanel.add(comboBox);
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
		llenarDocentes();
	}

	public void setDocentes(Docentes docentes) {
		this.docentes = docentes;
	}

	private void llenarDocentes() {
		for (Docente d : docentes.getDocentes()) {
			modelDocentes.addElement(d.getNombreCompleto());
		}
	}

}
