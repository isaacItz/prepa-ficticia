package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import modelo.Adeudo;
import modelo.Adeudos;
import modelo.Grupo;
import modelo.Grupos;
import modelo.Utileria;

public class RegistroAdeudos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TablaBusqueda tabla;
	private TablaBusqueda tablaAdeudos;
	private JTextField cajaNombre;
	private JTextField cajaDescripcion;
	private JTextField cajaCosto;
	private JCheckBox checkTodos;
	private JCheckBox checkGrupo;
	private JCheckBox checkSeleccionados;
	private JDateChooser dateChooser;
	private JComboBox<Grupo> comboGrupo;
	private DefaultComboBoxModel<Grupo> model;
	private Grupo grupo;
	private Grupos grupos;
	private Adeudos adeudos;

	public RegistroAdeudos(Grupos grupos, Adeudos adeudos, boolean consultar) {
		tabla = new TablaBusqueda(true);
		this.adeudos = adeudos;
		this.grupos = grupos;
		model = new DefaultComboBoxModel<>();
		comboGrupo = tabla.getComboGrupo();
		comboGrupo.setModel(model);
		llenarModelo();
		comboGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setGrupo();
			}
		});
		setTitle("Registro de Adeudos");
		setBounds(100, 100, 566, 530);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel panelpago = new JPanel();

		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			contentPanel.add(tabla);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel lblNombre = new JLabel("Nombre:");
				panel.add(lblNombre);
			}
			{
				cajaNombre = new JTextField();
				panel.add(cajaNombre);
				cajaNombre.setColumns(10);
			}
			{
				JLabel lblDescripcion = new JLabel("Descripcion:");
				panel.add(lblDescripcion);
			}
			{
				cajaDescripcion = new JTextField();
				panel.add(cajaDescripcion);
				cajaDescripcion.setColumns(10);
			}
			{
				JLabel lblFechaLimite = new JLabel("Fecha Limite:");
				panel.add(lblFechaLimite);
			}
			{
				dateChooser = new JDateChooser();
				panel.add(dateChooser);
			}
			{
				JLabel lblNewLabel = new JLabel("Costo:");
				panel.add(lblNewLabel);
			}
			{
				cajaCosto = new JTextField();
				panel.add(cajaCosto);
				cajaCosto.setColumns(10);
			}
			{
				JPanel panel_1 = new JPanel();
				ButtonGroup grupo = new ButtonGroup();
				panel.add(panel_1);
				{
					checkTodos = new JCheckBox("Todos Los Alumnos");
					grupo.add(checkTodos);
					panel_1.add(checkTodos);
					checkTodos.setSelected(true);
				}
				{
					checkGrupo = new JCheckBox("Todo el Grupo");
					grupo.add(checkGrupo);
					panel_1.add(checkGrupo);
				}
				{
					checkSeleccionados = new JCheckBox("Alumnos Seleccionados");
					grupo.add(checkSeleccionados);
					panel_1.add(checkSeleccionados);
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
						if (validar()) {
							adeudos.agregarAdeudo(getAdeudo());
							Utileria.mensaje("Adeudo añadido");
							RegistroAdeudos.this.dispose();
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
		comboGrupo.setSelectedIndex(0);
		setGrupo();
		if (consultar) {
			contentPanel.add(panelpago);
			setBounds(100, 100, 700, 530);
			tablaAdeudos = new TablaBusqueda(false);
			tablaAdeudos.setTablaAdeudos(adeudos);
			tablaAdeudos.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					tabla.setTablaAdeudo(tablaAdeudos.adeudoSeleccionado());
				}
			});
		}
	}

	private boolean validar() {
		if (!Utileria.validarCaja(cajaNombre)) {
			Utileria.mensaje("Escriba un nombre");
			return false;
		}
		if (!Utileria.validarFecha(dateChooser)) {
			Utileria.mensaje("Fecha no valida");
			return false;
		}
		if (!Utileria.esFechaFutura(dateChooser)) {
			Utileria.mensaje("No es una fecha proxima");
			return false;
		}
		if (!Utileria.validarCaja(cajaCosto)) {
			Utileria.mensaje("Llene el costo");
			return false;
		}
		if (!Utileria.esDouble(cajaCosto) && Utileria.getDecimal() < 0) {
			Utileria.mensaje("El Costo debe ser un decimal positivo");
			return false;
		}
		if (checkSeleccionados.isSelected()) {
			if (tabla.getAlumnosSeleccionados().size() == 0) {
				Utileria.mensaje("seleccione al menos un alumno");
				return false;
			}
		}
		return true;
	}

	private Adeudo getAdeudo() {
		Adeudo a = new Adeudo();
		a.setNombre(cajaNombre.getText());
		a.setDescripcion(cajaDescripcion.getText());
		a.setFecha(Utileria.toLocalDate(dateChooser.getDate()));
		Utileria.esDouble(cajaCosto);
		a.setCosto(Utileria.getDecimal());
		System.out.println(a.getCosto());
		if (checkTodos.isSelected()) {
			a.setAlumnos(grupos.getAlumnos());
		} else if (checkGrupo.isSelected()) {
			a.setGrupo(grupo);
		} else {
			a.setAlumnos(tabla.getAlumnosSeleccionados());
		}
		return a;
	}

	private void setGrupo() {
		grupo = (Grupo) comboGrupo.getSelectedItem();
		tabla.setTablaAdeudos(grupo);
	}

	private void llenarModelo() {
		model.addAll(grupos.getGrupos());
	}
}
