package com.ghs.ghshome.models.homePage.visitors;

import android.util.Log;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.KeyValueBean;
import com.ghs.ghshome.bean.VisitorsBean;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/11/23 11:30
 * Description:This is VisitorPresent
 */
public class VisitorPresent extends BasePresent<VisitorContract.IVisitorView> implements VisitorContract.IVisitorPresent {
    @Override
    public void searchVisitors(int villageId, int roomId, int ghsUserId, int state, int offset, int limit, String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .params("ghsUserId", ghsUserId)
                .params("state", state)
                .params("offset", offset)
                .params("limit", limit)
                .postToNetwork(Contract.SEARCH_VISITOR,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, VisitorsBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }

    @Override
    public void addVisitors(int villageId, int roomId, int ghsUserId, String visitorName, String visitorMobile,  String visitDay,  String carNum, String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .params("ghsUserId", ghsUserId)
                .params("visitorName", visitorName)
                .params("visitorMobile", visitorMobile)
                .params("visitDay", visitDay)
                .params("carNum", carNum)
                .postToNetwork(Contract.ADD_VISITOR,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), VisitorsBean.DataBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }

    @Override
    public void accessVisitors(int visitorId, int ghsUserId, String tag) {
        HttpProxy.getInstance()
                .params("visitorId", visitorId)
                .params("ghsUserId", ghsUserId)
                .postToNetwork(Contract.ACCESS_VISITOR,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), VisitorsBean.DataBean.class),tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                         if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }

    @Override
    public void rejectVisitors(int visitorId, int ghsUserId,String tag) {
        HttpProxy.getInstance()
                .params("visitorId", visitorId)
                .params("ghsUserId", ghsUserId)
                .postToNetwork(Contract.REJECT_VISITOR,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), VisitorsBean.DataBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }

    @Override
    public void getVisitorMsg(int visitorId, String tag) {
        HttpProxy.getInstance()
                .params("visitorId", visitorId)
                .postToNetwork(Contract.GET_VISITOR_MSG,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), VisitorsBean.DataBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }



    /**
     * 获取adapter数据
     *
     * @param item
     * @return
     */
    public List<KeyValueBean> getVisitorDetailMsg(VisitorsBean.DataBean item, boolean  hasPwd) {
        List<KeyValueBean> arrays = new ArrayList<>();
        arrays.add(new KeyValueBean("访客姓名：", item.getVisitorName()));
        arrays.add(new KeyValueBean("手机号码：", item.getVisitorMobile()));
        String  arriveTime = item.getVisitDay();
        if (StrUtils.isStringValueOk(arriveTime)) {
            arrays.add(new KeyValueBean("到访时间：", CalendarUtil.getTimeFromStringTime("yyyy-MM-dd","yyyy-MM-dd HH:mm:ss",arriveTime)));
        }
        arrays.add(new KeyValueBean("到访地址：", item.getVillageName()));
        String carNum = item.getCarNum();
        if (StrUtils.isStringValueOk(carNum)) {
            arrays.add(new KeyValueBean("是否驾车：", "是"));
            arrays.add(new KeyValueBean("车牌号码：", carNum));
        } else {
            arrays.add(new KeyValueBean("是否驾车：", "否"));
        }
        if (hasPwd) {
            arrays.add(new KeyValueBean("临时密码：",  item.getLockPassword()));
        }
        arrays.add(new KeyValueBean("审核状态：", getStatus(item)));
        return arrays;
    }
    /**
     * 获取请求状态
     *
     * @param item
     */
    private String getStatus(VisitorsBean.DataBean item) {
        //状态 1:审核中 2:通过 3：驳回
        int status = item.getState();
        String state = "";
        switch (status) {
            case 1:
                state = "审核中";
                break;
            case 2:
                state = "通过";
                break;
            case 3:
                state = "驳回";
                break;
            default:
                break;
        }
        return state;
    }
}
