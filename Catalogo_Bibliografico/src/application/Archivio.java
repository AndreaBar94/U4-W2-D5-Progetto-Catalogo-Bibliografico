package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.apache.commons.io.FileUtils;
import entities.Editoria;
import entities.Libri;
import entities.Riviste;

public class Archivio {

	public static Logger logger = LoggerFactory.getLogger(Archivio.class);
	
	private static File file = new File("info.txt");
	
	public static void main(String[] args) {
		
		//CREAZIONE LIBRI
		Libri libro1 = new Libri("9788865181901l", "Harry Potter 1", 1997, 304, "J.K.Rowling", "Fantasy");  
		Libri libro2 = new Libri("9788865181902l", "Harry Potter 2", 1998, 224, "J.K.Rowling", "Fantasy");  
		Libri libro3 = new Libri("9788865181903l", "Il Signore degli anelli", 1955, 500, "J. R. R. Tolkien", "Fantasy");  
		Libri libro4 = new Libri("9788865181904l", "Il Signore degli anelli", 2000, 600, "J. R. R. Tolkien", "Fantasy");  
		
		//CREAZIONE RIVISTE
		Riviste rivista1 = new Riviste("9788865181905l", "Focus", 1992, 130, "Mensile");
		Riviste rivista2 = new Riviste("9788865181906l", "Vanity Fair", 1913, 120, "Settimanale");
		Riviste rivista3 = new Riviste("9788865181907l", "L'Indice dei libri del mese", 1984, 160, "Semestrale");
		Riviste rivista4 = new Riviste("9788865181908l", "Foreign Policy", 1970, 120, "Semestrale");
		
		try {
			addElement(libro1);
			addElement(libro2);
			addElement(libro3);
			addElement(libro4);
			addElement(rivista1);
			addElement(rivista2);
			addElement(rivista3);
			addElement(rivista4);
			//deleteByISBN(9788865181901l);
			saveOnDisk();
			readByDisk();
			
			} catch (IOException e) {
			
				logger.error("Errore!" + e);
			}
		
		printArchivio();
		searchByISBN("9788865181901l");
		searchByYear(1913);
		searchByAut("J.K.Rowling");
		
	}
	
	//CREO UN ARCHIVIO GENERALE
	static Map<String, Editoria> archivio = new HashMap<>();

	public static void printArchivio() {
	    for (Map.Entry<String, Editoria> entry : archivio.entrySet()) {
	        logger.info("Archivio: {}", entry.getValue());
	    }
	}

	public static void addElement(Editoria elemento) throws IOException {
	    if (archivio.containsKey(elemento.getISBN())) {
	        throw new IOException("Elemento già presente nell'archivio");
	    }
	    archivio.put(elemento.getISBN(), elemento);
	}

	public static void deleteByISBN(String ISBN) throws IOException {
	    if (!archivio.containsKey(ISBN)) {
	        throw new IOException("Elemento non presente nell'archivio");
	    }
	    archivio.remove(ISBN);
	}

	public static Editoria searchByISBN(String ISBN) {
	    Editoria result = archivio.get(ISBN);
	    logger.info("Risultato ricerca per ISBN({}): {}", ISBN, result);
	    return result;
	}


	public static List<Editoria> searchByYear(long anno) {
	    List<Editoria> result = new ArrayList<>();
	    for (Map.Entry<String, Editoria> entry : archivio.entrySet()) {
	        if (entry.getValue().getAnnoPub() == anno) {
	            result.add(entry.getValue());
	        }
	    }
	    logger.info("Per questo anno in archivio abbiamo: {}", result);
	    return result;
	}

	public static List<Editoria> searchByAut(String autore) {
	    List<Editoria> result = new ArrayList<>();
	    for (Map.Entry<String, Editoria> entry : archivio.entrySet()) {
	        if (entry.getValue() instanceof Libri) {
	            Libri libro = (Libri) entry.getValue();
	            if (libro.getAutore().equals(autore)) {
	                result.add(libro);
	            }
	        }
	    }
	    logger.info("Risultato ricerca per {}: ", autore);
	    for(Editoria elemento : result) {
	        logger.info("{}", elemento);
	    }
	    return result;
	}

	
	//SCRITTURA SU FILE DI TESTO
	public static void saveOnDisk() throws IOException {
	    String str = "";
	    for (Editoria elemento : archivio.values()) {
	        if (str.length() != 0) {
	            str += "#";
	        }
	        if (elemento instanceof Libri) {
	            str += Libri.toStringFile((Libri) elemento);
	        } else if (elemento instanceof Riviste) {
	            str += Riviste.toStringFile((Riviste) elemento);
	        }
	    }
	    FileUtils.writeStringToFile(file, str, "UTF-8");
	    logger.info("Archivio salvato correttamente sul file " + file);
	}

	
	//LETTURA DA FILE DI TESTO
	public static void readByDisk() throws IOException {
	    archivio.clear();
	    String str = FileUtils.readFileToString(file, "UTF-8");
	    List<String> splitStr = Arrays.asList(str.split("#"));
	    for (String newStr : splitStr) {
	        Editoria element = null;
	        if (newStr.startsWith(Libri.class.getSimpleName())) {
	            element = Libri.fromStringFile(newStr);
	        } else if (newStr.startsWith(Riviste.class.getSimpleName())) {
	            element = Riviste.fromStringFile(newStr);
	        }
	        if (element != null) {
	            archivio.put(element.getISBN(), element);
	        }
	    }
	    logger.info("Archivio caricato correttamente dal file " + file);
	}


}
