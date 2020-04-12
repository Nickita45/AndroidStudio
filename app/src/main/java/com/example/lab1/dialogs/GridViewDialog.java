package com.example.lab1.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class GridViewDialog extends DialogFragment {
    private int choosenNumber;
    public GridViewDialog(int number)
    {
        choosenNumber=number;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Диалог для числа");
        builder.setMessage("Вы выбрали число "+choosenNumber);
        builder.setPositiveButton("Ок",null);
        //builder.setNeutralButton("Neutral",null);
        //builder.setNegativeButton("Нет",null);

           /* Обратите внимание на метод getView()
   builder.setView(LayoutInflater.from(getActivity())
           .inflate(R.layout.dialog_layout, false));
   */

        return builder.create();
    }
}
