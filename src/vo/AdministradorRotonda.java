package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class AdministradorRotonda {
	
	@JsonProperty(value="cedula")
	private Long cedula;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="correo")
	private String correo;
	
	@JsonProperty(value="idRotonda")
	private Long idRotonda;
	
	public AdministradorRotonda(@JsonProperty(value="cedula")Long cedula, 
				   @JsonProperty(value="nombre")String nombre, 
				   @JsonProperty(value="correo") String correo, 
				   @JsonProperty(value="idRotonda")Long idRotonda)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		this.correo = correo;
		this.idRotonda = idRotonda;
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
}