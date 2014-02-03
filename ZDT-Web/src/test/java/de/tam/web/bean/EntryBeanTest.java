package de.tam.web.bean;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.tam.web.entries.EntryBean;
import de.tam.web.entries.EntryModel;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EntryBeanTest {

	@Autowired
	private EntryBean entryBean;

	@Test
	public void shouldGetCorrectMessage() {
		String message = entryBean.getMessage();
		Assert.assertEquals("Hello from Spring", message);
	}

	@Test
	public void shouldSaveTaskAndResetTaskInstanceOnBean() {
		EntryModel oldTask = entryBean.getEntry();
		entryBean.getEntry().setDescription("Sample Description");
		entryBean.saveEntry();
		Assert.assertNotNull("Saved task ID is null,probably not saved",
				oldTask.getId());
		Assert.assertNull("Task has not been reset", entryBean.getEntry()
				.getDescription());
		Assert.assertNull("Task has not been reset", entryBean.getEntry()
				.getId());
		Assert.assertNotSame("Task object has not been replaced", oldTask,
				entryBean.getEntry());
	}
}
