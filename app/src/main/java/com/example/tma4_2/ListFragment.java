package com.example.tma4_2;

import static com.example.tma4_2.MainActivity.dbExecutor;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {
    private MyItemRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            adapter = new MyItemRecyclerViewAdapter(new ArrayList<Student>(),getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            for (int i = 0; i < 10 ; i++) {
                Student student = new Student((1L+i) ,"tony",(i+1),"chinese");
                dbExecutor.execute(() -> {
                    ((MainActivity)getActivity()).studentDao.insert(student);
                });

            }
            info();


            recyclerView.setAdapter(adapter);
            adapter.setListner(new MyItemRecyclerViewAdapter.MyListner() {
                @Override
                public void getItemId(Student student) {
                    dbExecutor.execute(() -> {
                        ((MainActivity)getActivity()).studentDao.delete(student);
                    });

                    info();

                }
            });
        }
        return view;
    }

    public void info() {
        dbExecutor.execute(() -> {
            List<Student> studentList = ((MainActivity)getActivity()).studentDao.getAll();
            getActivity().runOnUiThread(() -> adapter.update(studentList) );
        });
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_image, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_to_addFragment ){
                AddFragment addFragment = new AddFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, addFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }else  if (id == R.id.action_to_aboutFragment){
                AboutFragment aboutFragment = new AboutFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, aboutFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }

        return super.onOptionsItemSelected(item);
    }








}