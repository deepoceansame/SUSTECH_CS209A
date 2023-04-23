package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.example.ListFileVisitor;
public class ReadSrcAndRt {

    /**
     * list files in the given directory and subdirs (with recursion)
     * @param paths
     * @return
     */
    public static List<File> getFiles(String paths) {
        List<File> filesList = new ArrayList<File>();
        for (final String path : paths.split(File.pathSeparator)) {
            final File file = new File(path);
            if( file.isDirectory()) {
                recurse(filesList, file);
            }
            else {
                filesList.add(file);
            }
        }
        return filesList;
    }

    private static void recurse(List<File> filesList, File f) {
        File list[] = f.listFiles();
        for (File file : list) {
            if (file.isDirectory()) {
                recurse(filesList, file);
            }
            else {
                filesList.add(file);
            }
        }
    }

    /**
     * List the content of the given jar
     * @param jarPath
     * @return
     * @throws IOException
     */
    public static List<String> getJarContent(String jarPath) throws IOException{
        List<String> content = new ArrayList<String>();
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> e = jarFile.entries();
        while (e.hasMoreElements()) {
            JarEntry entry = (JarEntry)e.nextElement();
            String name = entry.getName();
            content.add(name);
        }
        return content;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("==WORKING DIRECTORY==");
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);

        String srcPathStr = "./src/main/java/org/example/src.zip";
        Path dirSrc = Paths.get(srcPathStr);

        String rtPathStr = "./src/main/java/org/example/rt.jar";

        ListFileVisitor lv = new ListFileVisitor();

        boolean showZip = true;
        boolean showJar = true;

        if (showZip) {
            FileSystem zipFs = FileSystems.newFileSystem(Path.of(srcPathStr), Collections.emptyMap());
            Path zipIORoot = zipFs.getPath("/java/io");
            Path zipNIORoot = zipFs.getPath("/java/nio");
            try(
                    Stream<Path> entries = Files.walk(zipIORoot);
                    Stream<Path> entriesNIO = Files.walk(zipNIORoot);
            ){
                List<Path> filesIO = entries.filter(en->Files.isRegularFile(en)&&en.toString().contains(".java")).toList();
                List<Path> filesNIO = entriesNIO.filter(en->Files.isRegularFile(en)&&en.toString().contains(".java")).toList();
                System.out.println("# of .java files in java.io/java.nio:"+ (filesNIO.size() + filesIO.size()));
                filesIO.forEach(System.out::println);
                filesNIO.forEach(System.out::println);
            }
            zipFs.close();
        }

        if (showJar){
            FileSystem jarFs = FileSystems.newFileSystem(Path.of(rtPathStr), Collections.emptyMap());
            Path jarRoot = jarFs.getPath("/");
            Path jarIORoot = jarFs.getPath("/java/io");
            Path jarNIORoot = jarFs.getPath("/java/nio");
            try(
                    Stream<Path> entries = Files.walk(jarIORoot);
                    Stream<Path> entriesNIO = Files.walk(jarNIORoot);
            ){
                List<Path> filesIO = entries.filter(en->Files.isRegularFile(en)&&en.toString().contains(".class")).toList();
                List<Path> filesNIO = entriesNIO.filter(en->Files.isRegularFile(en)&&en.toString().contains(".class")).toList();
                System.out.println("# of .class files in java.io/java.nio:"+(filesNIO.size()+filesIO.size()));
                filesNIO.forEach(System.out::println);
                filesIO.forEach(System.out::println);

            }
            jarFs.close();
        }




    }

}


