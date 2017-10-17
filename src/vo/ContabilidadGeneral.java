package vo;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContabilidadGeneral
{  	@JsonProperty(value="nombreRestaurante")
	private String nombreRestaurante;
	 @JsonProperty(value="fechaContabilidad")
	private Date fechaContabilidad;
	private double valorVentas;
	@JsonProperty(value="valorCostos")
	private double valorCostos;
	@JsonProperty(value="idRotonda")
	private Long idRotonda;
	
	 public ContabilidadGeneral(@JsonProperty(value="nombreRestaurante")String nombreRestaurante, 
			   @JsonProperty(value="fechaContabilidad")Date fechaContabilidad,
			   @JsonProperty(value="valorCostos")double valorCostos, 
			   @JsonProperty(value="valorVentas")double valorVentas,
			   @JsonProperty(value="idRotonda")Long idRotonda)
{
	this.valorCostos=valorCostos;
	this.valorVentas=valorVentas;
	this.nombreRestaurante=nombreRestaurante;
	this.fechaContabilidad=fechaContabilidad;
	this.idRotonda=idRotonda;
	
}
	
	public String getNombreRestaurante() {
		return nombreRestaurante;
	}
	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	public Date getFechaContabilidad() {
		return fechaContabilidad;
	}
	public void setFechaContabilidad(Date fechaContabilidad) {
		this.fechaContabilidad = fechaContabilidad;
	}
	public double getValorVentas() {
		return valorVentas;
	}
	public void setValorVentas(double valorVentas) {
		this.valorVentas = valorVentas;
	}
	public double getValorCostos() {
		return valorCostos;
	}
	public void setValorCostos(double valorCostos) {
		this.valorCostos = valorCostos;
	}
	public Long getIdRotonda() {
		return idRotonda;
	}
	public void setIdRotonda(Long idRotonda) {
		this.idRotonda = idRotonda;
	}
	
	
	
}
