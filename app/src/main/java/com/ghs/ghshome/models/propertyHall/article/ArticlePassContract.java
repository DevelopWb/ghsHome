package com.ghs.ghshome.models.propertyHall.article;

import com.ghs.ghshome.base.BaseViewInterface;

public interface ArticlePassContract {
    public String PROPERTYPASSLIST = "propertyPassList";//物品放行列表
    public String PRoPRETYPARTICULARS = "propertyparticulars";//物品详情
    public String ADDPROPERTY = "addProperty";//添加物品
    public String ADDREPAIRS = "addrepairs";//添加报修
    public String COMPLAIN_SUGGEST = "complain_suggest";//投诉建议

    public interface ProPertyView extends BaseViewInterface {
    }

    interface IPrePertyPresent {

        void getPropertyList(int offset, int limit, int userId, int roomId, String tag);

        void getPropertyParticulars(int id, String tag);

        void addProperty(int villageId, int roomId, int ghsUserId, String content, String imageUrl, String tag);

        //报修
        void addRepairs(int roomId, int ghsUserId, String content, String imageUrl, String hopeGotoTime, String tag);
        //投诉建议
        void saveComplainOrSuggest(int roomId,int ghsUserId,String content,String imageUrl,int serviceType,String tag);
    }


}
