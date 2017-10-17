package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu {

	@JsonProperty(value="idMenu")
	private Long idMenu;
		
	@JsonProperty(value="costo")
	private double costo;
	
	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="nombreRestaurante")
	private String nombreRestaurante;
	
	@JsonProperty(value="idPostre")
	private Long idPostre;
	
	@JsonProperty(value="idEntrada")
	private Long idEntrada;

	@JsonProperty(value="idPlatoFuerte")
	private Long idPlatoFuerte;
	
	@JsonProperty(value="idAcompaniamiento")
	private Long idAcompaniamiento;
	
	@JsonProperty(value="idBebida")
	private Long idBebida;
	
	
	public Menu(@JsonProperty(value="idMenu")Long idMenu,
				@JsonProperty(value="costo") double costo, 
				@JsonProperty(value="precio")double precio, 
				@JsonProperty(value= "nombreRestaurante")String nombreRestaurante,
				@JsonProperty(value="idPostre") Long idPostre, 
				@JsonProperty(value="idEntrada") Long idEntrada,
				@JsonProperty(value="idPlatoFuerte") Long idPlatoFuerte,
				@JsonProperty(value="idAcompaniamiento") Long idAconpaniamiento,
				@JsonProperty(value="idBebida") Long idBebida) 
	{
		this.idMenu = idMenu;
		this.costo = costo;
		this.precio = precio;
		this.nombreRestaurante = nombreRestaurante;
		this.idPostre=idPostre;
		this.idEntrada=idEntrada;
		this.idPlatoFuerte=idPlatoFuerte;
		this.idAcompaniamiento=idAconpaniamiento;
		this.idBebida=idBebida;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	

	public String getNombreRestaurante() {
		return nombreRestaurante;
	}

	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Long getIdPostre() {
		return idPostre;
	}

	public void setIdPostre(Long idPostre) {
		this.idPostre = idPostre;
	}

	public Long getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Long idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Long getIdPlatoFuerte() {
		return idPlatoFuerte;
	}

	public void setIdPlatoFuerte(Long idPlatoFuerte) {
		this.idPlatoFuerte = idPlatoFuerte;
	}

	public Long getIdAcompaniamiento() {
		return idAcompaniamiento;
	}

	public void setIdAcompaniamiento(Long idAcompaniamiento) {
		this.idAcompaniamiento = idAcompaniamiento;
	}

	public Long getIdBebida() {
		return idBebida;
	}

	public void setIdBebida(Long idBebida) {
		this.idBebida = idBebida;
	}	
	
	
	
}
