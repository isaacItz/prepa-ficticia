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
import modelo.Parciales;
import modelo.Utileria;

public class RegistroGrupo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<Integer> comboGrado;
	private JComboBox<Character> comboLetra;
	private Grupos grupos;
	private JComboBox<Docente> comboDocente;
	private DefaultComboBoxModel<Docente> modelDocentes;
	private Docentes docentes;
	private Docente docente;
	private JComboBox<Integer> comboParciales;

	public RegistroGrupo(Grupos grupos, Docentes docentes) {
		this.docentes = docentes;
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
			comboDocente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDocente();
				}
			});
			contentPanel.add(comboDocente);
		}
		{
			JLabel lblCantidadDeParciales = new JLabel("Cantidad de Parciales:");
			contentPanel.add(lblCantidadDeParciales);
		}
		{
			comboParciales = new JComboBox<>();
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
						if (validarGrupo()) {
							registrarGrupo();
							Utileria.mensaje("Grupo Registrado");
							RegistroGrupo.this.dispose();
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
		llenarDocentes();
		setLocationRelativeTo(null);
	}

	private boolean validarGrupo() {
		if (grupos.existeGrupo(comboGrado.getSelectedIndex() + 1, comboLetra.getSelectedItem().toString().charAt(0))) {
			Utileria.mensaje("Grupo ya registrado");
			return false;
		}
		return true;
	}

	private void registrarGrupo() {
		grupos.agregarGrupo(getGrupo());
	}

	public Grupo getGrupo() {
		Grupo g = new Grupo();
		g.setDocente(docente);
		g.setLetra(comboLetra.getItemAt(comboLetra.getSelectedIndex()));
		Parciales p = new Parciales(comboParciales.getSelectedIndex() + 1);
		g.setParciales(p);
		g.setSemestre(comboGrado.getSelectedIndex() + 1);
		return g;
	}

	private void setDocente() {
		docente = (Docente) comboDocente.getSelectedItem();
	}

	private void llenarDocentes() {
		comboDocente.setModel(modelDocentes);
		for (Docente d : docentes.getDocentes()) {
			modelDocentes.addElement(d);
		}
	}

}
