package app.project.library.io.file;

import app.project.library.model.Library;

interface FileManager {
    Library importData();
    void exportData(Library library);
}
