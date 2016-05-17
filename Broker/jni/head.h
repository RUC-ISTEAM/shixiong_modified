#include <stdio.h>
#include <android/log.h>
#include <string.h>
#include <dlfcn.h>

#define LOG_TAG "--- GOTMODIFY ---"
#define LOG(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG, __VA_ARGS__)

#define true 1
#define false 0
#define four 4
#define two 2

void changeGot();
void modConnect();
//void printLog();

