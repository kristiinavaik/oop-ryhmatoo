
import javax.swing.JOptionPane;

public class Harjutused {

	public static final String PEALKIRI = "Õpime eesti keelt";
	private static int oiged = 0;
	private static int valed = 0;
	
	public static void main(String[] args) {
		String fraasifail = "grammatikalaused.txt";
		String komafail = "komalaused.txt";
		
		// Määrame harjutuse liigi ja söödame järjendisse vastava faili
		int valik = harjutuseValikuDialoog(); 
		
		if (valik == 0) { // Õigekirjaharjutus
			oigekirjaHarjutus(fraasifail);
		} else if (valik == 1) { // Kirjavahemärkide harjutus
			kirjavahemarkideHarjutus(komafail);
		}
	}

	public static void oigekirjaHarjutus(String fraasifail) {
		Fraasid fraasid = new Fraasid(fraasifail);
		int eelmineIndeks = -1;

		Fraas fraas;
		String tulemus, vastus;
		int tulemuseTyyp, suvalineIndeks;
		while (true) {
			suvalineIndeks = fraasid.suvalineIndeks(eelmineIndeks);
			fraas = fraasid.getFraas(suvalineIndeks);
			eelmineIndeks = suvalineIndeks;
			
			vastus = kysiOigekirjaVastus(fraas);
			if (vastus == null) {
				tulemusteDialoog();
				break;
			} else if (fraas.kontrolliVastus(vastus)) {
				tulemus = "Tubli, \"" + fraas.korrektneFraas() + "\" on õige vastus!";
				tulemuseTyyp = JOptionPane.INFORMATION_MESSAGE;
				oiged++;
			} else {
				tulemus = "Vastasite valesti, õige vastus on \"" + fraas.korrektneFraas() + "\"!";
				tulemuseTyyp = JOptionPane.WARNING_MESSAGE;
				valed++;
			}
			dialoog(tulemus, tulemuseTyyp);
		}
	}
	
	public static void kirjavahemarkideHarjutus(String komafail) {
		Laused laused = new Laused(komafail);
		int eelmineIndeks = -1;
		
		Lause lause;
		String tulemus, vastus;
		int tulemuseTyyp, suvalineIndeks;
		while (true) {
			suvalineIndeks = laused.suvalineIndeks(eelmineIndeks);
			lause = laused.getLause(suvalineIndeks);
			eelmineIndeks = suvalineIndeks;
			
			vastus = kysiKomaVastus(lause, "Sisesta kirjavahemärkidega!\n");
			if (vastus == null) {
				tulemusteDialoog();
				break;
			} else if (lause.kontrolliVastus(vastus)) {
				tulemus = "Tubli, \"" + lause.getFraas1() + "\" on õige!";
				tulemuseTyyp = JOptionPane.INFORMATION_MESSAGE;
				oiged++;
			} else {
				 tulemus = "Vastasite valesti. \nTeie vastus: \"" + vastus + "\"\nÕige vastus: \"" + lause.getFraas1() + "\"";
				 tulemuseTyyp = JOptionPane.WARNING_MESSAGE;
				 valed++;
			}
			dialoog(tulemus, tulemuseTyyp);
		}
	}
	
	public static String kysiOigekirjaVastus(Fraas fraas) {
		int vastus = sisendFraasiDialoog(fraas.toString());
		if (vastus == -1) {
			return null; // kasutaja vajutas cancel
		} else if (vastus == 0) {
			return fraas.getFraas1();
		} else {
			return fraas.getFraas2();
		}
	}
	
	public static String kysiKomaVastus(Lause lause, String sonum) {
		String vastus = sisendDialoog(sonum + lause.ilmaPunktuatsioonita());
		if (vastus == null) {
			return null; // kasutaja vajutas cancel
		} else {
			return vastus;
		}
	}
	
	public static int harjutuseValikuDialoog(){
		String[] options = {"Õigekirja", "Kirjavahemärke"};
		int valik = JOptionPane.showOptionDialog(null,
				"Mida tahad harjutada?",
				PEALKIRI,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		
		return valik;
	}
	
	public static int sisendFraasiDialoog(String sonum) {
		String[] options = {"   A   ", "   B   "};
		int sisestus = JOptionPane.showOptionDialog(null,
				sonum,
				PEALKIRI,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);
		return sisestus;
	}
	
	public static String sisendDialoog(String sonum) {
		String sisestus = JOptionPane.showInputDialog(null, sonum, PEALKIRI, JOptionPane.QUESTION_MESSAGE);
		return sisestus;
	}
	
	public static void dialoog(String sonum, int tyyp) {
		JOptionPane.showMessageDialog(null, sonum, PEALKIRI, tyyp);
	}
	
	public static void tulemusteDialoog(){
		String tulemus = "Sinu tulemus: \nÕigesti: " + oiged + "\nValesti: " + valed;
		dialoog(tulemus, JOptionPane.PLAIN_MESSAGE);
	}

}
