package appdynamics.challenge.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import appdynamics.challenge.R;
import appdynamics.challenge.UserData;
import appdynamics.challenge.ui.detail.DetailActivity;
import appdynamics.challenge.ui.login.LoginViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private GridView gridView;
    private GridAdapter gridAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = (GridView) root.findViewById(R.id.ticket_gridview);
        gridAdapter = new GridAdapter(root.getContext());
        gridView.setAdapter(gridAdapter);

        Log.d("User in Home is ", ""+ UserData.username);
        Log.d("Commpany in Home is ", ""+UserData.company);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = (Item) gridView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("type", item.getType().name());
                intent.putExtra("price", item.getPrice());
                startActivity(intent);

            }
        });




        return root;
    }
}