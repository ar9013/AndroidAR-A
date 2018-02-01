package com.yue.ar.suite;

import android.os.FileObserver;
import android.util.Log;

import org.apache.commons.io.FilenameUtils;

import java.io.File;


import static android.R.attr.path;

/**
 * Created by luokangyu on 2017/11/14.
 */

public class FolderObserver extends FileObserver {

    private static final String TAG = "FolderObserver";

    String rootPath, markerImageFilePath, keypointPath, descriptorPath;

    private final String[] okFileExtensions = new String[] {"jpg", "png"};

    static final int mask = (FileObserver.CREATE |
            FileObserver.DELETE |
            FileObserver.DELETE_SELF |
            FileObserver.MODIFY |
            FileObserver.MOVED_FROM |
            FileObserver.MOVED_TO |
            FileObserver.DELETE_SELF);

    ARManager arManager = null;

    public FolderObserver(String root) {
        super(root, mask);

        if (!root.endsWith(File.separator)) {
            root += File.separator;
        }

        rootPath = root;

        arManager = new ARManager();
    }

    @Override
    public void onEvent(int event, String filePath) {
        event &= FileObserver.ALL_EVENTS;

        switch (event) {
            case FileObserver.CREATE:
                Log.d(TAG, "CREATE:" + rootPath + filePath);

                // when detect the new  marker image ,make Feature (keypoint and descriptor), save in linkedList.
                File markerImage = new File(rootPath+filePath);

                for(String extension:okFileExtensions) {
                    if (markerImage.getName().toLowerCase().endsWith(extension)) {
                        // use core ARManager class make marker feature
                        String markerId = FilenameUtils.removeExtension(markerImage.getName());
                        Log.d(TAG, "markerId: "+markerId);

                        arManager.makeFeature(markerId,markerImage);

                    }
                }
                break;

            case FileObserver.DELETE:
                Log.d(TAG, "DELETE:" + rootPath + filePath);
                break;

            case FileObserver.MOVED_FROM:
                Log.d(TAG, "MOVED_FROM:" + rootPath + filePath);
                break;

            case FileObserver.MOVED_TO:
                Log.d(TAG, "MOVED_TO:" + filePath);
                break;

//            case FileObserver.DELETE_SELF:
//                Log.d(TAG, "DELETE_SELF:" + rootPath + filePath);
//                break;

//            case FileObserver.MODIFY:
//                Log.d(TAG, "MODIFY:" + rootPath + filePath);
//                break;

//            case FileObserver.MOVE_SELF:
//                Log.d(TAG, "MOVE_SELF:" + filePath);
//                break;

            default:
                // just ignore
                break;
        }
    }

    public void close() {
        super.finalize();
    }

}
