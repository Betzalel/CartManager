package edu.gatech.seclass.scm.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

import edu.gatech.seclass.scm.R;

/**
 * Created by shivendrasrivastava on 10/18/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_BILLING_ADDRESS = "billing_address";

    public static final String COLUMN_EMAIL_ADDRESS = "email_address";

    public static final String COLUMN_CUSTOMER_ID = "customer_id";

    public static final String COLUMN_STATUS = "status";

    public static final String COLUMN_REWARD = "reward";

    public static final String COLUMN_REWARD_DATE = "reward_date";

    public static final String COLUMN_PRODUCT_ID = "product_id";

    public static final String COLUMN_PRODUCT_DESC = "product_desc";

    public static final String COLUMN_PRICE = "price";

    public static final String COLUMN_SIZE = "size";

    public static final String COLUMN_QTY_AVAIL = "qty_available";

    public static final String COLUMN_AMOUNT = "amount";

    public static final String COLUMN_DISCOUNT = "discount";

    public static final String COLUMN_CREDIT_CARD_NO = "credit_card_no";

    public static final String COLUMN_AUTH_RESPONSE = "auth_response";

    public static final String COLUMN_TRANSACTION_ID = "transaction_id";

    public static final String COLUMN_ORDER_ID = "order_id";

    public static final String COLUMN_TOTAL_PRICE = "total_price";

    public static final String COLUMN_SUBTOTAL_PRICE = "subtotal_price";

    public static final String COLUMN_TAX = "tax";

    public static final String COLUMN_DISCOUNT_TYPE = "discount_type";

    public static final String COLUMN_ORDER_ENTRY_ID = "order_entry_id";

    public static final String COLUMN_ORDER_ENTRY_PRICE = "order_entry_price";

    public static final String COLUMN_ORDER_PLACE_DATE = "order_place_date";

    public static final String COLUMN_TRANSACTION_DATE = "transaction_date";

    public static final String COLUMN_QTY = "qty";

    public static final String CUSTOMER = "CUSTOMER";

    public static final String PRODUCT = "PRODUCT";

    public static final String TRANSACTION = "ORDER_TRANS";

    public static final String ORDER = "ORDERS";

    public static final String ORDER_ENTRY = "ORDER_ENTRY";

    public static final String DATABASE_NAME = "SMOOTHIECARTDB";

    public static final int DATABASE_VERSION = 1;

    public static final String VALUES = "VALUES";

    private Context context;

    private static final String CREATE_CUSTOMER_TABLE_SQL = "CREATE TABLE " + CUSTOMER + "("
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_BILLING_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_EMAIL_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_CUSTOMER_ID + " TEXT PRIMARY KEY, "
            + COLUMN_STATUS + " TEXT , "
            + COLUMN_REWARD + " TEXT , "
            + COLUMN_REWARD_DATE + " TEXT, "
            + COLUMN_DISCOUNT + " INTEGER "
            + ");";

    private static final String CREATE_PRODUCT_TABLE_SQL = "CREATE TABLE " + PRODUCT + "("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_PRODUCT_DESC + " TEXT NOT NULL, "
            + COLUMN_PRICE + " TEXT NOT NULL, "
            + COLUMN_SIZE + " TEXT NOT NULL, "
            + COLUMN_QTY_AVAIL + " INTEGER NOT NULL "
            + ");";

    private static final String CREATE_TRANSACTION_TABLE_SQL = "CREATE TABLE " + TRANSACTION + "("
            + COLUMN_CUSTOMER_ID + " TEXT , "
            + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ORDER_ID + " INTEGER NOT NULL, "
            + COLUMN_AMOUNT + " REAL NOT NULL, "
            + COLUMN_DISCOUNT + " REAL NOT NULL, "
            + COLUMN_DISCOUNT_TYPE + " TEXT NOT NULL, "
            + COLUMN_CREDIT_CARD_NO + " TEXT NOT NULL, "
            + COLUMN_AUTH_RESPONSE + " TEXT, "
            + COLUMN_TRANSACTION_DATE + " TEXT NOT NULL "
            + ");";

    private static final String CREATE_ORDER_TABLE_SQL = "CREATE TABLE " + ORDER + "("
            + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CUSTOMER_ID + " TEXT, "
            + COLUMN_SUBTOTAL_PRICE + " REAL NOT NULL, "
            + COLUMN_TOTAL_PRICE + " REAL NOT NULL, "
            + COLUMN_TAX + " REAL NOT NULL, "
            + COLUMN_DISCOUNT + " REAL NOT NULL, "
            + COLUMN_DISCOUNT_TYPE + " TEXT NOT NULL, "
            + COLUMN_ORDER_PLACE_DATE + " TEXT NOT NULL "
            + ");";

    private static final String CREATE_ORDER_ENTRY_TABLE_SQL = "CREATE TABLE " + ORDER_ENTRY + "("
            + COLUMN_ORDER_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CUSTOMER_ID + " INTEGER, "
            + COLUMN_PRODUCT_ID + " TEXT NOT NULL, "
            + COLUMN_PRODUCT_DESC + " TEXT NOT NULL, "
            + COLUMN_DISCOUNT + " REAL NOT NULL, "
            + COLUMN_DISCOUNT_TYPE + " TEXT NOT NULL, "
            + COLUMN_ORDER_ENTRY_PRICE + " REAL NOT NULL, "
            + COLUMN_QTY + " INTEGER NOT NULL "
            + ");";

    private static final String INSERT_CUSTOMER_TABLE_SQL = "INSERT INTO " + CUSTOMER + "("
            + COLUMN_NAME + ", "
            + COLUMN_BILLING_ADDRESS + ", "
            + COLUMN_EMAIL_ADDRESS + ", "
            + COLUMN_CUSTOMER_ID + ", "
            + COLUMN_STATUS + ", "
            + COLUMN_REWARD + ", "
            + COLUMN_REWARD_DATE + ", "
            + COLUMN_DISCOUNT + ") "
            + VALUES + " (";

    private static final String CUSTOMER_1_DATA = "'Ralph Hapschatt', " +
            "'2, Overlook Point, Lincolnshire, IL 60069', " +
            "'raplh@mailinator.com', 'b53b7c86ffeeaddbbe352f1f4dcd8e1a', '', '', '', 0)";

    private static final String CUSTOMER_2_DATA = "'Betty Monroe', " +
            "'3, Overlook Point, Lincolnshire, IL 60069', " +
            "'betty@mailinator.com', 'b6acb59441af4ea13129d8373df8145e', '', '', '', 0)";

    private static final String CUSTOMER_3_DATA = "'Everett Scott', " +
            "'4, Overlook Point, Lincolnshire, IL 60069', " +
            "'everett@mailinator.com', 'f184cd0f0e056d4c58c4b0264e5a6bcc', '', '', '', 0 )";

    private static final String CUSTOMER_4_DATA = "'Robert Houck', " +
            "'5, Overlook Point, Lincolnshire, IL 60069', " +
            "'robert@mailinator.com', 'e0b2197ef9f76e180da04c825aba21dc', '', '', '', 0)";

    private static final String CUSTOMER_5_DATA = "'Monica Ruth', " +
            "'6, Overlook Point, Lincolnshire, IL 60069', " +
            "'monica@mailinator.com', 'fbfe3361fa3d7fa6792cdffa792965a6', '', '', '', 0)";

    private static final String CUSTOMER_6_DATA = "'Shivendra Srivastava', " +
            "'7, Overlook Point, Lincolnshire, IL 60069', " +
            "'shivendra@mailinator.com', 'ea7848bd7bd196b198b970d13cc18281', '', '', '', 0)";

    private static final String CUSTOMER_7_DATA = "'Gail Larkin', " +
            "'8, Overlook Point, Lincolnshire, IL 60069', " +
            "'gail@mailinator.com', '4907c3ad5783a6a27ab1e6ece52a2758', '', '', '', 0)";

    private static final String INSERT_PRODUCT_TABLE_SQL = "INSERT INTO " + PRODUCT + "("
            + COLUMN_PRODUCT_ID + ", "
            + COLUMN_PRODUCT_DESC + ", "
            + COLUMN_PRICE + ", "
            + COLUMN_SIZE + ", "
            + COLUMN_QTY_AVAIL + ") "
            + VALUES + " (";

    private static final String PRODUCT_1_DATA = "'010705090', " +
            "'BANANA GINGER SMOOTHIE', " +
            "'8.00', '20 oz', '20')";

    private static final String PRODUCT_2_DATA = "'010705091', " +
            "'ORANGE DREAM CREAMSICLE', " +
            "'9.00', '20 oz', '20' )";

    private static final String PRODUCT_3_DATA = "'010705092', " +
            "'GREEN TEA, BLUEBERRY AND BANANA', " +
            "'9.00', '20 oz', '20' )";

    private static final String PRODUCT_4_DATA = "'010705093', " +
            "'VERY BERRY BREAKFAST', " +
            "'10.00', '20 oz', '20' )";

    private static final String PRODUCT_5_DATA = "'010705094', " +
            "'WORLDS BEST SMOOTHIE', " +
            "'12.00', '20 oz', '20' )";

    private static final String PRODUCT_6_DATA = "'010705095', " +
            "'PINEAPPLE PASSION', " +
            "'7.00', '20 oz', '20' )";

    private static final String PRODUCT_7_DATA = "'010705096', " +
            "'STRAWBERRY KIWI SMOOTHIE', " +
            "'6.00', '20 oz', '20'  )";

    private static final String PRODUCT_8_DATA = "'010705097', " +
            "'BANANA BLUE BERRY SOY SMOOTHIE', " +
            "'8.00', '20 oz', '20'  )";

    private static final String PRODUCT_9_DATA = "'010705098', " +
            "'PAPAYA SMOOTHIE', " +
            "'8.00', '20 oz', '20' )";

    private static final String PRODUCT_10_DATA = "'010705099', " +
            "'PEACHY SMOOTHIE', " +
            "'9.00', '20 oz', '20' )";

    public static final String INSERT_TRANSACTION_TABLE_SQL = "INSERT INTO " + TRANSACTION + "("
            + COLUMN_CUSTOMER_ID + ", "
            + COLUMN_TRANSACTION_ID + ", "
            + COLUMN_ORDER_ID + ", "
            + COLUMN_AMOUNT + ", "
            + COLUMN_DISCOUNT + ", "
            + COLUMN_DISCOUNT_TYPE + ", "
            + COLUMN_CREDIT_CARD_NO + ", "
            + COLUMN_AUTH_RESPONSE + ", "
            + COLUMN_TRANSACTION_DATE + ") "
            + VALUES + " (";

    public static final String TRANSACTION_1_DATA = "'b53b7c86ffeeaddbbe352f1f4dcd8e1a', "+
            "1, " + " 1, "+ "32.00, " + "0.0, " + "''," + "'378209948214821',"+
            "'Approved', "+"'10/1/2015 17:00' )";

    public static final String TRANSACTION_2_DATA = "'b6acb59441af4ea13129d8373df8145e', "+
            "2, " + " 2, "+ "30.00, " + "5.0, " + "'REWARD'," + "'344120929760982',"+
            "'Approved', "+"'10/2/2015 18:00' )";

    public static final String TRANSACTION_3_DATA = "'f184cd0f0e056d4c58c4b0264e5a6bcc', "+
            "3, " + " 3, "+ "55.00, " + "5.0, " + "'REWARD'," + "'373270463588150',"+
            "'Approved', "+"'10/3/2015 14:31' )";

    public static final String TRANSACTION_4_DATA = "'e0b2197ef9f76e180da04c825aba21dc', "+
            "4, " + " 4, "+ "8.00, " + "2.0, " + "'REWARD'," + "'344092755759331',"+
            "'Approved', "+"'10/2/2015 12:00' )";

    public static final String TRANSACTION_5_DATA = "'e0b2197ef9f76e180da04c825aba21dc', "+
            "5, " + " 5, "+ "10.00, " + "0.0, " + "''," + "'374392316734366',"+
            "'Approved', "+"'10/5/2015 10:01' )";

  /**  public static final String TRANSACTION_6_DATA = "'fbfe3361fa3d7fa6792cdffa792965a6', "+
            "6, " + " 6, "+ "15.00, " + "1.0, " + "'REWARD'," + "'346748032528363',"+
            "'Approved', "+"'10/8/2015 15:20' )";**/

    public static final String TRANSACTION_7_DATA = "'ea7848bd7bd196b198b970d13cc18281', "+
            "7, " + " 7, "+ "17.00, " + "0.0, " + "''," + "'342600154502797',"+
            "'Approved', "+"'10/5/2015 17:34' )";

    public static final String TRANSACTION_8_DATA = "'ea7848bd7bd196b198b970d13cc18281', "+
            "8, " + " 8, "+ "24.00, " + "0.0, " + "''," + "'376798546886537',"+
            "'Approved', "+"'10/6/2015 11:34' )";

    public static final String TRANSACTION_9_DATA = "'ea7848bd7bd196b198b970d13cc18281', "+
            "9, " + " 9, "+ "40.00, " + "2.0, " + "'REWARD'," + "'370809994953467',"+
            "'Approved', "+"'10/20/2015 10:18' )";

    public static final String TRANSACTION_10_DATA = "'4907c3ad5783a6a27ab1e6ece52a2758', "+
            "10, " + " 10, "+ "21.00, " + "0.0, " + "''," + "'378684675921329',"+
            "'Approved', "+"'10/19/2015 10:05' )";

    public static final String TRANSACTION_11_DATA = "'4907c3ad5783a6a27ab1e6ece52a2758', "+
            "11, " + " 11, "+ "6.00, " + "0.0, " + "''," + "'344676603144835',"+
            "'Approved', "+"'10/18/2015 14:32' )";

    public static final String TRANSACTION_12_DATA = "'4907c3ad5783a6a27ab1e6ece52a2758', "+
            "12, " + " 12, "+ "8.00, " + "0.0, " + "''," + "'345975850414092',"+
            "'Approved', "+"'10/12/2015 18:00' )";

    public static final String INSERT_ORDER_TABLE_SQL = "INSERT INTO " + ORDER + "("
            + COLUMN_ORDER_ID + ", "
            + COLUMN_CUSTOMER_ID + ", "
            + COLUMN_TOTAL_PRICE + ", "
            + COLUMN_SUBTOTAL_PRICE + ", "
            + COLUMN_TAX + ", "
            + COLUMN_DISCOUNT + ", "
            + COLUMN_DISCOUNT_TYPE + ", "
            + COLUMN_ORDER_PLACE_DATE + ") "
            + VALUES + " (";

    public static final String ORDER_1_DATA = "1, 'b53b7c86ffeeaddbbe352f1f4dcd8e1a', "+
            "32.0, 32.0, 0, 0.00, '', '10/1/2015 17:00' )";

    public static final String ORDER_2_DATA = "2, 'b6acb59441af4ea13129d8373df8145e', "+
            "35.0, 40.0, 0, 5.00, 'Reward', '10/2/2015 18:00' )";

    public static final String ORDER_3_DATA = "3, 'f184cd0f0e056d4c58c4b0264e5a6bcc', "+
            "55.0, 60.0, 0, 5.00, 'Reward', '10/3/2015 14:31' )";

    public static final String ORDER_4_DATA = "4, 'e0b2197ef9f76e180da04c825aba21dc', "+
            "10.0, 10, 0, 2.0, 'Reward',  '10/2/2015 12:00' )";

    public static final String ORDER_5_DATA = "5, 'e0b2197ef9f76e180da04c825aba21dc', "+
            "10.0, 10, 0, 0.0, '',  '10/5/2015 10:01' )";

    public static final String ORDER_6_DATA = "6, 'fbfe3361fa3d7fa6792cdffa792965a6', "+
            "16.0, 16, 0, 1.0, 'Reward',  '10/8/2015 15:20' )";

    public static final String ORDER_7_DATA = "7, 'ea7848bd7bd196b198b970d13cc18281', "+
            "17.0, 17, 0, 0.0, '',  '10/5/2015 17:34' )";

    public static final String ORDER_8_DATA = "8, 'ea7848bd7bd196b198b970d13cc18281', "+
            "24.0, 24, 0, 0.0, '',  '10/6/2015 11:34' )";

    public static final String ORDER_9_DATA = "9, 'ea7848bd7bd196b198b970d13cc18281', "+
            "42.0, 42, 0, 0.0, '',  '10/20/2015 10:18' )";

    public static final String ORDER_10_DATA = "10, '4907c3ad5783a6a27ab1e6ece52a2758', "+
            "21.0, 21, 0, 0.0, '',  '10/19/2015 10:05' )";

    public static final String ORDER_11_DATA = "11, '4907c3ad5783a6a27ab1e6ece52a2758', "+
            "6.0, 6, 0, 0.0, '',  '10/18/2015 14:32' )";

    public static final String ORDER_12_DATA = "12, '4907c3ad5783a6a27ab1e6ece52a2758', "+
            "8.0, 8.0, 0, 0.0, '', '10/12/2015 18:00' )";


    public static final String INSERT_ORDER_ENTRY_SQL = "INSERT INTO " + ORDER_ENTRY + "("
            + COLUMN_ORDER_ENTRY_ID + ", "
            + COLUMN_CUSTOMER_ID + ", "
            + COLUMN_PRODUCT_ID + ", "
            + COLUMN_PRODUCT_DESC + ", "
            + COLUMN_DISCOUNT + ", "
            + COLUMN_DISCOUNT_TYPE + ", "
            + COLUMN_ORDER_ENTRY_PRICE + ", "
            + COLUMN_QTY + ") "
            + VALUES + " (";

    public static final String ORDER_ENTRY_1_DATA = "1,'b53b7c86ffeeaddbbe352f1f4dcd8e1a', "+
            "'10705090', 'BANANA GINGER SMOOTHIE', 0, '', 32.0, 4 )";

    public static final String ORDER_ENTRY_2_DATA = "2, 'b6acb59441af4ea13129d8373df8145e', "+
            "'10705095', 'PINEAPPLE PASSION', 5.0, 'REWARD', 35.0, 5 )";

    public static final String ORDER_ENTRY_3_DATA = "3, 'f184cd0f0e056d4c58c4b0264e5a6bcc', "+
            "'10705094', 'WORLDS BEST SMOOTHIE', 5.0, 'REWARD', 55.0, 5 )";

    public static final String ORDER_ENTRY_4_DATA = "4, 'e0b2197ef9f76e180da04c825aba21dc', "+
            "'10705093', 'VERY BERRY BREAKFAST', 2.0, 'REWARD', 10.0, 1 )";

    public static final String ORDER_ENTRY_5_DATA = "5, 'e0b2197ef9f76e180da04c825aba21dc', "+
            "'10705093', 'VERY BERRY BREAKFAST', 0.0, '', 10.0, 1 )";

    public static final String ORDER_ENTRY_6_DATA = "6, 'fbfe3361fa3d7fa6792cdffa792965a6', "+
            "'10705097', 'BANANA BLUE BERRY SOY SMOOTHIE', 1.0, 'REWARD', 16.0, 2 )";

    public static final String ORDER_ENTRY_7_DATA = "7, 'ea7848bd7bd196b198b970d13cc18281', "+
            "'10705095', 'PINEAPPLE PASSION', 0.0, '', 7.0, 1 )";

    public static final String ORDER_ENTRY_8_DATA = "8, 'ea7848bd7bd196b198b970d13cc18281', "+
            "'10705098', 'PAPAYA SMOOTHIE', 0.0, '', 24.0, 1 )";

    public static final String ORDER_ENTRY_9_DATA = "9, 'ea7848bd7bd196b198b970d13cc18281', "+
            "'10705098', 'PAPAYA SMOOTHIE', 0.0, '', 42.0, 7 )";

    public static final String ORDER_ENTRY_10_DATA = "10,  '4907c3ad5783a6a27ab1e6ece52a2758', "+
            "'10705095', 'PINEAPPLE PASSION', 0.0, '', 21.0, 3 )";

    public static final String ORDER_ENTRY_11_DATA = "11, '4907c3ad5783a6a27ab1e6ece52a2758', "+
            "'10705096', 'STRAWBERRY KIWI SMOOTHIE', 0.0, '', 6.0, 1 )";

    public static final String ORDER_ENTRY_12_DATA = "12,  '4907c3ad5783a6a27ab1e6ece52a2758', "+
            "'10705097', 'BANANA BLUE BERRY SOY SMOOTHIE', 0.0, '', 8.0, 1 )";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //Log.w("SmoothieCartManager :::", "Database creation in progress ");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CUSTOMER_TABLE_SQL);
        db.execSQL(CREATE_PRODUCT_TABLE_SQL);
        db.execSQL(CREATE_TRANSACTION_TABLE_SQL);
        db.execSQL(CREATE_ORDER_TABLE_SQL);
        db.execSQL(CREATE_ORDER_ENTRY_TABLE_SQL);
        Log.w("SmoothieCartManager :::", "Database tables created successfully ");
        //Log.w("SmoothieCartManager :::", "Inserting data into tables ");
        insertDataIntoCustomer(db);
        insertDataIntoProduct(db);
        insertDataIntoOrderAndOrderEntry(db);
        insertDataIntoTransactions(db);
        //Log.w("SmoothieCartManager :::", "Data inserted successfully ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.w("ANDROID APP", " Upgrading Database from " + oldVersion + " to " + newVersion);

        db.execSQL(" DROP TABLE IF EXISTS " + CUSTOMER);
        db.execSQL(" DROP TABLE IF EXISTS " + PRODUCT);
        db.execSQL(" DROP TABLE IF EXISTS " + TRANSACTION);
        db.execSQL(" DROP TABLE IF EXISTS " + ORDER);
        db.execSQL(" DROP TABLE IF EXISTS " + ORDER_ENTRY);

        onCreate(db);
    }

    /**
     * Creates the database if it does not exist.
     */
    public void createDatabase(){
        Boolean isUpdateRequired = Boolean.parseBoolean(context.getResources().getString(R.string.db_update));
       // if(!databaseExists()){
         //   this.getReadableDatabase();
       // }
        if(isUpdateRequired){
            Log.w("SmoothieCartManager :::", "Upgrading DB based on config..");
            context.deleteDatabase(DATABASE_NAME);
            this.getReadableDatabase();
        }
    }

    /**
     * Checks if the database exists.
     * @return boolean
     */
    private boolean databaseExists(){
        try{
            File dbFile = getDatabasePath();
            if(dbFile.exists()){
                return true;
            }else{
                Log.w("SmoothieCartManager :::", "Database tables already exist. Not creating again... ");
            }
        }catch(SQLiteException ex){
            ex.printStackTrace();
        }
        return false;
    }

    private File getDatabasePath(){
        return context.getDatabasePath(DATABASE_NAME);
    }

    private void insertDataIntoCustomer(SQLiteDatabase db){
        db.execSQL(INSERT_CUSTOMER_TABLE_SQL + CUSTOMER_1_DATA);
        db.execSQL(INSERT_CUSTOMER_TABLE_SQL + CUSTOMER_2_DATA);
        db.execSQL(INSERT_CUSTOMER_TABLE_SQL + CUSTOMER_3_DATA);
        db.execSQL(INSERT_CUSTOMER_TABLE_SQL + CUSTOMER_4_DATA);
        db.execSQL(INSERT_CUSTOMER_TABLE_SQL + CUSTOMER_5_DATA);
        db.execSQL(INSERT_CUSTOMER_TABLE_SQL + CUSTOMER_6_DATA);
        db.execSQL(INSERT_CUSTOMER_TABLE_SQL + CUSTOMER_7_DATA);
    }

    private void insertDataIntoProduct(SQLiteDatabase db){
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_1_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_2_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_3_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_4_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_5_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_6_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_7_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_8_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_9_DATA);
        db.execSQL(INSERT_PRODUCT_TABLE_SQL + PRODUCT_10_DATA);
    }

    private void insertDataIntoTransactions(SQLiteDatabase db){
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_1_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_2_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_3_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_4_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_5_DATA);
      /**  db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_6_DATA);**/
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_7_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_8_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_9_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_10_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_11_DATA);
        db.execSQL(INSERT_TRANSACTION_TABLE_SQL + TRANSACTION_12_DATA);
    }

    private void insertDataIntoOrderAndOrderEntry(SQLiteDatabase db){
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_1_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_1_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_2_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_2_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_3_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_3_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_4_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_4_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_5_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_5_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_6_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_6_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_7_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_7_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_8_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_8_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_9_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_9_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_10_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_10_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_11_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_11_DATA);
        db.execSQL(INSERT_ORDER_TABLE_SQL + ORDER_12_DATA);
        db.execSQL(INSERT_ORDER_ENTRY_SQL + ORDER_ENTRY_12_DATA);
    }
}
