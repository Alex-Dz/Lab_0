package com.unal.lab_0.Persistence.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "MUNICIPIO")
@Getter
@Setter
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public final String toString() {
        return "Municipio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", area=" + area +
                ", presupuesto=" + presupuesto +
                ", persona=" + persona +
                ", viviendas=" + viviendas +
                '}';
    }
}
