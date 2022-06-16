package fp.TestCanalTwitch;

import java.util.List;
import java.util.SortedMap;

import fp.CanalTwitch.CanalTwitch;
import fp.CanalTwitch.EstadisticasTwitch;
import fp.CanalTwitch.FactoriasTwitch;

public class TestEstadisticasTwitch {

	public static void main(String[] args) {
		List<CanalTwitch> canales=FactoriasTwitch.leeCanales("data/CanalTwitch.csv");
		EstadisticasTwitch c=new EstadisticasTwitch(canales);
		System.out.println("\nTestMayorPorcentajeDeRetencion");
		System.out.println("================================");
		testMayorPorcentajeDeRetencion(c,"ESPAÑOL");
		System.out.println("\nTestDuracionTotalDelContenidoPublicadoSociosTwitch");
		System.out.println("================================");
		testDuracionTotalDelContenidoPublicadoSociosTwitch(c,true);
		System.out.println("\nTestDuracionTotalDelContenidoPublicadoSociosTwitch2");
		System.out.println("================================");
		testDuracionTotalDelContenidoPublicadoSociosTwitch2(c,true);
		System.out.println("\nTestNumCanalesTienenNumeroEspectadoresOcasionalesPorEncimaMedia");
		System.out.println("================================");
		testNumCanalesTienenNumeroEspectadoresOcasionalesPorEncimaMedia(c);
		System.out.println("\nTestCanalesConContenidoAdultoConMasTiempoVisionado");
		System.out.println("================================");
		testCanalesConContenidoAdultoConMasTiempoVisionado(c, 30);
		System.out.println("\nTestIdiomasPorCanalesMasEfectivos");
		System.out.println("================================");
		testIdiomasPorCanalesMasEfectivos(c);
		System.out.println("\nTestIdiomasPorCanalesMasEfectivos2");
		System.out.println("================================");
		testIdiomasPorCanalesMasEfectivos2(c);
		System.out.println("\nTestGetPorcentajePresenciaPorIdioma");
		System.out.println("================================");
		testGetPorcentajePresenciaPorIdioma(c);
	}

	private static void testMayorPorcentajeDeRetencion(EstadisticasTwitch c, String string) {
		try {
			String msg=String.format("El canal de Twitch del idioma %d con mayor porcentaje de retencion es: %s ",
					string,c.mayorPorcentajeDeRetencion(string));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	
	private static void testDuracionTotalDelContenidoPublicadoSociosTwitch(EstadisticasTwitch c, Boolean socios) {
		try {
			String msg=String.format("La duracion total del contenido que son socios %d de twitch es: %s",
					socios,c.duracionTotalDelContenidoPublicadoSociosTwitch(socios));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	
	private static void testDuracionTotalDelContenidoPublicadoSociosTwitch2(EstadisticasTwitch c, Boolean socios) {
		try {
			String msg=String.format("La duracion total del contenido que son socios %d de twitch es: %s",
					socios,c.duracionTotalDelContenidoPublicadoSociosTwitch(socios));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	

	private static void testNumCanalesTienenNumeroEspectadoresOcasionalesPorEncimaMedia(EstadisticasTwitch c) {
		try {
			String msg=String.format("El numero de canales que tienen un numero de espectadores ocasionales por encima de la media son: %s",
					c.numCanalesTienenNumeroEspectadoresOcasionalesPorEncimaMedia());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	
	private static void testCanalesConContenidoAdultoConMasTiempoVisionado(EstadisticasTwitch c, Integer n) {
		try {
			String msg=String.format("El porcentaje de los %d canales con contenido adulto con mas tiempo visionado es: %s",n,
					c.canalesConContenidoAdultoConMasTiempoVisionado(n));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	

	private static void testIdiomasPorCanalesMasEfectivos(EstadisticasTwitch c) {
		try {
			String msg=String.format("El canal mas efectivo por idioma es: ");
			System.out.println(msg);
			SortedMap<String,CanalTwitch> p=c.idiomasPorCanalesMasEfectivos();
			p.entrySet().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	
	private static void testIdiomasPorCanalesMasEfectivos2(EstadisticasTwitch c) {
		try {
			String msg=String.format("El canal mas efectivo por idioma es: ");
			System.out.println(msg);
			SortedMap<String,CanalTwitch> p=c.idiomasPorCanalesMasEfectivos2();
			p.entrySet().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	
	private static void testGetPorcentajePresenciaPorIdioma(EstadisticasTwitch c) {
		try {
			String msg=String.format("La lista de los idiomas ordenados de mayor a menor por porcentaje de presencia es: %s",
					c.getPorcentajePresenciaPorIdioma());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada " + e.getMessage());
		}
	}
	
	


}
