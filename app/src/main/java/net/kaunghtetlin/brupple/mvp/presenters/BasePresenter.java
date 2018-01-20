package net.kaunghtetlin.brupple.mvp.presenters;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */

public abstract class BasePresenter<T> {

    protected T mView;

    public void onCreate(T view){
        mView=view;
    }

    public abstract void onStart();

    public void onResume(){}

    public abstract void onStop();

    public void onPause(){}

    public void onDestroy(){}
}
