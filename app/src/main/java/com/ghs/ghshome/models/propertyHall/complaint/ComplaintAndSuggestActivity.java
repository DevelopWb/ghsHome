package com.ghs.ghshome.models.propertyHall.complaint;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.displayPhotos.SelectPhotosToUploadFragment;
import com.ghs.ghshome.bean.PropertyParticularsBean;
import com.ghs.ghshome.models.propertyHall.article.ArticlePassContract;
import com.ghs.ghshome.models.propertyHall.article.ArticlePassPresenter;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.RxScheduler;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 投诉建议
 */
public class ComplaintAndSuggestActivity extends BaseActivity<ArticlePassContract.ProPertyView, ArticlePassPresenter> implements View.OnClickListener, ArticlePassContract.ProPertyView {


    /**
     * 请详细描述问题，以便更好的处理
     */
    private EditText mMineSugguestContentEt;
    /**
     * 确定
     */
    private TextView mEntryConfirm;

    private SelectPhotosToUploadFragment selectPhotosFragment;
    private LinearLayout fitment_start_time_ly;
    private TextView fitment_start_time;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_complaint_and_suggest);

    }

    @Override
    public void initLayoutView() {
        initActionBar("投诉建议", "");
        initView();
    }

    @Override
    public ArticlePassPresenter creatPresenter() {
        return new ArticlePassPresenter();
    }


    @Override
    public void getDate() {

    }


    public void initView() {
        mMineSugguestContentEt = findViewById(R.id.mine_sugguest_content_et);
        mEntryConfirm = (TextView) findViewById(R.id.entry_confirm);
        fitment_start_time_ly = (LinearLayout) findViewById(R.id.fitment_start_time_ly);
        fitment_start_time = (TextView) findViewById(R.id.fitment_start_time);
        fitment_start_time_ly.setOnClickListener(this);
        selectPhotosFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.select_photos_fg);
        selectPhotosFragment.setSpanCount(5, 9, 20,false).setEtMinHeightAndHint(35, "添加照片");
        fitment_start_time_ly.setOnClickListener(this);
        mEntryConfirm.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.fitment_start_time_ly:

                List<String> strings = new ArrayList<>();
                strings.add("建议");
                strings.add("投诉");
                PickerManager.getInstance().showOptionPicker(this, strings,new PickerManager.OnOptionPickerSelectedListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        fitment_start_time.setText(strings.get(options1));
                    }
                });

                break;
            case R.id.entry_confirm:
                if (!StrUtils.isStringValueOk(mMineSugguestContentEt.getText().toString())) {
                    showToast("问题内容不能为空");
                    return;
                }
                showProgressDialog();
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        String icons = selectPhotosFragment.uploadPhotosToOssForPath();
                            getPresenter().saveComplainOrSuggest(UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), mMineSugguestContentEt.getText().toString(), icons, switchType(fitment_start_time.getText().toString().trim()), ArticlePassContract.COMPLAIN_SUGGEST);
                    }

                });


                break;
        }
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

        if (propertyParticularsBean != null) {
            showToast(propertyParticularsBean.getMessage());
            finish();
        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);

    }


    public int switchType(String type) {
        int types = 0;
        switch (type) {
            case "建议":
                types = 3;
                break;

            case "投诉":
                types = 2;
                break;
        }

        return types;
    }

}
