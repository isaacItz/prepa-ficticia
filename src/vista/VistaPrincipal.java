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
		
		JMenu mnAdeudos = new JMenu("Adeudos");
		menuBar.add(mnAdeudos);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mnAdeudos.add(mntmConsultar);
		
		JMenuItem mntmRegistrar_1 = new JMenuItem("Registrar");
		mnAdeudos.add(mntmRegistrar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
