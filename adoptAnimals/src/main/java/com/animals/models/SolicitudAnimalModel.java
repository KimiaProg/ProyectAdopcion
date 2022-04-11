package com.animals.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;

@Where(clause = "deleted = false")
@Table(name= "solicitudAnimal")
@Data
@Entity(name="solicitudAnimal")
public class SolicitudAnimalModel {
	
	@ManyToOne
    @JoinColumn(name = "animalesDNI",foreignKey = @ForeignKey(name="ANIMAL_ID_FK"))
	private AnimalModel animalesDNI;
	@ManyToOne
    @JoinColumn(name = "nombreUsuario",foreignKey = @ForeignKey(name="USUARIO_ID_FK"))
	private UsuarioModel nombreUsuario;
	@Id
	private String fecha;
	@Column
	private String estado;
	@Column
	private String deleted;


}
