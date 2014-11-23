import java.io.*;

public class Logi {

	public static final String LOGIFAIL = "logi.txt"; 	

	 /*
	  * taastab logifaili algse kuju
	  */
	public void resetLog() {
		FileWriter out;
		try {
			out = new FileWriter(LOGIFAIL);
			out.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	/*
	 * Sõltuvalt harjutusest saavad järgnevad kaks meetodit
	 * sobivad argumendid, et teada, mida kirjutada õigeks
	 * ja mida valeks vastuseks.
	 */
	public void kirjutaKirjavahemarkideManguLogi(Lause lause) {
		try {
			FileWriter out = new FileWriter(LOGIFAIL, true);
			out.write("Teie vastus: " + Kasutajaliides.textField.getText() + "\n");
			out.write("Õige vastus: " + lause.getFraas1() + "\n\n");
			out.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public void kirjutaOigekirjaManguLogi(String valeFraas, String oigeFraas){
		try {
			FileWriter out = new FileWriter(LOGIFAIL, true);
			out.write("Teie vastus: " + valeFraas + "\n");
			out.write("Õige vastus: " + oigeFraas + "\n\n");
			out.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	/*
	 * loeb failist andmed ühte stringi
	 */
	public String loeLogi(){
		//StringBuider on vajalik järjestikuseks lisamiseks .append käsuga.
		StringBuilder vigasedLaused = new StringBuilder();
		String rida;
		
	    try {
	    	BufferedReader fr = new BufferedReader(new FileReader(LOGIFAIL));
	    	while ((rida = fr.readLine()) != null) {
				vigasedLaused.append(rida);
				vigasedLaused.append(System.getProperty("line.separator"));
			}
	    	fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return vigasedLaused.toString();
	}
}
