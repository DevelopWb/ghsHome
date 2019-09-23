package com.ghs.ghshome.models.homePage.carPark.bindcarnum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.models.homePage.carPark.CarParkContract;
import com.ghs.ghshome.models.homePage.carPark.CarParkPresent;
import com.ghs.ghshome.models.homePage.seed.SeedTaskAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by 8级大的狂风
 * created date 2018/9/28 19:43.
 * application  车牌信息
 */
public class BindCarNumActivity extends BaseActivity<CarParkContract.ICarParkView, CarParkPresent> implements View.OnClickListener, CarParkContract.ICarParkView {

    /**
     *
     */
    private TextView mCarInfoFirstNoTv;
    /**
     *
     */
    private TextView mCarInfoSecendNoTv;
    /**
     *
     */
    private TextView mCarInfoThirdNoTv;
    /**
     *
     */
    private TextView mCarInfoFourthNoTv;
    /**
     *
     */
    private TextView mCarInfoFifthNoTv;
    /**
     *
     */
    private TextView mCarInfoSixthNoTv;
    /**
     *
     */
    private TextView mCarInfoSeventhNoTv;
    /**
     * 确定
     */
    private TextView mCarInfoConfirmTv;
    /**
     *
     */
    private TextView mCarInfoEightthNoTv;
    private CarNumAdapter carInfoAdapter;

    private int clickedButton = 1;//1代表 第一个按钮  省份的简称
    private View footView;
    private CarNumAdapter deleteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        switch (PubUtil.ADD_CAR_NUM_ENTER) {
            case 1:
                initActionBar("添加车牌", null);

                break;
            default:
                initActionBar("绑定车辆", null);

                break;
        }
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.bind_car_num);

    }

    @Override
    public CarParkPresent creatPresenter() {
        return new CarParkPresent();
    }

    private void initView() {
        mCarInfoFirstNoTv = (TextView) findViewById(R.id.plate_num_first_no_tv);
        mCarInfoFirstNoTv.setOnClickListener(this);
        mCarInfoSecendNoTv = (TextView) findViewById(R.id.plate_num_secend_no_tv);
        mCarInfoSecendNoTv.setOnClickListener(this);
        mCarInfoThirdNoTv = (TextView) findViewById(R.id.plate_num_third_no_tv);
        mCarInfoThirdNoTv.setOnClickListener(this);
        mCarInfoFourthNoTv = (TextView) findViewById(R.id.plate_num_fourth_no_tv);
        mCarInfoFourthNoTv.setOnClickListener(this);
        mCarInfoFifthNoTv = (TextView) findViewById(R.id.plate_num_fifth_no_tv);
        mCarInfoFifthNoTv.setOnClickListener(this);
        mCarInfoSixthNoTv = (TextView) findViewById(R.id.plate_num_sixth_no_tv);
        mCarInfoSixthNoTv.setOnClickListener(this);
        mCarInfoSeventhNoTv = (TextView) findViewById(R.id.plate_num_seventh_no_tv);
        mCarInfoSeventhNoTv.setOnClickListener(this);
        RecyclerView mCarInfoRv = (RecyclerView) findViewById(R.id.plate_num_rv);
        carInfoAdapter = new CarNumAdapter(R.layout.car_info_item);
        GridLayoutManager managere = new GridLayoutManager(this, 6);
        mCarInfoRv.setLayoutManager(managere);
        mCarInfoRv.setAdapter(carInfoAdapter);
        mCarInfoConfirmTv = (TextView) findViewById(R.id.plate_num_confirm_tv);
        mCarInfoConfirmTv.setOnClickListener(this);
        if (1 == PubUtil.ADD_CAR_NUM_ENTER) {
            mCarInfoConfirmTv.setText("确定");
        } else {
            mCarInfoConfirmTv.setText("绑定");
        }

        mCarInfoEightthNoTv = (TextView) findViewById(R.id.plate_num_eightth_no_tv);
        mCarInfoEightthNoTv.setOnClickListener(this);
        addFootView();
        selectCarInfo(1);
        initConfirmButtomStatus();
        adapterClickListener();


    }

    /**
     * item点击事件
     */
    private void adapterClickListener() {
        deleteAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                selectNum(adapter, position);
            }
        });
        carInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                selectNum(adapter, position);

            }
        });
    }

    /**
     * 选择车牌号
     * @param adapter
     * @param position
     */
    private void selectNum(BaseQuickAdapter adapter, int position) {
        String text = (String) adapter.getData().get(position);
        if (!TextUtils.isEmpty(text)) {
            if (!"删除".equals(text)) {
                switch (clickedButton) {
                    case 1:
                        mCarInfoFirstNoTv.setText(text);
                        selectCarInfo(2);
                        break;
                    case 2:

                        mCarInfoSecendNoTv.setText(text);
                        selectCarInfo(3);
                        break;
                    case 3:

                        mCarInfoThirdNoTv.setText(text);
                        selectCarInfo(4);
                        break;
                    case 4:

                        mCarInfoFourthNoTv.setText(text);
                        selectCarInfo(5);
                        break;
                    case 5:

                        mCarInfoFifthNoTv.setText(text);
                        selectCarInfo(6);
                        break;
                    case 6:

                        mCarInfoSixthNoTv.setText(text);
                        selectCarInfo(7);
                        break;
                    case 7:

                        mCarInfoSeventhNoTv.setText(text);
                        selectCarInfo(8);
                        break;
                    case 8:

                        mCarInfoEightthNoTv.setText(text);
                        break;
                    default:
                        break;
                }
            }

        }
        initConfirmButtomStatus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plate_num_first_no_tv:

                selectCarInfo(1);
                break;
            case R.id.plate_num_secend_no_tv:
                selectCarInfo(2);
                break;
            case R.id.plate_num_third_no_tv:
                selectCarInfo(3);

                break;
            case R.id.plate_num_fourth_no_tv:
                selectCarInfo(4);

                break;
            case R.id.plate_num_fifth_no_tv:
                selectCarInfo(5);

                break;
            case R.id.plate_num_sixth_no_tv:
                selectCarInfo(6);

                break;
            case R.id.plate_num_seventh_no_tv:
                selectCarInfo(7);

                break;

            case R.id.plate_num_eightth_no_tv:
                selectCarInfo(8);

                break;
            case R.id.plate_num_confirm_tv:
                StringBuilder sb = new StringBuilder();
                String firstNo = mCarInfoFirstNoTv.getText().toString().trim();
                String secondNo = mCarInfoSecendNoTv.getText().toString().trim();
                String thirdNo = mCarInfoThirdNoTv.getText().toString().trim();
                String fourth = mCarInfoFourthNoTv.getText().toString().trim();
                String fifth = mCarInfoFifthNoTv.getText().toString().trim();
                String sixth = mCarInfoSixthNoTv.getText().toString().trim();
                String seventh = mCarInfoSeventhNoTv.getText().toString().trim();
                String eighth = mCarInfoEightthNoTv.getText().toString().trim();
                sb.append(firstNo);
                sb.append(secondNo);
                sb.append(thirdNo);
                sb.append(fourth);
                sb.append(fifth);
                sb.append(sixth);
                sb.append(seventh);
                sb.append(eighth);

                switch (PubUtil.ADD_CAR_NUM_ENTER) {
                    case 1:
                        Intent intent = new Intent();
                        intent.putExtra(ActivityResultManager.CAR_NUM, sb.toString().trim());
                        setResult(ActivityResultManager.ADD_VISITOR_ADD_CAR, intent);
                        finish();
                        break;
                    default:
                        UserInfoUtil userInfoUtil = UserInfoUtil.getInstance();
                        showProgressDialog();
                        getPresenter().bindCar(userInfoUtil.getVillageId(), userInfoUtil.getRoomId(), userInfoUtil.getUserId(), sb.toString().trim(), CarParkContract.BIND_CAR);

                        break;
                }


                break;
            case R.id.car_info_item2_delete_bt:
                //删除
                deleteSelectedNum();
                break;
            default:
                break;

        }
    }

    /**
     * 删除选中的车牌号
     */
    private void deleteSelectedNum() {
        switch (clickedButton) {
            case 1:
                mCarInfoFirstNoTv.setText("");
                break;
            case 2:
                mCarInfoFirstNoTv.setText("");
                selectCarInfo(1);
                break;
            case 3:
                mCarInfoSecendNoTv.setText("");
                selectCarInfo(2);
                break;
            case 4:
                mCarInfoThirdNoTv.setText("");
                selectCarInfo(3);
                break;
            case 5:
                mCarInfoFourthNoTv.setText("");
                selectCarInfo(4);
                break;
            case 6:
                mCarInfoFifthNoTv.setText("");
                selectCarInfo(5);
                break;
            case 7:
                mCarInfoSixthNoTv.setText("");
                selectCarInfo(6);
                break;
            case 8:
                mCarInfoSeventhNoTv.setText("");
                mCarInfoEightthNoTv.setText("");
                selectCarInfo(7);
                break;
            default:

                break;
        }
    }

    /**
     * 初始化确定按钮
     */
    private void initConfirmButtomStatus() {
        String firstNo = mCarInfoFirstNoTv.getText().toString().trim();
        String secondNo = mCarInfoSecendNoTv.getText().toString().trim();
        String thirdNo = mCarInfoThirdNoTv.getText().toString().trim();
        String fourthNo = mCarInfoFourthNoTv.getText().toString().trim();
        String fifthNo = mCarInfoFifthNoTv.getText().toString().trim();
        String sixthNo = mCarInfoSixthNoTv.getText().toString().trim();
        String seventhNo = mCarInfoSeventhNoTv.getText().toString().trim();
        if (!TextUtils.isEmpty(firstNo) && !TextUtils.isEmpty(secondNo) && !TextUtils.isEmpty(thirdNo)
                && !TextUtils.isEmpty(fourthNo) && !TextUtils.isEmpty(fifthNo) && !TextUtils.isEmpty(sixthNo)
                && !TextUtils.isEmpty(seventhNo)) {
            mCarInfoConfirmTv.setBackgroundResource(R.drawable.bt_selecter_default_green);
            mCarInfoConfirmTv.setClickable(true);
            mCarInfoConfirmTv.setFocusable(true);
        } else {
            mCarInfoConfirmTv.setBackgroundResource(R.drawable.rv_gray_shadow_shape);
            mCarInfoConfirmTv.setClickable(false);
            mCarInfoConfirmTv.setFocusable(false);
        }
    }

    /**
     * 选择车牌信息
     *
     * @param i
     */
    private void selectCarInfo(int i) {
        mCarInfoFirstNoTv.setBackgroundResource(R.drawable.plate_num_unselected_drawable_left);
        mCarInfoSecendNoTv.setBackgroundResource(R.drawable.plate_num_unselected_drawable_right);
        mCarInfoThirdNoTv.setBackgroundResource(R.drawable.plate_num_unselected_drawable_left);
        mCarInfoFourthNoTv.setBackgroundResource(R.drawable.plate_num_unselected_drawable_center);
        mCarInfoFifthNoTv.setBackgroundResource(R.drawable.plate_num_unselected_drawable_center);
        mCarInfoSixthNoTv.setBackgroundResource(R.drawable.plate_num_unselected_drawable_center);
        mCarInfoSeventhNoTv.setBackgroundResource(R.drawable.plate_num_unselected_drawable_right);
        mCarInfoEightthNoTv.setBackgroundResource(R.drawable.plate_num_unselect_drawable);
        switch (i) {
            case 1:
                clickedButton = 1;
                mCarInfoFirstNoTv.setBackgroundResource(R.drawable.plate_num_selected_drawable_left);
                initAdapterData(getCarInfoFirstData());
                break;
            case 2:
                clickedButton = 2;

                mCarInfoSecendNoTv.setBackgroundResource(R.drawable.plate_num_selected_drawable_right);
                initAdapterData(getCarInfoSecendData());
                break;
            case 3:
                clickedButton = 3;

                mCarInfoThirdNoTv.setBackgroundResource(R.drawable.plate_num_selected_drawable_left);
                initAdapterData(getCarInfoOtherData(3));
                break;
            case 4:
                clickedButton = 4;

                mCarInfoFourthNoTv.setBackgroundResource(R.drawable.plate_num_selected_drawable_center);
                initAdapterData(getCarInfoOtherData(4));
                break;
            case 5:
                clickedButton = 5;

                mCarInfoFifthNoTv.setBackgroundResource(R.drawable.plate_num_selected_drawable_center);
                initAdapterData(getCarInfoOtherData(5));

                break;
            case 6:
                clickedButton = 6;

                mCarInfoSixthNoTv.setBackgroundResource(R.drawable.plate_num_selected_drawable_center);
                initAdapterData(getCarInfoOtherData(6));

                break;
            case 7:
                clickedButton = 7;

                mCarInfoSeventhNoTv.setBackgroundResource(R.drawable.plate_num_selected_drawable_right);
                initAdapterData(getCarInfoOtherData(7));

                break;
            case 8:
                clickedButton = 8;
                mCarInfoEightthNoTv.setBackgroundResource(R.drawable.plate_num_select_drawable);
                initAdapterData(getCarInfoOtherData(8));

                break;
            default:
                break;
        }
    }

    /**
     * 适配器数据
     * @param data
     */
    private void initAdapterData(List<String> data) {
        carInfoAdapter.setNewData(data.subList(0,data.size()-4));
        deleteAdapter.setNewData(data.subList(data.size()-4,data.size()-1));


    }

    private void addFootView() {
        footView = LayoutInflater.from(this).inflate(R.layout.car_info_item2, null);
        RecyclerView item2Rv = footView.findViewById(R.id.bind_car_item2_rv);
        deleteAdapter = new CarNumAdapter(R.layout.car_info_item);
        initRecyclerview(item2Rv, deleteAdapter,     LinearLayoutManager.HORIZONTAL,false);
        Button deleteBt = footView.findViewById(R.id.car_info_item2_delete_bt);
        deleteBt.setOnClickListener(this);
        carInfoAdapter.addFooterView(footView);
    }

    /**
     * 获取车牌信息的第一组数据
     *
     * @return
     */
    private List<String> getCarInfoFirstData() {
        List<String> array = new ArrayList<>();
        array.add("京");
        array.add("津");
        array.add("冀");
        array.add("晋");
        array.add("蒙");
        array.add("辽");
        array.add("吉");
        array.add("黑");
        array.add("沪");
        array.add("苏");
        array.add("浙");
        array.add("皖");
        array.add("闽");
        array.add("赣");
        array.add("鲁");
        array.add("豫");
        array.add("鄂");
        array.add("湘");
        array.add("粤");
        array.add("桂");
        array.add("琼");
        array.add("渝");
        array.add("川");
        array.add("贵");
        array.add("云");
        array.add("藏");
        array.add("陕");
        array.add("甘");
        array.add("青");
        array.add("宁");
        array.add("新");
        array.add("台");
        array.add("");
        array.add("");
        return array;
    }

    /**
     * 获取车牌信息的第二组数据
     *
     * @return
     */
    private List<String> getCarInfoSecendData() {
        List<String> array = new ArrayList<>();
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("E");
        array.add("F");
        array.add("G");
        array.add("H");
        array.add("I");
        array.add("J");
        array.add("K");
        array.add("L");
        array.add("M");
        array.add("N");
        array.add("O");
        array.add("P");
        array.add("Q");
        array.add("R");
        array.add("S");
        array.add("T");
        array.add("U");
        array.add("V");
        array.add("W");
        array.add("X");
        array.add("Y");
        array.add("Z");
        array.add("");
        array.add("");
        return array;
    }

    /**
     * 获取车牌信息的第二组数据
     * position ==7  添加警 挂
     *
     * @return
     */
    private ArrayList<String> getCarInfoOtherData(int position) {
        ArrayList<String> array = new ArrayList<>();
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("E");
        array.add("F");
        array.add("G");
        array.add("H");
        array.add("I");
        array.add("J");
        array.add("K");
        array.add("L");
        array.add("M");
        array.add("N");
        array.add("O");
        array.add("P");
        array.add("Q");
        array.add("R");
        array.add("S");
        array.add("T");
        array.add("U");
        array.add("V");
        array.add("W");
        array.add("X");
        array.add("Y");
        array.add("Z");
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        array.add("7");
        array.add("8");
        array.add("9");
        array.add("0");
        if (7 == position) {
            array.add("警");
            array.add("挂");
        } else {
            array.add("");
            array.add("");
        }

        array.add("");
        array.add("");
        return array;
    }

    @Override
    protected void onDestroy() {
        clickedButton = 1;
        super.onDestroy();
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        showToast("绑定成功");
        execSeedTask(SeedTaskAdapter.SEED_TASK_BINDCAR, -1);
        setResult(ActivityResultManager.CAR_PARK_ADD_CAR);
        finish();
    }

    @Override
    public void onError(String tag) {
        showToast(tag);

    }

    @Override
    public void onBackPressed() {
        if (0 == PubUtil.ADD_CAR_NUM_ENTER) {
            setResult(ActivityResultManager.CAR_PARK_ADD_CAR);
        } else if (1 == PubUtil.ADD_CAR_NUM_ENTER) {

//            Intent intent = new Intent();
//            intent.putExtra(ActivityResultManager.CAR_NUM,"");
            setResult(ActivityResultManager.ADD_VISITOR_ADD_CAR);
        }

        super.onBackPressed();
    }
}
