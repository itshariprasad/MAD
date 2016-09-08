package edu.iit.hawk.vkumar17.madprojectteam13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NowPlayingActivity extends Activity {
	private ListView lvMovies;
	private NowPlayingMoviesAdapter adapterMovies;
	private NowPlayingClient client;
	public static final String MOVIE_DETAIL_KEY = "movie";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_now_playing);
		lvMovies = (ListView) findViewById(R.id.lvMovies);
		ArrayList<NowPlayingMovie> aMovies = new ArrayList<NowPlayingMovie>();
		adapterMovies = new NowPlayingMoviesAdapter(this, aMovies);
		lvMovies.setAdapter(adapterMovies);
		fetchBoxOfficeMovies();
		setupMovieSelectedListener();
	}

	private void fetchBoxOfficeMovies() {
		client = new NowPlayingClient();
		client.getBoxOfficeMovies(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int code, JSONObject body) {
				JSONArray items = null;
				try {
					items = body.getJSONArray("movies");
					ArrayList<NowPlayingMovie> movies = NowPlayingMovie.fromJson(items);
					adapterMovies.addAll(movies);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setupMovieSelectedListener() {
		lvMovies.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowId) {
				Intent i = new Intent(NowPlayingActivity.this, NowPlayingDetailActivity.class);
				i.putExtra(MOVIE_DETAIL_KEY, adapterMovies.getItem(position));
				startActivity(i);
			}
		});
	}

}
