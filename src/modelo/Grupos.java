package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grupos implements Serializable{
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

	public boolean existeGrupo(int num, char letra) {
		for (Grupo g : grupos) {
			if (g.getLetra() == letra && g.getSemestre() == num) {
				return true;
			}
		}
		return false;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

}