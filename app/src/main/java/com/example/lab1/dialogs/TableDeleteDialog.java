package com.example.lab1.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class TableDeleteDialog extends DialogFragment {
    private int id=-1;
    private int position;
    public TableDeleteDialog(int position ) {
        this.position=position;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Удаление");
        builder.setMessage("Вы точно хотите удалить? ");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                id=position;
            }
        });

        //builder.setNeutralButton("Neutral",null);
        builder.setNegativeButton("Нет",null);

           /* Обратите внимание на метод getView()
   builder.setView(LayoutInflater.from(getActivity())
           .inflate(R.layout.dialog_layout, false));
   */

        return builder.create();
    }
    public int getDeletedItem(){
        return id;
    }


}
