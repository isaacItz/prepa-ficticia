package modelo;

import java.util.ArrayList;
import java.util.List;

public class Grupos {
	private List<Grupo> grupos;

	public Grupos() {
		this.grupos = new ArrayList<>();
	}

	public void agregarGrupo(Grupo g) {
		grupos.add(g);
	}

	public Grupo getGrupo(int num, char letra) {
		for (Grupo g : grupos) {
			if (g.getLetra() == letra && g.getSemestre() == num) {
				return g;
			}
		}
		return null;
	}

}