package modelo;

import java.util.HashMap;

public class Parcial {
	private int parcial;
	private HashMap<Alumno, Double> alumnos;

	public Parcial(int parcial) {
		this.parcial = parcial;
		this.alumnos = new HashMap<>();
	}

	public void setCalificacion(Alumno a, double cal) {
		this.alumnos.put(a, cal);
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
