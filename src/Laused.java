import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Laused extends Andmed {
	
	public Laused(String failiNimi) {
		super();
		File fail = new File(failiNimi);
		Scanner sc;	//loen faili sisse ja salvestan fraasid järjendisse
		try {
			sc = new Scanner(fail);
			while (sc.hasNextLine()) {
				String rida = sc.nextLine();
				this.andmed.add(new Lause(rida));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Viga, faili \"" + failiNimi + "\" ei leitud!");
		}
	}
	
	public Lause getLause(int indeks) {
		return (Lause) getAnne(indeks);
	}
}
