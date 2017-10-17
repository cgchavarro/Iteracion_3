package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Tipo
{

	@JsonProperty(value="idTipo")
	private Long idTipo;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	//TODO completar atributos
	
	public Tipo(@JsonProperty(value="idTipo")Long idTipo, 
				@JsonProperty(value="nombre")String nombre)
	{
		this.idTipo = idTipo;
		this.nombre = nombre;
	}
	
	public Long getIdTipo()
	{
		return idTipo;
	}
	
	public void setIdTipo(Long idTipo)
	{
		this.idTipo = idTipo;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
}
