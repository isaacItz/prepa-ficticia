package vista;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

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

import modelo.Docente;
import modelo.Docentes;
import modelo.Utileria;

public class TablaBusqueda extends JPanel {
	private JTextField textField;
	private JTable table;
	private JLabel etiquetaTexto;
	private DefaultTableModel modelo;
	private TableRowSorter<TableModel> sorter;

	/**
	 * Create the panel.
	 */
	public TablaBusqueda() {
		setBorder(new TitledBorder(null, "Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

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

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

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

	public void setTabla(Docentes docentes) {
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
		iniciarTabla(rows, columns);
		modelo.setDataVector(mat.toArray(new String[rows][columns]),
				new String[] { "Nombre", "Ap. Paterno", "Ap. Materno", "CURP", "Fecha Nac.", "Sexo" });
	}

	public void iniciarTabla(int rows, int cols) {
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

		table.setModel(modelo);
		sorter = new TableRowSorter<TableModel>(modelo);
		table.setRowSorter(sorter);
	}

	public void filtro() {
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

}
