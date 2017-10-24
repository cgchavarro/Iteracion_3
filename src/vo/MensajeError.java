package vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class MensajeError {

	@JsonProperty(value="mensaje")

	private String mensaje;

	public MensajeError(@JsonProperty(value="mensaje") String mensaje) {
		this.mensaje=mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
}
