package com.tecsup.petclinic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Integer id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public Specialty findById(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findById(id);
        if (!specialty.isPresent()) {
            throw new SpecialtyNotFoundException("Especialidad no encontrada con ID: " + id);
        }
        return specialty.get();
    }
}