package com.mah.ag0071.assigment1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by User on 2017-09-15.
 */

public class MainController {

    private MainActivity mainActivity;
    private OverviewFragment overviewFragment;
    private AddIncomeFragment addIncomeFragment;
    private IncomeFragment incomeFragment;
    private ExpenditureFragment expenditureFragment;
    private Parcelable parcelable;
    private User activeUser;
    private IncomeDBHelper incomeDBHelper;
    private ExpensesDBHelper expensesDBHelper;
    private Incomes[] incomes,sortedIncomes;
    private Expenditure[] expenditures,sortedExpend;
    private int saldo,expSaldo,incSaldo;
    private String[] addFragmentInfo,incomeFragmentInfo,exoenFragmentInfo;
    private boolean addFragmentInfoFlag,incomeFragmentFlag,expenFragmentFlag;
    private int fragmentValue;



    public MainController(MainActivity activity,Intent intent) {
        this.mainActivity = activity;

        parcelable = intent.getParcelableExtra("activeUser");
        activeUser = (User) parcelable;

        overviewFragment = new OverviewFragment();
        overviewFragment.setMainController(this);
        incomeFragment = new IncomeFragment();
        incomeFragment.setController(this);
        addIncomeFragment = new AddIncomeFragment();
        addIncomeFragment.setController(this);
        expenditureFragment = new ExpenditureFragment();
        expenditureFragment.setMainController(this);

        incomeDBHelper = new IncomeDBHelper(this.mainActivity);
        expensesDBHelper = new ExpensesDBHelper(this.mainActivity);


        //mainActivity.setFragment(overviewFragment,false);
        fragmentValue = 1;

        //clearDBs();
        getDbInformation();

    }


    public String[] updateOverView(){
        Log.v("HELP","Active user " + activeUser.getFirstName() + activeUser.getSurName());
        Log.v("HELP","Saldos " + saldo + " " + incSaldo + " " + expSaldo);
        //overviewFragment.setText(activeUser.getFirstName(),activeUser.getSurName(),String.valueOf(saldo),
                //String.valueOf(incSaldo),String.valueOf(expSaldo));
        String[] array  = new String[5];
        array[0] = activeUser.getFirstName();
        array[1] = activeUser.getSurName();
        array[2] = String.valueOf(saldo);
        array[3] = String.valueOf(incSaldo);
        array[4] = String.valueOf(expSaldo);
        return array;
    }


    public void swapToShowExpenses() {
        fragmentValue = 3;
        expenditureFragment.setExpenditure(expenditures);
        mainActivity.setFragment(expenditureFragment,true);
    }

    public void swapToShowAdd() {
        fragmentValue = 4;
        mainActivity.setFragment(addIncomeFragment,true);
    }

    public void swapToShowIncome() {
        fragmentValue = 2;
        incomeFragment.setIncomes(incomes);
        mainActivity.setFragment(incomeFragment,true);
    }

    public void swapToShowOverview(){
        fragmentValue = 1;
        overviewFragment.setText(activeUser.getFirstName(),activeUser.getSurName(),String.valueOf(saldo),
                String.valueOf(incSaldo),String.valueOf(expSaldo));
        mainActivity.setFragment(overviewFragment,false);
    }

    public void addTransaction(int id,String date,String title,String category,String amount) {
        Log.v("Transaction",date + title + category + amount);
        if (id == R.id.raBtnIncome){
            addIncome(date,title,category,amount);
            //updateIncomeValues();
            this.swapToShowOverview();
        }else if(id == R.id.raBtnExpenses){
            addExpen(date,title,category,amount);
            //updateExpensValues();
            this.swapToShowOverview();
        }else{
            //Something wrong
        }
        Log.v("SALDO","Saldo är direkt efter update: " + saldo);
        getDbInformation();
    }

    public void addIncome(String date,String title,String categry,String amount){
        if (date.equals(R.string.add_btndate) || title.equals("") || categry.equals("") || amount.equals("")){
            Toast toast = Toast.makeText(mainActivity.getApplicationContext(),R.string.Error_Add,Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Incomes incomes = new Incomes(date,Integer.parseInt(amount),title,categry,activeUser.getUserName());
            addIncomeToDB(incomes);
        }
    }

    public void addExpen(String date,String title,String category,String amount){
        if (date.equals(R.string.add_btndate) || title.equals("") || category.equals("") || amount.equals("")){
            Toast toast = Toast.makeText(mainActivity.getApplicationContext(),R.string.Error_Add,Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Expenditure expenditure = new Expenditure(activeUser.getUserName(),date,
                    category,title,Integer.parseInt(amount));
            addExpenToDB(expenditure);
        }
    }

    private void addExpenToDB(Expenditure expenditure){
        SQLiteDatabase db = expensesDBHelper.getWritableDatabase();
        //expensesDBHelper.onUpgrade(db,1,2);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpensesDBHelper.COLUMN_USERNAME,expenditure.getUsername());
        contentValues.put(ExpensesDBHelper.COLUMN_TITLE,expenditure.getTitle());
        contentValues.put(ExpensesDBHelper.COLUMN_CATEGORY,expenditure.getCategory());
        contentValues.put(ExpensesDBHelper.COLUMN_DATE,expenditure.getDate());
        contentValues.put(ExpensesDBHelper.COLUMN_AMOUNT,expenditure.getAmount());
        db.insert(ExpensesDBHelper.TABEL_NAME,"",contentValues);
    }

    private void addIncomeToDB(Incomes incomes) {
        SQLiteDatabase db = incomeDBHelper.getWritableDatabase();
        //incomeDBHelper.onUpgrade(db,1,2);
        ContentValues values = new ContentValues();
        values.put(IncomeDBHelper.COLUMN_USERNAME,incomes.getUsername());
        values.put(IncomeDBHelper.COLUMN_CATEGORY,incomes.getCategory());
        values.put(IncomeDBHelper.COLUMN_DATE,incomes.getDate());
        values.put(IncomeDBHelper.COLUMN_AMOUNT,incomes.getAmount());
        values.put(IncomeDBHelper.COLUMN_TITLE,incomes.getTitle());
        db.insert(IncomeDBHelper.TABEL_NAME,"",values);
    }


    public void saveInfo(Bundle information) {

        information.putInt("CurrentFragment", fragmentValue);
        information.putParcelable("CurrentUser",activeUser);
        information.putParcelableArray("Expenses",expenditures);
        information.putParcelableArray("Incomes",incomes);
        information.putInt("Saldo",saldo);
        information.putInt("IncSaldo",incSaldo);
        information.putInt("ExpSaldo",expSaldo);

        switch (fragmentValue){
            case 2:
                information.putStringArray("incomefragment",incomeFragment.getValues());
                break;
            case 3:
                information.putStringArray("expenfragment",expenditureFragment.getValues());
                break;
            case 4:
                information.putStringArray("addfragment",addIncomeFragment.getValue());
                break;
        }
    }

    public void rescueFragment(Bundle information){

        int fragmentid = information.getInt("CurrentFragment");
        parcelable = information.getParcelable("CurrentUser");
        activeUser = (User) parcelable;
        expenditures = (Expenditure[]) information.getParcelableArray("Expenses");
        expenditureFragment.setExpenditure(expenditures);
        incomes = (Incomes[]) information.getParcelableArray("Incomes");
        incomeFragment.setIncomes(incomes);
        fragmentValue = fragmentid;
        saldo = information.getInt("Saldo");
        expSaldo = information.getInt("ExpSaldo");
        incSaldo = information.getInt("IncSaldo");

        switch (fragmentid){
            case 1:
                Log.v("FRAGMENT","Fragment overview");
                mainActivity.setFragment(overviewFragment,false);
                break;
            case 2:
                Log.v("FRAGMENT","Fragment Income");
                incomeFragmentInfo = information.getStringArray("incomefragment");
                incomeFragmentFlag = true;
                mainActivity.setFragment(incomeFragment,true);
                break;
            case 3:
                Log.v("FRAGMENT","Fragment expenditure");
                exoenFragmentInfo = information.getStringArray("expenfragment");
                expenFragmentFlag = true;
                mainActivity.setFragment(expenditureFragment,true);
                break;
            case 4:
                addFragmentInfo = information.getStringArray("addfragment");
                addFragmentInfoFlag = true;
                mainActivity.setFragment(addIncomeFragment,true);
                break;
        }

    }

    public void getDbInformation() {
        updateIncomeValues();
        updateExpensValues();
        saldo = incSaldo - expSaldo;
        Log.v("SALDO","Saldo är: " + saldo);
        Log.v("TESTING","Income " + incomes.length);
        Log.v("TESTING","Exp " + expenditures.length);
    }


    private void updateExpensValues() {
        int titleIndex,usernameIndex,categoryIndex,amountIndex,dateIndex,idIndex;
        SQLiteDatabase db = expensesDBHelper.getReadableDatabase();
        /*
        Cursor cursor = db.rawQuery("Select * FROM " + ExpensesDBHelper.TABEL_NAME +
                    " WHERE " + ExpensesDBHelper.COLUMN_USERNAME + " = ?",new String[]{activeUser.getFirstName()});
                    */
        Cursor cursor = db.rawQuery("Select * FROM " + ExpensesDBHelper.TABEL_NAME +
                " WHERE " + ExpensesDBHelper.COLUMN_USERNAME + " = ?",new String[]{activeUser.getUserName()});

        expenditures = new Expenditure[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_TITLE);
        usernameIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_USERNAME);
        categoryIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_CATEGORY);
        amountIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_AMOUNT);
        dateIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_DATE);
        idIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_ID);

        expSaldo = 0;
        if (cursor != null && cursor.moveToFirst()){
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                expenditures[i] = new Expenditure(cursor.getInt(idIndex),cursor.getString(usernameIndex),
                        cursor.getString(titleIndex),cursor.getString(categoryIndex),
                        cursor.getString(dateIndex),cursor.getInt(amountIndex));
                Log.v("Hämtade exp","" + expenditures.toString());
                expSaldo = expSaldo + expenditures[i].getAmount();
            }
        }else{
            Log.v("Content Values","Fel med att hämta");
        }


    }

    private void updateIncomeValues() {
        int titleIndex,usernameIndex,categoryIndex,amountIndex,dateIndex,idIndex,IncomeLength;
        SQLiteDatabase db = incomeDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM " + IncomeDBHelper.TABEL_NAME +
                " WHERE " + IncomeDBHelper.COLUMN_USERNAME + " = ? ", new String[]{activeUser.getUserName()});
        int index = cursor.getCount();
        incomes = new Incomes[index];


        titleIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_TITLE);
        usernameIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_USERNAME);
        categoryIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_CATEGORY);
        amountIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_AMOUNT);
        dateIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_DATE);
        idIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_ID);

        incSaldo = 0;
        for (int i = 0; i < incomes.length; i++) {
            cursor.moveToPosition(i);
            incomes[i] = new Incomes(cursor.getInt(idIndex),cursor.getString(usernameIndex),
                    cursor.getString(categoryIndex),cursor.getString(dateIndex),
                    cursor.getString(titleIndex),cursor.getInt(amountIndex));
            incSaldo = incSaldo + incomes[i].getAmount();
        }

    }



    private void clearDBs(){
        SQLiteDatabase db = incomeDBHelper.getWritableDatabase();
        db.delete(IncomeDBHelper.TABEL_NAME,null,null);
        db = expensesDBHelper.getWritableDatabase();
        db.delete(ExpensesDBHelper.TABEL_NAME,null,null);
    }


    public void setStartFragment() {
        fragmentValue = 1;
        mainActivity.setFragment(overviewFragment,false);
    }

    public void showIncomeDate(String startDate,String endDate){
        int titleIndex,usernameIndex,categoryIndex,amountIndex,dateIndex,idIndex;
        SQLiteDatabase db = incomeDBHelper.getReadableDatabase();
        String query = "Select * FROM " + IncomeDBHelper.TABEL_NAME + " WHERE " +
                IncomeDBHelper.COLUMN_USERNAME + " = ?"+ " AND " + IncomeDBHelper.COLUMN_DATE +
                " BETWEEN " + " ? " + " AND " + " ? ";
        /*
        Cursor cursor = db.rawQuery("Select * FROM " + IncomeDBHelper.TABEL_NAME +
                    " WHERE " + IncomeDBHelper.COLUMN_DATE + " BETWEEN ? " + " AND ? "
                    ,new String[]{startDate,endDate} );
        */
        Cursor cursor = db.rawQuery(query,new String[]{activeUser.getUserName(),startDate,endDate});

        sortedIncomes = new Incomes[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_TITLE);
        usernameIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_USERNAME);
        categoryIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_CATEGORY);
        amountIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_AMOUNT);
        dateIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_DATE);
        idIndex = cursor.getColumnIndex(IncomeDBHelper.COLUMN_ID);

        for (int i = 0; i < sortedIncomes.length; i++) {
            cursor.moveToPosition(i);
            sortedIncomes[i] = new Incomes(cursor.getInt(idIndex),cursor.getString(usernameIndex),
                    cursor.getString(categoryIndex),cursor.getString(dateIndex),
                    cursor.getString(titleIndex),cursor.getInt(amountIndex));

        }

        incomeFragment.changeAdapter(sortedIncomes);

    }

    public void showExpDate(String startDate, String endDate) {
        int titleIndex,usernameIndex,categoryIndex,amountIndex,dateIndex,idIndex;
        SQLiteDatabase db = expensesDBHelper.getReadableDatabase();
        String query = "Select * FROM " + ExpensesDBHelper.TABEL_NAME + " WHERE " +
                ExpensesDBHelper.COLUMN_USERNAME + " = ?"+ " AND " + ExpensesDBHelper.COLUMN_DATE +
                " BETWEEN " + " ? " + " AND " + " ? ";

        Cursor cursor = db.rawQuery(query
                ,new String[]{activeUser.getUserName(),startDate,endDate} );

        sortedExpend = new Expenditure[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_TITLE);
        usernameIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_USERNAME);
        categoryIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_CATEGORY);
        amountIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_AMOUNT);
        dateIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_DATE);
        idIndex = cursor.getColumnIndex(ExpensesDBHelper.COLUMN_ID);

        for (int i = 0; i < sortedExpend.length; i++) {
            cursor.moveToPosition(i);
            sortedExpend[i] = new Expenditure(cursor.getInt(idIndex),
                    cursor.getString(usernameIndex), cursor.getString(titleIndex),
                    cursor.getString(categoryIndex), cursor.getString(dateIndex),
                    cursor.getInt(amountIndex));
        }

        expenditureFragment.changeAdapter(sortedExpend);

    }

    public void getAddValues() {
        if (addFragmentInfoFlag){
            addIncomeFragment.setValues(addFragmentInfo);
            addFragmentInfoFlag = false;
            addIncomeFragment.updateComponents();
        }

    }

    public void setFragmentValue() {
        Log.v("Hello","FragmentValue: " + fragmentValue);
        if (fragmentValue == 1){
            mainActivity.finish();
        }else {
            fragmentValue = 1;
        }

    }

    public Incomes[] incomeValues() {
        if (incomeFragmentFlag){
            incomeFragmentFlag = false;
            incomeFragment.updateComponents(incomeFragmentInfo);
        }
        return incomes;
    }


    public Expenditure[] expenValues(){
        Log.v("Frag","Expenditures in expenValues: " + expenditures.length);
        if (expenFragmentFlag){
            expenFragmentFlag = false;
            expenditureFragment.updateComponents(exoenFragmentInfo);
        }
        return expenditures;
    }

}
