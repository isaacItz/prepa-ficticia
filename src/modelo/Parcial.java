package modelo;

import java.io.Serializable;
import java.util.HashMap;

public class Parcial implements Serializable {
	private int parcial;
	private HashMap<Alumno, Double> alumnos;

	public Parcial(int parcial) {
		this.parcial = parcial;
		this.alumnos = new HashMap<>();
	}

	public void setCalificacion(Alumno a, double cal) {
		this.alumnos.put(a, cal);
	}

	public void actualizarCalificacion(Alumno a, double cal) {
		this.alumnos.replace(a, cal);
	}

	public double getCalf(Alumno a) {
		return alumnos.get(a);
	}

	public boolean existeCalf(Alumno a) {
		return alumnos.containsKey(a);
	}

	public void elimiarAlumno(Alumno a) {
		alumnos.remove(a);
	}

	public int getParcial() {
		return parcial;
	}

	public void setParcial(int parcial) {
		this.parcial = parcial;
	}

	public HashMap<Alumno, Double> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(HashMap<Alumno, Double> alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public String toString() {
		return "Parcial [parcial=" + parcial + ", alumnos=" + alumnos + "]";
	}

}
