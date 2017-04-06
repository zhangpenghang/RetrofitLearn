package ph.com.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



import java.util.List;

import ph.com.test.bean.ClassfyBean;
import ph.com.test.fragment.BaseFragment;

/**
 * Created by qf on 16/8/7.
 */
public class VpAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private ClassfyBean classfyBean;

    public VpAdapter(FragmentManager fm, List<BaseFragment> fragments, ClassfyBean classfyBean) {
        super(fm);
        this.fragments = fragments;
        this.classfyBean = classfyBean;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return classfyBean.getTngou().get(position).getTitle();
    }
}
