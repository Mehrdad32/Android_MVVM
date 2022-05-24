# Android_MVVM
Best way to implement MVVM design pattern in Android

## Steps:

### 1- Add view bingind feature in module gradle:
```
android {
...

  buildFeatures {
    viewBinding true
  }
}
```

### 2- Add this dependecies in module gradle:
```
implementation 'androidx.annotation:annotation:1.3.0'
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.4.1'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1'
```

### 3- Create model class:
**Note:** This step is optional and you can skip and declare propreties in ViewModel (next step).
```Java
public class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
```

### 4- Create ViewModel class:
```Java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
        
    }
}
```

## 5- Add Binding property to Activity:
```Java
public class MainActivity extends AppCompatActivity {

    //Create binding proprety here:
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set Binding:
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        ...

        });
    }
}
```

## 6- Use from ViewModel in Activity
Create a ViewModel the first time the system calls an activity's onCreate() method.
Re-created activities receive the same MyViewModel instance created by the first activity.
```Java
public class MyActivity extends AppCompatActivity {
    private UserViewModel model;
    ...
    
    public void onCreate(Bundle savedInstanceState) {
        ...

        model = new ViewModelProvider(this).get(UserViewModel.class);
        model.getUsers().observe(this, users -> {
            // update UI
        });
    }
}
```

## Another example: 
https://medium.com/androiddevelopers/viewmodels-a-simple-example-ed5ac416317e
