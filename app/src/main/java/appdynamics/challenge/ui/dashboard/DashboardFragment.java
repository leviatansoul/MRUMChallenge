package appdynamics.challenge.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;

import appdynamics.challenge.R;
import appdynamics.challenge.ui.detail.DetailActivity;
import appdynamics.challenge.ui.home.HomeFragment;
import appdynamics.challenge.ui.home.HomeViewModel;


public class DashboardFragment extends Fragment {


    private DashboardViewModel dashboardViewModel;
    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final Button scanQR = root.findViewById(R.id.scanQR);
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(root.getContext(), true, false);
                startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST);

            }
        });

        dashboardViewModel.validateTicket().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean ticketValidated) {
                if(ticketValidated){

                    //detailViewModel.insertTicket(type);

                    Toast.makeText(root.getContext(), "Done!", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(root.getContext(), "Fail", Toast.LENGTH_SHORT).show();

                }
            }
        });



        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this.getContext(), "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            dashboardViewModel.validateTicket(barcode.rawValue);
            Toast.makeText(this.getContext(), barcode.rawValue, Toast.LENGTH_SHORT).show();
        }

    }
}