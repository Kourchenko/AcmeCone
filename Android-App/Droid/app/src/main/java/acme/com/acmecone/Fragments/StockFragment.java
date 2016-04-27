package acme.com.acmecone.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Map;

import acme.com.acmecone.R;
import acme.com.acmecone.Utility.ConstantVar;


public class StockFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    public Button stockB;
    private EditText stock_edit_quantity;
    private AutoCompleteTextView stock_edit_text;
    private Vibrator vib;
    private int i = 0;

    private static final String[] STOCK_ITEMS = new String[]{
            // RVOs
            "RVO White TPO", "RVO Grey TPO", "RVO White PVC", "RVO Grey PVC",


            // Breather Vents
                // 2-Way
            "[ 2 - WAY ] BV White TPO", "[ 2 - WAY ] BV Grey TPO", "[ 2 - WAY ] BV White PVC", "[ 2 - WAY ] BV Grey PVC",
            "Breather Vent White TPO (2-Way)", "Breather Vent Grey TPO (2-Way)",
            "Breather Vent White PVC (2-Way)", "Breather Vent Grey PVC (2-Way)",

                // 1-Way
            "[ 1 - WAY ] BV White TPO", "[ 1 - WAY ] BV Grey TPO", "[ 1 - WAY ] BV White PVC", "[ 1 - WAY ] BV Grey PVC",
            "Breather Vent White TPO (1-Way)", "Breather Vent Grey TPO (1-Way)",
            "Breather Vent White PVC (1-Way)", "Breather Vent Grey PVC (1-Way)",


            // Cones -- Split
            "[ SPLIT ] Short A Cone White TPO", "[ SPLIT ] Short A Cone Grey TPO", "[ SPLIT ] Short A Cone White PVC", "[ SPLIT ] Short A Cone Grey PVC",
            "[ SPLIT ] A Cone White TPO", "[ SPLIT ] A Cone Grey TPO", "[ SPLIT ] A Cone White PVC", "[ SPLIT ] A Cone Grey PVC",
            "[ SPLIT ] B Cone White TPO", "[ SPLIT ] B Cone Grey TPO", "[ SPLIT ] B Cone White PVC", "[ SPLIT ] B Cone Grey PVC",
            "[ SPLIT ] C Cone White TPO", "[ SPLIT ] C Cone Grey TPO", "[ SPLIT ] C Cone White PVC", "[ SPLIT ] C ConeGrey PVC",


            // Cones -- Non-Split
            "[ NON SPLIT ] Short A White TPO", "[ NON SPLIT ] Short A Cone Grey TPO", "[ NON SPLIT ] Short A Cone White PVC", "[ NON SPLIT ] Short A Cone Grey PVC",
            "[ NON SPLIT ] A Cone White TPO", "[ NON SPLIT ] A Cone Grey TPO", "[ NON SPLIT ] A Cone White PVC", "[ NON SPLIT ] A Cone Grey PVC",
            "[ NON SPLIT ] B Cone White TPO", "[ NON SPLIT ] B Cone Grey TPO", "[ NON SPLIT ] B Cone White PVC", "[ NON SPLIT ] B Cone Grey PVC",
            "[ NON SPLIT ] C Cone White TPO", "[ NON SPLIT ] C Cone Grey TPO", "[ NON SPLIT ] C Cone White PVC", "[ NON SPLIT ] C Cone Grey PVC",


            // Corners
            "[ INSIDE ] Corner White TPO", "[ INSIDE ] Corner Grey TPO", "[ INSIDE ] Corner White PVC", "[ INSIDE ] Corner Grey PVC",
            "[ OUTSIDE ] Corner White TPO", "[ OUTSIDE ] Corner Grey TPO", "[ OUTSIDE ] Corner White PVC", "[ OUTSIDE ] Corner Grey PVC",


            // Drains
            "[ Standard ] 2\" Drop Scupper White TPO", "[ Standard ] 2\" Drop Scupper Grey TPO",
            "[ Standard ] 2\" Drop Scupper White PVC", "[ Standard ] 2\" Drop Scupper Grey PVC",

            "[ Standard ] 3\" Drop Scupper White TPO", "[ Standard ] 3\" Drop Scupper Grey TPO",
            "[ Standard ] 3\" Drop Scupper White PVC", "[ Standard ] 3\" Drop Scupper Grey PVC",

            "[ Metal ] 2\" Drop White TPO", "[ Metal ] 2\" Drop Grey TPO",
            "[ Metal ] 2\" Drop White PVC", "[ Metal ] 2\" Drop Grey PVC",

            "[ Metal ] Drop 3\" White TPO", "[ Metal ] 3\" Drop Grey TPO",
            "[ Metal ] Drop 3\" White PVC", "[ Metal ] 3\" Drop Grey PVC",

            "3\" Commercial Drain White TPO", "3\" Commercial Drain White PVC",
            "3\" Commercial Drain Grey TPO", "3\" Commercial Drain Grey PVC",


            // ThruWalls
            "4\"X4\" Standard ThruWall Scupper White TPO", "4\"X4\" Standard ThruWall Scupper Grey TPO",
            "4\"X4\" Standard ThruWall Scupper White PVC", "4\"X4\" Standard ThruWall Scupper Grey PVC",

            "6\"X6\" Standard ThruWall Scupper White TPO", "6\"X6\" Standard ThruWall Scupper Grey TPO",
            "6\"X6\" Standard ThruWall Scupper White PVC", "6\"X6\" Standard ThruWall Scupper Grey PVC",


            // Pitch Pans
            // Round
            "[ ROUND ] 6\" Pitch Pan White TPO", "[ ROUND ] 6\" Pitch Pan Grey TPO",
            "[ ROUND ] 6\" Pitch Pan White PVC", "[ ROUND ] 6\" Pitch Pan Grey PVC",

            "[ ROUND ] 9\" Pitch Pan White TPO", "[ ROUND ] 9\" Pitch Pan Grey TPO",
            "[ ROUND ] 9\" Pitch Pan White PVC", "[ ROUND ] 9\" Pitch Pan Grey PVC",

            "[ ROUND ] 12\" Pitch Pan White TPO", "[ ROUND ] 12\" Pitch Pan Grey TPO",
            "[ ROUND ] 12\" Pitch Pan White PVC", "[ ROUND ] 12\" Pitch Pan Grey PVC",

            // Square
            "[ SQUARE ] 6\"X6\" Pitch Pan White TPO", "[ SQUARE ] 6\"X6\" Pitch Pan Grey TPO",
            "[ SQUARE ] 6\"x6\" Pitch Pan White PVC", "[ SQUARE ] 6\"x6\" Pitch Pan Grey PVC",

            "[ SQUARE ] 9\"X9\" Pitch Pan White TPO", "[ SQUARE ] 9\"X9\" Pitch Pan Grey TPO",
            "[ SQUARE ] 9\"X9\" Pitch Pan White PVC", "[ SQUARE ] 9\"X9\" Pitch Pan Grey PVC",


            "[ SQUARE ] 12\"X12\" Pitch Pan White TPO", "[ SQUARE ] 12\"X12\" Pitch Pan Grey TPO",
            "[ SQUARE ] 12\"X12\" Pitch Pan White PVC", "[ SQUARE ] 12\"X12\" Pitch Pan Grey PVC"

    };

    public StockFragment() {
        // Required constructor:: EMPTY
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.stock_fragment, container, false);
        final Context context = getActivity().getApplicationContext();

        vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        // InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        stock_edit_quantity = (EditText) view.findViewById(R.id.stock_edit_quantity);
        stock_edit_text = (AutoCompleteTextView) view.findViewById(R.id.stock_edit_field);
        stockB = (Button) view.findViewById(R.id.stock_add_button);


        stockB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    final Integer quantity = Integer.parseInt(stock_edit_quantity.getText().toString());
                    final String type = stock_edit_text.getText().toString();

                    if (ConstantVar.REVIEW_DATABASE.values().contains(type)) {

                        // Alert User of Overwriting Product
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                        alert.setTitle(type + " already in Cart!")
                                .setPositiveButton("ADD TO SUM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        for (Map.Entry<Integer, String> entry : ConstantVar.REVIEW_DATABASE.entrySet()) {
                                            String mDSET = entry.getKey() + ": " + entry.getValue();
                                            if (ConstantVar.DATASET.contains(mDSET)) {

                                                System.out.println("DATASET contained: " + mDSET);
                                                System.out.println("DATASET indexOf: " + ConstantVar.DATASET.indexOf(mDSET));
                                                Integer temp = entry.getKey();
                                                Integer sum = temp + quantity;

                                                System.out.println();
                                                System.out.println();
                                                System.out.println(">>> " + ConstantVar.REVIEW_DATABASE.toString());
                                                System.out.println(">> " + ConstantVar.DATASET.toString());
                                                System.out.println();
                                                System.out.println();

                                                ConstantVar.DATASET.remove(ConstantVar.DATASET.indexOf(mDSET));
                                                ConstantVar.REVIEW_DATABASE.values().removeAll(Collections.singleton(entry.getValue()));

                                                ConstantVar.REVIEW_DATABASE.put(sum, type);
                                                ConstantVar.DATASET.add(0, sum + ": " + type);

                                            }
                                        }
                                    }
                                })
                                .setNeutralButton("REPLACE QUANTITY", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        //  Find and Replace Quantity Product in List
                                        ConstantVar.DATASET.removeAll(Collections.singleton(type));
                                        ConstantVar.REVIEW_DATABASE.values().removeAll(Collections.singleton(type));

                                        ConstantVar.REVIEW_DATABASE.put(quantity, type);
                                        ConstantVar.DATASET.add(0, quantity + ": " + type);

                                    }
                                })
                                .show();

                        stock_edit_quantity.setText("");
                        stock_edit_text.setText("");

                    } else if (!ConstantVar.REVIEW_DATABASE.values().contains(type)) {
                        ConstantVar.REVIEW_DATABASE.put(quantity, type);
                        ConstantVar.DATASET.add(0, quantity.toString() + ": " + type);

                        stock_edit_text.setText("");
                        stock_edit_quantity.setText("");
                    }
                } catch (NumberFormatException e) {
                    vib.vibrate(50);
                }
            }
         });

        //AUTO-COMPLETE TABLE
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.my_list_item, STOCK_ITEMS);
        final AutoCompleteTextView stockItem = stock_edit_text;
        stockItem.setAdapter(adapter);

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.stock_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        return view;
    }

    public boolean builderTools(final AlertDialog.Builder builder, LayoutInflater inflater, final String string) {
        /*
        * Alert Dialog Box Builder for each idString
        */
        final View activity = inflater.inflate(R.layout.stock_alert_dialog, null);

        try {
            builder.setView(activity)
                    .setTitle(string)
                    .setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (getView() != null) {
                                final EditText editText = (EditText) activity.findViewById(R.id.stock_dialog_edittext);
                                final Integer quantity = Integer.parseInt(editText.getText().toString());


                                if (ConstantVar.REVIEW_DATABASE.values().contains(string)) {
                                    AlertDialog.Builder existsAlert = new AlertDialog.Builder(getActivity());
                                    existsAlert.setTitle(string + " already in Cart!")
                                            .setPositiveButton("ADD TO SUM", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    for (Map.Entry<Integer, String> entry : ConstantVar.REVIEW_DATABASE.entrySet()) {
                                                        if (entry.getValue().equals(string)) {
                                                            Integer temp = entry.getKey();
                                                            Integer sum = temp + quantity;

                                                            ConstantVar.REVIEW_DATABASE.values().removeAll(Collections.singleton(string));
                                                            ConstantVar.DATASET.removeAll(Collections.singleton(temp.toString() + ": " + string));

                                                            System.out.println("Empty REVIEW: " + ConstantVar.REVIEW_DATABASE.toString());
                                                            System.out.println("Empty DATASET: " + ConstantVar.DATASET.toString());

                                                            ConstantVar.REVIEW_DATABASE.put(sum, string);
                                                            ConstantVar.DATASET.add(0, sum.toString() + ": " + string);


                                                        }
                                                    }
                                                }
                                            })
                                            .setNeutralButton("REPLACE QUANTITY", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    for (Map.Entry<Integer, String> entry : ConstantVar.REVIEW_DATABASE.entrySet()) {
                                                        if (entry.getValue().equals(string)) {
                                                            ConstantVar.DATASET.removeAll(Collections.singleton(entry.getKey().toString() + ": " + string));
                                                        }
                                                    }
                                                    ConstantVar.REVIEW_DATABASE.values().removeAll(Collections.singleton(string));

                                                    ConstantVar.REVIEW_DATABASE.put(quantity, string);
                                                    ConstantVar.DATASET.add(0, quantity + ": " + string);
                                                }
                                            })
                                            .show();

                                } else if (!ConstantVar.REVIEW_DATABASE.values().contains(string)) {
                                    ConstantVar.REVIEW_DATABASE.put(quantity, string);
                                    ConstantVar.DATASET.add(0, quantity.toString() + ": " + string);
                                }
                            }
                        }
                    })

                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

        } catch (NumberFormatException e) {
            vib.vibrate(50);
        }
        return true;

    }

    @SuppressWarnings("StetementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        switch (id) {
            case R.id.stock_RVVWTPO:
                builderTools(builder, inflater, "RVO White TPO");
                break;

            case R.id.stock_RVVGTPO:
                builderTools(builder, inflater, "RVO Grey TPO");
                break;

            case R.id.stock_RVVWPVC:
                builderTools(builder, inflater, "RVO White PVC");
                break;

            case R.id.stock_RVVGPVC:
                builderTools(builder, inflater, "RVO Grey PVC");
                break;

            case R.id.stock_SASWT:
                builderTools(builder, inflater, "[SPLIT] Short A Cone White TPO");
                break;

            case R.id.stock_SASGT:
                builderTools(builder, inflater, "[SPLIT] Short A Cone Grey TPO");
                break;

            case R.id.stock_SASWP:
                builderTools(builder, inflater, "[SPLIT] Short A Cone White PVC");
                break;

            case R.id.stock_SASGP:
                builderTools(builder, inflater, "[SPLIT] Short A Cone Grey PVC");
                break;

            case R.id.stock_ACSWT:
                builderTools(builder, inflater, "[SPLIT] A Cone White TPO");
                break;

            case R.id.stock_ACSGT:
                builderTools(builder, inflater, "[SPLIT] A Cone Grey TPO");
                break;

            case R.id.stock_ACSWP:
                builderTools(builder, inflater, "[SPLIT] A Cone White PVC");
                break;


            case R.id.stock_ACSGP:
                builderTools(builder, inflater, "[SPLIT] A Cone Grey PVC");
                break;

            case R.id.stock_BCSWT:
                builderTools(builder, inflater, "[SPLIT] B Cone White TPO");
                break;

            case R.id.stock_BCSGT:
                builderTools(builder, inflater, "[SPLIT] B Cone Grey TPO");
                break;

            case R.id.stock_BCSWP:
                builderTools(builder, inflater, "[SPLIT] B Cone White PVC");
                break;

            case R.id.stock_BCSGP:
                builderTools(builder, inflater, "[SPLIT] B Cone Grey PVC");
                break;

            case R.id.stock_CCSWT:
                builderTools(builder, inflater, "[SPLIT] C Cone White TPO");
                break;

            case R.id.stock_CCSGT:
                builderTools(builder, inflater, "[SPLIT] C Cone Grey TPO");
                break;

            case R.id.stock_CCSWP:
                builderTools(builder, inflater, "[SPLIT] C Cone White PVC");
                break;

            case R.id.stock_CCSGP:
                builderTools(builder, inflater, "[SPLIT] C Cone Grey PVC");
                break;




            case R.id.stock_BV1WT:
                builderTools(builder, inflater, "1-Way Breather Vent White TPO");
                break;

            case R.id.stock_BV1GT:
                builderTools(builder, inflater, "1-Way Breather Vent Grey TPO");
                break;

            case R.id.stock_BV1WP:
                builderTools(builder, inflater, "1-Way Breather Vent White PVC");
                break;

            case R.id.stock_BV1GP:
                builderTools(builder, inflater, "1-Way Breather Vent Grey PVC");
                break;

            case R.id.stock_BV2WT:
                builderTools(builder, inflater, "2-Way Breather Vent White TPO");
                break;

            case R.id.stock_BV2GT:
                builderTools(builder, inflater, "2-Way Breather Vent Grey TPO");
                break;

            case R.id.stock_BV2WP:
                builderTools(builder, inflater, "2-Way Breather Vent White PVC");
                break;

            case R.id.stock_BV2GP:
                builderTools(builder, inflater, "2-Way Breather Vent Grey PVC");
                break;

            case R.id.stock_CIWTPO:
                builderTools(builder, inflater, "[INSIDE] Corner White TPO");
                break;

            case R.id.stock_CIGTPO:
                builderTools(builder, inflater, "[INSIDE] Corner Grey TPO");
                break;

            case R.id.stock_CIWPVC:
                builderTools(builder, inflater, "[INSIDE] Corner White PVC");
                break;

            case R.id.stock_CIGPVC:
                builderTools(builder, inflater, "[INSIDE] Corner Grey PVC");
                break;

            case R.id.stock_COWTPO:
                builderTools(builder, inflater, "[OUTSIDE] Corner White TPO");
                break;

            case R.id.stock_COGTPO:
                builderTools(builder, inflater, "[OUTSIDE] Corner Grey TPO");
                break;

            case R.id.stock_COWPVC:
                builderTools(builder, inflater, "[OUTSIDE] Corner White PVC");
                break;

            case R.id.stock_COGPVC:
                builderTools(builder, inflater, "[OUTSIDE] Corner Grey PVC");
                break;

            case R.id.DS2WT:
                builderTools(builder, inflater, "2\" Drop Scupper White TPO");
                break;

            case R.id.DS2GT:
                builderTools(builder, inflater, "2\" Drop Scupper Grey TPO");
                break;

            case R.id.DS2WP:
                builderTools(builder, inflater, "2\" Drop Scupper White PVC");
                break;

            case R.id.DS2GP:
                builderTools(builder, inflater, "2\" Drop Scupper Grey PVC");
                break;

            case R.id.DS3WT:
                builderTools(builder, inflater, "3\" Drop Scupper White TPO");
                break;

            case R.id.DS3GT:
                builderTools(builder, inflater, "3\" Drop Scupper Grey TPO");
                break;

            case R.id.DS3WP:
                builderTools(builder, inflater, "3\" Drop Scupper White PVC");
                break;

            case R.id.DS3GP:
                builderTools(builder, inflater, "3\" Drop Scupper Grey PVC");
                break;

            case R.id.CDS3WT:
                builderTools(builder, inflater, "3\" Commercial Drain White TPO");
                break;

            case R.id.CDS3GT:
                builderTools(builder, inflater, "3\" Commercial Drain Grey TPO");
                break;

            case R.id.CDS3WP:
                builderTools(builder, inflater, "3\" Commercial Drain White PVC");
                break;

            case R.id.CDS3GP:
                builderTools(builder, inflater, "3\" Commercial Drain Grey PVC");
                break;

            case R.id.TW4WT:
                builderTools(builder, inflater, "4\"X4\" ThruWall Scupper White TPO");
                break;

            case R.id.TW4GT:
                builderTools(builder, inflater, "4\"X4\" ThruWall Scupper Grey TPO");
                break;

            case R.id.TW4WP:
                builderTools(builder, inflater, "4\"X4\" ThruWall Scupper White PVC");
                break;

            case R.id.TW4GP:
                builderTools(builder, inflater, "4\"X4\" ThruWall Scupper Grey PVC");
                break;

            case R.id.TW6WT:
                builderTools(builder, inflater, "6\"X6\" ThruWall Scupper White TPO");
                break;

            case R.id.TW6GT:
                builderTools(builder, inflater, "6\"X6\" ThruWall Scupper Grey TPO");
                break;

            case R.id.TW6WP:
                builderTools(builder, inflater, "6\"X6\" ThruWall Scupper White PVC");
                break;

            case R.id.TW6GP:
                builderTools(builder, inflater, "6\"X6\" ThruWall Scupper Grey PVC");
                break;


        }

        return true;
    }

}
