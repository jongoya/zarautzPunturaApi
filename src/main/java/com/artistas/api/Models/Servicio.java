package com.artistas.api.Models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private Long clientId;
    private Long fecha;
    private Long empleadoId;
    private ArrayList <Long> servicios = new ArrayList<>();
    private String observacion;
    private boolean isEfectivo;
    private String imgPlantilla;
    private String selector;
    private ArrayList <Long> sistemas = new ArrayList<>();
    private String meridianos;
    private String equilibrio;
    private String imgPlantilla2;
    
    public Servicio() {
    	
    }

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public Long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}

	public ArrayList<Long> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Long> servicios) {
		this.servicios = servicios;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isEfectivo() {
		return isEfectivo;
	}

	public void setEfectivo(boolean isEfectivo) {
		this.isEfectivo = isEfectivo;
	}
	
	public String getImgPlantilla() {
		return imgPlantilla;
	}

	public void setImgPlantilla(String imgPlantilla) {
		this.imgPlantilla = imgPlantilla;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public ArrayList<Long> getSistemas() {
		return sistemas;
	}

	public void setSistemas(ArrayList<Long> sistemas) {
		this.sistemas = sistemas;
	}

	public String getMeridianos() {
		return meridianos;
	}

	public void setMeridianos(String meridianos) {
		this.meridianos = meridianos;
	}

	public String getEquilibrio() {
		return equilibrio;
	}

	public void setEquilibrio(String equilibrio) {
		this.equilibrio = equilibrio;
	}

	public String getImgPlantilla2() {
		return imgPlantilla2;
	}

	public void setImgPlantilla2(String imgPlantilla2) {
		this.imgPlantilla2 = imgPlantilla2;
	}



	private static final long serialVersionUID = 1L;
}