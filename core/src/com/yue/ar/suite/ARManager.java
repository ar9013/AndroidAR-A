package com.yue.ar.suite;

import com.badlogic.gdx.Gdx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayDeque;
import java.util.HashMap;

import boofcv.abst.feature.detdesc.DetectDescribePoint;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;


/**
 * Created by luokangyu on 2017/11/14.
 */

public class ARManager {

    private static final String TAG = "ARManager";

    DetectDescribePoint detDesc = null;
    
    public static final int MARERLESS_LBS_AR = 0;
    public static final int MARKERLESS_AR = 1;
    public static final int LBS_AR =2;

   public static HashMap<String , Feature> featureMap = null;
    GrayScaleTask grayScaleTask = null;

    public ARManager() {
        featureMap = new HashMap<String, Feature>();
        grayScaleTask = new GrayScaleTask();

    }

    private void setMode(int mode) {

        switch (mode) {

            case MARERLESS_LBS_AR: {
                detectMarkerlessLBSAR();
            }
            break;

            case MARKERLESS_AR:
            {
                detectMarkerlessAR();
            }
            break;

            case LBS_AR:
            {
                detectLBSAR();
            }
            break;
        }
    }

    /**
     *
     * @param markerId
     * @param markerImage
     * use markerId and markerImage to create Feature object
     * store Feature object in HashMap, with <markerId, feature>
     * pass the set item from HashMap to markerQueue
     */

    void makeFeature(String markerId,File markerImage ){
        Gdx.app.log(TAG,"markerId: "+markerId);
        Gdx.app.log(TAG,"markerImage path: "+markerImage.getAbsolutePath());

        Feature feature = new Feature(markerId, markerImage);

        featureMap.put(markerId,feature);
        grayScaleTask.markerQueue.addLast(feature); //從 HashMap 到 Queue

        // 放到 queue 裡面

        grayScaleTask.run();



    }

    private void detectLBSAR() {
    }

    private void detectMarkerlessAR() {
    }

    private void detectMarkerlessLBSAR() {
        
    }


}
