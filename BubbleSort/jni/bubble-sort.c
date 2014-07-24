#include<stdio.h>
#include<time.h>
#include<android/log.h>
#include "bubble-wrapper.h"
#define LOGGER(...) ((void)__android_log_print(ANDROID_LOG_INFO, "bubble sort", __VA_ARGS__))

JNIEXPORT jlong JNICALL Java_com_example_bubblesort_MainActivity_bubbleSortC(JNIEnv *env, jobject obj, jintArray input)
{
  int c, d, swap;
  struct timeval tstart, tend;
  jint * input_array;
  jsize input_length = (*env)->GetArrayLength(env, input);
  input_array = (*env)->GetIntArrayElements(env, input, 0);
  gettimeofday(&tstart, NULL);
  for (c = 0 ; c < ( input_length - 1 ); c++)
  {
    for (d = 0 ; d < input_length - c - 1; d++)
    {
      if (input_array[d] > input_array[d+1]) /* For decreasing order use < */
      {
        swap       = input_array[d];
        input_array[d]   = input_array[d+1];
        input_array[d+1] = swap;
      }
    }
  }
  gettimeofday(&tend, NULL);
  long time_diff = (tend.tv_sec - tstart.tv_sec)*1000000 + (tend.tv_usec - tstart.tv_usec);
  //(*env)->ReleaseIntArrayElements(env, input, input_array, 0);
  return time_diff;
}
