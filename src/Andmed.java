import java.util.ArrayList;
import java.util.Random;


public class Andmed {
	
	private Random random;
	protected ArrayList<Anne> andmed;
	
	public Andmed() {
		this.random = new Random();
		this.andmed = new ArrayList<Anne>();
	}
	
	public int suvalineIndeks(int eelmineIndeks) {
		int suvalineIndeks = random.nextInt(andmed.size());
		if (andmed.size() != 1 && suvalineIndeks == eelmineIndeks) {
			return suvalineIndeks(eelmineIndeks);
		}
		return suvalineIndeks;
	}
	
	public Anne getAnne(int indeks) {
		return andmed.get(indeks);
	}
	
	@Override
	public String toString() {
		String string = "";
		for (Anne paar : this.andmed) {
			string += paar + "\n";
		}
		return string;
	}

}
