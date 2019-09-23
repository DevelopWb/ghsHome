package com.ghs.ghshome.models.propertyHall.openByFace;

import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.tools.FileUtil;
import com.ghs.ghshome.tools.ImageUtil;
import com.ghs.ghshome.tools.RxScheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * created by tobato
 * created date 2019/6/18 16:01.
 * application   采集人脸
 */
public class TakeFacePhotoActivity extends BaseActivity<FaceOpenContrat.IFaceOpenView, FaceOpenPresenter> implements View.OnClickListener, SurfaceHolder.Callback, FaceOpenContrat.IFaceOpenView {

    private SurfaceView mSurfaceSv;
    private ImageView mHeaderLeftIv;
    /**
     * 采集
     */
    private TextView mTakePhotoTv;
    private Camera.PictureCallback mpictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            String imagePath = FileUtil.getHeadPicDir(TakeFacePhotoActivity.this)
                    + FileUtil.SAVED_FACE_OPEN;
            File tempfile = new File(imagePath);
            try {
                FileOutputStream fos = new FileOutputStream(tempfile);
                fos.write(data);
                fos.close();
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {

                            String image = ImageUtil.decodeSampledBitmap(imagePath, 400, 800);
                            getPresenter().addPhoto(mUserInfoUtil.getUserId(), image, FaceOpenContrat.ADD_PHOTO);
                    }
                });
//

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private ImageView mSurfaceBgIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);
        initView();

    }

    @Override
    protected void setLayout() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * 开始预览
     *
     * @param camera
     * @param holder
     */
    private void startPreview(int width, int heigh, Camera camera, SurfaceHolder holder) {
        try {
            Camera.Parameters parameters = camera.getParameters();
            //设置预览尺寸
            List<Camera.Size> preSizes = parameters.getSupportedPreviewSizes();
            Camera.Size size = getCloselyPreSize(width, heigh, preSizes);
            parameters.setPreviewSize(size.width, size.height);
            //设置图片质量 像素
            parameters.setJpegQuality(100);
            parameters.setJpegThumbnailQuality(100);
            parameters.setPictureFormat(PixelFormat.JPEG);// 设置照片的输出格式
            List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
            if (sizes != null && sizes.size() > 0) {
                for (Camera.Size size1 : sizes) {
                    parameters.setPictureSize(size1.width, size1.height);
                    break;
                }
            }
            parameters.setRotation(270);
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);
            camera.setParameters(parameters);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过对比得到与宽高比最接近的尺寸（如果有相同尺寸，优先选择）
     *
     * @param surfaceWidth  需要被进行对比的原宽
     * @param surfaceHeight 需要被进行对比的原高
     * @param preSizeList   需要对比的预览尺寸列表
     * @return 得到与原宽高比例最接近的尺寸
     */
    protected Camera.Size getCloselyPreSize(int surfaceWidth, int surfaceHeight,
                                            List<Camera.Size> preSizeList) {

        int ReqTmpWidth;
        int ReqTmpHeight;
        boolean mIsPortrait = true;
        // 当屏幕为垂直的时候需要把宽高值进行调换，保证宽大于高
        if (mIsPortrait) {
            ReqTmpWidth = surfaceHeight;
            ReqTmpHeight = surfaceWidth;
        } else {
            ReqTmpWidth = surfaceWidth;
            ReqTmpHeight = surfaceHeight;
        }
        //先查找preview中是否存在与surfaceview相同宽高的尺寸
        for (Camera.Size size : preSizeList) {
            if ((size.width == ReqTmpWidth) && (size.height == ReqTmpHeight)) {
                return size;
            }
        }

        // 得到与传入的宽高比最接近的size
        float reqRatio = ((float) ReqTmpWidth) / ReqTmpHeight;
        float curRatio, deltaRatio;
        float deltaRatioMin = Float.MAX_VALUE;
        Camera.Size retSize = null;
        for (Camera.Size size : preSizeList) {
            curRatio = ((float) size.width) / size.height;
            deltaRatio = Math.abs(reqRatio - curRatio);
            if (deltaRatio < deltaRatioMin) {
                deltaRatioMin = deltaRatio;
                retSize = size;
            }
        }

        return retSize;
    }

    /**
     * 释放Camera
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 获取Camera
     *
     * @return
     */
    private Camera getCamera() {
        Camera camera;
        try {
            //打开后置摄像头
            camera = Camera.open(1);
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo_tv:
                if (mCamera != null) {
                    //采集拍照
                    showProgressDialog();
                    mCamera.takePicture(null, null, mpictureCallback);
                }

                break;
            case R.id.surface_bg_iv:
                if (mCamera != null) {
                    mCamera.autoFocus(null);
                }
                break;
            case R.id.header_left_iv:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public FaceOpenPresenter creatPresenter() {
        return new FaceOpenPresenter();
    }

    @Override
    public void getDate() {

    }

    private void initView() {
        mSurfaceSv = (SurfaceView) findViewById(R.id.surface_sv);
        mSurfaceSv.setZOrderOnTop(false);
        mSurfaceHolder = mSurfaceSv.getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        mSurfaceHolder.addCallback(this);
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setOnClickListener(this);
        mTakePhotoTv = (TextView) findViewById(R.id.take_photo_tv);
        mTakePhotoTv.setOnClickListener(this);
        mSurfaceBgIv = (ImageView) findViewById(R.id.surface_bg_iv);
        mSurfaceBgIv.setOnClickListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //chuagnj
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mCamera != null) {
            mCamera.stopPreview();
            startPreview(width, height, mCamera, holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        showToast("采集成功");
        finish();
    }

    @Override
    public void onError(String tag) {
        showToast(tag);
        if (mCamera != null) {
            mCamera.stopPreview();
            startPreview(mSurfaceSv.getWidth(), mSurfaceSv.getHeight(), mCamera, mSurfaceHolder);
        }

    }
}
