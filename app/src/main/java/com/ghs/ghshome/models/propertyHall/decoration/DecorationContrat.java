package com.ghs.ghshome.models.propertyHall.decoration;

import com.ghs.ghshome.base.BaseViewInterface;

import java.util.Map;

public interface DecorationContrat {

    public String DECORATIONLIST = "decorationlist";//装修备案列表
    public String ADDFITMENTENTRY = "addfitmententry";//新建装修备案
    public String ADDFITMENTFIREENTRY = "addfitmentfireentry";//新建动火备案
    public String FITMENTPARTICULARS="fitmentParticulars";//装修备案详情
    public String FITMENTFIREPARTICULARS="fitmentfireparticulars";//动火备案详情

    public interface DecorationView extends BaseViewInterface {
    }

    interface IDecorationPresenter {

        void getDecorationList(int offset, int limit, int userId, int roomId, String tag);

        //装修备案详情
        void getFitmentParticulars(int id, String tag);

        //动火备案详情
        void getFitmentFireParticulars(int id, String tag);
        //新建装修备案
        void addFitment(Map<String,String> map,int ghsUserId,int villageId,int roomId,int content, int selfFlag,String tag);
        //新建动火备案
        void addFitmentFire(Map<String,String> map,int ghsUserId,int villageId,int roomId,String tag);

    }

}
