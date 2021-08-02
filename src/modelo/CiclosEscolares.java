package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CiclosEscolares implements Serializable {

	private CicloEscolar cicloActual;
	private List<CicloEscolar> ciclos;

	public CiclosEscolares() {
		this.ciclos = new ArrayList<>();
	}

	public void setCicloActual(CicloEscolar actual) {
		this.cicloActual = actual;
	}

	public void agregarCiclo(CicloEscolar ciclo) {
		ciclos.add(ciclo);
	}

	public CicloEscolar getCicloActual() {
		return this.cicloActual;
	}
}
