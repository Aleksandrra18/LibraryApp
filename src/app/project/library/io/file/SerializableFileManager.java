package app.project.library.io.file;

import app.project.library.exception.DataExportException;
import app.project.library.exception.DataImportException;
import app.project.library.model.Library;

import javax.xml.crypto.Data;
import java.io.*;

class SerializableFileManager implements FileManager {

    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        try (ObjectInputStream fis = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
            return (Library) fis.readObject();
        }catch (FileNotFoundException e){
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych pliku " + FILE_NAME);
        }
    }

    @Override
    public void exportData(Library library) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME);
        }
    }
}
