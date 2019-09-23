package com.ghs.ghshome.models.checkIdentity;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.CellsBean;
import com.ghs.ghshome.bean.CitiesBean;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.bean.RoomsBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.bean.VillageListBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2019/9/20 14:16
 * Description:This is CheckIdentityPresent
 */
public class CheckIdentityPresent  extends BasePresent<CheckIdentityContract.ICheckIdentityView> implements CheckIdentityContract.ICheckIdentityPresent {
    @Override
    public void getVillageList(String cityName, String tag) {
        HttpProxy.getInstance().params("cityName", cityName)
                .postToNetwork(Contract.GET_VILLAGE_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, VillageListBean.class), tag);
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
    public void getUserName(String mobile, String tag) {
        HttpProxy.getInstance()
                .params("mobile", mobile)
                .postToNetwork(Contract.GET_USER_NAME, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(PubUtil.getServerData(content), tag);
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
    public void searchVillage(String searchKey, String tag) {
        HttpProxy.getInstance()
                .params("searchKey", searchKey)
                .postToNetwork(Contract.SEARCH_VILLAGE, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, VillageListBean.class), tag);
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
    public void getCellList(int villageId, String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_CELL_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CellsBean.class),tag);
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
    public void getRoomList(int cellId, String tag) {
        HttpProxy.getInstance()
                .params("cellId", cellId)
                .postToNetwork(Contract.GET_ROOM_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, RoomsBean.class), tag);
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
    public void getCityList(String tag) {
        HttpProxy.getInstance()
                .postToNetwork(Contract.GET_CITY_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CitiesBean.class), tag);
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
    public void submitCheck(int userId, int roomId, int roleType, String mobile, String fullName, String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("roomId", roomId)
                .params("roleType", roleType)
                .params("mobile", mobile)
                .params("fullName", fullName)
                .postToNetwork(Contract.SUBMIT_CHECK, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), UserAndRoomBean.DataBean.GhsUserRoomDOBean.class), tag);
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
    public void reSubmitCheck(int userRoomId, int roomId, int roleType, String tag) {
        HttpProxy.getInstance()
                .params("userRoomId", userRoomId)
                .params("roomId", roomId)
                .params("roleType", roleType)
                .postToNetwork(Contract.RESUBMIT_CHECK, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), UserAndRoomBean.DataBean.GhsUserRoomDOBean.class), tag);
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
     * 物业电话
     * @param tag
     */
    public void customerServerTel(final String tag) {
        HttpProxy.getInstance()
                .postToNetwork(Contract.CUSTOMER_SERVER_TEL,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyTelBean.class),tag);
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
