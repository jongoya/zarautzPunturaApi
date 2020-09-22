package com.artistas.api.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sistemas")
public class SistemasEquilibrio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sistemaId;
	private String nombre;
	private String subtitulo;
	private String sis1;
	private String sis2;
	private String sis3;
	private String sis4;
	private String sis5;
	private String sis6;
	
	public Long getSistemaId() {
		return sistemaId;
	}
	
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSubtitulo() {
		return subtitulo;
	}
	
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	public String getSis1() {
		return sis1;
	}
	
	public void setSis1(String sis1) {
		this.sis1 = sis1;
	}
	
	public String getSis2() {
		return sis2;
	}
	
	public void setSis2(String sis2) {
		this.sis2 = sis2;
	}
	
	public String getSis3() {
		return sis3;
	}
	
	public void setSis3(String sis3) {
		this.sis3 = sis3;
	}
	
	public String getSis4() {
		return sis4;
	}
	
	public void setSis4(String sis4) {
		this.sis4 = sis4;
	}
	
	public String getSis5() {
		return sis5;
	}
	
	public void setSis5(String sis5) {
		this.sis5 = sis5;
	}
	public String getSis6() {
		return sis6;
	}
	
	public void setSis6(String sis6) {
		this.sis6 = sis6;
	}
}
