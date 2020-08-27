package com.example.library_user.adapter;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library_base.ui.adapter.BaseBindingAdapter;
import com.example.library_base.util.ToastUtils;
import com.example.library_user.BR;
import com.example.library_user.R;
import com.example.library_user.bean.ImageBean;
import com.example.library_user.databinding.ItemSampleBinding;
import com.example.library_user.ui.SampleListFragement;

import java.util.List;

public class SampleAdapter extends BaseBindingAdapter<ImageBean, ItemSampleBinding> {


    public SampleAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_sample;
    }

    @Override
    protected void onBindItem(ItemSampleBinding binding, ImageBean item, RecyclerView.ViewHolder holder) {
        binding.setVariable(BR.click,new Click());
        binding.setImage(item);
    }
    public class Click{
        public void onClick(ImageBean imageBean){
            ToastUtils.showToast(mContext,imageBean.getUrl()+"");
        }
    }
}
