package com.ghs.ghshome.models.propertyHall.repairs;

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
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.RxScheduler;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.Calendar;
import java.util.Date;
/**
 * created by tobato
 * created date 2019/6/11 10:30.
 * application   报修
 */
public class RepairsActivity extends BaseActivity<ArticlePassContract.ProPertyView, ArticlePassPresenter> implements View.OnClickListener, ArticlePassContract.ProPertyView {


    private SelectPhotosToUploadFragment selectPhotosFragment;
    /**
     * 详细报修内容...
     */
    private EditText mMineSugguestContentEt;
    private LinearLayout fitment_start_time_ly;
    /**
     * 确定
     */
    private TextView mEntryConfirm;
    private TextView fitment_start_time;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_repairs);

    }

    @Override
    public void initLayoutView() {
        initActionBar("报修", "");
        initView();
    }

    @Override
    public ArticlePassPresenter creatPresenter() {
        return new ArticlePassPresenter();
    }


    @Override
    public void getDate() {


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fitment_start_time_ly:
                setTime();
                break;
            case R.id.entry_confirm:
                if (!StrUtils.isStringValueOk(mMineSugguestContentEt.getText().toString())) {
                    showToast("报修内容不能为空");
                    return;
                }
                showProgressDialog();
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        String icons = selectPhotosFragment.uploadPhotosToOssForPath();
                            getPresenter().addRepairs(UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), mMineSugguestContentEt.getText().toString(), icons,fitment_start_time.getText().toString(), ArticlePassContract.ADDREPAIRS);
                    }

                });
                break;

        }
    }


    public void initView() {
        mMineSugguestContentEt = (EditText) findViewById(R.id.mine_sugguest_content_et);
        fitment_start_time_ly = (LinearLayout) findViewById(R.id.fitment_start_time_ly);
        fitment_start_time = findViewById(R.id.fitment_start_time);
        fitment_start_time_ly.setOnClickListener(this);

        mEntryConfirm = (TextView) findViewById(R.id.entry_confirm);
        mEntryConfirm.setOnClickListener(this);
        selectPhotosFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.select_photos_fg);
        selectPhotosFragment.setSpanCount(5, 9, 20,false).setEtMinHeightAndHint(35, "添加照片");

    }


    private void setTime() {
        Calendar startDate = Calendar.getInstance();
        int currentYear = getCurrentYear();
        startDate.set(currentYear, 1, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(currentYear + 1, 12, 0);

        //时间选择器
        PickerManager.getInstance().showTimePickerViewIncludeRangDate(this, null, "", new PickerManager.OnTimePickerTimeSelectedListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                fitment_start_time.setText(CalendarUtil.getTimeFromDate("yyyy-MM-dd",date));
            }
        },startDate,endDate);

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
}
