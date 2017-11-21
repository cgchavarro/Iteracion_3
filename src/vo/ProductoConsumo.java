package vo;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoConsumo {


	@JsonProperty(value="idProducto")
	private Long idProducto;


	@JsonProperty(value="nombre")
	private String nombre;

	@JsonProperty(value="nombreRestaurante")
	private String nombreRestaurante;
	
	@JsonProperty(value="fecha")
	private Date fecha;


	@JsonProperty(value="conteo")
	private int conteo;






	public ProductoConsumo(@JsonProperty(value="idProducto")Long idProducto, 
			@JsonProperty(value="nombre")String nombre, 
			@JsonProperty(value="nombreRestaurante")String nombreRestaurante,
			@JsonProperty(value="fecha")Date fecha, 
			@JsonProperty(value="conteo")int conteo
			) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.fecha = fecha;
		this.nombreRestaurante = nombreRestaurante;
		this.conteo = conteo;

		
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


	public String getNombreRestaurante() {
		return nombreRestaurante;
	}


	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public int getConteo() {
		return conteo;
	}


	public void setConteo(int conteo) {
		this.conteo = conteo;
	}


	public String toParametros()
	{
		return 	Long.class.getName() + ":" + idProducto + "," + 
				String.class.getName()+ ":" + nombre + "," +
				String.class.getName()+ ":" + nombreRestaurante + "," +
				Date.class.getName() + ":" + fecha + "," +
				Double.class.getName() + ":" + conteo;
	}

}
