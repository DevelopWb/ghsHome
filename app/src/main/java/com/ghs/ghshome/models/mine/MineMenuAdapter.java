package com.ghs.ghshome.models.mine;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.MineMenuBean;
import com.ghs.ghshome.bean.MultiItemBean;
import com.ghs.ghshome.tools.StrUtils;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/30 15:12
 * Description:This is MineMenuAdapter
 */
public class MineMenuAdapter extends BaseMultiItemQuickAdapter<MultiItemBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MineMenuAdapter(List<MultiItemBean> data) {
        super(data);
        addItemType(MultiItemBean.MINE_FRAGMENT_DIVIDER, R.layout.divider_ten_dp);
        addItemType(MultiItemBean.MINE_FRAGMENT_CONTENT, R.layout.mine_menu_item);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemBean item) {
        switch (helper.getItemViewType()) {
            case MultiItemBean.MINE_FRAGMENT_DIVIDER:
                break;
            case MultiItemBean.MINE_FRAGMENT_CONTENT:
                MineMenuBean  bean = (MineMenuBean) item.getObject();
                String title = bean.getTitle();
                helper.setImageResource(R.id.mine_menu_icon_iv,bean.getDrawbleId());
                helper.setText(R.id.mine_menu_tv,title);
                if (StrUtils.isStringValueOk(bean.getDes())) {
                    helper.setGone(R.id.mine_menu_des_tv,true);
                    helper.setText(R.id.mine_menu_des_tv,bean.getDes());
                }else{
                    helper.setGone(R.id.mine_menu_des_tv,false);
                }
                switch (title) {
                    case "账单统计":
                        helper.setGone(R.id.mine_menu_divider_v,false);
                        break;
                    case "APP反馈建议":
                        helper.setGone(R.id.mine_menu_divider_v,false);
                        break;
                    case "设置":
                        helper.setGone(R.id.mine_menu_divider_v,false);
                        break;
                    default:
                        helper.setGone(R.id.mine_menu_divider_v,true);
                        break;
                }
                break;
            default:
                break;
        }

    }
}
