package org.tech.innisfree.challanges;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

/**
 * Hello world!
 *
 */


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<Integer> arrayNumbers = new ArrayList<>();

        int arrayNum= 10;
        int result =0;

         for (int i = 1; i <= arrayNum -1; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                System.out.println(i);
                result=result+i;
                arrayNumbers.add(i);
            }
        }
         System.out.println(result);

//      try {
//        File file = new File("/home/kabelo-kgobane/Software/sarb_aem_implementation/Schemas/SampleXml/ProdBulkFiles/Prod_Bulk_C23_Taquanta.xml");
//        InputStream myScan = new FileInputStream(file);
//        byte[] b = new byte[(int)file.length()];
//        myScan.read(b);
//        String cowo = new String(b);
//        System.out.println(cowo);
//        Encoder encoder = Base64.getEncoder();
//        byte[] data = cowo.getBytes(UTF_8);
//        String encodedString = encoder.encodeToString(data);
//        System.out.println("Encoded: " + encodedString);
//        OutputStream out = new FileOutputStream("/home/kabelo-kgobane/eclipse-workspace/AEMLunchTimeLearningProjects/JavaChallanges/challanges/src/main/java/org/tech/innisfree/challanges/test.txt");
//        out.write(encodedString.getBytes());
//        out.close();
//      } catch (IOException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
    }

}
