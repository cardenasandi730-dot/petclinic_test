package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    /**
     * Búsqueda por ID
     */
    @Test
    public void testFindSpecialtyById() {
        Integer ID = 1;
        String EXPECTED_NAME = "radiology";

        try {
            Specialty specialty = specialtyService.findById(ID);
            log.info("Especialidad encontrada: {}", specialty);
            assertEquals(EXPECTED_NAME, specialty.getName());
        } catch (SpecialtyNotFoundException e) {
            fail("No se encontró la especialidad: " + e.getMessage());
        }
    }

    /**
     * Creación de una nueva especialidad
     */
    @Test
    public void testCreateSpecialty() {
        String NAME = "dermatology";

        Specialty specialty = new Specialty(NAME);
        Specialty specialtyCreated = specialtyService.create(specialty);

        log.info("Especialidad creada: {}", specialtyCreated);

        assertNotNull(specialtyCreated.getId());
        assertEquals(NAME, specialtyCreated.getName());
    }

    /**
     * Actualización de una especialidad
     */
    @Test
    public void testUpdateSpecialty() {
        String NAME = "cardiology";
        String UP_NAME = "advanced cardiology";

        Specialty specialty = new Specialty(NAME);
        Specialty specialtyCreated = specialtyService.create(specialty);

        specialtyCreated.setName(UP_NAME);
        Specialty specialtyUpdated = specialtyService.update(specialtyCreated);

        log.info("Especialidad actualizada: {}", specialtyUpdated);

        assertEquals(UP_NAME, specialtyUpdated.getName());
    }

    /**
     * Eliminación de una especialidad
     */
    @Test
    public void testDeleteSpecialty() {
        String NAME = "neurology";

        Specialty specialty = new Specialty(NAME);
        Specialty specialtyCreated = specialtyService.create(specialty);

        specialtyService.delete(specialtyCreated.getId());

        try {
            specialtyService.findById(specialtyCreated.getId());
            fail("La especialidad no debería existir");
        } catch (SpecialtyNotFoundException e) {
            assertTrue(true);
        }
    }
}