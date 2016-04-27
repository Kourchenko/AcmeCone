package acme.com.acmecone.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import acme.com.acmecone.Adapters.GMailSender;
import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;

public class CheckoutFragment extends Fragment {

    private AutoCompleteTextView name, company, phone, email, manufacturer, optional_note;
    private CalendarView calendar;
    private Vibrator vib;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = inflater.inflate(R.layout.checkout_fragment, container, false);
        final Context context = getActivity().getApplicationContext();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final FloatingActionButton fab_orderform = (FloatingActionButton) view.findViewById(R.id.fab_viewB);
        final FloatingActionButton fab_calendar = (FloatingActionButton) view.findViewById(R.id.fab_viewA);
        final FrameLayout viewB = (FrameLayout) view.findViewById(R.id.viewB);


        calendar = (CalendarView) view.findViewById(R.id.calendar);
        name = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_name);
        company = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_company);
        phone = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_phone);
        email = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_email);
        manufacturer = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_manufacturer);
        optional_note = (AutoCompleteTextView) view.findViewById(R.id.orderform_edit_note);



        fab_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewB.getVisibility() == View.VISIBLE) {
                    if (ConstantVar.REVIEW_DATABASE.isEmpty()) {
                        fab_calendar.setVisibility(View.VISIBLE);
                        viewB.setVisibility(View.GONE);
                        fab_orderform.setVisibility(View.GONE);
                    }

                } else {
                    fab_calendar.setVisibility(View.GONE);
                    viewB.setVisibility(View.VISIBLE);
                    fab_orderform.setVisibility(View.VISIBLE);

                }
            }
        });

        fab_orderform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewB.getVisibility() == View.VISIBLE) {

                    try {


                    if (getView() != null && ConstantVar.REVIEW_DATABASE.isEmpty()) {
                        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                    } else {
                        final String mName = name.getText().toString();
                        final String mEmail = email.getText().toString();
                        final String mCompany = company.getText().toString();
                        final String mPhone = phone.getText().toString();
                        final String mManufacturer = manufacturer.getText().toString();
                        final String mNotes = optional_note.getText().toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        final String selectedDate = sdf.format(new Date(calendar.getDate()));

                        if (!mName.isEmpty()
                                || !mEmail.isEmpty()
                                || !mCompany.isEmpty()
                                || !mPhone.isEmpty()
                                || !mManufacturer.isEmpty()
                                || !mNotes.isEmpty()) {

                            if (!ConstantVar.REVIEW_DATABASE.isEmpty()) {
                                new AsyncTask<Void, Void, Void>() {
                                    @Override
                                    public Void doInBackground(Void... arg) {
                                        try {
                                            String orderString = "";
                                            String orderInfo = mName + "\n"
                                                    + mEmail + "\n"
                                                    + mCompany + "\n"
                                                    + mPhone + "\n"
                                                    + mManufacturer + "\n";

                                            for (String type: ConstantVar.DATASET) {
                                                orderString += type + "\n";
                                            }

                                            final String allTogether = "Date Needed: " + selectedDate + "\n\n" + "STOCK: \n" + orderString + "\n\n" + orderInfo;

                                            GMailSender sender = new GMailSender("acmecone.acme@gmail.com", "Acmecone97402");
                                            sender.sendMail(mCompany + "ordered!", allTogether, mEmail, "acmecone.acme@gmail.com, " + mEmail);

                                            Snackbar.make(container, "Successfully Sent Message!", Snackbar.LENGTH_LONG).show();

                                        } catch (Exception e) {
                                            vib.vibrate(50);
                                            Log.e("SendMail", e.getMessage(), e);
                                        }

                                        return null;
                                    }
                                }.execute();

                            } else {
                                vib.vibrate(50);
                            }
                        }
                    }
                } finally {
                        fab_calendar.setVisibility(View.VISIBLE);
                        viewB.setVisibility(View.GONE);
                        fab_orderform.setVisibility(View.GONE);

                        Snackbar.make(container, "Can\'t SEND. Nothing Ordered...", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }

}
