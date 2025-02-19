package com.fanap.corepos.device.install;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {

    public static void copyToCacheDir(String apkAddress)
    {
        boolean bCacheDirCreated = false;
        File cacheDir = new File("/cache/apkfolder");
        if(cacheDir.exists())
            deleteRecursive(cacheDir);

        bCacheDirCreated = cacheDir.mkdirs();
        if(bCacheDirCreated) {
            String[] cmdCacheDir = {"chmod", "-R", "777", "/cache/apkfolder/"};
            ProcessBuilder bldrCacheDir = new ProcessBuilder(cmdCacheDir);
            try {
                bldrCacheDir.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //copyApkFileToCacheDir(context, "terminal.apk");
            copyFileOrDirectory(apkAddress/*"/mnt/sdcard/terminal2.apk"*/, "/cache/apkfolder/");

        }


        if(bCacheDirCreated) {
            String[] cmdFileDir = {"chmod", "-R", "777", "/cache/apkfolder/"+getFileName(apkAddress)};
            ProcessBuilder bldrFileDir = new ProcessBuilder(cmdFileDir);
            try {
                bldrFileDir.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void deleteRecursive(File fileOrDirectory)
    {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }



    private static void copyFileOrDirectory(String srcDir, String dstDir) {

        try {
            File src = new File(srcDir);
            File dst = new File(dstDir, src.getName());

            if (src.isDirectory()) {

                String files[] = src.list();
                int filesLength = files.length;
                for (int i = 0; i < filesLength; i++) {
                    String src1 = (new File(src, files[i]).getPath());
                    String dst1 = dst.getPath();
                    copyFileOrDirectory(src1, dst1);

                }
            } else {
                copyFile(src, dst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    private static String getFileName(String fileAddress){
        File file = new File(fileAddress);
        return file.getName();
    }
}
