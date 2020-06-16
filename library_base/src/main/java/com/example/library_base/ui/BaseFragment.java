/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.library_base.ui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import com.example.library_base.BR;

import com.example.library_base.appContext.AppContext;
import com.example.library_base.base.model.BaseModel;
import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.fragment.NavHostFragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Create by KunMinX at 19/7/11
 */
public abstract class BaseFragment<M extends BaseModel> extends Fragment {
    protected AppCompatActivity mActivity;
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewDataBinding mBinding;
    private BaseDialog baseDialog;
    private SharedViewModel mSharedViewModel;
    protected M viewModel;
    //protected abstract M getModel();
    protected abstract BaseDialog getDialog();
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    protected abstract void initViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseDialog=getDialog();
        mSharedViewModel = ((AppContext) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity).get(SharedViewModel.class);
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        if (type != null) {
            Type[] actualTypeArguments = type.getActualTypeArguments();
            Class<M> tClass = (Class<M>) actualTypeArguments[0];
            viewModel= ((AppContext)getActivity().getApplicationContext()).getAppViewModelProvider(getActivity()).get(tClass);
        }
        initViewModel();
        //TODO 注意 liveData 的 lambda 回调中不可为空，不然会出现 Cannot add the same observer with different lifecycles 的现象，
        // 详见：https://stackoverflow.com/questions/47025233/android-lifecycle-library-cannot-add-the-same-observer-with-different-lifecycle
       // NetworkStateManager.getInstance().mNetworkStateCallback.observe(this, this::onNetworkStateChanged);
    }

    /*@SuppressWarnings("EmptyMethod")
    protected void onNetworkStateChanged(NetState netState) {
        //TODO 子类可以重写该方法，统一的网络状态通知和处理
    }*/

    protected abstract DataBindingConfig getDataBindingConfig();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DataBindingConfig dataBindingConfig = getDataBindingConfig();

        //TODO tip: DataBinding 严格模式：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这样的方式，来彻底解决 视图调用的一致性问题，
        // 如此，视图刷新的安全性将和基于函数式编程的 Jetpack Compose 持平。

        // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.vm, dataBindingConfig.getStateViewModel());
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewModel != null){
            viewModel.isShowLoadingData.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (baseDialog != null){
                        if (aBoolean){
                            baseDialog.show();
                        }else {
                            baseDialog.dismiss();
                        }
                    }
                }
            });
        }
    }

    protected void loadInitData() {

    }

    public boolean isDebug() {
        return mActivity.getApplicationContext().getApplicationInfo() != null &&
                (mActivity.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    protected void showLongToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(int stringRes) {
        showLongToast(mActivity.getApplicationContext().getString(stringRes));
    }

    protected void showShortToast(int stringRes) {
        showShortToast(mActivity.getApplicationContext().getString(stringRes));
    }

    protected <T extends ViewModel> T getFragmentViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        return mActivityProvider.get(modelClass);
    }

    protected NavController nav() {
        return NavHostFragment.findNavController(this);
    }

    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }
    protected ViewDataBinding getBinding() {//危险动作 暂时没想到其他好的方案
        return mBinding;
    }
}
