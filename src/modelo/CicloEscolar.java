package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class CicloEscolar implements Serializable {

	private LocalDate inicio;
	private LocalDate fin;
	private Grupos grupos;

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public Grupos getGrupos() {
		return grupos;
	}

	public void setGrupos(Grupos grupos) {
		this.grupos = grupos;
	}

}
