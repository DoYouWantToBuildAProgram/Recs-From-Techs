package com.example.erica.recsfromtechs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.erica.recsfromtechs.dummy.State;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyActivityTest extends AppCompatActivity  {
    /** this is our volley queue for holding our REST requests.  Volley will create the necessary threading to
     * handle the requests for us in the background.
     */
    private RequestQueue queue;

    /**
     * hold whatever result comes back from the REST call.  Not required in this case really, but comes
     * in handy for debugging.
     */
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_activity_test);

        //create the queue.  You should only have one of these in your entire application, so you might
        //need to create a singleton to hold it if you are making REST requests throughout the app.
        queue = Volley.newRequestQueue(this);
    }

    /**
     * Button handler for getting the state code
     * This method sets up a REST request, and gets back a
     * JSON object from the request.  Each request will have a unique format for the coded objects.
     * Refer to the documentation.   For our REST call, the documented format is below.
     * The JSON is formatted as:
     *
     * {
     "RestResponse" : {
     "messages" : [ "More webservices are available at http://www.groupkt.com/post/f2129b88/services.htm", "Total [249] records found." ],
     "result" : [ {
     "name" : "Afghanistan",
     "alpha2_code" : "AF",
     "alpha3_code" : "AFG"
     }, {
     "name" : "Åland Islands",
     "alpha2_code" : "AX",
     "alpha3_code" : "ALA"
     }, {
     "name" : "Albania",
     "alpha2_code" : "AL",
     "alpha3_code" : "ALB"
     }, etc.

     *  We do not care about the messages part in this example.  Our state data is in the result array of states.
     *
     * @param view reference to the button pressed
     */
    public void onGetMoviePress(View view) {

        //this is the URL for our REST service
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists.json";

        /*
            We expect to get back a JSON response.  Volley also has String responses.
            This is an async call, but all the threading is handled for us in the background
            We just need 2 callback functions.
                onResponse = this is called when the response actually comes back from server
                onError = this is called if there is any error in the response
         */
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        //handle a valid response coming back.  Getting this string mainly for debug
                        response = resp.toString();
                        //printing first 500 chars of the response.  Only want to do this for debug
                        TextView view = (TextView) findViewById(R.id.textView2);
                        view.setText(response.substring(0, 500));

                        //Now we parse the information.  Looking at the format, everything encapsulated in RestResponse object
                        JSONObject obj1 = null;
                        try {
                            obj1 = resp.getJSONObject("RestResponse");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        assert obj1 != null;
                        //From that object, we extract the array of actual data labeled result
                        JSONArray array = obj1.optJSONArray("result");
                        ArrayList<State> states = new ArrayList<>();
                        for(int i=0; i < array.length(); i++) {

                            try {
                                //for each array element, we have to create an object
                                JSONObject jsonObject = array.getJSONObject(i);
                                State s = new State();
                                assert jsonObject != null;
                                s.setName(jsonObject.optString("title"));
                                //s.setA2Code(jsonObject.optString("alpha2_code"));
                                //s.setA3Code(jsonObject.optString("alpha3_code"));
                                //save the object for later
                                states.add(s);


                            } catch (JSONException e) {
                                Log.d("VolleyApp", "Failed to get JSON object");
                                e.printStackTrace();
                            }
                        }
                        //once we have all data, then go to list screen
                        changeView(states);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response = "JSon Request Failed!!";
                        //show error on phone
                        TextView view = (TextView) findViewById(R.id.textView2);
                        view.setText(response);
                    }
                });
        //this actually queues up the async response with Volley
        queue.add(jsObjRequest);
    }

    /**
     * This method changes to our new list view of the states, but we have to pass the
     * state array into the intent so the new screen gets the data.
     *
     * @param states the list of state objects we created from the JSon response
     */
    private void changeView(ArrayList<State> states) {
        Intent intent = new Intent(this, ItemListActivity.class);
        //this is where we save the info.  note the State object must be Serializable
        intent.putExtra("states", states);
        startActivity(intent);
    }


}
