import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.filespec.PdfFileSpec;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SupTest {

    public static final String srcFolder = "./src/main/resources/";
    public static void createAttachment(String filename, PdfDocument pdfDoc, List<InputStream> inputStreamList) throws IOException {
        InputStream inputStream = new FileInputStream(filename);
        PdfFileSpec spec = PdfFileSpec.createEmbeddedFileSpec(pdfDoc,
                inputStream, "Attachement", "some_name", null, null, false);
        pdfDoc.addFileAttachment("some_name", spec);
        inputStreamList.add(inputStream);
    }

    public static void main(String args[]) throws IOException{

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(new ByteOutputStream()));
        PdfPage page1 = pdfDoc.addNewPage();

        List<String> filenameList = new ArrayList<String>();
        for(int i = 0; i <10; i++) {
            filenameList.add( srcFolder + "sample_mine_" + i + ".pdf");
        }

        List<InputStream> inputStreamList = new ArrayList<InputStream>();

        for(String filename : filenameList){
            createAttachment(filename, pdfDoc, inputStreamList);
        }
        pdfDoc.close();
        for(InputStream inputStream:inputStreamList){//Closing input streams here because itext start reading file at document.close();
            if(inputStream!=null) inputStream.close();
        }
    }
}
