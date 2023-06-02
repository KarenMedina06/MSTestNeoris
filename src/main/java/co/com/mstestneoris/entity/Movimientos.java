package co.com.mstestneoris.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import co.com.mstestneoris.dto.MovimientosDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Movimientos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimientos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimiento")
    private Integer id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "fecha")
    private LocalDate fecha;
	
	@Column(name = "tipoMovimiento")
	private String tipoMovimiento;
	
	@Column(name = "valor")
	private Long valor;
	
	@Column(name = "saldo")
	private Long saldo;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "idCuenta")
    private Cuentas cuentas;
	
	public Movimientos(MovimientosDTO dto) {
		this.id = dto.getId();
		this.tipoMovimiento = dto.getTipoMovimiento();
		this.valor = dto.getValor();
		this.saldo = dto.getSaldo();
		this.fecha = LocalDate.now();
	}
	
	public Movimientos(MovimientosDTO dto, Cuentas cuenta) {
		this.id = dto.getId();
		this.tipoMovimiento = dto.getTipoMovimiento();
		this.valor = dto.getValor();
		this.saldo = dto.getSaldo();
		this.fecha = LocalDate.now();
		this.cuentas = cuenta;
	}
	
}
