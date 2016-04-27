package acme.com.acmecone.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class PitchPansFragment extends Fragment {

    private RadioGroup pansGroup;
    public RadioButton pansRound, pansSquare;
    private EditText pansQ, pansW, pansL, pansD, pansH, pansF;
    private Spinner getPansW, getPansL, getPansD, getPansH, getPansF;
    private RelativeLayout pansChoiceRound, pansChoiceSquare;
    private String pansChoice;
    private EditText pansNotes;
    public Button pansSubmit;
    private String mCustomReviewMessage = "";
    private String mCustomSendMessage = "";
    

    public PitchPansFragment() {
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
        View view = inflater.inflate(R.layout.pitchpans_fragment, container, false);

        final Context context = getActivity().getApplicationContext();


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        pansGroup = (RadioGroup) view.findViewById(R.id.pans_radioB);

        pansQ = (EditText) view.findViewById(R.id.pans_quantity);
        pansW = (EditText) view.findViewById(R.id.pans_width);
        pansL = (EditText) view.findViewById(R.id.pans_length);
        pansD = (EditText) view.findViewById(R.id.pans_diameter);
        pansH = (EditText) view.findViewById(R.id.pans_height);
        pansF = (EditText) view.findViewById(R.id.pans_flange);

        pansNotes = (EditText) view.findViewById(R.id.pans_notes);

        pansChoiceRound = (RelativeLayout) view.findViewById(R.id.pans_view_round);
        pansChoiceSquare = (RelativeLayout) view.findViewById(R.id.pans_view_square);

        getPansW = (Spinner) view.findViewById(R.id.pans_width_frac);
        ArrayAdapter<CharSequence> spinnerPansW = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getPansW.setAdapter(spinnerPansW);

        getPansL = (Spinner) view.findViewById(R.id.pans_length_frac);
        ArrayAdapter<CharSequence> spinnerPansL = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getPansL.setAdapter(spinnerPansL);

        getPansD = (Spinner) view.findViewById(R.id.pans_diameter_frac);
        ArrayAdapter<CharSequence> spinnerPansD = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getPansD.setAdapter(spinnerPansD);

        getPansH = (Spinner) view.findViewById(R.id.pans_height_frac);
        ArrayAdapter<CharSequence> spinnerPansH = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getPansH.setAdapter(spinnerPansH);

        getPansF = (Spinner) view.findViewById(R.id.pans_flange_frac);
        ArrayAdapter<CharSequence> spinnerPansF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        getPansF.setAdapter(spinnerPansF);


        ArrayAdapter<String> adapterColors = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView pansColors = (AutoCompleteTextView) view.findViewById(R.id.pans_color);
        pansColors.setAdapter(adapterColors);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView pansMaterial = (AutoCompleteTextView) view.findViewById(R.id.pans_material);
        pansMaterial.setAdapter(adapterMaterial);


        pansRound = (RadioButton) view.findViewById(R.id.pans_roundR);
        pansRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Round", Toast.LENGTH_SHORT).show();
                pansChoiceRound.setVisibility(View.VISIBLE);
                pansChoiceSquare.setVisibility(View.GONE);
                pansChoice = "Round";

            }
        });

        pansSquare = (RadioButton) view.findViewById(R.id.pans_squareR);
        pansSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Square", Toast.LENGTH_SHORT).show();
                pansChoiceSquare.setVisibility(View.VISIBLE);
                pansChoiceRound.setVisibility(View.GONE);
                pansChoice = "Square";

            }
        });

        pansSubmit = (Button) view.findViewById(R.id.pans_submitB);
        pansSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderPansQ = pansQ.getText().toString();
                final String orderPansW = pansW.getText().toString();
                final String orderPansWF = getPansW.getSelectedItem().toString();
                final String orderPansL = pansL.getText().toString();
                final String orderPansLF = getPansL.getSelectedItem().toString();
                final String orderPansD = pansD.getText().toString();
                final String orderPansDF = getPansD.getSelectedItem().toString();
                final String orderPansH = pansH.getText().toString();
                final String orderPansHF = getPansH.getSelectedItem().toString();
                final String orderPansF = pansF.getText().toString();
                final String orderPansFF = getPansF.getSelectedItem().toString();
                final String orderPansColor = pansColors.getText().toString();
                final String orderPansMaterial = pansMaterial.getText().toString();
                final String orderPansNotes = pansNotes.getText().toString();
                final String orderChoice = pansChoice;

                if (!orderPansQ.isEmpty() && !orderPansH.isEmpty() && !orderPansF.isEmpty()) {

                    if (!orderPansW.isEmpty() && !orderPansL.isEmpty()) {
                        mCustomReviewMessage += orderPansQ + ": " + orderPansColor + " " + orderPansMaterial
                                + "\n" + "Square Pitch Pans"
                                + "\nLength: " + orderPansL + " " + orderPansLF + "\""
                                + "\nWidth: "  + orderPansW + " " + orderPansWF + "\""
                                + "\nHeight: " + orderPansH + " " + orderPansHF + "\""
                                + "\nFlange: " + orderPansF + " " + orderPansFF + "\""
                                + "\n"
                                + "Pitch Pans Notes: " + orderPansNotes
                                + "\n\n";

                        mCustomSendMessage += orderPansColor + " " + orderPansMaterial
                                + "\n"
                                + orderPansQ + " Square Pitch Pans "
                                + orderPansL + " " + orderPansLF + "\" L "
                                + orderPansW + " " + orderPansWF + "\" W "
                                + orderPansH + " " + orderPansHF + "\" H "
                                + orderPansF + " " + orderPansFF + "\" F "
                                + "\n"
                                + "Pitch Pans Notes: " + orderPansNotes
                                + "\n\n";

                        ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                        ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);
                        Toast.makeText(context, "Added to Order √", Toast.LENGTH_SHORT).show();

                        pansGroup.clearCheck();
                        pansQ.setText("");
                        pansW.setText("");
                        getPansW.setSelection(0);
                        pansL.setText("");
                        getPansL.setSelection(0);
                        pansD.setText("");
                        getPansD.setSelection(0);
                        pansH.setText("");
                        getPansH.setSelection(0);
                        pansF.setText("");
                        getPansF.setSelection(0);
                        pansColors.setText("");
                        pansMaterial.setText("");

                        mCustomReviewMessage = "";
                        mCustomSendMessage = "";


                    } else {
                        mCustomReviewMessage += orderPansQ + ": " + orderPansColor + " " + orderPansMaterial
                                + "\n" + "Round Pitch Pans"
                                + "\nDiameter" + orderPansD + " " + orderPansDF + "\" "
                                + "\nHeight: " + orderPansH + " " + orderPansHF + "\" "
                                + "\nFlange: " + orderPansF + " " + orderPansFF + "\" "
                                + "\n"
                                + "Pitch Pans Notes: " + orderPansNotes
                                + "\n\n";

                        mCustomSendMessage += orderPansColor + " " + orderPansMaterial
                                + "\n"
                                + orderPansQ + ") " + "Round Pitch Pans "
                                + orderPansL + " " + orderPansLF + "\" L "
                                + orderPansW + " " + orderPansWF + "\" W "
                                + orderPansH + " " + orderPansHF + "\" H "
                                + orderPansF + " " + orderPansFF + "\" F "
                                + "\n"
                                + "Pitch Pans Notes: " + orderPansNotes
                                + "\n\n";

                        ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                        ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);
                        Toast.makeText(context, "Added to Order √", Toast.LENGTH_SHORT).show();

                        pansGroup.clearCheck();
                        pansQ.setText("");
                        pansW.setText("");
                        getPansW.setSelection(0);
                        pansL.setText("");
                        getPansL.setSelection(0);
                        pansD.setText("");
                        getPansD.setSelection(0);
                        pansH.setText("");
                        getPansH.setSelection(0);
                        pansF.setText("");
                        getPansF.setSelection(0);
                        pansColors.setText("");
                        pansMaterial.setText("");

                        mCustomReviewMessage = "";
                        mCustomSendMessage = "";

                    }

                } else {
                    Toast.makeText(context, "Fill out completely...", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }

}