import java.util.ArrayList;
import java.util.Random;


public class Andmed {
	
	private Random random;
	protected ArrayList<Ylesanne> andmed;
	
	public Andmed() {
		this.random = new Random();
		this.andmed = new ArrayList<Ylesanne>();
	}
	
	public int suvalineIndeks(int eelmineIndeks) {
		int suvalineIndeks = random.nextInt(andmed.size());
		if (andmed.size() != 1 && suvalineIndeks == eelmineIndeks) {
			return suvalineIndeks(eelmineIndeks);
		}
		return suvalineIndeks;
	}
	
	public Ylesanne getAnne(int indeks) {
		return andmed.get(indeks);
	}
	
	@Override
	public String toString() {
		String string = "";
		for (Ylesanne paar : this.andmed) {
			string += paar + "\n";
		}
		return string;
	}

}
