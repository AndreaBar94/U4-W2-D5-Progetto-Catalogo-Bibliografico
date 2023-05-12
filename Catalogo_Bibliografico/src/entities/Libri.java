package entities;


public class Libri extends Editoria{
	
	private String autore;
	private String genere;
	
	public Libri(String ISBN, String titolo, long annoPub, long pagine, String autore, String genere) {
		
		super(ISBN, titolo, annoPub, pagine);
		this.autore = autore;
		this.genere = genere;
		
	}
	
	public String getAutore() {
		return autore;
	}
	
	public void setAutore(String autore) {
		this.autore = autore;
	}
	
	public String getGenere() {
		return genere;
	}
	
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	@Override
	public String toString() {
	    return super.toString() + ", Autore: " + autore + ", Genere: " + genere;
	}
	
	public static String toStringFile(Libri libro) {
		return Libri.class.getSimpleName()
				+ "@" + libro.titolo
				+ "@" + libro.annoPub
				+ "@" + libro.pagine
				+ "@" + libro.autore
				+ "@" + libro.genere;
	}

	public static Libri fromStringFile(String stringFile) {
		String[] split = stringFile.split("@");
		
		return new Libri(split[1], split[2], Integer.valueOf(split[3]), Integer.valueOf(split[4]), split[5], split[6]);
	}

}
