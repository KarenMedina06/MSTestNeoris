package co.com.mstestneoris.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.mstestneoris.dto.CuentasDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cuentas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cuentas implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCuenta")
	private Integer id;
	
	@Column(name = "numeroCuenta", unique = true, nullable = false)
	private Long numeroCuenta;
	
	@Column(name = "tipoCuenta")
	private String tipoCuenta;
	
	@Column(name = "saldoInicial")
	private Long saldoInicial;
	
	@Column(name = "estado")
	private boolean estado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cuentas", cascade = CascadeType.ALL)
    private List<Movimientos> movimientos;

	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private Clientes cliente;
    
    public Cuentas(CuentasDTO dto) {
		this.id = dto.getId();
		this.numeroCuenta = dto.getNumeroCuenta();
		this.tipoCuenta = dto.getTipoCuenta();
		this.saldoInicial = dto.getSaldoInicial();
		this.estado = dto.isEstado();
	}
	
	public Cuentas(CuentasDTO dto, Clientes clientes) {
		this.id = dto.getId();
		this.numeroCuenta = dto.getNumeroCuenta();
		this.tipoCuenta = dto.getTipoCuenta();
		this.saldoInicial = dto.getSaldoInicial();
		this.estado = dto.isEstado();
		this.cliente = clientes;
	}
	
}
