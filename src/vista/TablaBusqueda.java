package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.PatternSyntaxException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Adeudo;
import modelo.Adeudos;
import modelo.Alumno;
import modelo.Docente;
import modelo.Docentes;
import modelo.Grupo;
import modelo.Parcial;
import modelo.Utileria;

public class TablaBusqueda extends JPanel {
	private JTextField textField;
	private JTable table;
	private JLabel etiquetaTexto;
	private DefaultTableModel modelo;
	private TableRowSorter<TableModel> sorter;
	private Docentes docentes;
	private Grupo grupo;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblGrupo;
	private JComboBox<Grupo> comboGrupo;
	private List<Double[]> listaCalificaciones;
	private Adeudos adeudos;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public TablaBusqueda(boolean withGroup) {
		setBorder(new TitledBorder(null, "Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		panel_2 = new JPanel();
		if (withGroup)
			panel_1.add(panel_2);

		lblGrupo = new JLabel("Grupo:");
		panel_2.add(lblGrupo);

		comboGrupo = new JComboBox<>();

		panel_2.add(comboGrupo);

		JPanel panel = new JPanel();
		panel_1.add(panel);

		etiquetaTexto = new JLabel("Ingrese el nombre:");
		panel.add(etiquetaTexto);

		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				filtro();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				filtro();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				filtro();
			}
		});
		panel.add(textField);
		textField.setColumns(15);

	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setEtiquetaTexto(String etiquetaTexto) {
		this.etiquetaTexto.setText(etiquetaTexto);
	}

	public void setTablaDocentes(Docentes docentes) {
		this.docentes = docentes;
		List<String[]> mat = new ArrayList<>();
		for (Docente d : docentes.getDocentes()) {
			String[] fila = new String[6];
			System.out.println(d);
			fila[0] = d.getNombre();
			fila[1] = d.getPaterno();
			fila[2] = d.getMaterno();
			fila[3] = d.getCurp();
			fila[4] = Utileria.formatearFecha(d.getFechaNac());
			fila[5] = String.valueOf(d.getSexo());
			mat.add(fila);
		}
		int rows = mat.size();
		int columns = 6;
		iniciarTabla(rows, columns, mat.toArray(new String[rows][columns]),
				new String[] { "Nombre", "Ap. Paterno", "Ap. Materno", "CURP", "Fecha Nac.", "Sexo" });
	}

	public void setTablaAlumnos(Grupo grupo) {
		if (grupo.getAlumnos().size() > 0) {
			this.grupo = grupo;
			List<String[]> mat = new ArrayList<>();
			int columns = 7;
			for (Alumno a : grupo.getAlumnos()) {
				String[] fila = new String[columns];
				fila[0] = String.valueOf(a.getMatricula());
				fila[1] = a.getStatus();
				fila[2] = a.getNombreCompleto();
				fila[3] = a.getCurp();
				fila[4] = Utileria.formatearFecha(a.getFechaNac());
				fila[5] = String.valueOf(a.getSexo());
				fila[6] = a.getGrupo().getNombreGrupo();
				mat.add(fila);
			}
			int rows = mat.size();
			iniciarTabla(rows, columns, mat.toArray(new String[rows][columns]),
					new String[] { "Matricula", "Status", "Nombre", "CURP", "Fecha Nac.", "Sexo", "Grupo" });
		} else {

			Utileria.mensaje("No hay alumnos");
		}

	}

	public void setTablaCalificaciones(Grupo grupo) {
		this.grupo = grupo;
		List<Object[]> mat = new ArrayList<>();
		int columns = 7 + grupo.getParciales().getCantidad();
		for (Alumno a : grupo.getAlumnos()) {
			Object[] fila = new Object[columns];
			fila[0] = String.valueOf(a.getMatricula());
			fila[1] = a.getStatus();
			fila[2] = a.getNombreCompleto();
			fila[3] = a.getCurp();
			fila[4] = Utileria.formatearFecha(a.getFechaNac());
			fila[5] = String.valueOf(a.getSexo());
			fila[6] = a.getGrupo().getNombreGrupo();
			for (int i = 7; i < columns; i++) {
				Parcial p = grupo.getParciales().getParcial(i - 6);
				if (p.existeCalf(a))
					fila[i] = p.getCalf(a);
				else
					fila[i] = "";
			}
			mat.add(fila);
		}
		List<String> columnas = new ArrayList<>();
		columnas.addAll(
				Arrays.asList(new String[] { "Matricula", "Status", "Nombre", "CURP", "Fecha Nac.", "Sexo", "Grupo" }));
		for (int i = 7; i < columns; i++) {
			columnas.add("Parcial " + (i - 6));
		}
		int rows = mat.size();
		iniciarTabla(rows, columns, mat.toArray(new Object[rows][columns]), columnas.toArray());
	}

	public void setTablaAdeudos(Grupo grupo) {
		this.grupo = grupo;
		List<Object[]> mat = new ArrayList<>();
		int columns = 8;
		for (Alumno a : grupo.getAlumnosDeAlta()) {
			Object[] fila = new Object[columns];
			fila[0] = String.valueOf(a.getMatricula());
			fila[1] = a.getStatus();
			fila[2] = a.getNombreCompleto();
			fila[3] = a.getCurp();
			fila[4] = Utileria.formatearFecha(a.getFechaNac());
			fila[5] = String.valueOf(a.getSexo());
			fila[6] = a.getGrupo().getNombreGrupo();
			fila[7] = false;
			mat.add(fila);
		}
		List<String> lista = Arrays.asList(
				new String[] { "Matricula", "Status", "Nombre", "CURP", "Fecha Nac.", "Sexo", "Grupo", "Seleccion" });
		int rows = mat.size();
		iniciarTabla(rows, columns, mat.toArray(new Object[rows][columns]), lista.toArray());
	}

	public void setTablaAdeudo(Adeudo adeudo) {
		List<Object[]> mat = new ArrayList<>();
		int columns = 8;
		for (Entry<Alumno, Boolean> a : adeudo.getAlumnos().entrySet()) {
			Object[] fila = new Object[columns];
			fila[0] = String.valueOf(a.getKey().getMatricula());
			fila[1] = a.getKey().getStatus();
			fila[2] = a.getKey().getNombreCompleto();
			fila[3] = a.getKey().getCurp();
			fila[4] = Utileria.formatearFecha(a.getKey().getFechaNac());
			fila[5] = String.valueOf(a.getKey().getSexo());
			fila[6] = a.getKey().getGrupo().getNombreGrupo();
			fila[7] = a.getValue();
			mat.add(fila);
		}
		List<String> lista = Arrays.asList(
				new String[] { "Matricula", "Status", "Nombre", "CURP", "Fecha Nac.", "Sexo", "Grupo", "Pagado" });
		int rows = mat.size();
		iniciarTabla(rows, columns, mat.toArray(new Object[rows][columns]), lista.toArray());
	}

	public Adeudo adeudoSeleccionado() {
		return adeudos.getAdeudos().get(getFilaSeleccionada());
	}

	public void setTablaAdeudos(Adeudos adeudos) {
		this.adeudos = adeudos;
		List<Object[]> mat = new ArrayList<>();
		int columns = 6;
		for (Adeudo d : adeudos.getAdeudos()) {
			Object[] fila = new Object[columns];
			fila[0] = d.getNombre();
			fila[1] = d.getDescripcion();
			fila[2] = d.getFecha();
			fila[3] = d.getCosto();
			fila[4] = d.getPagados();
			fila[5] = d.getNoPagados();
			mat.add(fila);
		}
		List<String> lista = Arrays
				.asList(new String[] { "Nombre", "Descripcion", "Fecha", "Costo", "Pagados", "No pagados" });
		int rows = mat.size();
		iniciarTabla(rows, columns, mat.toArray(new Object[rows][columns]), lista.toArray());
	}

	public void iniciarTabla(int rows, int cols, Object[][] data, Object[] cabecera) {
		modelo = new DefaultTableModel(rows, cols) {
			public Class getColumnClass(int column) {
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};

		modelo.setDataVector(data, cabecera);
		table.setModel(modelo);
		sorter = new TableRowSorter<TableModel>(modelo);
		table.setRowSorter(sorter);
	}

	public int getFilaSeleccionada() {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	public boolean hayFilaSeleccionada() {
		return table.getSelectedRow() > -1;
	}

	public Docente getDocenteSeleccionado() {
		return docentes.getDocentes().get(getFilaSeleccionada());
	}

	public Alumno getAlumnoSeleccionado() {
		return grupo.getAlumnos().get(getFilaSeleccionada());
	}

	private void filtro() {
		String text = textField.getText();
		if (text.length() == 0) {
			sorter.setRowFilter(null);
		} else {
			try {
				sorter.setRowFilter(RowFilter.regexFilter(text));
			} catch (PatternSyntaxException pse) {
				System.out.println("Bad regex pattern");
			}
		}
	}

	public boolean validarCalificaciones() {
		listaCalificaciones = new ArrayList<>();
		for (int i = 0; i < modelo.getRowCount(); i++) {
			ArrayList<Double> fila = new ArrayList<>();
			for (int j = 0; j < grupo.getParciales().getCantidad(); j++) {
				if (table.getValueAt(i, 7 + j).toString().isEmpty()) {
					fila.add(null);
				} else {
					if (!Utileria.esDouble(table.getValueAt(i, 7 + j)) && Utileria.getDecimal() < 0) {
						return false;
					} else {
						fila.add(Utileria.getDecimal());
					}
				}
			}
			listaCalificaciones.add(fila.toArray(new Double[fila.size()]));
		}
		return true;
	}

	public void registrarCalificaciones() {
		Iterator<Double[]> it = listaCalificaciones.iterator();
		List<Alumno> alumnos = grupo.getAlumnosDeAlta();
		for (int i = 0; i < alumnos.size(); i++) {
			Double[] calfs = it.next();
			for (int j = 0; j < calfs.length; j++) {
				if (calfs[j] != null) {
					Parcial p = grupo.getParciales().getEvaluaciones().get(j);
					System.out.println(calfs[j]);
					if (p.existeCalf(alumnos.get(i))) {
						p.actualizarCalificacion(alumnos.get(i), calfs[j]);
					} else {
						p.setCalificacion(alumnos.get(i), calfs[j]);
					}
				}
			}
		}
	}

	public List<Alumno> getAlumnosSeleccionados() {
		List<Alumno> alumnos = new ArrayList<>();
		for (int i = 0; i < table.getRowCount(); i++) {
			if ((boolean) table.getValueAt(i, 8)) {
				alumnos.add(grupo.getAlumnos().get(i));
			}
		}
		return alumnos;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JComboBox<Grupo> getComboGrupo() {
		return comboGrupo;
	}
}
