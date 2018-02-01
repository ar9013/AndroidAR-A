package com.yue.ar.suite;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

public class ARFileService extends Service {

    private static final String TAG = "ARFileService";
    String exStorage = Environment.getExternalStorageDirectory().getAbsolutePath();

    static  String arHomePath, keypointsPath, descriptorsPath, markersPath;
    File arHome,keypoints,descriptors,markers;

    FolderObserver folderObserver = null;

    public ARFileService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // .foldename , to hind the folder.

        arHomePath = exStorage + "/arHome";
        keypointsPath = arHomePath + "/.keypoints";
        descriptorsPath = arHomePath + "/.descriptors";
        markersPath = arHomePath + "/markers";

        arHome = new File(arHomePath);
        keypoints = new File(keypointsPath);
        descriptors = new File(descriptorsPath);
        markers = new File(markersPath);

        folderObserver = new FolderObserver(markersPath);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        checkARHome();
        ckeckMarkers();
        checkKeypoints();
        checkDescriptors();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void checkARHome() {
        Log.d(TAG,"checkARHome");

        if(!arHome.exists()){
            Log.d(TAG,"ARHome folder not exist, make ARHome folder");
            arHome.mkdir();
        }else{
            Log.d(TAG,"ARHome folder is exist");
        }
    }


    private void ckeckMarkers(){
        Log.d(TAG,"ckeckMarkerImages");

        if(markers.exists()){
            Log.d(TAG,"markers folder is exist");
            folderObserver.startWatching();

        }else{
            Log.d(TAG,"markers folder not exist, make markers folder");
            markers.mkdir();
        }
    }

    private void checkKeypoints() {
        Log.d(TAG,"checkKeypoints");

        if(!keypoints.exists()){
            Log.d(TAG,"keypoints folder not exist, make keypoints folder  ");
            keypoints.mkdir();
        }else{
            Log.d(TAG,"keypoints folder is exist");
        }
    }

    private void checkDescriptors() {
        Log.d(TAG,"checkDescriptors");

        if(!descriptors.exists())
        {
            Log.d(TAG,"descriptors folder mot exist,make descriptors folder");
            descriptors.mkdir();

        }else{
            Log.d(TAG,"descriptors folder is exist");

        }
    }
}
