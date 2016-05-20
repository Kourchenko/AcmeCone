package acme.com.acmecone.Fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.FrameLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import acme.com.acmecone.Adapters.GMailSender;
import acme.com.acmecone.Items.Cone;
import acme.com.acmecone.Items.Corner;
import acme.com.acmecone.Items.Curb;
import acme.com.acmecone.Items.Drop;
import acme.com.acmecone.Items.Pan;
import acme.com.acmecone.Items.Pipe;
import acme.com.acmecone.Items.Scupper;
import acme.com.acmecone.Items.Sleeper;
import acme.com.acmecone.Items.Tube;
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
                    if (ConstantVar.STOCK.isEmpty()) {
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

                        if (getView() != null) {

                            final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                            final String mName = name.getText().toString();
                            final String mEmail = email.getText().toString();
                            final String mCompany = company.getText().toString();
                            final String mPhone = phone.getText().toString();
                            final String mManufacturer = manufacturer.getText().toString();
                            final String mNotes = optional_note.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                            final String selectedDate = sdf.format(new Date(calendar.getDate()));

                            if (!mName.isEmpty()
                                    && !mEmail.isEmpty()
                                    && !mCompany.isEmpty()
                                    && !mPhone.isEmpty()
                                    && !mManufacturer.isEmpty()) {

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

                                            if (!ConstantVar.DATASET.isEmpty()) {
                                                for (String type : ConstantVar.DATASET) {
                                                    orderString += type + "\n";
                                                }
                                            }
                                            if (!ConstantVar.CONES.isEmpty()) {
                                                orderString += "\n\nCustom Cones: \n";
                                                for (Cone cone: ConstantVar.CONES) {
                                                    orderString +=  cone.quantity +  " " + cone.type + "Cone(s) \n"
                                                            +cone.height + " " + cone.heightFrac + " H \n"
                                                            +cone.bot + " " + cone.botFrac + " B \n"
                                                            +cone.top + " " + cone.topFrac + " T \n"
                                                            +cone.flange + " " + cone.flangeFrac + " F \n"
                                                            +cone.color + " " + cone.material
                                                            +"\n";
                                                }
                                            }
                                            if (!ConstantVar.CORNERS.isEmpty()) {
                                                orderString += "\n\n Custom Corners: \n";
                                                for (Corner corner: ConstantVar.CORNERS) {
                                                    orderString += corner.quantity + " " + corner.type + " Corner(s) \n"
                                                            +corner.depth + " " + corner.depthFrac + " Depth \n"
                                                            +corner.height + " " + corner.heightFrac + " H \n"
                                                            +corner.flange + " " + corner.flangeFrac + " F \n"
                                                            +corner.color + " " + corner.material
                                                            +"\n";
                                                }
                                            }
                                             if (!ConstantVar.PIPES.isEmpty()) {
                                                 orderString += "\n\nCustom Pipe Wraps: \n";
                                                 for (Pipe pipe: ConstantVar.PIPES) {
                                                     orderString += pipe.quantity + " " + pipe.type + " Pipe(s) \n"
                                                             +pipe.diameter + " " + pipe.diameterFrac + " Dia. \n"
                                                             +pipe.height + " " + pipe.heightFrac + " H \n"
                                                             +pipe.flange + " " + pipe.flangeFrac + " F \n"
                                                             +pipe.color + " " + pipe.material
                                                             + "\n";
                                                 }
                                             }

                                            if (!ConstantVar.DROPS.isEmpty()) {
                                                orderString += "\n\nCustom Drop Scuppers: \n";
                                                for (Drop drop: ConstantVar.DROPS) {
                                                    orderString +=  drop.quantity + " Drop(s) \n"
                                                            +drop.diameter + " " + drop.diameterFrac + " Dia. \n"
                                                            +drop.depth + " " + drop.depthFrac + " Dep. \n"
                                                            +drop.flange + " " + drop.flangeFrac + " F \n"
                                                            +drop.color + " " + drop.material
                                                            + "\n";
                                                }
                                            }

                                                if (!ConstantVar.SCUPPERS.isEmpty()) {
                                                    orderString += "\n\nCustom Scuppers: \n";
                                                    for (Scupper scupper: ConstantVar.SCUPPERS) {
                                                        orderString +=  scupper.quantity + " " + scupper.type + " Scupper(s) \n"
                                                                        +scupper.depth + " " + scupper.depthFrac + " Dep. \n"
                                                                        +scupper.length + " " + scupper.lengthFrac + " L \n"
                                                                        +scupper.width + " " + scupper.widthFrac + " W \n"
                                                                        +scupper.flange + " " + scupper.flangeFrac + " F \n"
                                                                        +scupper.color + " " + scupper.material
                                                                        + "\n";
                                                    }
                                                }

                                                if (!ConstantVar.PANS.isEmpty()) {
                                                    orderString += "\n\nCustom Pitch Pans: \n";
                                                    for (Pan pan: ConstantVar.PANS) {
                                                        if (pan.dimension.equals("Round")) {
                                                            orderString +=  pan.quantity + " " + " " + pan.dimension + " " + pan.type + " Pitch Pan(s) \n"
                                                                            +pan.height + " " + pan.heightFrac + " H \n"
                                                                            +pan.diameter + " " + pan.diameterFrac + "Dia. \n"
                                                                            +pan.flange + " " + pan.flangeFrac + " F \n"
                                                                            +pan.color + " " + pan.material
                                                                            + "\n";

                                                        } else if (pan.dimension.equals("Square")) {
                                                            orderString +=  pan.quantity + " " + pan.dimension + " " +pan.type + " Pitch Pans(s) \n"
                                                                            +pan.height + " " + pan.heightFrac + " H \n"
                                                                            +pan.length + " " + pan.lengthFrac + " L \n"
                                                                            +pan.width + " " + pan.widthFrac + " W \n"
                                                                            +pan.flange + " " + pan.flangeFrac + " F \n"
                                                                            +pan.color + " " + pan.material
                                                                            + "\n";
                                                        }
                                                    }
                                                }

                                                if (!ConstantVar.TUBES.isEmpty()) {
                                                    for (Tube tube: ConstantVar.TUBES) {
                                                        orderString +=  tube.quantity + " " + tube.type + " Tube Wrap(s) \n"
                                                                        +tube.height + " " + tube.heightFrac + " H \n"
                                                                        +tube.length + " " + tube.lengthFrac + " L \n"
                                                                        +tube.width + " " + tube.widthFrac + " W \n"
                                                                        +tube.flange + " " + tube.flangeFrac + " F \n"
                                                                        +tube.color + " " + tube.material
                                                                        + "\n";
                                                    }
                                                }

                                                if (!ConstantVar.CURBS.isEmpty()) {
                                                    for (Curb curb: ConstantVar.CURBS) {
                                                        orderString +=  curb.quantity + " " + curb.type + " Curb Wrap(s) \n"
                                                                        +curb.height + " " + curb.heightFrac + " H \n"
                                                                        +curb.length + " " + curb.lengthFrac + " L \n"
                                                                        +curb.width + " " + curb.widthFrac + " W \n"
                                                                        +curb.flange + " " + curb.flangeFrac + " F \n"
                                                                        +curb.color + " " + curb.material
                                                                        + "\n";
                                                    }
                                                }

                                                if (!ConstantVar.SLEEPERS.isEmpty()) {
                                                    for (Sleeper sleeper: ConstantVar.SLEEPERS) {
                                                        orderString +=  sleeper.quantity + " " + "Sleeper Box(es) \n"
                                                                        +sleeper.height + " " + sleeper.heightFrac + " H \n"
                                                                        +sleeper.length + " " + sleeper.lengthFrac + " L \n"
                                                                        +sleeper.width + " " + sleeper.widthFrac + " W \n"
                                                                        +sleeper.flange + " " + sleeper.flangeFrac + " F \n"
                                                                        +sleeper.color + " " + sleeper.material
                                                                        + "\n";
                                                    }
                                                }

                                            if (orderString.isEmpty()) {
                                                Snackbar.make(container, "Can\'t SEND. Nothing Ordered...", Snackbar.LENGTH_LONG).show();

                                            } else {
                                                final String allTogether = "Date Needed: " + selectedDate + "\n\n" + "STOCK: \n" + orderString + "\n\n" + orderInfo;

                                                GMailSender sender = new GMailSender("EMAIL@address.com", "PW");
                                                // toRecipients must be seperated by commas: "emailAddress,"
                                                sender.sendMail(mCompany + "ordered!", allTogether, mEmail, mEmail);

                                                ConstantVar.STOCK.clear();
                                                ConstantVar.CONES.clear();
                                                ConstantVar.CORNERS.clear();
                                                ConstantVar.PIPES.clear();
                                                ConstantVar.DROPS.clear();
                                                ConstantVar.DROPS.clear();
                                                ConstantVar.SCUPPERS.clear();
                                                ConstantVar.PANS.clear();
                                                ConstantVar.TUBES.clear();
                                                ConstantVar.CURBS.clear();
                                                ConstantVar.SLEEPERS.clear();

                                                Snackbar.make(container, "Successfully Sent Message!", Snackbar.LENGTH_LONG).show();
                                            }

                                            } catch (Exception e) {
                                                Log.e("SendMail", e.getMessage(), e);
                                            }

                                            return null;
                                        }
                                    }.execute();

                            }
                        }
                    } finally {
                        fab_calendar.setVisibility(View.VISIBLE);
                        fab_calendar.animate();
                        viewB.setVisibility(View.GONE);
                        viewB.animate();
                        fab_orderform.setVisibility(View.GONE);
                        fab_orderform.animate();

                        if (ConstantVar.STOCK.isEmpty()
                                && (ConstantVar.CONES.isEmpty())
                                && (ConstantVar.CORNERS.isEmpty())
                                && (ConstantVar.PIPES.isEmpty())
                                && (ConstantVar.DROPS.isEmpty())
                                && (ConstantVar.SCUPPERS.isEmpty())
                                && (ConstantVar.PANS.isEmpty())
                                && (ConstantVar.TUBES.isEmpty())
                                && (ConstantVar.CURBS.isEmpty())
                                && (ConstantVar.SLEEPERS.isEmpty())) {

                            Snackbar.make(container, "Can\'t SEND. Nothing Ordered...", Snackbar.LENGTH_LONG).show();

                        }
                    }
                }
            }
        });

        return view;
    }

}
