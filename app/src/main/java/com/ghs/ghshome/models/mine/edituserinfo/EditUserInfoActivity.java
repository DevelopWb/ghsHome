package com.ghs.ghshome.models.mine.edituserinfo;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.oss.OssUploadManager;
import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.custom.MineEditCustomView;
import com.ghs.ghshome.models.homePage.seed.SeedTaskAdapter;
import com.ghs.ghshome.models.justtalk.JCManager;
import com.ghs.ghshome.models.mine.edituserinfo.modifymobile.ModifyMobileActivity;
import com.ghs.ghshome.models.mine.edituserinfo.modifynIckname.ModifyNickNameActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.FileUtil;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.ImageUtil;
import com.ghs.ghshome.tools.RxScheduler;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;
import com.ghs.ghshome.wxapi.weixinauth.IWeiXinAuthCallBack;
import com.ghs.ghshome.wxapi.weixinauth.IWeiXinAuthListener;
import com.orhanobut.hawk.Hawk;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * created by tobato
 * created date 2019/5/31 15:40.
 * application   编辑个人资料
 */
public class EditUserInfoActivity extends BaseActivity<EditUserInfoContract.IEditUserInfoView, EditUserInfoPresent> implements EditUserInfoContract.IEditUserInfoView, IWeiXinAuthCallBack, RequestStatus, View.OnClickListener {

    private MineEditCustomView mEditMobileMcv;
    private MineEditCustomView mEditWeixinNameMcv;
    private MineEditCustomView mEditNickNameMcv;
    private ImageView mEditHeardIv;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetDialog bottomSheetDialog_weixin;
    /**
     * 桥不是
     */
    private String unionid = "";
    private String weixin_name = "";
    private LinearLayout mEditUserHeadPicLl;
    private LinearLayout mEditUserNameLl;
    private LinearLayout mEditMobileLl;
    private OssUploadManager ossUploadManager;
    private TextView mEditHeadTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        mEditHeardIv = (ImageView) findViewById(R.id.edit_head_iv);
        mEditMobileMcv = (MineEditCustomView) findViewById(R.id.edit_mobile_mcv);
        mEditWeixinNameMcv = (MineEditCustomView) findViewById(R.id.edit_weixin_name_mcv);
        mEditNickNameMcv = (MineEditCustomView) findViewById(R.id.edit_user_name_mcv);
        mEditNickNameMcv.gettitleBarRightTv().setText(UserInfoUtil.getInstance().getNickName());
        mEditUserHeadPicLl = (LinearLayout) findViewById(R.id.edit_user_head_pic_ll);
        mEditUserHeadPicLl.setOnClickListener(this);
        mEditUserNameLl = (LinearLayout) findViewById(R.id.edit_user_name_ll);
        mEditUserNameLl.setOnClickListener(this);
        mEditMobileLl = (LinearLayout) findViewById(R.id.edit_mobile_ll);
        mEditMobileLl.setOnClickListener(this);
        mEditHeadTv = (TextView) findViewById(R.id.edit_head_tv);
    }


    @Override
    public void getDate() {
        ossUploadManager = OssUploadManager.getInstance(this);
        setHeadPicBgResource(mEditHeardIv, mEditHeadTv, true);
        mEditMobileMcv.gettitleBarRightTv().setText(StrUtils.formatMobile(UserInfoUtil.getInstance().getMobile()));
        String unition = UserInfoUtil.getInstance().getUnionId();
        if (StrUtils.isStringValueOk(unition)) {
            String name = Hawk.get(String.valueOf(UserInfoUtil.getInstance().getUserId()) + HawkProperty.WEI_XIN_NAME);
            mEditWeixinNameMcv.gettitleBarRightTv().setText(name);

        } else {
            mEditWeixinNameMcv.gettitleBarRightTv().setText("未绑定");
        }

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("编辑个人资料", "");

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_edit_user_info);
    }


    /**
     * 上传图片
     */
    private void uploadPicInfo(String picPath) {
        String iconServerPath = ossUploadManager.uploadPicInfo(picPath);
        getPresenter().updateUserInfo(UserInfoUtil.getInstance().getUserId(), getParamas("headImage", iconServerPath), EditUserInfoContract.MODIFY_HEAD_IMAGE);
    }


    @Override
    public EditUserInfoPresent creatPresenter() {
        return new EditUserInfoPresent();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                String imagePath = FileUtil.getHeadPicDir(this)
                        + FileUtil.SAVED_PICTURE_NAME;
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(imagePath);
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                GlideManagerUtil.getInstance(this)
                        .loadCirclePic(userHeadPic, R.mipmap.default_user_head_icon, mEditHeardIv);
                uploadPicInfo(userHeadPic);
            } else if (requestCode == ActivityResultManager.SYSTEM_PICTURE) {
                ContentResolver resolver = getContentResolver();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                try {
                    InputStream inputStream = resolver.openInputStream(data.getData());

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                    GlideManagerUtil.getInstance(this)
                            .loadCirclePic(userHeadPic, R.mipmap.default_user_head_icon, mEditHeardIv);
                    uploadPicInfo(userHeadPic);
                    uploadPicInfo(userHeadPic);

                } catch (FileNotFoundException e) {


                    e.printStackTrace();
                }
            }
        } else if (ActivityResultManager.EDIT_USER_MODIFY_MOBILE == resultCode) {
            mEditMobileMcv.gettitleBarRightTv().setText(data.getStringExtra("MODIFYED_MOBILE"));
            RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                @Override
                public void doOnIOThread() {
//                boolean selectVillage = UserInfoUtil.getInstance().isSelectedCurrentVillage();
//                if (selectVillage) {
                    JCManager.getInstance().client.login(mUserInfoUtil.getMobile(), "1000");
//                }
                }
            });

        } else if (ActivityResultManager.MODIFY_NICK_NAME == resultCode) {
            mEditNickNameMcv.gettitleBarRightTv().setText(data.getStringExtra(ActivityResultManager.NICK_NAME));


        }


    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case EditUserInfoContract.MODIFY_UNIONID:
                mEditWeixinNameMcv.gettitleBarRightTv().setText("已绑定");
                break;
            case EditUserInfoContract.MODIFY_HEAD_IMAGE:
                showToast("头像更换成功");
                LoginBean loginBean = (LoginBean) o;
                Hawk.put(HawkProperty.LOGIN_BEAN, loginBean);
                execSeedTask(SeedTaskAdapter.SEED_TASK_UPLOAD_HEAD_PIC, -1);
                break;
            default:
                break;
        }

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
    }

    @Override
    public void onError(String tag) {
        if (StrUtils.isStringValueOk(tag)) {
            if (tag.contains("微信号已被其他人占用")) {
                mEditWeixinNameMcv.gettitleBarRightTv().setText("未绑定");

            }
            showToast(tag);
        }

    }

    @Override
    public void AuthFinished(Map<String, String> data) {
        unionid = data.get("unionid");
        weixin_name = data.get("name");
        if (StrUtils.isStringValueOk(unionid)) {
            Hawk.put(HawkProperty.WEI_XIN_UNIONID, unionid);
        }
        if (StrUtils.isStringValueOk(weixin_name)) {
            Hawk.put(String.valueOf(UserInfoUtil.getInstance().getUserId()) + HawkProperty.WEI_XIN_NAME, weixin_name);
        }

        getPresenter().updateUserInfo(UserInfoUtil.getInstance().getUserId(), getParamas("unionId", unionid), EditUserInfoContract.MODIFY_UNIONID);

    }

    /**
     * 获取请求参数
     *
     * @return
     */
    private Map<String, String> getParamas(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    @Override
    public void AuthError(Throwable throwable) {

        String msg = throwable.getMessage();
        if (StrUtils.isStringValueOk(msg)) {
            if (msg.contains("2008")) {
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setMessage("您还没有安装微信，请安装后重试")
                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
                setAlertDialogHeightWidth(alertDialog, -1, 0);

            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.EDIT_USER_INFO);
        super.onBackPressed();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
                takePicturesFromActivity(bottomSheetDialog);

                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮
                selectPicturesFromSysPhoto(bottomSheetDialog);
                break;

            case R.id.mine_edit_bind_weixin_cancel_tv:////
                if (bottomSheetDialog_weixin.isShowing()) {
                    bottomSheetDialog_weixin.dismiss();
                }


                break;
            case R.id.edit_weixin_name_mcv:
                String status = mEditWeixinNameMcv.gettitleBarRightTv().getText().toString().trim();
                if ("已绑定".equals(status)) {
                    return;
                }
                View weixin_view = LayoutInflater.from(this).inflate(R.layout.mine_edit_bound_weixin, null);
                bottomSheetDialog_weixin = new BottomSheetDialog(this);
                bottomSheetDialog_weixin.setCancelable(false);
                bottomSheetDialog_weixin.setContentView(weixin_view);
                bottomSheetDialog_weixin.show();
                weixin_view.findViewById(R.id.mine_edit_bind_weixin_iv).setOnClickListener(this);
                weixin_view.findViewById(R.id.mine_edit_bind_weixin_cancel_tv).setOnClickListener(this);
                break;
            case R.id.mine_edit_bind_weixin_iv:////绑定微信中的跳转至微信按钮
                if (bottomSheetDialog_weixin.isShowing()) {
                    bottomSheetDialog_weixin.dismiss();
                }
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new IWeiXinAuthListener(this));

                break;
            case R.id.edit_user_head_pic_ll:
                if (isNetWorkConnected()) {
                    View view = LayoutInflater.from(this).inflate(R.layout.mine_edit_update_head_pic, null);
                    bottomSheetDialog = new BottomSheetDialog(this);
                    bottomSheetDialog.setCancelable(false);
                    bottomSheetDialog.setContentView(view);
                    bottomSheetDialog.show();
                    view.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(this);
                    view.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(this);
                    view.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(this);
                }
                break;
            case R.id.edit_user_name_ll:
                Intent intent_nick = new Intent(this, ModifyNickNameActivity.class);
                intent_nick.putExtra(ActivityResultManager.NICK_NAME, mEditNickNameMcv.gettitleBarRightTv().getText().toString().trim());
                startActivityForResult(intent_nick, ActivityResultManager.MODIFY_NICK_NAME);
                break;
            case R.id.edit_mobile_ll:
                startActivityForResult(new Intent(this, ModifyMobileActivity.class), ActivityResultManager.EDIT_USER_MODIFY_MOBILE);

                break;
        }

    }

}


