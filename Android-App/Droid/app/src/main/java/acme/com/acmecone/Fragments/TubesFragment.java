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


public class TubesFragment extends Fragment {

    private RadioGroup tubesRadio;
    private RadioButton tubesS, tubesN;
    private EditText getTubesQ, getTubesL, getTubesW, getTubesH, getTubesF;
    private Spinner tubesLF, tubesWF, tubesHF, tubesFF;
    private EditText tubesNotes;
    private String tubesChoice;
    private String mCustomReviewMessage = "";
    private String mCustomSendMessage = "";
    private Button tubesSubmit;


    public TubesFragment() {
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
        View view = inflater.inflate(R.layout.tubewrap_fragment, container, false);

        final Context context = getActivity().getApplicationContext();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        tubesRadio = (RadioGroup) view.findViewById(R.id.tubes_radio);

        tubesS = (RadioButton) view.findViewById(R.id.tubes_split);
        tubesS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Split", Toast.LENGTH_SHORT).show();
                tubesChoice = "Split";
            }
        });

        tubesN = (RadioButton) view.findViewById(R.id.tubes_nonsplit);
        tubesN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Non-Split", Toast.LENGTH_SHORT).show();
                tubesChoice = "Non-Split";
            }
        });

        tubesNotes = (EditText) view.findViewById(R.id.tubes_notes);

        getTubesQ = (EditText) view.findViewById(R.id.tubewrap_quantity);
        getTubesW = (EditText) view.findViewById(R.id.tubewrap_width);
        getTubesL = (EditText) view.findViewById(R.id.tubewrap_length);
        getTubesH = (EditText) view.findViewById(R.id.tubewrap_height);
        getTubesF = (EditText) view.findViewById(R.id.tubewrap_flange);

        tubesWF = (Spinner) view.findViewById(R.id.tubewrap_width_frac);
        ArrayAdapter<CharSequence> spinnerTubesWF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        tubesWF.setAdapter(spinnerTubesWF);

        tubesLF = (Spinner) view.findViewById(R.id.tubewrap_length_frac);
        ArrayAdapter<CharSequence> spinnerTubesLF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        tubesLF.setAdapter(spinnerTubesLF);

        tubesHF = (Spinner) view.findViewById(R.id.tubewrap_height_frac);
        ArrayAdapter<CharSequence> spinnerTubesHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        tubesHF.setAdapter(spinnerTubesHF);

        tubesFF = (Spinner) view.findViewById(R.id.tubewrap_flange_frac);
        ArrayAdapter<CharSequence> spinnerTubesFF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        tubesFF.setAdapter(spinnerTubesFF);

        ArrayAdapter<String> adapterColors = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView color = (AutoCompleteTextView) view.findViewById(R.id.tubes_color);
        color.setAdapter(adapterColors);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView material = (AutoCompleteTextView) view.findViewById(R.id.tubes_material);
        material.setAdapter(adapterMaterial);


        tubesS = (RadioButton) view.findViewById(R.id.tubes_split);
        tubesS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Split", Toast.LENGTH_SHORT).show();
                tubesChoice = "Split";
            }
        });

        tubesN = (RadioButton) view.findViewById(R.id.tubes_nonsplit);
        tubesN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Non-Split", Toast.LENGTH_SHORT).show();
                tubesChoice = "NS";
            }
        });

        tubesSubmit = (Button) view.findViewById(R.id.tubes_submitB);
        tubesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String orderTubesQ = getTubesQ.getText().toString();
                final String orderTubesW = getTubesW.getText().toString();
                final String orderTubesL = getTubesL.getText().toString();
                final String orderTubesH = getTubesH.getText().toString();
                final String orderTubesF = getTubesF.getText().toString();
                final String orderTubesWF = tubesWF.getSelectedItem().toString();
                final String orderTubesLF = tubesLF.getSelectedItem().toString();
                final String orderTubesHF = tubesHF.getSelectedItem().toString();
                final String orderTubesFF = tubesFF.getSelectedItem().toString();
                final String orderTubesNotes = tubesNotes.getText().toString();
                final String orderColor = color.getText().toString();
                final String orderMaterial = material.getText().toString();
                final String orderChoice = tubesChoice;

                if (!orderTubesQ.isEmpty() && !orderTubesW.isEmpty()
                        && !orderTubesL.isEmpty()
                        && !orderTubesH.isEmpty()
                        && !orderTubesF.isEmpty()
                        && !orderColor.isEmpty()
                        && !orderMaterial.isEmpty()) {

                    mCustomReviewMessage += orderTubesQ + ": " + orderColor + " " + orderMaterial
                            + "\n" +  orderChoice + " Custom Tube Wraps"
                            + "\nLength: " + orderTubesL + " " + orderTubesLF + "\""
                            + "\nWidth: " + orderTubesW + " " + orderTubesWF + "\""
                            + "\nLength: " + orderTubesH + " " + orderTubesHF + "\""
                            + "\nWidth: " + orderTubesF + " " + orderTubesFF + "\""
                            + "\n"
                            + "Tube Wraps Notes: " + orderTubesNotes
                            + "\n\n";

                    mCustomSendMessage += orderColor + " " + orderMaterial
                            + "\n"
                            + orderChoice + ") "
                            + orderTubesQ + " Tube Wraps "
                            + orderTubesW + " " + orderTubesWF + "\" W "
                            + orderTubesL + " " + orderTubesLF + "\" L "
                            + orderTubesH + " " + orderTubesHF + "\" H "
                            + orderTubesF + " " + orderTubesFF + "\" F "
                            + "\n"
                            + "Tube Wraps Notes: " + orderTubesNotes
                            + "\n\n";


                    ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                    ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);

                    Toast.makeText(context, "Added to Order √", Toast.LENGTH_SHORT).show();

                    tubesRadio.clearCheck();
                    getTubesQ.setText("");
                    getTubesW.setText("");
                    getTubesL.setText("");
                    getTubesH.setText("");
                    getTubesF.setText("");
                    tubesWF.setSelection(0);
                    tubesLF.setSelection(0);
                    tubesHF.setSelection(0);
                    tubesFF.setSelection(0);
                    color.setText("");
                    material.setText("");
                    tubesNotes.setText("");

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