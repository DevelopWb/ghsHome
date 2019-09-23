package com.ghs.ghshome.models.homePage.officialMsg;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.OfficialNoticeListBean;
import com.ghs.ghshome.tools.CalendarUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class OfficialMessageAdapter extends BaseQuickAdapter<OfficialNoticeListBean.DataBean.RowsBean, BaseViewHolder> {

    public OfficialMessageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OfficialNoticeListBean.DataBean.RowsBean item) {
        if (item.getUserNoticeType() == 1) {//1代表已读，2代表未读
           helper.setTextColor(R.id.village_notice_title_tv, ContextCompat.getColor(mContext,R.color.gray_deeper));
        }else{
            helper.setTextColor(R.id.village_notice_title_tv, ContextCompat.getColor(mContext,R.color.text_color));

        }
        if (item.getTop()==1) {//是否置顶：0代表否，1代表是
            helper.setGone(R.id.news_topping_iv,true);
        }else{
            helper.setGone(R.id.news_topping_iv,false);
        }
        helper.setText(R.id.village_notice_title_tv, item.getTitle());
        String time = item.getAuditTime();
        time = CalendarUtil.getTimeFromStringTime("MM月dd日", "yyyy-MM-dd HH:mm:ss", time);
        helper.setText(R.id.village_notice_time_tv, time);
        Document content = Jsoup.parse(item.getContent());
        helper.setText(R.id.village_notice_content_tv, content.body().text());

    }
}
