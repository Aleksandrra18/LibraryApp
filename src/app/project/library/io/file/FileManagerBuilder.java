package app.project.library.io.file;

import app.project.library.exception.NoSuchFileTypeException;
import app.project.library.io.ConsolePrinter;
import app.project.library.io.DataReader;

import java.io.File;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build() {
        printer.printLine("Wybierz format danych");
        FileType type = getFileType();
        switch (type){
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Nieobsługiwany typ danych");
        }
    }

    private FileType getFileType() {
        boolean typeOK = false;
        FileType result = null;
        do {
            printType();
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOK = true;
            } catch (NoSuchFileTypeException e) {
                printer.printLine("Nieobsługiwany typ danych, wybierz ponownie");
            }
        } while (!typeOK);
        return result;
    }

    private void printType() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }


}
