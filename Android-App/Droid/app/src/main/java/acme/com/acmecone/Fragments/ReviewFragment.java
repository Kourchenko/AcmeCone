package acme.com.acmecone.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        } else {
            stockLayout.setVisibility(View.VISIBLE);
        }

        coneLayout = (LinearLayout) view.findViewById(R.id.layout_cone);
        if (ConstantVar.CONES.isEmpty()) {
            coneLayout.setVisibility(View.GONE);
        } else {
            coneLayout.setVisibility(View.VISIBLE);
        }

        cornerLayout = (LinearLayout) view.findViewById(R.id.layout_corner);
        if (ConstantVar.CORNERS.isEmpty()) {
            cornerLayout.setVisibility(View.GONE);
        } else {
            cornerLayout.setVisibility(View.VISIBLE);
        }

        pipeLayout = (LinearLayout) view.findViewById(R.id.layout_pipe);
        if (ConstantVar.PIPES.isEmpty()) {
            pipeLayout.setVisibility(View.GONE);
        } else {
            pipeLayout.setVisibility(View.VISIBLE);
        }

        dropLayout = (LinearLayout) view.findViewById(R.id.layout_drop);
        if (ConstantVar.DROPS.isEmpty()) {
            dropLayout.setVisibility(View.GONE);
        } else {
            dropLayout.setVisibility(View.VISIBLE);
        }

        scupperLayout = (LinearLayout) view.findViewById(R.id.layout_scupper);
        if (ConstantVar.SCUPPERS.isEmpty()) {
            scupperLayout.setVisibility(View.GONE);
        } else {
            scupperLayout.setVisibility(View.VISIBLE);
        }

        panLayout = (LinearLayout) view.findViewById(R.id.layout_pitch_pan);
        if (ConstantVar.PANS.isEmpty()) {
            panLayout.setVisibility(View.GONE);
        } else {
            panLayout.setVisibility(View.VISIBLE);
        }

        tubeLayout = (LinearLayout) view.findViewById(R.id.layout_tube);
        if (ConstantVar.TUBES.isEmpty()) {
            tubeLayout.setVisibility(View.GONE);
        } else {
            tubeLayout.setVisibility(View.VISIBLE);
        }

        curbLayout = (LinearLayout) view.findViewById(R.id.layout_curb);
        if (ConstantVar.CURBS.isEmpty()) {
            curbLayout.setVisibility(View.GONE);
        } else {
            curbLayout.setVisibility(View.VISIBLE);
        }

        sleeperLayout = (LinearLayout) view.findViewById(R.id.layout_sleeper);
        if (ConstantVar.SLEEPERS.isEmpty()) {
            sleeperLayout.setVisibility(View.GONE);
        } else {
            sleeperLayout.setVisibility(View.VISIBLE);
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
                }
            }
        });

        stockListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (coneTouchListener.existPendingDismisses()) {
                    coneTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        coneListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (cornerTouchListener.existPendingDismisses()) {
                    cornerTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        cornerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (pipeTouchListener.existPendingDismisses()) {
                    pipeTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        pipeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);
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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dropTouchListener.existPendingDismisses()) {
                    dropTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        dropListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);
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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (scupperTouchListener.existPendingDismisses()) {
                    scupperTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        scupperListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (panTouchListener.existPendingDismisses()) {
                    panTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        panListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tubeTouchListener.existPendingDismisses()) {
                    tubeTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        tubeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (curbTouchListener.existPendingDismisses()) {
                    curbTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        curbListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (sleeperTouchListener.existPendingDismisses()) {
                    sleeperTouchListener.undoPendingDismiss();
                } else {
                    // Intent;
                }
            }
        });

        sleeperListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vib.vibrate(100);

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
                ConstantVar.DATASET.remove(position);

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
            return ConstantVar.CONES.get(position).quantity + " " + ConstantVar.CONES.get(position).type + " Cone(s): " + ConstantVar.CONES.get(position).top + ConstantVar.CONES.get(position).topFrac + " Top Dia. " + ConstantVar.CONES.get(position).bot + ConstantVar.CONES.get(position).botFrac + "\" B Dia.";
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

            viewHolder.dataTextView.setText(ConstantVar.CONES.get(position).quantity + " " + ConstantVar.CONES.get(position).type + " Cone(s): " + ConstantVar.CONES.get(position).top + ConstantVar.CONES.get(position).topFrac + " Top Dia. " + ConstantVar.CONES.get(position).bot + ConstantVar.CONES.get(position).botFrac + "\" B Dia." );
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
            return ConstantVar.CORNERS.get(position).quantity + " " + ConstantVar.CORNERS.get(position).type + " Corner(s): " + ConstantVar.CORNERS.get(position).height + ConstantVar.CORNERS.get(position).heightFrac + " H " + ConstantVar.CORNERS.get(position).depth + ConstantVar.CORNERS.get(position).depthFrac + " Dep.";
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

            viewHolder.dataTextView.setText(ConstantVar.CORNERS.get(position).quantity + " " + ConstantVar.CORNERS.get(position).type + " Corner(s): " + ConstantVar.CORNERS.get(position).height + ConstantVar.CORNERS.get(position).heightFrac + " H " + ConstantVar.CORNERS.get(position).depth + ConstantVar.CORNERS.get(position).depthFrac + " Dep.");
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
            return ConstantVar.PIPES.get(position).quantity + " " + ConstantVar.PIPES.get(position).type + " Pipe Wraps: " + ConstantVar.PIPES.get(position).height + ConstantVar.PIPES.get(position).heightFrac + " H " + ConstantVar.PIPES.get(position).color + " " + ConstantVar.PIPES.get(position).color;
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

            viewHolder.dataTextView.setText(ConstantVar.PIPES.get(position).quantity + " " + ConstantVar.PIPES.get(position).type + " Pipe Wraps: " + ConstantVar.PIPES.get(position).height + ConstantVar.PIPES.get(position).heightFrac + " H " + ConstantVar.PIPES.get(position).color + " " + ConstantVar.PIPES.get(position).color);
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
            return ConstantVar.DROPS.get(position).quantity + ": " + ConstantVar.DROPS.get(position).diameter + " Diameter " + ConstantVar.DROPS.get(position).depth + " Depth";
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

            viewHolder.dataTextView.setText(ConstantVar.DROPS.get(position).quantity + ": " + ConstantVar.DROPS.get(position).diameter + " Diameter " + ConstantVar.DROPS.get(position).depth + " Depth");
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
            return ConstantVar.SCUPPERS.get(position).quantity + ": " + ConstantVar.SCUPPERS.get(position).type + " " + ConstantVar.SCUPPERS.get(position).depth + " Depth";
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

            viewHolder.dataTextView.setText(ConstantVar.SCUPPERS.get(position).quantity + ": " + ConstantVar.SCUPPERS.get(position).type + " " + ConstantVar.SCUPPERS.get(position).depth + " Depth");
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
            return ConstantVar.PANS.get(position).quantity + ": " + ConstantVar.PANS.get(position).type + " " + ConstantVar.PANS.get(position).height + " H";
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

            viewHolder.dataTextView.setText(ConstantVar.PANS.get(position).quantity + ": " + ConstantVar.PANS.get(position).type + " " + ConstantVar.PANS.get(position).height + " H");
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
            return ConstantVar.TUBES.get(position).quantity + ": " + ConstantVar.TUBES.get(position).type + " " + ConstantVar.TUBES.get(position).height + " H";
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

            viewHolder.dataTextView.setText(ConstantVar.TUBES.get(position).quantity + ": " + ConstantVar.TUBES.get(position).type + " " + ConstantVar.TUBES.get(position).height + " H");
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
            return ConstantVar.CURBS.get(position).quantity + ": " + ConstantVar.CURBS.get(position).type + " " + ConstantVar.CURBS.get(position).height + " H";
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

            viewHolder.dataTextView.setText(ConstantVar.CURBS.get(position).quantity + ": " + ConstantVar.CURBS.get(position).type + " " + ConstantVar.CURBS.get(position).height + " H");
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
            return ConstantVar.SLEEPERS.get(position).quantity + ": " + ConstantVar.SLEEPERS.get(position).length + " L " + ConstantVar.SLEEPERS.get(position).height + " H";
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

            viewHolder.dataTextView.setText(ConstantVar.SLEEPERS.get(position).quantity + ": " + ConstantVar.SLEEPERS.get(position).length + " L " + ConstantVar.SLEEPERS.get(position).height + " H");
            return convertView;
        }
    }

}













