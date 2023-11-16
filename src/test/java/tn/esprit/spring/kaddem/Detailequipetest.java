package tn.esprit.spring.kaddem;



import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tn.esprit.spring.kaddem.entities.Niveau.JUNIOR;


class Detailequipetest {

    @Mock
    private EquipeRepository equipeReposito;

    @InjectMocks
    private EquipeServiceImpl equipeservice;

    public Detailequipetest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEquipe() {
        // Arrange

        Equipe p = new Equipe ("equipe1", JUNIOR);
        when(equipeReposito.save(p)).thenReturn(p);

        // Act
        Equipe eq = equipeservice.addEquipe(p);

        // Assert
        assertNotNull(eq);
        assertEquals("New equipe", eq.getNomEquipe());
        assertEquals(JUNIOR, eq.getNiveau());

    }
    @Test
    void testRetrieveEquipe() {

        Equipe p = new Equipe (1,"equipe1", JUNIOR);

        when(equipeReposito.findById(1)).thenReturn(Optional.of(p));

        Equipe result = equipeservice.retrieveEquipe(1);

        assertEquals(p, result);
        assertEquals("equipe1", result.getNomEquipe());
    }


    @Test
    void testAddPiste_NotFound() {
        // Arrange
        Equipe p = new Equipe (1,"equipe1", JUNIOR);

        when(equipeReposito.save(p)).thenReturn(null);

        // Act
        Equipe result = equipeservice.addEquipe(p);

        // Assert
        assertNull(result);
    }

    @Test
    void testRetrieveAllEquips() {
        // Arrange
        List<Equipe> equipeList = new ArrayList<>();
        equipeList.add(new Equipe   (1,"equipe1", JUNIOR));
        equipeList.add(new Equipe   (3,"equipe3", JUNIOR));

        when(equipeReposito.findAll()).thenReturn(equipeList);

        // Act
        List<Equipe> result = equipeservice.retrieveAllEquipes();

        // Assert
        assertEquals(2, result.size());
        assertEquals("equipe1", result.get(0).getNomEquipe());
        assertEquals("equipe3", result.get(1).getNomEquipe());
    }

    // Other test methods...

    @Test
    void testDeleteEquipe() {
        // Arrange

        Equipe p = new Equipe (1,"equipe1", JUNIOR);
        when(equipeReposito.findById(1)).thenReturn(Optional.of(p));

        // Act
        equipeservice.deleteEquipe(1);

        // Assert
        verify(equipeReposito, times(1)).deleteById(1);
    }

 /*   @Test
    public void testRemovePiste_NotFound() {
        // Arrange
        Long numPiste = 4L;
        Piste piste = new Piste(numPiste, "P2", Color.BLACK, 1, 7);
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        // Act
        pisteServices.removePiste(numPiste);

        // Assert
        verify(pisteRepository, never()).deleteById(numPiste);
    }


  */
}


