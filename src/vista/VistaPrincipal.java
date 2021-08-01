package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Grupos;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private ObjectOutputStream creadorDeFlujo;
	private ObjectInputStream lectorDeFlujo;
	private Grupos grupos;

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
		if (!cargarObjetos()) {
			this.grupos = new Grupos();
			System.out.println("no se pudieron cargar los datos");
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDocentes = new JMenu("Docentes");
		menuBar.add(mnDocentes);
		
		JMenuItem mntmRegistrar_4 = new JMenuItem("Registrar");
		mnDocentes.add(mntmRegistrar_4);
		
		JMenuItem mntmModificar_1 = new JMenuItem("Modificar");
		mnDocentes.add(mntmModificar_1);
		
		JMenuItem mntmConsultar_3 = new JMenuItem("Consultar");
		mnDocentes.add(mntmConsultar_3);

		JMenu mnGrupos = new JMenu("Grupos");
		menuBar.add(mnGrupos);

		JMenuItem mntmRegistrar_3 = new JMenuItem("Registrar");
		mntmRegistrar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnGrupos.add(mntmRegistrar_3);

		JMenu mnAlumnos = new JMenu("Alumnos");
		menuBar.add(mnAlumnos);

		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
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

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				setVisible(false);

				// if (!new File("P").exists())
				crearStream("grupos", grupos);
				System.exit(0);

			}
		});

		setLocationRelativeTo(null);
	}

	private <T> T leerStream(String nom, T coleccion) {
		File archivo = new File("objeto-" + nom + "");
		if (archivo.exists()) {
			try {
				lectorDeFlujo = new ObjectInputStream(new FileInputStream(archivo));
				coleccion = ((T) lectorDeFlujo.readObject());

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return coleccion;
	}

	private void crearStream(String nombre, Object o) {
		try {
			File archivo = new File("objeto-" + nombre + "");
			archivo.createNewFile();
			creadorDeFlujo = new ObjectOutputStream(new FileOutputStream(archivo));
			creadorDeFlujo.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean cargarObjetos() {
		grupos = leerStream("grupos", grupos);
		if (grupos == null)
			return false;
		return true;
	}

}
