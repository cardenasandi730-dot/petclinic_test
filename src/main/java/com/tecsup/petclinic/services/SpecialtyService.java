package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;

public interface SpecialtyService {

    Specialty create(Specialty specialty);

    Specialty update(Specialty specialty);

    void delete(Integer id);

    Specialty findById(Integer id) throws SpecialtyNotFoundException;
}