package com.yue.ar.suite;

import java.io.File;

import boofcv.struct.image.GrayU8;

/**
 * Created by luokangyu on 2017/11/18.
 */

public class Feature {

    String markerId = null;
    File srcMarkerFile = null;
    GrayU8 markerGray = null;

    public Feature(String markerId , File srcMarkerFile){

        this.markerId = markerId;
        this.srcMarkerFile = srcMarkerFile;

    }











}
