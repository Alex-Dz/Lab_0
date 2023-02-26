package com.unal.lab_0.Persistence.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "MUNICIPIO")
@Data
public class Municipio {

    @Id
    @Column(name = "id_mun")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "area")
    private Double area;
    @Column(name = "presupuesto")
    private Integer presupuesto;

    /* ----- RELATIONSHIPS ----- */
    @OneToOne(mappedBy = "municipio")
    private Persona persona;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL)
    private List<Vivienda> viviendas;
}
