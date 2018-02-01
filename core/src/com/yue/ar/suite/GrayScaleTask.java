package com.yue.ar.suite;

import com.badlogic.gdx.Gdx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayDeque;
import java.util.HashMap;

import boofcv.core.image.ConvertImage;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;

import static com.yue.ar.suite.ARManager.featureMap;

/**
 * Created by luokangyu on 01/02/2018.
 */

public class GrayScaleTask implements Runnable {
    private static final String TAG = "GrayScaleTask";

     ArrayDeque<Feature> markerQueue = null;
        String markerId = null;

    public GrayScaleTask(){
        markerQueue = new ArrayDeque<Feature>();
    }

    /**
     * get first item ,make gray , then save to the item
     */

    @Override
    public void run() {

        if(markerQueue.size()>0){

                Feature temp = markerQueue.pollFirst();

                markerId = temp.markerId; // 取得 第一項  markerId
                Gdx.app.log(TAG, "markerId :" + markerId);

                BufferedImage srcBuffer = UtilImageIO.loadImage(UtilIO.pathExample(temp.srcMarkerFile.getAbsolutePath()));
                 Gdx.app.log(TAG, "markerId :" + markerId + " set markerGray to HashMap");

                Planar<GrayU8> markerSrc = ConvertBufferedImage.convertFrom(srcBuffer, true, ImageType.pl(3, GrayU8.class));

                GrayU8 markerGray = new GrayU8(markerSrc.width, markerSrc.height);
                ConvertImage.average(markerSrc, markerGray); // convert src to gray

                featureMap.get(markerId).markerGray = markerGray;  // 加入 灰階資料到 feature 物件

                Gdx.app.log(TAG, "markerId :" + markerId + " set markerGray to HashMap");

        }else{
            Gdx.app.log(TAG,"markerQueue no data");
        }
    }

}
