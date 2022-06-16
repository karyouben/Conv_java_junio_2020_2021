package fp.TestCanalTwitch;

import java.util.List;

import fp.CanalTwitch.CanalTwitch;
import fp.CanalTwitch.FactoriasTwitch;

public class TestFactoriasTwitch {

	public static void main(String[] args) {
		testLeeCanales("data/CanalTwitch.csv");
	}

	private static void testLeeCanales(String fichero) {
		System.out.println("\nTestLeeCanales ==========");
		List<CanalTwitch> canales=FactoriasTwitch.leeCanales(fichero);
		System.out.println(" CanalTwitch: ");
		for(CanalTwitch c:canales) {
			System.out.println(c);
		}
	}

}
