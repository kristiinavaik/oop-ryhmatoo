
public class Lause extends Ylesanne {

	private String lause;
	
	public Lause(String lause) {
		this.lause = lause;
	}

	public String getFraas1() {
		return lause;
	}

	public void setFraas1(String fraas1) {
		this.lause = fraas1;
	}

	public String ilmaPunktuatsioonita() {
		return lause.replaceAll("[^A-Za-z0-9ÕõÄäÖöÜü ]", "");
	}
	
	public boolean kontrolliVastus(String vastus) {
		return vastus.trim().equals(lause);
	}
	
	public String korrektneFraas() {
		return lause;
	}

	@Override
	public String toString() {
		return lause;
	}
	
}
