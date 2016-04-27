package acme.com.acmecone.Activities;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import acme.com.acmecone.Fragments.BoxesViewFragment;
import acme.com.acmecone.Fragments.CornersViewFragment;
import acme.com.acmecone.Fragments.DrainsViewFragment;
import acme.com.acmecone.Fragments.MetalViewFragment;
import acme.com.acmecone.Fragments.PipeWrapViewFragment;
import acme.com.acmecone.Fragments.PitchPansViewFragment;
import acme.com.acmecone.Fragments.ScuppersViewFragment;
import acme.com.acmecone.Fragments.TubeWrapViewFragment;
import acme.com.acmecone.Fragments.ConesViewFragment;
import acme.com.acmecone.R;


public class HomeActivity extends Fragment {

    public ImageView cones;
    public ImageView boxes;
    public ImageView corners;
    public ImageView pipe_wraps;
    public ImageView drains;
    public ImageView edge_metal;
    public ImageView scuppers;
    public ImageView pitch_pans;
    public ImageView tube_wraps;

    public ImageView LOGO;

    int clicked = 0;

    public HomeActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        final Context context = getActivity().getApplicationContext();

        cones = (ImageView) view.findViewById(R.id.main_cone);
        cones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conesIntent = new Intent(context, ConesViewFragment.class);
                startActivity(conesIntent);
            }
        });

        boxes = (ImageView) view.findViewById(R.id.main_boxes);
        boxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent boxesIntent = new Intent(context, BoxesViewFragment.class);
                startActivity(boxesIntent);
            }
        });

        corners = (ImageView) view.findViewById(R.id.main_corners);
        corners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cornersIntent = new Intent(context, CornersViewFragment.class);
                startActivity(cornersIntent);
            }
        });

        pipe_wraps = (ImageView) view.findViewById(R.id.main_pipewraps);
        pipe_wraps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pipewrapsIntent = new Intent(context, PipeWrapViewFragment.class);
                startActivity(pipewrapsIntent);
            }
        });

        drains = (ImageView) view.findViewById(R.id.main_drains);
        drains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drainsIntent = new Intent(context, DrainsViewFragment.class);
                startActivity(drainsIntent);
            }
        });

        edge_metal = (ImageView) view.findViewById(R.id.main_edge_metal);
        edge_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent metalIntent = new Intent(context, MetalViewFragment.class);
                startActivity(metalIntent);
            }
        });

        scuppers = (ImageView) view.findViewById(R.id.main_scuppers);
        scuppers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scuppersIntent = new Intent(context, ScuppersViewFragment.class);
                startActivity(scuppersIntent);
            }
        });

        pitch_pans = (ImageView) view.findViewById(R.id.main_pitchpans);
        pitch_pans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pitchpansIntent = new Intent(context, PitchPansViewFragment.class);
                startActivity(pitchpansIntent);
            }
        });

        tube_wraps = (ImageView) view.findViewById(R.id.main_tubewrap);
        tube_wraps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tubewrapsIntent = new Intent(context, TubeWrapViewFragment.class);
                startActivity(tubewrapsIntent);
            }
        });

        final int matches = 5;
        LOGO = (ImageView) view.findViewById(R.id.logo);
        LOGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matches !=  clicked) {
                    Integer need =  5 - clicked;
                    Toast.makeText(context, "Need to Click: " + need.toString(), Toast.LENGTH_SHORT).show();
                    clicked += 1;
                } else if (matches == clicked) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: (866) 516-4079"));
                    startActivity(callIntent);
                    clicked = 0;
                }
            }
        });

        return view;
    }

    /*
    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (position == 0) {
            Intent loginIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(loginIntent);
        }

        else if (position == 1) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(aboutIntent);
        }

        else if (position == 2) {
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
    */
}
