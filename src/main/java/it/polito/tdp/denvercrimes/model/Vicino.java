package it.polito.tdp.denvercrimes.model;

public class Vicino implements Comparable<Vicino> {
	private int vicino;
	private Double distanza;
	public Vicino(int vicino, Double distanza) {
		super();
		this.vicino = vicino;
		this.distanza = distanza;
	}
	public int getVicino() {
		return vicino;
	}
	public void setVicino(int vicino) {
		this.vicino = vicino;
	}
	public Double getDistanza() {
		return distanza;
	}
	public void setDistanza(Double distanza) {
		this.distanza = distanza;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distanza == null) ? 0 : distanza.hashCode());
		result = prime * result + vicino;
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
		Vicino other = (Vicino) obj;
		if (distanza == null) {
			if (other.distanza != null)
				return false;
		} else if (!distanza.equals(other.distanza))
			return false;
		if (vicino != other.vicino)
			return false;
		return true;
	}
	@Override
	public int compareTo(Vicino o) {
		return this.distanza.compareTo(o.getDistanza());
	}
	@Override
	public String toString() {
		return  vicino + "  " + distanza ;
	}
	
	
	
	
}
