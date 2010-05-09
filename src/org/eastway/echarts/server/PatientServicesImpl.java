package org.eastway.echarts.server;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.client.PatientServices;
import org.eastway.echarts.domain.EHR;
import org.eastway.echarts.domain.EHRService;
import org.eastway.echarts.domain.Patient;
import org.eastway.echarts.domain.PatientService;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.PatientDTO;
import org.eastway.echarts.shared.SessionExpiredException;

@SuppressWarnings("serial")
public class PatientServicesImpl extends RpcServicesImpl implements PatientServices {
	@Override
	public PatientDTO getPatient(long patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		PatientService patientService = new PatientService(em);
		Patient patient = patientService.find(patientId);
		em.close();
		emf.close();
		return patient.toDto();
	}

	@Override
	public LinkedHashMap<String, Long> getPatientList(String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		LinkedHashMap<String, Long> pl = new LinkedHashMap<String, Long>();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		EHRService service = new EHRService(em);
		List<EHR> ehrList = service.findAll();
		for (EHR ehr : ehrList)
			pl.put(ehr.getSubject().getCaseNumber() + " - "
					+ ehr.getSubject().getName(), ehr.getId());
		em.close();
		emf.close();
		return pl;
	}
}
