import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Mangud {
	
	private static Laused laused = new Laused(Konstandid.KOMADEFAIL);
	private static Fraasid fraasid = new Fraasid(Konstandid.FRAASIDEFAIL);
	
	static int oiged = 0;
	static int valed = 0;
	
	static void mangiKirjavahemarkideMangu(String eelmineTulemus, boolean oige) {
		if (eelmineTulemus == null) {
			oiged = 0;
			valed = 0;
		} else {
			Kasutajaliides.tulemusText.setText(eelmineTulemus);
			if (oige) {
				Kasutajaliides.tulemusText.setFill(Color.GREEN);
			} else {
				Kasutajaliides.tulemusText.setFill(Color.RED);
			}
		}
		
		Lause lause = laused.getLause();
		
		Kasutajaliides.valik1Text.setText(lause.ilmaPunktuatsioonita());
		Kasutajaliides.textField.setText(lause.ilmaPunktuatsioonita());

		Kasutajaliides.valik1Nupp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (lause.kontrolliVastus(Kasutajaliides.textField.getText())) {
					oiged++;
					mangiKirjavahemarkideMangu("Tubli, \"" + lause.getFraas1() + "\" on õige vastus!", true);
				} else {
					valed++;
					mangiKirjavahemarkideMangu("Vastasite valesti, õige vastus on \"" + lause.getFraas1() + "\"!", false);
				}
			}
		});
		
		Kasutajaliides.textField.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (lause.kontrolliVastus(Kasutajaliides.textField.getText())) {
					oiged++;
					mangiKirjavahemarkideMangu("Tubli, \"" + lause.getFraas1() + "\" on õige vastus!", true);
				} else {
					valed++;
					mangiKirjavahemarkideMangu("Vastasite valesti, õige vastus on \"" + lause.getFraas1() + "\"!", false);
				}
			}
		});
	}
	
	static void mangiOigekirjaMangu(String eelmineTulemus, boolean oige) {
		if (eelmineTulemus == null) {
			oiged = 0;
			valed = 0;
		} else {
			Kasutajaliides.tulemusText.setText(eelmineTulemus);
			if (oige) {
				Kasutajaliides.tulemusText.setFill(Color.GREEN);
			} else {
				Kasutajaliides.tulemusText.setFill(Color.RED);
			}
		}
		
		Fraas fraas = fraasid.getFraas();

		String fraas1 = fraas.getFraas1();
		String fraas2 = fraas.getFraas2();
		
		Kasutajaliides.valik1Text.setText("a) \"" + fraas1 + "\"");
		Kasutajaliides.valik2Text.setText("b) \"" + fraas2 + "\"");

		Kasutajaliides.valik1Nupp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if (fraas.kontrolliVastus(fraas1)) {
					oiged++;
					mangiOigekirjaMangu("Tubli, \"" + fraas1 + "\" on õige vastus!", true);
				} else {
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
					valed++;
					mangiOigekirjaMangu("Vastasite valesti, õige vastus on \"" + fraas1 + "\"!", false);
				}
			}
		});
	}
	
}
