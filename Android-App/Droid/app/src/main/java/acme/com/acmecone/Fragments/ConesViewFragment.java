package acme.com.acmecone.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import acme.com.acmecone.Activities.AboutUsActivity;
import acme.com.acmecone.NavigationDrawerCallbacks;
import acme.com.acmecone.NavigationDrawerFragment;
import acme.com.acmecone.R;


public class ConesViewFragment extends AppCompatActivity {
    public Toolbar mToolbar;

    public Spinner mFractions;
    public Spinner mSpinner;
    public Spinner mTop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cones_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final LayoutInflater inflater = getLayoutInflater();
        final Context context = getApplicationContext();

        final AlertDialog.Builder builder = new AlertDialog.Builder(ConesViewFragment.this);

        final View view = inflater.inflate(R.layout.cone_custom_dialog, null);

        final FloatingActionButton add = (FloatingActionButton) findViewById(R.id.fab_cones);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setView(inflater.inflate(R.layout.cone_custom_dialog, null));
                builder.setTitle("Cones");
                builder.setIcon(R.drawable.ic_text_cone);
                builder.create();



                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
