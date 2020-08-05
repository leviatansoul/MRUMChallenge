package appdynamics.challenge.ui.mytickets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import appdynamics.challenge.UserData;

public class MyTicketsViewModel extends ViewModel {

    private MutableLiveData<String> mUsername = new MutableLiveData<>();
    private MutableLiveData<String> mCompany = new MutableLiveData<>();;


    public MyTicketsViewModel() {

    }

    public LiveData<String> getUsername() {
        mUsername.setValue(UserData.username);
        return mUsername;
    }
    public LiveData<String> getCompany() {
        mCompany.setValue(UserData.company);
        return mCompany;
    }
}