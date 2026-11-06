package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    /**
     * Prueba de búsqueda por ID
     */
    @Test
    public void testFindVetById() {
        Integer ID = 1;
        String EXPECTED_NAME = "James";

        try {
            Vet vet = vetService.findById(ID);
            log.info("Veterinario encontrado: {}", vet);
            assertEquals(EXPECTED_NAME, vet.getFirstName());
        } catch (VetNotFoundException e) {
            fail("No se encontró el veterinario: " + e.getMessage());
        }
    }

    /**
     * Prueba de creación de un nuevo veterinario
     */
    @Test
    public void testCreateVet() {
        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Perez";

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        Vet vetCreated = vetService.create(vet);

        log.info("Veterinario creado: {}", vetCreated);

        assertNotNull(vetCreated.getId());
        assertEquals(FIRST_NAME, vetCreated.getFirstName());
        assertEquals(LAST_NAME, vetCreated.getLastName());
    }

    /**
     * Prueba de actualización de un veterinario
     */
    @Test
    public void testUpdateVet() {
        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Perez";
        String UP_LAST_NAME = "Gomez";

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        Vet vetCreated = vetService.create(vet);

        vetCreated.setLastName(UP_LAST_NAME);
        Vet vetUpdated = vetService.update(vetCreated);

        log.info("Veterinario actualizado: {}", vetUpdated);

        assertEquals(UP_LAST_NAME, vetUpdated.getLastName());
    }

    /**
     * Prueba de eliminación de un veterinario
     */
    @Test
    public void testDeleteVet() {
        String FIRST_NAME = "Ana";
        String LAST_NAME = "Torres";

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        Vet vetCreated = vetService.create(vet);

        vetService.delete(vetCreated.getId());

        try {
            vetService.findById(vetCreated.getId());
            fail("El veterinario no debería existir");
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }
}