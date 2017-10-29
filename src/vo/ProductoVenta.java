package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoVenta {


	@JsonProperty(value="idProducto")
	private Long idProducto;


	@JsonProperty(value="nombre_restaurante")
	private String nombre_restaurante;



	@JsonProperty(value="ventasTotales")
	private int ventasTotales;


	@JsonProperty(value="cantidadVendidos")
	private int cantidadVendidos;



	public ProductoVenta(@JsonProperty(value="idProducto")Long idProducto, 
			@JsonProperty(value="nombre_restaurante")String nombre, 
			
			@JsonProperty(value="ventasTotales") int cantidad, @JsonProperty(value="cantidadVendidos") int vendidos
			) {
		this.idProducto = idProducto;
		this.nombre_restaurante = nombre;
		this.ventasTotales=cantidad;
		this.cantidadVendidos= vendidos;
		
	}

	
	public Long getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}


	


	public String getNombre_restaurante() {
		return nombre_restaurante;
	}


	public void setNombre_restaurante(String nombre_restaurante) {
		this.nombre_restaurante = nombre_restaurante;
	}


	public int getVentasTotales() {
		return ventasTotales;
	}


	public void setVentasTotales(int ventasTotales) {
		this.ventasTotales = ventasTotales;
	}


	


	public int getCantidadVendidos() {
		return cantidadVendidos;
	}


	public void setCantidadVendidos(int cantidadVendidos) {
		this.cantidadVendidos = cantidadVendidos;
	}


	public String toParametros()
	{
		return 	Long.class.getName() + ":" + idProducto + "," + 
				String.class.getName()+ ":" + nombre_restaurante + "," +
								Integer.class.getName() + ":" + ventasTotales +
								Integer.class.getName() + ":" + cantidadVendidos;
	}

}
