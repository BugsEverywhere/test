package com.siemens.allkindsoftest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Simons on 2017/6/1.
 */
public class testCopyFiles {

    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\Simons\\Desktop\\templatesFiles\\20170529_Grating_DE1038TR_12329.xls");

        File targetFile = new File("C:\\Users\\Simons\\Desktop\\targetPath\\456.xls");

        if(!targetFile.exists()){

            targetFile.createNewFile();
        }

        System.out.println(file.getName());

        copyFileUsingFileChannels(file,targetFile);



    }


    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }
}
