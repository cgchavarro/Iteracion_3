package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class AdministradorRestaurante {
	
	@JsonProperty(value="cedula")
	private Long cedula;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="correo")
	private String correo;
	
	@JsonProperty(value="nombreRestaurante")
	private String nombreRestaurante;
	
	public AdministradorRestaurante(@JsonProperty(value="cedula")Long cedula, 
				   @JsonProperty(value="nombre")String nombre, 
				   @JsonProperty(value="correo") String correo, 
				   @JsonProperty(value="nombreRestaurante")String nombreRestaurante)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		this.correo = correo;
		this.nombreRestaurante = nombreRestaurante;
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

	public String getNombreRestaurante() {
		return nombreRestaurante;
	}

	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	
	
}

