package com.example.tensorflow_porting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/*
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;
*/


import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class MainActivity extends AppCompatActivity {

    private static final String MODEL_PATH = "cnn.tflite";

    private Classifier classifier;

    private Executor executor = Executors.newSingleThreadExecutor();
    private TextView textViewResult;
    private Button btnDetectObject, btnToggleCamera;
    private ImageView imageViewResult;
    //private CameraView cameraView;

    // Used to load the 'native-lib' library on application startup.
    // we also append the tensorflow inferecing module here
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("tensorflow_inference");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        // tensorflow model calling part
        initTensorFlowAndLoadModel();

    }

    // tensorflow lite model calling
    private void initTensorFlowAndLoadModel(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    classifier = TensorflowImageClassifier.create(
                            getAssets(),
                            MODEL_PATH
                    );
                } catch (final Exception e){
                    throw new RuntimeException("error initialization of Tensorflow model !!", e);
                }
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    /**
     The tensorflow lite source code is adapted from:
     https://github.com/amitshekhariitbhu/Android-TensorFlow-Lite-Example
     **/
}
