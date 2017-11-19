package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClienteTipo {
	
	@JsonProperty(value="cedula")
	private Long cedula;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="correo")
	private String correo;
	
	@JsonProperty(value="idRotonda")
	private Long idRotonda;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	public ClienteTipo(@JsonProperty(value="cedula")Long cedula, 
				   @JsonProperty(value="nombre")String nombre, 
				   @JsonProperty(value="correo") String correo, 
				   @JsonProperty(value="idRotonda")Long idRotonda, 
				   @JsonProperty(value="tio")String tipo)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		this.correo = correo;
		this.idRotonda = idRotonda;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public Long getIdRotonda() {
		return idRotonda;
	}

	public void setIdRotonda(Long idRotonda) {
		this.idRotonda = idRotonda;
	}
	
	public String toParametros()
	{
		return 	Long.class.getName() + ":" + cedula + "," + 
				String.class.getName()+ ":" + nombre + "," +
				String.class.getName() + ":" + correo + "," + 
				Long.class.getName() + ":" + idRotonda +
				String.class.getName() + ":" + tipo + ",";
	}
}