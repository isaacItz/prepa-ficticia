package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAlumnos = new JMenu("Alumnos");
		menuBar.add(mnAlumnos);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mnAlumnos.add(mntmRegistrar);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mnAlumnos.add(mntmModificar);
		
		JMenu mnOperaciones = new JMenu("Operaciones");
		mnAlumnos.add(mnOperaciones);
		
		JMenuItem mntmBaja = new JMenuItem("Baja");
		mnOperaciones.add(mntmBaja);
		
		JMenuItem mntmAlta = new JMenuItem("Alta");
		mnOperaciones.add(mntmAlta);
		
		JMenuItem mntmConsultar_1 = new JMenuItem("Consultar");
		mnAlumnos.add(mntmConsultar_1);
		
		JMenu mnCalificaciones = new JMenu("Calificaciones");
		menuBar.add(mnCalificaciones);
		
		JMenuItem mntmRegistrar_2 = new JMenuItem("Registrar");
		mnCalificaciones.add(mntmRegistrar_2);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mnCalificaciones.add(mntmConsultar);
		
		JMenu mnAdeudos = new JMenu("Adeudos");
		menuBar.add(mnAdeudos);
		
		JMenuItem mntmRegistrar_1 = new JMenuItem("Registrar");
		mnAdeudos.add(mntmRegistrar_1);
		
		JMenuItem mntmConsultar_2 = new JMenuItem("Consultar");
		mnAdeudos.add(mntmConsultar_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
