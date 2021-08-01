package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grupo implements Serializable {
	private Docente docente;
	private List<Alumno> alumnos;
	private Parciales parciales;
	private int semestre;
	private char letra;

	public Grupo() {
		super();
		this.alumnos = new ArrayList<Alumno>();
	}

	public Grupo(Docente docente, List<Alumno> alumnos, int semestre, char letra) {
		super();
		this.parciales = new Parciales();
		this.docente = docente;
		this.alumnos = alumnos;
		this.semestre = semestre;
		this.letra = letra;
	}

	public Parciales getParciales() {
		return parciales;
	}

	public void setParciales(Parciales parciales) {
		this.parciales = parciales;
	}

	public void agregarAlumno(Alumno a) {
		this.alumnos.add(a);
	}

	public void darBajaAlumno(Alumno a) {
		a.setStatus(Alumno.BAJA);
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public String getNombreGrupo() {
		return String.valueOf(semestre).concat(String.valueOf(letra));
	}

	@Override
	public String toString() {
		return getNombreGrupo().concat(" Docente: ").concat(getDocente().getNombre())
				.concat(" Alumnos: ".concat(String.valueOf(alumnos.size())));
	}
}
