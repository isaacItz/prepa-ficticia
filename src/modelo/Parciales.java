package modelo;

import java.util.ArrayList;
import java.util.List;

public class Parciales {
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