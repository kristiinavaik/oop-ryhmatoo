import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Mangud {

	// Harjutatavad lausete ja fraaside failide nimed on kirjas klassis Konstandid.
	private static Laused laused = new Laused(Konstandid.KOMADEFAIL);
	private static Fraasid fraasid = new Fraasid(Konstandid.FRAASIDEFAIL);

	// Logitegemine on klassis Logi. Teen sellest uue isendi.
	private static Logi log = new Logi();

	
	// Õigete-valede vastuste loendur.
	static int oiged = 0;
	static int valed = 0;

	static void kontrolliKirjavahemarkideMangu(Lause lause){
		// Kuna mängu kontroll võib toimuda kahes kohas,
		// on mõttekas see tuua eraldi meetodisse. 
		if (Kasutajaliides.textField.getText().length()  <= lause.ilmaPunktuatsioonita().length()){
			// kontrollin, kas kasutaja on üldse midagi teinud.
			// Kui ei ole või vastus on antud lausest lühem, ei arvesta vastust.			
			Kasutajaliides.tulemusText.setFill(Color.RED);
			Kasutajaliides.tulemusText.setText("Sisestasite antust lühema teksti või ei lisanud midagi!");
		}
		else if (lause.kontrolliVastus(Kasutajaliides.textField.getText())) {
			oiged++;
			mangiKirjavahemarkideMangu("Tubli, \"" + lause.getFraas1() + "\" on õige vastus!", true);
		} else {
			// vale vastuse puhul kirjutan antud vastuse ja õige vastuse logisse.
			log.kirjutaKirjavahemarkideManguLogi(lause);
			valed++;
			mangiKirjavahemarkideMangu("Vastasite valesti, õige vastus on \"" + lause.getFraas1() + "\"!", false);
		}
	}
	
	static void mangiKirjavahemarkideMangu(String eelmineTulemus, boolean oige) {
		// Kui esimeseks argumendiks on null, tähendab see, et harjutus on
		// käivitatud esimest korda või uuesti, nii et tuleb nullida loendur
		// ja mitte saata midagi tulemusText tekstiks. Pärast esimest korda
		// saadab käivitab see sama klass iseenda uute argumentidega, millest
		// esimeses on eelmise vastuse kohta käiv tekst. Selle värv sõltub
		// teisest argumendist: true õige vastuse ja 
		// false vale vastuse korral
		if (eelmineTulemus == null) {
			oiged = 0;
			valed = 0;
			log.resetLog(); // Kustutan logifaili sisu.
		} else {
			Kasutajaliides.tulemusText.setText(eelmineTulemus);
			if (oige) {
				Kasutajaliides.tulemusText.setFill(Color.GREEN);
			} else {
				Kasutajaliides.tulemusText.setFill(Color.RED);
			}
		}

		Lause lause = laused.getLause(); // juhuslik lause

		// Kirjutab üle kasutajaliidese teksti ja tekstivälja.
		Kasutajaliides.valik1Text.setText(lause.ilmaPunktuatsioonita());
		Kasutajaliides.textField.setText(lause.ilmaPunktuatsioonita());

		// nupule vajutades käivitub sama meetod uute argumentidega.
		Kasutajaliides.valik1Nupp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				kontrolliKirjavahemarkideMangu(lause);
			}
		});

		// Sama, mis nupule vajutades, toimub ka vajutades tekstisisetusväljas Enter klahvi.
		Kasutajaliides.textField.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				kontrolliKirjavahemarkideMangu(lause);
			}
		});
	}

	/*
	 * Kirjavahemärkide mängu loogika
	 */
	static void mangiOigekirjaMangu(String eelmineTulemus, boolean oige) {
		if (eelmineTulemus == null) {
			oiged = 0;
			valed = 0;
			log.resetLog();
		} else {
			Kasutajaliides.tulemusText.setText(eelmineTulemus);
			if (oige) {
				Kasutajaliides.tulemusText.setFill(Color.GREEN);
			} else {
				Kasutajaliides.tulemusText.setFill(Color.RED);
			}
		}

		// Võtame sisse fraasiobjekti ja loeme sellest eraldi fraasid. Kuvame.
		Fraas fraas = fraasid.getFraas();

		String fraas1 = fraas.getFraas1();
		String fraas2 = fraas.getFraas2();

		Kasutajaliides.valik1Text.setText("a) \"" + fraas1 + "\"");
		Kasutajaliides.valik2Text.setText("b) \"" + fraas2 + "\"");

		// Mõlema nupu vajutamise puhul toimub vastuse kontrollimine ja uue info väljastamine vastavalt vastusele.
		Kasutajaliides.valik1Nupp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (fraas.kontrolliVastus(fraas1)) {
					oiged++;
					mangiOigekirjaMangu("Tubli, \"" + fraas1 + "\" on õige vastus!", true);
				} else {
					log.kirjutaOigekirjaManguLogi(fraas1, fraas2);
					valed++;
					mangiOigekirjaMangu("Vastasite valesti, õige vastus on \"" + fraas2 + "\"!", false);
				}
			}
		});

		Kasutajaliides.valik2Nupp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (fraas.kontrolliVastus(fraas2)) {
					oiged++;
					mangiOigekirjaMangu("Tubli, \"" + fraas2 + "\" on õige vastus!", true);
				} else {
					log.kirjutaOigekirjaManguLogi(fraas2, fraas1);
					valed++;
					mangiOigekirjaMangu("Vastasite valesti, õige vastus on \"" + fraas1 + "\"!", false);
				}
			}
		});
	}

}
