package com.ghs.ghshome.models.mine.appSuggestion;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.oss.OssUploadManager;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.ConfigUtil;
import com.ghs.ghshome.tools.FileUtil;
import com.ghs.ghshome.tools.ImageUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/5/31 18:18.
 * application   app反馈建议
 */
public class AppSuggestionActivity extends BaseActivity<AppSuggestionContract.IMySuggestionView, AppSuggestionPresent> implements AppSuggestionContract.IMySuggestionView, View.OnClickListener {

    /**
     * 反馈内容...
     */
    private EditText mMineSugguestContentEt;
    /**
     * 提 交
     */
    private TextView mMineSugguestConfirmTv;
    private RecyclerView mMineSuggestionRv;
    private AppSuggestionAdapter adapter;
    private List<String> icons = new ArrayList<>();
    private BottomSheetDialog bottomSheetDialog;
    private OssUploadManager ossUploadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        icons.add("-1");
        adapter.setNewData(icons);
        ossUploadManager = OssUploadManager.getInstance(this);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("APP反馈建议", "");


    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_my_suggestion);

    }


    @Override
    public AppSuggestionPresent creatPresenter() {
        return new AppSuggestionPresent();
    }


    private void initView() {
        mMineSugguestContentEt = (EditText) findViewById(R.id.mine_sugguest_content_et);
        mMineSugguestConfirmTv = (TextView) findViewById(R.id.mine_sugguest_confirm_tv);
        mMineSugguestConfirmTv.setOnClickListener(this);
        mMineSuggestionRv = (RecyclerView) findViewById(R.id.mine_suggestion_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mMineSuggestionRv.setLayoutManager(manager);
        adapter = new AppSuggestionAdapter(R.layout.mine_suggestion_item_layout);
        mMineSuggestionRv.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                imageView = (ImageView) adapter.getViewByPosition(mMineSuggestionRv, position, R.id.mine_sugguest_icon_iv);
                List<String> arrays = reSortIconList();
                String icon_path = arrays.get(position);
                switch (view.getId()) {
                    case R.id.mine_sugguest_icon_iv:
                        if ("-1".equals(icon_path)) {
                            View bottomView = LayoutInflater.from(AppSuggestionActivity.this).inflate(R.layout.mine_edit_update_head_pic, null);
                            bottomSheetDialog = new BottomSheetDialog(AppSuggestionActivity.this);
                            bottomSheetDialog.setCancelable(false);
                            bottomSheetDialog.setContentView(bottomView);
                            bottomSheetDialog.show();
                            bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(AppSuggestionActivity.this);
                            bottomView.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(AppSuggestionActivity.this);
                            bottomView.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(AppSuggestionActivity.this);
                            bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);
                            bottomView.findViewById(R.id.mine_edit_title_line_tv).setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mine_sugguest_delete_iv:
                        arrays.remove(position);
                        icons.clear();
                        if (arrays.size() < 3) {
                            if (!arrays.contains("-1")) {
                                arrays.add("-1");
                            }
                        }
                        icons = arrays;
                        adapter.setNewData(arrays);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(this));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                icons.add(userHeadPic);
                adapter.setNewData(reSortIconList());

            } else if (requestCode == ActivityResultManager.SYSTEM_PICTURE) {
                ContentResolver resolver = getContentResolver();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                try {
                    InputStream inputStream = resolver.openInputStream(data.getData());

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                    icons.add(userHeadPic);
                    adapter.setNewData(reSortIconList());
                } catch (FileNotFoundException e) {


                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 对icons集合处理
     *
     * @return
     */
    private List<String> reSortIconList() {
        List<String> icons_new = new ArrayList<>();
        for (String icon : icons) {
            if (!"-1".equals(icon)) {
                icons_new.add(icon);
            }
        }
        if (icons.size() < 4) {
            icons_new.add("-1");
        }
        return icons_new;
    }


    /**
     * 上传建议
     *
     * @param content
     */
    private void uploadSuggestionsToService(String content) {
        //图片的地址从icons里面取出
        String iconPath1 = "";
        String iconPath2 = "";
        String iconPath3 = "";
        List<String> uploadIcons = reSortIconList();
        for (int i = 0; i < uploadIcons.size(); i++) {
            if (i == 0) {
                iconPath1 = uploadIcons.get(0);
                if ("-1".equals(iconPath1)) {
                    iconPath1 = "";
                }
            }
            if (i == 1) {
                iconPath2 = uploadIcons.get(1);
                if ("-1".equals(iconPath2)) {
                    iconPath2 = "";
                }
            }
            if (i == 2) {
                iconPath3 = uploadIcons.get(2);
                if ("-1".equals(iconPath3)) {
                    iconPath3 = "";
                }
            }

        }
        String iconServerPath1 = "";
        String iconServerPath2 = "";
        String iconServerPath3 = "";
        if (!TextUtils.isEmpty(iconPath1)) {
            iconServerPath1 = ossUploadManager.getUploadImageNameServer();
            ossUploadManager.asyncPutImage(ConfigUtil.IMAGE_PATH_SERVER + iconServerPath1, iconPath1);
        }
        if (!TextUtils.isEmpty(iconPath2)) {
            iconServerPath2 = ossUploadManager.getUploadImageNameServer();
            ossUploadManager.asyncPutImage(ConfigUtil.IMAGE_PATH_SERVER + iconServerPath2, iconPath2);
        }
        if (!TextUtils.isEmpty(iconPath3)) {
            iconServerPath3 = ossUploadManager.getUploadImageNameServer();
            ossUploadManager.asyncPutImage(ConfigUtil.IMAGE_PATH_SERVER + iconServerPath3, iconPath3);
        }
        getPresenter().uploadSuggestion(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getVillageId(), content, iconServerPath1, iconServerPath2, iconServerPath3, RequestStatus.UPDATE);
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
            case RequestStatus.UPDATE:
                int code = (int) o;
                if (1000 == code) {
                    showToast("提交成功");
                }
                //清除数据
                icons.clear();
                icons.add("-1");
                adapter.setNewData(icons);
                mMineSugguestContentEt.setText("");
                break;
            default:
                break;
        }

    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void onBackPressed() {
        hideKeyboard(mHeaderLeftIv);
        super.onBackPressed();
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mine_sugguest_confirm_tv:
                String content = mMineSugguestContentEt.getText().toString().trim();
                if (!StrUtils.isStringValueOk(content)) {
                    showToast("请填写建议内容");
                    return;
                }

                uploadSuggestionsToService(content);

                break;
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
               takePicturesFromActivity(bottomSheetDialog);

                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮
               selectPicturesFromSysPhoto(bottomSheetDialog);
                break;
        }
    }
}
