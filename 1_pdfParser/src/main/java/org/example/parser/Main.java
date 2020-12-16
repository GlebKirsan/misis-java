package org.example.parser;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.parser.exception.FileDoesntExistOrIsADirectoryException;
import org.example.parser.exception.FileNotCreatedException;
import org.example.parser.exception.InvalidNumberOfArgumentsException;
import org.example.parser.exception.PdfFileReadException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@CommonsLog
public class Main {

    @SuppressWarnings({"java:S106"})
    public static void main(String[] args) {
        checkArgsHasOneItem(args);
        File file = new File(args[0]);
        checkFileExistence(file);

        String text = readPdfFileText(file);
        System.out.println(text);
        writePdfContentToOutputFile(text);
    }

    private static void checkArgsHasOneItem(String[] args) {
        if (args.length == 0) {
            log.info(StringConstants.PROVIDE_PATH_TO_A_PDF);
            throw new InvalidNumberOfArgumentsException(StringConstants.PROVIDE_PATH_TO_A_PDF);
        }
    }

    private static void checkFileExistence(File file) {
        if (!file.exists() || file.isDirectory()) {
            String message = String.format(StringConstants.FILE_DOESNT_EXIST_OR_IS_A_DIRECTORY_TEMPLATE,
                                           file.getName());
            log.info(message);
            throw new FileDoesntExistOrIsADirectoryException(message);
        }
    }

    private static String readPdfFileText(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            return pdfTextStripper.getText(document);
        } catch (IOException exception) {
            log.error(StringConstants.FAILED_TO_READ_PDF, exception);
            throw new PdfFileReadException(StringConstants.FAILED_TO_READ_PDF);
        }
    }

    private static void writePdfContentToOutputFile(String text) {
        File outputFile = createFile();
        try (FileWriter output = new FileWriter(outputFile)) {
            output.write(text);
        } catch (IOException exception) {
            log.error(StringConstants.FAILED_TO_WRITE_PDF_CONTENT);
        }
    }

    private static File createFile() {
        String filename = String.format(StringConstants.FILENAME_TEMPLATE, UUID.randomUUID());
        File outputFile = new File(filename);
        boolean isCreated;
        String message = String.format(StringConstants.MESSAGE_TEMPLATE, filename);
        try {
            isCreated = outputFile.createNewFile();
        } catch (IOException exception) {
            isCreated = Boolean.FALSE;
            log.error(message);
        }
        if (!isCreated) {
            throw new FileNotCreatedException(message);
        }

        return outputFile;
    }
}
