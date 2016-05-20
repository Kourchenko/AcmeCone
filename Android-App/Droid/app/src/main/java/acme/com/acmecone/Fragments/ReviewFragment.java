package acme.com.acmecone.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


import acme.com.acmecone.Adapters.ProductsAdapter;
import acme.com.acmecone.Adapters.ViewAdapter;
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
import acme.com.acmecone.Adapters.ListViewAdapter;
import acme.com.acmecone.Adapters.SwipeToDismissTouchListener;
import acme.com.acmecone.Utility.ConstantVar;


public class ReviewFragment extends Fragment {

    private static final int TIME_TO_AUTOMATICALLY_DISMISS_ITEM = 3000;
    public ListView stockListView, coneListView, cornerListView, pipeListView, dropListView, scupperListView, panListView, tubeListView, curbListView, sleeperListView;
    public LinearLayout stockLayout, coneLayout, cornerLayout, pipeLayout, dropLayout, scupperLayout, panLayout, tubeLayout, curbLayout, sleeperLayout;
    private Vibrator vib;


    private static final String[] STOCK_ITEMS = new String[]{
            // RVOs
            "RVO White TPO", "RVO Grey TPO", "RVO White PVC", "RVO Grey PVC",


            // Breather Vents
            "2-Way BV White TPO", "2-Way BV Grey TPO", "2-Way White PVC", "2-Way Grey PVC",
            "Breather Vent White TPO (2-Way)", "Breather Vent Grey TPO (2-Way)",
            "Breather Vent White PVC (2-Way)", "Breather Vent Grey PVC (2-Way)",


            // Cones -- Split
            "A Cone [Split] White TPO", "A Cone [Split] Grey TPO", "A Cone [Split] White PVC", "A Cone [Split] Grey PVC",
            "B Cone [Split] White TPO", "B Cone [Split] Grey TPO", "B Cone [Split] White PVC", "B Cone [Split] Grey PVC",
            "C Cone [Split] White TPO", "C Cone [Split] Grey TPO", "C Cone [Split] White PVC", "C Cone [Split] Grey PVC",


            // Cones -- Non-Split
            "A Cone [Non-Split] White TPO", "A Cone [Non-Split] Grey TPO", "A Cone [Non-Split] White PVC", "A Cone [Non-Split] Grey PVC",
            "B Cone [Non-Split] White TPO", "B Cone [Non-Split] Grey TPO", "B Cone [Non-Split] White PVC", "B Cone [Non-Split] Grey PVC",
            "C Cone [Non-Split] White TPO", "C Cone [Non-Split] Grey TPO", "C Cone [Non-Split] White PVC", "C Cone [Non-Split] Grey PVC",


            // Corners
            "Inside Corner White TPO", "Inside Corner Grey TPO", "Inside Corner White PVC", "Inside Corner Grey PVC",
            "Outside Corner White TPO", "Outside Corner Grey TPO", "Outside Corner White PVC", "Outside Corner Grey PVC",


            // Drains
            "Drop Scupper - 2\" White TPO", "Drop Scupper - 2\" Grey TPO",
            "Drop Scupper - 2\" White PVC", "Drop Scupper - 2\" Grey PVC",

            "Drop Scupper - 3\" White TPO", "Drop Scupper - 3\" Grey TPO",
            "Drop Scupper - 3\" White PVC", "Drop Scupper - 3\" Grey PVC",

            "Metal Drop - 2\" White TPO", "Metal Drop - 2\" Grey TPO",
            "Metal Drop - 2\" White PVC", "Metal Drop - 2\" Grey PVC",

            "Metal Drop - 3\" White TPO", "Metal Drop - 3\" Grey TPO",
            "Metal Drop - 3\" White PVC", "Metal Drop - 3\" Grey PVC",

            "3\" Commercial Drain White TPO", "3\" Commercial Drain White PVC",
            "3\" Commercial Drain Grey TPO", "3\" Commercial Drain Grey PVC",

            "4\"X4\" Standard ThruWall Scupper White TPO", "4\"X4\" Standard ThruWall Scupper Grey TPO",
            "4\"X4\" Standard ThruWall Scupper White PVC", "4\"X4\" Standard ThruWall Scupper Grey PVC",

            "6\"X6\" Standard ThruWall Scupper White TPO", "6\"X6\" Standard ThruWall Scupper Grey TPO",
            "6\"X6\" Standard ThruWall Scupper White PVC", "6\"X6\" Standard ThruWall Scupper Grey PVC",

            "Pitch Pan Standard White TPO", "Pitch Pan Standard Grey TPO",
            "Pitch Pan Standard White PVC", "Pitch Pan Standard Grey PVC"
    };

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.review_fragment, container, false);
        final Context context = getActivity().getApplicationContext();
        final StockBaseAdapter stockAdapter = new StockBaseAdapter();
        final ConeBaseAdapter coneAdapter = new ConeBaseAdapter();
        final CornerBaseAdapter cornerAdapter = new CornerBaseAdapter();
        final PipeBaseAdapter pipeAdapter = new PipeBaseAdapter();
        final DropBaseAdapter dropAdapter = new DropBaseAdapter();
        final ScupperBaseAdapter scupperAdapter = new ScupperBaseAdapter();
        final PansBaseAdapter pansAdapter = new PansBaseAdapter();
        final TubesBaseAdapter tubesAdapter = new TubesBaseAdapter();
        final CurbsBaseAdapter curbsAdapter = new CurbsBaseAdapter();
        final SleepersBaseAdapter sleepersAdapter = new SleepersBaseAdapter();



        vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        stockListView = (ListView) view.findViewById(R.id.review_list_view_stock);
        coneListView = (ListView) view.findViewById(R.id.review_list_view_cones);
        cornerListView = (ListView) view.findViewById(R.id.review_list_view_corners);
        pipeListView = (ListView) view.findViewById(R.id.review_list_view_pipes);
        dropListView = (ListView) view.findViewById(R.id.review_list_view_drops);
        scupperListView = (ListView) view.findViewById(R.id.review_list_view_scuppers);
        panListView = (ListView) view.findViewById(R.id.review_list_view_pans);
        tubeListView = (ListView) view.findViewById(R.id.review_list_view_tubes);
        curbListView = (ListView) view.findViewById(R.id.review_list_view_curbs);
        sleeperListView = (ListView) view.findViewById(R.id.review_list_view_sleepers);


        // ! Layout Changes for Empty Lists
        stockLayout = (LinearLayout) view.findViewById(R.id.layout_stock);
        if (ConstantVar.DATASET.isEmpty()) {
            stockLayout.setVisibility(View.GONE);
            stockAdapter.notifyDataSetChanged();
        } else {
            stockLayout.setVisibility(View.VISIBLE);
            stockAdapter.notifyDataSetChanged();
        }

        coneLayout = (LinearLayout) view.findViewById(R.id.layout_cone);
        if (ConstantVar.CONES.isEmpty()) {
            coneLayout.setVisibility(View.GONE);
            coneAdapter.notifyDataSetChanged();
        } else {
            coneLayout.setVisibility(View.VISIBLE);
            coneAdapter.notifyDataSetChanged();
        }

        cornerLayout = (LinearLayout) view.findViewById(R.id.layout_corner);
        if (ConstantVar.CORNERS.isEmpty()) {
            cornerLayout.setVisibility(View.GONE);
            cornerAdapter.notifyDataSetChanged();
        } else {
            cornerLayout.setVisibility(View.VISIBLE);
            cornerAdapter.notifyDataSetChanged();
        }

        pipeLayout = (LinearLayout) view.findViewById(R.id.layout_pipe);
        if (ConstantVar.PIPES.isEmpty()) {
            pipeLayout.setVisibility(View.GONE);
            pipeAdapter.notifyDataSetChanged();
        } else {
            pipeLayout.setVisibility(View.VISIBLE);
            pipeAdapter.notifyDataSetChanged();
        }

        dropLayout = (LinearLayout) view.findViewById(R.id.layout_drop);
        if (ConstantVar.DROPS.isEmpty()) {
            dropLayout.setVisibility(View.GONE);
            pipeAdapter.notifyDataSetChanged();
        } else {
            dropLayout.setVisibility(View.VISIBLE);
            pipeAdapter.notifyDataSetChanged();
        }

        scupperLayout = (LinearLayout) view.findViewById(R.id.layout_scupper);
        if (ConstantVar.SCUPPERS.isEmpty()) {
            scupperLayout.setVisibility(View.GONE);
            scupperAdapter.notifyDataSetChanged();
        } else {
            scupperLayout.setVisibility(View.VISIBLE);
            scupperAdapter.notifyDataSetChanged();
        }

        panLayout = (LinearLayout) view.findViewById(R.id.layout_pitch_pan);
        if (ConstantVar.PANS.isEmpty()) {
            panLayout.setVisibility(View.GONE);
            pansAdapter.notifyDataSetChanged();
        } else {
            panLayout.setVisibility(View.VISIBLE);
            pansAdapter.notifyDataSetChanged();
        }

        tubeLayout = (LinearLayout) view.findViewById(R.id.layout_tube);
        if (ConstantVar.TUBES.isEmpty()) {
            tubeLayout.setVisibility(View.GONE);
            tubesAdapter.notifyDataSetChanged();
        } else {
            tubeLayout.setVisibility(View.VISIBLE);
            tubesAdapter.notifyDataSetChanged();
        }

        curbLayout = (LinearLayout) view.findViewById(R.id.layout_curb);
        if (ConstantVar.CURBS.isEmpty()) {
            curbLayout.setVisibility(View.GONE);
            curbsAdapter.notifyDataSetChanged();
        } else {
            curbLayout.setVisibility(View.VISIBLE);
            curbsAdapter.notifyDataSetChanged();
        }

        sleeperLayout = (LinearLayout) view.findViewById(R.id.layout_sleeper);
        if (ConstantVar.SLEEPERS.isEmpty()) {
            sleeperLayout.setVisibility(View.GONE);
            sleepersAdapter.notifyDataSetChanged();
        } else {
            sleeperLayout.setVisibility(View.VISIBLE);
            sleepersAdapter.notifyDataSetChanged();
        }

        stockListView.setAdapter(stockAdapter);
        coneListView.setAdapter(coneAdapter);
        cornerListView.setAdapter(cornerAdapter);
        pipeListView.setAdapter(pipeAdapter);
        dropListView.setAdapter(dropAdapter);
        scupperListView.setAdapter(scupperAdapter);
        panListView.setAdapter(pansAdapter);
        tubeListView.setAdapter(tubesAdapter);
        curbListView.setAdapter(curbsAdapter);
        sleeperListView.setAdapter(sleepersAdapter);


        /*
        * STOCK
         */
        final SwipeToDismissTouchListener stockTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(stockListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                stockAdapter.remove(position);
                            }
                        });

        stockTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        stockListView.setOnTouchListener(stockTouchListener);

        stockListView.setOnScrollListener((AbsListView.OnScrollListener) stockTouchListener.makeScrollListener());
        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (stockTouchListener.existPendingDismisses()) {
                    stockTouchListener.undoPendingDismiss();
                } else {
                    // Intent
                    ConstantVar.DATASET.get(position);
                    stockAdapter.notifyDataSetChanged();
                }
            }
        });

        stockListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(50);

                return true;
            }
        });

        /*
         * STOCK - END
         */


        /*
         * CONE
         */
        final SwipeToDismissTouchListener coneTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(coneListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                coneAdapter.remove(position);
                            }
                        });

        coneTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        coneListView.setOnTouchListener(coneTouchListener);

        coneListView.setOnScrollListener((AbsListView.OnScrollListener) coneTouchListener.makeScrollListener());
        coneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (coneTouchListener.existPendingDismisses()) {
                    coneTouchListener.undoPendingDismiss();
                } else {
                    try {
                        ArrayAdapter<CharSequence> coneArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_cone = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_cone = View.inflate(view.getRootView().getContext(), R.layout.dialog_cone_custom, null);
                        builder_cone.setTitle("Custom Cone");
                        builder_cone.setView(layout_cone);

                        final RadioGroup cone_radioGroup = (RadioGroup) layout_cone.findViewById(R.id.cone_btn_radio_group);
                        final RadioButton cone_radioSplit = (RadioButton) layout_cone.findViewById(R.id.cone_btn_radio_split);
                        final RadioButton cone_radioNonSplit = (RadioButton) layout_cone.findViewById(R.id.cone_btn_radio_nonsplit);
                        final EditText cone_quantity = (EditText) layout_cone.findViewById(R.id.cone_text_quantity);
                        final EditText cone_height = (EditText) layout_cone.findViewById(R.id.cone_text_height);
                        final Spinner cone_heightFrac = (Spinner) layout_cone.findViewById(R.id.menu_cone_height_spinner);
                        final EditText cone_top = (EditText) layout_cone.findViewById(R.id.cone_text_top);
                        final Spinner cone_topFrac = (Spinner) layout_cone.findViewById(R.id.menu_cone_top_spinner);
                        final EditText cone_bot = (EditText) layout_cone.findViewById(R.id.cone_text_bottom);
                        final Spinner cone_botFrac = (Spinner) layout_cone.findViewById(R.id.menu_cone_bottom_spinner);
                        final EditText cone_flange = (EditText) layout_cone.findViewById(R.id.cone_text_flange);
                        final Spinner cone_flangeFrac = (Spinner) layout_cone.findViewById(R.id.menu_cone_flange_spinner);
                        final EditText cone_color = (EditText) layout_cone.findViewById(R.id.cone_text_color);
                        final EditText cone_material = (EditText) layout_cone.findViewById(R.id.cone_text_material);

                        cone_heightFrac.setAdapter(coneArrayAdp);
                        cone_topFrac.setAdapter(coneArrayAdp);
                        cone_botFrac.setAdapter(coneArrayAdp);
                        cone_flangeFrac.setAdapter(coneArrayAdp);

                        if (ConstantVar.CONES.get(position).type.equals("Split")) {
                            cone_radioGroup.check(cone_radioSplit.getId());
                        } else if (ConstantVar.CONES.get(position).type.equals("Non-Split")) {
                            cone_radioGroup.check(cone_radioNonSplit.getId());
                        }

                        cone_quantity.setText(Integer.toString(ConstantVar.CONES.get(position).quantity));
                        cone_height.setText(Integer.toString(ConstantVar.CONES.get(position).height));
                        cone_heightFrac.setSelection(coneArrayAdp.getPosition(ConstantVar.CONES.get(position).heightFrac));
                        cone_top.setText(Integer.toString(ConstantVar.CONES.get(position).top));
                        cone_topFrac.setSelection(coneArrayAdp.getPosition(ConstantVar.CONES.get(position).topFrac));
                        cone_bot.setText(Integer.toString(ConstantVar.CONES.get(position).top));
                        cone_botFrac.setSelection(coneArrayAdp.getPosition(ConstantVar.CONES.get(position).botFrac));
                        cone_flange.setText(Integer.toString(ConstantVar.CONES.get(position).flange));
                        cone_flangeFrac.setSelection(coneArrayAdp.getPosition(ConstantVar.CONES.get(position).flangeFrac));
                        cone_color.setText(ConstantVar.CONES.get(position).color);
                        cone_material.setText(ConstantVar.CONES.get(position).material);

                        builder_cone.setPositiveButton("Replace", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.CONES.remove(position);

                                String type = "Split";
                                if (cone_radioGroup.getCheckedRadioButtonId() == cone_radioSplit.getId()) {
                                    type = "Split";
                                } else if (cone_radioGroup.getCheckedRadioButtonId() == cone_radioNonSplit.getId()) {
                                    type = "Non-Split";
                                }

                                Cone cone = new Cone();
                                cone.type = type;
                                cone.quantity = Integer.parseInt(cone_quantity.getText().toString());
                                cone.height = Integer.parseInt(cone_height.getText().toString());
                                cone.heightFrac = cone_heightFrac.getSelectedItem().toString();
                                cone.top = Integer.parseInt(cone_top.getText().toString());
                                cone.topFrac = cone_topFrac.getSelectedItem().toString();
                                cone.bot = Integer.parseInt(cone_bot.getText().toString());
                                cone.botFrac = cone_botFrac.getSelectedItem().toString();
                                cone.flange = Integer.parseInt(cone_flange.getText().toString());
                                cone.flangeFrac = cone_flangeFrac.getSelectedItem().toString();
                                cone.color = cone_color.getText().toString();
                                cone.material = cone_material.getText().toString();

                                ConstantVar.CONES.add(cone);
                                coneAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Cone!", Toast.LENGTH_SHORT).show();

                            }
                        });

                        builder_cone.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder_cone.show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        coneListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.CONES.get(position).type + " Cone", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        /*
         * CONE - END
         */


        /*
         * CORNER
         */
        final SwipeToDismissTouchListener cornerTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(cornerListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                cornerAdapter.remove(position);
                            }
                        });

        cornerTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        cornerListView.setOnTouchListener(cornerTouchListener);

        cornerListView.setOnScrollListener((AbsListView.OnScrollListener) cornerTouchListener.makeScrollListener());
        cornerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (cornerTouchListener.existPendingDismisses()) {
                    cornerTouchListener.undoPendingDismiss();
                } else {
                    try {
                        ArrayAdapter<CharSequence> cornerArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_corner = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_corner = View.inflate(view.getRootView().getContext(), R.layout.dialog_corner_custom, null);
                        builder_corner.setTitle("Custom Corner");
                        builder_corner.setView(layout_corner);

                        final RadioGroup corner_radioGroup = (RadioGroup) layout_corner.findViewById(R.id.corner_btn_radio_group);
                        final RadioButton corner_radioInside = (RadioButton) layout_corner.findViewById(R.id.corner_btn_radio_inside);
                        final RadioButton corner_radioOutside = (RadioButton) layout_corner.findViewById(R.id.corner_btn_radio_outside);
                        final EditText corner_quantity = (EditText) layout_corner.findViewById(R.id.corner_text_quantity);
                        final EditText corner_height = (EditText) layout_corner.findViewById(R.id.corner_text_height);
                        final Spinner corner_heightFrac = (Spinner) layout_corner.findViewById(R.id.corner_menu_height_spinner);
                        final EditText corner_depth = (EditText) layout_corner.findViewById(R.id.corner_text_depth);
                        final Spinner corner_depthFrac = (Spinner) layout_corner.findViewById(R.id.corner_menu_depth_spinner);
                        final EditText corner_flange = (EditText) layout_corner.findViewById(R.id.corner_text_flange);
                        final Spinner corner_flangeFrac = (Spinner) layout_corner.findViewById(R.id.corner_menu_flange_spinner);
                        final EditText corner_color = (EditText) layout_corner.findViewById(R.id.corner_text_color);
                        final EditText corner_material = (EditText) layout_corner.findViewById(R.id.corner_text_material);

                        corner_heightFrac.setAdapter(cornerArrayAdp);
                        corner_depthFrac.setAdapter(cornerArrayAdp);
                        corner_flangeFrac.setAdapter(cornerArrayAdp);

                        if (ConstantVar.CORNERS.get(position).type.equals("Inside")) {
                            corner_radioGroup.check(corner_radioInside.getId());
                        } else if (ConstantVar.CORNERS.get(position).type.equals("Outside")) {
                            corner_radioGroup.check(corner_radioOutside.getId());
                        }

                        corner_quantity.setText(Integer.toString(ConstantVar.CORNERS.get(position).quantity));
                        corner_height.setText(Integer.toString(ConstantVar.CORNERS.get(position).height));
                        corner_heightFrac.setSelection(cornerArrayAdp.getPosition(ConstantVar.CORNERS.get(position).heightFrac));
                        corner_depth.setText(Integer.toString(ConstantVar.CORNERS.get(position).depth));
                        corner_depthFrac.setSelection(cornerArrayAdp.getPosition(ConstantVar.CORNERS.get(position).depthFrac));
                        corner_flange.setText(Integer.toString(ConstantVar.CORNERS.get(position).flange));
                        corner_flangeFrac.setSelection(cornerArrayAdp.getPosition(ConstantVar.CORNERS.get(position).flangeFrac));
                        corner_color.setText(ConstantVar.CORNERS.get(position).color);
                        corner_material.setText(ConstantVar.CORNERS.get(position).material);

                        builder_corner.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.CORNERS.remove(position);

                                String type = "Inside";
                                if (corner_radioGroup.getCheckedRadioButtonId() == corner_radioInside.getId()) {
                                    type = "Inside";
                                } else if (corner_radioGroup.getCheckedRadioButtonId() == corner_radioOutside.getId()) {
                                    type = "Outside";
                                }

                                Corner corner = new Corner();
                                corner.type = type;
                                corner.quantity = Integer.parseInt(corner_quantity.getText().toString());
                                corner.height = Integer.parseInt(corner_height.getText().toString());
                                corner.heightFrac = corner_heightFrac.getSelectedItem().toString();
                                corner.depth = Integer.parseInt(corner_depth.getText().toString());
                                corner.depthFrac = corner_depthFrac.getSelectedItem().toString();
                                corner.flange = Integer.parseInt(corner_flange.getText().toString());
                                corner.flangeFrac = corner_flangeFrac.getSelectedItem().toString();
                                corner.color = corner_color.getText().toString();
                                corner.material = corner_material.getText().toString();

                                ConstantVar.CORNERS.add(corner);
                                cornerAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Cone(s)!", Toast.LENGTH_SHORT).show();

                            }
                        });

                        builder_corner.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            }
                        });

                        builder_corner.show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        cornerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.CORNERS.get(position).type + " Corner",  Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        /*
         * CORNER - END
         */


        /*
         * PIPE WRAP
         */

        final SwipeToDismissTouchListener pipeTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(pipeListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                pipeAdapter.remove(position);
                            }
                        });

        pipeTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        pipeListView.setOnTouchListener(pipeTouchListener);

        pipeListView.setOnScrollListener((AbsListView.OnScrollListener) pipeTouchListener.makeScrollListener());
        pipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (pipeTouchListener.existPendingDismisses()) {
                    pipeTouchListener.undoPendingDismiss();
                } else {
                    try {
                        ArrayAdapter<CharSequence> pipeArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_pipe = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_pipe = View.inflate(view.getRootView().getContext(), R.layout.dialog_pipe_custom, null);
                        builder_pipe.setTitle("Custom Pipe Wrap");
                        builder_pipe.setView(layout_pipe);

                        final RadioGroup pipe_radioGroup = (RadioGroup) layout_pipe.findViewById(R.id.pipe_btn_radio_group);
                        final RadioButton pipe_radioSplit = (RadioButton) layout_pipe.findViewById(R.id.pipe_btn_radio_split);
                        final RadioButton pipe_radioNonSplit = (RadioButton) layout_pipe.findViewById(R.id.pipe_btn_radio_nonsplit);
                        final EditText pipe_quantity = (EditText) layout_pipe.findViewById(R.id.pipe_text_quantity);
                        final EditText pipe_height = (EditText) layout_pipe.findViewById(R.id.pipe_text_height);
                        final Spinner pipe_heightFrac = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_height_spinner);
                        final EditText pipe_diameter = (EditText) layout_pipe.findViewById(R.id.pipe_text_diameter);
                        final Spinner pipe_diameterFrac = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_diameter_spinner);
                        final EditText pipe_flange = (EditText) layout_pipe.findViewById(R.id.pipe_text_flange);
                        final Spinner pipe_flangeFrac = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_flange_spinner);
                        final EditText pipe_color = (EditText) layout_pipe.findViewById(R.id.pipe_text_color);
                        final EditText pipe_material = (EditText) layout_pipe.findViewById(R.id.pipe_text_material);

                        pipe_heightFrac.setAdapter(pipeArrayAdp);
                        pipe_diameterFrac.setAdapter(pipeArrayAdp);
                        pipe_flangeFrac.setAdapter(pipeArrayAdp);

                        if (ConstantVar.PIPES.get(position).type.equals("Split")) {
                            pipe_radioGroup.check(pipe_radioSplit.getId());
                        } else if (ConstantVar.PIPES.get(position).type.equals("Non-Split")) {
                            pipe_radioGroup.check(pipe_radioNonSplit.getId());
                        }

                        pipe_quantity.setText(Integer.toString(ConstantVar.PIPES.get(position).quantity));
                        pipe_height.setText(Integer.toString(ConstantVar.PIPES.get(position).height));
                        pipe_heightFrac.setSelection(pipeArrayAdp.getPosition(ConstantVar.DROPS.get(position).depthFrac));
                        pipe_diameter.setText(Integer.toString(ConstantVar.PIPES.get(position).diameter));
                        pipe_diameterFrac.setSelection(pipeArrayAdp.getPosition(ConstantVar.DROPS.get(position).diameterFrac));
                        pipe_flange.setText(Integer.toString(ConstantVar.PIPES.get(position).flange));
                        pipe_flangeFrac.setSelection(pipeArrayAdp.getPosition(ConstantVar.PIPES.get(position).flangeFrac));
                        pipe_color.setText(ConstantVar.PIPES.get(position).color);
                        pipe_material.setText(ConstantVar.PIPES.get(position).material);

                        builder_pipe.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.PIPES.remove(position);

                                String type = "Split";
                                if (pipe_radioGroup.getCheckedRadioButtonId() == pipe_radioSplit.getId()) {
                                    type = "Split";
                                } else if (pipe_radioGroup.getCheckedRadioButtonId() == pipe_radioNonSplit.getId()) {
                                    type = "Non-Split";
                                }

                                Pipe pipe = new Pipe();
                                pipe.type = type;
                                pipe.quantity = Integer.parseInt(pipe_quantity.getText().toString());
                                pipe.height = Integer.parseInt(pipe_height.getText().toString());
                                pipe.heightFrac = pipe_heightFrac.getSelectedItem().toString();
                                pipe.diameter = Integer.parseInt(pipe_diameter.getText().toString());
                                pipe.diameterFrac = pipe_diameterFrac.getSelectedItem().toString();
                                pipe.flange = Integer.parseInt(pipe_flange.getText().toString());
                                pipe.flangeFrac = pipe_flangeFrac.getSelectedItem().toString();
                                pipe.color = pipe_color.getText().toString();
                                pipe.material = pipe_material.getText().toString();

                                ConstantVar.PIPES.add(pipe);
                                pipeAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Pipe Wrap(s)!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder_pipe.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder_pipe.show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        pipeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.PIPES.get(position).diameter + " " + ConstantVar.PIPES.get(position).diameterFrac + " Dia. Pipe Wrap(s)",  Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        /*
         * PIPE WRAP - END
         */

        /*
         * DROP SCUPPER
         */
        final SwipeToDismissTouchListener dropTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(dropListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                dropAdapter.remove(position);
                            }
                        });

        dropTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        dropListView.setOnTouchListener(dropTouchListener);

        dropListView.setOnScrollListener((AbsListView.OnScrollListener) dropTouchListener.makeScrollListener());
        dropListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (dropTouchListener.existPendingDismisses()) {
                    dropTouchListener.undoPendingDismiss();
                } else {
                    try {
                        ArrayAdapter<CharSequence> dropArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_drop = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_drop = View.inflate(view.getRootView().getContext(), R.layout.dialog_drop_custom, null);
                        builder_drop.setTitle("Custom Drop Scupper");
                        builder_drop.setView(layout_drop);

                        final EditText drop_quantity = (EditText) layout_drop.findViewById(R.id.drop_text_quantity);
                        final EditText drop_depth = (EditText) layout_drop.findViewById(R.id.drop_text_depth);
                        final Spinner drop_depthFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_depth_spinner);
                        final EditText drop_diameter = (EditText) layout_drop.findViewById(R.id.drop_text_diameter);
                        final Spinner drop_diameterFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_diameter_spinner);
                        final EditText drop_flange = (EditText) layout_drop.findViewById(R.id.drop_text_flange);
                        final Spinner drop_flangeFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_flange_spinner);
                        final EditText drop_color = (EditText) layout_drop.findViewById(R.id.drop_text_color);
                        final EditText drop_material = (EditText) layout_drop.findViewById(R.id.drop_text_material);

                        drop_depthFrac.setAdapter(dropArrayAdp);
                        drop_diameterFrac.setAdapter(dropArrayAdp);
                        drop_flangeFrac.setAdapter(dropArrayAdp);

                        drop_quantity.setText(Integer.toString(ConstantVar.DROPS.get(position).quantity));
                        drop_depth.setText(Integer.toString(ConstantVar.DROPS.get(position).depth));
                        drop_depthFrac.setSelection(dropArrayAdp.getPosition(ConstantVar.DROPS.get(position).depthFrac));
                        drop_diameter.setText(Integer.toString(ConstantVar.DROPS.get(position).diameter));
                        drop_diameterFrac.setSelection(dropArrayAdp.getPosition(ConstantVar.DROPS.get(position).diameterFrac));
                        drop_flange.setText(Integer.toString(ConstantVar.DROPS.get(position).flange));
                        drop_flangeFrac.setSelection(dropArrayAdp.getPosition(ConstantVar.DROPS.get(position).flangeFrac));
                        drop_color.setText(ConstantVar.DROPS.get(position).color);
                        drop_material.setText(ConstantVar.DROPS.get(position).material);

                        builder_drop.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.DROPS.remove(position);

                                Drop drop = new Drop();
                                drop.quantity = Integer.parseInt(drop_quantity.getText().toString());
                                drop.depth = Integer.parseInt(drop_depth.getText().toString());
                                drop.depthFrac = drop_depthFrac.getSelectedItem().toString();
                                drop.diameter = Integer.parseInt(drop_diameter.getText().toString());
                                drop.diameterFrac = drop_diameterFrac.getSelectedItem().toString();
                                drop.flange = Integer.parseInt(drop_flange.getText().toString());
                                drop.flangeFrac = drop_flangeFrac.getSelectedItem().toString();
                                drop.color = drop_color.getText().toString();
                                drop.material = drop_material.getText().toString();

                                ConstantVar.DROPS.add(drop);
                                dropAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Drop Scupper(s)!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder_drop.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder_drop.show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        dropListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.DROPS.get(position).diameter + " " + ConstantVar.DROPS.get(position).diameterFrac + " Dia. Drop Scupper(s)",  Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        /*
         * DROP - END
         */


        /*
         * SCUPPER
         */
        final SwipeToDismissTouchListener scupperTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(scupperListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                scupperAdapter.remove(position);
                            }
                        });

        scupperTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        scupperListView.setOnTouchListener(scupperTouchListener);

        scupperListView.setOnScrollListener((AbsListView.OnScrollListener) scupperTouchListener.makeScrollListener());
        scupperListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (scupperTouchListener.existPendingDismisses()) {
                    scupperTouchListener.undoPendingDismiss();
                } else {

                    try {
                        ArrayAdapter<CharSequence> scupperArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_scupper = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_scupper = View.inflate(view.getRootView().getContext(), R.layout.dialog_scupper_custom, null);
                        builder_scupper.setTitle("Custom Scupper");
                        builder_scupper.setView(layout_scupper);

                        final RadioGroup scupper_radioGroup = (RadioGroup) layout_scupper.findViewById(R.id.scupper_btn_radio_group);
                        final RadioButton scupper_radioTW = (RadioButton) layout_scupper.findViewById(R.id.scupper_btn_radio_tw);
                        final RadioButton scupper_radioOvrf = (RadioButton) layout_scupper.findViewById(R.id.scupper_btn_radio_ovrf);
                        final EditText scupper_quantity = (EditText) layout_scupper.findViewById(R.id.scupper_text_quantity);
                        final EditText scupper_depth = (EditText) layout_scupper.findViewById(R.id.scupper_text_depth);
                        final Spinner scupper_depthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_depth_spinner);
                        final EditText scupper_width = (EditText) layout_scupper.findViewById(R.id.scupper_text_width);
                        final Spinner scupper_widthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_depth_spinner);
                        final EditText scupper_length = (EditText) layout_scupper.findViewById(R.id.scupper_text_length);
                        final Spinner scupper_lengthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_length_spinner);
                        final EditText scupper_flange = (EditText) layout_scupper.findViewById(R.id.scupper_text_flange);
                        final Spinner scupper_flangeFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_flange_spinner);
                        final EditText scupper_color = (EditText) layout_scupper.findViewById(R.id.scupper_text_color);
                        final EditText scupper_material = (EditText) layout_scupper.findViewById(R.id.scupper_text_material);

                        scupper_depthFrac.setAdapter(scupperArrayAdp);
                        scupper_widthFrac.setAdapter(scupperArrayAdp);
                        scupper_lengthFrac.setAdapter(scupperArrayAdp);
                        scupper_flangeFrac.setAdapter(scupperArrayAdp);

                        if (ConstantVar.SCUPPERS.get(position).type.equals("Thru-Wall")) {
                            scupper_radioGroup.check(scupper_radioTW.getId());
                        } else if (ConstantVar.SCUPPERS.get(position).type.equals("Overflow")) {
                            scupper_radioGroup.check(scupper_radioOvrf.getId());
                        }

                        scupper_quantity.setText(Integer.toString(ConstantVar.SCUPPERS.get(position).quantity));
                        scupper_depth.setText(Integer.toString(ConstantVar.SCUPPERS.get(position).depth));
                        scupper_depthFrac.setSelection(scupperArrayAdp.getPosition(ConstantVar.SCUPPERS.get(position).depthFrac));
                        scupper_width.setText(Integer.toString(ConstantVar.SCUPPERS.get(position).width));
                        scupper_widthFrac.setSelection(scupperArrayAdp.getPosition(ConstantVar.SCUPPERS.get(position).widthFrac));
                        scupper_length.setText(Integer.toString(ConstantVar.SCUPPERS.get(position).length));
                        scupper_lengthFrac.setSelection(scupperArrayAdp.getPosition(ConstantVar.SCUPPERS.get(position).lengthFrac));
                        scupper_flange.setText(Integer.toString(ConstantVar.SCUPPERS.get(position).flange));
                        scupper_flangeFrac.setSelection(scupperArrayAdp.getPosition(ConstantVar.SCUPPERS.get(position).flangeFrac));
                        scupper_color.setText(ConstantVar.SCUPPERS.get(position).color);
                        scupper_material.setText(ConstantVar.SCUPPERS.get(position).material);

                        builder_scupper.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String type = "Thru-Wall";
                                if (scupper_radioGroup.getCheckedRadioButtonId() == scupper_radioTW.getId()) {
                                    type = "Thru-Wall";
                                } else if (scupper_radioGroup.getCheckedRadioButtonId() == scupper_radioOvrf.getId()) {
                                    type = "Overflow";
                                }

                                Scupper scupper = new Scupper();
                                scupper.type = type;
                                scupper.quantity = Integer.parseInt(scupper_quantity.getText().toString());
                                scupper.depth = Integer.parseInt(scupper_depth.getText().toString());
                                scupper.depthFrac = scupper_depthFrac.getSelectedItem().toString();
                                scupper.width = Integer.parseInt(scupper_width.getText().toString());
                                scupper.widthFrac = scupper_widthFrac.getSelectedItem().toString();
                                scupper.length = Integer.parseInt(scupper_length.getText().toString());
                                scupper.lengthFrac = scupper_lengthFrac.getSelectedItem().toString();
                                scupper.flange = Integer.parseInt(scupper_flange.getText().toString());
                                scupper.flangeFrac = scupper_flangeFrac.getSelectedItem().toString();
                                scupper.color = scupper_color.getText().toString();
                                scupper.material = scupper_material.getText().toString();

                                ConstantVar.SCUPPERS.add(scupper);
                                scupperAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Scupper(s)!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder_scupper.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder_scupper.show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        scupperListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.SCUPPERS.get(position).type + " Scupper(s)",  Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        /*
         * SCUPPER - END
         */


        /*
         * PITCH PAN
         */
        final SwipeToDismissTouchListener panTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(panListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                pansAdapter.remove(position);
                            }
                        });

        panTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        panListView.setOnTouchListener(panTouchListener);

        panListView.setOnScrollListener((AbsListView.OnScrollListener) panTouchListener.makeScrollListener());
        panListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (panTouchListener.existPendingDismisses()) {
                    panTouchListener.undoPendingDismiss();
                } else {
                    try {
                        final ArrayAdapter<CharSequence> panArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_pan = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_pan = View.inflate(view.getRootView().getContext(), R.layout.dialog_pitch_pans_custom, null);
                        builder_pan.setTitle("Custom Scupper");
                        builder_pan.setView(layout_pan);

                        final String[] pan_array_dimension = {"Round", "Square"};

                        final RelativeLayout pan_dimension_width = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_width_hide);
                        final RelativeLayout pan_dimension_length = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_length_hide);
                        final RelativeLayout pan_dimension_diameter = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_diameter_hide);
                        final RadioGroup pan_radioGroupType = (RadioGroup) layout_pan.findViewById(R.id.pitchpans_btn_radio_group_type);
                        final RadioButton pan_radioGroupSplit = (RadioButton) layout_pan.findViewById(R.id.pitchpans_btn_radio_split);
                        final RadioButton pan_radioGroupNonSplit = (RadioButton) layout_pan.findViewById(R.id.pitchpans_btn_radio_nonsplit);

                        final EditText pan_quantity = (EditText) layout_pan.findViewById(R.id.pitchpans_text_quantity);
                        final EditText pan_height = (EditText) layout_pan.findViewById(R.id.pitchpans_text_height);
                        final Spinner pan_heightFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_height_spinner);
                        final EditText pan_diameter = (EditText) layout_pan.findViewById(R.id.pitchpans_text_diameter);
                        final Spinner pan_diameterFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_diameter_spinner);
                        final EditText pan_length = (EditText) layout_pan.findViewById(R.id.pitchpans_text_length);
                        final Spinner pan_lengthFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_length_spinner);
                        final EditText pan_width = (EditText) layout_pan.findViewById(R.id.pitchpans_text_width);
                        final Spinner pan_widthFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_width_spinner);
                        final EditText pan_flange = (EditText) layout_pan.findViewById(R.id.pitchpans_text_flange);
                        final Spinner pan_flangeFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_flange_spinner);
                        final EditText pan_color = (EditText) layout_pan.findViewById(R.id.pitchpans_text_color);
                        final EditText pan_material = (EditText) layout_pan.findViewById(R.id.pitchpans_text_material);

                        pan_heightFrac.setAdapter(panArrayAdp);
                        pan_diameterFrac.setAdapter(panArrayAdp);
                        pan_lengthFrac.setAdapter(panArrayAdp);
                        pan_widthFrac.setAdapter(panArrayAdp);
                        pan_flangeFrac.setAdapter(panArrayAdp);

                        if (ConstantVar.PIPES.get(position).type.equals("Split")) {
                            pan_radioGroupType.check(pan_radioGroupSplit.getId());

                        } else if (ConstantVar.PIPES.get(position).type.equals("Non-Split")) {
                            pan_radioGroupType.check(pan_radioGroupNonSplit.getId());
                        }


                        int dimension_index = 0;
                        if (ConstantVar.PANS.get(position).dimension.equals("Round")) {
                            dimension_index = 0;

                            pan_quantity.setText(Integer.toString(ConstantVar.PANS.get(position).quantity));
                            pan_height.setText(Integer.toString(ConstantVar.PANS.get(position).height));
                            pan_heightFrac.setSelection(panArrayAdp.getPosition(ConstantVar.PANS.get(position).heightFrac));
                            pan_diameter.setText(Integer.toString(ConstantVar.PANS.get(position).diameter));
                            pan_diameterFrac.setSelection(panArrayAdp.getPosition(ConstantVar.PANS.get(position).diameterFrac));
                            pan_flange.setText(Integer.toString(ConstantVar.PANS.get(position).flange));
                            pan_flangeFrac.setSelection(panArrayAdp.getPosition(ConstantVar.PANS.get(position).flangeFrac));
                            pan_color.setText(ConstantVar.PANS.get(position).color);
                            pan_material.setText(ConstantVar.PANS.get(position).material);

                        } else if (ConstantVar.PANS.get(position).dimension.equals("Square")) {
                            dimension_index = 1;
                            pan_quantity.setText(Integer.toString(ConstantVar.PANS.get(position).quantity));
                            pan_height.setText(Integer.toString(ConstantVar.PANS.get(position).height));
                            pan_heightFrac.setSelection(panArrayAdp.getPosition(ConstantVar.PANS.get(position).heightFrac));
                            pan_length.setText(Integer.toString(ConstantVar.PANS.get(position).length));
                            pan_lengthFrac.setSelection(panArrayAdp.getPosition(ConstantVar.PANS.get(position).lengthFrac));
                            pan_width.setText(Integer.toString(ConstantVar.PANS.get(position).width));
                            pan_widthFrac.setSelection(panArrayAdp.getPosition(ConstantVar.PANS.get(position).widthFrac));
                            pan_flange.setText(Integer.toString(ConstantVar.PANS.get(position).flange));
                            pan_flangeFrac.setSelection(panArrayAdp.getPosition(ConstantVar.PANS.get(position).flangeFrac));
                            pan_color.setText(ConstantVar.PANS.get(position).color);
                            pan_material.setText(ConstantVar.PANS.get(position).material);
                        }

                        builder_pan.setSingleChoiceItems(pan_array_dimension, dimension_index, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    pan_dimension_length.setVisibility(View.GONE);
                                    pan_dimension_width.setVisibility(View.GONE);
                                    pan_dimension_diameter.setVisibility(View.VISIBLE);
                                } else if (i == 1) {
                                    pan_dimension_length.setVisibility(View.VISIBLE);
                                    pan_dimension_width.setVisibility(View.VISIBLE);
                                    pan_dimension_diameter.setVisibility(View.GONE);
                                }
                            }
                        });

                        builder_pan.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.PANS.remove(position);

                                String type = ConstantVar.PANS.get(position).type;
                                if (pan_radioGroupType.getCheckedRadioButtonId() == pan_radioGroupSplit.getId()) {
                                    type = "Split";
                                } else if (pan_radioGroupType.getCheckedRadioButtonId() == pan_radioGroupNonSplit.getId()) {
                                    type = "Non-Split";
                                }
                                if (pan_dimension_diameter.getVisibility() == View.VISIBLE) {


                                    Pan pan = new Pan();
                                    pan.dimension = "Round";
                                    pan.type = type;
                                    pan.quantity = Integer.parseInt(pan_quantity.getText().toString());
                                    pan.height = Integer.parseInt(pan_height.getText().toString());
                                    pan.heightFrac = pan_heightFrac.getSelectedItem().toString();
                                    pan.diameter = Integer.parseInt(pan_diameter.getText().toString());
                                    pan.diameterFrac = pan_diameterFrac.getSelectedItem().toString();
                                    pan.flange = Integer.parseInt(pan_flange.getText().toString());
                                    pan.flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                    pan.color = pan_color.getText().toString();
                                    pan.material = pan_material.getText().toString();

                                    ConstantVar.PANS.add(pan);
                                    pansAdapter.notifyDataSetChanged();
                                    Toast.makeText(getContext(), "Updated Pitch Pan!", Toast.LENGTH_SHORT).show();

                                } else if (pan_dimension_diameter.getVisibility() == View.GONE) {
                                    Pan pan = new Pan();
                                    pan.dimension = "Square";
                                    pan.type = type;
                                    pan.quantity = Integer.parseInt(pan_quantity.getText().toString());
                                    pan.height = Integer.parseInt(pan_height.getText().toString());
                                    pan.heightFrac = pan_heightFrac.getSelectedItem().toString();
                                    pan.length = Integer.parseInt(pan_length.getText().toString());
                                    pan.lengthFrac = pan_lengthFrac.getSelectedItem().toString();
                                    pan.width = Integer.parseInt(pan_width.getText().toString());
                                    pan.widthFrac = pan_widthFrac.getSelectedItem().toString();
                                    pan.flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                    pan.color = pan_color.getText().toString();
                                    pan.material = pan_material.getText().toString();

                                    ConstantVar.PANS.add(pan);
                                    pansAdapter.notifyDataSetChanged();
                                    Toast.makeText(getContext(), "Updated Pitch Pan!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder_pan.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();;
                            }
                        });

                        builder_pan.show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        panListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.PANS.get(position).type + " Pitch Pan(s)",  Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        /*
         * PITCH PAN - END
         */


        /*
         * TUBE WRAP
         */
        final SwipeToDismissTouchListener tubeTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(tubeListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                tubesAdapter.remove(position);
                            }
                        });

        tubeTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        tubeListView.setOnTouchListener(tubeTouchListener);

        tubeListView.setOnScrollListener((AbsListView.OnScrollListener) tubeTouchListener.makeScrollListener());
        tubeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (tubeTouchListener.existPendingDismisses()) {
                    tubeTouchListener.undoPendingDismiss();
                } else {
                    try {
                        ArrayAdapter<CharSequence> tubeArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_tube = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_tube = View.inflate(view.getRootView().getContext(), R.layout.dialog_tube_custom, null);
                        builder_tube.setTitle("Custom Tube Wraps");
                        builder_tube.setView(layout_tube);

                        final RadioGroup tube_radioType = (RadioGroup) layout_tube.findViewById(R.id.tube_btn_radio_group);
                        final RadioButton tube_radioSplit = (RadioButton) layout_tube.findViewById(R.id.tube_btn_radio_split);
                        final RadioButton tube_radioNonSplit = (RadioButton) layout_tube.findViewById(R.id.tube_btn_radio_nonsplit);
                        final EditText tube_quantity = (EditText) layout_tube.findViewById(R.id.tube_text_quantity);
                        final EditText tube_height = (EditText) layout_tube.findViewById(R.id.tube_text_height);
                        final Spinner tube_heightFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_height_spinner);
                        final EditText tube_length = (EditText) layout_tube.findViewById(R.id.tube_text_length);
                        final Spinner tube_lengthFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_length_spinner);
                        final EditText tube_width = (EditText) layout_tube.findViewById(R.id.tube_text_width);
                        final Spinner tube_widthFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_width_spinner);
                        final EditText tube_flange = (EditText) layout_tube.findViewById(R.id.tube_text_flange);
                        final Spinner tube_flangeFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_flange_spinner);
                        final EditText tube_color = (EditText) layout_tube.findViewById(R.id.tube_text_color);
                        final EditText tube_material = (EditText) layout_tube.findViewById(R.id.tube_text_material);

                        tube_heightFrac.setAdapter(tubeArrayAdp);
                        tube_lengthFrac.setAdapter(tubeArrayAdp);
                        tube_widthFrac.setAdapter(tubeArrayAdp);
                        tube_flangeFrac.setAdapter(tubeArrayAdp);

                        if (ConstantVar.TUBES.get(position).type.equals("Split")) {
                            tube_radioType.check(tube_radioSplit.getId());
                        } else if (ConstantVar.TUBES.get(position).type.equals("Non-Split")) {
                            tube_radioType.check(tube_radioNonSplit.getId());
                        }

                        tube_quantity.setText(Integer.toString(ConstantVar.TUBES.get(position).quantity));
                        tube_height.setText(Integer.toString(ConstantVar.TUBES.get(position).height));
                        tube_heightFrac.setSelection(tubeArrayAdp.getPosition(ConstantVar.TUBES.get(position).heightFrac));
                        tube_length.setText(Integer.toString(ConstantVar.TUBES.get(position).length));
                        tube_lengthFrac.setSelection(tubeArrayAdp.getPosition(ConstantVar.TUBES.get(position).lengthFrac));
                        tube_width.setText(Integer.toString(ConstantVar.TUBES.get(position).width));
                        tube_widthFrac.setSelection(tubeArrayAdp.getPosition(ConstantVar.TUBES.get(position).widthFrac));
                        tube_flange.setText(Integer.toString(ConstantVar.TUBES.get(position).flange));
                        tube_flangeFrac.setSelection(tubeArrayAdp.getPosition(ConstantVar.TUBES.get(position).flangeFrac));
                        tube_color.setText(ConstantVar.TUBES.get(position).color);
                        tube_material.setText(ConstantVar.TUBES.get(position).material);

                        builder_tube.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.TUBES.remove(position);

                                String type = "Split";
                                if (tube_radioType.getCheckedRadioButtonId() == tube_radioSplit.getId()) {
                                    type = "Split";
                                } else if (tube_radioType.getCheckedRadioButtonId() == tube_radioNonSplit.getId()) {
                                    type = "Non-Split";
                                }

                                Tube tube = new Tube();
                                tube.type = type;
                                tube.quantity = Integer.parseInt(tube_quantity.getText().toString());
                                tube.height = Integer.parseInt(tube_height.getText().toString());
                                tube.heightFrac = tube_heightFrac.getSelectedItem().toString();
                                tube.width = Integer.parseInt(tube_width.getText().toString());
                                tube.widthFrac = tube_widthFrac.getSelectedItem().toString();
                                tube.length = Integer.parseInt(tube_length.getText().toString());
                                tube.lengthFrac = tube_lengthFrac.getSelectedItem().toString();
                                tube.flange = Integer.parseInt(tube_flange.getText().toString());
                                tube.flangeFrac = tube_flangeFrac.getSelectedItem().toString();
                                tube.color = tube_color.getText().toString();
                                tube.material = tube_material.getText().toString();

                                ConstantVar.TUBES.add(tube);
                                tubesAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Tube Wrap(s)", Toast.LENGTH_SHORT).show();

                            }
                        });

                        builder_tube.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder_tube.show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        tubeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.TUBES.get(position).type + " Tube Wrap(s)", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        /*
         * TUBE WRAPS - END
         */


        /*
         * CURB WRAPS
         */
        final SwipeToDismissTouchListener curbTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(curbListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                curbsAdapter.remove(position);
                            }
                        });

        curbTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        curbListView.setOnTouchListener(curbTouchListener);

        curbListView.setOnScrollListener((AbsListView.OnScrollListener) curbTouchListener.makeScrollListener());
        curbListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (curbTouchListener.existPendingDismisses()) {
                    curbTouchListener.undoPendingDismiss();
                } else {
                    try {
                        ArrayAdapter<CharSequence> curbArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_curb = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_curb = View.inflate(view.getRootView().getContext(), R.layout.dialog_curb_custom, null);
                        builder_curb.setTitle("Custom Curb Wraps");
                        builder_curb.setView(layout_curb);

                        final RadioGroup curb_radioGroup = (RadioGroup) layout_curb.findViewById(R.id.curb_radioGroup);
                        final RadioButton curb_radioSplit = (RadioButton) layout_curb.findViewById(R.id.curb_radio_split);
                        final RadioButton curb_radioNonSplit = (RadioButton) layout_curb.findViewById(R.id.curb_radio_nonsplit);
                        final EditText curb_quantity = (EditText) layout_curb.findViewById(R.id.curb_text_quantity);
                        final EditText curb_height = (EditText) layout_curb.findViewById(R.id.curb_text_height);
                        final Spinner curb_heightFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_height_spinner);
                        final EditText curb_length = (EditText) layout_curb.findViewById(R.id.curb_text_length);
                        final Spinner curb_lengthFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_length_spinner);
                        final EditText curb_width = (EditText) layout_curb.findViewById(R.id.curb_text_width);
                        final Spinner curb_widthFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_width_spinner);
                        final EditText curb_flange = (EditText) layout_curb.findViewById(R.id.curb_text_flange);
                        final Spinner curb_flangeFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_flange_spinner);
                        final EditText curb_color = (EditText) layout_curb.findViewById(R.id.curb_text_color);
                        final EditText curb_material = (EditText) layout_curb.findViewById(R.id.curb_text_material);

                        curb_heightFrac.setAdapter(curbArrayAdp);
                        curb_lengthFrac.setAdapter(curbArrayAdp);
                        curb_widthFrac.setAdapter(curbArrayAdp);
                        curb_flangeFrac.setAdapter(curbArrayAdp);


                        if (ConstantVar.CURBS.get(position).type.equals("Split")) {
                            curb_radioGroup.check(curb_radioSplit.getId());
                        } else if (ConstantVar.CURBS.get(position).type.equals("Non-Split")) {
                            curb_radioGroup.check(curb_radioNonSplit.getId());
                        }

                        curb_quantity.setText(Integer.toString(ConstantVar.CURBS.get(position).quantity));
                        curb_height.setText(Integer.toString(ConstantVar.CURBS.get(position).height));
                        curb_heightFrac.setSelection(curbArrayAdp.getPosition(ConstantVar.CURBS.get(position).heightFrac));
                        curb_length.setText(Integer.toString(ConstantVar.CURBS.get(position).length));
                        curb_lengthFrac.setSelection(curbArrayAdp.getPosition(ConstantVar.CURBS.get(position).lengthFrac));
                        curb_width.setText(Integer.toString(ConstantVar.CURBS.get(position).width));
                        curb_widthFrac.setSelection(curbArrayAdp.getPosition(ConstantVar.CURBS.get(position).widthFrac));
                        curb_flange.setText(Integer.toString(ConstantVar.CURBS.get(position).flange));
                        curb_flangeFrac.setSelection(curbArrayAdp.getPosition(ConstantVar.CURBS.get(position).flangeFrac));
                        curb_color.setText(ConstantVar.CURBS.get(position).color);
                        curb_material.setText(ConstantVar.CURBS.get(position).material);

                        builder_curb.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.CURBS.remove(position);

                                String type = "Split";
                                if (curb_radioGroup.getCheckedRadioButtonId() == curb_radioSplit.getId()) {
                                    type = "Split";
                                } else if (curb_radioGroup.getCheckedRadioButtonId() == curb_radioNonSplit.getId()){
                                    type = "Non-Split";
                                }

                                Curb curb = new Curb();
                                curb.type = type;
                                curb.quantity = Integer.parseInt(curb_quantity.getText().toString());
                                curb.height = Integer.parseInt(curb_height.getText().toString());
                                curb.heightFrac = curb_heightFrac.getSelectedItem().toString();
                                curb.length = Integer.parseInt(curb_length.getText().toString());
                                curb.lengthFrac = curb_lengthFrac.getSelectedItem().toString();
                                curb.width = Integer.parseInt(curb_width.getText().toString());
                                curb.widthFrac = curb_widthFrac.getSelectedItem().toString();
                                curb.flange = Integer.parseInt(curb_flange.getText().toString());
                                curb.flangeFrac = curb_flangeFrac.getSelectedItem().toString();
                                curb.color = curb_color.getText().toString();
                                curb.material = curb_material.getText().toString();

                                ConstantVar.CURBS.add(curb);
                                curbsAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Curb Wrap(s)", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder_curb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder_curb.show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        curbListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.CURBS.get(position).type + " Curb Wrap(s)", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        /*
         * CURB WRAPS - END
         */


        /*
         * SLEEPER BOXES
         */
        final SwipeToDismissTouchListener sleeperTouchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(sleeperListView),
                        new SwipeToDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ViewAdapter recyclerView, int position) {
                                sleepersAdapter.remove(position);
                            }
                        });

        sleeperTouchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);
        sleeperListView.setOnTouchListener(sleeperTouchListener);

        sleeperListView.setOnScrollListener((AbsListView.OnScrollListener) sleeperTouchListener.makeScrollListener());
        sleeperListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (sleeperTouchListener.existPendingDismisses()) {
                    sleeperTouchListener.undoPendingDismiss();
                } else {
                    try {
                        ArrayAdapter<CharSequence> sleeperArrayAdp = ArrayAdapter.createFromResource(view.getRootView().getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        AlertDialog.Builder builder_sleeper = new AlertDialog.Builder(view.getRootView().getContext());
                        View layout_sleeper = View.inflate(view.getRootView().getContext(), R.layout.dialog_sleeper_custom, null);
                        builder_sleeper.setTitle("Custom Sleeper Boxes");
                        builder_sleeper.setView(layout_sleeper);

                        final EditText sleeper_quantity = (EditText) layout_sleeper.findViewById(R.id.sleeper_text_quantity);
                        final EditText sleeper_height = (EditText) layout_sleeper.findViewById(R.id.sleeper_text_height);
                        final Spinner sleeper_heightFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_height_spinner);
                        final EditText sleeper_width = (EditText) layout_sleeper.findViewById(R.id.sleeper_text_width);
                        final Spinner sleeper_widthFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_width_spinner);
                        final EditText sleeper_length = (EditText) layout_sleeper.findViewById(R.id.sleeper_text_length);
                        final Spinner sleeper_lengthFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_length_spinner);
                        final EditText sleeper_flange = (EditText) layout_sleeper.findViewById(R.id.sleeper_text_flange);
                        final Spinner sleeper_flangeFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_flange_spinner);
                        final EditText sleeper_color = (EditText) layout_sleeper.findViewById(R.id.sleeper_text_color);
                        final EditText sleeper_material = (EditText) layout_sleeper.findViewById(R.id.sleeper_text_material);

                        sleeper_heightFrac.setAdapter(sleeperArrayAdp);
                        sleeper_widthFrac.setAdapter(sleeperArrayAdp);
                        sleeper_lengthFrac.setAdapter(sleeperArrayAdp);
                        sleeper_flangeFrac.setAdapter(sleeperArrayAdp);

                        sleeper_quantity.setText(Integer.toString(ConstantVar.SLEEPERS.get(position).quantity));
                        sleeper_height.setText(Integer.toString(ConstantVar.SLEEPERS.get(position).height));
                        sleeper_heightFrac.setSelection(sleeperArrayAdp.getPosition(ConstantVar.SLEEPERS.get(position).heightFrac));
                        sleeper_width.setText(Integer.toString(ConstantVar.SLEEPERS.get(position).width));
                        sleeper_widthFrac.setSelection(sleeperArrayAdp.getPosition(ConstantVar.SLEEPERS.get(position).widthFrac));
                        sleeper_length.setText(Integer.toString(ConstantVar.SLEEPERS.get(position).length));
                        sleeper_lengthFrac.setSelection(sleeperArrayAdp.getPosition(ConstantVar.SLEEPERS.get(position).lengthFrac));
                        sleeper_flange.setText(Integer.toString(ConstantVar.SLEEPERS.get(position).flange));
                        sleeper_flangeFrac.setSelection(sleeperArrayAdp.getPosition(ConstantVar.SLEEPERS.get(position).flangeFrac));
                        sleeper_color.setText(ConstantVar.SLEEPERS.get(position).color);
                        sleeper_material.setText(ConstantVar.SLEEPERS.get(position).material);

                        builder_sleeper.setPositiveButton("REPLACE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ConstantVar.SLEEPERS.remove(position);

                                Sleeper sleeper = new Sleeper();
                                sleeper.quantity = Integer.parseInt(sleeper_quantity.getText().toString());
                                sleeper.height = Integer.parseInt(sleeper_height.getText().toString());
                                sleeper.heightFrac = sleeper_heightFrac.getSelectedItem().toString();
                                sleeper.length = Integer.parseInt(sleeper_length.getText().toString());
                                sleeper.lengthFrac = sleeper_lengthFrac.getSelectedItem().toString();
                                sleeper.width = Integer.parseInt(sleeper_width.getText().toString());
                                sleeper.widthFrac = sleeper_widthFrac.getSelectedItem().toString();
                                sleeper.flange = Integer.parseInt(sleeper_flange.getText().toString());
                                sleeper.flangeFrac = sleeper_flangeFrac.getSelectedItem().toString();
                                sleeper.color = sleeper_color.getText().toString();
                                sleeper.material = sleeper_material.getText().toString();

                                ConstantVar.SLEEPERS.add(sleeper);
                                sleepersAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Updated Sleeper Box(es)", Toast.LENGTH_SHORT).show();

                            }
                        });

                        builder_sleeper.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder_sleeper.show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Nothing Changed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        sleeperListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ConstantVar.SLEEPERS.get(position).height + ConstantVar.SLEEPERS.get(position).heightFrac + " H Sleeper Box(es)", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        /*
         * SLEEPER BOXES - END
         */


        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);

        if (getView() != null) {
            if (visible) {
                Snackbar.make(getView(), "REVIEW: Swipe Left or Right to Dismiss", Snackbar.LENGTH_SHORT).show();
            }
        }
    }


    static class StockBaseAdapter extends BaseAdapter {

        StockBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.DATASET.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.DATASET.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                for (Map.Entry<String, Integer> entry: ConstantVar.STOCK.entrySet()) {
                    if (ConstantVar.DATASET.get(position).equals(entry.getValue() + ": " + entry.getKey())) {
                        ConstantVar.STOCK.remove(entry.getKey());
                        ConstantVar.DATASET.remove(position);
                    }
                }

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.DATASET.get(position));
            return convertView;
        }
    }


    static class ConeBaseAdapter extends BaseAdapter {
        ConeBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.CONES.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.CONES.get(position).quantity + " " + ConstantVar.CONES.get(position).type + " Cone(s): " + ConstantVar.CONES.get(position).top + " " + ConstantVar.CONES.get(position).topFrac + " Top Dia. " + ConstantVar.CONES.get(position).bot + " " + ConstantVar.CONES.get(position).botFrac + "\" B Dia.";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.CONES.remove(position);


            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.CONES.get(position).quantity + " " + ConstantVar.CONES.get(position).type + " Cone(s): " + ConstantVar.CONES.get(position).top + " " + ConstantVar.CONES.get(position).topFrac + " Top Dia. " + ConstantVar.CONES.get(position).bot + " " + ConstantVar.CONES.get(position).botFrac + "\" B Dia." );
            return convertView;
        }
    }


    static class CornerBaseAdapter extends BaseAdapter {
        CornerBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.CORNERS.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.CORNERS.get(position).quantity + " " + ConstantVar.CORNERS.get(position).type + " Corner(s): " + ConstantVar.CORNERS.get(position).height + " " + ConstantVar.CORNERS.get(position).heightFrac + " H " + ConstantVar.CORNERS.get(position).depth + " " + ConstantVar.CORNERS.get(position).depthFrac + " Dep.";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.CORNERS.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.CORNERS.get(position).quantity + " " + ConstantVar.CORNERS.get(position).type + " Corner(s): " + ConstantVar.CORNERS.get(position).height + " " + ConstantVar.CORNERS.get(position).heightFrac + " H " + ConstantVar.CORNERS.get(position).depth + " " + ConstantVar.CORNERS.get(position).depthFrac + " Dep.");
            return convertView;
        }
    }

    static class PipeBaseAdapter extends BaseAdapter {

        PipeBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.PIPES.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.PIPES.get(position).quantity + " " + ConstantVar.PIPES.get(position).type + " Pipe Wraps: " + ConstantVar.PIPES.get(position).height + " " + ConstantVar.PIPES.get(position).heightFrac + " H " + ConstantVar.PIPES.get(position).color + " " + ConstantVar.PIPES.get(position).material;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.PIPES.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.PIPES.get(position).quantity + " " + ConstantVar.PIPES.get(position).type + " Pipe Wraps: " + ConstantVar.PIPES.get(position).height + " " + ConstantVar.PIPES.get(position).heightFrac + " H " + ConstantVar.PIPES.get(position).color + " " + ConstantVar.PIPES.get(position).material);
            return convertView;
        }
    }

    static class DropBaseAdapter extends BaseAdapter {

        DropBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.DROPS.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.DROPS.get(position).quantity + ": " + ConstantVar.DROPS.get(position).diameter + " " + ConstantVar.DROPS.get(position).diameterFrac + " Dia. " + ConstantVar.DROPS.get(position).depth + " " + ConstantVar.DROPS.get(position).depthFrac + " Dep.";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.DROPS.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.SCUPPERS.get(position).quantity + " " + ConstantVar.SCUPPERS.get(position).type + " Scupper(s) " + ConstantVar.SCUPPERS.get(position).length + " " + ConstantVar.SCUPPERS.get(position).lengthFrac + " L " + ConstantVar.SCUPPERS.get(position).width + " " + ConstantVar.SCUPPERS.get(position).widthFrac + " W " + ConstantVar.SCUPPERS.get(position).depth + " " + ConstantVar.SCUPPERS.get(position).depthFrac + " Dep.");
            return convertView;
        }
    }

    static class ScupperBaseAdapter extends BaseAdapter {

        ScupperBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.SCUPPERS.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.SCUPPERS.get(position).quantity + " " + ConstantVar.SCUPPERS.get(position).type + " Scupper(s) " + ConstantVar.SCUPPERS.get(position).length + " " + ConstantVar.SCUPPERS.get(position).lengthFrac + " L " + ConstantVar.SCUPPERS.get(position).width + " " + ConstantVar.SCUPPERS.get(position).widthFrac + " W " + ConstantVar.SCUPPERS.get(position).depth + " " + ConstantVar.SCUPPERS.get(position).depthFrac + " Dep.";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.SCUPPERS.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.SCUPPERS.get(position).quantity + " " + ConstantVar.SCUPPERS.get(position).type + " Scupper(s) " + ConstantVar.SCUPPERS.get(position).length + " " + ConstantVar.SCUPPERS.get(position).lengthFrac + " L " + ConstantVar.SCUPPERS.get(position).width + " " + ConstantVar.SCUPPERS.get(position).widthFrac + " W " + ConstantVar.SCUPPERS.get(position).depth + " " + ConstantVar.SCUPPERS.get(position).depthFrac + " Dep.");
            return convertView;
        }
    }

    static class PansBaseAdapter extends BaseAdapter {

        PansBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.PANS.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.PANS.get(position).quantity + " " + ConstantVar.PANS.get(position).dimension + " " + ConstantVar.PANS.get(position).type + " Pitch Pan(s): " + ConstantVar.PANS.get(position).height + " " + ConstantVar.PANS.get(position).heightFrac + " H " + ConstantVar.PANS.get(position).color + " " + ConstantVar.PANS.get(position).material;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.PANS.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.PANS.get(position).quantity + " " + ConstantVar.PANS.get(position).dimension + " " + ConstantVar.PANS.get(position).type + " Pitch Pan(s): " + ConstantVar.PANS.get(position).height + " " + ConstantVar.PANS.get(position).heightFrac + " H " + ConstantVar.PANS.get(position).color + " " + ConstantVar.PANS.get(position).material);
            return convertView;
        }
    }

    static class TubesBaseAdapter extends BaseAdapter {

        TubesBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.TUBES.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.TUBES.get(position).quantity + " " + ConstantVar.TUBES.get(position).type + " Tube Wrap(s): " + ConstantVar.TUBES.get(position).height + " " + ConstantVar.TUBES.get(position).heightFrac + " H " + ConstantVar.TUBES.get(position).width + " " + ConstantVar.TUBES.get(position).widthFrac + " W " + ConstantVar.TUBES.get(position).length + " " + ConstantVar.TUBES.get(position).lengthFrac + " L " + ConstantVar.TUBES.get(position).color + " " + ConstantVar.TUBES.get(position).material;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.TUBES.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.TUBES.get(position).quantity + " " + ConstantVar.TUBES.get(position).type + " Tube Wrap(s): " + ConstantVar.TUBES.get(position).height + " " + ConstantVar.TUBES.get(position).heightFrac + " H " + ConstantVar.TUBES.get(position).width + " " + ConstantVar.TUBES.get(position).widthFrac + " W " + ConstantVar.TUBES.get(position).length + " " + ConstantVar.TUBES.get(position).lengthFrac + " L " + ConstantVar.TUBES.get(position).color + " " + ConstantVar.TUBES.get(position).material);
            return convertView;
        }
    }

    static class CurbsBaseAdapter extends BaseAdapter {

        CurbsBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.CURBS.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.CURBS.get(position).quantity + " " + ConstantVar.CURBS.get(position).type + " Curb Wraps: " + ConstantVar.CURBS.get(position).height + " " + ConstantVar.CURBS.get(position).heightFrac + " H " + ConstantVar.CURBS.get(position).width + " " + ConstantVar.CURBS.get(position).widthFrac + " W " + ConstantVar.CURBS.get(position).length + " " + ConstantVar.CURBS.get(position).lengthFrac + " L";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.CURBS.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.CURBS.get(position).quantity + " " + ConstantVar.CURBS.get(position).type + " Curb Wraps: " + ConstantVar.CURBS.get(position).height + " " + ConstantVar.CURBS.get(position).heightFrac + " H " + ConstantVar.CURBS.get(position).width + " " + ConstantVar.CURBS.get(position).widthFrac + " W " + ConstantVar.CURBS.get(position).length + " " + ConstantVar.CURBS.get(position).lengthFrac + " L");
            return convertView;
        }
    }

    static class SleepersBaseAdapter extends BaseAdapter {

        SleepersBaseAdapter() {
        }

        @Override
        public int getCount() {
            return ConstantVar.SLEEPERS.size();
        }

        @Override
        public String getItem(int position) {
            return ConstantVar.SLEEPERS.get(position).quantity + " Sleeper Box(es): " + ConstantVar.SLEEPERS.get(position).length + " " + ConstantVar.SLEEPERS.get(position).lengthFrac + " L " + ConstantVar.SLEEPERS.get(position).width + " " + ConstantVar.SLEEPERS.get(position).widthFrac + " W " + ConstantVar.SLEEPERS.get(position).height + " " + ConstantVar.SLEEPERS.get(position).heightFrac + " H";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void remove(int position) {
            try {
                ConstantVar.SLEEPERS.remove(position);

            } catch (ConcurrentModificationException e) {
                System.out.println("CurrentModificationException : " + e);
            } finally {
                notifyDataSetChanged();
            }
        }
        static class ViewHolder {
            TextView dataTextView;
            ViewHolder(View view) {
                dataTextView = (TextView) view.findViewById(R.id.txt_data);
                view.setTag(this);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.SLEEPERS.get(position).quantity + " Sleeper Box(es): " + ConstantVar.SLEEPERS.get(position).length + " " + ConstantVar.SLEEPERS.get(position).lengthFrac + " L " + ConstantVar.SLEEPERS.get(position).width + " " + ConstantVar.SLEEPERS.get(position).widthFrac + " W " + ConstantVar.SLEEPERS.get(position).height + " " + ConstantVar.SLEEPERS.get(position).heightFrac + " H");
            return convertView;
        }
    }

}













