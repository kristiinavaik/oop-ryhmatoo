import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fraasid extends Andmed {
	
	public Fraasid(String failiNimi) {
		super();
		File fail = new File(failiNimi);
		Scanner sc;	//loen faili sisse ja salvestan fraasid järjendisse
		try {
			sc = new Scanner(fail);
			while (sc.hasNextLine()) {
				String rida = sc.nextLine();
				this.andmed.add(new Fraas(rida));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Viga, faili \"" + failiNimi + "\" ei leitud!");
		}
	}

	public Fraas getFraas(int indeks) {
		return (Fraas) getAnne(indeks);
	}
	
}
