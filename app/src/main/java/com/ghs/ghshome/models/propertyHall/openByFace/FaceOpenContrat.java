package com.ghs.ghshome.models.propertyHall.openByFace;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * created by guohainan
 * created date 2019/6/18
 * application
 * package name com.ghs.ghshome.models.propertyHall.openByFace
 */
public interface FaceOpenContrat {
    String GETUSERFACEINFO = "getUfaceUserInfo";//获取人脸开门信息
    String REFRESHROLE = "refreshRole";//刷新范围
    String GETPHOTOLIST = "getPhotoList";//获取照片集合
    String DELETEPHOTO = "deletePhoto";//删除当前照片
    String ADD_PHOTO = "add_photo";//采集照片

    public interface IFaceOpenView extends BaseViewInterface {
    }

    interface IFaceOpenPresenter {
        //获取人脸开门信息
        void getUfcaceInfo(int userid, int villageId, int cellId, String tag);

        //刷新范围
        void refreshRole(int userId, int villageId, int cellId, String tag);

        // 获取人脸照片集合
        void getPhotoList(int userId, String tag);

        //删除人脸照片
        void deletePhoto(int userId, String ufacePhotoId, String tag);
        //采集人脸照片
        void addPhoto(int userId, String photoBase64, String tag);


    }


}
