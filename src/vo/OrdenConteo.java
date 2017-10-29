package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class OrdenConteo
{

	@JsonProperty(value="cantidadOrdenes")
	private int cantidadOrdenes;
	
	@JsonProperty(value="cantidadOrdenesClientes")
	private int cantidadOrdenesClientes;
	
	@JsonProperty(value="cantidadOrdenesClientesNoClientes")
	private int cantidadOrdenesClientesNoClientes;
	//TODO completar atributos
	
	public OrdenConteo(@JsonProperty(value="cantidadOrdenes") int cantidadClientes, 
			@JsonProperty(value="cantidadOrdenesClientes") int cantidadOrdenesClientes, @JsonProperty(value="cantidadOrdenesClientesNoClientes") int noClientes	)
	{
	this.cantidadOrdenes=cantidadClientes;
	this.cantidadOrdenesClientes= cantidadOrdenesClientes;
	this.cantidadOrdenesClientesNoClientes=noClientes;
	}
	

	
	public int getCantidadOrdenes() {
		return cantidadOrdenes;
	}



	public void setCantidadOrdenes(int cantidadOrdenes) {
		this.cantidadOrdenes = cantidadOrdenes;
	}



	public int getCantidadOrdenesClientes() {
		return cantidadOrdenesClientes;
	}



	public void setCantidadOrdenesClientes(int cantidadOrdenesClientes) {
		this.cantidadOrdenesClientes = cantidadOrdenesClientes;
	}



	public int getCantidadOrdenesClientesNoClientes() {
		return cantidadOrdenesClientesNoClientes;
	}



	public void setCantidadOrdenesClientesNoClientes(int cantidadOrdenesClientesNoClientes) {
		this.cantidadOrdenesClientesNoClientes = cantidadOrdenesClientesNoClientes;
	}



	public String toParametros()
	{
		return 	int.class.getName() + ":" + cantidadOrdenes + "," + 
				int.class.getName() + ":" + cantidadOrdenesClientes + "," + 
				int.class.getName()+ ":" + cantidadOrdenesClientesNoClientes;
	}
}
