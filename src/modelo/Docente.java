package modelo;

import java.io.Serializable;

public class Docente extends Persona implements Serializable {

	@Override
	public String toString() {
		return "Docente [toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
