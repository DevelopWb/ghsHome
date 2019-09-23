package com.ghs.ghshome.models.homePage.villagenotice;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.VillageNoticeBean;
import com.ghs.ghshome.bean.VillageNoticeDetailBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/7/23 14:03
 * Description:This is VillageNoticePresent
 */
public class VillageNoticePresent extends BasePresent<VillageNoticeContract.IVillageNoticeView> implements VillageNoticeContract.IVillageNoticePresent {
    @Override
    public void getNoticeList(int offset,int limit,int userId, int villageId,final String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }
        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("userId", userId)
                .params("villageId", villageId)
                .postToNetwork(Contract.VILLAGE_NOTICE_LIST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, VillageNoticeBean.class), tag);
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
    public void readVillageNotice(int noticeId) {
        HttpProxy.getInstance()
                .params("noticeId", noticeId)
                .postToNetwork(Contract.READ_VILLAGR_NOTICE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, VillageNoticeDetailBean.class), RequestStatus.UPDATE);
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
    public void readOfficialNotice(int userId, int noticeId, String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("noticeId", noticeId)
                .postToNetwork(Contract.READ_OFFICIAL_NOTICE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, VillageNoticeDetailBean.class), tag);
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
}
