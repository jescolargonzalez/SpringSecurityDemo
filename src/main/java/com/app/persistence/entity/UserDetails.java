package com.app.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "telefono" , unique = true , nullable = false)
    private String telefono;

    @Column(name = "direccion" , nullable = false)
    private String direccion;

    @Column(name = "pais" , nullable = false)
    private String pais;

    @Column(name = "codigo_postal",nullable = false)
    private String codigoPostal;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "iban", nullable = false, unique = true)
    private String iban;

    @OneToOne
    @JoinColumn(name = "user_entity_id", referencedColumnName = "id")
    private UserEntity userEntity;

}