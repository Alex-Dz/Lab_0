package com.unal.lab_0.Persistence.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PERSONA")
@Getter
@Setter
public class Persona {
    @Id
    @Column(name = "di")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "sexo")
    private String sexo;

    /* ----- RELATIONSHIPS ----- */

    @OneToMany(mappedBy = "cabezaDeFamilia", cascade = CascadeType.ALL)
    private List<Persona> personasACargo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CdF_di", referencedColumnName = "di")
    private Persona cabezaDeFamilia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mun", referencedColumnName = "id_mun")
    private Municipio municipio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_viv", referencedColumnName = "id_viv")
    private Vivienda vivienda;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PROPIEDAD",
            joinColumns = @JoinColumn(name = "di"),
            inverseJoinColumns = @JoinColumn(name = "id_viv"))
    private List<Vivienda> propiedades;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String telefono, Integer edad, String sexo, List<Persona> personasACargo, Persona cabezaDeFamilia, Municipio municipio, Vivienda vivienda, List<Vivienda> propiedades) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.sexo = sexo;
        this.personasACargo = personasACargo;
        this.cabezaDeFamilia = cabezaDeFamilia;
        this.municipio = municipio;
        this.vivienda = vivienda;
        this.propiedades = propiedades;
    }

    @Override
    public final String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", edad=" + edad +
                ", sexo='" + sexo + '\'' +
                ", personasACargo=" + personasACargo +
                ", cabezaDeFamilia=" + cabezaDeFamilia +
                ", municipio=" + municipio +
                ", vivienda=" + vivienda +
                ", propiedades=" + propiedades +
                '}';
    }
}
