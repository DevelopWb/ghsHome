package com.ghs.ghshome.models.homePage.villagenotice;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.VillageNoticeBean;
import com.ghs.ghshome.tools.CalendarUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * Author:wang_sir
 * Time:2018/7/23 14:11
 * Description:This is VillageNoticeAdapter
 */
public class VillageNoticeAdapter extends BaseQuickAdapter<VillageNoticeBean.DataBean.RowsBean, BaseViewHolder> {
    public VillageNoticeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VillageNoticeBean.DataBean.RowsBean item) {
        if (item != null) {
            if (item.getTop()==1) {//是否置顶：0代表否，1代表是
                helper.setGone(R.id.news_topping_iv,true);
            }else{
                helper.setGone(R.id.news_topping_iv,false);
            }
            helper.setText(R.id.village_notice_title_tv,item.getTitle());

            String  time = item.getUpdateTime();
            time  = CalendarUtil.getTimeFromStringTime("MM月dd日","yyyy-MM-dd HH:mm:ss", time);
            helper.setText(R.id.village_notice_time_tv,time);
            Document content = Jsoup.parse(item.getIntroduction());
            helper.setText(R.id.village_notice_content_tv,content.body().text());

        }
    }
}
