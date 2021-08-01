package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Parciales implements Serializable{
	private List<Parcial> evaluaciones;

	public Parciales() {
		this.evaluaciones = new ArrayList<>();
	}

	public void registrarParcial(Parcial p) {
		this.evaluaciones.add(p);
	}

	public Parcial getParcial(int parcial) {
		for (Parcial p : evaluaciones) {
			if (p.getParcial() == parcial)
				return p;
		}
		return null;
	}
}