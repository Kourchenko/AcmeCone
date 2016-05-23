package acme.com.acmecone.Activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Locale;

import acme.com.acmecone.Adapters.NavigationDrawerCallbacks;
import acme.com.acmecone.Fragments.NavigationDrawerFragment;
import com.acme.acmecone.R;


public class AboutUsActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    public Toolbar mToolbar;
    public ImageView logoB;

    public RelativeLayout directionsApp, youtubeApp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mToolbar = (Toolbar) findViewById(R.id.about_toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.about_fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.about_fragment_drawer, (DrawerLayout) findViewById(R.id.about_drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("Acme Cone", "info@acmecone.com", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        directionsApp = (RelativeLayout) findViewById(R.id.about_acme_directions);
        directionsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:0,0?q=3237+W+1st+Ave+Eugene+OR+97402");
                Intent directionsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(directionsIntent);
            }
        });

        youtubeApp = (RelativeLayout)findViewById(R.id.about_acme_youtube);
        youtubeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://c11OibHrKuA"));
                    youtubeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(youtubeIntent);
                } catch(ActivityNotFoundException e) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=c11OibHrKuA"));
                    startActivity(i);
                }
            }
        });

        logoB = (ImageView)findViewById(R.id.about_acme_cone_text);
        logoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }


    protected boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (position == 0) {

        }
        else if (position == 1) {
            Intent registerIntent = new Intent(this, AboutUsActivity.class);
            startActivity(registerIntent);

        } else if (position == 2) {
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
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);

            case R.id.action_help:
                Intent registerIntent = new Intent(this, RegisterActivity.class);
                startActivity(registerIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}









