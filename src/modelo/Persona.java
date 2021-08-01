package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Persona implements Serializable {

	private String nombre;
	private String paterno;
	private String materno;
	private LocalDate fechaNac;
	private char sexo;
	private String curp;

	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(String nombre, String paterno, String materno, LocalDate fechaNac, char sexo) {
		super();
		this.nombre = nombre;
		this.paterno = paterno;
		this.materno = materno;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombreCompleto() {
		return nombre.concat(" ").concat(paterno).concat(" ").concat(materno);
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curp == null) ? 0 : curp.hashCode());
		result = prime * result + ((fechaNac == null) ? 0 : fechaNac.hashCode());
		result = prime * result + ((materno == null) ? 0 : materno.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((paterno == null) ? 0 : paterno.hashCode());
		result = prime * result + sexo;
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
		Persona other = (Persona) obj;
		if (curp == null) {
			if (other.curp != null)
				return false;
		} else if (!curp.equals(other.curp))
			return false;
		if (fechaNac == null) {
			if (other.fechaNac != null)
				return false;
		} else if (!fechaNac.equals(other.fechaNac))
			return false;
		if (materno == null) {
			if (other.materno != null)
				return false;
		} else if (!materno.equals(other.materno))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (paterno == null) {
			if (other.paterno != null)
				return false;
		} else if (!paterno.equals(other.paterno))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + ", fechaNac=" + fechaNac
				+ ", sexo=" + sexo + "]";
	}

}
