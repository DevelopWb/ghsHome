package com.ghs.ghshome.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ImageUtil {
    private static String TAG = "ImageUtil";

    public static ImageUtil getInstance() {
        return ImageUtilHolder.imageUtil;
    }

    private static class ImageUtilHolder {
        private static ImageUtil imageUtil = new ImageUtil();
    }

    /*
     * bitmap转base64
     * */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /*end*/

    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * base64编码字符集转化成图片文件。
     *
     * @param base64Str
     * @param path      文件存储路径
     * @return 是否成功
     */
    public static boolean base64ToFile(String base64Str, String path) {
        byte[] data = Base64.decode(base64Str, Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                //调整异常数据
                data[i] += 256;
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 图片到byte数组
     *
     * @param path
     * @return
     */
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileInputStream input = null;
        try {
            input = new FileInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }


    /**
     * byte数组到图片
     *
     * @param data
     * @param path
     */
    public static void byte2image(byte[] data, String path, String filename) {
        if (data == null || data.length < 3 || path.equals("")) return;
        try {
            FileOutputStream imageOutput = new FileOutputStream(new File(path, filename));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 把bitmap转换成字节数组
     *
     * @param bm      bitmap
     * @param quality 压缩质量百分比
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bm, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        int i = baos.toByteArray().length;
        return baos.toByteArray();
    }

    /**
     * 字节数组转换File对象
     *
     * @param byteOne
     * @param filePath
     * @return
     */
    public static File acquireByteFile(byte[] byteOne, String filePath) {
        File file = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            file = new File(filePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(byteOne);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return file;
    }

    /**
     * 旋转图片
     *
     * @param bm
     * @return
     */
    public static Bitmap adjustPhotoRotation(Bitmap bm, int orientationDegree) {

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2,
                (float) bm.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bm.getHeight();
            targetY = 0;
        } else {
            targetX = bm.getHeight();
            targetY = bm.getWidth();
        }

        final float[] values = new float[9];
        m.getValues(values);

        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];

        m.postTranslate(targetX - x1, targetY - y1);

        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(),
                Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bm1);
        canvas.drawBitmap(bm, m, paint);
        return bm1;

    }

    /**
     * 读取图片属性：旋转的角度 转换图片
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static Bitmap readPictureDegreeToForwordBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (degree != 0) {
            return adjustPhotoRotation(bitmap, degree);
        } else {
            return bitmap;
        }
    }

    /**
     * 保存图片
     *
     * @param path 新图片的存储路径
     * @param bmp  处理的图片
     * @return
     */
    public static String saveCroppedImage(String path, Bitmap bmp) {
        Long time = System.currentTimeMillis();
        File file_dir = new File(path);
        if (!file_dir.exists()) {
            file_dir.mkdirs();
        }
        String newestImagePath = path + time + ".jpeg";
        File file = new File(newestImagePath);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newestImagePath;
    }

    public static boolean isHasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    // 保存图片到手机指定目录
    public static void savaBitmap(Context context, byte[] bytes) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = FileUtil.getHeadPicDir(context) + FileUtil.SAVED_PICTURE_HEARD;
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(filePath);
                fos.write(bytes);
                Toast.makeText(context, "图片已保存到" + filePath, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(context, "请检查SD卡是否可用", Toast.LENGTH_SHORT).show();
        }
    }




//    /**
//     * 异步压缩图片
//     *
//     * @param mContext
//     * @param imagePath
//     * @param onCompressCallBack
//     */
//    public static void compressedPicAsyn(Context mContext, String imagePath, final OnCompressCallBack onCompressCallBack) {
//        Luban.with(mContext)
//                .load(imagePath)
//                .ignoreBy(400)
//                .setTargetDir(FileUtil.getHeadPicDir(mContext))
//                .filter(new CompressionPredicate() {
//                    @Override
//                    public boolean apply(String path) {
//                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
//                    }
//                })
//                .setCompressListener(new OnCompressListener() {
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onSuccess(File file) {
//                        if (onCompressCallBack != null) {
//                            onCompressCallBack.compressSuccess(file.getAbsolutePath());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (onCompressCallBack != null) {
//                            onCompressCallBack.compressError();
//                        }
//                    }
//                }).setRenameListener(new OnRenameListener() {
//            @Override
//            public String rename(String filePath) {
//                return System.currentTimeMillis() + ".jpeg";
//            }
//        })
//                .launch();
//    }

    /**
     * 计算inSampleSize的大小
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 压缩图片
     *
     * @param imagePath
     * @param reqWidth
     * @param reqHeight /
     * @return
     */
    public static String decodeSampledBitmap(String imagePath, int reqWidth, int reqHeight) {
        long size = 0;
        Bitmap bitmap = null;
        try {
            size = FileUtil.getFileSize(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (size > 400 * 1024) {
            bitmap = decodeSampledBitmapFromImagePath(imagePath, reqWidth, reqHeight);
        } else {
            bitmap = BitmapFactory.decodeFile(imagePath);
        }
        Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true); //按指定大小转换

        return bitmapToBase64(mBitmap);
    }

    /**
     * 压缩图片
     *
     * @param imagePath
     * @param reqWidth
     * @param reqHeight //如果图片大于400k 等比例压缩  如果压缩完还大于400k，就进行质量压缩
     * @return
     */
    public static void decodeBitmap(Context mContext, String imagePath, int reqWidth, int reqHeight) {
        long size = 0;
        Bitmap bitmap = null;
        try {
            size = FileUtil.getFileSize(new File(imagePath));
            if (size > 400 * 1024) {
                Log.d(TAG, "图片大于400k");
                int quality = 100;
                bitmap = decodeSampledBitmapFromImagePath(imagePath, reqWidth, reqHeight);
                while (size > 400 * 1024) {
                    quality -= 10;
                    size = FileUtil.getFileSize(decodeImageQuality(mContext, bitmap, quality));
                    Log.d(TAG, "图片的大小------------" + size + "----------图片的压缩质量" + quality);
                }
                Log.d(TAG, "最终图片的大小------------" + size + "----------最终图片的压缩质量" + quality);

            } else {
                bitmap = BitmapFactory.decodeFile(imagePath);
                Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true); //按指定大小转换
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 等比例压缩图片
     *
     * @param imagePath
     * @param reqWidth  图片的宽度
     * @param reqHeight 图片的高度
     * @return
     */
    public static Bitmap decodeSampledBitmapFromImagePath(String imagePath, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, 480, 640);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath, options);
    }

    /**
     * 压缩图片的质量
     */
    private static File decodeImageQuality(Context mContext, Bitmap resultBitmap, int quality) {
        File file = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        resultBitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
        try {
            file = new File(FileUtil.getHeadPicDir(mContext), "face_open_upload.jpeg");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public interface OnCompressCallBack {
        void compressSuccess(String path);

        void compressError();
    }
}
