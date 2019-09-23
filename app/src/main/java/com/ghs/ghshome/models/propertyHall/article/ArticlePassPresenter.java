package com.ghs.ghshome.models.propertyHall.article;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.PropertyListBean;
import com.ghs.ghshome.bean.PropertyParticularsBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

public class ArticlePassPresenter extends BasePresent<ArticlePassContract.ProPertyView> implements ArticlePassContract.IPrePertyPresent {


    @Override
    public void getPropertyList(int offset, int limit, int userId, int roomId, String tag) {
        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("userId", userId)
                .params("roomId", roomId)
                .postToNetwork(Contract.PROPERTYPASS_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyListBean.class), tag);
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
    public void getPropertyParticulars(int id, String tag) {
        HttpProxy.getInstance()
                .params("id", id)
                .postToNetwork(Contract.PROPERTY_PARTICULARS, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyParticularsBean.class), tag);
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
    public void addProperty(int villageId, int roomId, int ghsUserId, String content, String imageUrl, String tag) {


        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .params("ghsUserId", ghsUserId)
                .params("content", content)
                .params("imageUrl", imageUrl)
                .postToNetwork(Contract.ADDPROPERTY, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyParticularsBean.class), tag);
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

    //报修
    @Override
    public void addRepairs(int roomId, int ghsUserId, String content, String imageUrl, String hopeGotoTime, String tag) {

        HttpProxy.getInstance()
                .params("roomId", roomId)
                .params("ghsUserId", ghsUserId)
                .params("content", content)
                .params("imageUrl", imageUrl)
                .params("hopeGotoTime", hopeGotoTime)
                .postToNetwork(Contract.ADDREPAIRS, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyParticularsBean.class), tag);
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

    //投诉建议
    @Override
    public void saveComplainOrSuggest(int roomId, int ghsUserId, String content, String imageUrl, int serviceType, String tag) {


        HttpProxy.getInstance()
                .params("roomId", roomId)
                .params("ghsUserId", ghsUserId)
                .params("content", content)
                .params("imageUrl", imageUrl)
                .params("serviceType", serviceType)
                .postToNetwork(Contract.COMPALAINT_SUGGEST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyParticularsBean.class), tag);
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
