package com.ghs.ghshome.models.homePage;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.HomePageHouseKeeperBean;
import com.ghs.ghshome.bean.HouseKeeperBean;
import com.ghs.ghshome.bean.LifeServiceBean;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.bean.OfficialNoticeListBean;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.bean.RoomListBean;
import com.ghs.ghshome.bean.SeedRecordBean;
import com.ghs.ghshome.bean.SeedTaskBean;
import com.ghs.ghshome.bean.WorkOrderBean;
import com.ghs.ghshome.bean.WorkOrderDesBean;
import com.ghs.ghshome.models.main.MainContact;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2019/5/21 14:03
 * Description:This is HomePagePresent
 */
public class HomePagePresent extends BasePresent<HomePageContract.IHomePageView> implements HomePageContract.IHomePagePresent, RequestStatus {

    private final HomePageModel mHomePageModel;

    public HomePagePresent() {
        mHomePageModel = new HomePageModel();
    }

    @Override
    public void getUsuallyUsedService(int villageId, int userId, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("userId", userId)
                .postToNetwork(Contract.GET_USUALLY_USED_SERVICES, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, LifeServiceBean.class), tag);
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
    public void saveUsuallyUsedService(int userId, String menuIds, final String tag) {
        HttpProxy.getInstance()
                .params("menuIds", menuIds)
                .params("userId", userId)
                .postToNetwork(Contract.SAVE_USUALLY_USED_SERVICES, new NetResponseCallBack() {
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

    @Override
    public void getAllServices(int villageId, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_ALL_SERVICES, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, LifeServiceBean.class), tag);
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
    public void workOrderList(int offset, int limit, int villageId, int ghsUserId, final String tag) {
        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("villageId", villageId)
                .params("ghsUserId", ghsUserId)
                .postToNetwork(Contract.WORK_ORDER_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderBean.class), tag);
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
    public void workOrderdes(int serviceWorkId, final String tag) {
        HttpProxy.getInstance()
                .params("serviceWorkId", serviceWorkId)
                .postToNetwork(Contract.WORK_ORDER_DES, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderDesBean.class), tag);
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
    public void customerServerTel(final String tag) {
        HttpProxy.getInstance()
                .postToNetwork(Contract.CUSTOMER_SERVER_TEL, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyTelBean.class), tag);
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
    public void getHomePageHouseKeeperData(int villageId, int userId, int roomId, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("userId", userId)
                .params("roomId", roomId)
                .postToNetwork(Contract.GET_HOME_HOUSE_KEEPER_DATA, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, HomePageHouseKeeperBean.class), tag);
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
    public void getAllHouseKeeperData(int offset, int limit, int villageId, int userId, int roomId, final String tag) {
        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("villageId", villageId)
                .params("userId", userId)
                .params("roomId", roomId)
                .postToNetwork(Contract.GET_HOUSE_KEEPER_ALL_DATA, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, HouseKeeperBean.class), tag);
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
    public void getUnReadOfficialNoticeNum(int userId, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .postToNetwork(Contract.UNREAD_OFFICIAL_NOTICE, new NetResponseCallBack() {
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
    public void getOfficialNoticeList(int offset, int limit, int userId, final String tag) {
        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("userId", userId)
                .postToNetwork(Contract.OFFICIAL_NOTICE_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, OfficialNoticeListBean.class), tag);
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
    public void getBillDetail(int orderId, final String tag) {
        HttpProxy.getInstance()
                .params("orderId", orderId)
                .postToNetwork(Contract.LIFE_BILL_DETAIL, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), MyBillInfolBean.DataBean.class), tag);

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
    public void getSeedTaskList(int userId, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .postToNetwork(Contract.SEED_TASK_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, SeedTaskBean.class), tag);

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
    public void getSeedRecordList(int userId, int type, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("type", type)
                .postToNetwork(Contract.SEED_RECORD_LIST, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, SeedRecordBean.class), tag);

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
    public void getSeedAmount(int userId, final String tag) {
        mHomePageModel.getSeedAmount(userId, this, tag);
    }


    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o, tag);
        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }
    }

    /**
     * 获取小区列表
     *
     * @param userId
     */
    public void getUserRoomList(int userId) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .getToNetwork(Contract.LOGIN_USER_ROOMS, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, RoomListBean.class), MainContact.GET_ROOMS);
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
