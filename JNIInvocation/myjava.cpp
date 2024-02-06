#include <stdio.h>
#include <jni.h>       /* where everything is defined */

int main() 
{
  JavaVM *jvm;       /* denotes a Java VM */
  JNIEnv *env;       /* pointer to native method interface */

  JavaVMOption options[4];
  int n = 0;
  options[n++].optionString = "-Djava.class.path=.:./";

  JavaVMInitArgs vm_args;
  vm_args.version = JNI_VERSION_1_6;
  vm_args.nOptions = n;
  vm_args.options = options;

  /* Get the default initialization arguments and set the class 
   * path */
  long status = JNI_GetDefaultJavaVMInitArgs(&vm_args);
  if (status == JNI_ERR)
    printf("GetDefaultJavaVM failed with %ld\n", status);

  /* load and initialize a Java VM, return a JNI interface 
   * pointer in env */
  JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);

  /* invoke the Main.test method using the JNI */
  jclass cls = env->FindClass("Main");
  jmethodID mid = env->GetStaticMethodID(cls, "test", "(I)V");
  env->CallStaticVoidMethod(cls, mid, 100);

  /* We are done. */
  jvm->DestroyJavaVM();
}
