package vo;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva 
{
	@JsonProperty(value="idReserva")
	private Long idReserva;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	@JsonProperty(value="numComensales")
	private int numComensales;
	
	@JsonProperty(value="idCliente")
	private Long idCliente;
	
	@JsonProperty(value="idZona")
	private Long idZona;
	
	//TODO completar atributos


	public Reserva(@JsonProperty(value="idReserva")Long idReserva, 
				   @JsonProperty(value="fecha")Date fecha, 
				   @JsonProperty(value="numComensales")int numComensales, 
				   @JsonProperty(value="idCliente")Long idCliente, 
				   @JsonProperty(value="idZona")Long idZona) {
		this.idReserva = idReserva;
		this.fecha = fecha;
		this.numComensales = numComensales;
		this.idCliente = idCliente;
		this.idZona = idZona;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumComensales() {
		return numComensales;
	}

	public void setNumComensales(int numComensales) {
		this.numComensales = numComensales;
	}
	
	
	
}
