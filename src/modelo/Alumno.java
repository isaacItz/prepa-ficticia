package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Alumno extends Persona implements Serializable {
	public static final String ALTA = "Alta";
	public static final String BAJA = "Baja";
	private int matricula;
	private Grupo grupo;
	private String status;

	public Alumno() {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + matricula;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (matricula != other.matricula)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
