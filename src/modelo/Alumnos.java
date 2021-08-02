package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Alumnos implements Serializable {

	private List<Alumno> alumnos;

	public Alumnos() {
		this.alumnos = new ArrayList<>();
	}

	public void agregarAlumno(Alumno a) {
		alumnos.add(a);
	}

	public int getMatricula() {
		return alumnos.size() + 1;
	}
}
