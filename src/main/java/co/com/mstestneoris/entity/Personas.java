package co.com.mstestneoris.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Personas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "identificacion", unique = true, nullable = false)
	private String identificacion;
	
	@Column(name = "nombres", nullable = false)
	private String nombres;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "edad")
	private String edad;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;


}
