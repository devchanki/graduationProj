package com.chanki.tmi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link uploadBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link uploadBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class uploadBoardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private AlertDialog uploadDialog;

    // TODO: Rename and change types of parameters
    private String idParam;
    private String majorParam;
    private String nameParam;

    private OnFragmentInteractionListener mListener;

    public uploadBoardFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment uploadBoardFragment.
     */
     //TODO: Rename and change types and number of parameters
    public static uploadBoardFragment newInstance(String param1, String param2, String param3) {
        uploadBoardFragment fragment = new uploadBoardFragment();
        Bundle args = new Bundle();
        args.putString("id", param1);
        args.putString("name", param2);
        args.putString("major", param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idParam = getArguments().getString("id");
            nameParam = getArguments().getString("name");
            majorParam = getArguments().getString("major");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_upload_board, container, false);

        final TextView title = (TextView) v.findViewById(R.id.boardTitle);
        final TextView content = (TextView) v.findViewById(R.id.boardContent);
        Button uploadButton = (Button) v.findViewById(R.id.boardUploadButton);



        uploadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                String titleString = title.getText().toString();
                String contentString = content.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                uploadDialog = builder.setMessage("업로드에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                uploadDialog.show();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                uploadDialog = builder.setMessage("업로드에 실패했습니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                uploadDialog.show();
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                uploadBoardRequest uploadBoard = new uploadBoardRequest(idParam, nameParam, majorParam, titleString, contentString, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(uploadBoard);
            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
