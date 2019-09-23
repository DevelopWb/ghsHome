package com.ghs.ghshome.models.propertyHall.decoration;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.DecorationBean;
import com.ghs.ghshome.bean.FitmentFireParticularsBean;
import com.ghs.ghshome.bean.FitmentParticularsBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

import java.util.Map;

public class DecorationPresenter extends BasePresent<DecorationContrat.DecorationView> implements DecorationContrat.IDecorationPresenter {


    @Override
    public void getDecorationList(int offset, int limit, int userId, int roomId, String tag) {

        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("userId", userId)
                .params("roomId", roomId)
                .postToNetwork(Contract.DECORATION_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, DecorationBean.class), tag);
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
    public void getFitmentParticulars(int id, String tag) {

        HttpProxy.getInstance()
                .params("id", id)
                .postToNetwork(Contract.FITMENT_PARTICULARS, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FitmentParticularsBean.class), tag);
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
    public void getFitmentFireParticulars(int id, String tag) {
        HttpProxy.getInstance()
                .params("id", id)
                .postToNetwork(Contract.FITMENT_FIRE_PARTICULARS, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FitmentFireParticularsBean.class), tag);
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
    //新建装修备案
    @Override
    public void addFitment(Map<String, String> map, int ghsUserId, int villageId, int roomId, int proxyFlag, int selfFlag, String tag) {
        HttpProxy.getInstance()
                .params("ghsUserId",ghsUserId)
                .params("villageId",villageId)
                .params("roomId",roomId)
                .params("proxyFlag",proxyFlag)
                .params("selfFlag",selfFlag)
                .params(map)
                .postToNetwork(Contract.DECORATION_FITMENT_SAVE, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FitmentFireParticularsBean.class), tag);
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
    public void addFitmentFire(Map<String, String> map, int ghsUserId, int villageId, int roomId, String tag) {


        HttpProxy.getInstance()
                .params("ghsUserId",ghsUserId)
                .params("villageId",villageId)
                .params("roomId",roomId)
                .params(map)
                .postToNetwork(Contract.DECORATION_FITMENT_FIRE_SAVE, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FitmentFireParticularsBean.class), tag);
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
