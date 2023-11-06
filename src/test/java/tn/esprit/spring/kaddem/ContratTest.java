package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContratTest {

    @Test
    public void testCreateContrat() {
        Date startDate = new Date();
        Date endDate = new Date();
        Specialite specialite = Specialite.IA;
        Boolean archive = false;
        Integer montantContrat = 1000;

        Contrat contrat = new Contrat(startDate, endDate, specialite, archive, montantContrat);

        assertNotNull(contrat);
        assertEquals(startDate, contrat.getDateDebutContrat());
        assertEquals(endDate, contrat.getDateFinContrat());
        assertEquals(specialite, contrat.getSpecialite());
        assertEquals(archive, contrat.getArchive());
        assertEquals(montantContrat, contrat.getMontantContrat());
    }

    @Test
    public void testUpdateContrat() {
        Date startDate = new Date();
        Date endDate = new Date();
        Specialite specialite = Specialite.CLOUD;
        Boolean archive = true;
        Integer montantContrat = 2000;

        Contrat contrat = new Contrat(startDate, endDate, specialite, archive, montantContrat);

        // Update the Contrat
        Date newStartDate = new Date();
        Date newEndDate = new Date();
        Specialite newSpecialite = Specialite.RESEAUX;
        Boolean newArchive = false;
        Integer newMontantContrat = 1500;

        contrat.setDateDebutContrat(newStartDate);
        contrat.setDateFinContrat(newEndDate);
        contrat.setSpecialite(newSpecialite);
        contrat.setArchive(newArchive);
        contrat.setMontantContrat(newMontantContrat);

        assertEquals(newStartDate, contrat.getDateDebutContrat());
        assertEquals(newEndDate, contrat.getDateFinContrat());
        assertEquals(newSpecialite, contrat.getSpecialite());
        assertEquals(newArchive, contrat.getArchive());
        assertEquals(newMontantContrat, contrat.getMontantContrat());
    }
}