#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" hdf5
    popd
    exit
fi

LIBREMIDI=libremidi-master
download "https://github.com/jcelerier/libremidi/archive/refs/heads/master.zip" $LIBREMIDI.zip

mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`
echo "Decompressing archives..."
unzip ../$LIBREMIDI.zip
cd $LIBREMIDI

case $PLATFORM in
    linux-x86_64)
        mkdir build
        cd build
        cmake -DCMAKE_INSTALL_PREFIX=$INSTALL_PATH ..
        make -j $MAKEJ
        make install
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../..
