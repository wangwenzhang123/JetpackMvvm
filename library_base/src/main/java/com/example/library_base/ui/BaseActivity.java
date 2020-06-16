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

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.library_base.BR;
import com.example.library_base.appContext.AppContext;
import com.example.library_base.base.model.BaseModel;
import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.manager.NetworkStateManager;
import com.example.library_base.util.AdaptScreenUtils;
import com.example.library_base.util.BarUtils;
import com.example.library_base.util.ScreenUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Create by KunMinX at 19/8/1
 */
public abstract class BaseActivity<M extends BaseModel> extends AppCompatActivity {
    private ViewModelProvider mActivityProvider;
    private ViewDataBinding mBinding;
    protected BaseDialog baseDialog;
    private SharedViewModel mSharedViewModel;
    protected abstract void initViewModel();
    protected abstract DataBindingConfig getDataBindingConfig();
    protected M viewModel;
    protected abstract BaseDialog getDialog();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(this, true);
        getLifecycle().addObserver(NetworkStateManager.getInstance());
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        if (type != null) {
            Type[] actualTypeArguments = type.getActualTypeArguments();
            Class<M> tClass = (Class<M>) actualTypeArguments[0];
            viewModel= ((AppContext)getApplicationContext()).getAppViewModelProvider(this).get(tClass);
        }
        initViewModel();
        baseDialog=getDialog();
        mSharedViewModel = ((AppContext) getApplicationContext()).getAppViewModelProvider(this).get(SharedViewModel.class);
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        binding.setLifecycleOwner(this);
        binding.setVariable(BR._all, dataBindingConfig.getStateViewModel());
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
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
    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    protected void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(int stringRes) {
        showLongToast(getApplicationContext().getString(stringRes));
    }

    protected void showShortToast(int stringRes) {
        showShortToast(getApplicationContext().getString(stringRes));
    }

    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }
    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }
}
