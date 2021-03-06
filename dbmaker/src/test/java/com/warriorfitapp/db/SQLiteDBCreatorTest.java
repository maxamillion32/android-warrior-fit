package com.warriorfitapp.db;

import com.warriorfitapp.config.Config;
import com.warriorfitapp.config.ConfigReaderException;
import com.warriorfitapp.config.xml.XMLConfigReader;
import com.warriorfitapp.db.sqlite.SQLiteDBCreator;

import com.google.common.io.Resources;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;

/**
 * @author Andrii Kovalov
 */
public class SQLiteDBCreatorTest {
    public static final Logger LOG = LoggerFactory.getLogger(SQLiteDBCreatorTest.class);

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void testCreateDatabase() throws Exception {
        LOG.debug("testCreateDatabase");

        Config config = readConfig();

        SQLiteDBCreator dbCreator = new SQLiteDBCreator();

        // File file = testFolder.newFile("cyberfit_test.db");
        File file = new File("build/db/cyberfit_test.db");

        LOG.debug("DB file: " + file.getAbsolutePath());

        dbCreator.createDatabase(config, com.warriorfitapp.db.sqlite.schema.DBSchema.defaultSchema(), Paths.get(file.toURI()));
    }

    private Config readConfig() throws ConfigReaderException, IOException {
        URL resource = Resources.getResource("default_config.xml");
        assertNotNull(resource);
        XMLConfigReader configReader = new XMLConfigReader();
        Config config = configReader.readConfig(resource.openStream());
        assertNotNull(config);
        return config;
    }
}