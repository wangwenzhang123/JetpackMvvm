package com.example.library_user.ui;

import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.BaseFragment;
import com.example.library_base.ui.DataBindingConfig;
import com.example.library_user.R;
import com.example.library_user.model.SecondModel;

public class SecondFragment extends BaseFragment<SecondModel> {
   /* @Override
    protected SecondModel getModel() {
        return new SecondModel();
    }
*/
    @Override
    protected BaseDialog getDialog() {
        return null;
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_second,viewModel);
    }
}
