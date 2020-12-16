package org.pdf.parser.exception;

public class FileDoesntExistOrIsADirectoryException extends RuntimeException {

    public FileDoesntExistOrIsADirectoryException(String message) {
        super(message);
    }
}
