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
		this.docente = docente;
		this.alumnos = alumnos;
		this.semestre = semestre;
		this.letra = letra;
	}

	public boolean existeAlumno(Alumno a) {
		return alumnos.contains(a);
	}

	public boolean dadoDeBaja(Alumno a) {
		return a.getStatus().equals("Baja");
	}

	public void eliminarAlumno(Alumno a) {
		alumnos.remove(a);
		parciales.eliminarAlumno(a);
	}

	public int getAlumno(int matricula) {
		for (int i = 0; i < alumnos.size(); i++) {
			if (alumnos.get(i).getMatricula() == matricula) {
				return i;
			}
		}
		return -1;
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

	public List<Alumno> getAlumnosDeAlta() {
		List<Alumno> lista = new ArrayList<>();
		for (Alumno a : alumnos) {
			if (a.getStatus().equals("Alta")) {
				lista.add(a);
			}
		}
		return lista;
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
		return getNombreGrupo().concat(" Docente: ").concat(getDocente().getNombreCompleto())
				.concat(" Alumnos: ".concat(String.valueOf(alumnos.size())));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alumnos == null) ? 0 : alumnos.hashCode());
		result = prime * result + ((docente == null) ? 0 : docente.hashCode());
		result = prime * result + letra;
		result = prime * result + ((parciales == null) ? 0 : parciales.hashCode());
		result = prime * result + semestre;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (alumnos == null) {
			if (other.alumnos != null)
				return false;
		} else if (!alumnos.equals(other.alumnos))
			return false;
		if (docente == null) {
			if (other.docente != null)
				return false;
		} else if (!docente.equals(other.docente))
			return false;
		if (letra != other.letra)
			return false;
		if (parciales == null) {
			if (other.parciales != null)
				return false;
		} else if (!parciales.equals(other.parciales))
			return false;
		if (semestre != other.semestre)
			return false;
		return true;
	}

}
