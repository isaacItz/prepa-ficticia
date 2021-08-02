package modelo;

import java.time.LocalDate;

public class Adeudo {

	private String nombre;
	private String descripcion;
	private LocalDate fecha;
	private Double costo;

	public String getNombre() {
		return nombre;
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
