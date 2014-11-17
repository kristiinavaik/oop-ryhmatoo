public class Fraas extends Ylesanne {

	private String fraas1;
	private boolean fraas1Korrektne;
	private String fraas2;
	private boolean fraas2Korrektne;

	/*
	 * @param	fraasideRida	String kujul "- aadressiteõigsus | + aadressideõigsus"
	*/
	public Fraas(String fraasideRida) {
		int poolitajaAsukoht = fraasideRida.indexOf("|");
		String fraasiString1 = fraasideRida.substring(0, poolitajaAsukoht-1);
		String fraasiString2 = fraasideRida.substring(poolitajaAsukoht+1);
		fraasiString1 = fraasiString1.trim();
		fraasiString2 = fraasiString2.trim();

		this.fraas1 = fraasiString1.substring(2);
		this.fraas1Korrektne = fraasiString1.charAt(0) == '+';
		this.fraas2 = fraasiString2.substring(2);
		this.fraas2Korrektne = fraasiString2.charAt(0) == '+';
	}

	public String getFraas1() {
		return fraas1;
	}

	public String getFraas2() {
		return fraas2;
	}

	public boolean kontrolliVastus(String vastus) {
		if (fraas1.equals(vastus) && fraas1Korrektne) {
			return true;
		} else if (fraas2.equals(vastus) && fraas2Korrektne) {
			return true;
		} else {
			return false;
		}
	}

	public String korrektneFraas() {
		return fraas1Korrektne ? fraas1 : fraas2;
	}

	@Override
	public String toString() {
		return "a) " + fraas1 + ";\nb) " + fraas2 + ".";
	}

}
