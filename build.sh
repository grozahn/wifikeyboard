#!/bin/bash

SDK_PATH=/opt/android-sdk
ADB="$SDK_PATH/platform-tools/adb"
AAPT="$SDK_PATH/build-tools/29.0.1/aapt"
AIDL="$SDK_PATH/build-tools/29.0.1/aidl"
DX="$SDK_PATH/build-tools/29.0.1/dx"
ZIPALIGN="$SDK_PATH/build-tools/29.0.1/zipalign"
SIGNAPK="tools/signapk"
PLATFORM="$SDK_PATH/platforms/android-17"

PACKAGE=com/volosyukivan

[[ ! -d obj ]] && mkdir obj

echo "[*] Cleaning..."
rm -rf bin/wifikeyboard.build.apk
rm -rf obj/*
rm -rf gen/$PACKAGE/*
rm -rf src/$PACKAGE/R.java

echo "[*] Copying resources..."
# cp html/key.html res/raw/key.html
cp html/wkbui.html res/raw/key.html

echo "[*] Generating R.java file..."
$AAPT package -f -m -J src -M AndroidManifest.xml -S res -I $PLATFORM/android.jar

echo "[*] Processing AIDLs..."
$AIDL -o gen -p $PLATFORM/framework.aidl -I src/$PACKAGE src/$PACKAGE/PortUpdateListener.aidl
$AIDL -o gen -p $PLATFORM/framework.aidl -I src/$PACKAGE src/$PACKAGE/RemoteKeyListener.aidl
$AIDL -o gen -p $PLATFORM/framework.aidl -I src/$PACKAGE src/$PACKAGE/RemoteKeyboard.aidl

echo "[*] Compiling..."
javac -d obj -classpath "src;gen/$PACKAGE" -bootclasspath $PLATFORM/android.jar gen/$PACKAGE/*java src/$PACKAGE/*.java

echo "[*] Translating in Dalvik bytecode..."
$DX --dex --output=classes.dex obj

echo "[*] Making APK..."
$AAPT package -f -m -F bin/wifikeyboard.unsigned.apk -M AndroidManifest.xml -S res -I $PLATFORM/android.jar
$AAPT add bin/wifikeyboard.unsigned.apk classes.dex

echo "[*] Aligning and signing APK..."
java -jar $SIGNAPK/signapk.jar -w $SIGNAPK/testkey.x509.pem $SIGNAPK/testkey.pk8 bin/wifikeyboard.unsigned.apk bin/wifikeyboard.signed.apk
$ZIPALIGN -f 4 bin/wifikeyboard.signed.apk bin/wifikeyboard.build.apk

rm bin/wifikeyboard.signed.apk
rm bin/wifikeyboard.unsigned.apk

[[ $1 == 'install' ]] && echo "[*] Installing using adb..." && $ADB install -r bin/wifikeyboard.build.apk
