package co.com.mstestneoris.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.mstestneoris.dto.ClientesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clientes extends Personas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Integer id;
	
	@Column(name = "contrasena")
	private String contrasena;
	
	@Column(name = "estado")
	private boolean estado;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Cuentas> cuentas;
	
	
	public Clientes(ClientesDTO dto) {
		setIdentificacion(dto.getIdentificacion());
		setNombres(dto.getNombres());
		setEdad(dto.getEdad());
		setDireccion(dto.getDireccion());
		setGenero(dto.getGenero());
		setTelefono(dto.getTelefono());
		setContrasena(dto.getContrasena());
		setEstado(dto.isEstado());
	}
	

	
	
}
