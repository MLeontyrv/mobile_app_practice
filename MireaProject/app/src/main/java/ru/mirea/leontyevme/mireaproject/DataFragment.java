package ru.mirea.leontyevme.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        TextView InfoTextView = view.findViewById(R.id.textView);
        String Info = "Уникальная информация о уязвимости ms17-010...\n\n" +
                "Уязвимости, описанные в MS Security Bulletin под номером 17-010, прогремели в 2017 году на весь мир. В списке уязвимых ОС присутствуют: Windows Vista, 7, 8.1, 10; Windows Server 2008, 2012, 2016- то есть вполне современные версии ОС. MS17-010 включает в себя несколько различных программных ошибок в Windows SMB Server, точнее в SMBv1.\n\n" +
                "SMB - server message block - протокол, позволяющий компьютерам в локальной сети получать доступ к совместным ресурсам (shared resources), таким как файлы и принтеры, видеть друг друга в локальной сети и т.п.\n\n" +
                "Возможности, которые получает атакующий, почти безграничны! В случае удачной атаки хакер получает возможность исполнения на хосте-жертве произвольного кода с привилегиями System - наивысшими возможными в Windows.\n\n";

        InfoTextView.setText(Info);
        return view;
    }
}