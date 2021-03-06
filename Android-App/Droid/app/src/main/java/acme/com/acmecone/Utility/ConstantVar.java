package acme.com.acmecone.Utility;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import acme.com.acmecone.Items.Cone;
import acme.com.acmecone.Items.Corner;
import acme.com.acmecone.Items.Curb;
import acme.com.acmecone.Items.Drop;
import acme.com.acmecone.Items.Pan;
import acme.com.acmecone.Items.Pipe;
import acme.com.acmecone.Items.Scupper;
import acme.com.acmecone.Items.Sleeper;
import acme.com.acmecone.Items.Tube;

public class ConstantVar {

    // User LOGIN: Database User Retrieval, Lookup, Get, Add
    public static final String PREFS_LOGIN_USER_ID_KEY = "__USER_ID__";
    public static final String PREFS_LOGIN_PASSWORD_ID_KEY = "__PASSWORD_ID__";

    // Order Form
    public static String NAME = "";
    public static String EMAIL = "";
    public static String COMPANY = "";
    public static String MANUFACTURER = "";

    public static final ArrayList<String> MACHINE_LEARNING_name  = new ArrayList<>();
    public static final ArrayList<String> MACHINE_LEARNING_email = new ArrayList<>();
    public static final ArrayList<String> MACHINE_LEARNING_company = new ArrayList<>();
    public static final ArrayList<String> MACHINE_LEARNING_manf = new ArrayList<>();

    public static final Map<String, String> SEND_DATABASE = new HashMap<>();
    public static final ArrayList<String> DATASET = new ArrayList<>();


    public static final Map<String, Integer> STOCK  = new HashMap<>();
    public static final ArrayList<Cone> CONES       = new ArrayList<>();
    public static final ArrayList<Corner> CORNERS   = new ArrayList<>();
    public static final ArrayList<Pipe> PIPES       = new ArrayList<>();
    public static final ArrayList<Drop> DROPS       = new ArrayList<>();
    public static final ArrayList<Scupper> SCUPPERS = new ArrayList<>();
    public static final ArrayList<Pan> PANS         = new ArrayList<>();
    public static final ArrayList<Tube> TUBES       = new ArrayList<>();
    public static final ArrayList<Curb> CURBS       = new ArrayList<>();
    public static final ArrayList<Sleeper> SLEEPERS = new ArrayList<>();

    public static final String[] MATERIALS = new String[] {
            "TPO 60mil", "PVC 60mil",
            "TPO 45mil", "PVC 45mil"
    };

    public static final String[] COLORS = new String[] {
            "WHITE", "GREY"
    };

}
