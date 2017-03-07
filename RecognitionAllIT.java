/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.javaanpr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
@RunWith(Parameterized.class)
public class RecognitionAllIT {
        
    private File plateFile;
    private String expectedPlate;
    
        public RecognitionAllIT(File plateFile, String expectedPlate) {
        this.plateFile = plateFile;
        this.expectedPlate = expectedPlate;
    
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Parameterized.Parameters
public static Collection<Object[]> testDataCreator() throws IOException {
    
        String snapshotDirPath = "src/test/resources/snapshots";
        String resultsPath = "src/test/resources/results.properties";
        InputStream resultsStream = new FileInputStream(new File(resultsPath));
        
        Properties properties = new Properties();
        properties.load(resultsStream);
        resultsStream.close();
        assertTrue(properties.size() > 0);
        
        
        File snapshotDir = new File(snapshotDirPath);
        File[] snapshots = snapshotDir.listFiles();
        
  Collection<Object[]> dataForOneImage= new ArrayList();
  for (File file : snapshots) {
      String name = file.getName();
      String plateExpected = properties.getProperty(name);
      dataForOneImage.add(new Object[]{file, plateExpected });
  }
  return dataForOneImage;
}


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testAllSnaps() throws Exception {
         Intelligence intel = new Intelligence();
        assertNotNull(intel);
        
        CarSnapshot car = new CarSnapshot(new FileInputStream(plateFile));
        String rec = intel.recognize(car);
         assertThat(expectedPlate,equalTo(rec));
        
     
     
     }

    
}
