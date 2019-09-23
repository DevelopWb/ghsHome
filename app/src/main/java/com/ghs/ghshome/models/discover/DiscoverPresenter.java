package com.ghs.ghshome.models.discover;

import android.util.Log;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.BannerListBean;
import com.ghs.ghshome.bean.CommunityListBean;
import com.ghs.ghshome.bean.CommunityParticularsBean;
import com.ghs.ghshome.bean.OfficialListBean;
import com.ghs.ghshome.bean.OfficialPlBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

public class DiscoverPresenter extends BasePresent<DiscoverContract.IDiscoverView> implements DiscoverContract.IDiscoverPresnerter{
    @Override
    public void getBannerList(int villageId ,final String tag) {

        HttpProxy.getInstance()
                .params("villageId",villageId)
                .postToNetwork(Contract.BANNER_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, BannerListBean.class), tag);
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
    public void getClickBanner(int bannerid,final  String tag) {

        HttpProxy.getInstance()
                .params("bannerId",bannerid)
                .postToNetwork(Contract.CLICKE_BANNER, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, BannerListBean.class), tag);
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
    public void getCommunityList(int userId, int villageId,final  String tag) {
        Log.i("TAG",userId+"------"+villageId);
        HttpProxy.getInstance()
                .params("userId",userId)
                .params("villageId",villageId)
                .postToNetwork(Contract.COMMUNITY_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CommunityListBean.class), tag);
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
    public void getCommUnityParticulars(int id, int userId,final String tag) {

        HttpProxy.getInstance()
                .params("id",id)
                .params("userId",userId)
                .postToNetwork(Contract.COMMUNITY_PARTICULAR, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CommunityParticularsBean.class), tag);
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
    public void getCommUnityApply(int id, int userId, int roomid, String leaveMessage,final String tag) {

        HttpProxy.getInstance()
                .params("id",id)
                .params("userId",userId)
                .params("roomId",roomid)
                .params("leaveMessage",leaveMessage)
                .postToNetwork(Contract.COMMUNITY_APPLY, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CommunityParticularsBean.class), tag);
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
    public void getOfficialList(final String tag) {
        HttpProxy.getInstance()
                .postToNetwork(Contract.OFFICAIAL_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, OfficialListBean.class), tag);
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
    public void getOfficialParticulars(int id, final String tag) {


        HttpProxy.getInstance()
                .params("id",id)
                .postToNetwork(Contract.OFFICAIAL_PATTICULARS, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, OfficialPlBean.class), tag);
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
    public void getMyEventList(int userId, int villageId, final String tag) {



        HttpProxy.getInstance()
                .params("userId",userId)
                .params("villageId",villageId)
                .postToNetwork(Contract.MY_EVENT_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CommunityListBean.class), tag);
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
