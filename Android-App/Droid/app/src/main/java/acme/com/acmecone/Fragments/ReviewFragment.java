package acme.com.acmecone.Fragments;

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
    public ListView stockListView;
    public ListView coneListView;
    public ListView cornerListView;
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
        final MyBaseAdapter stockAdapter = new MyBaseAdapter();
        final ConeBaseAdapter coneAdapter = new ConeBaseAdapter();
        final CornerBaseAdapter cornerAdapter = new CornerBaseAdapter();
        vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        // Resolved input keyboard layout below EditTexts && AutoCompleteTextViews
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        stockListView = (ListView) view.findViewById(R.id.review_list_view_stock);
        coneListView = (ListView) view.findViewById(R.id.review_list_view_cones);
        cornerListView = (ListView) view.findViewById(R.id.review_list_view_corners);


        stockListView.setAdapter(stockAdapter);
        coneListView.setAdapter(coneAdapter);
        cornerListView.setAdapter(cornerAdapter);


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


    static class MyBaseAdapter extends BaseAdapter {

        MyBaseAdapter() {
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
                for (Map.Entry<Integer, String> entry: ConstantVar.REVIEW_DATABASE.entrySet()) {
                    String mDSET = entry.getKey() + ": " + entry.getValue();
                    if (ConstantVar.DATASET.get(position).equals(mDSET)) {
                        ConstantVar.DATASET.remove(ConstantVar.DATASET.indexOf(ConstantVar.DATASET.get(position)));
                        ConstantVar.REVIEW_DATABASE.values().removeAll(Collections.singleton(entry.getValue()));

                    } else {
                        System.out.println("Error Found While removing: " + mDSET);
                    }
                }

                if (ConstantVar.DATASET.size() == 1 && ConstantVar.REVIEW_DATABASE.size() != ConstantVar.DATASET.size()) {
                    ConstantVar.DATASET.remove(0);
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
            return ConstantVar.CONES.get(position).quantity + ": " + ConstantVar.CONES.get(position).type + " " + ConstantVar.CONES.get(position).top + " T";
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.CONES.get(position).quantity + ": " + ConstantVar.CONES.get(position).type + " " + ConstantVar.CONES.get(position).type + " T");
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
            return ConstantVar.CORNERS.get(position).quantity + ": " + ConstantVar.CORNERS.get(position).type + " " + ConstantVar.CORNERS.get(position).height + " H";
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = convertView == null
                    ? new ViewHolder(convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.review_list_item, parent, false))
                    : (ViewHolder) convertView.getTag();

            viewHolder.dataTextView.setText(ConstantVar.CORNERS.get(position).quantity + ": " + ConstantVar.CORNERS.get(position).type + " " + ConstantVar.CORNERS.get(position).height + " H");
            return convertView;
        }
    }
}













