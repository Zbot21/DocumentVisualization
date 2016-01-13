/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Chris Bellis, Chris Perry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package pdfs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Chris on 8/19/2015.
 * Simple utility class for extracting text from a PDF
 */
public class PDFTextExtractor {
    private static final Log log = LogFactory.getLog(PDFTextExtractor.class);
    // Instantiate this so that we can just have it accessable
    private PDFTextStripper stripper;
    public PDFTextExtractor(){
        try{
            stripper = new PDFTextStripper();
        }catch (IOException e){
            log.error("Could not create PDF Text Stripper", e);
        }

    }


    /**
     * Given a PDF file, gets all the text from it
     *
     * @param filename The filename to get all the text from
     * @return The fulltext of the file
     * @throws IOException
     */
    public String extractText(String filename) throws IOException {
        PDDocument document = getPDDocument(filename);
        String res = stripper.getText(document);
        document.close();
        return res;
    }

    public void extractTextToWriter(String filename, Writer out) throws IOException{
        PDDocument document = getPDDocument(filename);
        stripper.writeText(document, out);
        document.close();
    }

    private PDDocument getPDDocument(String filename) throws IOException{
        if(stripper == null) throw new IOException("ERROR: PDFStripper was not created");
        File file = new File(filename);
        if (!file.exists()) throw new IOException("ERROR: " + filename + " doesn't exist");
        return PDDocument.load(file);
    }
}
