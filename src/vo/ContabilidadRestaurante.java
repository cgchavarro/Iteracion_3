package vo;

import    java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContabilidadRestaurante 
{
	@JsonProperty(value="idVenta")
    private Long idVenta;
	@JsonProperty(value="costoVenta")
    private double costoVenta;
	@JsonProperty(value="precioVenta")
    private double precioVenta;
	@JsonProperty(value="fechaVenta")
    private Date fechaVenta;
	@JsonProperty(value="nombreRestaurante")
    private String nombreRestaurante;
    
    
    public ContabilidadRestaurante(@JsonProperty(value="idVenta")Long idVenta, 
			   @JsonProperty(value="costoVenta")double costoVenta, 
			   @JsonProperty(value="precioVenta") double precioVenta, 
			   @JsonProperty(value="fechaVenta")Date fechaVenta,
			   @JsonProperty(value="nombreRestaurante")String nombreRestaurante)
{
	this.idVenta=idVenta;
	this.costoVenta=costoVenta;
	this.precioVenta=precioVenta;
	this.fechaVenta=fechaVenta;
	this.nombreRestaurante=nombreRestaurante;
	
}
    
	public Long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}
	public double getCostoVenta() {
		return costoVenta;
	}
	public void setCostoVenta(double costoVenta) {
		this.costoVenta = costoVenta;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public String getNombreRestaurante() {
		return nombreRestaurante;
	}
	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
    
    
}
