import java.util.ArrayList;
import java.util.Random;


public class Andmed {
	
	private Random random;
	protected ArrayList<Ylesanne> andmed;
	private int eelmineIndeks = -1;
	
	public Andmed() {
		this.random = new Random();
		this.andmed = new ArrayList<Ylesanne>();
	}
	
	public int suvalineIndeks() {
		int suvalineIndeks = random.nextInt(andmed.size());
		if (andmed.size() != 1 && suvalineIndeks == eelmineIndeks) {
			return suvalineIndeks();
		}
		return suvalineIndeks;
	}
	
	public Ylesanne getYlesanne() {
		eelmineIndeks = suvalineIndeks();
		return andmed.get(eelmineIndeks);
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
