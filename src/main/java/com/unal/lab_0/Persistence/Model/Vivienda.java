package com.unal.lab_0.Persistence.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VIVIENDA")
@Data
public class Vivienda {

    @Id
    @Column(name = "id_viv")
    private Integer id;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "capacidad")
    private Integer capacidad;
    @Column(name = "niveles")
    private Integer niveles;

    /* ----- RELATIONSHIPS ----- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mun", referencedColumnName = "id_mun")
    private Municipio municipio;

    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.ALL)
    private List<Persona> habitantes;

    @ManyToMany(mappedBy = "propiedades")
    List<Persona> propietarios;
}
