package pl.wspa.student.wspacitibikenyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public abstract class ConnectionDialog extends DialogFragment {
    private boolean clickResult=true;
    protected CheckBox checkBox;
    protected TextView message;
    View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        onOkClick(dialog,id);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onCancelClick(dialog,id);
                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.dialog_connection, null);
        checkBox=(CheckBox)view.findViewById(R.id.dci_checkBox);
        message =(TextView)view.findViewById(R.id.dci_message);
        return builder.create();
    }
    public abstract void onOkClick(DialogInterface dialog, int id);
    public void onCancelClick(DialogInterface dialog, int id){
        clickResult =false;
    }

    public boolean getResult() {
        return clickResult;
    }
}
