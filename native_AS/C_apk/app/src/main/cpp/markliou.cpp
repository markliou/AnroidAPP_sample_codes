#include <jni.h>
#include <string>

std::string just_push_msg(){
    std::string fan = "This is fan push msg";
    return fan;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_c_1apk_MainActivity_stringFromJNIfan(
        JNIEnv* env,
        jobject /* this */) {
    //std::string fan = "This is fan lib";
    std::string fan = just_push_msg() ;
    return env->NewStringUTF(fan.c_str());
}

