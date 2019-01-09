package hotel.hotelapp;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import entities.Department;

public class BackgroundTask {

    private static final String TAG = BackgroundTask.class.getName();

    Context context;
    ArrayList<Department> departmentsArrayList = new ArrayList<>();
    String URL_DEP = URLs.URL_DEPARTMENTS;

    public BackgroundTask(Context context){
        this.context = context;
    }
    public ArrayList<Department> getList(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_DEP, (String)null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    int count = 0;
                    while (count<response.length()){
                        try {
                            JSONObject jsonObject = response.getJSONObject(count);
                            Department department = new Department(jsonObject.getLong("id"), jsonObject.getString("name"));
                            departmentsArrayList.add(department);
                            count++;

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error...",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(context).addToRequesteQueue(jsonArrayRequest);

        Log.i(TAG, departmentsArrayList.toString());
        return departmentsArrayList;
    }
}
