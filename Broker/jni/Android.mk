LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
LOCAL_LDLIBS :=-llog
LOCAL_MODULE    := text
LOCAL_SRC_FILES := text.c Function.c NewFunction.c
include $(BUILD_SHARED_LIBRARY)