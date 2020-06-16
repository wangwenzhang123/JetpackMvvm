package com.example.library_user.ui;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.BaseFragment;
import com.example.library_base.ui.DataBindingConfig;
import com.example.library_user.BR;
import com.example.library_user.model.LoginModel;
import com.example.library_user.R;
import com.example.library_user.dialog.SampleDialog;
import com.example.library_user.bean.UserBean;

public class LoginFragment extends BaseFragment<LoginModel> {
    /*@Override
    protected LoginModel getModel() {
        if (viewModel ==  null){
            viewModel=new LoginModel();
        }
        return viewModel;
    }*/

    @Override
    protected BaseDialog getDialog() {
        return new SampleDialog(getActivity());
    }

    @Override
    protected void initViewModel() {
        viewModel=getFragmentViewModel(LoginModel.class);
        viewModel.userBeanMutableLiveData.observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(UserBean userBean) {

            }
        });

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {

        return new DataBindingConfig(R.layout.fragment_uer,viewModel)
                .addBindingParam(BR.click,new Click());
    }
    public class Click{
        public void onSetText(){
            viewModel.getData();
            Bundle bundle=new Bundle();
            bundle.putString("key","王神");
            //nav().navigate(R.id.action_mainFragment_to_featureOneFragment,bundle);
        }
    }
}
