package modelo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Utileria {

	public boolean estaVacio(JTextField caja) {
		return caja.getText().isEmpty();
	}

	public boolean validarCaracter(JTextField caja, int numero) {
		return (caja.getText().length() == numero);
	}

	public void escribir(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean verificarRadioBottom(JRadioButton boton) {
		return boton.isSelected();
	}

	public static String leerString(String mensaje) {
		return JOptionPane.showInputDialog(mensaje);
	}

	public boolean isMujer(JTextField caja) {
		String nombre = caja.getText();
		nombre = nombre.toUpperCase();
		int letraF = nombre.length() - 1;
		char l = nombre.charAt(letraF);
		if (l == 'A') {
			return true;
		} else {
			return false;
		}
	}

	public static <T> int linealSearch(List<T> coleccion, T dato, Comparator<T> c) {
		int i = 0;
		while (i < coleccion.size() && c.compare(coleccion.get(i), dato) != 0)
			i++;

		return i == coleccion.size() ? -1 : i;
	}

	public boolean isHombre(JTextField caja) {
		String nombre = caja.getText();
		nombre = nombre.toUpperCase();
		int letraF = nombre.length() - 1;
		char l = nombre.charAt(letraF);
		if (l == 'O') {
			return true;
		} else {
			return false;
		}
	}

	public boolean validarCaja(JComboBox<String> combo) {
		if (combo.getSelectedIndex() != 0) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean validarCaja(JTextField caja) {
		return !caja.getText().isEmpty();
	}

	public static boolean horaValida(JTextField caja) {
		try {
			int hora = Integer.parseInt(caja.getText().split(":")[0]);
			int minutos = Integer.parseInt(caja.getText().split(":")[1]);
			return hora < 25 && minutos < 60;
		} catch (Exception e) {
			return false;
		}
	}

	public static LocalTime getHora(JTextField caja) {
		int hora = Integer.parseInt(caja.getText().split(":")[0]);
		int minutos = Integer.parseInt(caja.getText().split(":")[1]);
		return LocalTime.of(hora, minutos);
	}

	public static boolean validarCajaFecha(JDateChooser dateChooser) {
		return toLocalDate(dateChooser.getDate()).isBefore(LocalDate.now());
	}

	public static LocalDate toLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date toDate(LocalDate date) {
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static void mensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public static String formatearFecha(Date fecha) {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		return sd.format(fecha);
	}

	public static String formatearFecha(LocalDate fecha) {
		DateTimeFormatter sd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return sd.format(fecha);
	}
}
