package com.ghs.ghshome.models.propertyHall.openByFace;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.FacePhotoListBean;
import com.ghs.ghshome.bean.FaceUfaceUserInfoBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * created by guohainan
 * created date 2019/6/18
 * application
 * package name com.ghs.ghshome.models.propertyHall.openByFace
 */
public class FaceOpenPresenter extends BasePresent<FaceOpenContrat.IFaceOpenView> implements FaceOpenContrat.IFaceOpenPresenter {
    @Override
    public void getUfcaceInfo(int userid,int villageId, int cellId, String tag) {

        HttpProxy.getInstance()
                .params("userId", userid)
                .params("villageId", villageId)
                .params("cellId", cellId)
                .postToNetwork(Contract.GETUSERFACEINFO, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FaceUfaceUserInfoBean.class), tag);
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
    public void refreshRole(int userId, int villageId, int cellId, String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", villageId)
                .params("cellId", cellId)
                .postToNetwork(Contract.REFRESHROLE, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FaceUfaceUserInfoBean.class), tag);
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
    public void getPhotoList(int userId, String tag) {

        HttpProxy.getInstance()
                .params("userId", userId)
                .postToNetwork(Contract.GETPHOTOLIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FacePhotoListBean.class), tag);
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
    public void deletePhoto(int userId, String ufacePhotoId, String tag) {


        HttpProxy.getInstance()
                .params("userId", userId)
                .params("ufacePhotoId", ufacePhotoId)
                .postToNetwork(Contract.DELETEPHOTO, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FacePhotoListBean.class), tag);
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
    public void addPhoto(int userId, String photoBase64, String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("photoBase64", photoBase64)
                .postToNetwork(Contract.ADD_PHOTO, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("", tag);
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
