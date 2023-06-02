package co.com.mstestneoris.dto;

import lombok.Data;

@Data
public class ClientesDTO {

	private Integer id;
	private String identificacion;
	private String nombres;
	private String genero;
	private String edad;
	private String direccion;
	private String telefono;
	private String contrasena;
	private boolean estado;

	public ClientesDTO() {

	}

	public ClientesDTO(Integer id, String identificacion, String nombres, String genero, String edad, String direccion,
			String telefono, String contrasena, boolean estado) {
		super();
		this.id = id;
		this.identificacion = identificacion;
		this.nombres = nombres;
		this.genero = genero;
		this.edad = edad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.contrasena = contrasena;
		this.estado = estado;
	}

}
