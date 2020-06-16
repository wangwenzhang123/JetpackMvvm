package com.example.library_base.ui.binding;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.library_base.R;
import com.example.library_base.ui.adapter.CommonViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Create by KunMinX at 2020/3/13
 */
public class TabPageBindingAdapter {

    @BindingAdapter(value = {"initTabAndPage"}, requireAll = false)
    public static void initTabAndPage(TabLayout tabLayout, boolean initTabAndPage) {
        int count = tabLayout.getTabCount();
        String[] title = new String[count];
        for (int i = 0; i < count; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null && tab.getText() != null) {
                title[i] = tab.getText().toString();
            }
        }
        /*ViewPager viewPager = (tabLayout.getRootView()).findViewById(R.id.view_pager); //此处需要把viewpager的id替换成自己的
        if (viewPager != null) {
            viewPager.setAdapter(new CommonViewPagerAdapter(count, false, title));
            tabLayout.setupWithViewPager(viewPager);
        }*/
    }

    @BindingAdapter(value = {"tabSelectedListener"}, requireAll = false)
    public static void tabSelectedListener(TabLayout tabLayout, TabLayout.OnTabSelectedListener listener) {
        tabLayout.addOnTabSelectedListener(listener);
    }

}
