package acme.com.acmecone.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;

public class SleepersFragment extends Fragment {

    private EditText getSleepersQ, getSleepersL, getSleepersW, getSleepersH, getSleepersF;
    private Spinner sleepersLF, sleepersWF, sleepersHF, sleepersFF;
    private Button sleepersB;
    private EditText sleepersNotes;
    private String mCustomReviewMessage = "";
    private String mCustomSendMessage = "";

    public SleepersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sleepers_fragment, container, false);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final Context context = getActivity().getApplicationContext();

        sleepersNotes = (EditText) view.findViewById(R.id.curbs_notes);

        getSleepersQ = (EditText) view.findViewById(R.id.sleepers_quantity);
        getSleepersL = (EditText) view.findViewById(R.id.sleepers_length);
        getSleepersW = (EditText) view.findViewById(R.id.sleepers_width);
        getSleepersH = (EditText) view.findViewById(R.id.sleepers_height);
        getSleepersF = (EditText) view.findViewById(R.id.sleepers_flange);

        sleepersLF = (Spinner) view.findViewById(R.id.sleepers_length_frac);
        ArrayAdapter<CharSequence> spinnerSleepersLF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        sleepersLF.setAdapter(spinnerSleepersLF);

        sleepersWF = (Spinner) view.findViewById(R.id.sleepers_width_frac);
        ArrayAdapter<CharSequence> spinnerSleepersWF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        sleepersWF.setAdapter(spinnerSleepersWF);

        sleepersHF = (Spinner) view.findViewById(R.id.sleepers_height_frac);
        ArrayAdapter<CharSequence> spinnerSleepersHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        sleepersHF.setAdapter(spinnerSleepersHF);

        sleepersFF = (Spinner) view.findViewById(R.id.sleepers_flange_frac);
        ArrayAdapter<CharSequence> spinnerSleepersFF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        sleepersFF.setAdapter(spinnerSleepersFF);

        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(context, R.layout.my_list_item);
        final AutoCompleteTextView colors = (AutoCompleteTextView) view.findViewById(R.id.sleepers_color);
        colors.setAdapter(adapterColor);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item);
        final AutoCompleteTextView materials = (AutoCompleteTextView) view.findViewById(R.id.sleepers_material);
        materials.setAdapter(adapterMaterial);

        sleepersB = (Button) view.findViewById(R.id.sleepers_submitB);
        sleepersB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderSleepersQ = getSleepersQ.getText().toString();
                final String orderSleepersL = getSleepersL.getText().toString();
                final String orderSleepersLF = sleepersLF.getSelectedItem().toString();
                final String orderSleepersW = getSleepersW.getText().toString();
                final String orderSleepersWF = sleepersWF.getSelectedItem().toString();
                final String orderSleepersH = getSleepersH.getText().toString();
                final String orderSleepersHF = sleepersHF.getSelectedItem().toString();
                final String orderSleepersF = getSleepersF.getText().toString();
                final String orderSleepersFF = sleepersFF.getSelectedItem().toString();
                final String orderColor = colors.getText().toString().trim();
                final String orderMaterial = materials.getText().toString().trim();
                final String orderNotes = sleepersNotes.getText().toString();

                if (!orderSleepersQ.isEmpty()
                        && !orderSleepersL.isEmpty()
                        && !orderSleepersW.isEmpty()
                        && !orderSleepersH.isEmpty()
                        && !orderSleepersF.isEmpty()
                        && !orderColor.isEmpty()
                        && !orderMaterial.isEmpty()) {

                    mCustomReviewMessage += orderSleepersQ + ": " + orderColor + orderMaterial
                            + "\n" + "Custom Curbs"
                            + "\nLength: " + orderSleepersL + " " + orderSleepersLF + "\""
                            + "\nWidth: " + orderSleepersW + " " + orderSleepersWF + "\""
                            + "\nHeight: " + orderSleepersH + " " + orderSleepersHF + "\""
                            + "\nFlange: " + orderSleepersF + " " + orderSleepersFF + "\""
                            + "\n"
                            + "Sleepers Notes: " + orderNotes
                            + "\n\n";

                    mCustomSendMessage += orderColor + " " + orderMaterial
                            + "\n"
                            + orderSleepersQ + ")" + " Custom Sleeper Boxes "
                            + orderSleepersL + " " + orderSleepersLF + "\" L "
                            + orderSleepersW + " " + orderSleepersWF + "\" W "
                            + orderSleepersF + " " + orderSleepersFF + "\" F "
                            + "\n"
                            + "Sleepers Notes: " + orderNotes
                            + "\n\n";

                    ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                    ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);

                    Toast.makeText(context, "Added to Order: √", Toast.LENGTH_SHORT).show();

                    getSleepersQ.setText("");
                    getSleepersL.setText("");
                    sleepersLF.setSelection(0);
                    getSleepersW.setText("");
                    sleepersWF.setSelection(0);
                    getSleepersH.setText("");
                    sleepersHF.setSelection(0);
                    getSleepersF.setText("");
                    sleepersFF.setSelection(0);
                    colors.setText("");
                    materials.setText("");
                    sleepersNotes.setText("");

                    mCustomReviewMessage = "";
                    mCustomSendMessage = "";

                } else {
                    Toast.makeText(context, "Fill Order Completely...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

}