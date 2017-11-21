package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClienteTipo {
	
	@JsonProperty(value="cedula")
	private Long cedula;
	
	@JsonProperty(value="nombre")
	private String nombre;

	@JsonProperty(value="tipo")
	private String tipo;
	
	public ClienteTipo(@JsonProperty(value="cedula")Long cedula, 
				   @JsonProperty(value="nombre")String nombre, 
				 
				   @JsonProperty(value="tio")String tipo)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		
		this.tipo=tipo;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	public String toParametros()
	{
		return 	Long.class.getName() + ":" + cedula + "," + 
				String.class.getName()+ ":" + nombre + "," +
				
				String.class.getName() + ":" + tipo + ",";
	}
}