
package com.Grupo6.ReclutamientoEmpleados.Entidades;

import com.Grupo6.ReclutamientoEmpleados.Enums.Rol;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre_usuario;
    
    private String contrasenha;
    
    private Rol rol;
//    
//    @OneToOne
//    private Empleador empleador;
//    
//    @OneToOne
//    private Empleado empleado;

    public Usuario() {
    }

    public Usuario(String id, String nombre_usuario, String contrasenha, Rol rol, Empleador empleador, Empleado empleado) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.contrasenha = contrasenha;
        this.rol = rol;
//        this.empleador = empleador;
//        this.empleado = empleado;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

//    public Empleador getEmpleador() {
//        return empleador;
//    }
//
//    public void setEmpleador(Empleador empleador) {
//        this.empleador = empleador;
//    }
//
//    public Empleado getEmpleado() {
//        return empleado;
//    }
//
//    public void setEmpleado(Empleado empleado) {
//        this.empleado = empleado;
//    }
    
    
    
    
    
}
