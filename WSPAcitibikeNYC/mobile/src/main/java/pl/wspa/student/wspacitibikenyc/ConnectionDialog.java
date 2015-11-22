package pl.wspa.student.wspacitibikenyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by Karolina i Daniel on 2015-11-22.
 */
public abstract class ConnectionDialog extends DialogFragment {
    private boolean clickResult=true;

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
        return builder.create();
    }
    public abstract void onOkClick(DialogInterface dialog, int id);
    public void onCancelClick(DialogInterface dialog, int id){
        clickResult =false;
        ConnectionDialog.this.getDialog().cancel();
    }

    public boolean getResult() {
        return clickResult;
    }
}
