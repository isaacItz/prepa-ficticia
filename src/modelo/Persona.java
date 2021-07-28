package modelo;

import java.time.LocalDate;

public class Persona {

	private String nombre;
	private String paterno;
	private String materno;
	private LocalDate fechaNac;
	private char sexo;

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
	public String toString() {
		return "Persona [nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + ", fechaNac=" + fechaNac
				+ ", sexo=" + sexo + "]";
	}

}
