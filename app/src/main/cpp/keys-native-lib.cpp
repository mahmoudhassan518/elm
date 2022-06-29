//
// Created by Mahmoud on 20/12/2021.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
Java_com_mahmoud_elm_modules_core_data_model_ElmKeys_geApiUrl(JNIEnv *env, jobject object) {
    return env->NewStringUTF("https://325bd587-5d9a-4145-8112-058bdba045f6.mock.pstmn.io/");
}