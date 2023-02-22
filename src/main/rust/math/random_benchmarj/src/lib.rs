use jni::{sys::{jint, jfloat}, JNIEnv, objects::JObject};
use rand::{thread_rng, Rng};
#[no_mangle]
pub extern "system" fn Java_Benchmarks_NativeBenchmarks_Math_RNGnative_setup(_env:JNIEnv, _obj:JObject) {
}
#[no_mangle]
pub extern "system" fn Java_Benchmarks_NativeBenchmarks_Math_RNGnative_execute(_env:JNIEnv, _obj:JObject) {
    let mut rng = thread_rng();
    let _num:u32 = rng.gen::<u32>();
}
