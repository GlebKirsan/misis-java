package org.example.parser;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringConstants {
    String FILENAME_TEMPLATE = "output_%s.txt";
    String FAILED_TO_READ_PDF = "Неудачное чтение pdf-файла";
    String MESSAGE_TEMPLATE = "Неудачное создание файла с именем %s";
    String FAILED_TO_WRITE_PDF_CONTENT = "Неудачная запись содержимого pdf-файла";
    String PROVIDE_PATH_TO_A_PDF = "Передайте аргументом путь до существующего pdf-файла";
    String FILE_DOESNT_EXIST_OR_IS_A_DIRECTORY_TEMPLATE = "Файл %s не существует или является директорией";
}
