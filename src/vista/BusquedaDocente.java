package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Docentes;
import modelo.Utileria;

public class BusquedaDocente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Docentes docentes;
	private TablaBusqueda tabla;

	public BusquedaDocente(Docentes docentes) {
		setModal(true);
		this.docentes = docentes;
		setTitle("Consulta de Docentes");
		setBounds(100, 100, 595, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		tabla = new TablaBusqueda(false);
		tabla.setTablaDocentes(this.docentes);
		tabla.setEtiquetaTexto("Busqueda:");

		contentPanel.add(tabla, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (tabla.hayFilaSeleccionada()) {
							RegistroDocente modDoc = new RegistroDocente(tabla.getDocenteSeleccionado());
							modDoc.setTitle("Modificacion de Docente");
							modDoc.setVisible(true);
							refrescarTabla();
						} else {
							Utileria.mensaje("No hay un docente seleccionado");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
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

	private void refrescarTabla() {
		tabla.setTablaDocentes(this.docentes);
	}
}
