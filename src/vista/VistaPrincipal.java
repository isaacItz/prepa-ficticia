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
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Adeudos;
import modelo.Alumnos;
import modelo.CiclosEscolares;
import modelo.Docentes;
import modelo.Grupos;
import modelo.Utileria;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private ObjectOutputStream creadorDeFlujo;
	private ObjectInputStream lectorDeFlujo;
	private Grupos grupos;
	private Alumnos alumnos;
	private Docentes docentes;
	private CiclosEscolares ciclosEscolares;
	private Adeudos adeudos;

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
		setTitle("Preparatoria");
		if (!cargarObjetos()) {
			this.grupos = new Grupos();
			this.docentes = new Docentes();
			this.ciclosEscolares = new CiclosEscolares();
			this.alumnos = new Alumnos();
			this.adeudos = new Adeudos();
			System.err.println("no se pudieron cargar los datos");
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 414);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDocentes = new JMenu("Docentes");
		menuBar.add(mnDocentes);

		JMenuItem mntmRegistrar_4 = new JMenuItem("Registrar");
		mntmRegistrar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroDocente vistaDocentes = new RegistroDocente(docentes);
				vistaDocentes.setVisible(true);
			}
		});
		mnDocentes.add(mntmRegistrar_4);

		JMenuItem mntmConsultar_3 = new JMenuItem("Consultar");
		mntmConsultar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (docentes.hayDocentes()) {
					BusquedaDocente doc = new BusquedaDocente(docentes);
					doc.setLocationRelativeTo(null);
					doc.setVisible(true);
				} else {
					Utileria.mensaje("No hay docentes registrados");
				}
			}
		});
		mnDocentes.add(mntmConsultar_3);

		JMenu mnGrupos = new JMenu("Grupos");
		menuBar.add(mnGrupos);

		JMenuItem mntmRegistrar_3 = new JMenuItem("Registrar");
		mntmRegistrar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (docentes.hayDocentes()) {
					RegistroGrupo regGrup = new RegistroGrupo(grupos, docentes);
					regGrup.setVisible(true);
				} else {
					Utileria.mensaje("Debe Registrar Docentes primero");
				}
			}
		});
		mnGrupos.add(mntmRegistrar_3);

		JMenu mnAlumnos = new JMenu("Alumnos");
		menuBar.add(mnAlumnos);

		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (grupos.hayGrupos()) {
					RegistroAlumno reg = new RegistroAlumno(grupos, alumnos, false);
					reg.setVisible(true);
				} else {
					Utileria.mensaje("Primero debe registar un grupo");
				}

			}
		});
		mnAlumnos.add(mntmRegistrar);

		JMenuItem mntmConsultar_1 = new JMenuItem("Consultar");
		mntmConsultar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (grupos.hayGrupos()) {
					if (grupos.hayAlumnos()) {
						BusquedaAlumnos busqA = new BusquedaAlumnos(grupos, alumnos);
						busqA.setVisible(true);
					} else {
						Utileria.mensaje("No hay Alumnos registrados");
					}
				} else {
					Utileria.mensaje("No hay grupos registrados;");
				}
			}
		});
		mnAlumnos.add(mntmConsultar_1);

		JMenu mnCalificaciones = new JMenu("Calificaciones");
		menuBar.add(mnCalificaciones);

		JMenuItem mntmRegistrar_2 = new JMenuItem("Registrar");
		mntmRegistrar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (grupos.hayGrupos()) {
					if (grupos.hayAlumnosDeAlta()) {
						RegistroCalificaciones regCal = new RegistroCalificaciones(grupos);
						regCal.setVisible(true);

					} else {
						Utileria.mensaje("No hay Alumnos dados de alta");
					}
				} else {
					Utileria.mensaje("No hay grupos registrados;");
				}
			}
		});
		mnCalificaciones.add(mntmRegistrar_2);

		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (grupos.hayGrupos()) {
					if (grupos.hayAlumnosDeAlta()) {
						RegistroCalificaciones regCal = new RegistroCalificaciones(grupos);
						regCal.soloConsultar();
						regCal.setVisible(true);

					} else {
						Utileria.mensaje("No hay Alumnos dados de alta");
					}
				} else {
					Utileria.mensaje("No hay grupos registrados;");
				}
			}
		});
		mnCalificaciones.add(mntmConsultar);

		JMenu mnAdeudos = new JMenu("Adeudos");
		menuBar.add(mnAdeudos);

		JMenuItem mntmRegistrar_1 = new JMenuItem("Registrar");
		mntmRegistrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (grupos.hayGrupos()) {
					if (grupos.hayAlumnosDeAlta()) {
						RegistroAdeudos regAdeu = new RegistroAdeudos(grupos, adeudos, false);
						regAdeu.setVisible(true);

					} else {
						Utileria.mensaje("No hay Alumnos dados de alta");
					}
				} else {
					Utileria.mensaje("No hay grupos registrados;");
				}
			}
		});
		mnAdeudos.add(mntmRegistrar_1);

		JMenuItem mntmConsultar_2 = new JMenuItem("Consultar");
		mntmConsultar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (grupos.hayGrupos()) {
					if (grupos.hayAlumnosDeAlta()) {
						ConsultaAdeudos regAdeu = new ConsultaAdeudos(grupos, adeudos);
						regAdeu.setVisible(true);

					} else {
						Utileria.mensaje("No hay Alumnos dados de alta");
					}
				} else {
					Utileria.mensaje("No hay grupos registrados;");
				}
			}
		});
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
				crearStream("docentes", docentes);
				crearStream("alumnos", alumnos);
				crearStream("ciclosEscolares", ciclosEscolares);
				crearStream("adeudos", adeudos);
				System.out.println("bye");
				System.exit(0);

			}
		});

		setLocationRelativeTo(null);
	}

	private <T> T leerStream(String nom, T coleccion) {
		File archivo = new File("objetos/objeto-" + nom + "");
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

	private <T> void crearStream(String nombre, T o) {
		try {
			Files.createDirectories(Paths.get("objetos/"));
			FileOutputStream archivo = new FileOutputStream("objetos/objeto-" + nombre + "");
			// archivo.flush();
			creadorDeFlujo = new ObjectOutputStream((archivo));
			creadorDeFlujo.writeObject(o);
			creadorDeFlujo.close();
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean cargarObjetos() {
		grupos = leerStream("grupos", grupos);
		docentes = leerStream("docentes", docentes);
		ciclosEscolares = leerStream("ciclosEscolares", ciclosEscolares);
		alumnos = leerStream("alumnos", alumnos);
		adeudos = leerStream("adeudos", adeudos);
		if (docentes == null)
			return false;
		if (grupos == null)
			return false;
		return true;
	}

}
