package entities;


public abstract class Editoria {
	
	protected String ISBN;
	protected String titolo;
	protected long annoPub;
	protected long pagine;
	
	public Editoria(String ISBN, String titolo, long annoPub, long pagine) {
		
		this.ISBN = ISBN;
		this.titolo = titolo;
		this.annoPub = annoPub;
		this.pagine = pagine;
	}
	
	
		public String getISBN() {
			return ISBN;
		}
		
		public String setISBN(String ISBN) {
			return this.ISBN = ISBN;
		}
		
	
		public String getTitolo() {
			return titolo;
		}
		
		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}
		
		
		public long getAnnoPub() {
			return annoPub;
		}
		
		public void setAnnoPub(long annoPub) {
			this.annoPub = annoPub;
		}
		
		
		public long getPagine() {
			return pagine;
		}
		
		public void setPagine(long pagine) {
			this.pagine = pagine;
		}
		
		@Override
		public String toString() {
		    return "ISBN: " + ISBN + ", Titolo: " + titolo + ", Anno di pubblicazione: " + annoPub + ", Pagine: " + pagine;
		}

}
