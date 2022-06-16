package fp.CanalTwitch;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class EstadisticasTwitch {
	private List<CanalTwitch> canales;
	
	
	public EstadisticasTwitch() {
		canales=new ArrayList<>();
	}
	
	public EstadisticasTwitch(Collection<CanalTwitch> canales) {
		this.canales=new ArrayList<>(canales);
	}
	
	public EstadisticasTwitch(List<CanalTwitch> canales) {
		this.canales=new ArrayList<>(canales);
	}
	
	public EstadisticasTwitch(Stream<CanalTwitch> canales) {
		this.canales=canales.collect(Collectors.toList());
	}

	public List<CanalTwitch> getCanales() {
		return canales;
	}

	@Override
	public int hashCode() {
		return Objects.hash(canales);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticasTwitch other = (EstadisticasTwitch) obj;
		return Objects.equals(canales, other.canales);
	}

	@Override
	public String toString() {
		return "EstadisticasTwitch [canales=" + canales + "]";
	}
	
	//ejercicio 1
	
	public String mayorPorcentajeDeRetencion(String idioma) {
		return canales.stream()
				.filter(c->c.idioma().equals(idioma) || c.idioma()==null)
				.max(Comparator.comparing(CanalTwitch::porcentajeRetencion))
				.map(CanalTwitch::nombre)
				.orElse("");
	}
	
	//ejercicio 2
	
	public Duration duracionTotalDelContenidoPublicadoSociosTwitch(Boolean socios) {
		Long res= (long) canales.stream()
				.filter(c->c.socioTwitch().equals(socios))
				.mapToDouble(c->c.duracionContenido().getSeconds())
				.sum();
		return Duration.ofSeconds(res);
				
				
	}
	
	public Duration duracionTotalDelContenidoPublicadoSociosTwitch2(Boolean socios) {
		return canales.stream()
				.filter(c->c.socioTwitch().equals(socios))
				.map(CanalTwitch::duracionContenido)
				.reduce(Duration.ZERO, Duration::plus);
				
	}
	
	//ejercicio 3
	
	public Integer numCanalesTienenNumeroEspectadoresOcasionalesPorEncimaMedia() {
		Double mediaEspectadoresOcacionales=canales.stream()
				.mapToInt(CanalTwitch::numEspectadoresOcasionales)
				.average()
				.getAsDouble();
		
		return canales.stream()
				.filter(c->c.numEspectadoresOcasionales()>mediaEspectadoresOcacionales)
				.collect(Collectors.collectingAndThen(Collectors.counting(), c->c.intValue()));
	}
	
	//ejercicio 4
	
	public Double canalesConContenidoAdultoConMasTiempoVisionado(Integer n) {
		Checkers.check("El numero n tiene que ser positivo", n>0);
		Map<Boolean,List<CanalTwitch>> m=mapMasVisionados(n);
		
		Integer numCanalesAdultos=m.get(true).size();
		Integer numCanalesNoAdultos=m.get(false).size();
		return 100.0*numCanalesAdultos/(numCanalesNoAdultos+numCanalesAdultos);
	}
	

	
	public Map<Boolean,List<CanalTwitch>> mapMasVisionados(Integer n) {
		return canales.stream()
				.sorted(Comparator.comparing(CanalTwitch::tiempoVisionado).reversed())
				.limit(n)
				.collect(Collectors.partitioningBy(CanalTwitch::contenidoAdulto));
		
	}
	
	
	//ejercicio 5
	
	public SortedMap<String,CanalTwitch> idiomasPorCanalesMasEfectivos(){
		return canales.stream()
				.collect(Collectors.toMap(CanalTwitch::idioma,Function.identity(),
						BinaryOperator.maxBy(Comparator.comparing(CanalTwitch::ratioEfectividad)),TreeMap::new));
	}
	
	public SortedMap<String,CanalTwitch> idiomasPorCanalesMasEfectivos2(){
		return canales.stream()
				.collect(Collectors.groupingBy(CanalTwitch::idioma,TreeMap::new,
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(CanalTwitch::ratioEfectividad)), Optional::get)));
	}
	
	//ejercicio 6
	
	public Map<String, Double> mapaPorcentajePresenciaPorIdioma(){
		Map<String,Long> m=duracionTotalPorIdioma();
		Long p=totalDuracionTodosCanales();
		return m.entrySet().stream()
				.collect(Collectors.toMap(c->c.getKey(), c->c.getValue()*100.0/p));
		
	}
	
	public List<String> getPorcentajePresenciaPorIdioma(){
		Map<String, Double> m=mapaPorcentajePresenciaPorIdioma();
		Comparator<Map.Entry<String, Double>> r= Comparator.comparing(Map.Entry::getValue);
		return m.entrySet().stream()
				.sorted(r.reversed())
				.map(c->c.getKey())
				.collect(Collectors.toList());
	}
	
	public Map<String,Long> duracionTotalPorIdioma(){
		return canales.stream()
				.collect(Collectors.groupingBy(CanalTwitch::idioma,Collectors.summingLong(canal->canal.duracionContenido().toSeconds())));
		
	}
	
	public Long totalDuracionTodosCanales() {
		 Map<String,Long> m=duracionTotalPorIdioma();
		return m.values().stream()
				.mapToLong(Long::longValue)
				.sum();
	}
	
	
	
	

}
