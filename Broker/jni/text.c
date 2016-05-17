#include <jni.h>
#include <head.h>
//#include <stdio.h>
//#include <string.h>
//#include <android/log.h>
//#define LOG_TAG "MediaRecorder"
//#define LOG(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG, __VA_ARGS__)


Java_broker_iser_ruc_edu_cn_Native_Mod(JNIEnv *env,jobject thiz){
	modConnect();
	return;
    //return (*env)->NewStringUTF(env, "Hello from JNI !");
}


