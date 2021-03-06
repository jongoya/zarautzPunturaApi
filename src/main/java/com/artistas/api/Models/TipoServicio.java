package com.artistas.api.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tipo_servicio")
public class TipoServicio implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicioId;
    private String nombre;
    private Long numCabinas;
    private boolean bloqueaTerapeuta;
    private int duracion;
    private Long terapeuta;
    private double precio;
    private Long red_color_value;
    private Long green_color_value;
    private Long blue_color_value;
	
	@Transient
	private String unique_deviceId;
    
    public TipoServicio() {
    	
    }

	public Long getServicioId() {
		return servicioId;
	}

	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnique_deviceId() {
		return unique_deviceId;
	}

	public void setUnique_deviceId(String unique_deviceId) {
		this.unique_deviceId = unique_deviceId;
	}

	public Long getNumCabinas() {
		return numCabinas;
	}

	public void setNumCabinas(Long numCabinas) {
		this.numCabinas = numCabinas;
	}

	public boolean isBloqueaTerapeuta() {
		return bloqueaTerapeuta;
	}

	public void setBloqueaTerapeuta(boolean bloqueaTerapeuta) {
		this.bloqueaTerapeuta = bloqueaTerapeuta;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Long getTerapeuta() {
		return terapeuta;
	}

	public void setTerapeuta(Long terapeuta) {
		this.terapeuta = terapeuta;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Long getRed_color_value() {
		return red_color_value;
	}

	public void setRed_color_value(Long red_color_value) {
		this.red_color_value = red_color_value;
	}

	public Long getGreen_color_value() {
		return green_color_value;
	}

	public void setGreen_color_value(Long green_color_value) {
		this.green_color_value = green_color_value;
	}

	public Long getBlue_color_value() {
		return blue_color_value;
	}

	public void setBlue_color_value(Long blue_color_value) {
		this.blue_color_value = blue_color_value;
	}

	private static final long serialVersionUID = 1L;
}
