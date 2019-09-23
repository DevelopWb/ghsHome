package com.ghs.ghshome.models.propertyHall.openByFace;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.promission.CheckPermListener;
import com.ghs.ghshome.bean.FacePhotoListBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PubUtil;

import java.util.List;

public class PhotosOfTakedActivity extends BaseActivity<FaceOpenContrat.IFaceOpenView, FaceOpenPresenter> implements FaceOpenContrat.IFaceOpenView, View.OnClickListener {

    private RecyclerView mPhotoTaskedRv;
    private PhotoListAdapter photoListAdapter;
    /**
     * 确定
     */
    private TextView mEntryConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_of_taked);
        initActionBar("采集人脸", "");
        initView();
        setResult(ActivityResultManager.TASKED_FACE_LIST);
    }

    @Override
    protected void setLayout() {

    }

    @Override
    public FaceOpenPresenter creatPresenter() {
        return new FaceOpenPresenter();
    }


    @Override
    public void getDate() {
        showProgressDialog();
        getPresenter().getPhotoList(mUserInfoUtil.getUserId(), FaceOpenContrat.GETPHOTOLIST);


    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            //获取列表
            case FaceOpenContrat.GETPHOTOLIST:

                FacePhotoListBean photoListBean = (FacePhotoListBean) o;
                if (photoListBean != null) {
                    List<FacePhotoListBean.DataBean> listBeanData = photoListBean.getData();
                    photoListAdapter.setNewData(listBeanData);
                }

                break;


            //删除照片
            case FaceOpenContrat.DELETEPHOTO:
                FacePhotoListBean facePhotoListBean = (FacePhotoListBean) o;
                if (facePhotoListBean != null) {
                    showToast("删除" + facePhotoListBean.getMessage());
                    getPresenter().getPhotoList(mUserInfoUtil.getUserId(), FaceOpenContrat.GETPHOTOLIST);

                }

                break;


        }


    }

    @Override
    public void onError(String tag) {
        showToast(tag);


    }

    private void initView() {
        mPhotoTaskedRv = (RecyclerView) findViewById(R.id.Photo_Tasked_rv);
        photoListAdapter = new PhotoListAdapter(R.layout.photo_taked_item_layout, null);
        initRecyclerview(mPhotoTaskedRv, photoListAdapter, LinearLayoutManager.VERTICAL, false);

        photoListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String guid = photoListAdapter.getData().get(position).getGuid();
                isDeletePhoto(position, guid);
            }
        });

        mEntryConfirm = (TextView) findViewById(R.id.entry_confirm);
        mEntryConfirm.setOnClickListener(this);
    }


    public void isDeletePhoto(int position, String uFacePhotoId) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("删除该照片可能导致您无法正常人脸开门，确定删除吗？")
                .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showProgressDialog();
                        getPresenter().deletePhoto(mUserInfoUtil.getUserId(), uFacePhotoId, FaceOpenContrat.DELETEPHOTO);
                        dialog.dismiss();
                    }
                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })

                .show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.entry_confirm:
                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        //采集照片
                        if (photoListAdapter != null) {
                            if (photoListAdapter.getData().size() >= 3) {
                                showToast("最多采集三张照片");
                                return;
                            }
                            startActivityForResult(new Intent(PhotosOfTakedActivity.this, TakeFacePhotoActivity.class), ActivityResultManager.TASKED_FACE_PHOTO);
                        }
                    }

                    @Override
                    public void selectedAllPermission() {

                    }
                }, R.string.perm_camera_store, PubUtil.promissions[1], PubUtil.promissions[3]);


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityResultManager.TASKED_FACE_PHOTO) {
            getPresenter().getPhotoList(mUserInfoUtil.getUserId(), FaceOpenContrat.GETPHOTOLIST);
        }
    }
}
