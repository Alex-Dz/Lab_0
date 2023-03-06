package com.unal.lab_0.Persistence.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "VIVIENDA")
@Getter
@Setter
public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public final String toString() {
        return "Vivienda{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", capacidad=" + capacidad +
                ", niveles=" + niveles +
                ", municipio=" + municipio +
                ", habitantes=" + habitantes +
                ", propietarios=" + propietarios +
                '}';
    }
}
