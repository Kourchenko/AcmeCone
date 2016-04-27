package acme.com.acmecone.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import acme.com.acmecone.Fragments.ConesFragment;
import acme.com.acmecone.Fragments.CornersFragment;
import acme.com.acmecone.Fragments.CurbsFragment;
import acme.com.acmecone.Fragments.DrainsFragment;
import acme.com.acmecone.Fragments.PipesFragment;
import acme.com.acmecone.Fragments.PitchPansFragment;
import acme.com.acmecone.Fragments.ReviewFragment;
import acme.com.acmecone.Fragments.ScuppersFragment;
import acme.com.acmecone.Fragments.SleepersFragment;
import acme.com.acmecone.Fragments.TubesFragment;
import acme.com.acmecone.R;

public class OrderFormTabbed extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderform_tabbed);

        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ReviewFragment(), "STOCK");
        adapter.addFragment(new ConesFragment(), "CONES");
        adapter.addFragment(new CornersFragment(), "CORNERS");
        adapter.addFragment(new PipesFragment(), "PIPE WRAPS");
        adapter.addFragment(new DrainsFragment(), "DROP SCUPPERS");
        adapter.addFragment(new ScuppersFragment(), "SCUPPERS");
        adapter.addFragment(new PitchPansFragment(), "PITCH PANS");
        adapter.addFragment(new TubesFragment(), "TUBE WRAPS");
        adapter.addFragment(new CurbsFragment(), "CURB WRAPS");
        adapter.addFragment(new SleepersFragment(), "SLEEPER BOXES");
        viewPager.setAdapter(adapter);
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


















