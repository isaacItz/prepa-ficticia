package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Adeudo implements Serializable {

	private String nombre;
	private String descripcion;
	private LocalDate fecha;
	private Double costo;
	private HashMap<Alumno, Boolean> alumnos;
	private Grupo grupo;
	private boolean todosLosGrupos;

	public Adeudo() {
		this.alumnos = new HashMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public HashMap<Alumno, Boolean> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		for (Alumno a : alumnos) {
			this.alumnos.put(a, false);
		}
	}

	public boolean getAlumno(Alumno a) {
		return alumnos.get(a);
	}

	public int getPagados() {
		int counter = 0;
		for (Entry<Alumno, Boolean> b : alumnos.entrySet()) {
			if (b.getValue()) {
				counter++;
			}
		}
		return counter;
	}

	public int getNoPagados() {
		int counter = 0;
		for (Entry<Alumno, Boolean> b : alumnos.entrySet()) {
			if (!b.getValue()) {
				counter++;
			}
		}
		return counter;
	}

	public void setAlumno(Alumno a, boolean val) {
		alumnos.replace(a, val);
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		for (Alumno a : grupo.getAlumnos()) {
			this.alumnos.put(a, false);
		}
		this.grupo = grupo;
	}

	public boolean isTodosLosGrupos() {
		return todosLosGrupos;
	}

	public void setTodosLosGrupos(boolean todosLosGrupos, Grupos grupos) {

		for (Alumno a : grupos.getAlumnos()) {
			this.alumnos.put(a, false);
		}
		this.todosLosGrupos = todosLosGrupos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Adeudo [nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha + ", costo=" + costo
				+ "]";
	}

}
