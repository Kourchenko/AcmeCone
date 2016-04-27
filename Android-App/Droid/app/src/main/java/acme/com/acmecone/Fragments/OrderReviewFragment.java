package acme.com.acmecone.Fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Map;

import acme.com.acmecone.Activities.AboutUsActivity;
import acme.com.acmecone.NavigationDrawerCallbacks;
import acme.com.acmecone.NavigationDrawerFragment;
import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class OrderReviewFragment extends ActionBarActivity
        implements NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public Toolbar mToolbar;
    private TextView orderReview;
    private String orderSummary = "";
    private Button submitOrder;
    private String mEmailMessage = "";
    private String calendarDate = "";
    private TextView getOrderReviewNull;
    private CalendarView getOrderCalendarDate;
    private boolean calendarClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderreview_layout);
        calendarClicked = false;


        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("Acme Cone", "info@acmecone.com", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        getOrderReviewNull = (TextView) findViewById(R.id.order_review_null);
        getOrderCalendarDate = (CalendarView) findViewById(R.id.orderreview_dateBy);
        getOrderCalendarDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendarClicked = true;
                calendarDate = month+1 + "/" + dayOfMonth + "/" + year;
            }
        });

        if (ConstantVar.SEND_DATABASE.isEmpty()) {
            getOrderReviewNull.setVisibility(View.VISIBLE);
        } else if (!ConstantVar.SEND_DATABASE.isEmpty()) {

            getOrderReviewNull.setVisibility(View.GONE);
        }

        orderReview = (TextView)findViewById(R.id.order_review);
        for (Map.Entry<Integer, String> entry: ConstantVar.REVIEW_DATABASE.entrySet()) {
            orderSummary += entry.getValue() + "\n" + "_____________________________" + "\n";
        }

        orderReview.setText(orderSummary);


        submitOrder = (Button) findViewById(R.id.review_submit);
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ConstantVar.SEND_DATABASE.isEmpty()) {

                    final String name = ConstantVar.NAME;
                    final String email = ConstantVar.EMAIL;
                    final String company = ConstantVar.COMPANY;
                    final String manufacturer = ConstantVar.MANUFACTURER;

                    Intent mEmail = new Intent(Intent.ACTION_SEND);
                    mEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"diego@acmecone.com", "asa@acmecone.com"});
                    mEmail.putExtra(Intent.EXTRA_SUBJECT, "Order From: " + name);

                    if (calendarClicked) {
                        mEmailMessage += "Need Order By: " + calendarDate + "\n";
                    } else {
                        Calendar c = Calendar.getInstance();
                        calendarDate += c.get(Calendar.MONTH)+1 + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR);
                        mEmailMessage += "Need Order By: " +  calendarDate + "\n";
                    }


                    mEmailMessage += "Manufacturer Request: " + manufacturer + "\n\n\n\n";

                    for (Map.Entry<String, String> entry: ConstantVar.SEND_DATABASE.entrySet()) {
                        mEmailMessage += entry.getValue() + "\n\n";
                    }

                    mEmailMessage += "\n\n"
                            + name + "\n"
                            + email + "\n"
                            + company;

                    mEmail.putExtra(Intent.EXTRA_TEXT, mEmailMessage + "\n");
                    mEmail.setType("message/rfc822");

                    startActivity(Intent.createChooser(mEmail, "Send email..."));

                    Toast.makeText(OrderReviewFragment.this, "Send Email...", Toast.LENGTH_SHORT).show();

                    mEmailMessage = "";
                    calendarDate = "";
                    calendarClicked = false;
                    ConstantVar.SEND_DATABASE.clear();
                    ConstantVar.REVIEW_DATABASE.clear();

                    Toast.makeText(OrderReviewFragment.this, "Placed Order √", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderReviewFragment.this, "Nothing Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (position == 0) {
        }
        else if (position == 1) {
            Intent aboutIntent = new Intent(this, AboutUsActivity.class);
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
}
