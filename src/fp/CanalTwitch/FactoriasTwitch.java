package fp.CanalTwitch;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriasTwitch {
	
	public static List<CanalTwitch> leeCanales(String fichero){
		Checkers.checkNoNull(fichero);
		List<String> lineas=Ficheros.leeFichero("Error al leer el fichero", fichero);
		lineas.remove(0);
		
		List<CanalTwitch> res= new ArrayList<CanalTwitch>();
		for(String linea:lineas) {
			CanalTwitch c=parseaCanales(linea);
			res.add(c);
		}return res;
	}

	private static CanalTwitch parseaCanales(String linea) {
		Checkers.checkNoNull(linea);
		String[] trozos=linea.split(";");
		Checkers.check("Formato no valido ", trozos.length==11);
		String nombre=trozos[0].trim();
		Duration tiempoVisionado=Duration.parse(trozos[1].trim());
		Duration duracionContenido=Duration.parse(trozos[2].trim());
		Integer numEspectadoresPicoMax=Integer.parseInt(trozos[3].trim());
		Integer mediaEspectadores=Integer.parseInt(trozos[4].trim());
		Integer numFollowers=Integer.parseInt(trozos[5].trim());
		Integer numFollowersGanados=Integer.parseInt(trozos[6].trim());
		Integer numEspectadoresGanados=Integer.parseInt(trozos[7].trim());
		Boolean socioTwitch=Boolean.parseBoolean(trozos[8].trim());
		Boolean contenidoAdulto=Boolean.parseBoolean(trozos[9].trim());
		String idioma=trozos[10].trim();
		return new CanalTwitch(nombre, tiempoVisionado, duracionContenido, numEspectadoresPicoMax, mediaEspectadores, numFollowers,
				numFollowersGanados, numEspectadoresGanados, socioTwitch, contenidoAdulto, idioma);
	}

}
