#JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_20.jdk/Contents/Home
JAVA_HOME=~/Projects/jvm

javac Main.java
clang -o app -I $JAVA_HOME/include -I $JAVA_HOME/include/darwin -L $JAVA_HOME/jre/lib/jli/ myjava.cpp -ljli

export LD_LIBRARY_PATH=$JAVA_HOME/jre/lib/jli

# Oracle bug https://bugs.openjdk.java.net/browse/JDK-7131356
# requires JLI to be linked before server VM and instead of libjvm




