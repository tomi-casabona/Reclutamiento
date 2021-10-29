package com.Grupo6.ReclutamientoEmpleados.Entidades;

import com.Grupo6.ReclutamientoEmpleados.Enums.CarnetConducir;
import com.Grupo6.ReclutamientoEmpleados.Enums.DisponibilidadHoraria;
import com.Grupo6.ReclutamientoEmpleados.Enums.MovilidadPropia;
import com.Grupo6.ReclutamientoEmpleados.Enums.PosibleReubicacion;
import com.Grupo6.ReclutamientoEmpleados.Enums.SecundarioCompleto;
import com.Grupo6.ReclutamientoEmpleados.Enums.Sexo;
import com.Grupo6.ReclutamientoEmpleados.Enums.TerciarioCompleto;
import com.Grupo6.ReclutamientoEmpleados.Enums.UniversitarioCompleto;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Empleado extends Usuario {
    
    private String nombre;
    private String apellido;
    private Date fechaNac;
   
    @OneToMany
    private List<Categoria> categorias;
    
    private Sexo sexo;
    private SecundarioCompleto secundarioCompleto;
    private TerciarioCompleto TerciarioCompleto;
    private UniversitarioCompleto universitarioCompleto;
    private DisponibilidadHoraria disponibilidadHoraria;
    private MovilidadPropia movilidadPropia;
    private PosibleReubicacion posiblereubucacion;
    private CarnetConducir carnetConducir;
    private String email;
    private String numeroTelefonico;
    private String experienciaLaboral;    
    private String otrosDatos;

    public Empleado() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public SecundarioCompleto getSecundarioCompleto() {
        return secundarioCompleto;
    }

    public void setSecundarioCompleto(SecundarioCompleto secundarioCompleto) {
        this.secundarioCompleto = secundarioCompleto;
    }

    public TerciarioCompleto getTerciarioCompleto() {
        return TerciarioCompleto;
    }

    public void setTerciarioCompleto(TerciarioCompleto TerciarioCompleto) {
        this.TerciarioCompleto = TerciarioCompleto;
    }

    public UniversitarioCompleto getUniversitarioCompleto() {
        return universitarioCompleto;
    }

    public void setUniversitarioCompleto(UniversitarioCompleto universitarioCompleto) {
        this.universitarioCompleto = universitarioCompleto;
    }

    public DisponibilidadHoraria getDisponibilidadHoraria() {
        return disponibilidadHoraria;
    }

    public void setDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHoraria = disponibilidadHoraria;
    }

    public MovilidadPropia getMovilidadPropia() {
        return movilidadPropia;
    }

    public void setMovilidadPropia(MovilidadPropia movilidadPropia) {
        this.movilidadPropia = movilidadPropia;
    }

    public PosibleReubicacion getPosiblereubucacion() {
        return posiblereubucacion;
    }

    public void setPosiblereubucacion(PosibleReubicacion posiblereubucacion) {
        this.posiblereubucacion = posiblereubucacion;
    }

    public CarnetConducir getCarnetConducir() {
        return carnetConducir;
    }

    public void setCarnetConducir(CarnetConducir carnetConducir) {
        this.carnetConducir = carnetConducir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        this.otrosDatos = otrosDatos;
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    
    
}
