package com.tsu.develop.plugdemo.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Utils {
    private static final String TAG = "Utils";
    public static String copyAssetAndWrite(Context context , String fileName){
        try{
            File cacheDir = context.getCacheDir();
            if(!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir , fileName);
            if(!outFile.exists()){
                boolean res = outFile.createNewFile();
                if(res){
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[((InputStream) is).available()];
                    int byteCount;
                    while((byteCount = is.read(buffer)) !=-1){
                        fos.write(buffer , 0 , byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Log.d(TAG , "Download finish !");//Toast.makeText(context , "Download finish !" , Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            }else{
                Log.d(TAG , "File had exist !");
                //Toast.makeText(context , "File had exist !" , Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
