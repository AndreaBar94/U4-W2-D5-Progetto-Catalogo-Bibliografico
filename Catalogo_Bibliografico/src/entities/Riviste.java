package entities;


public class Riviste extends Editoria{
	
	private String periodicità;
	
	
	public Riviste(String ISBN, String titolo, long annoPub, long pagine, String periodicità) {
		
		super(ISBN, titolo, annoPub, pagine);
		this.periodicità = periodicità;
	}

	public String getPeriodicità() {
		return periodicità;
	}
	
	public void setPeriodicità(String periodicità) {
		this.periodicità = periodicità;
	}
	
	
	@Override
	public String toString() {
	    return super.toString() + ", Periodicità " + periodicità ;
	}
	
	public static String toStringFile(Riviste rivista) {
		return Riviste.class.getSimpleName()  // Serve per identificare il tipo di elemento
				+ "@" + rivista.ISBN
				+ "@" + rivista.titolo
				+ "@" + rivista.annoPub
				+ "@" + rivista.pagine
				+ "@" + rivista.periodicità;
	}

	public static Riviste fromStringFile(String stringFile) {
		String[] split = stringFile.split("@");
		
		return new Riviste(split[1], split[2], Integer.valueOf(split[3]), Integer.valueOf(split[4]), split[5]);
	}
	
}
