package vo;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class OrdenRestaurante {
	
	@JsonProperty(value="idOrdenRestaurante")
	private Long idOrdenRestaurante;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	@JsonProperty(value="idMenu")
	private Long idMenu;

	@JsonProperty(value="idRotonda")
	private Long idRotonda;
	
	@JsonProperty(value="idCliente")
	private Long idCliente;
	
	@JsonProperty(value="servida")
	private boolean servida;

	public OrdenRestaurante(@JsonProperty(value="idOrdenRestaurante")Long idOrdenRestaurante, 
							@JsonProperty(value="fecha")Date fecha, 
							@JsonProperty(value="idMenu")Long idMenu, 
							@JsonProperty(value="idRotonda")Long idRotonda,
							@JsonProperty(value="idCliente") Long idCliente,
							@JsonProperty(value="servida") boolean servida) {
		this.idOrdenRestaurante = idOrdenRestaurante;
		this.fecha = fecha;
		this.idMenu = idMenu;
		this.idRotonda = idRotonda;
		this.idCliente=idCliente;
		this.servida=servida;
	}

	public Long getIdOrdenRestaurante() {
		return idOrdenRestaurante;
	}

	public void setIdOrdenRestaurante(Long idOrdenRestaurante) {
		this.idOrdenRestaurante = idOrdenRestaurante;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}
	
	public Long getIdRotonda() {
		return idRotonda;
	}

	public void setIdRotonda(Long idRotonda) {
		this.idRotonda = idRotonda;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public boolean isServida() {
		return servida;
	}

	public void setServida(boolean servida) {
		this.servida = servida;
	}	
	
	
	
}
