package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Docentes implements Serializable {

	private List<Docente> docentes;

	public Docentes() {
		this.docentes = new ArrayList<>();
	}

	public void agregarDocente(Docente d) {
		docentes.add(d);
	}

	public boolean existeDocente(Docente d) {
		return docentes.contains(d);
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public boolean hayDocentes() {
		return docentes.size() > 0;
	}
}
