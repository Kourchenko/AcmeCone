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
import android.widget.TextView;
import android.widget.Toast;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class PipesFragment extends Fragment{

    private RadioGroup pipesGroup;
    private RadioButton pipesS, pipesN;
    private Button pipesB;
    private EditText pipesQ, pipesH, pipesD, pipesF;
    private Spinner pipesDF, pipesHF, pipesFF;
    private EditText pipesNotes;
    public String pipesRadioChoice = "";
    private String mCustomReviewMessage = "";
    private String mCustomSendMessage = "";


    public PipesFragment() {
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
        final View view = inflater.inflate(R.layout.pipes_fragment, container, false);

        final Context context = getActivity().getApplicationContext();

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        pipesGroup = (RadioGroup) view.findViewById(R.id.pipes_radioB);

        pipesS = (RadioButton) view.findViewById(R.id.pipes_split);
        pipesS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Split", Toast.LENGTH_SHORT).show();
                pipesRadioChoice = "Split";
            }
        });

        pipesN = (RadioButton) view.findViewById(R.id.pipes_nonsplit);
        pipesN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Non-Split", Toast.LENGTH_SHORT).show();
                pipesRadioChoice = "Split";
            }
        });

        pipesQ = (EditText) view.findViewById(R.id.pipes_quantity);
        pipesD = (EditText) view.findViewById(R.id.pipes_diameter);
        pipesH = (EditText) view.findViewById(R.id.pipes_height);
        pipesF = (EditText) view.findViewById(R.id.pipes_flange);

        pipesDF = (Spinner) view.findViewById(R.id.pipes_diameter_frac);
        ArrayAdapter<CharSequence> spinnerPipesDF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        pipesDF.setAdapter(spinnerPipesDF);

        pipesHF = (Spinner) view.findViewById(R.id.pipes_height_frac);
        ArrayAdapter<CharSequence> spinnerPipesHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        pipesHF.setAdapter(spinnerPipesHF);

        pipesFF = (Spinner) view.findViewById(R.id.pipes_flange_frac);
        ArrayAdapter<CharSequence> spinnerPipesFF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        pipesFF.setAdapter(spinnerPipesFF);

        pipesNotes = (EditText) view.findViewById(R.id.pipes_notes);

        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView colors = (AutoCompleteTextView) view.findViewById(R.id.pipes_color);
        colors.setAdapter(adapterColor);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView materials = (AutoCompleteTextView) view.findViewById(R.id.pipes_material);
        materials.setAdapter(adapterMaterial);


        pipesB = (Button) view.findViewById(R.id.pipes_submitB);
        pipesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderPipesRadio = pipesRadioChoice;
                final String orderPipesQ = pipesQ.getText().toString();
                final String orderPipesD = pipesD.getText().toString();
                final String orderPipesH = pipesH.getText().toString();
                final String orderPipesF = pipesF.getText().toString();
                final String orderColors = colors.getText().toString();
                final String orderMaterials = materials.getText().toString();
                final String orderPipesDF = pipesDF.getSelectedItem().toString();
                final String orderPipesHF = pipesHF.getSelectedItem().toString();
                final String orderPipesFF = pipesFF.getSelectedItem().toString();
                final String orderNotes = pipesNotes.getText().toString();

                if (!orderPipesQ.isEmpty()
                        && !orderPipesD.isEmpty()
                        && !orderPipesH.isEmpty()
                        && !orderPipesF.isEmpty()
                        && !orderPipesRadio.isEmpty()
                        && !orderColors.isEmpty()
                        && !orderMaterials.isEmpty()) {

                    mCustomReviewMessage += orderPipesQ + ": " + orderColors + " " + orderMaterials
                            + "\n" + orderPipesRadio + " Custom Pipe Wraps"
                            + "\nDiameter: " + orderPipesD + " " + orderPipesDF + "\" "
                            + "\nHeight: "   + orderPipesH + " " + orderPipesHF + "\" "
                            + "\nFlange: "   + orderPipesF + " " + orderPipesFF + "\" "
                            + "\n"
                            + "Custom Pipes Notes: " + orderNotes
                            +"\n\n";


                    mCustomSendMessage += orderColors + " " + orderMaterials
                            + "\n"
                            + orderPipesQ + ") "
                            + orderPipesRadio + " Custom Pipe Wraps "
                            + orderPipesD + " " + orderPipesDF + "\" D "
                            + orderPipesH + " " + orderPipesHF + "\" H "
                            + orderPipesF + " " + orderPipesFF + "\" F "
                            + "\n"
                            + "PipeWrap Notes: " + orderNotes
                            + "\n\n";

                    ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                    ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);

                    Toast.makeText(context, "Added to Order √", Toast.LENGTH_SHORT).show();

                    pipesGroup.clearCheck();
                    pipesRadioChoice = "";
                    pipesQ.setText("");
                    pipesD.setText("");
                    pipesDF.setSelection(0);
                    pipesH.setText("");
                    pipesHF.setSelection(0);
                    pipesF.setText("");
                    pipesFF.setSelection(0);
                    pipesNotes.setText("");
                    colors.setText("");
                    materials.setText("");

                    mCustomReviewMessage = "";
                    mCustomSendMessage = "";
                }
                else {
                    Toast.makeText(context, "Fill Order Completely...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}