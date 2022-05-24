package ir.mehrdad32.mvvmsample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public UserViewModel() {
        users = new MutableLiveData<>();
        List<User> list = new ArrayList<>() ;
        users.setValue(list);
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
        List<User> userList = new ArrayList<>();
        users.setValue(userList);
    }

    public void addUser() {
        List<User> userList = users.getValue();
        if(userList== null) {
            loadUsers();
            userList = users.getValue();
        }
        userList.add(new User(0, "A", "B"));
        users.setValue(userList);
    }
}
