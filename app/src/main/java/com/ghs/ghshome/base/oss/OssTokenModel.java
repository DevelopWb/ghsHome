package com.ghs.ghshome.base.oss;

import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.OssTokenBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/8/10 19:18
 * Description:This is OssTokenModel
 */
public class OssTokenModel implements OssInterface {

    @Override
    public void getOssToken(final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .postToNetwork(Contract.MINE_OSS_TOKEN,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, OssTokenBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });

    }
}
