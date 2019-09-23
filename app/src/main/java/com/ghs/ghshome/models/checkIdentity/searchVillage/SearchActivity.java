package com.ghs.ghshome.models.checkIdentity.searchVillage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.VillageListBean;
import com.ghs.ghshome.custom.IrregularTextView;
import com.ghs.ghshome.models.checkIdentity.CheckIdentityActivity;
import com.ghs.ghshome.models.checkIdentity.CheckIdentityContract;
import com.ghs.ghshome.models.checkIdentity.CheckIdentityPresent;
import com.ghs.ghshome.models.checkIdentity.SelectVillageAdapter;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/9/10 14:54.
 * application   搜索
 */
public class SearchActivity extends BaseActivity<CheckIdentityContract.ICheckIdentityView, CheckIdentityPresent> implements CheckIdentityContract.ICheckIdentityView, View.OnClickListener {


    private ImageView mSearchIv;
    private EditText mSearchContentEt;
    private ImageView mClearContentIv;
    /**
     * 取消
     */
    private TextView mCancelSearchTv;
    /**
     * 历史搜索
     */
    private TextView mSearchHistoryTitleTv;
    private RecyclerView mSearchContentRv;
    /**
     * 清空历史记录
     */
    private TextView mSearchHistoryClearTv;
    private SelectVillageAdapter searchedContentAdapter;
    private RecyclerView mSearchHisRv;
    private LinearLayout mSearchContentLl;
    private IrregularTextView mBaseSearchItv;
    private LinearLayout mSearchHistoryLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_search);
        initView();
    }

    @Override
    public CheckIdentityPresent creatPresenter() {
        return new CheckIdentityPresent();
    }

    @Override
    public void getDate() {
        if (Hawk.contains(HawkProperty.SEARCH_HIS_LIST)) {
            mSearchHistoryLl.setVisibility(View.VISIBLE);
            List<String> hisSearchData = Hawk.get(HawkProperty.SEARCH_HIS_LIST);
            if (hisSearchData != null) {
                String [] data = new String[hisSearchData.size()];
                for (int i = 0; i < hisSearchData.size(); i++) {
                    String  item = hisSearchData.get(i);
                    data[i] =item;
                }
                mBaseSearchItv.initViews(data, new IrregularTextView.OnItemClick() {
                    @Override
                    public void onClick(String content) {
                        setViewsVisible(mClearContentIv);
                        mSearchContentEt.setText(content);
                        search(content);
                    }
                });

            }
        } else {
            mSearchHistoryLl.setVisibility(View.GONE);
        }
    }

    private void initView() {
        mSearchIv = (ImageView) findViewById(R.id.search_iv);
        mSearchIv.setOnClickListener(this);
        mSearchContentEt = (EditText) findViewById(R.id.search_content_et);
        mClearContentIv = (ImageView) findViewById(R.id.clear_content_iv);
        mClearContentIv.setOnClickListener(this);
        mCancelSearchTv = (TextView) findViewById(R.id.cancel_search_tv);
        mCancelSearchTv.setOnClickListener(this);
        mSearchHistoryTitleTv = (TextView) findViewById(R.id.search_history_title_tv);
        mSearchContentRv = (RecyclerView) findViewById(R.id.search_content_rv);
        mSearchHistoryClearTv = (TextView) findViewById(R.id.search_history_clear_tv);
        mSearchHistoryClearTv.setOnClickListener(this);
        searchedContentAdapter = new SelectVillageAdapter(R.layout.simple_text_item);
        initRecyclerview(mSearchContentRv, searchedContentAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true, mSearchContentRv, false, false);
        searchedContentAdapter.setEmptyView(getAdapterEmptyView("暂无记录"));
        searchedContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VillageListBean.DataBean dataBean = (VillageListBean.DataBean) adapter.getData().get(position);
                IntentUtil.getInstance().startActivityWithParcelableData(dataBean, SearchActivity.this, CheckIdentityActivity.class);

            }
        });
        mSearchContentEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String contnet = v.getText().toString().trim();
                    if (StrUtils.isStringValueOk(contnet)) {
                        search(contnet);
                    } else {
                        showToast("请输入要搜索的社区名称");
                    }

                    return true;
                }
                return false;
            }
        });
        mSearchContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString().trim();
                if (StrUtils.isStringValueOk(content)) {
                    setViewsVisible(mClearContentIv);
                } else {
                    getDate();
                    setViewsInvisible(true, mClearContentIv, mSearchContentLl);
                }

            }
        });
        mSearchContentLl = (LinearLayout) findViewById(R.id.search_content_ll);
        mBaseSearchItv = (IrregularTextView) findViewById(R.id.base_search_itv);
        mSearchHistoryLl = (LinearLayout) findViewById(R.id.search_history_ll);
    }

    /**
     * 开始搜索
     *
     * @param contnet
     */
    private void search(String contnet) {
        //获取保存本地的历史记录
        List<String> arrays = null;
        if (Hawk.contains(HawkProperty.SEARCH_HIS_LIST)) {
            arrays = Hawk.get(HawkProperty.SEARCH_HIS_LIST);
        } else {
            arrays = new ArrayList<>();
        }
        if (!arrays.contains(contnet)) {
            arrays.add(contnet);
        }
        Hawk.put(HawkProperty.SEARCH_HIS_LIST, arrays);
        //请求
        getPresenter().searchVillage(contnet, CheckIdentityContract.SEARCH_VILLAGE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_iv:
                //开始搜索
                search(mSearchContentEt.getText().toString().trim());
                break;
            case R.id.clear_content_iv:
                //清除内容
                mSearchContentEt.setText("");
                mSearchContentEt.setHint("输入要搜索的社区名称");
                setViewsInvisible(true, mClearContentIv);
                break;
            case R.id.cancel_search_tv:
                //取消搜索
                onBackPressed();
                break;
            case R.id.search_history_clear_tv:
                //清空历史记录
                searchedContentAdapter.setNewData(null);
                Hawk.delete(HawkProperty.SEARCH_HIS_LIST);
                getDate();
                break;
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
        VillageListBean listBean = (VillageListBean) o;
        if (listBean != null) {
            List<VillageListBean.DataBean> villages = listBean.getData();
            if (villages != null) {
                mSearchContentLl.setVisibility(View.VISIBLE);
                searchedContentAdapter.setNewData(villages);
            }
        }
    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void onBackPressed() {
        hideKeyboard(mSearchContentEt);
        super.onBackPressed();
    }
}
