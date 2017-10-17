package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Restaurante 
{

	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="tipoComida")
	private String tipoComida;
	
	@JsonProperty(value="pagWeb")
	private String pagWeb;
	
	@JsonProperty(value="encargado")
	private String encargado;
	
	@JsonProperty(value="idZona")
	private Long idZona;
	
	@JsonProperty(value="idRotonda")
	private Long idRotonda;

	public Restaurante(
					   @JsonProperty(value="nombre")String nombre, 
					   @JsonProperty(value="tipoComida")String tipoComida, 
					   @JsonProperty(value="pagWeb")String pagWeb, 
					   @JsonProperty(value="encargado")String encargado, 
					   @JsonProperty(value="idZona")Long idZona, 
					   @JsonProperty(value="idRotonda")Long idRotonda) {
	
		this.nombre = nombre;
		this.tipoComida = tipoComida;
		this.pagWeb = pagWeb;
		this.encargado = encargado;
		this.idZona = idZona;
		this.idRotonda = idRotonda;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public Long getIdRotonda() {
		return idRotonda;
	}

	public void setIdRotonda(Long idRotonda) {
		this.idRotonda = idRotonda;
	}

	public String getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	public String getPagWeb() {
		return pagWeb;
	}

	public void setPagWeb(String pagWeb) {
		this.pagWeb = pagWeb;
	}

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}	
	
}
