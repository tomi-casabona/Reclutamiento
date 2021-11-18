package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.FotoRepositorio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional
    public Foto guardar(MultipartFile archivo) throws ErrorWeb, IOException {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
             

                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (IOException e) {
                throw new IOException("No se ha podido subir su foto "+e.getMessage());
            }
        }else{
            throw new ErrorWeb("La foto no puede ser nula");
        }
                
    }

    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo) throws Exception {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }}
            return null;

        }
        
       
    }
