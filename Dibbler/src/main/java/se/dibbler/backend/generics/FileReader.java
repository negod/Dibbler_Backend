/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
public class FileReader {

    public Response<List<String>> getFilesWithExtensionInFolder(String folder, FileType fileType) {

        try {
            GenericExtFilter filter = new GenericExtFilter(fileType.getIdWithDot());

            ClassLoader classLoader = getClass().getClassLoader();
            File dir = new File(classLoader.getResource(folder).getFile());

            System.out.println("Current classloader path: " + classLoader.getResource(folder).getPath());

            if (!dir.isDirectory()) {
                System.out.println("File is no a directory: " + dir.getAbsolutePath() + " Application can read from directory: " + dir.canRead());
                return Response.error(FileError.NOT_FOLDER);
            }

            String[] files = dir.list(filter);
            return Response.success(addPathToFileName(folder, files));

        } catch (Exception e) {
            return Response.error(FileError.UNHANDELED_EXCEPTION);
        }

    }

    public List<String> addPathToFileName(String folder, String[] fileNamesArray) {

        List<String> fileNamesList = new ArrayList<>();
        for (String fileName : fileNamesArray) {
            fileNamesList.add("/" + folder + "/" + fileName);
        }
        return fileNamesList;

    }

    public class GenericExtFilter implements FilenameFilter {

        private final String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }

}
