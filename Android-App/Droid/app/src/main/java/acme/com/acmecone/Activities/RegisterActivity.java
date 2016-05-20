package acme.com.acmecone.Activities;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import acme.com.acmecone.Adapters.NavigationDrawerCallbacks;
import acme.com.acmecone.Fragments.NavigationDrawerFragment;
import acme.com.acmecone.R;


public class RegisterActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    public Toolbar mToolbar;

    ImageView logoB;
    EditText editName, editEmail, editPass;
    Button signinB, registerB;
    Integer MY_PERMISSIONS;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("Acme Cone", "sales@acmecone.com", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));


        // Login/Sign Up

        editName = (EditText) findViewById(R.id.register_edit_name);
        editEmail = (EditText) findViewById(R.id.register_email);
        editPass = (EditText) findViewById(R.id.register_password);

        signinB = (Button) findViewById(R.id.register_loginBttn);
        registerB = (Button) findViewById(R.id.register_registerBttn);
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);

            }
        });

        logoB = (ImageView) findViewById(R.id.register_logoBttn);
        logoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }


    protected boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (position == 0) {

        } else if (position == 1) {

            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);

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
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}


