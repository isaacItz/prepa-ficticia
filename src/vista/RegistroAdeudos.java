package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import modelo.Utileria;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroAdeudos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TablaBusqueda tabla;
	private JTextField cajaNombre;
	private JTextField cajaDescripcion;
	private JTextField cajaCosto;
	private JCheckBox checkTodos;
	private JCheckBox checkGrupo;
	private JCheckBox checkSeleccionados;
	private JDateChooser dateChooser;

	public RegistroAdeudos() {
		setTitle("Registro de Adeudos");
		setBounds(100, 100, 566, 530);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
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
		tabla = new TablaBusqueda(true);
		contentPanel.add(tabla);
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
		return true;
	}

	private Adeudo getAdeudo() {
		return null;
	}
}
