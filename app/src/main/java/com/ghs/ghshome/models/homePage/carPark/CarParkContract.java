package com.ghs.ghshome.models.homePage.carPark;


import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/10/23 15:04
 * Description:This is CarParkContract
 */
public interface CarParkContract {
    String GET_PARK_RULE = "getCarPortRuleMsg";//获取停车规则
    String GET_BOUND_CARS = "get_carList";//获取绑定的车辆信息
    String GET_CARS_FEE_RECORD = "carFeeList";//获取车费记录
    String OUT_OF_PARK = "ownerDriveOff";//出场
    String UNBIND_CAR = "removeCar";//解除绑定
    String BIND_CAR = "addcar";//绑定
    String COUPON = "coupon";//优惠券
    String PARKING_SPACE_LIST = "/carpark/carportList";//车位列表

    interface ICarParkView extends BaseViewInterface {
    }

    interface ICarParkPresent {

        void getCarPortRuleMsg(int villageId, String tag);

        void getBoundCars(int villageId, int roomId, int userId, String tag);

        void getCarFeeRecord(int villageId, int userId,String carNum,String tag);

        /**
         * 获取停车位
         * @param villageId
         * @param userId
         * @param tag
         */
        void getParkSpaceList(int villageId, int userId, String tag);

        void outOfPark(int villageId, int carId, int roomId, int userId, String tag);

        void unBindCar(int userId, int carId, String tag);

        void bindCar(int villageId, int roomId, int userId, String carNum, String tag);

        void getCouponListNoPage(int userId, double payMoney, int couponType, int state,String  tag);

    }
}
