package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.LigneEcritureComptable;
import com.intech.comptabilite.repositories.EcritureComptableRepository;

@SpringBootTest
public class EcritureComptableServiceTest {

	@Autowired
	private EcritureComptableService ecritureComptableService;

	@MockBean
	private EcritureComptableRepository h;

	private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
		BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
		BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
		String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
				.subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
		LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
				vLibelle, vDebit, vCredit);
		return vRetour;
	}

	@Test
	public void getEcritureComptableByRefNoFound() {
		// Arrange
		EcritureComptable ecritureComptableByRef = new EcritureComptable();

		boolean haveError = false;

		when(h.getByReference("BQ-2016/00001")).thenReturn(null);

		// Act
		try {
			ecritureComptableByRef = ecritureComptableService.getEcritureComptableByRef("BQ-2016/00001");
		} catch (Exception e) {
			haveError = true;
		}

		// Assert
		assertTrue(haveError);
		verify(h).getByReference("BQ-2016/00001");
	}

	@Test
	public void getEcritureComptableByRefFound() {
		// Arrange
		EcritureComptable ecritureComptable = new EcritureComptable();
		EcritureComptable ecritureComptableByRef = new EcritureComptable();

		boolean haveError = false;

		ecritureComptable.setReference("BQ-2016/00001");

		when(h.getByReference("BQ-2016/00001")).thenReturn(Optional.of(ecritureComptable));

		// Act
		try {
			ecritureComptableByRef = ecritureComptableService.getEcritureComptableByRef("BQ-2016/00001");
		} catch (Exception e) {
			haveError = true;
		}

		// Assert
		assertEquals("BQ-2016/00001", ecritureComptableByRef.getReference());
		assertFalse(haveError);
		verify(h).getByReference("BQ-2016/00001");
	}

	@Test
	public void getTotalCredit() {
		// Arrange
		BigDecimal totalCreditPrevisu = new BigDecimal("66");
		BigDecimal totalCredit;

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "152", "33"));

		// Act
		totalCredit = ecritureComptableService.getTotalCredit(ecritureComptable);

		// Asset
		assertTrue(totalCreditPrevisu.compareTo(totalCredit) == 0);

	}

	@Test
	public void getTotalCreditNo() {
		// Arrange
		BigDecimal totalCreditPrevisu = new BigDecimal("254");
		BigDecimal totalCredit;

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "152", "33"));

		// Act
		totalCredit = ecritureComptableService.getTotalCredit(ecritureComptable);

		// Asset
		assertFalse(totalCreditPrevisu.compareTo(totalCredit) == 0);

	}

	@Test
	public void getTotalDebit() {
		// Arrange
		BigDecimal totalCreditPrevisu = new BigDecimal("453");
		BigDecimal totalDebit;

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "152", "33"));

		// Act
		totalDebit = ecritureComptableService.getTotalDebit(ecritureComptable);

		// Asset
		assertTrue(totalCreditPrevisu.compareTo(totalDebit) == 0);

	}

	@Test
	public void getTotalDebitNo() {
		// Arrange
		BigDecimal totalDebitPrevisu = new BigDecimal("454");
		BigDecimal totalDebit;

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "152", "33"));

		// Act
		totalDebit = ecritureComptableService.getTotalDebit(ecritureComptable);

		// Asset
		assertFalse(totalDebitPrevisu.compareTo(totalDebit) == 0);

	}

	@Test
	public void isEquilibree() {
		EcritureComptable vEcriture;
		vEcriture = new EcritureComptable();

		vEcriture.setLibelle("Equilibrée");
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
		Assertions.assertTrue(ecritureComptableService.isEquilibree(vEcriture));

		vEcriture.getListLigneEcriture().clear();
		vEcriture.setLibelle("Non équilibrée");
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
		Assertions.assertFalse(ecritureComptableService.isEquilibree(vEcriture));
	}

}
