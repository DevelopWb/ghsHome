package com.ghs.ghshome.models.homePage.seed;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.CarFeeRecordItem1;
import com.ghs.ghshome.bean.CarFeeRecordItem2;
import com.ghs.ghshome.bean.CarPayRecord;
import com.ghs.ghshome.bean.SeedRecordBean;
import com.ghs.ghshome.bean.SeedRecordItem1;
import com.ghs.ghshome.bean.SeedRecordItem2;
import com.ghs.ghshome.tools.PubUtil;

import java.util.List;


/**
 * Author:wang_sir
 * Time:2018/7/23 14:11
 * Description:This is VillageNoticeAdapter
 */
public class SeedRecordAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    public static final int TYPE_LEVEL_3 = 3;
    public static final int TYPE_LEVEL_4 = 4;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SeedRecordAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_seed_record1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_seed_record2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_seed_record1);
        addItemType(TYPE_LEVEL_4, R.layout.item_expandable_park_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_1:
                SeedRecordItem1 recordItem1 = (SeedRecordItem1) item;
                helper.setText(R.id.seed_record_item1_title_tv, recordItem1.getTitle());
                if (recordItem1.isExpanded()) {
                    helper.setImageResource(R.id.seed_record_item1_tag_iv, R.mipmap.arrow_down_green);
                } else {
                    helper.setImageResource(R.id.seed_record_item1_tag_iv, R.mipmap.arrow_right_green);
                }
                helper.getView(R.id.seed_record_item1_cl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (recordItem1.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });

                break;
            case TYPE_LEVEL_2:
                SeedRecordItem2 recordItem2 = (SeedRecordItem2) item;
                SeedRecordBean.DataBean.SeedDetailDOListBean bean = recordItem2.getBean();
                helper.setText(R.id.seed_record_item2_title_tv, bean.getRemark());
                if (1== PubUtil.SEED_RECORD_TYPE) {
                    helper.setText(R.id.seed_record_item2_value_tv, "+" + bean.getSeed() + "颗");
                }else{
                    helper.setText(R.id.seed_record_item2_value_tv, "-" + bean.getSeed() + "颗");
                }

                break;
            case TYPE_LEVEL_3:
                CarFeeRecordItem1 carFeeRecordItem1 = (CarFeeRecordItem1) item;
                helper.setText(R.id.seed_record_item1_title_tv, carFeeRecordItem1.getTitle());
                if (carFeeRecordItem1.isExpanded()) {
                    helper.setImageResource(R.id.seed_record_item1_tag_iv, R.mipmap.arrow_down_green);
                } else {
                    helper.setImageResource(R.id.seed_record_item1_tag_iv, R.mipmap.arrow_right_green);
                }
                helper.getView(R.id.seed_record_item1_cl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (carFeeRecordItem1.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_4:
                CarFeeRecordItem2 carFeeRecordItem2 = (CarFeeRecordItem2) item;
                CarPayRecord.DataBean.CarparkTempOrderDOListBean recordItem2Bean = carFeeRecordItem2.getBean();
                helper.setText(R.id.consume_time_tv, recordItem2Bean.getPaySuccessTime());
                helper.setText(R.id.consume_amount_tv, "-" + recordItem2Bean.getServiceFee());
                break;
            default:
                break;
        }
    }
}
