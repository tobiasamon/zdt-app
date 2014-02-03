package de.tam.web.entries;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("entryBean")
public class EntryBean {

	private static final Logger logger = LoggerFactory.getLogger(EntryBean.class);

	private EntryModel entry = new EntryModel();
	private List<EntryModel> entries;

	@Autowired
	private EntryDao entryDao;

	
	public String getMessage() {
		logger.debug("Returning message from task home bean");
		return "Hello from Spring";
	}	

	public EntryModel getEntry() {
		return entry;
	}

	public void saveEntry() {
		entryDao.save(entry);
		entry = new EntryModel();
		invalidateEntries();
	}

	private void invalidateEntries() {
		entries = null;
	}

	public List<EntryModel> getEntries() {
		if (entries == null) {
			entries = entryDao.list();
		}
		return entries;
		
	}
}
