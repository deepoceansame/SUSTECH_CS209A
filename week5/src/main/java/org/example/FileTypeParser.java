package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTypeParser {

    public static boolean checkHeader(List<Byte> header, byte[] standard){
        for (int i=0;i<header.size();i++){
            if (Integer.compareUnsigned(header.get(i), standard[i])!=0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws URISyntaxException {
        String fileName = args[0];

        URI uri = BufferReader.class.getClassLoader().getResource(fileName).toURI();
        String filePath = Paths.get(uri).toString();
        System.out.println("Filename:"+fileName);
        try (
                FileInputStream fis = new FileInputStream(filePath);

                )
        {

            byte[] buffer = new byte[65535];
            int byteNum = fis.read(buffer);

            System.out.printf("File Header(Hex):");
            System.out.printf("[");
            List<Byte> header = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                if (i==3){
                    System.out.printf("%02x]", buffer[i]);
                } else{
                    System.out.printf("%02x, ",buffer[i]);
                }
                header.add(buffer[i]);
            }
            System.out.print("\n");
            byte[] pngHeader = new byte[]{(byte)0x89, (byte)0x50, (byte)0x4e, (byte)0x47};
            byte[] zipHeader = new byte[]{(byte)0x50, (byte)0x4b, (byte)0x03, (byte)0x04};
            byte[] classHeader = new byte[]{(byte)0xca, (byte)0xfe, (byte)0xba, (byte)0xbe};
            if(checkHeader(header, pngHeader)){
                System.out.printf("File Type: png");
            }
            else if(checkHeader(header, zipHeader)){
                System.out.printf("File Type: zip or jar");
            }
            else if(checkHeader(header, classHeader)){
                System.out.printf("File Type: class");
            }
            System.out.print("\n");

        } catch (FileNotFoundException e) {
            System.out.println("The pathname does not exist.");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Character Encoding is not supported.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed or interrupted when doing the I/O operations");
            e.printStackTrace();
        }
    }
}
