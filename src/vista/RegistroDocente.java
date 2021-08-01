package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import modelo.Docente;
import modelo.Docentes;
import modelo.Utileria;

public class RegistroDocente extends JDialog {

	private final FormularioPersona contentPanel = new FormularioPersona();
	private Docentes docentes;

	public RegistroDocente(Docentes docentes) {
		this.docentes = docentes;

		setModal(true);
		setBounds(100, 100, 532, 407);

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (contentPanel.validarPanel()) {
							Docente d = contentPanel.getDocente();
							if (!docentes.existeDocente(d)) {
								docentes.agregarDocente(d);

								Utileria.mensaje("Persona registrada con exito");
							} else {
								Utileria.mensaje("Persona ya registrada");
							}

						} else {
							Utileria.mensaje("Llene correctamente los campos");
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
		setTitle("Registro de Docentes");
	}

}
