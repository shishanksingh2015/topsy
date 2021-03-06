package com.moldedbits.android;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.moldedbits.android.dialogs.LoadingDialog;
import com.moldedbits.android.utils.fragment_transaction_handler.FragmentTransactionHandler;
import com.moldedbits.android.R;


/**
 *
 * Created by shishank on 08/01/16.
 */
// TODO: 05/04/16 Add Loader
public class BaseActivity extends AppCompatActivity {

    private FrameLayout mContentFrame;
    public FragmentTransactionHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        mContentFrame = (FrameLayout) findViewById(R.id.base_container);
        handler = new FragmentTransactionHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.setActivity(this);
        handler.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.setActivity(null);
    }

    public FragmentTransactionHandler getHandler() {
        return handler;
    }

    @Override
    public void setContentView(int layoutResID) {
        getLayoutInflater().inflate(layoutResID, mContentFrame, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dismissLoadingDialogWithHandler(LoadingDialog dialog) {
        Message msg
                = getHandler().obtainMessage(FragmentTransactionHandler.LOADING_DIALOG_DISMISS_MSG,
                dialog);
        getHandler().sendMessage(msg);
    }

}
