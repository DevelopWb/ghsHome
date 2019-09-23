package com.ghs.ghshome.models.propertyHall.article;


import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.displayPhotos.SelectPhotosToUploadFragment;
import com.ghs.ghshome.bean.PropertyParticularsBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.RxScheduler;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;
/**
 * created by tobato
 * created date 2019/6/18 14:15.
 * application  添加物品放行
 */
public class AddArticlePassActivity extends BaseActivity<ArticlePassContract.ProPertyView, ArticlePassPresenter> implements ArticlePassContract.ProPertyView, View.OnClickListener {
    /**
     * 请输入
     */
    private EditText mAddPorpertyNameEt;
    /**
     * 请输入
     */
    private EditText mAddPorpertyMobile;
    /**
     * 请输入物品清单
     */
    private EditText mAddPorpertyContent;
    /**
     * 提交
     */
    private TextView mEntryConfirm;
    /**
     * 2345
     */
    private TextView mAddPorpertyRoomEt;
    private SelectPhotosToUploadFragment selectPhotosFragment;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_property_pass);

    }

    @Override
    public void initLayoutView() {
        initActionBar("添加物品放行", "");
        initView();
    }

    @Override
    public ArticlePassPresenter creatPresenter() {
        return new ArticlePassPresenter();
    }

    @Override
    public void getDate() {

        mAddPorpertyRoomEt.setText(UserInfoUtil.getInstance().getRoomName());
        mAddPorpertyNameEt.setText(UserInfoUtil.getInstance().getFullName());
        mAddPorpertyMobile.setText(UserInfoUtil.getInstance().getMobile() + "");
        setResult(ActivityResultManager.ADD_PROPERTY);
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        PropertyParticularsBean propertyParticularsBean = (PropertyParticularsBean) o;
        String message = propertyParticularsBean.getMessage();
        showToast(message);
        finish();

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }



    public void initView() {
        mAddPorpertyNameEt = (EditText) findViewById(R.id.add_porperty_name_et);
        mAddPorpertyMobile = (EditText) findViewById(R.id.add_porperty_mobile);
        mAddPorpertyContent = (EditText) findViewById(R.id.add_porperty_content);
        mEntryConfirm = (TextView) findViewById(R.id.entry_confirm);
        mEntryConfirm.setOnClickListener(this);
        mAddPorpertyRoomEt = (TextView) findViewById(R.id.add_porperty_room_et);
        selectPhotosFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.select_photos_fg);
        selectPhotosFragment.setSpanCount(5, 9, 20,false).setEtMinHeightAndHint(35,"添加照片");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.entry_confirm:
                if (!StrUtils.isStringValueOk(mAddPorpertyNameEt.getText().toString())) {
                    showToast("房屋号不能为空！");
                    return;
                }

                if (!StrUtils.isStringValueOk(mAddPorpertyNameEt.getText().toString())) {
                    showToast("姓名不能为空！");
                    return;
                }
                if (!StrUtils.isStringValueOk(mAddPorpertyMobile.getText().toString())) {
                    showToast("联系电话不能为空！");
                    return;
                }
                if (!StrUtils.isStringValueOk(mAddPorpertyContent.getText().toString())) {
                    showToast("放行内容不能为空！");
                    return;
                }

                showProgressDialog();
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        String icons = selectPhotosFragment.uploadPhotosToOssForPath();
                        Log.i("TGA",icons);
                            getPresenter().addProperty(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), mAddPorpertyContent.getText().toString(), icons, ArticlePassContract.ADDPROPERTY);
                    }

                });



                break;
        }
    }
}
