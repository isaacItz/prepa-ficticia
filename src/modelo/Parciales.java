package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Parciales implements Serializable {
	private List<Parcial> evaluaciones;

	public Parciales(int parciales) {
		this.evaluaciones = new ArrayList<>(parciales);
		for (int i = 0; i < parciales; i++) {
			Parcial p = new Parcial(i + 1);
			evaluaciones.add(p);
		}
	}

	public void registrarParcial(Parcial p) {
		this.evaluaciones.add(p);
	}

	public void eliminarAlumno(Alumno a) {
		for (Parcial parcial : evaluaciones) {
			parcial.elimiarAlumno(a);
		}
	}

	public Parcial getParcial(int parcial) {
		for (Parcial p : evaluaciones) {
			if (p.getParcial() == parcial)
				return p;
		}
		return null;
	}

	public int getCantidad() {
		return evaluaciones.size();
	}

	public List<Parcial> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<Parcial> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evaluaciones == null) ? 0 : evaluaciones.hashCode());
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
		Parciales other = (Parciales) obj;
		if (evaluaciones == null) {
			if (other.evaluaciones != null)
				return false;
		} else if (!evaluaciones.equals(other.evaluaciones))
			return false;
		return true;
	}
}