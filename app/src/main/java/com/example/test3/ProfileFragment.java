//IM/2020/025 - Naduni Rabel
package com.example.test3;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.test3.Model.User;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    //IM/2020/025 - Naduni Rabel
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView firstNameTextView;
    private TextView logout;

    private TextView newpd;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }
    //IM/2020/025 - Naduni Rabel
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  IM/2020/025 - Naduni Rabel
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI elements
        firstNameTextView = view.findViewById(R.id.name);
        logout = view.findViewById(R.id.logOutText);
        newpd = view.findViewById(R.id.newpwd);
        userClass userClassObj = userClass.getInstance();
        int loggedInUserId = userClassObj.getLoggedInUserId();//get logged user
        if (loggedInUserId != -1) {
            User user = userClassObj.getUserById(loggedInUserId);
            if (user != null) {
                String firstName = user.getFirstName();//get username to a variable
                firstNameTextView.setText(firstName);//Set user name in Text view
            }
        }
        //IM/2020/025 - Naduni Rabel
        logout.setOnClickListener(new View.OnClickListener() {//Logout btn listener
            @Override
            public void onClick(View v) {
                goToLoginPage();
            }
        });
        //IM/2020/025 - Naduni Rabel
        newpd.setOnClickListener(new View.OnClickListener() { // New pwd btn listener
            @Override
            public void onClick(View v) {
                goToNewPasswordPage();}
        });
        return view;
        //  IM/2020/025 - Naduni Rabel
    }

    //  IM/2020/025 - Naduni Rabel
    public void goToLoginPage(){//Log out btn : Go back to login page
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    //  IM/2020/025 - Naduni Rabel
    public void goToNewPasswordPage(){// New password btn: Go to new password btn
        Intent intent = new Intent(getActivity(), NewPasswordActivity.class);
        startActivity(intent);
    }
}
//IM/2020/025 - Naduni Rabel