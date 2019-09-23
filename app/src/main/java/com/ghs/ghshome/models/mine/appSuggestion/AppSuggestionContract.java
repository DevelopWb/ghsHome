package com.ghs.ghshome.models.mine.appSuggestion;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/7/19 15:26
 * Description:This is AppSuggestionContract
 */
public interface AppSuggestionContract {
    String OSS_TOKEN = "oss_token";

    interface IMySuggestionView extends BaseViewInterface {

    }

    interface IMySuggestionPresent {
        void uploadSuggestion(int userId, int roomId, int villageId, String contnet, String imageUrl1, String imageUrl2, String imageUrl3,String tag);

    }


}
