################################################################################
#
# BubbleSortDemoApp Android make file
#
# Copyright (c) 2012 - 2014. Trantor Software Pvt Ltd. All rights reserved.
#
################################################################################

LOCAL_PATH := $(call my-dir)

################################################################################
# Define the application module:
################################################################################
include $(CLEAR_VARS)

LOCAL_MODULE           := bubble-sort
LOCAL_SRC_FILES        := bubble-sort.c
LOCAL_LDLIBS           += -llog


include $(BUILD_SHARED_LIBRARY)

