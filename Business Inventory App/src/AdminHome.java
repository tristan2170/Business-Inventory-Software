package edu.qc.seclass.fim;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminHome extends AppCompatActivity {

    DBHelper myDb;
    EditText editCategory, editType, editColor, editBrand, editPrice, editSize, editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnViewAllByCategory;
    Button btnViewAllByType;
    Button btnViewUpdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        myDb = new DBHelper(this);

        // Views/buttons that need to be created are in red
        editTextId = (EditText) findViewById(R.id.edit_ID);
        editCategory = (EditText) findViewById(R.id.edit_Category);
        editType = (EditText) findViewById(R.id.edit_Type);
        editColor = (EditText) findViewById(R.id.edit_Color);
        editBrand = (EditText) findViewById(R.id.edit_Brand);
        editPrice = (EditText) findViewById(R.id.edit_Price);
        editSize = (EditText) findViewById(R.id.edit_Size);
        btnAddData = (Button) findViewById(R.id.admin_Add);
        btnViewAll = (Button) findViewById(R.id.admin_ViewAll);
        btnViewAllByCategory = (Button) findViewById(R.id.admin_Category);
        btnViewAllByType = (Button) findViewById(R.id.admin_Type);
        btnDelete = (Button) findViewById(R.id.admin_Delete);
        btnViewUpdate = (Button) findViewById(R.id.admin_Update);


        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAll();
            }
        });

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData();
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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonDelete();
            }
        });
        btnViewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });

    }

    public void buttonDelete() {

        Integer deleted = myDb.deleteData(editTextId.getText().toString());


    }

    public void UpdateData() {

        boolean isUpdated = myDb.UpdateData(editTextId.getText().toString(), editCategory.getText().toString(),
                editType.getText().toString(), editColor.getText().toString(), editBrand.getText().toString(), editPrice.getText().toString(),
                editSize.getText().toString());

        if (isUpdated = true)
            Toast.makeText(AdminHome.this, "Data updated", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(AdminHome.this, "Data not updated", Toast.LENGTH_LONG).show();


    }

    public void AddData() {


        boolean isInserted = myDb.insertData(editCategory.getText().toString(),
                editType.getText().toString(), editColor.getText().toString(),
                editBrand.getText().toString(), editPrice.getText().toString(),
                editSize.getText().toString());
        if (isInserted = true)
            Toast.makeText(AdminHome.this, "Data stored", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(AdminHome.this, "Data not stored", Toast.LENGTH_LONG).show();


    }


    public void viewAll() {

        Cursor res = myDb.getAll();
        if (res.getCount() == 0) {
            Toast.makeText(AdminHome.this, "Nothing here to show! ", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id: " + res.getString(0) + "\n");
            buffer.append("Category: " + res.getString(1) + "\n");
            buffer.append("Type: " + res.getString(2) + "\n");
            buffer.append("Color: " + res.getString(3) + "\n");
            buffer.append("Brand: " + res.getString(4) + "\n");
            buffer.append("Price: " + res.getString(5) + "\n");
            buffer.append("Size: " + res.getString(6) + "\n");

        }
        display("Products: ", buffer.toString());


    }

    public void ViewAllByCategory() {

        Cursor res = myDb.getAllByCategory();
        if (res.getCount() == 0) {
            Toast.makeText(AdminHome.this, "Nothing here to show! ", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Category: " + res.getString(1) + "\n");

        }
        display("Products: ", buffer.toString());

    }


    public void ViewAllByType() {

        Cursor res = myDb.getAllByType();
        if (res.getCount() == 0) {
            Toast.makeText(AdminHome.this, "Nothing here to show! ", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Type: " + res.getString(2) + "\n");

        }
        display("Products: ", buffer.toString());

    }

    public void display(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminHome.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}