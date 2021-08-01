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
import modelo.Grupo;
import modelo.Grupos;

public class RegistroGrupo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<Integer> comboGrado;
	private JComboBox<Character> comboLetra;
	private Grupos grupos;
	private JComboBox<Docente> comboDocente;
	private DefaultComboBoxModel<Docente> modelDocentes;
	private Docentes docentes;

	public RegistroGrupo(Grupos grupos) {
		modelDocentes = new DefaultComboBoxModel<Docente>();
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
			comboGrado = new JComboBox<>();
			comboGrado.setModel(new DefaultComboBoxModel<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }));
			contentPanel.add(comboGrado);
		}
		{
			JLabel lblLetra = new JLabel("Letra:");
			contentPanel.add(lblLetra);
		}
		{
			comboLetra = new JComboBox<>();
			comboLetra.setModel(new DefaultComboBoxModel<>(new Character[] { 'A', 'B', 'C', 'D', 'E', 'F' }));
			contentPanel.add(comboLetra);
		}
		{
			JLabel lblDocente = new JLabel("Docente:");
			contentPanel.add(lblDocente);
		}
		{
			comboDocente = new JComboBox<>();
			contentPanel.add(comboDocente);
		}
		{
			JLabel lblCantidadDeParciales = new JLabel("Cantidad de Parciales:");
			contentPanel.add(lblCantidadDeParciales);
		}
		{
			JComboBox<Integer> comboParciales = new JComboBox<>();
			comboParciales.setModel(
					new DefaultComboBoxModel<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }));
			contentPanel.add(comboParciales);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
		llenarDocentes();
	}

	public void setDocentes(Docentes docentes) {
		this.docentes = docentes;
	}

	private void validarGrupo() {
		if (grupos.existeGrupo(comboGrado.getSelectedIndex() + 1, comboLetra.getSelectedItem().toString().charAt(0))) {

		}
	}

	private void registrarGrupo() {
		Grupo grupo = new Grupo();

	}

	private void llenarDocentes() {
		comboDocente.setModel(modelDocentes);
		for (Docente d : docentes.getDocentes()) {
			modelDocentes.addElement(d.getNombreCompleto());
		}
	}

}
