package com.ghs.ghshome.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * Author:wang_sir
 * Time:2018/7/19 9:52
 * Description:This is FileUtil
 */
public class FileUtil {


    public static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值
    public static String SAVED_PICTURE_NAME = "user_head_uncrop.jpg";
    public static String SAVED_FACE_OPEN = "face_open.jpeg";//人脸开门采集的图片
    public static String SAVED_PICTURE_HEARD = "saved_head_pic.jpg";

    /**
     * 获取头像图片的根目录
     *
     * @return
     */
    public static String getHeadPicDir(Context context) {
        String path = context
                .getExternalCacheDir().getAbsolutePath() + "/userPic/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }



    /**
     * 创建文件
     *
     * @param filePath
     */
    private static File creatFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 删除文件
     *
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


    /**
     * 清除缓存
     * @return
     */
    public static boolean clearCacheMemory(Context context){
        //清除缓存
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }


        return true;
    }


    /**
     * 获取缓存大小
     *
     * @param context
     * @return
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        //缓存
        long cacheSize =0;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFileSizes(context.getExternalCacheDir());
        }
        cacheSize += getFileSizes(context.getCacheDir());

        return formetFileSize(cacheSize);
    }

    /**
     * 获取指定文件夹的大小
     *
     * @param f
     * @return
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size+=getFileSize(flist[i]);
            }
        }
        return size;
    }
    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }


    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    /**
     * 获取头像图片的根路径
     *
     * @return
     */
    public static String getHeadPicRootPath(Context context) {
        return getHeadPicDir(context)+SAVED_PICTURE_NAME;
    }

    /**
     * 打开相机
     * 兼容7.0
     *
     * @param activity    Activity
     * @param file        File
     */
    public static void openCamera(Activity activity, File file) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(activity, file));
        activity.startActivityForResult(intent, ActivityResultManager.TAKE_PICTURE);
    }
    /**
     * 打开相机
     * 兼容7.0
     *
     * @param file        File
     */
    public static void openCamera(Fragment fragment, File file) {
        if (fragment == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(fragment.getContext(), file));
        fragment.startActivityForResult(intent, ActivityResultManager.TAKE_PICTURE);
    }

    /**
     * 获取对应的URI
     * @param context
     * @param file
     * @return
     */
    public static Uri getUriFromFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.ghs.ghshome.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }
}
