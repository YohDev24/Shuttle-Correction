import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileBrowser {

    public List<BaseFileObject> browse(File[] files) {
        List<BaseFileObject> folderObjects = new ArrayList<>();

        if (files == null) {
            return folderObjects;
        }

        for (File file : files) {
            if (file == null) {
                continue;
            }

            BaseFileObject baseFileObject;

            if (file.isDirectory()) {
                File[] listOfFiles = file.listFiles(FileHelper.getAudioFilter());

                if (listOfFiles == null || listOfFiles.length == 0) {
                    continue;
                }

                baseFileObject = new FolderObject();
                baseFileObject.path = FileHelper.getPath(file);
                baseFileObject.name = file.getName();

                for (File subFile : listOfFiles) {
                    if (subFile.isDirectory()) {
                        ((FolderObject) baseFileObject).folderCount++;
                    } else {
                        ((FolderObject) baseFileObject).fileCount++;
                    }
                }

                if (!folderObjects.contains(baseFileObject)) {
                    folderObjects.add(baseFileObject);
                }

            } else {
                baseFileObject = new FileObject();
                baseFileObject.path = FileHelper.getPath(file);
                baseFileObject.name = FileHelper.getName(file.getName());
                baseFileObject.size = file.length();
                ((FileObject) baseFileObject).extension = FileHelper.getExtension(file.getName());

                if (!TextUtils.isEmpty(((FileObject) baseFileObject).extension)) {
                    ((FileObject) baseFileObject).tagInfo = new TagInfo(baseFileObject.path);
                    folderObjects.add(baseFileObject);
                }
            }
        }

        return folderObjects;
    }
}
