package com.ghs.ghshome.models.homePage.carPark;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.BoundCarsBean;
import com.ghs.ghshome.bean.CarParkRule;
import com.ghs.ghshome.bean.CarPayRecord;
import com.ghs.ghshome.bean.CouponsBean;
import com.ghs.ghshome.bean.OutParkBean;
import com.ghs.ghshome.bean.ParkSpaceBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2018/11/12 14:05
 * Description:This is CarParkPresent
 */
public class CarParkPresent extends BasePresent<CarParkContract.ICarParkView> implements CarParkContract.ICarParkPresent {
    @Override
    public void getCarPortRuleMsg(int villageId, final String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_PARK_RULE,  new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CarParkRule.class),tag);

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
    public void getBoundCars(int villageId, int roomId, int userId, final String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .params("userId", userId)
                .postToNetwork(Contract.GET_BOUND_CARS,  new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, BoundCarsBean.class),tag);
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
    public void getCarFeeRecord(int villageId, int userId,String carNum, final String tag) {


        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("userId", userId)
                .params("carNum", carNum)
                .postToNetwork(Contract.GET_CARS_FEE_RECORD,new NetResponseCallBack() {
            @Override
            public void onSuccess(String content) {
                if (getView() != null) {
                    getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CarPayRecord.class),tag);

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
    public void getParkSpaceList(int villageId, int userId, String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("userId", userId)
                .postToNetwork(Contract.PARKING_SPACE_LIST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, ParkSpaceBean.class),tag);

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
    public void outOfPark(int villageId,  int carId, int roomId, int userId,final  String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .params("userId", userId)
                .params("carId", carId)
                .postToNetwork(Contract.OUT_OF_PARK,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, OutParkBean.class),tag);
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
    public void unBindCar(int userId, int carId, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("carId", carId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())
                .postToNetwork(Contract.UNBIND_CAR,new NetResponseCallBack() {
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

    @Override
    public void bindCar(int villageId, int roomId, int userId, String carNum, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .params("userId", userId)
                .params("carNum", carNum)
                .postToNetwork(Contract.BIND_CAR,new NetResponseCallBack() {
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

    @Override
    public void getCouponListNoPage(int userId, double payMoney, int couponType, int state, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("payMoney", payMoney)
                .params("couponType", couponType)
                .params("state", state)
                .postToNetwork(Contract.MINE_COUPONS_NO_PAGE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CouponsBean.class),tag);

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
