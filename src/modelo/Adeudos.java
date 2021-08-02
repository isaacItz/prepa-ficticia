package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adeudos implements Serializable {
	private List<Adeudo> adeudos;

	public Adeudos() {
		this.adeudos = new ArrayList<>();
	}

	public void agregarAdeudo(Adeudo adeudo) {
		adeudos.add(adeudo);
	}

	public List<Adeudo> getAdeudos() {
		return adeudos;
	}

	public void setAdeudos(List<Adeudo> adeudos) {
		this.adeudos = adeudos;
	}

}
