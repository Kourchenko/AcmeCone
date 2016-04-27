package acme.com.acmecone.Fragments;

import android.app.usage.ConfigurationStats;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class ConesFragment extends Fragment{

    private RadioGroup conesGroup;
    private RadioButton conesSplit, conesNon;
    private EditText conesQ, conesT, conesB, conesH, conesF;
    private Spinner conesTF, conesBF, conesHF, conesFF;
    private Button conesSubmit;
    private EditText conesNotes;
    private String conesChoice;
    String conesRadioChoice = "";
    private String mCustomSendMessage = "";
    private String mCustomReviewMessage = "";



    public ConesFragment() {
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

        final View view = inflater.inflate(R.layout.cones_fragment, container, false);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final Context context = getActivity().getApplicationContext();


        conesGroup = (RadioGroup) view.findViewById(R.id.cones_radioB);

        conesSplit = (RadioButton) view.findViewById(R.id.cones_split);
        conesSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Split", Toast.LENGTH_SHORT).show();
                conesChoice = "Split";
            }
        });

        conesNon = (RadioButton) view.findViewById(R.id.cones_nonsplit);
        conesNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Non-Split", Toast.LENGTH_SHORT).show();
                conesChoice = "NS";
            }
        });

        conesNotes = (EditText) view.findViewById(R.id.cones_notes);

        conesQ = (EditText) view.findViewById(R.id.cones_quantity);
        conesT = (EditText) view.findViewById(R.id.cones_top);
        conesB = (EditText) view.findViewById(R.id.cones_bottom);
        conesH = (EditText) view.findViewById(R.id.cones_height);
        conesF = (EditText) view.findViewById(R.id.cones_flange);

        conesTF = (Spinner) view.findViewById(R.id.cones_top_frac);
        ArrayAdapter<CharSequence> spinnerConesTF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        conesTF.setAdapter(spinnerConesTF);

        conesBF = (Spinner) view.findViewById(R.id.cones_bottom_frac);
        final ArrayAdapter<CharSequence> spinnerConesBF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        conesBF.setAdapter(spinnerConesBF);

        conesHF = (Spinner) view.findViewById(R.id.cones_height_frac);
        ArrayAdapter<CharSequence> spinnerConesHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        conesHF.setAdapter(spinnerConesHF);

        conesFF = (Spinner) view.findViewById(R.id.cones_flange_frac);
        ArrayAdapter<CharSequence> spinnerConesFF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        conesFF.setAdapter(spinnerConesFF);

        ArrayAdapter<String> adapterColors = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView conesColors = (AutoCompleteTextView) view.findViewById(R.id.cones_color);
        conesColors.setAdapter(adapterColors);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView conesMaterial = (AutoCompleteTextView) view.findViewById(R.id.cones_material);
        conesMaterial.setAdapter(adapterMaterial);

        conesSubmit = (Button) view.findViewById(R.id.cones_submitB);
        conesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderConesRadio = conesChoice;

                final String orderConesQ = conesQ.getText().toString();
                final String orderConesT = conesT.getText().toString();
                final String orderConesTF = conesTF.getSelectedItem().toString();
                final String orderConesB = conesB.getText().toString();
                final String orderConesBF = conesBF.getSelectedItem().toString();
                final String orderConesH = conesH.getText().toString();
                final String orderConesHF = conesHF.getSelectedItem().toString();
                final String orderConesF = conesF.getText().toString();
                final String orderConesFF = conesFF.getSelectedItem().toString();
                final String orderConesColor = conesColors.getText().toString().trim();
                final String orderConesMaterial = conesMaterial.getText().toString().trim();
                final String orderNotes = conesNotes.getText().toString();

                if (!orderConesQ.isEmpty()
                        && !orderConesT.isEmpty()
                        && !orderConesB.isEmpty()
                        && !orderConesH.isEmpty()
                        && !orderConesF.isEmpty()
                        && !orderConesRadio.isEmpty()) {

                    mCustomReviewMessage += orderConesQ + ": " + orderConesRadio
                                        + "\n"
                                        + "Custom Cones"
                                        + "\n" + orderConesColor + " " + orderConesMaterial
                                        + "\nTop:" + orderConesT + " " + orderConesTF + "\""
                                        + "\nBottom: " + orderConesB + " " + orderConesBF + "\""
                                        + "\nHeight: " + orderConesH + " " + orderConesHF + "\""
                                        + "\nFlange: " + orderConesF + " " + orderConesFF + "\""
                                        + "\n"
                                        + "Cones Notes: " + orderNotes
                                        +"\n\n";

                    mCustomSendMessage += orderConesColor + " " + orderConesMaterial
                            + "\n"
                            + orderConesQ + ") "
                            + orderConesRadio + " Cones "
                            + orderConesT + " " + orderConesTF + "\" Top "
                            + orderConesB + " " + orderConesBF + "\" Bottom "
                            + orderConesH + " " + orderConesHF + "\" H "
                            + orderConesF + " " + orderConesFF + "\" F "
                            + "\n"
                            + "Cones Notes: " + orderNotes
                            + "\n\n";

                    ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                    ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);

                    Toast.makeText(getActivity().getApplicationContext(), "Added to Order √", Toast.LENGTH_SHORT).show();

                    conesGroup.clearCheck();
                    conesRadioChoice = "";
                    conesNotes.setText("");
                    conesQ.setText("");
                    conesT.setText("");
                    conesTF.setSelection(0);
                    conesB.setText("");
                    conesBF.setSelection(0);
                    conesH.setText("");
                    conesHF.setSelection(0);
                    conesF.setText("");
                    conesFF.setSelection(0);
                    conesColors.setText("");
                    conesMaterial.setText("");

                    mCustomReviewMessage = "";
                    mCustomSendMessage = "";

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Fill Order Completely...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}