package acme.com.acmecone.Fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import acme.com.acmecone.Activities.LoginActivity;
import acme.com.acmecone.Activities.RegisterActivity;
import acme.com.acmecone.NavigationDrawerCallbacks;
import acme.com.acmecone.NavigationDrawerFragment;
import acme.com.acmecone.R;

/**
 * Created by Diego
 */
public class InventoryFragment extends ActionBarActivity
    implements NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentations of the navigation drawer
     *
     */

    private NavigationDrawerFragment mNavigationDrawerFragment;
    public Toolbar mToolbar;

    ImageView logoB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderform_tabbed);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("Acme Cone", "sales@acmecone.com", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        logoB = (ImageView)findViewById(R.id.acme_cone_text);
        logoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (position == 0) {
        }

        else if (position == 1) {
            Intent loginIntent = new Intent(InventoryFragment.this, LoginActivity.class);
            startActivity(loginIntent);
        }
        else if (position == 2) {
            Intent registerIntent = new Intent(InventoryFragment.this, RegisterActivity.class);
            startActivity(registerIntent);


        } else if (position == 3) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: (866) 516-4079"));
            startActivity(callIntent);
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
