package tn.esprit.spring.kaddem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

// FIRST STEP IS SETUP
@RunWith(MockitoJUnitRunner.class)
//a way to reduce boilerplate code and to ensure the framework is used correctly
public class UniversiteServiceImplTest {

    // SECOND STEP IS  Mock Dependencies
    @Mock
    private UniversiteRepository universiteRep;

    @Mock
    private DepartementRepository departementRep;

    @InjectMocks
    // creates an instance of the class
    private UniversiteServiceImpl universiteS;

    /*@Before
// this is replaced by the code in top
    public void setup() {
        universiteService = new UniversiteServiceImpl();
        universiteService.setUniversiteRepository(universiteRepository);  // Assuming you have setters; if not, you might consider adding them.
        universiteService.setDepartementRepository(departementRepository);  // Again, assuming you have a setter for this.
    }*/
    // the THIRD STEP IS TO  Write Test Methods
    @Test
    public void testRetrieveUniversite() {
        // 1. Setup:
        Universite sampleUniversite = new Universite(); // Assuming you have a default constructor
        Integer sampleId = 1;

        // 2. Mocking Behavior:
        Mockito.when(universiteRep.findById(sampleId))
                .thenReturn(Optional.of(sampleUniversite));

        // 3. Invocation:
        Universite result = universiteS.retrieveUniversite(sampleId);

        // 4. Verification:
        Assert.assertEquals(sampleUniversite, result);
        Mockito.verify(universiteRep, Mockito.times(1)).findById(sampleId);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRetrieveUniversiteNotFound() {
        Integer sampleId = 2;

        Mockito.when(universiteRep.findById(sampleId))
                .thenReturn(Optional.empty());

        universiteS.retrieveUniversite(sampleId); // This should throw an exception
    }
    @Test
    public void testDeleteUniversite() {
        // 1. Setup:
        Universite sampleUniversite = new Universite();
        Integer sampleId = 1;

        Mockito.when(universiteRep.findById(sampleId))
                .thenReturn(Optional.of(sampleUniversite));

        // 2. Invocation:
        universiteS.deleteUniversite(sampleId);

        // 3. Verification:
        Mockito.verify(universiteRep, Mockito.times(1)).delete(sampleUniversite);
    }
    @Test
    public void testRetrieveDepartementsByUniversite() {
        // 1. Setup:
        Universite sampleUniversite = new Universite();
        Set<Departement> sampleDepartements = new HashSet<>();
        // Add some sample departements to the set if necessary
        sampleUniversite.setDepartements(sampleDepartements);
        Integer sampleId = 1;

        // 2. Mocking Behavior:
        Mockito.when(universiteRep.findById(sampleId))
                .thenReturn(Optional.of(sampleUniversite));

        // 3. Invocation:
        Set<Departement> result = universiteS.retrieveDepartementsByUniversite(sampleId);

        // 4. Verification:
        Assert.assertEquals(sampleDepartements, result);
        Mockito.verify(universiteRep, Mockito.times(1)).findById(sampleId);
    }

    // 5. Edge Case (when the Universite is not found):
    @Test
    public void testRetrieveDepartementsByUniversiteNotFound() {
        Integer sampleId = 2;

        Mockito.when(universiteRep.findById(sampleId))
                .thenReturn(Optional.empty());

        Set<Departement> result = universiteS.retrieveDepartementsByUniversite(sampleId);
        Assert.assertTrue(result.isEmpty());  // Because your method returns null when no Universite is found.
    }
    @Test
    public void testAssignUniversiteToDepartement() {
        // 1. Setup:
        Universite sampleUniversite = new Universite();
        Set<Departement> departements = new HashSet<>();
        sampleUniversite.setDepartements(departements);

        Departement sampleDepartement = new Departement();
        Integer sampleIdUniversite = 1;
        Integer sampleIdDepartement = 1;

        // 2. Mocking Behavior:
        Mockito.when(universiteRep.findById(sampleIdUniversite))
                .thenReturn(Optional.of(sampleUniversite));
        Mockito.when(departementRep.findById(sampleIdDepartement))
                .thenReturn(Optional.of(sampleDepartement));
        Mockito.doAnswer(invocation -> null).when(universiteRep).save(Mockito.any(Universite.class));


        // 3. Invocation:
        universiteS.assignUniversiteToDepartement(sampleIdUniversite, sampleIdDepartement);

        // 4. Verification:
        Assert.assertTrue(sampleUniversite.getDepartements().contains(sampleDepartement));
        Mockito.verify(universiteRep, Mockito.times(1)).findById(sampleIdUniversite);
        Mockito.verify(departementRep, Mockito.times(1)).findById(sampleIdDepartement);
        Mockito.verify(universiteRep, Mockito.times(1)).save(sampleUniversite);
    }
    @Test(expected = NullPointerException.class)
    public void testAssignUniversiteToDepartement_NullPointerException() {
        // Given: No Universite with the provided id
        Integer sampleIdUniversite = 1;
        Integer sampleIdDepartement = 1;

        Mockito.when(universiteRep.findById(sampleIdUniversite))
                .thenReturn(Optional.empty());  // This means Universite not found

        Mockito.when(departementRep.findById(sampleIdDepartement))
                .thenReturn(Optional.of(new Departement()));  // Still returns a Departement for completeness, but you can mock it to return empty as well if needed.

        // When: Trying to assign a Departement to a non-existent Universite
        universiteS.assignUniversiteToDepartement(sampleIdUniversite, sampleIdDepartement);

        // Then: Expect a NullPointerException
    }


}