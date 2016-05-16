package acme.com.acmecone.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import acme.com.acmecone.Items.Cone;
import acme.com.acmecone.Items.Corner;
import acme.com.acmecone.Items.Pipe;

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
    public static final Map<Integer, String> REVIEW_DATABASE = new HashMap<>();
    public static final ArrayList<String> DATASET = new ArrayList<>();


    public static final ArrayList<Cone> CONES = new ArrayList<>();
    public static final ArrayList<Corner> CORNERS = new ArrayList<>();
    public static final ArrayList<Pipe> PIPES = new ArrayList<>();

    public static final String[] MATERIALS = new String[] {
            "TPO 60mil", "PVC 60mil",
            "TPO 45mil", "PVC 45mil"
    };

    public static final String[] COLORS = new String[] {
            "WHITE", "GREY"
    };

    public static String ORDER_MESSAGE = "";


}
