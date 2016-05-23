package acme.com.acmecone;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.acme.acmecone.R;

import java.util.ArrayList;
import java.util.List;

import acme.com.acmecone.Fragments.HomeActivity;
import acme.com.acmecone.Fragments.CheckoutFragment;
import acme.com.acmecone.Fragments.CustomFragment;
import acme.com.acmecone.Fragments.ReviewFragment;
import acme.com.acmecone.Fragments.StockFragment;


public class MainTabbed extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_viewpager);

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar_actionbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_list_white_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_add_white_24dp);
        mTabLayout.getTabAt(3).setIcon(R.drawable.ic_border_color_white_24dp);
        mTabLayout.getTabAt(4).setIcon(R.drawable.ic_exit_to_app_white_24dp);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeActivity(), "");
        adapter.addFragment(new ReviewFragment(), "");
        adapter.addFragment(new StockFragment(), "");
        adapter.addFragment(new CustomFragment(), "");
        adapter.addFragment(new CheckoutFragment(), "");


        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}
