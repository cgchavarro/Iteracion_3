package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Zona 
{

	@JsonProperty(value="idZona")
	private Long idZona;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="esZonaAbierta")
	private boolean esZonaAbierta;
	
	@JsonProperty(value="capacidad")
	private int capacidad;
	
	@JsonProperty(value="aptoParaTodos")
	private boolean aptoParaTodos;
	
	@JsonProperty(value="condicionesTecnicas")
	private String condicionesTecnicas;

	@JsonProperty(value="idRotonda")
	private Long idRotonda;
	
	//TODO completar atributos

	public Zona(@JsonProperty(value="idZona")Long idZona, 
				@JsonProperty(value="nombre")String nombre, 
				@JsonProperty(value="esZonaAbierta")boolean esZonaAbierta, 
				@JsonProperty(value="capacidad")int capacidad, 
				@JsonProperty(value="aptoParaTodos")boolean aptoParaTodos, 
				@JsonProperty(value="condicionesTecnicas")String condicionesTecnicas, 
				@JsonProperty(value="idRotonda")Long idRotonda) {
		this.idZona = idZona;
		this.nombre = nombre;
		this.esZonaAbierta = esZonaAbierta;
		this.capacidad = capacidad;
		this.aptoParaTodos = aptoParaTodos;
		this.condicionesTecnicas = condicionesTecnicas;
		this.idRotonda = idRotonda;
	}	

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public Long getIdRotonda() {
		return idRotonda;
	}

	public void setIdRotonda(Long idRotonda) {
		this.idRotonda = idRotonda;
	}

	public boolean isEsZonaAbierta() {
		return esZonaAbierta;
	}

	public void setEsZonaAbierta(boolean esZonaAbierta) {
		this.esZonaAbierta = esZonaAbierta;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isAptoParaTodos() {
		return aptoParaTodos;
	}

	public void setAptoParaTodos(boolean aptoParaTodos) {
		this.aptoParaTodos = aptoParaTodos;
	}

	public String getCondicionesTecnicas() {
		return condicionesTecnicas;
	}

	public void setCondicionesTecnicas(String condicionesTecnicas) {
		this.condicionesTecnicas = condicionesTecnicas;
	}
	
	
}
