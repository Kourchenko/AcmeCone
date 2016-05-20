package acme.com.acmecone.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

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

public class CustomFragment extends Fragment {


    private String[] rowData = {
            "Cone",
            "Corners",
            "Pipe Wraps",
            "Drop Scuppers",
            "Scuppers",
            "Pitch Pans",
            "Tube Wraps",
            "Curb Wraps",
            "Sleeper Boxes"
    };

    private Integer[] imgData = {
            R.mipmap.default_cones,
            R.mipmap.default_corners,
            R.mipmap.default_pipewraps,
            R.mipmap.default_drains,
            R.mipmap.default_scuppers,
            R.mipmap.default_pitchpans,
            R.mipmap.default_tubewraps,
            R.mipmap.default_boxes,
            R.mipmap.default_boxes
    };

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mCustomAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.custom_fragment, container, false);
        final Context context = getActivity().getApplicationContext();

        mRecycleView = (RecyclerView) view.findViewById(R.id.custom_recycle_view);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecycleView.setLayoutManager(mLayoutManager);
        mCustomAdapter = new CustomAdapter(rowData, imgData);
        mRecycleView.setAdapter(mCustomAdapter);

        return view;
    }


    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        String[] textData;
        Integer[] imgData;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView mText;
            public ImageView mImage;

            public ViewHolder(View v) {
                super(v);
                mText = (TextView) v.findViewById(R.id.custom_row_txt);
                mImage = (ImageView) v.findViewById(R.id.custom_row_img);

                v.setClickable(true);
                v.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

                switch (position) {
                    case 0:

                        AlertDialog.Builder builder_cone = new AlertDialog.Builder(v.getContext());

                        builder_cone.setTitle("Custom Cone");
                        final View layout_cone = View.inflate(v.getContext(), R.layout.dialog_cone_custom, null);
                        builder_cone.setView(layout_cone);

                        final RadioGroup cone_RadioGroup = (RadioGroup) layout_cone.findViewById(R.id.cone_btn_radio_group);
                        final RadioButton cone_RadioSplit = (RadioButton) layout_cone.findViewById(R.id.cone_btn_radio_split);
                        final RadioButton cone_RadioNonSplit = (RadioButton) layout_cone.findViewById(R.id.cone_btn_radio_nonsplit);
                        final Spinner cone_menuTop = (Spinner) layout_cone.findViewById(R.id.menu_cone_top_spinner);
                        final Spinner cone_menuBtm = (Spinner) layout_cone.findViewById(R.id.menu_cone_bottom_spinner);
                        final Spinner cone_menuFlg = (Spinner) layout_cone.findViewById(R.id.menu_cone_flange_spinner);
                        final Spinner cone_menuHth = (Spinner) layout_cone.findViewById(R.id.menu_cone_height_spinner);

                        final ArrayAdapter<CharSequence> coneAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        cone_menuTop.setAdapter(coneAdapter);
                        cone_menuBtm.setAdapter(coneAdapter);
                        cone_menuHth.setAdapter(coneAdapter);
                        cone_menuFlg.setAdapter(coneAdapter);

                        builder_cone.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    String type = "Split";
                                    if (cone_RadioGroup.getCheckedRadioButtonId() == cone_RadioSplit.getId()) {
                                        type = "Split";
                                    } else if (cone_RadioGroup.getCheckedRadioButtonId() == cone_RadioNonSplit.getId()) {
                                        type = "Non-Split";
                                    }

                                    final int quantity = Integer.parseInt(((EditText) layout_cone.findViewById(R.id.cone_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_cone.findViewById(R.id.cone_text_height)).getText().toString());
                                    final String heightFrac = cone_menuHth.getSelectedItem().toString();
                                    final int top = Integer.parseInt(((EditText) layout_cone.findViewById(R.id.cone_text_top)).getText().toString());
                                    final String topFrac = cone_menuTop.getSelectedItem().toString();
                                    final int bot = Integer.parseInt(((EditText) layout_cone.findViewById(R.id.cone_text_bottom)).getText().toString());
                                    final String botFrac = cone_menuBtm.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_cone.findViewById(R.id.cone_text_flange)).getText().toString());
                                    final String flangeFrac = cone_menuFlg.getSelectedItem().toString();
                                    final String color = ((EditText) layout_cone.findViewById(R.id.cone_text_color)).getText().toString();
                                    final String material = ((EditText) layout_cone.findViewById(R.id.cone_text_material)).getText().toString();

                                    Cone cone = new Cone();
                                    cone.quantity = quantity;
                                    cone.type = type;
                                    cone.height = height;
                                    cone.heightFrac = heightFrac;
                                    cone.top = top;
                                    cone.topFrac = topFrac;
                                    cone.bot = bot;
                                    cone.botFrac = botFrac;
                                    cone.flange = flange;
                                    cone.flangeFrac = flangeFrac;
                                    cone.color = color;
                                    cone.material = material;

                                    ConstantVar.CONES.add(cone);
                                    coneAdapter.notifyDataSetChanged();;
                                    Toast.makeText(layout_cone.getContext(), "ADDED Cone!", Toast.LENGTH_SHORT).show();

                                } catch (NumberFormatException e) {
                                        Toast.makeText(layout_cone.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder_cone.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builder_cone.show();

                        break;

                    case 1:
                        AlertDialog.Builder builder_corner = new AlertDialog.Builder(v.getContext());

                        builder_corner.setTitle("Custom Corner");
                        final View layout_corner = View.inflate(v.getContext(), R.layout.dialog_corner_custom, null);
                        builder_corner.setView(layout_corner);

                        final RadioGroup corner_RadioGroup = (RadioGroup) layout_corner.findViewById(R.id.corner_btn_radio_group);
                        final RadioButton corner_RadioInside = (RadioButton) layout_corner.findViewById(R.id.corner_btn_radio_inside);
                        final RadioButton corner_RadioOutside = (RadioButton) layout_corner.findViewById(R.id.corner_btn_radio_outside);
                        final Spinner corner_menuHeight = (Spinner) layout_corner.findViewById(R.id.corner_menu_height_spinner);
                        final Spinner corner_menuDepth = (Spinner) layout_corner.findViewById(R.id.corner_menu_depth_spinner);
                        final Spinner corner_menuFlange = (Spinner) layout_corner.findViewById(R.id.corner_menu_flange_spinner);


                        final ArrayAdapter<CharSequence> cornerAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        corner_menuHeight.setAdapter(cornerAdapter);
                        corner_menuDepth.setAdapter(cornerAdapter);
                        corner_menuFlange.setAdapter(cornerAdapter);

                        builder_corner.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    String type = "Inside";
                                    if (corner_RadioGroup.getCheckedRadioButtonId() == corner_RadioInside.getId()) {
                                        type = "Inside";
                                    } else if (corner_RadioGroup.getCheckedRadioButtonId() == corner_RadioOutside.getId()) {
                                        type = "Outside";
                                    }
                                    final int quantity = Integer.parseInt(((EditText) layout_corner.findViewById(R.id.corner_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_corner.findViewById(R.id.corner_text_height)).getText().toString());
                                    final String heightFrac = corner_menuHeight.getSelectedItem().toString();
                                    final int depth = Integer.parseInt(((EditText) layout_corner.findViewById(R.id.corner_text_depth)).getText().toString());
                                    final String depthFrac = corner_menuDepth.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_corner.findViewById(R.id.corner_text_flange)).getText().toString());
                                    final String flangeFrac = corner_menuFlange.getSelectedItem().toString();
                                    final String color = ((EditText) layout_corner.findViewById(R.id.corner_text_color)).getText().toString();
                                    final String material = ((EditText) layout_corner.findViewById(R.id.corner_text_material)).getText().toString();

                                    Corner corner = new Corner();
                                    corner.type = type;
                                    corner.quantity = quantity;
                                    corner.height = height;
                                    corner.heightFrac = heightFrac;
                                    corner.depth = depth;
                                    corner.depthFrac = depthFrac;
                                    corner.flange = flange;
                                    corner.flangeFrac = flangeFrac;
                                    corner.color = color;
                                    corner.material = material;

                                    ConstantVar.CORNERS.add(corner);
                                    Toast.makeText(layout_corner.getContext(), "ADDED Corner!", Toast.LENGTH_SHORT).show();

                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_corner.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_corner.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_corner.show();
                        break;

                    case 2:
                        AlertDialog.Builder builder_pipe = new AlertDialog.Builder(v.getContext());

                        builder_pipe.setTitle("Custom Pipe Wraps");
                        final View layout_pipe = View.inflate(v.getContext(), R.layout.dialog_pipe_custom, null);
                        builder_pipe.setView(layout_pipe);

                        final RadioGroup pipe_RadioGroup = (RadioGroup) layout_pipe.findViewById(R.id.pipe_btn_radio_group);
                        final RadioButton pipe_RadioSplit = (RadioButton) layout_pipe.findViewById(R.id.pipe_btn_radio_split);
                        final RadioButton pipe_RadioNonSplit = (RadioButton) layout_pipe.findViewById(R.id.pipe_btn_radio_nonsplit);
                        final Spinner pipe_menuHeight = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_height_spinner);
                        final Spinner pipe_menuDiameter = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_diameter_spinner);
                        final Spinner pipe_menuFlange = (Spinner) layout_pipe.findViewById(R.id.pipe_menu_flange_spinner);

                        ArrayAdapter<CharSequence> pipeAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pipe_menuHeight.setAdapter(pipeAdapter);
                        pipe_menuDiameter.setAdapter(pipeAdapter);
                        pipe_menuFlange.setAdapter(pipeAdapter);

                        builder_pipe.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {

                                    String type = "Split";
                                    if (pipe_RadioGroup.getCheckedRadioButtonId() == pipe_RadioSplit.getId()) {
                                        type = "Split";
                                    } else if (pipe_RadioGroup.getCheckedRadioButtonId() == pipe_RadioNonSplit.getId()) {
                                        type = "Non-Split";
                                    }

                                    final int quantity = Integer.parseInt(((EditText) layout_pipe.findViewById(R.id.pipe_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_pipe.findViewById(R.id.pipe_text_height)).getText().toString());
                                    final String heightFrac = pipe_menuHeight.getSelectedItem().toString();
                                    final int diameter = Integer.parseInt(((EditText) layout_pipe.findViewById(R.id.pipe_text_diameter)).getText().toString());
                                    final String diameterFrac = pipe_menuDiameter.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_pipe.findViewById(R.id.pipe_text_flange)).getText().toString());
                                    final String flangeFrac = pipe_menuFlange.getSelectedItem().toString();
                                    final String color = ((EditText) layout_pipe.findViewById(R.id.pipe_text_color)).getText().toString();
                                    final String material = ((EditText) layout_pipe.findViewById(R.id.pipe_text_material)).getText().toString();

                                    Pipe pipe = new Pipe();
                                    pipe.type = type;
                                    pipe.quantity = quantity;
                                    pipe.height = height;
                                    pipe.heightFrac = heightFrac;
                                    pipe.diameter = diameter;
                                    pipe.diameterFrac = diameterFrac;
                                    pipe.flange = flange;
                                    pipe.flangeFrac = flangeFrac;
                                    pipe.color = color;
                                    pipe.material = material;

                                    ConstantVar.PIPES.add(pipe);
                                    Toast.makeText(layout_pipe.getContext(), "ADDED Pipe Wrap!", Toast.LENGTH_SHORT).show();

                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_pipe.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_pipe.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_pipe.show();
                        break;

                    case 3:
                        AlertDialog.Builder builder_drop = new AlertDialog.Builder(v.getContext());

                        builder_drop.setTitle("Custom Drop Scuppers");
                        final View layout_drop = View.inflate(v.getContext(), R.layout.dialog_drop_custom, null);
                        builder_drop.setView(layout_drop);

                        final Spinner drop_diameterFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_diameter_spinner);
                        final Spinner drop_depthFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_depth_spinner);
                        final Spinner drop_flangeFrac = (Spinner) layout_drop.findViewById(R.id.drop_menu_flange_spinner);

                        ArrayAdapter<CharSequence> dropAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        drop_diameterFrac.setAdapter(dropAdapter);
                        drop_depthFrac.setAdapter(dropAdapter);
                        drop_flangeFrac.setAdapter(dropAdapter);

                        builder_drop.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    final int quantity = Integer.parseInt(((EditText) layout_drop.findViewById(R.id.drop_text_quantity)).getText().toString());
                                    final int diameter = Integer.parseInt(((EditText) layout_drop.findViewById(R.id.drop_text_diameter)).getText().toString());
                                    final String diameterFrac = drop_diameterFrac.getSelectedItem().toString();
                                    final int depth = Integer.parseInt(((EditText) layout_drop.findViewById(R.id.drop_text_depth)).getText().toString());
                                    final String depthFrac = drop_depthFrac.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_drop.findViewById(R.id.drop_text_flange)).getText().toString());
                                    final String flangeFrac = drop_flangeFrac.getSelectedItem().toString();
                                    final String color = ((EditText) layout_drop.findViewById(R.id.drop_text_color)).getText().toString();
                                    final String material = ((EditText) layout_drop.findViewById(R.id.drop_text_material)).getText().toString();

                                    Drop drop = new Drop();
                                    drop.quantity = quantity;
                                    drop.diameter = diameter;
                                    drop.diameterFrac = diameterFrac;
                                    drop.depth = depth;
                                    drop.depthFrac = depthFrac;
                                    drop.flange = flange;
                                    drop.flangeFrac = flangeFrac;
                                    drop.color = color;
                                    drop.material = material;

                                    ConstantVar.DROPS.add(drop);
                                    Toast.makeText(layout_drop.getContext(), "ADDED Drop Scupper!", Toast.LENGTH_SHORT).show();

                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_drop.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_drop.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_drop.show();

                        break;

                    case 4:
                        AlertDialog.Builder builder_scupper = new AlertDialog.Builder(v.getContext());

                        builder_scupper.setTitle("Custom Scuppers");
                        final View layout_scupper = View.inflate(v.getContext(), R.layout.dialog_scupper_custom, null);
                        builder_scupper.setView(layout_scupper);

                        final RadioGroup scupper_radioGroup = (RadioGroup) layout_scupper.findViewById(R.id.scupper_btn_radio_group);
                        final RadioButton scupper_radio_tw = (RadioButton) layout_scupper.findViewById(R.id.scupper_btn_radio_tw);
                        final RadioButton scupper_radio_ovrf = (RadioButton) layout_scupper.findViewById(R.id.scupper_btn_radio_ovrf);
                        final Spinner scupper_depthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_depth_spinner);
                        final Spinner scupper_widthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_width_spinner);
                        final Spinner scupper_lengthFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_length_spinner);
                        final Spinner scupper_flangeFrac = (Spinner) layout_scupper.findViewById(R.id.scupper_menu_flange_spinner);

                        ArrayAdapter<CharSequence> scupperAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        scupper_depthFrac.setAdapter(scupperAdapter);
                        scupper_widthFrac.setAdapter(scupperAdapter);
                        scupper_lengthFrac.setAdapter(scupperAdapter);
                        scupper_flangeFrac.setAdapter(scupperAdapter);

                        builder_scupper.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    String type = "Thru-Wall";
                                    if (scupper_radioGroup.getCheckedRadioButtonId() == scupper_radio_tw.getId()) {
                                        type = "Thru-Wall";
                                    } else if (scupper_radioGroup.getCheckedRadioButtonId() == scupper_radio_ovrf.getId()) {
                                        type = "Overflow";
                                    }

                                    final int quantity = Integer.parseInt(((EditText) layout_scupper.findViewById(R.id.scupper_text_quantity)).getText().toString());
                                    final int depth = Integer.parseInt(((EditText) layout_scupper.findViewById(R.id.scupper_text_depth)).getText().toString());
                                    final String depthFrac = scupper_depthFrac.getSelectedItem().toString();
                                    final int width = Integer.parseInt(((EditText) layout_scupper.findViewById(R.id.scupper_text_width)).getText().toString());
                                    final String widthFrac = scupper_widthFrac.getSelectedItem().toString();
                                    final int length = Integer.parseInt(((EditText) layout_scupper.findViewById(R.id.scupper_text_length)).getText().toString());
                                    final String lengthFrac = scupper_lengthFrac.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_scupper.findViewById(R.id.scupper_text_flange)).getText().toString());
                                    final String flangeFrac = scupper_flangeFrac.getSelectedItem().toString();
                                    final String color = ((EditText) layout_scupper.findViewById(R.id.scupper_text_color)).getText().toString();
                                    final String material = ((EditText) layout_scupper.findViewById(R.id.scupper_text_material)).getText().toString();

                                    Scupper scupper = new Scupper();
                                    scupper.type = type;
                                    scupper.quantity = quantity;
                                    scupper.depth = depth;
                                    scupper.depthFrac = depthFrac;
                                    scupper.width = width;
                                    scupper.widthFrac = widthFrac;
                                    scupper.length = length;
                                    scupper.lengthFrac = lengthFrac;
                                    scupper.flange = flange;
                                    scupper.flangeFrac = flangeFrac;
                                    scupper.color = color;
                                    scupper.material = material;

                                    ConstantVar.SCUPPERS.add(scupper);
                                    Toast.makeText(layout_scupper.getContext(), "ADDED Scupper!", Toast.LENGTH_SHORT).show();

                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_scupper.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_scupper.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_scupper.show();
                        break;

                    case 5:
                        AlertDialog.Builder builder_pan = new AlertDialog.Builder(v.getContext());
                        builder_pan.setTitle("Custom Pitch Pans");
                        final View layout_pan = View.inflate(v.getContext(), R.layout.dialog_pitch_pans_custom, null);
                        builder_pan.setView(layout_pan);

                        final String[] pan_array_dimension = {"Round", "Square"};

                        final RelativeLayout pan_dimension_width = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_width_hide);
                        final RelativeLayout pan_dimension_length = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_length_hide);
                        final RelativeLayout pan_dimension_diameter = (RelativeLayout) layout_pan.findViewById(R.id.pitchpans_dimension_diameter_hide);
                        final RadioGroup pan_radioGroupType = (RadioGroup) layout_pan.findViewById(R.id.pitchpans_btn_radio_group_type);
                        final RadioButton pan_radioGroupSplit = (RadioButton) layout_pan.findViewById(R.id.pitchpans_btn_radio_split);
                        final RadioButton pan_radioGroupNonSplit = (RadioButton) layout_pan.findViewById(R.id.pitchpans_btn_radio_nonsplit);
                        final Spinner pan_heightFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_height_spinner);
                        final Spinner pan_diameterFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_diameter_spinner);
                        final Spinner pan_lengthFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_length_spinner);
                        final Spinner pan_widthFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_width_spinner);
                        final Spinner pan_flangeFrac = (Spinner) layout_pan.findViewById(R.id.pitchpans_menu_flange_spinner);


                        ArrayAdapter<CharSequence> panAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        pan_heightFrac.setAdapter(panAdapter);
                        pan_diameterFrac.setAdapter(panAdapter);
                        pan_lengthFrac.setAdapter(panAdapter);
                        pan_widthFrac.setAdapter(panAdapter);
                        pan_flangeFrac.setAdapter(panAdapter);


                        builder_pan.setSingleChoiceItems(pan_array_dimension, 0, new DialogInterface.OnClickListener() {
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

                        builder_pan.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    String type = "Split";
                                    if (pan_radioGroupType.getCheckedRadioButtonId() == pan_radioGroupSplit.getId()) {
                                        type = "Split";
                                    } else if (pan_radioGroupType.getCheckedRadioButtonId() == pan_radioGroupNonSplit.getId()) {
                                        type = "Non-Split";
                                    }

                                    if (pan_dimension_diameter.getVisibility() == View.VISIBLE) {

                                            final String dimension = "Round";
                                            final int quantity = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_height)).getText().toString());
                                            final int height = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_height)).getText().toString());
                                            final String heightFrac = pan_heightFrac.getSelectedItem().toString();
                                            final int diameter = Integer.parseInt(((EditText)layout_pan.findViewById(R.id.pitchpans_text_diameter)).getText().toString());
                                            final String diameterFrac = pan_diameterFrac.getSelectedItem().toString();
                                            final int flange = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_flange)).getText().toString());
                                            final String flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                            final String color = ((EditText) layout_pan.findViewById(R.id.pitchpans_text_color)).getText().toString();
                                            final String material = ((EditText) layout_pan.findViewById(R.id.pitchpans_text_material)).getText().toString();

                                            Pan pan = new Pan();
                                            pan.dimension = dimension;
                                            pan.type = type;
                                            pan.quantity = quantity;
                                            pan.height = height;
                                            pan.heightFrac = heightFrac;
                                            pan.diameter = diameter;
                                            pan.diameterFrac = diameterFrac;
                                            pan.flange = flange;
                                            pan.flangeFrac = flangeFrac;
                                            pan.color = color;
                                            pan.material = material;

                                            ConstantVar.PANS.add(pan);
                                            Toast.makeText(layout_pan.getContext(), "ADDED Pitch Pan!", Toast.LENGTH_SHORT).show();

                                        } else if (pan_dimension_diameter.getVisibility() == View.GONE) {
                                            final String dimension = "Square";
                                            final int quantity = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_height)).getText().toString());
                                            final int height = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_height)).getText().toString());
                                            final String heightFrac = pan_heightFrac.getSelectedItem().toString();
                                            final int length = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_length)).getText().toString());
                                            final String lengthFrac = pan_lengthFrac.getSelectedItem().toString();
                                            final int width = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_width)).getText().toString());
                                            final String widthFrac = pan_widthFrac.getSelectedItem().toString();
                                            final int flange = Integer.parseInt(((EditText) layout_pan.findViewById(R.id.pitchpans_text_flange)).getText().toString());
                                            final String flangeFrac = pan_flangeFrac.getSelectedItem().toString();
                                            final String color = ((EditText) layout_pan.findViewById(R.id.pitchpans_text_color)).getText().toString();
                                            final String material = ((EditText) layout_pan.findViewById(R.id.pitchpans_text_material)).getText().toString();

                                            Pan pan = new Pan();
                                            pan.dimension = dimension;
                                            pan.type = type;
                                            pan.quantity = quantity;
                                            pan.height = height;
                                            pan.heightFrac = heightFrac;
                                            pan.length = length;
                                            pan.lengthFrac = lengthFrac;
                                            pan.width = width;
                                            pan.widthFrac = widthFrac;
                                            pan.flange = flange;
                                            pan.flangeFrac = flangeFrac;
                                            pan.color = color;
                                            pan.material = material;

                                            ConstantVar.PANS.add(pan);
                                            Toast.makeText(layout_pan.getContext(), "ADDED Pitch Pan!", Toast.LENGTH_SHORT).show();

                                        }

                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_pan.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_pan.show();
                        break;

                    case 6:
                        AlertDialog.Builder builder_tube = new AlertDialog.Builder(v.getContext());

                        builder_tube.setTitle("Custom Tube Wraps");
                        final View layout_tube = View.inflate(v.getContext(), R.layout.dialog_tube_custom, null);
                        builder_tube.setView(layout_tube);

                        final RadioGroup tube_radioGroup = (RadioGroup) layout_tube.findViewById(R.id.tube_btn_radio_group);
                        final RadioButton tube_radioSplit = (RadioButton) layout_tube.findViewById(R.id.tube_btn_radio_split);
                        final RadioButton tube_radioNonSplit = (RadioButton) layout_tube.findViewById(R.id.tube_btn_radio_nonsplit);
                        final Spinner tube_heightFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_height_spinner);
                        final Spinner tube_lengthFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_length_spinner);
                        final Spinner tube_widthFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_width_spinner);
                        final Spinner tube_flangeFrac = (Spinner) layout_tube.findViewById(R.id.tube_menu_flange_spinner);

                        ArrayAdapter<CharSequence> tubeAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        tube_heightFrac.setAdapter(tubeAdapter);
                        tube_lengthFrac.setAdapter(tubeAdapter);
                        tube_widthFrac.setAdapter(tubeAdapter);
                        tube_flangeFrac.setAdapter(tubeAdapter);

                        builder_tube.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    String type = "Split";
                                    if (tube_radioGroup.getCheckedRadioButtonId() == tube_radioSplit.getId()) {
                                        type = "Split";

                                    } else if (tube_radioGroup.getCheckedRadioButtonId() == tube_radioNonSplit.getId()) {
                                        type = "Non-Split";
                                    }
                                    final int quantity = Integer.parseInt(((EditText) layout_tube.findViewById(R.id.tube_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_tube.findViewById(R.id.tube_text_height)).getText().toString());
                                    final String heightFrac = ((Spinner) layout_tube.findViewById(R.id.tube_menu_height_spinner)).getSelectedItem().toString();
                                    final int length = Integer.parseInt(((EditText) layout_tube.findViewById(R.id.tube_text_length)).getText().toString());
                                    final String lengthFrac = ((Spinner) layout_tube.findViewById(R.id.tube_menu_width_spinner)).getSelectedItem().toString();
                                    final int width = Integer.parseInt(((EditText) layout_tube.findViewById(R.id.tube_text_width)).getText().toString());
                                    final String widthFrac = ((Spinner) layout_tube.findViewById(R.id.tube_menu_flange_spinner)).getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_tube.findViewById(R.id.tube_text_flange)).getText().toString());
                                    final String flangeFrac = ((Spinner) layout_tube.findViewById(R.id.tube_menu_flange_spinner)).getSelectedItem().toString();
                                    final String color = ((EditText) layout_tube.findViewById(R.id.tube_text_color)).getText().toString();
                                    final String material = ((EditText) layout_tube.findViewById(R.id.tube_text_material)).getText().toString();

                                    Tube tube = new Tube();
                                    tube.type = type;
                                    tube.quantity = quantity;
                                    tube.height = height;
                                    tube.heightFrac = heightFrac;
                                    tube.length = length;
                                    tube.lengthFrac = lengthFrac;
                                    tube.width = width;
                                    tube.widthFrac = widthFrac;
                                    tube.flange = flange;
                                    tube.flangeFrac = flangeFrac;
                                    tube.color = color;
                                    tube.material = material;

                                    ConstantVar.TUBES.add(tube);
                                    Toast.makeText(layout_tube.getContext(), "ADDED Tube Wraps!", Toast.LENGTH_SHORT).show();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_tube.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_tube.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_tube.show();

                        break;

                    case 7:
                        AlertDialog.Builder builder_curb = new AlertDialog.Builder(v.getContext());
                        builder_curb.setTitle("Custom Curb Wraps");
                        final View layout_curb = View.inflate(v.getContext(), R.layout.dialog_curb_custom, null);
                        builder_curb.setView(layout_curb);

                        final RadioGroup curb_radioGroup = (RadioGroup) layout_curb.findViewById(R.id.curb_radioGroup);
                        final RadioButton curb_radioSplit = (RadioButton) layout_curb.findViewById(R.id.curb_radio_split);
                        final RadioButton curb_radioNonSplit = (RadioButton) layout_curb.findViewById(R.id.curb_radio_nonsplit);
                        final Spinner curb_heightFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_height_spinner);
                        final Spinner curb_lengthFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_length_spinner);
                        final Spinner curb_widthFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_width_spinner);
                        final Spinner curb_flangeFrac = (Spinner) layout_curb.findViewById(R.id.curb_menu_flange_spinner);

                        ArrayAdapter<CharSequence> curbAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        curb_heightFrac.setAdapter(curbAdapter);
                        curb_lengthFrac.setAdapter(curbAdapter);
                        curb_widthFrac.setAdapter(curbAdapter);
                        curb_flangeFrac.setAdapter(curbAdapter);

                        builder_curb.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    String type = "Split";
                                    if (curb_radioGroup.getCheckedRadioButtonId() == curb_radioSplit.getId()) {
                                        type = "Split";
                                    } else if (curb_radioGroup.getCheckedRadioButtonId() == curb_radioNonSplit.getId()) {
                                        type = "Non-Split";
                                    }
                                    final int quantity = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.curb_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.curb_text_height)).getText().toString());
                                    final String heightFrac = curb_heightFrac.getSelectedItem().toString();
                                    final int length = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.curb_text_length)).getText().toString());
                                    final String lengthFrac = curb_lengthFrac.getSelectedItem().toString();
                                    final int width = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.curb_text_width)).getText().toString());
                                    final String widthFrac = curb_widthFrac.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_curb.findViewById(R.id.curb_text_flange)).getText().toString());
                                    final String flangeFrac = curb_flangeFrac.getSelectedItem().toString();
                                    final String color = ((EditText) layout_curb.findViewById(R.id.curb_text_color)).getText().toString();
                                    final String material = ((EditText) layout_curb.findViewById(R.id.curb_text_material)).getText().toString();

                                    Curb curb = new Curb();
                                    curb.type = type;
                                    curb.quantity = quantity;
                                    curb.height = height;
                                    curb.heightFrac = heightFrac;
                                    curb.length = length;
                                    curb.lengthFrac = lengthFrac;
                                    curb.width = width;
                                    curb.widthFrac = widthFrac;
                                    curb.flange = flange;
                                    curb.flangeFrac = flangeFrac;
                                    curb.color = color;
                                    curb.material = material;

                                    ConstantVar.CURBS.add(curb);
                                    Toast.makeText(layout_curb.getContext(), "ADDED Curb Wrap!", Toast.LENGTH_SHORT).show();

                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_curb.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_curb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_curb.show();
                        break;

                    case 8:
                        AlertDialog.Builder builder_sleeper = new AlertDialog.Builder(v.getContext());
                        builder_sleeper.setTitle("Custom Sleeper Box");
                        final View layout_sleeper = View.inflate(v.getContext(), R.layout.dialog_sleeper_custom, null);
                        builder_sleeper.setView(layout_sleeper);

                        final Spinner sleeper_heightFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_height_spinner);
                        final Spinner sleeper_lengthFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_length_spinner);
                        final Spinner sleeper_widthFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_width_spinner);
                        final Spinner sleeper_flangeFrac = (Spinner) layout_sleeper.findViewById(R.id.sleeper_menu_flange_spinner);

                        ArrayAdapter<CharSequence> sleeperAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.custom_fractions, R.layout.my_spinner_item);
                        sleeper_heightFrac.setAdapter(sleeperAdapter);
                        sleeper_lengthFrac.setAdapter(sleeperAdapter);
                        sleeper_widthFrac.setAdapter(sleeperAdapter);
                        sleeper_flangeFrac.setAdapter(sleeperAdapter);

                        builder_sleeper.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    final int quantity = Integer.parseInt(((EditText) layout_sleeper.findViewById(R.id.sleeper_text_quantity)).getText().toString());
                                    final int height = Integer.parseInt(((EditText) layout_sleeper.findViewById(R.id.sleeper_text_height)).getText().toString());
                                    final String heightFrac = sleeper_heightFrac.getSelectedItem().toString();
                                    final int length = Integer.parseInt(((EditText) layout_sleeper.findViewById(R.id.sleeper_text_length)).getText().toString());
                                    final String lengthFrac = sleeper_lengthFrac.getSelectedItem().toString();
                                    final int width = Integer.parseInt(((EditText) layout_sleeper.findViewById(R.id.sleeper_text_width)).getText().toString());
                                    final String widthFrac = sleeper_widthFrac.getSelectedItem().toString();
                                    final int flange = Integer.parseInt(((EditText) layout_sleeper.findViewById(R.id.sleeper_text_flange)).getText().toString());
                                    final String flangeFrac = sleeper_flangeFrac.getSelectedItem().toString();
                                    final String color = ((EditText) layout_sleeper.findViewById(R.id.sleeper_text_color)).getText().toString();
                                    final String material = ((EditText) layout_sleeper.findViewById(R.id.sleeper_text_material)).getText().toString();

                                    Sleeper sleeper = new Sleeper();
                                    sleeper.quantity = quantity;
                                    sleeper.height = height;
                                    sleeper.heightFrac = heightFrac;
                                    sleeper.length = length;
                                    sleeper.lengthFrac = lengthFrac;
                                    sleeper.width = width;
                                    sleeper.widthFrac = widthFrac;
                                    sleeper.flange = flange;
                                    sleeper.flangeFrac = flangeFrac;
                                    sleeper.color = color;
                                    sleeper.material = material;

                                    ConstantVar.SLEEPERS.add(sleeper);
                                    Toast.makeText(layout_sleeper.getContext(), "ADDED Sleeper Box!", Toast.LENGTH_SHORT).show();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(layout_sleeper.getContext(), "Nothing Added Empty Entries...", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        builder_sleeper.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });

                        builder_sleeper.show();
                        break;
                }
            }
        }

        public CustomAdapter(String[] textData, Integer[] imgData) {
            // TODO Auto-generated constructor stub
            this.textData = textData;
            this.imgData = imgData;
        }



        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);

            TextView text = (TextView) parent.findViewById(R.id.custom_row_txt);
            ImageView img = (ImageView) parent.findViewById(R.id.custom_row_img);

            ViewHolder vh = new ViewHolder(vi);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mText.setText(textData[position]);
            holder.mImage.setImageResource(imgData[position]);
        }


        @Override
        public int getItemCount() {
            // TODO Auto-generated method stub
            return textData.length;
        }


        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

    }
}
