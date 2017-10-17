package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto {
	

	@JsonProperty(value="idProducto")
	private Long idProducto;
	

	@JsonProperty(value="nombre")
	private String nombre;
	

	@JsonProperty(value="idCategoria")
	private Long idCategoria;
	

	@JsonProperty(value="descripcionEsp")
	private String descripcionEsp;
	

	@JsonProperty(value="descripcionIng")
	private String descripcionIng;
	

	@JsonProperty(value="tiempo")
	private int tiempo;
	

	@JsonProperty(value="costo")
	private double costo;
	

	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="cantidad")
	private int cantidad;
	
	@JsonProperty(value="nombreRestaurante")
	private String nombreRestaurante;

	
	
	public Producto(@JsonProperty(value="idProducto")Long idProducto, 
					@JsonProperty(value="nombre")String nombre, 
					@JsonProperty(value="idCategoria")Long idCategoria, 
					@JsonProperty(value="descripcionEsp")String descripcionEsp, 
					@JsonProperty(value="descripcionIng")String descripcionIng, 
					@JsonProperty(value="tiempo")int tiempo, 
					@JsonProperty(value="costo")double costo, 
					@JsonProperty(value="precio")double precio,
					@JsonProperty(value="cantidad") int cantidad,
					@JsonProperty(value="nombreRestaurante") String nombreRestaurante) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.idCategoria = idCategoria;
		this.descripcionEsp = descripcionEsp;
		this.descripcionIng = descripcionIng;
		this.tiempo = tiempo;
		this.costo = costo;
		this.precio = precio;
		this.cantidad=cantidad;
		this.nombreRestaurante=nombreRestaurante;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCategoria() {
		return idCategoria;
	}

	public void setCategoria(Long categoria) {
		this.idCategoria = categoria;
	}

	public String getDescripcionEsp() {
		return descripcionEsp;
	}

	public void setDescripcionEsp(String descripcionEsp) {
		this.descripcionEsp = descripcionEsp;
	}

	public String getDescripcionIng() {
		return descripcionIng;
	}

	public void setDescripcionIng(String descripcionIng) {
		this.descripcionIng = descripcionIng;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
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

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombreRestaurante() {
		return nombreRestaurante;
	}

	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	

}
