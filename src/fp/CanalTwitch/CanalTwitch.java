package fp.CanalTwitch;

import java.time.Duration;

public record CanalTwitch(String nombre,Duration tiempoVisionado,Duration duracionContenido,Integer numEspectadoresPicoMax,
		Integer mediaEspectadores,Integer numFollowers,Integer numFollowersGanados,Integer numEspectadoresGanados,Boolean socioTwitch,Boolean contenidoAdulto,
		String idioma) implements Comparable<CanalTwitch>{
	
	public Double porcentajeRetencion() {
		Double res=0.0;
		if(numEspectadoresGanados()>0) {
			res=numFollowersGanados()*100.0/numEspectadoresGanados();
		}
		return res;
	}
	
	public Double ratioEfectividad() {
		return (tiempoVisionado().getSeconds()*1.0/duracionContenido().getSeconds());
	}
	
	public Integer numEspectadoresOcasionales() {
		return Math.abs(numEspectadoresPicoMax()-mediaEspectadores()) ;
		
	}
	
	public int compareTo(CanalTwitch o) {
		int res=this.nombre().compareTo(o.nombre());
		return res;
	}

}
