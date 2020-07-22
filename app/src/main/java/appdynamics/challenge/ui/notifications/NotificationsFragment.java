package appdynamics.challenge.ui.notifications;

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
import appdynamics.challenge.ui.home.Item;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private ListView ticketList;
    private ValidatedTicketAdapter validatedTicketAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        ticketList = (ListView) root.findViewById(R.id.ticketList);
        validatedTicketAdapter = new ValidatedTicketAdapter(root.getContext());
        ticketList.setAdapter(validatedTicketAdapter);


        final TextView textView_company = root.findViewById(R.id.text_company);
        notificationsViewModel.getCompany().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView_company.setText(s);
            }
        });

        final TextView textView_username = root.findViewById(R.id.text_username);
        notificationsViewModel.getUsername().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView_username.setText(s);
            }
        });

        return root;
    }
}