package tn.esprit.spring.kaddem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class UniversiteServiceImplMockTest {

    @Mock
    private UniversiteRepository universityRep;

    @Mock
    private DepartementRepository departmentRep;

    @InjectMocks

    private UniversiteServiceImpl universityS;

    @Test
    public void testRetrieveUniversite() {

        Universite sampleUniversite = new Universite();
        Integer sampleId = 1;

        when(universityRep.findById(sampleId))
                .thenReturn(Optional.of(sampleUniversite));


        Universite result = universityS.retrieveUniversite(sampleId);


        assertEquals(sampleUniversite, result);
        verify(universityRep, times(1)).findById(sampleId);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRetrieveUniversiteNotFound() {
        Integer sampleId = 2;

        when(universityRep.findById(sampleId))
                .thenReturn(Optional.empty());

        universityS.retrieveUniversite(sampleId);
    }
    @Test
    public void testDeleteUniversite() {

        Universite sampleUniversite = new Universite();
        Integer sampleId = 1;

        when(universityRep.findById(sampleId))
                .thenReturn(Optional.of(sampleUniversite));


        universityS.deleteUniversite(sampleId);

        verify(universityRep, times(1)).delete(sampleUniversite);
    }
    @Test
    public void testRetrieveDepartementsByUniversite() {

        Universite sampleUniversite = new Universite();
        Set<Departement> sampleDepartments = new HashSet<>();
        sampleUniversite.setDepartements(sampleDepartments);
        Integer sampleId = 1;


        when(universityRep.findById(sampleId))
                .thenReturn(Optional.of(sampleUniversite));


        Set<Departement> result = universityS.retrieveDepartementsByUniversite(sampleId);


        assertEquals(sampleDepartments, result);
        verify(universityRep, times(1)).findById(sampleId);
    }

    @Test
    public void testRetrieveDepartementsByUniversiteNotFound() {
        Integer sampleId = 2;

        when(universityRep.findById(sampleId))
                .thenReturn(Optional.empty());

        Set<Departement> result = universityS.retrieveDepartementsByUniversite(sampleId);
        assertTrue(result.isEmpty());
    }
    @Test
    public void testAssignUniversiteToDepartement() {

        Universite sampleUniversite = new Universite();
        Set<Departement> departements = new HashSet<>();
        sampleUniversite.setDepartements(departements);

        Departement sampleDepartement = new Departement();
        Integer sampleIdUniversite = 1;
        Integer sampleIdDepartement = 1;


        when(universityRep.findById(sampleIdUniversite))
                .thenReturn(Optional.of(sampleUniversite));
        when(departmentRep.findById(sampleIdDepartement))
                .thenReturn(Optional.of(sampleDepartement));
        Mockito.doAnswer(invocation -> null).when(universityRep).save(Mockito.any(Universite.class));



        universityS.assignUniversiteToDepartement(sampleIdUniversite, sampleIdDepartement);


        assertTrue(sampleUniversite.getDepartements().contains(sampleDepartement));
        verify(universityRep, times(1)).findById(sampleIdUniversite);
        verify(departmentRep, times(1)).findById(sampleIdDepartement);
        verify(universityRep, times(1)).save(sampleUniversite);
    }
    @Test(expected = NullPointerException.class)
    public void testAssignUniversiteToDepartement_NullPointerException() {
        Integer sampleIdUniversity = 1;
        Integer sampleIdDepartement = 1;

        when(universityRep.findById(sampleIdUniversity))
                .thenReturn(Optional.empty());

        when(departmentRep.findById(sampleIdDepartement))
                .thenReturn(Optional.of(new Departement()));

        universityS.assignUniversiteToDepartement(sampleIdUniversity, sampleIdDepartement);


    }
    @Test
    public void testRetrieveAllUniversites() {

        List<Universite> mockedList = new ArrayList<>();
        mockedList.add(new Universite());
        when(universityRep.findAll()).thenReturn(mockedList);

        List<Universite> result = universityS.retrieveAllUniversites();


        verify(universityRep, times(1)).findAll();
        assertEquals(mockedList.size(), result.size());
    }

    @Test
    public void testRetrieveAllUniversites_Empty() {
        when(universityRep.findAll()).thenReturn(new ArrayList<>());
        List<Universite> result = universityS.retrieveAllUniversites();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddUniversite() {

        Universite mockUniversite = new Universite();
        when(universityRep.save(any(Universite.class))).thenReturn(mockUniversite);


        Universite result = universityS.addUniversite(new Universite());


        verify(universityRep, times(1)).save(any(Universite.class));
        assertEquals(mockUniversite, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUniversite() {
        universityS.addUniversite(null);
    }
    @Test
    public void testUpdateUniversite() {

        Universite mockUniversite = new Universite();
        when(universityRep.save(any(Universite.class))).thenReturn(mockUniversite);


        Universite result = universityS.updateUniversite(new Universite());


        verify(universityRep, times(1)).save(any(Universite.class));
        assertEquals(mockUniversite, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullUniversite() {
        universityS.updateUniversite(null);
    }

    @Test(expected = DataAccessException.class)
    public void testAddUniversite_DatabaseError() {
        Universite u = new Universite();
        when(universityRep.save(u)).thenThrow(new DataAccessException("DB error") {});
        universityS.addUniversite(u);
    }
}