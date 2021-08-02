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

public class ConsultaAdeudos extends JDialog {

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
	private Grupo grupo;
	private Grupos grupos;
	private Adeudos adeudos;

	public ConsultaAdeudos(Grupos grupos, Adeudos adeudos) {
		tabla = new TablaBusqueda(false);
		tabla.setBorder("Alumnos");
		this.adeudos = adeudos;
		this.grupos = grupos;

		setTitle("Consulta de Adeudos");

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel panelpago = new JPanel();
		contentPanel.add(panelpago);
		setBounds(100, 100, 700, 658);
		panelpago.setLayout(new GridLayout(0, 1, 0, 0));
		tablaAdeudos = new TablaBusqueda(false);
		tablaAdeudos.setBorder("Adeudos");
		panelpago.add(tablaAdeudos);
		tablaAdeudos.setTablaAdeudos(adeudos);
		{
			JPanel panel = new JPanel();
//			contentPanel.add(panel);
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
				JButton okButton = new JButton("Salir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ConsultaAdeudos.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setLocationRelativeTo(null);

		tablaAdeudos.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				tabla.setTablaAdeudo(tablaAdeudos.adeudoSeleccionado());
			}
		});

	}

}
