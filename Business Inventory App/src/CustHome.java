package edu.qc.seclass.fim;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustHome extends AppCompatActivity {

    DBHelper myDb;
    Button btnViewAll;
    Button btnViewAllByCategory;
    Button btnViewAllByType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_home);
        myDb = new DBHelper(this);

        btnViewAll = (Button)findViewById(R.id.button_ViewAll);
        btnViewAllByCategory = (Button)findViewById(R.id.button_Category);
        btnViewAllByType = (Button)findViewById(R.id.button_Type);



        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAll();
            }
        });

        btnViewAllByCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewAllByCategory();
            }
        });

        btnViewAllByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewAllByType();
            }
        });

    }

    public void viewAll(){

        Cursor res = myDb.getAll();
        if(res.getCount() == 0)
        {
            Toast.makeText(CustHome.this, "Nothing here to show! ", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id: " + res.getString(0)+"\n");
            buffer.append("Category: " + res.getString(1)+"\n");
            buffer.append("Type: " + res.getString(2)+"\n");
            buffer.append("Color: " + res.getString(3)+"\n");
            buffer.append("Brand: " + res.getString(4)+"\n");
            buffer.append("Price: " + res.getString(5)+"\n");
            buffer.append("Size: " + res.getString(6)+"\n");

        }
        display("Products: ", buffer.toString());
    }

    public void ViewAllByCategory(){

        Cursor res = myDb.getAllByCategory();
        if(res.getCount() == 0)
        {
            Toast.makeText(CustHome.this, "Nothing here to show! ", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Category: " + res.getString(1)+"\n");

        }
        display("Products: ", buffer.toString());
    }

    public void ViewAllByType(){
        Cursor res = myDb.getAllByType();
        if(res.getCount() == 0)
        {
            Toast.makeText(CustHome.this, "Nothing here to show! ", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Type: " + res.getString(2)+"\n");

        }
        display("Products: ", buffer.toString());
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(CustHome.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}