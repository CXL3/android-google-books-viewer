package com.pocorusso.holiducodingtask;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of the list of volumes
 * It fetches the volumes information from Google Books API then use the information to
 * populate its RecyclerView's cards.
 */
public class VolumeListFragment extends Fragment {

    private static final String TAG = "VolumeListFragment";
    private List<Volume> mVolumes;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_volume_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rss_item_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mVolumes = new ArrayList<Volume>();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchVolumes();
    }

    private void fetchVolumes() {

        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(), getActivity().getString(R.string.loading_data),
                getActivity().getString(R.string.wait), false, false);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, Constants.API_QUERY, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();

                        //calling method to parse json array
                        parseResponse(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO more error handling here would be nice
                        loading.dismiss();

                        Log.e(TAG, "Failed to get data. " + error.getMessage());
                        error.printStackTrace();
                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //Adding request to the queue
        requestQueue.add(jsonObjectRequest);
    }

    //Parse json response
    //Example request: https://www.googleapis.com/books/v1/volumes/_ojXNuzgHRcC
    private void parseResponse(JSONObject jsonObject) {

        try {
            JSONArray jsonArray = jsonObject.getJSONArray(Constants.TAG_ITEMS);
            for (int j = 0; j < jsonArray.length(); j++) {
                Volume volume = new Volume();
                JSONObject item = (JSONObject) jsonArray.get(j);
                if (item != null) {
                    JSONObject volumeInfo = item.getJSONObject(Constants.TAG_VOLUME_INFO);
                    if (volumeInfo != null) {
                        volume.setTitle(volumeInfo.getString(Constants.TAG_TITLE));

                        JSONObject imageLinks = volumeInfo.getJSONObject(Constants.TAG_IMAGE_LINKS);
                        if (imageLinks != null) {
                            volume.setImageUrl(imageLinks.getString(Constants.TAG_THUMBNAIL));
                        }
                    }
                }
                mVolumes.add(volume);
            }
        } catch (JSONException e) {
            //TODO more error handling would be nice
            e.printStackTrace();
        }

        mRecyclerView.setAdapter(new VolumeCardAdapter(getActivity(), mVolumes));
    }
}


