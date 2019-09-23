package com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.OpenRecordBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/5 10:32
 * Description:This is OpenRecordPresent
 */
public class OpenRecordPresent extends BasePresent<OpenRecordContract.OpenRecordView> implements OpenRecordContract.IOpenRecordPresent {


    @Override
    public void getOpenRecordList(int userId, int roomId, int roleType, String startDate, String endDate,final String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }

        HttpProxy.getInstance()
                .params("userId",userId)
                .params("roleType",roleType)
                .params("roomId",roomId)
                .params("startDate",startDate)
                .params("endDate",endDate)
                .postToNetwork(Contract.OPEN_RECORD_LIST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            if (getView() != null) {
                                getView().stopLoading(tag);
                                OpenRecordBean openRecordBean = GsonManager.getInstance().parseJsonToBean(content, OpenRecordBean.class);
                                getView().updateView(sortData(openRecordBean), tag);
                            }
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
    public void allowedToSee(int userRoomId, int showLockLog,final String tag) {
        HttpProxy.getInstance()
                .params("userRoomId", userRoomId)
                .params("showLockLog", showLockLog)
                .postToNetwork(Contract.OPEN_RECORD_SHOWABLE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("",tag);
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
     * 重新整理数据
     *
     * @param bean
     */
    private List<Object> sortData(OpenRecordBean bean) {
        List<OpenRecordBean.DataBean> arrays = bean.getData();
        List<Object> arrays_obj = new ArrayList<>();
        for (OpenRecordBean.DataBean array : arrays) {
            List<OpenRecordBean.DataBean.LogDOListBean> logs = array.getLogDOList();
            arrays_obj.add(array.getDateTitle());
            if (logs != null && logs.size() > 0) {
                for (OpenRecordBean.DataBean.LogDOListBean log : logs) {
                    arrays_obj.add(log);
                }
            }
        }

        return arrays_obj;
    }


}
