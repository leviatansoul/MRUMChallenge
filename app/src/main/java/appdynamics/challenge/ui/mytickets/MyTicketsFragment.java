package appdynamics.challenge.ui.mytickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import appdynamics.challenge.R;

public class MyTicketsFragment extends Fragment {

    private MyTicketsViewModel myTicketsViewModel;
    private ListView ticketList;
    private ValidatedTicketAdapter validatedTicketAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myTicketsViewModel =
                ViewModelProviders.of(this).get(MyTicketsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mytickets, container, false);

        ticketList = (ListView) root.findViewById(R.id.ticketList);
        validatedTicketAdapter = new ValidatedTicketAdapter(root.getContext());
        ticketList.setAdapter(validatedTicketAdapter);


        final TextView textView_company = root.findViewById(R.id.text_company);
        myTicketsViewModel.getCompany().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView_company.setText(s);
            }
        });

        final TextView textView_username = root.findViewById(R.id.text_username);
        myTicketsViewModel.getUsername().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView_username.setText(s);
            }
        });

        return root;
    }
}