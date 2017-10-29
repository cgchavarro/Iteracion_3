package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoVenta {


	@JsonProperty(value="idProducto")
	private Long idProducto;


	@JsonProperty(value="nombre_restaurante")
	private String nombre;



	@JsonProperty(value="ventasTotales")
	private int ventas;


	



	public ProductoVenta(@JsonProperty(value="idProducto")Long idProducto, 
			@JsonProperty(value="nombre_restaurante")String nombre, 
			
			@JsonProperty(value="ventasTotales") int cantidad
			) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.ventas=cantidad;
		
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


	public int getVentas() {
		return ventas;
	}


	public void setVentas(int ventas) {
		this.ventas = ventas;
	}


	public String toParametros()
	{
		return 	Long.class.getName() + ":" + idProducto + "," + 
				String.class.getName()+ ":" + nombre + "," +
								Integer.class.getName() + ":" + ventas ;
	}

}
