package com.example.tma4_2;

import static com.example.tma4_2.MainActivity.dbExecutor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;


public class AddFragment extends Fragment {
    private Button btnAdd;
    private EditText edtId;
    private EditText edtName;
    private EditText edtMajor;
    private EditText edtYear;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btnAdd = view.findViewById(R.id.btn_add);
        edtId = view.findViewById(R.id.ed_Id);
        edtName = view.findViewById(R.id.ed_Name);
        edtMajor = view.findViewById(R.id.ed_major);
        edtYear = view.findViewById(R.id.ed_year);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtId.getText().toString();
                String name = edtName.getText().toString();
                String major = edtMajor.getText().toString();
                String year = edtYear.getText().toString();

                dbExecutor.execute(() -> {
                    try {
                        ((MainActivity)getActivity()).studentDao.
                                insert(new Student(Long.parseLong(id), name, Integer.parseInt(year), major));

                    }catch (Exception e){
                        e.printStackTrace();
                        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(),
                                        "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT)
                                .show());

                    }
                });

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NavController navController =
                                Navigation.findNavController(requireActivity(),R.id.fragment_container);
                        navController.popBackStack();
                    }
                });

            }
        });




        return view;
    }
}