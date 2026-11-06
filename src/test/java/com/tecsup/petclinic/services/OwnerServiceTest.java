package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    /**
     * Búsqueda por ID
     */
    @Test
    public void testFindOwnerById() {
        Integer ID = 1;
        String EXPECTED_NAME = "George";

        try {
            Owner owner = ownerService.findById(ID);
            log.info("Dueño encontrado: {}", owner);
            assertEquals(EXPECTED_NAME, owner.getFirstName());
        } catch (OwnerNotFoundException e) {
            fail("No se encontró el dueño: " + e.getMessage());
        }
    }

    /**
     * Creación de un nuevo dueño
     */
    @Test
    public void testCreateOwner() {
        String FIRST_NAME = "Juan";
        String LAST_NAME = "Perez";
        String ADDRESS = "Av. Arequipa 123";
        String CITY = "Lima";
        String TELEPHONE = "987654321";

        Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
        Owner ownerCreated = ownerService.create(owner);

        log.info("Dueño creado: {}", ownerCreated);

        assertNotNull(ownerCreated.getId());
        assertEquals(FIRST_NAME, ownerCreated.getFirstName());
        assertEquals(LAST_NAME, ownerCreated.getLastName());
    }

    /**
     * Actualización de un dueño
     */
    @Test
    public void testUpdateOwner() {
        String FIRST_NAME = "Juan";
        String LAST_NAME = "Perez";
        String UP_CITY = "Arequipa";

        Owner owner = new Owner(FIRST_NAME, LAST_NAME, "Calle 1", "Lima", "123456789");
        Owner ownerCreated = ownerService.create(owner);

        ownerCreated.setCity(UP_CITY);
        Owner ownerUpdated = ownerService.update(ownerCreated);

        log.info("Dueño actualizado: {}", ownerUpdated);

        assertEquals(UP_CITY, ownerUpdated.getCity());
    }

    /**
     * Eliminación de un dueño
     */
    @Test
    public void testDeleteOwner() {
        String FIRST_NAME = "Maria";
        String LAST_NAME = "Lopez";

        Owner owner = new Owner(FIRST_NAME, LAST_NAME, "Av. Central 456", "Cusco", "912345678");
        Owner ownerCreated = ownerService.create(owner);

        ownerService.delete(ownerCreated.getId());

        try {
            ownerService.findById(ownerCreated.getId());
            fail("El dueño no debería existir");
        } catch (OwnerNotFoundException e) {
            assertTrue(true);
        }
    }
}