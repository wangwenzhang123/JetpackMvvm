package com.example.library_user.model;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.library_base.base.model.BaseModel;
import com.example.library_base.net.bean.BaseAppEntity;
import com.example.library_user.ui.view.LoginView;
import com.example.library_user.bean.UserBean;
import com.example.library_user.data.SampleDataSource;

import io.reactivex.functions.Consumer;

public class LoginModel extends BaseModel implements LoginView {
    private SampleDataSource sampleDataSource;
    public MutableLiveData<UserBean> userBeanMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<String> title=new MutableLiveData<>();
    public ObservableField<String> name=new ObservableField<String>();
    public LoginModel() {
        sampleDataSource=new SampleDataSource();
    }
    {
        name.set("wangshen");
    }
    @Override
    public void getData() {
        title.setValue("王神呀");
         /* sampleDataSource.sampleRequest("1231")
                .compose(this.<BaseAppEntity<UserBean>>handleEverythingResult())
                .subscribe(new Consumer<BaseAppEntity<UserBean>>() {
                    @Override
                    public void accept(BaseAppEntity<UserBean> sampleBeanBaseAppEntity) throws Exception {
                       userBeanMutableLiveData.setValue(sampleBeanBaseAppEntity.getContent());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("LoginModel",name.get());
                    }
                });*/
    }
}
