package hotel.hotelapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import entities.Department;

public class DepartmentActivity extends AppCompatActivity {
    private static final String TAG = DepartmentActivity.class.getName();

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Department> departmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_departments);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        BackgroundTask backgroundTask = new BackgroundTask(DepartmentActivity.this);
        departmentArrayList = backgroundTask.getList();
        Log.i(TAG, departmentArrayList.toString());
        adapter = new RecyclerAdapter(departmentArrayList);
        recyclerView.setAdapter(adapter);

    }
}
