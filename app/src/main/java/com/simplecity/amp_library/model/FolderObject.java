package com.simplecity.amp_library.model;

import com.simplecity.amp_library.interfaces.FileType;

public class FolderObject extends BaseFileObject {

    public int fileCount;
    public int folderCount;

    public FolderObject() {
        this.fileType = FileType.FOLDER;
    }

    @Override
    public String toString() {
        return "FolderObject{" +
                "fileCount=" + fileCount +
                ", folderCount=" + folderCount +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FolderObject that = (FolderObject) o;
        return fileCount == that.fileCount &&
            folderCount == that.folderCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileCount, folderCount);
    }

}
