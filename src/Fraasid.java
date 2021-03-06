import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fraasid extends Andmed {

	public Fraasid(String failiNimi) {
		super();
		File fail = new File(failiNimi);
		String rida;
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(fail)); //Kui fail on olemas, loen puhvrisse ja salvestan listi.
			while ((rida = br.readLine()) != null){
				this.andmed.add(new Fraas(rida));
			}
			br.close();
		} catch (FileNotFoundException e){
			System.err.println("Viga, faili \"" + failiNimi + "\" ei leitud!");
		} catch (IOException ioe) {
			System.err.println("Lugemisviga failist \"" + failiNimi + "\"!");
		}
	}

	public Fraas getFraas() {
		return (Fraas) getYlesanne();
	}

}
