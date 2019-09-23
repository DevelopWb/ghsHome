package com.ghs.ghshome.models.propertyHall.article;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghshome.bean.PropertyParticularsBean;
import com.ghs.ghshome.models.propertyHall.article.ArticleAdapter.ImagesAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.StrUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * created by tobato
 * created date 2019/6/13 15:46.
 * application   物品放行 详情
 */
public class ArticleDetailActivity extends BaseActivity<ArticlePassContract.ProPertyView, ArticlePassPresenter> implements ArticlePassContract.ProPertyView {


    /**
     * 一单元105
     */
    private TextView mProperptyRoomnameTv;
    /**
     * 李小双
     */
    private TextView mProperptyNameTv;
    /**
     * 18810891140
     */
    private TextView mProperptyMobileTv;
    /**
     * 褐石公园小区一单元-102一大堆东西
     */
    private TextView mProperptyContentTv;
    private RecyclerView mPropertyImagesRv;
    /**
     * 审核中
     */
    private TextView mProperptyStateTv;
    private ImagesAdapter imagesAdapter;
    private PropertyParticularsBean.DataBean data;
    private LinearLayout property_images_ly;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_pro_perty_particulars);


    }

    @Override
    public void initLayoutView() {
        initActionBar("物品放行详情", "");
        initView();
    }

    @Override
    public ArticlePassPresenter creatPresenter() {
        return new ArticlePassPresenter();
    }

    @Override
    public void getDate() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(ActivityResultManager.ARTICLE_ID, -1);
        if (id != -1) {
            getPresenter().getPropertyParticulars(id, ArticlePassContract.PRoPRETYPARTICULARS);
        }

    }


    @Override
    public void actionBarRightTvOnClick() {

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

            case ArticlePassContract.PRoPRETYPARTICULARS:

                PropertyParticularsBean propertyParticularsBean = (PropertyParticularsBean) o;
                data = propertyParticularsBean.getData();
                if (data != null) {
                    mProperptyRoomnameTv.setText(StrUtils.isStringValueOk(data.getRoomFullName())?data.getRoomFullName():"暂无记录");
                    mProperptyNameTv.setText(StrUtils.isStringValueOk(data.getFullName())?data.getFullName():"暂无记录");
                    mProperptyMobileTv.setText(StrUtils.isStringValueOk(data.getMobile())?data.getMobile():"暂无记录");
                    mProperptyContentTv.setText(StrUtils.isStringValueOk(data.getContent())?data.getContent():"暂无记录");
                    mProperptyStateTv.setText(getState(data.getState()));
                    if(StrUtils.isStringValueOk(data.getImageUrl())){
                        if (data.getImageUrl().contains(",")) {
                            String[] split = data.getImageUrl().split(",");
                            imagesAdapter.setNewData(Arrays.asList(split));
                        } else {
                            List<String> imageList = new ArrayList<>();
                            imageList.add(data.getImageUrl());
                            imagesAdapter.setNewData(imageList);
                        }
                    }else {
                        property_images_ly.setVisibility(View.GONE);

                    }


                }
                break;
        }


    }

    public String getState(int state) {
        String states = "";
        switch (state) {
            case 1:
                states = "审核中";
                break;

            case 2:
                states = "通过";
                break;

            case 3:
                states = "不通过";
                break;


        }

        return states;

    }

    @Override
    public void onError(String tag) {

    }



    public void initView() {
        mProperptyRoomnameTv = (TextView) findViewById(R.id.properpty_roomname_tv);
        mProperptyNameTv = (TextView) findViewById(R.id.properpty_name_tv);
        mProperptyMobileTv = (TextView) findViewById(R.id.properpty_mobile_tv);
        mProperptyContentTv = (TextView) findViewById(R.id.properpty_content_tv);
        mPropertyImagesRv = (RecyclerView) findViewById(R.id.property_Images_rv);
        mProperptyStateTv = (TextView) findViewById(R.id.properpty_state_tv);
        imagesAdapter = new ImagesAdapter(R.layout.property_image_layout, null);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mPropertyImagesRv.setLayoutManager(layoutManager);
        mPropertyImagesRv.setAdapter(imagesAdapter);
        imagesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                     if(data!= null){
                         new DisplayPhotosActivity().startDisplayPhotosActivity(ArticleDetailActivity.this,data.getImageUrl() , position);

                     }

            }
        });

        property_images_ly = findViewById(R.id.property_images_ly);
    }




}
