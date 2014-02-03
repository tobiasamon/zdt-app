package de.tam.web.entries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EntryDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(EntryModel entry) {
		entityManager.persist(entry);
	}

	@SuppressWarnings("unchecked")
	public List<EntryModel> list() {
		return entityManager.createQuery("select e from Entry e")
				.getResultList();
	}

}
