/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_ogre4j_AnimationState */

#ifndef _Included_org_ogre4j_AnimationState
#define _Included_org_ogre4j_AnimationState
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: table */
/*
 * Class:     org_ogre4j_AnimationState
 * Method:    addTime
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_addTime
  (JNIEnv *, jclass, jint, jfloat);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    createInstance
 * Signature: (Ljava/lang/String;FFFZ)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_AnimationState_createInstance
  (JNIEnv *, jclass, jstring, jfloat, jfloat, jfloat, jboolean);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    dispose
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_dispose
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    getAnimationName
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_ogre4j_AnimationState_getAnimationName
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    getEnabled
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_AnimationState_getEnabled
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    getLength
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_org_ogre4j_AnimationState_getLength
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    getLoop
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_AnimationState_getLoop
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    getTimePosition
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_org_ogre4j_AnimationState_getTimePosition
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    getWeight
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_org_ogre4j_AnimationState_getWeight
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    setAnimationName
 * Signature: (ILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_setAnimationName
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    setEnabled
 * Signature: (IZ)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_setEnabled
  (JNIEnv *, jclass, jint, jboolean);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    setLength
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_setLength
  (JNIEnv *, jclass, jint, jfloat);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    setLoop
 * Signature: (IZ)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_setLoop
  (JNIEnv *, jclass, jint, jboolean);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    setTimePosition
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_setTimePosition
  (JNIEnv *, jclass, jint, jfloat);

/*
 * Class:     org_ogre4j_AnimationState
 * Method:    setWeight
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_AnimationState_setWeight
  (JNIEnv *, jclass, jint, jfloat);

#ifdef __cplusplus
}
#endif
#endif