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


public class DrainsFragment extends Fragment{

    private RadioGroup drainsGroup;
    public RadioButton drainsCustom, drainsTwo, drainsThree, drainsComm;
    public Button drainsB;
    private RelativeLayout drainsDepth, drainsDiameter;
    private EditText drainsQ, drainsDe, drainsDi, drainsF;
    private Spinner drainsDeF, drainsDiF, drainsFF;
    private String drainsRadioChoiceRegular = "";
    private String drainsRadioChoiceCustom = "";
    private TextView drainsNotes;
    private String mCustomSendMessage = "";
    private String mCustomReviewMessage = "";

    public DrainsFragment() {
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

        final View view = inflater.inflate(R.layout.drains_fragment, container, false);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final Context context = getActivity().getApplicationContext();

        drainsDepth = (RelativeLayout) view.findViewById(R.id.drains_depth_choice);
        drainsDiameter = (RelativeLayout) view.findViewById(R.id.drains_diameter_choice);

        drainsNotes = (TextView) view.findViewById(R.id.drains_notes);

        drainsGroup = (RadioGroup) view.findViewById(R.id.drains_radio);
        drainsCustom = (RadioButton) view.findViewById(R.id.drains_custom);
        drainsTwo = (RadioButton) view.findViewById(R.id.drains_two_metal_choice);
        drainsThree = (RadioButton) view.findViewById(R.id.drains_three_metal_choice);
        drainsComm = (RadioButton) view.findViewById(R.id.drains_commercial_choice);

        drainsCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Custom Drops", Toast.LENGTH_SHORT).show();
                drainsDiameter.setVisibility(View.VISIBLE);
                drainsDepth.setVisibility(View.VISIBLE);
                drainsRadioChoiceRegular = "Custom";
                drainsRadioChoiceCustom = "";
            }
        });

        drainsTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "2\" Metal, Standard Metal Flange", Toast.LENGTH_SHORT).show();
                drainsDiameter.setVisibility(View.GONE);
                drainsDepth.setVisibility(View.GONE);
                drainsRadioChoiceCustom = "2\" Metal Drop";
                drainsRadioChoiceRegular = "";
            }
        });

        drainsThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "3\" Metal, Standard Metal Flange", Toast.LENGTH_SHORT).show();
                drainsDiameter.setVisibility(View.GONE);
                drainsDepth.setVisibility(View.GONE);
                drainsRadioChoiceCustom = "3\" Metal Drop";
                drainsRadioChoiceRegular = "";
            }
        });

        drainsComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "3\" Commercial, Standard Metal Flange", Toast.LENGTH_SHORT).show();
                drainsDiameter.setVisibility(View.GONE);
                drainsDepth.setVisibility(View.GONE);
                drainsRadioChoiceCustom = "3\" Commercial Drop";
                drainsRadioChoiceRegular = "";
            }
        });

        drainsQ = (EditText) view.findViewById(R.id.drains_quantity);
        drainsDi = (EditText) view.findViewById(R.id.drains_diameter);
        drainsDe = (EditText) view.findViewById(R.id.drains_depth);
        drainsF = (EditText) view.findViewById(R.id.drains_flange);

        drainsDiF = (Spinner) view.findViewById(R.id.drains_diameter_frac);
        ArrayAdapter<CharSequence> spinnerPipesDF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        drainsDiF.setAdapter(spinnerPipesDF);

        drainsDeF = (Spinner) view.findViewById(R.id.drains_depth_frac);
        ArrayAdapter<CharSequence> spinnerPipesHF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        drainsDeF.setAdapter(spinnerPipesHF);

        drainsFF = (Spinner) view.findViewById(R.id.drains_flange_frac);
        ArrayAdapter<CharSequence> spinnerPipesFF = ArrayAdapter.createFromResource(context,
                R.array.custom_fractions, R.layout.my_spinner_item);
        drainsFF.setAdapter(spinnerPipesFF);

        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.COLORS);
        final AutoCompleteTextView colors = (AutoCompleteTextView) view.findViewById(R.id.drains_color);
        colors.setAdapter(adapterColor);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(context, R.layout.my_list_item, ConstantVar.MATERIALS);
        final AutoCompleteTextView materials = (AutoCompleteTextView) view.findViewById(R.id.drains_material);
        materials.setAdapter(adapterMaterial);



        drainsB = (Button) view.findViewById(R.id.drains_submitB);
        drainsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderDrainsRadioRegular = drainsRadioChoiceRegular;
                final String orderDrainsRadioCustom = drainsRadioChoiceCustom;
                final String orderDrainsQ = drainsQ.getText().toString();
                final String orderDrainsDi = drainsDi.getText().toString();
                final String orderDrainsDe = drainsDe.getText().toString();
                final String orderDrainsF = drainsF.getText().toString();
                final String orderColor = colors.getText().toString();
                final String orderMaterial = materials.getText().toString();
                final String orderDrainsDiF = drainsDeF.getSelectedItem().toString();
                final String orderDrainsDeF = drainsDiF.getSelectedItem().toString();
                final String orderDrainsFF = drainsFF.getSelectedItem().toString();
                final String orderNotes = drainsNotes.getText().toString();

                if (!orderDrainsQ.isEmpty()
                        && !orderDrainsF.isEmpty()
                        && !orderColor.isEmpty()
                        && !orderMaterial.isEmpty()) {

                    if (!orderDrainsDi.isEmpty()
                            && !orderDrainsDe.isEmpty()
                            && !orderDrainsRadioRegular.isEmpty()) {

                        mCustomReviewMessage += orderDrainsQ + ": " + orderColor + " " + orderMaterial
                                + "\n" + orderDrainsRadioRegular + " Drains "
                                + "\nDiameter: " + orderDrainsDi + " " + orderDrainsDiF + "\""
                                + "\nDepth: " + orderDrainsDe + " " + orderDrainsDeF + "\""
                                + "\nFlange: " + orderDrainsF + " " + orderDrainsFF + "\""
                                + "\n"
                                + "Drains Notes: " + orderNotes
                                + "\n\n";

                        mCustomSendMessage += orderColor + " " + orderMaterial
                                + "\n"
                                + orderDrainsQ + ") "
                                + orderDrainsRadioRegular + " Drains "
                                + orderDrainsDi + " " + orderDrainsDiF + "\" Diameter  "
                                + orderDrainsDe + " " + orderDrainsDeF + "\" Depth  "
                                + orderDrainsF + " " + orderDrainsFF + "\" F"
                                + "\n"
                                + "Drains Notes: " + orderNotes
                                + "\n\n";

                        ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                        ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);

                        Toast.makeText(context, "Added to Order √", Toast.LENGTH_SHORT).show();

                        drainsGroup.clearCheck();
                        drainsRadioChoiceRegular = "";
                        drainsRadioChoiceCustom = "";
                        drainsQ.setText("");
                        drainsDi.setText("");
                        drainsDiF.setSelection(0);
                        drainsDe.setText("");
                        drainsDeF.setSelection(0);
                        drainsF.setText("");
                        drainsFF.setSelection(0);
                        colors.setText("");
                        materials.setText("");
                        mCustomReviewMessage = "";
                        mCustomSendMessage = "";

                    } else {
                        mCustomReviewMessage += orderDrainsQ + ": " + orderColor + " " + orderMaterial
                                + "\n" + orderDrainsRadioCustom + " Drains"
                                + "\nFlange: " + orderDrainsF + " " + orderDrainsFF + "\""
                                + "\n"
                                + "Drains Notes: " + orderNotes
                                + "\n\n";

                        mCustomSendMessage += orderColor + " " + orderMaterial
                                + "\n"
                                + orderDrainsQ + ") "
                                + orderDrainsRadioCustom + " Drains "
                                + orderDrainsF + " " + orderDrainsFF + "\" F "
                                + "\n"
                                + "Drains Notes: " + orderNotes
                                + "\n\n";

                        ConstantVar.REVIEW_DATABASE.put(Integer.parseInt(mCustomReviewMessage), mCustomReviewMessage);
                        ConstantVar.SEND_DATABASE.put(mCustomSendMessage, mCustomSendMessage);

                        Toast.makeText(context, "Added to Order √", Toast.LENGTH_SHORT).show();

                        drainsGroup.clearCheck();
                        drainsRadioChoiceRegular = "";
                        drainsRadioChoiceCustom = "";
                        drainsQ.setText("");
                        drainsDi.setText("");
                        drainsDiF.setSelection(0);
                        drainsDe.setText("");
                        drainsDeF.setSelection(0);
                        drainsF.setText("");
                        drainsFF.setSelection(0);
                        colors.setText("");
                        materials.setText("");

                        mCustomReviewMessage = "";
                        mCustomSendMessage = "";

                    }
                }
                else {
                    Toast.makeText(context, "Fill Order Completely...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;


    }

}