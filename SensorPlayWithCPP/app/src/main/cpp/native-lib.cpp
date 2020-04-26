#include <jni.h>
#include <string>

std::string msg() ;

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_sensorplaywithcpp_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_sensorplaywithcpp_MainActivity_stringFromJNIMarkMsg(
        JNIEnv* env,
        jobject MainActivity /* this */){
    std::string hello = msg();
    return env->NewStringUTF(hello.c_str());
}

std::string msg(){
    std::string hello =  "Mark's message" ;
    return hello;
}
