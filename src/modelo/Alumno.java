package modelo;

import java.time.LocalDate;

public class Alumno extends Persona {
	public static final String ALTA = "alta";
	public static final String BAJA = "baja";
	private int matricula;
	private Grupo grupo;
	private String status;

	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alumno(String nombre, String paterno, String materno, LocalDate fechaNac, char sexo) {
		super(nombre, paterno, materno, fechaNac, sexo);

	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Alumno [matricula=" + matricula + ", grupo=" + grupo + "]";
	}

}